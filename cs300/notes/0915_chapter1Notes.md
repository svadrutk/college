# Chapter 1 Notes

* Program consists of 
  * Input: program gets data
  * Process: program manipulates data
  * Output: program puts data somewhere else, possibly for use by another program (piping)
* Programs use variables to refer to data
* **Computational thinking**: Creating instruction sequences to solve problems
  * **Algorithm**: Sequence of instructions 
* You can insert debug output statements in order to figure out what's wrong with a program
  * If the program is big, you can add debug output statements per region instead of per line, then add separate debug output statements for each line if the one of the regions' output is weird

---

* You can use the Javadoc tool to auto-generate documentation by making comments above each class or method, such as 

```java
/**
 * A class representing an elapsed time measurement 
 * in hours and minutes. 
 * @author Svadrut Kukunooru
 * @version 1.3.4
 */ 
```

* Javadoc comments are usually written before class definitions and before each method

| Block tag  | Compatibility         | Description                                                  |
| ---------- | --------------------- | ------------------------------------------------------------ |
| `@author`  | classes               | Used to specify an author.                                   |
| `@version` | classes               | Used to specify a version number.                            |
| `@param`   | methods, constructors | Used to describe a parameter.                                |
| `@return`  | methods               | Used to describe the value or object returned by the method. |
| `@see`     | all                   | Used to refer reader to relevant websites or class members.  |

---

* Method signatures are important if they're dealing with arrays
  * If a method does not change the array contents, it must be calculating some value that must be returned, e.g. (SEE FIGURE 1)
  * If a method changes the array's contents, but not the array's size, it has a void return type, e.g. (SEE FIGURE 2)
  * If a method creates a new array or changes the array's size, it has an array return type, e.g. (SEE FIGURE 3)

FIGURE 1

```java
int binarySearch(int[] arrayReference, int target)
```

FIGURE 2

```java
void sortArray(int[] arrayReference)
```

FIGURE 3

```java
double[] copyOf(double[] arrayReference, int newLength)
```

**EXAMPLE PROBLEM**

Find the errors wrong with this program: 

```java
int[] hoursWorked = {0, 7, 9, 5};

copyOfRange(hoursWorked, 1, 3);

...

public static void copyOfRange(int[] arrayReference,
                            int start, int end) {
   int[] tempArray = new int[end - start];
   int index;

   for (index = start; index < end; ++index) {
      tempArray[index - start] = arrayReference[index];
   }

   arrayReference = tempArray;
}
```

First, let's go through the process of the program to see what's wrong. 

1. The array `hoursWorked` is constructed and initialized. It has 4 elements, all integers. 
2. `hoursWorked` is passed to the `copyOfRange` method with two other parameters.
   1. The `copyOfRange` method constructs a temporary array (`tempArray`) that has a size taken from taking the difference between two integer parameters; in this case, `1` and `3`. Therefore, the size of `tempArray` is `2`. 
   2. The integer variable `index` is initialized, with a value of `0`. 
   3. A `for` loop is created, where `tempArray` is filled with `arrayReference`'s elements within the specified index range (`1-3`)
   4. This changes `tempArray`, but does not change `hoursWorked`. `tempArray` is lost after the method ends. 

To fix this, we

1. Change the method signature to return `int[]` instead of `void` 
2. The method must return `tempArray` instead of  assigning `arrayReference` to `tempArray` and not returning `arrayReference` 
3. `hoursWorked` must be assigned to the returned array reference

FINAL PRODUCT: 

```java
int[] hoursWorked = {0, 7, 9, 5};
hoursWorked = copyOfRange(hoursWorked, 1, 3);   // 3. Store returned array

...

// 1. Correct method signature
public static int[] copyOfRange(int[] arrayReference, int start, int end) { // 1. Change method signature
   int[] tempArray = new int[end - start];
   int index;

   for (index = start; index < end; ++index) {
       tempArray[index - start] = arrayReference[index];
   }
   return tempArray;    // 2. Return array reference
}
```

---

* **SIDE EFFECT**: An error that unintentionally modifies data
  * EXAMPLE: A method that calculates values based on an array but unintentionally modifies said array in the process

**EXAMPLE PROBLEM**

Find what's wrong with this program

