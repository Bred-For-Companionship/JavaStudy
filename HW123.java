package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

public class HW123 {
    public static void main(String[] args) {
        linked_list_vs_array_list();
        list_v_set();
        comparable_v_comparator();
        interface_abstract_generics();

    }

    public static void linked_list_vs_array_list(){
        System.out.println("LL non contiguous w/ pointers; Arr List contiguous. Impact on insertion/deletion: ");
        System.out.println("Expected: LL O1, Arr L ON to shift all memory cells: ");

        List<Integer> list_test = new ArrayList<>();
        list_test.add(1);
        list_test.add(2);
        list_test.add(3);

        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++)
            {
                list_test.add(0, i);
            }
        long endTime = System.nanoTime();
        System.out.println("ArrayList Insertion at Start: " + (endTime - startTime) + " ns");

        List<Integer> list_test2 = new LinkedList<>();
        list_test2.add(1);
        list_test2.add(2);
        list_test2.add(3);

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++)
        {
            list_test2.add(0, i);
        }
        endTime = System.nanoTime();
        System.out.println("Linked List Insertion at Start: " + (endTime - startTime) + " ns");

        System.out.println("Impact on access, expected: LL is slow as we have 2 traverse pointers: ");

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++)
        {
            list_test.get(5000);
        }
        endTime = System.nanoTime();
        System.out.println("Arr List access middle: " + (endTime - startTime) + " ns");

        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++)
        {
            list_test2.get(5000);
        }
        endTime = System.nanoTime();
        System.out.println("Linked List access middle: " + (endTime - startTime) + " ns");
    }

    public static void list_v_set(){
        Set<String> hashset = new HashSet<>();
        hashset.add("Orange");
        hashset.add("Mango");
        hashset.add("Mango");
        hashset.add("Durian");
        hashset.add("Mao Shan Wang");
        System.out.println("HashSet (No order but unique): " + hashset);

        Set<String> linkedset = new LinkedHashSet<>();
        linkedset.add("Orange");
        linkedset.add("Mango");
        linkedset.add("Mango");
        linkedset.add("Durian");
        linkedset.add("Mao Shan Wang");
        System.out.println("LinkedHashSet (Insertion Order): " + linkedset);

        Set<String> treeset = new TreeSet<>();
        treeset.add("Orange");
        treeset.add("Mango");
        treeset.add("Mango");
        treeset.add("Durian");
        treeset.add("Mao Shan Wang");
        System.out.println("TreeSet (Alphabetically Sorted Order): " + treeset);
        //return treeset;
    }

    public static void comparable_v_comparator(){
        System.out.println("Using Comparable with stringwrapper to sort in descending alphabetical order: ");
        TreeSet<StringWrapper> treeset = new TreeSet<>();
        treeset.add(new StringWrapper("Orange)"));
        treeset.add(new StringWrapper("Mango"));
        treeset.add(new StringWrapper("Mango"));
        treeset.add(new StringWrapper("Durian"));
        treeset.add(new StringWrapper("Mao Shan Wang"));
        System.out.println("TreeSet (Alphabetically Descending Order): " + treeset);

        System.out.println("Using comparator, and seeing if it takes precedence over comparable: ");
        Comparator<StringWrapper> cmp_fn = new Comparator<StringWrapper>() {
            @Override
            public int compare(StringWrapper o1, StringWrapper o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        };

        TreeSet<StringWrapper> treeset2 = new TreeSet<>(cmp_fn);
        treeset2.add(new StringWrapper("Orange)"));
        treeset2.add(new StringWrapper("Mango"));
        treeset2.add(new StringWrapper("Mango"));
        treeset2.add(new StringWrapper("Durian"));
        treeset2.add(new StringWrapper("Mao Shan Wang"));
        System.out.println("Comparator in ascending order but comparable in descending order): " + treeset2);

    }

    public static void interface_abstract_generics(){
        System.out.println("Doing the sorting but with fruit wrapper class: ");
        Fruit durian = new Fruit("Durian");
        Fruit MaoShanWang = new Fruit("Mao Shan Wang");

        TreeSet<FruitWrapper> treeset = new TreeSet<>();
        treeset.add(new FruitWrapper(durian));
        treeset.add(new FruitWrapper(MaoShanWang));
        System.out.println("Sorting the wrapper that uses interfaces and abstract classes: " + treeset);
    }

}

class StringWrapper implements Comparable<StringWrapper>{
    final private String value;

    public StringWrapper(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int compareTo(StringWrapper other){
        return other.value.compareTo(this.value);
    }

    @Override
    public String toString() {
        return value;
    }
}

interface Eatable{
    void eat();

    static boolean is_eatable(Object o){
        return o instanceof Eatable;
    }

    boolean is_perishable = true;

    default boolean is_fresh(){
        return true;
    }
}

abstract class Food implements Eatable{
    private String name;
    private boolean fresh = true;

    public Food(String name) {
        this.name = name;
    }

    @Override
    public void eat() {

    }

