package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
public class HW891011 {

    public static void main(String[] args) {
        SpringApplication.run(HW891011.class, args);
    }

    @GetMapping("/path/{id}")
    public String pathExample(@PathVariable String id) {
        System.out.println("PathVariable received: " + id);
        return "PathVariable: " + id;
    }

    @GetMapping("/query")
    public String requestParamExample(@RequestParam String name) {
        System.out.println("RequestParam received: " + name);
        return "RequestParam: " + name;
    }

    @PostMapping("/body")
    public String requestBodyExample(@RequestBody String body) {
        System.out.println("RequestBody received: " + body);
        return "RequestBody: " + body;
    }

    @GetMapping("/response")
    @ResponseBody
    public String responseBodyExample() {
        String response = "This is a @ResponseBody response";
        System.out.println(response);
        return response;
    }
}



/*
What is IoC (Inversion of Control) - object control to container, framework manages lifecycle, reduces coupling
What is DI (Dependency Injection) - dependencies injected at runtime, promotes loose coupling, improves testability, done via constructor/setter/field
What is AOP (Aspect-Oriented Programming) Separates cross-cutting concerns, modularizes logging/security
Aspect: module for cross-cutting concern
JoinPoint: point in execution (e.g. method call)
Pointcut: expression to match join points
Advice: code to run at join point (before, after, etc.)
Target: actual object being advised
Bean Scope: Defines lifecycle and visibility of a bean
Prototype: new bean on every request
Singleton: one shared bean per container (default)
Request: new bean per HTTP request (web only)
ApplicationContext vs BeanFactory: BeanFactory: basic container, lazy loading
ApplicationContext: advanced, eager loading, supports AOP, i18n, events, etc.
Types of DI and Pros/Cons:
Setter Injection: flexible, optional deps; allows inconsistent state
Constructor Injection: immutable, core dependencies
field: hard to test, not recommended
How @Autowired Works: auto injects bean w/ reflection
@Autowired: injects bean by type
@Qualifier: used with @Autowired to specify bean by name when multiple matches exist
Spring Boot Pros & Cons: Less boilerplate, inbuilt tomcat server; starter dependencies; abstractions; less fine grained control
Start Spring Boot Project (from scratch): Init via [start.spring.io] or Spring Tool Suite
Add deps → Build w/ Maven/Gradle → Main class w/ @SpringBootApplication
Run main() → App runs on embedded Tomcat
@Controller vs @RestController: @Controller: returns view (HTML, JSP); @RestController: @Controller + @ResponseBody, returns JSON
Spring Actuator: health/metrics monitoring endpoints; Enabled via spring-boot-starter-actuator
Async: Use @EnableAsync on config class; Use @Async on method ; Must return Future, CompletableFuture, or void
how does spring handle exception- first finds handler in own module, then @ControllerAdvice + @ExceptionHandler for global handling,
then ResponseEntityExceptionHandler
how does spring validate data- Use @Valid or @Validated; @NotNull, @Size, @Email, etc.
how does spring do the log; Uses SLF4J + Logback (default)
**JDBC vs Hibernate**
- JDBC: low-level, manual SQL, boilerplate
- Hibernate: ORM, auto SQL gen, handles relationships, caching

**Statement vs PreparedStatement vs CallableStatement**
- Statement: basic SQL, no params
- PreparedStatement: param support, precompiled, safer
- CallableStatement: executes stored procs

**How to Prevent SQL Injection**
- Use PreparedStatement
- Avoid string concat for SQL
- ORM frameworks handle escaping
- Validate/sanitize user input

**What is ORM**
- Object-Relational Mapping
- Maps Java objs ↔ DB tables
- Auto handles CRUD, joins, etc.

**JPA vs Hibernate**
- JPA: spec/interface (javax.persistence)
- Hibernate: JPA impl + extra features

**Persistent States in Entity Lifecycle**
- Transient: new obj, not in DB
- Persistent: managed by EntityManager
- Detached: was persisted, now unmanaged
- Removed: marked for deletion

**Mapping Relationship**
- @OneToOne
- @OneToMany
- @ManyToOne
- @ManyToMany

**What is Cascade Type**
- Propagates ops to related entities
- Types: ALL, PERSIST, MERGE, REMOVE, DETACH, REFRESH

**What is Fetch Type**
- LAZY: loads on access (default for @OneToMany)
- EAGER: loads immediately (default for @ManyToOne)

**First/Second Level Cache**
- 1st: per session (default in Hibernate)
- 2nd: across sessions (requires config)

**Left Join vs Right Join vs Inner Join vs Outer Join vs Cross Join**
- Left: all left + matched right
- Right: all right + matched left
- Inner: only matched rows
- Outer: matched + unmatched from both
- Cross: cartesian product

**Union vs Union All**
- Union: merges + removes duplicates
- Union All: merges all, keeps duplicates

 */