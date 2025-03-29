package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.*;
import java.util.concurrent.*;

public class HW4567 {
    public static void main(String[] args) {
        stream_demo();
        thread_demo();
        completableFutureDemo();
    }

    public static void stream_demo(){
        Student student_A = new Student("Akash", 15, 82);
        Student student_B = new Student("Anna", 15, 0);
        Student student_C = new Student("Gomesh", 15, 99);

        List<Student> students = new ArrayList<>(Arrays.asList(student_A, student_B, student_C));

        Stream<Student> student_stream = students.stream();

        System.out.println("Names starting w A: ");
        student_stream.filter(student -> student.getName().startsWith("A")).forEach(System.out::println);

        System.out.println("Those who scored > 60: ");
        students.stream().filter(student -> student.getScore() > 60).forEach(System.out::println);

        System.out.println("All names: ");
        students.stream().forEach(student -> System.out.println(student.getName()));

        int total_score = students.stream().mapToInt(Student::getScore).sum();
        System.out.println("Total Score: " + total_score);

        Map<Integer, Long> ageFrequency = students.stream()
                .collect(Collectors.groupingBy(Student::getAge, Collectors.counting()));

        System.out.println("Age Frequency: " + ageFrequency);

    }

    public static void thread_demo() {
        new ThreadClass().start();

        new Thread(new RunnableClass()).start();

        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            executor.submit(() -> System.out.println("Using thread pool")); // Thread pool task

            Future<String> future = executor.submit(new CallableClass()); // Callable task
            System.out.println("Callable Result: " + future.get()); // Ensure the result is retrieved
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown(); // Shutdown only after all tasks are submitted
        }
    }

    private static void completableFutureDemo() {
        CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 1;
                })
                .thenApplyAsync(result -> result * 4)
                .thenApply(result -> result + 10)
                .thenAcceptAsync(result -> System.out.println("Final Result: " + result))
                .exceptionally(ex -> {
                    System.out.println("Error: " + ex.getMessage());
                    return null;
                });

        System.out.println("Doing some other work in parallel...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    private static class ThreadClass extends Thread {
        public void run() {
            System.out.println("Using Thread class");
        }
    }

    private static class RunnableClass implements Runnable {
        public void run() {
            System.out.println("Using Runnable");
        }
    }

    private static class CallableClass implements Callable<String> {
        public String call() throws Exception {
            return "Thread using Callable";
        }
    }

    private static class Student{
        private String name;
        private int age;
        private int score;

        public Student(String name, int age, int score) {
            this.name = name;
            this.age = age;
            this.score = score;
        }

        public int getAge() {
            return age;
        }

        public int getScore() {
            return score;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Student{" +
                    ", name='" + name  +
                    '}';
        }
    }
}

/*
4b- intermediate can be chained, lazy loading, returns stream- terminal eager
4c- new, runnable, blocked, waiting,timed waiting, terminated
4e- reuse threads, less overhead
4f- fixed thread pool- queue size unlimited; cached thread pool- core threads size unltd
4g- store result of async; no callbacks or composition, no manual completion
4h/i - extends future and enables compositon, exception handling
4j- lock is fine grained, synchronized locks whole class; locks can be fair and timed, need manual release
4k- notify/broadcast on condition variables

5a- 2 threads waiting on each other's resources
deadlock ex:
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            lock1.lock();
            lock2.lock();
            lock1.unlock();
            lock2.unlock();
        });

        Thread thread2 = new Thread(() -> {
            lock2.lock();
            lock1.lock();
            lock2.unlock();
            lock1.unlock();
        });

        thread1.start();
        thread2.start();
    }
}

avoid- use try locks, avoid nested locks, use thread safe data structures
4h- normal sync method- locks instance; static sync- locks class

import java.util.concurrent.locks.ReentrantLock;

public class DeadlockExample {
    private static final ReentrantLock lock1 = new ReentrantLock();
    private static final ReentrantLock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            lock1.lock();
            lock2.lock();
            lock1.unlock();
            lock2.unlock();
        });

        Thread thread2 = new Thread(() -> {
            lock2.lock();
            lock1.lock();
            lock2.unlock();
            lock1.unlock();
        });

        thread1.start();
        thread2.start();
    }
}
6-
factory- create objs but abstract constructor
builder- create obj with many optional components, chained creation
observer- like event listener, notify many obj from one obj
decorator pattern- add functionality dynamically
proxy- can be static through class or dynamic through reflection- provides surrogate eccess point
spring annotations- using reflections
- **HTTP Status Codes**:
  - **200**: OK, request succeeded.
  - **201**: Created, resource successfully created.
  - **202**: Accepted, request accepted but not processed yet.
  - **204**: No Content, request succeeded but no content returned.
  - **301**: Moved Permanently, resource has been permanently moved to a new URL.
  - **307**: Temporary Redirect, request should be repeated at another URL temporarily.
  - **400**: Bad Request, malformed request.
  - **401**: Unauthorized, user must authenticate.
  - **403**: Forbidden, server refuses to authorize request.
  - **404**: Not Found, resource not found.
  - **500**: Internal Server Error, server error processing request.

- **HTTP**: A protocol used for transferring data over the web.

- **HTTP Methods**:
  - **GET**: Retrieves data.
  - **POST**: Sends data.
  - **PUT**: Updates data.
  - **DELETE**: Removes data.
  - **PATCH**: Partially updates data.

- **POST vs PATCH**:
  - **POST**: Used for creating resources.
  - **PATCH**: Used for partial updates.

- **POST vs PUT**:
  - **POST**: Creates resources.
  - **PUT**: Updates or replaces resources.

- **TCP 3-Way Handshake**: The connection process between client and server: SYN, SYN-ACK, ACK.

- **TCP vs UDP**:
  - **TCP**: Reliable, connection-oriented, with error checking.
  - **UDP**: Faster, connectionless, without error checking.

- **Tomcat**: A web server and servlet container for Java.
  - **Components**: HTTP Connector, Web Server, Servlet Container.

- **Spring IOC (Inversion of Control)**: A design pattern where object creation and management are handled by the container.

- **IOC Container**: Responsible for managing beans, their lifecycle, and dependencies.

- **Advantage of IOC**: Decouples components, improving flexibility and testability.

- **Dependency Injection (DI)**: A technique where an object's dependencies are provided by an external source rather than being created inside the object.

- **How to do Dependency Injection**: Use annotations like `@Autowired` or constructor injection.

- **@Component vs @Bean**:
  - **@Component**: Defines a Spring bean automatically.
  - **@Bean**: Registers a bean manually in a `@Configuration` class.

- **@Configuration**: Indicates a class that contains bean definitions.

- **AOP (Aspect-Oriented Programming)**: Allows separating cross-cutting concerns.

- **JointPoint and Aspect in AOP**:
  - **JointPoint**: A point during execution (method call, field access).
  - **Aspect**: The module that handles cross-cutting concerns.

- **Spring Bean Scope**: Defines the lifecycle and visibility of beans.
  - Types: Singleton, Prototype, Request, Session, GlobalSession.

 */
