### 🔥**Spring - IoC & AOP**  

#### **🛠 Inversion of Control (IoC) & Dependency Injection (DI)**  
- **Tightly vs. Loosely Coupled** → change 1 have to change composed ones  
- **IoC Container** → Manages object lifecycle & dependencies.  
- **Dependency Injection (DI)** → Injects dependent objects instead of manually
- **Advantage**: Can unit test composed classes individually
- Factory Pattern, Service Locator and Sevice Design patterns also can achieve, in addition to DI
- IOC container implements Application Context interface, which extends Bean Factory interface; there are multiple implementations all considered IOC container, like XML, Class Path
  #### **📌 Types of DI:**  
✅ **Constructor Injection** (Preferred for required dependencies)  
```java
@Component
class Engine {
    void start() { System.out.println("Engine started!"); }
}

@Component
class Car {
    private final Engine engine;
    
    @Autowired
    public Car(Engine engine) { this.engine = engine; }

    void drive() { 
        engine.start(); 
        System.out.println("Car is driving!"); 
    }
}
```

✅ Setter Injection (Used for optional dependencies)
```java
@Autowired
    public void setEngine(Engine engine) { this.engine = engine; }
```

✅ Field Injection (Less recommended, harder to unit test), can prevent cyclic dependencies 
```java
class Car {
    @Autowired
    private Engine engine;
```
**🛠 Java beans**
- bean scope, can be denoted with @Scope('prototype') to get 1 instance each time
- default is singleton
- also request (new for ea request), application (one for each application, rather than application context like in singleton), session

**🛠 Bean lifecycle**
- @PostConstruct annotation for custom init, runs after managed bean init code
- @Predestroy for destructor logic

**🛠 @Configuration**
Marks a class as a Spring configuration class. ; Contains @Bean methods that define manual bean creation.; Ensures that beans are created as singletons (when combined with @Bean)
- 🔹 Without @Configuration, each method call would create a new instance.
- 🔹 But with @Configuration, Spring ensures only one instance per bean (singleton by default).

- ✅ When to use @Configuration? Use it when you need a centralized place to configure and create beans manually.- NEED TO CALL NEW
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // Marks this as a config class
public class AppConfig {

    @Bean
    public MyComponent myComponent() {
        return new MyComponent();
    }

    @Bean
    public MyService myService() {
        return new MyService(myComponent());  // Injecting manually
    }
}

```
🌀 **Aspect-Oriented Programming (AOP)**
Separates cross-cutting concerns (e.g., logging, security).

Uses Aspects (concerns), Advice (logic), Join Points (execution points), and Pointcuts (where to apply advice).
✅ **Example: Logging Aspect**  
```java
@Aspect
@Component
class LoggingAspect {
    
    @Before("execution(* com.example.service.*.*(..))") 
    public void logBeforeMethod() {
        System.out.println("Method execution started!");
    }
    
    @After("execution(* com.example.service.*.*(..))") 
    public void logAfterMethod() {
        System.out.println("Method execution finished!");
    }

    @Around("execution(* com.example.service.*.*(..))")
    public Object logAroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Method execution started!");
        Object result = joinPoint.proceed(); // Proceed with method execution
        System.out.println("Method execution finished!");
        return result;
    }
}
```
📌 AOP Key Concepts:
Aspect: A modularization of a concern (e.g., logging or security).

Advice: Code to be executed at specific join points.

Before: Executed before the join point.

After: Executed after the join point.

Around: Wraps around the join point, can modify the execution.

Join Point: A point in the execution flow (method execution, field access).

Pointcut: Expression that defines where advice should apply (e.g., method execution or class).

✅ **Example: Logging Aspect**  
```java
@Aspect
@Component
class LoggingAspect {

    // Define a reusable pointcut expression
    @Pointcut("execution(* com.example.service.*.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBefore() {
        System.out.println("Executing before a service method!");
    }

    @After("serviceMethods()")
    public void logAfter() {
        System.out.println("Executing after a service method!");
    }

    @Around("serviceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before execution: " + joinPoint.getSignature());
        Object result = joinPoint.proceed();  // Execute method
        System.out.println("After execution: " + joinPoint.getSignature());
        return result;
    }
}
```
### 🔥**Spring Core vs Spring MVC vs Spring Boot** 
MVC - Supports controllers (@Controller), request mappings (@RequestMapping), view resolvers, and form handling.
Uses DispatcherServlet to handle requests.
Spring Boot
Simplifies Spring application development by reducing boilerplate configuration.

Auto Configuration: Uses @SpringBootApplication to auto-configure dependencies.

Embedded servers like Tomcat, Jetty (no need for external setup).

Opinionated defaults but allows customization.

Provides Spring Boot Starters (predefined dependency setups like spring-boot-starter-web).
```java
// Spring Core (Manual Configuration)
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
MyService myService = context.getBean(MyService.class);

// Spring MVC (Controller Example)
@Controller
@RequestMapping("/hello")
public class HelloController {
    @GetMapping
    public String sayHello(Model model) {
        model.addAttribute("message", "Hello, Spring MVC!");
        return "hello"; // Returns view "hello.jsp" or "hello.html"
    }
}

// Spring Boot (Auto Configuration + REST API)
@RestController
@SpringBootApplication
public class MySpringBootApp {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApp.class, args);
    }

    @GetMapping("/greet")
    public String greet() {
        return "Hello, Spring Boot!";
    }
}
```

1:18
