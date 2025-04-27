import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import os
from math_functions import *
from plot_utils import *
import matplotlib.pylab as pylab

params = {'legend.fontsize': 'xx-large',
         'axes.labelsize': 'xx-large',
         'axes.titlesize':'xx-large',
         'xtick.labelsize':'x-large',
         'ytick.labelsize':'x-large'}
pylab.rcParams.update(params)

# Constants
rootDir = os.getcwd()
dataDir = os.path.abspath(os.path.join(rootDir, '..', '..', '..', 'data'))
testTypes = ['executor_service', 'thread_factory']
dirGraphResults = os.path.join(os.path.join(rootDir, '..', '..', '..', 'data_statistics_report'))

if (os.path.isdir(dirGraphResults) is not True):
    os.mkdir(dirGraphResults)

def extractAveragesFromTestsArrayFullPaths(csvTestPaths):
    dataAverage = []
    for test_path in csvTestPaths:
        file = pd.read_csv(test_path, delimiter=',', header=0)
        data = np.array(file)
        dataAverage.append(data)
    return np.average(dataAverage, axis=0)

def filterVirtualThreadTests(word):
    if "virtual" in word.lower():
        return True
    return False

def filterPlatformThreadTests(word):
    if "platform" in word.lower():
        return True
    return False

def getAllTestSystems():
    return os.listdir(dataDir)

def addFullPathToArrayofCSVFiles(array, parentPath):
    arrayWithFullPaths = []
    for csv_file in array:
        arrayWithFullPaths.append(os.path.join(parentPath, csv_file))
    return arrayWithFullPaths

def getPlatformThreadTestsFullPathsCSV(parentPath, testsInsideParent):
    executorServicePlatformThreadTests = list(filter(filterPlatformThreadTests, testsInsideParent))
    return addFullPathToArrayofCSVFiles(executorServicePlatformThreadTests, parentPath)

def getVirtualThreadTestsFullPathsCSV(parentPath, testsInsideParent):
    executorServicePlatformThreadTests = list(filter(filterVirtualThreadTests, testsInsideParent))
    return addFullPathToArrayofCSVFiles(executorServicePlatformThreadTests, parentPath)

