# 🔥 Stream API (Functional & Lazy)

✅ **Streams → Lazy (computed when needed)**  
   - Functional-style API  
   - **Immutable & thread-safe**  

✅ **Two Types of Operations**  
   - **Intermediate** → Returns a new `Stream` (chainable) - map/filter
   - **Terminal** → Produces a result, **ends the stream**  

   - `.collect(Collectors.toList())` → Convert to list  
   - `.forEach(System.out::println)` → Iterate  
   - `.reduce((a, b) -> a + b)` → Aggregate  
   - `.min(Comparator.comparing(x -> x))` → Min value  
   - `.max(Comparator.comparing(x -> x))` → Max value  

✅ **Example**  
```java
List<Integer> nums = Arrays.asList(1, 2, 3, 4);
List<Integer> evens = nums.stream()
    .filter(n -> n % 2 == 0)   // ✅ Intermediate
    .collect(Collectors.toList()); // ✅ Terminal
```
# 🔥 **Concurrency**  

✅ **6 Thread States**  
   - 🆕 `NEW` → Created, not started  
   - 🏃 `RUNNABLE` → Ready to run  
   - ✅ `TERMINATED` → Completed  
   - ⛔ `BLOCKED` → Waiting for a lock  
   - ⏳ `WAITING` → Indefinite wait (`wait()`)  
   - ⏱️ `TIMED_WAITING` → Waiting w/ timeout (`sleep(ms)`)  

✅ **4 Ways to Create Threads**  
   1️⃣ **Extend `Thread` & override `run()`**  
   ```java
   class MyThread extends Thread {
       public void run() { System.out.println("Thread running"); }
   }
   new MyThread().start();
  ```
2️⃣ Implement Runnable & pass to Thread (no return, no exceptions)
```java
class MyRunnable implements Runnable {
    public void run() { System.out.println("Runnable running"); }
}
new Thread(new MyRunnable()).start();
```
3️⃣ Implement Callable<T> (returns value, uses FutureTask, has exceptions)
```java
class MyCallable implements Callable<Integer> {
    public Integer call() { return 42; }
}
FutureTask<Integer> task = new FutureTask<>(new MyCallable());
new Thread(task).start();
System.out.println(task.get()); // ✅ Returns 42
```

4️⃣ Use Thread Pool (ExecutorService)
```java
ExecutorService pool = Executors.newFixedThreadPool(2);
pool.submit(() -> System.out.println("ThreadPool running"));
pool.shutdown();
```
![image](https://github.com/user-attachments/assets/8d316374-8e99-4522-8f0e-cb1f44ac35be)


default threadpools: newFixedThreadPool, newSingleThreadPool, newCachedThreadPool

# 🔥 CompletableFuture 

✅ **Extends `Future` & `CompletionStage`**  
   - Supports **chaining & async execution** - but using simple Futures doesn't allow chaining

✅ **Creating Async Tasks**  
   - `runAsync(Runnable, Executor)` → No return value  
   - `supplyAsync(Supplier<T>, Executor)` → Returns `T`  -from supplier functional interface
   - also have thenaccept (from consumer interface)

✅ **Chaining Tasks**  
   - `.thenApply(f)` → Transforms result (same thread)  
   - `.thenApplyAsync(f)` → Transforms w/ new thread

✅ **exceptions**  - .exceptionally with lambda (vanill Future can't handle excepions, can only throw)
- .handle(value, exception) to pass callback (bifunctional interface); 

✅ **Example**  
```java
CompletableFuture<Integer> cf = CompletableFuture
    .supplyAsync(() -> 10)   // Async computation
    .thenApply(x -> x * 2);  // Chain operation
System.out.println(cf.get()); // Output: 20
```

140:47
