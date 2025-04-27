fontsize = 16

def plotTimeMetrics(ax, data):
    x = data[:, 0]
    y = data[:, 1]
    ax.plot(x, y)
    # Returns: y_avg, y_min, y_max
    return round(sum(y) / len(y), 3), round(min(y), 7), round(max(y), 3)

def plotTimeMetricsData(ax, dataPlatform, dataVirtual, title, label_X, label_Y):
    tN_platform = plotTimeMetrics(ax, dataPlatform)
    tN_virtual = plotTimeMetrics(ax, dataVirtual)
    ax.set_title("Time metrics for " + title)
    ax.legend((
        "Platform: \nAVG_INCREASE="+str(tN_platform[0])+"\nMIN="+str(tN_platform[1])+"\nMAX="+str(tN_platform[2]),
        "Virtual: \nAVG_INCREASE="+str(tN_virtual[0])+"\nMIN="+str(tN_virtual[1])+"\nMAX="+str(tN_virtual[2])
        ))
    ax.set_xlabel(label_X)
    ax.set_ylabel(label_Y)

def plotEmpiricalData(ax, dataPlatform, dataVirtual, title, label_X, label_Y):
    x_platform = dataPlatform[:, 0]
    y_platform = dataPlatform[:, 1]
    x_virtual = dataVirtual[:, 0]
    y_virtual = dataVirtual[:, 1]

    ax.set_xscale('log', base=2)
    ax.set_title("Empirical performance " + title)
    ax.set_xlabel(label_X)
    ax.set_ylabel(label_Y)
    ax.scatter(x_platform, y_platform)
    ax.scatter(x_virtual, y_virtual)
    ax.legend(("Platform threads", "Virtual threads"))

def plotUsageCPU(ax, dataPlatform, dataVirtual, title, label_X, label_Y):
    x_platform = dataPlatform[:, 0]
    y_platform_cpu_usage = dataPlatform[:, 2]

    x_virtual = dataVirtual[:, 0]
    y_virtual_cpu_usage = dataVirtual[:, 2]

    ax.set_xscale('log', base=2)
    ax.set_title("CPU Usage " + title)

    ax.set_xlabel(label_X)
    ax.set_ylabel(label_Y)

    ax.plot(x_platform, y_platform_cpu_usage)
    ax.plot(x_virtual, y_virtual_cpu_usage)

    ax.legend(("Platform threads", "Virtual threads"))

def plotBarDiagramAverageUsageCPU(ax, dataPlatformExecutor, dataVirtualExecutor, dataPlatformPlain, dataVirtualPlain):
    avg_cpu_usage_platform_executor = sum(dataPlatformExecutor[:, 2]) / len(dataPlatformExecutor[:, 2])
    avg_cpu_usage_virtual_executor = sum(dataVirtualExecutor[:, 2]) / len(dataVirtualExecutor[:, 2])
    avg_cpu_usage_platform_plain = sum(dataPlatformPlain[:, 2]) / len(dataPlatformPlain[:, 2])
    avg_cpu_usage_virtual_plain = sum(dataVirtualPlain[:, 2]) / len(dataVirtualPlain[:, 2])

    bar_data = [avg_cpu_usage_platform_executor, avg_cpu_usage_virtual_executor, avg_cpu_usage_platform_plain, avg_cpu_usage_virtual_plain]
    x_labels = ["Executor\nPlatform", "Executor\nVirtual", "Plain\nPlatform", "Plain\nVirtual",]

    # Convert datapoints, for example 0.5 -> 50%
    converted_bar_data = []
    for cpu_usage_avg in bar_data:
        converted_bar_data.append(round(cpu_usage_avg*100, 3))

    bar_width = 0.7
    ax.set_title("Average JVM CPU Usage")
    ax.set_xlabel("")
    ax.set_ylabel("Average CPU Usage (%)")

    ax.bar(x_labels[0], converted_bar_data[0], color='blue', width=bar_width)
    ax.bar(x_labels[1], converted_bar_data[1], color='orange', width=bar_width)
    ax.bar(x_labels[2], converted_bar_data[2], color='green', width=bar_width)
    ax.bar(x_labels[3], converted_bar_data[3], color='red', width=bar_width)

    ax.legend((str(converted_bar_data[0])+"%", str(converted_bar_data[1])+"%", str(converted_bar_data[2])+"%", str(converted_bar_data[3])+"%"))

