# Virtual Thread Benchmark

This applciation measures the empirical performance of Platform an Virtual threads in Java. This application was developped briefly after the official release of Virtual threads in Java 21.

### Test Scenarios
Each test case is tested using 4 different scenarios:
- **Plain Threads**
  - Platform Threads: `Thread.ofPlatform().unstarted(task)`
  - Virtual Threads: `Thread.ofVirtual().unstarted(task)`
- **Executor Service**
  - Platform Threads: `Executors.newCachedThreadPool()`
  - Virtual Threads: `Executors.newVirtualThreadPerTaskExecutor()`

### Concrete Tests
- **Addition (Thread Spawning & CPU Computation)**
  -  Creates a single counter object
which has a thread-safe method to increase the counter. Multiple threads are
spawned where each thread is tasked to increase the counter by 1.
- **Simulated Blocking (Sleep Thread-Blocking)**
  -  Similar test case to Addition,
but with one caveat - each thread sleeps for a given amount of time.
- **File Opening (I/O Thread Blocking)**
  - This test case is similar to Simulated Blocking,
but instead of using artificial sleeping in a thread, the thread instead opens 3
files which contain a number in the first row, reads the number in the file and adds
it to the local sum variable. After all 3 files have been read by the single thread, the
thread-local sum variable gets added to the counter variable using a thread-safe add
operation.
- **Array Sort with Insertion Sort (Heavy CPU Computations)**
  - This test takes multiple
equal arrays in terms of size and elements and tasks each thread with each
separate array to be completely sorted using Insertion Sort. Essentially this means
that 10 threads would completely sort 10 distinct arrays using Insertion Sort and the
runtime is measured when all 10 arrays have been sorted.
- **Recursive Merge Sort (Recursive Computation)**
  - Sorts an array using merge sort
using a divide and conquer strategy. Each division is offloaded to a new thread until
a base case is reached. When an array can’t be divided anymore, the sorting of
small fractions of the array starts and the merge step happens of different threads
until finally the result is a fully sorted array.
- **Sieve of Eratosthenes (Finding Prime Numbers)**
  - Calculates all primes up to 10
million using multiple threads where each thread is tasked with filtering all primes
up to 10 million numbers.

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
