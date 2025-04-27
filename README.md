# Virtual Thread Benchmark

This applciation measures the empirical performance of Platform an Virtual threads in Java. This application was developped briefly after the official release of Virtual threads in Java 21.

### Environment file
In order to run this application, you need to provide a `.env` file for some varaibles that cannot be retrieved at runtime
```
PROCESSOR_NAME=Processor
PROCESSOR_SPEED=3.0GHz
```

<div>
  <img src="https://github.com/edweo/virtual-thread-benchmark/blob/main/1.png" alt="Image" width="700" />
  <h2>Addition</h2>
  <img src="https://github.com/edweo/virtual-thread-benchmark/blob/main/data_statistics_report/Mac-OS-X_Processor-3.0GHz_CPUs-8/addition/FULL_DATA_addition.png" alt="Image" width="1000" />
  <h2>Simulated Blocking</h2>
  <img src="https://github.com/edweo/virtual-thread-benchmark/blob/main/data_statistics_report/Mac-OS-X_Processor-3.0GHz_CPUs-8/addition_simulated_blocking_10ms/FULL_DATA_addition_simulated_blocking_10ms.png" alt="Image" width="1000" />
  <h2>File Opening</h2>
  <img src="https://github.com/edweo/virtual-thread-benchmark/blob/main/data_statistics_report/Mac-OS-X_Processor-3.0GHz_CPUs-8/addition_file_opening/FULL_DATA_addition_file_opening.png" alt="Image" width="1000" />
  <h2>Insertion Sort</h2>
  <img src="https://github.com/edweo/virtual-thread-benchmark/blob/main/data_statistics_report/Mac-OS-X_Processor-3.0GHz_CPUs-8/insertion_sort_arrays_with_100000_elements/FULL_DATA_insertion_sort_arrays_with_100000_elements.png" alt="Image" width="1000"/>
  <h2>Recursive Merge Sort</h2>
  <img src="https://github.com/edweo/virtual-thread-benchmark/blob/main/data_statistics_report/Mac-OS-X_Processor-3.0GHz_CPUs-8/merge_sort/FULL_DATA_merge_sort.png" alt="Image" width="1000" />
  <h2>Sieve of Eratosthenes</h2>
  <img src="https://github.com/edweo/virtual-thread-benchmark/blob/main/data_statistics_report/Mac-OS-X_Processor-3.0GHz_CPUs-8/sieve_of_eratosthenes_10million_numbers/FULL_DATA_sieve_of_eratosthenes_10million_numbers.png" alt="Image" width="1000" />
</div>