def generateTestResulrForHardwareSystem(hardwareSystemDirName):

    # Path for test data for a specific system: ../data/macos-m3-16-core/
    dirForTestSystemOS = os.path.join(dataDir, hardwareSystemDirName)

    # List of all tests under specific test system: ../data/macos-m3-16-core/*
    # [addition, addition_simulated_blocking, ...]
    allTestsForHardwareSystem = os.listdir(dirForTestSystemOS)

    # Loop through list of all test for specific test system
    for testName in allTestsForHardwareSystem:
        dirExecutorServiceTests = os.path.join(dirForTestSystemOS, testName, testTypes[0])
        dirThreadFactoryTests = os.path.join(dirForTestSystemOS, testName, testTypes[1])

        # Result all tests: [1_addition-simulated-blocking-1ms_platform_s.csv, 1_addition-simulated-blocking-1ms_virtual_s.csv, ...]
        executorServiceAllTests = os.listdir(dirExecutorServiceTests)
        threadFactoryAllTests = os.listdir(dirThreadFactoryTests)

        # Data headers
        dataHeaders = open(os.path.join(dirExecutorServiceTests, executorServiceAllTests[0])).readline().split(',')

        # Executor service test paths: platform/virtual threads
        executorServicePlatformThreadTestsFullPaths = getPlatformThreadTestsFullPathsCSV(dirExecutorServiceTests, executorServiceAllTests)
        executorServiceVirtualThreadTestsFullPaths = getVirtualThreadTestsFullPathsCSV(dirExecutorServiceTests, executorServiceAllTests)

        # Thread factory tests paths: platform/virtual threads
        threadFactoryPlatformThreadTestsFullPaths = getPlatformThreadTestsFullPathsCSV(dirThreadFactoryTests, threadFactoryAllTests)
        threadFactoryVirtualThreadTestsFullPaths = getVirtualThreadTestsFullPathsCSV(dirThreadFactoryTests, threadFactoryAllTests)

        # Calculate averages
        executorServicePlatfromTestsAverages = extractAveragesFromTestsArrayFullPaths(executorServicePlatformThreadTestsFullPaths)
        executorServiceVirtualTestsAverages = extractAveragesFromTestsArrayFullPaths(executorServiceVirtualThreadTestsFullPaths)
        threadFactoryPlatformTestsAverages = extractAveragesFromTestsArrayFullPaths(threadFactoryPlatformThreadTestsFullPaths)
        threadFactoryVirtualTestsAverages = extractAveragesFromTestsArrayFullPaths(threadFactoryVirtualThreadTestsFullPaths)

        # Figure and axes for matplot
        figure, ((ax1, ax2, ax3), (ax4, ax5, ax6)) = plt.subplots(2, 3, figsize=(30, 15))

        # Empirical data plots
        plotEmpiricalData(ax1, executorServicePlatfromTestsAverages, executorServiceVirtualTestsAverages, "Executor Service", dataHeaders[0], dataHeaders[1])
        plotEmpiricalData(ax4, threadFactoryPlatformTestsAverages, threadFactoryVirtualTestsAverages, "Plain Threads", dataHeaders[0], dataHeaders[1])

        # Time metrics plots
        plotTimeMetricsData(ax2, executorServicePlatfromTestsAverages, executorServiceVirtualTestsAverages, "Executor Service", dataHeaders[0], dataHeaders[1])
        plotTimeMetricsData(ax5, threadFactoryPlatformTestsAverages, threadFactoryVirtualTestsAverages, "Plain Threads", dataHeaders[0], dataHeaders[1])

        # Performance difference
        plotPerformanceDifferenceStatistics(ax3, ax6, executorServicePlatfromTestsAverages, executorServiceVirtualTestsAverages, 
                                      threadFactoryPlatformTestsAverages, threadFactoryVirtualTestsAverages, dataHeaders[1])

        # figure.suptitle(testName, fontsize=28)

        figure.tight_layout(pad=1.2, w_pad=10, h_pad=10)

        # Create directory for test system hardware
        # for storing all graphs generated
        pathForSystemDirWithHardwareName = os.path.join(dirGraphResults, hardwareSystemDirName)
        pathForSystemDirToSaveGraphs = os.path.join(pathForSystemDirWithHardwareName, testName)
        if (os.path.isdir(pathForSystemDirToSaveGraphs) is not True):
            os.makedirs(pathForSystemDirToSaveGraphs)

        # Save individual plots as separate files
        saveAxesToFigure(figure, ax1, pathForSystemDirToSaveGraphs, "1_empirical_data_executor_service.png")
        saveAxesToFigure(figure, ax2, pathForSystemDirToSaveGraphs, "2_time_metrics_executor_service.png")

        saveAxesToFigure(figure, ax4, pathForSystemDirToSaveGraphs, "4_empirical_data_plain_threads.png")
        saveAxesToFigure(figure, ax5, pathForSystemDirToSaveGraphs, "5_time_metrics_plain_threads.png")

        saveAxesToFigure(figure, ax3, pathForSystemDirToSaveGraphs, "3_performance_difference.png")
        saveAxesToFigure(figure, ax6, pathForSystemDirToSaveGraphs, "6_performance_percent_difference.png")

        # Save single file with all plots inside
        figFileName = "FULL_DATA_" + testName + '.png'
        plt.savefig(os.path.join(pathForSystemDirToSaveGraphs, figFileName), dpi=300)

def saveAxesToFigure(figure, ax, pathForSystemDirToSaveGraphs, fileName):
    extent = ax.get_window_extent().transformed(figure.dpi_scale_trans.inverted())
    figure.savefig(os.path.join(pathForSystemDirToSaveGraphs, fileName), bbox_inches=extent.expanded(1.32, 1.3))