from read_and_generate_stats_test import *

print("Generating statistics from empirical data...")

testSystems = getAllTestSystems()
for test_system in testSystems:
    generateTestResulrForHardwareSystem(test_system)

print("Finished generating statistics.")
print()