# ⚡ Java Execution Model

## 🔷 Compiled & Interpreted
- `javac` → `.java` → `.class` (bytecode)
- JVM: **Interprets** or **JIT-compiles** bytecode (platform independent!)

## 🔷 Class Loading
- **Bootstrap Loader**: `java.lang.*`, `java.util.*`
- **Extension Loader**: `JAVA_HOME/lib/ext/` 
- **Application Loader**: Classpath (`.jar`, `.class`)
- **Delegation Model**: App → Ext → Bootstrap (requests go up, results come down)
  
  ## 🔷 Linking
1. **Verification**: Checks bytecode
2. **Preparation**: Allocates `static` fields
3. **Resolution**: Converts symbolic refs → memory refs

## 🔷 Initialization
- Runs `static {}` blocks, init `static` fields
- Calls constructor

# 🔷 Runtime Data Area
- **Method Area**: Class level data (runtime const pool, method data), `static` vars
- **Heap**: Objects (GC-managed)
- **Stack**: Each thread’s method frames (call  stack/ method invokations)
- **PC Register**: Current instruction per thread
- **Native Method Stack**: Calls native (C/C++)

# 🔷 Execution Engine
- **Interpreter**: Bytecode to machine code line by line
- **JIT Compiler**: Generate optimized code (condense loops etc.)
- **GC (Garbage Collector)**: mark (find dead), sweep; squeeze remaining objects together so no fragments
- can be serial (single threaded), parallel (multi), g1 (parallel on high dead obj regions, good for large heap); CMS GC (depracated)

## Java Native Interface
- Call **C/C++** stored in **native method library** 

# keywords
- unused-goto, const
- used- primitive types, flow control, modifiers (protected (own class and chld), static, final, abstract, strictfp, native, transient, volatile
- exception-
- class  related- import, extends, implements. interface
- obj related- new, instaceof, super, this
- **final** modify variable- can't modify, must init; cannot reassign reference, but can modify in place; for method- cannot be overriden; for class-cannot extend
- **static** gives init value (int = 0) even when unassigned as static init during init phase; can modify block, class, variable, method; static methods can only access other static methods (getaround- create new instance to access); static class can only be nested (which can access outer classes) ex: Map.Entry<Integer, Integer>
- make immutable class- make class final, members private and final, no setter only getter, deepcopy any mutable objs passed into constructor, so users cannot get() and mod original
- final vs finally vs finalize- last is method invoked before gc
- **implements** to implement interface
- **extends**- single inheritence
  
# ⚡ OOP 

## 🔷 Abstraction  
 **Use `abstract class` or `interface`**  
- **Abstract Class (`extends`)** → Base types  
  - Can have **concrete + abstract** methods  
  - Supports **final, non-final, static, non-static** variables  
- **Interface (`implements`)** → Functionality  
  - Only **abstract methods** (Java 8+ allows default methods)  
  - Only **`static` and `final`** variables
    
## 🔷 Encapsulation  
 **Restrict access using `private` variables**  
 **Use getters & setters** to modify/access data  
❌ Direct access to private fields is not allowed  

---

## 🔷 Inheritance  
**`extends` (for classes)** → Inherit behavior from a parent class  
**`implements` (for interfaces)** → Implement contract methods  
⚠ **Java supports single inheritance for classes, multiple for interfaces**  

---

## 🔷 Polymorphism  
**Method Overloading** (Compile-time) → Same method name, different params  
**Method Overriding** (Runtime) → Redefine parent method in subclass  
❌ **Cannot override `static` methods** (they belong to class, not instance) 
