# ⚡ Xceptions 

## 🔹 Exception Hierarchy  
- **`Throwable`** → Parent of all exceptions & errors  
  - **`Exception`** → Recoverable | **Checked** (Compile-time) / **Unchecked** (Runtime)  
  - **`Error`** → Unrecoverable (e.g., `VirtualMachineError`, `AssertionError`)
  - Propagating Exceptions: Can throw to main(), which can pass to JVM or handle via try-catch

## 🔹 Throwing & Handling  
- **`throw`** → Inline exception (like Python’s `raise`)  
- **`throws`** → Declares exception in method signature

## 🔹 Try-Catch-Finally
- can have Multiple Catch Blocks, Subclass exceptions first
- Use | to catch multiple exceptions
- finally always executes, even if return in try
- Nested try inside finally → Handle errors in cleanup (e.g., closing files)
- **Alternative**: Try-With-Resources (Auto-Close); Works for objects implementing AutoCloseable (close() method required)
```java
 try (FileInputStream fis = new FileInputStream("file.txt")) { ... }
// Auto-closes `fis` after block  
```
## 🔹 Custom Exceptions
- Create in separate package
- Extend Exception (checked) or RuntimeException (unchecked)
```java
class MyException extends Exception { ... }
```
# ⚡ Generics 

## Use Wrappers?  
- **Use `Integer` (wrapper) instead of `int` (primitive)**  
- **Compile-time:** Everything is treated as `Object`   **Runtime:** Cast back to `Integer`, avoiding primitive limitations  

## 🔹 Syntax  
- **Class Generics**  
  ```java
  class Box<T> { T value; }
  ```
- **Method Generics (<> before return type!)**
```java
class Util {
    static <T> void print(T item) { System.out.println(item); }
}
```
## 🔹 Wildcards & Bounds
- Unbounded (?) → Accepts any type
- Upper Bound (extends) → T must be a subclass of B
- Lower Bound (super) → T must be a superclass of B
```java
void add(List<? super Integer> list) { list.add(10); }
```
- read more on wildcare usage vs strongly typed generics, can't really memorize this without having used them
  
# ⚡ J8 new stuff

🔹 **Functional Interface** → **Only 1 abstract method**  
   - Used with **lambda expressions**  
   - `@FunctionalInterface` annotation (optional, for clarity)  
   - ✅**Can have `default` methods** (concrete, multiple allowed)  **Regular interfaces** → ❌ **Cannot** have `default` methods 

🔥 **Built-in Functional Interfaces**  
   - `Consumer<T>` → Takes `T`, returns nothing (`accept(T)`)  
   - `Supplier<T>` → Takes nothing, returns `T` (`get()`)  
   - `Predicate<T>` → Takes `T`, returns `boolean` (`test(T)`)  
   - `Function<T, R>` → Takes `T`, returns `R` (`apply(T)`)
   - -bi predicate takes 2 arguments, as does bi function
   - 
 **`Optional<T>` (Avoid `null`)**  

✅ **Wrap possible `null` values** → Prevents `NullPointerException`  
   - `Optional.of(T)` → Throws if `T` is `null`  
   - `Optional.ofNullable(T)` → ✅ Allows `null`  

✅ **Extracting Values**  
   - `.orElse(defaultValue)` → Returns value or default  
   - `.orElseGet(() -> value)` → Uses supplier if empty  
   - `.orElseThrow(() -> new Exception())` → Throws if empty  

✅ **Example**  
```java
Optional<String> opt = Optional.ofNullable(null);
System.out.println(opt.orElse("Default")); // Output: Default  


 


  
