 ## 🛠 Maven  
- `mvn clean install` → Build artifact (`.jar`, `.war`) **to local**  
- `mvn clean deploy` → Deploy artifact **to remote**  
- `pom.xml` → Dependencies, compilers, UTF-8 encoding  

## 🔄 Git  
- `git fetch` → Get remote changes, **doesn’t merge**  
- `git pull` → Fetch + Merge  
- `git add` → Stage changes  
- `git commit` → Save snapshot  

## 🔤 String & Memory  
- **String** = Immutable 🔒 | **StringBuilder** (Non-thread-safe) / **StringBuffer** (Thread-safe) = Mutable  
- `"=="` compares **object refs**, **not values**  
- **String Pool (inside heap, GC-managed)**
- Non new keyword allocated- in string pool with same ref as identical
- int pool max of 8 bytes, values outside range don't fit
- .intern(); // Moves to pool if not present and returns ref
-   nteger wrappedInt = 30;  // Auto-boxing
-   int primitiveInt = wrappedInt;  // Auto-unboxing
-   
## 📦 Collections & Maps  
- **LinkedHashSet** → Keeps insertion order | **TreeSet** → Sorted order (`Comparable` / `Comparator`)  
- **LinkedList vs ArrayList**:  
  - **LL**: O(1) head/tail deletion (just change pointers)  
  - **AL**: O(N) deletion (shift elements)  
- **HashMap vs HashTable vs ConcurrentHashMap**  
  - `HashMap` → **Not thread-safe**, **allows null key** (Bucket `0`)  
  - `HashTable` → **Thread-safe**, **no null keys** (legacy)  
  - `ConcurrentHashMap` → **Thread-safe**, locks **buckets**, not whole object  
- **HashMap Internals**  
  - **16 buckets initially**, keys hashed & modded  
  - **If linked list > 8**, converts to **Red-Black Tree** (O(N) → O(logN))  
- **Comparator vs Comparable**  
  - `Comparator` (constructor-based) **takes precedence over** `compareTo()` which is overriden via (implementing `Comparable`)
  - 2 options for constructor based- lambda expression or separate class implementing comparator<obj>


  
