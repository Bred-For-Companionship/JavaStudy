# ⚡ Jceptions 

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
```
 try (FileInputStream fis = new FileInputStream("file.txt")) { ... }
// Auto-closes `fis` after block  
```
## 🔹 Custom Exceptions
- Create in separate package
- Extend Exception (checked) or RuntimeException (unchecked)
```
class MyException extends Exception { ... }
```