    @Override
    public boolean is_fresh() {
        if (Eatable.is_perishable)
        {
            return fresh;
        }
        else
        {
            return Eatable.super.is_fresh();
        }
    }

    public String getName() {
        return name;
    }
}

class Fruit extends Food{

    public Fruit(String name) {
        super(name);
    }

    public boolean is_sweet(){
        return true;
    }
}

class FruitWrapper<T extends Food> implements Comparable<FruitWrapper<T>>{
    private T item;

    public FruitWrapper(T item) {
        this.item = item;
    }

    @Override
    public int compareTo(FruitWrapper<T> o) {

        return this.item.getName().compareTo(o.item.getName());
    }

    @Override
    public String toString() {
        return item.getName();
    }
}



class Student{
    final private Integer id;
    final private String first_name;
    final private String last_name;
    private List<Course> courses;

    public Student(Integer id, String first_name, String last_name) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;

        courses = new LinkedList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public String getFirst_name() {
        return first_name;
    }

    public Integer getId() {
        return id;
    }

    public String getLast_name() {
        return last_name;
    }
}

class Course{

}

/*
1a list vs set- list duplicates ok
1b arr list vs linked list-Arr list contiguous; different access and modification times
1c map interface- mapping unique keys to vals, doesn’t extend collections
1d put in 1 of 16 buckets based on hash func,
1e two keys’ hash codes put them in same bucket -> walk down linked list, if >8, red B trees
1f collections- from .util, provides collections like list, set; and sorting, reverse etc
1g immutable class- class final, fields private/final, no setter methods, for getter-mutable fields are copied, no access to original
1h hashmap vs hashtable vs concurrenthashmap- former not thread safe, latter fine grained locking
1i string vs stringbuilder vs stringbuffer: immutable/not threadsafe/threadsafe
1j in code
1k overriding vs overloading- overriding, @override annotation with same signature by subclass, overloading- different params
1l JRE vs JDK vs JVM: runtime env, development kit- jre + compilers, debuggers etc, java virtual machine- translates bytecode to machine code
1m 8 primitives- int, long, float, double, byte, char, Boolean
1n primitive vs reference types- not objects, stored in stack vs objs in heap with memory ref

2a final-makes immutable, can be for class, method, variables;
b- incode
c- synchronized- only 1 thread can access, volatile- thread consistency after write; transient- cannot be serialized
d- throw vs throws- used inside method to raise vs declare potential exception
e- final- makes immutable, finally- runs after try, regardless; finalize- used in gc
f- this vs super- current instance vs parent
g- abstract class vs interface- abstract: concrete and virtual methods; virtual can only have abstract (unless we use default); abstract can have static and non static methods and members
h-in notes
i- accesibility- public-anywhere, protected-subclasses, same package; private- same class; default-same package
j- static scope- belongs to class not instances, stored in method area, init when class loaded
k- loads class files using parent delegation asks parent to load, parent sends result back down, if not found, loads on lower rungs (check notes for more details)

3a- checked vs unchecked exceptions- checked-compile time; unchecked-runtime – class not found vs. out of bounds e.g.
b-^^
c- try(resource here) {} ; auto cleanup, dispenses with need to close in finally, which may need recursive tries
d-an example is accessing array index of length + 1
e-  NoClassDefFoundError v ClassNotFoundException; former is runtime (compiled but missing due to deletion etc,; latter is compile time
f- why close resources in finally- it’s always run! Shut down sockets etc.
g- OOM error- heap or stack full, can’t handle with exceptions
h-generics- use generic type parameter- many classes can share one interface, method, class
i- type erasure- compiler replaces generic types with object,
j-
? extends X	You only need to read, and you want to support multiple subtypes flexibly.
? super X	You only need to write, and you want to support multiple supertypes flexibly.
k-Optional- container for value that may be null, don’t have to manually check for NPE;
 String name = null;
  String result = Optional.ofNullable(name).orElse("Default Name");
 try {
      String value = Optional.ofNullable(name).orElseThrow(() -> new IllegalArgumentException("Value is null!"));
          } catch (Exception e) {

l- annotated interface to use with lambda exrps- only 1 abstract method, can have many concrete; predefined ones like predicate, supplier, consumer, function, bipredicate, bifunction
m-interface analog to concrete method that can be overridden, otherwise interface can only have abstract and no implemented concrete ones
n-predicate- T/F, consumer supplier- only takes arguments/only provides; function-takes input, gives result
o- import java.util.function.*;

Predicate<Integer> isEven = x -> x % 2 == 0;
Supplier<String> supplier = () -> "Hello, Java!";
Consumer<String> printer = s -> System.out.println("Consumed: " + s);
Function<Integer, String> intToString = i -> "Number: " + i;
p- method reference- Class::Method to invoke; shorthand for lambda exrp

class Utils {
    public static boolean isEven(int num) {
        return num % 2 == 0;
}
Predicate<Integer> instanceRef = utils::isPositive;
Utils::isEven is shorthand for (num) -> Utils.isEven(num).

 */