def plotMemoryUsageStatistics(ax, dataPlatform, dataVirtual, title, label_X, label_Y):
    x_platform = dataPlatform[:, 0]
    y_platform_ram_usage = dataPlatform[:, 3]

    x_virtual = dataVirtual[:, 0]
    y_virtual_ram_usage = dataVirtual[:, 3]

    ax.set_xscale('log', base=2)
    ax.set_title("Average JVM RAM usage " + title)

    ax.set_xlabel(label_X)
    ax.set_ylabel(label_Y)

    ax.plot(x_platform, y_platform_ram_usage)
    ax.plot(x_virtual, y_virtual_ram_usage)

    ax.legend(("Platform threads", "Virtual threads"))

def plotPerformanceDifferenceStatistics(ax, axProcDif, dataPlatformExecutor, dataVirtualExecutor, dataPlatformPlain, dataVirtualPlain, label_Y):
    total_time_platform_executor = round(sum(dataPlatformExecutor[:, 1]), 3)
    total_time_virtual_executor = round(sum(dataVirtualExecutor[:, 1]), 3)
    total_time_platform_plain = round(sum(dataPlatformPlain[:, 1]), 3)
    total_time_virtual_plain = round(sum(dataVirtualPlain[:, 1]), 3)

    x_labels = ["Executor\nPlatform", "Executor\nVirtual", "Plain\nPlatform", "Plain\nVirtual",]
    bar_width = 0.7

    # Runtimes graph
    ax.set_title("Runtime Perfromance (lower is better)")
    ax.set_xlabel("")
    ax.set_ylabel("Total " + label_Y)
    ax.bar(x_labels[0], total_time_platform_executor, color='blue', width=bar_width)
    ax.bar(x_labels[1], total_time_virtual_executor, color='orange', width=bar_width)
    ax.bar(x_labels[2], total_time_platform_plain, color='green', width=bar_width)
    ax.bar(x_labels[3], total_time_virtual_plain, color='red', width=bar_width)
    ax.legend((str(total_time_platform_executor), str(total_time_virtual_executor), str(total_time_platform_plain), str(total_time_virtual_plain)))

    # Runtimes % difference graph
    axProcDif.set_xlabel("")
    axProcDif.set_ylabel("% differnece runtime")

    executor_platform_faster = calculatePercentDifference(total_time_virtual_executor, total_time_platform_executor)
    executor_virtual_faster = calculatePercentDifference(total_time_platform_executor, total_time_virtual_executor)
    plain_platform_faster = calculatePercentDifference(total_time_virtual_plain, total_time_platform_plain)
    plain_virtual_faster = calculatePercentDifference(total_time_platform_plain, total_time_virtual_plain)

    axProcDif.set_title("Runtime Perfromance % difference (higher is better) \n baseline 100% opposite threading model")
    axProcDif.bar(x_labels[0], executor_platform_faster[0], color='blue', width=bar_width)
    axProcDif.bar(x_labels[1], executor_virtual_faster[0], color='orange', width=bar_width)
    axProcDif.bar(x_labels[2], plain_platform_faster[0], color='green', width=bar_width)
    axProcDif.bar(x_labels[3], plain_virtual_faster[0], color='red', width=bar_width)
    
    axProcDif.legend((formatPerrcentDifferenceString(executor_platform_faster[0], executor_platform_faster[1], executor_platform_faster[2], "Executor Virtual"), 
                      formatPerrcentDifferenceString(executor_virtual_faster[0], executor_virtual_faster[1], executor_virtual_faster[2], "Executor Platform"), 
                      formatPerrcentDifferenceString(plain_platform_faster[0], plain_platform_faster[1], plain_platform_faster[2], "Plain Virtual"), 
                      formatPerrcentDifferenceString(plain_virtual_faster[0], plain_virtual_faster[1], plain_virtual_faster[2], "Plain Platform")))

    # Line comparing to other test
    axProcDif.axhline(y = 100, color = 'purple', linestyle = '-')

def calculatePercentDifference(time_reference_point, time_gain_calculate):
    fractional_difference = time_reference_point / time_gain_calculate
    percent_difference_relative_to_100 = fractional_difference * 100
    percent_gain = 0

    if percent_difference_relative_to_100 > 100:
        percent_gain = percent_difference_relative_to_100 - 100
    elif percent_difference_relative_to_100 < 100:
        percent_gain = (fractional_difference - 1) * 100

    return [round(percent_difference_relative_to_100, 3), round(percent_gain, 3), round(fractional_difference, 3)]

def formatPerrcentDifferenceString(total_percent, percent_difference, fraction, compared_to):
    sign = "+" if total_percent >= 100 else ""
    return f"{sign}{percent_difference}% ({fraction}x) of {compared_to}"