```java
public static String findMostFrequent(String[] arrayReference) {
   String mostCommon;
   int times;     // The number of times the current element has appeared previously
   int mostTimes; // The number of times the most common element has appeared
   int index;

   // Zero length arrays don't contain anything
   if (arrayReference.length == 0) {  
       return null;
   }

   // Sort the array
   Arrays.sort(arrayReference);
            
   mostCommon = arrayReference[0]; // Make sure method works with 1-element arrays
   times = 0; 
   mostTimes = 0; 
         
   // Start loop at one in order to use index - 1
   for (index = 1; index < arrayReference.length; ++index) {
                    
      // The next element matches the last one
      if (arrayReference[index - 1].equals(arrayReference[index])) {
         ++times;
      }
      else {  // new element was found, restart the count
         times = 0;
      } 
                    
      // Check to see if old element was most common so far
      if (times > mostTimes) {
         mostTimes = times;
         mostCommon = arrayReference[index - 1];
      }
   } 
           
   return mostCommon;
}

```

This method is supposed to return a value of the array but modifies the array in the process. To fix this, you can either

1. Use an alternative algorithm to calculate the value without sorting the array

```java
public static String findMostFrequent(String[] arrayReference) {
   String mostCommon = null;
   int mostFrequentCount;
   int index;
   int count;
   int frequency;
         
   mostFrequentCount = 0;

   for (index = 0; index < arrayReference.length; ++index) {
      frequency = 1;
           
      // Count the number of times arrayReference[index] occurs
      for (count = index + 1; count < arrayReference.length; ++count){
         if (arrayReference[count].equals(arrayReference[index])) {
            ++frequency;
         }
      }
                    
      // Check to see if this is the most frequent element so far
      if (frequency > mostFrequentCount) {
         mostCommon = arrayReference[index];
         mostFrequentCount = frequency;
      }
   } 

   return mostCommon;
}
```

2. Make a copy of the array and then use the same method to calculate the value

```java


public static String findMostFrequent(String[] arrayReference) {
   String mostCommon;
   int times;     // The number of times the current element has appeared previously
   int mostTimes; // The number of times the most common element has appeared
   int index;
   String[] arrayCopy;

   if (arrayReference.length == 0) {
      return null;
   }
            
   // Make a copy of arrayReference to avoid a side effect
   arrayCopy = Arrays.copyOf(arrayReference, arrayReference.length);         
   Arrays.sort(arrayCopy);  // Sort the array copy
            
   mostCommon = arrayCopy[0]; // Make sure method works with 1-element arrays
   times = 0;
   mostTimes = 0;
            
   // Have to start loop at one in order to use index - 1
   for (index = 1; index < arrayCopy.length; ++index) {
                    
      // The next element matches the last one
      if (arrayCopy[index - 1].equals(arrayCopy[index])) {
         ++times;
      } 
      else {  // new element was found, restart the count
         times = 0;
      } 
                    
      // Check to see if old element was most common so far
      if (times > mostTimes) { 
         mostTimes = times;
         mostCommon = arrayCopy[index - 1];
      }
   } 
            
   return mostCommon;
}

```

---

* **PERFECT SIZE ARRAY**: An array where number of elements = memory allocated
  * EXAMPLE: `int[] highTemps = {93,80,62,75,74,89,97}` 
* You should use perfect size arrays when you don't need additional values by the program; e.g. making an array that has the days of the week, since the days of the week are never going to change

```java
final String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
```

EXAMPLE (of perfect size arrays in a method)

```java
public static void fill(int[] arrayRef, int initializedValue) {
   int index;

   for (index = 0; index < arrayRef.length; ++index) {
      arrayRef[index] = initializedValue;
   }
}
```

Perfect size arrays are only passed to a method using the array's reference. No. of elements/data in array are both accessed from said reference. 

---

* **OVERSIZE ARRAY**: An array where the number of elements < memory allocated

```java
int[] salesTransactions = new int[1000];
int salesTransactionsSize = 0; 
```

* Useful when the number of elements (that will be) stored in the array is unknown
* Oversize arrays have three variables:
  * The array reference
  * The current size
  * A constant for an array capacity 

```java
final int QUIZ_ANSWER_CAPACITY = 200; 
boolean[] quizAnswers = new boolean(QUIZ_ANSWER_CAPACITY); 
int quizAnswerSize = 0; 
```

---

* When oversize arrays are used in a method, you need two parameters: the array reference and the current size of the array

```java
public static void printOversizeArray(String[] arrayReference, int arraySize) {
    // code to print array here
}
```

* ALWAYS REMEMBER to return the new size of a modified oversize array

---

* To find out whether a method requires a perfect size or oversize array, look at the method signature:

```java
int findIndexMax(String[] arr) // perfect size array
int binarySearch(String[] arr, int size) // oversize array
```

* Additionally, you can look at the return type:

```java
return arr[] // perfect size array
// oversize arrays cannot be returned in methods since you need two separate parameters (size and reference) which is prohibited in Java
```





