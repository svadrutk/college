# Chapter 2 Notes

* **OBJECT**: A group of data (variables) and operations that can be performed on that data (methods)
  * Notice that most objects are thought as the operations that can be performed on said object
* **ABSTRACTION**: A user interacting with an item at a high level with low-level operations hidden (encapsulation)
* **ADT (ABSTRACT DATA TYPE)**: An object that has relatively well-defined operations 

---

* **REFERENCE**: A variable that refers to an object; however, there are many different types of references

```java
travelTime = new TimeHrMin(); // creates a new TimeHrMin object; travelTime is the reference to that

int num = 40; // creates a int with a value of 40; num is the reference to that int
```

* Two reference variables can refer to the same object, such as: 

```java
RunnerInfo currRun = new RunnerInfo(); 
RunnerInfo lastRun; 
lastRun = currRun; 
```

---

* Variables are two types: 
  * **Primitive Types**: A variable that directly stores the data for that variable
  * **Reference Types**: A variable that refers to an instance of a class

```java
int numStudents = 20; // primitive type
Integer maxPlayers = 10; // reference type that refers to an instance of the Integer class
```

* **WRAPPER CLASSES**: Default reference types that add something to the primitive types

```java
Integer maxPlayers = 10; // Integer is the wrapper class
```

* Wrapper classes are **immutable**; they cannot be changed after creation 
* You do not use `==` to compare wrapper class objects; instead, you should use the `equals()` and `compareTo()` methods, since `==` checks if the wrapper classes both refer to the same Integer object, but don't actually check if the numbers are the same 
* **REFERENCE VARIABLE**: A variable that refers to an object/array
* **AUTOBOXING**: Conversion of primitive types => wrapper classes
* **UNBOXING**: Conversion of wrapper class => primitive types
* You can convert wrappers to/from strings using the `toString` method

```java
Integer.toString(16); 
```

* **ARRAYLIST**: Ordered list of reference types

```java
import java.util.ArrayList;
ArrayList<Integer> vals = new Arraylist<Integer> ()
```

---

* A program's memory has four parts:
  * **CODE**: Where instructions are stored
  * **STATIC MEMORY**: Where static fields are allocated
  * **STACK**: Where a method's local variables are allocated when it is called
  * **HEAP**: Where the "new" operator allocates memory for objects
* q

