# Java Coding Interview Questions

These are practical coding problems often asked in interviews.

## 1. Reverse a String
```java
public class ReverseString {
    public static String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(reverse("Java"));
    }
}
```

## 2. Check if a String is a Palindrome
```java
public class PalindromeCheck {
    public static boolean isPalindrome(String s) {
        String cleaned = s.replaceAll("\\s+", "").toLowerCase();
        return new StringBuilder(cleaned).reverse().toString().equals(cleaned);
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("madam"));
    }
}
```

## 3. Find the Second Largest Number
```java
public class SecondLargest {
    public static int secondLargest(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int n : arr) {
            if (n > largest) {
                second = largest;
                largest = n;
            } else if (n > second && n != largest) {
                second = n;
            }
        }

        return second;
    }
}
```

## 4. Remove Duplicates from an ArrayList
```java
List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 4));
Set<Integer> unique = new LinkedHashSet<>(list);
System.out.println(unique);
```

## 5. Count Frequency of Characters
```java
Map<Character, Integer> freq = new LinkedHashMap<>();
for (char c : "java".toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
System.out.println(freq);
```

## 6. Fibonacci Series
```java
public class Fibonacci {
    public static void printFib(int n) {
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
    }
}
```

## 7. Find Missing Number in an Array
```java
public class MissingNumber {
    public static int findMissing(int[] arr, int n) {
        int sum = n * (n + 1) / 2;
        for (int x : arr) sum -= x;
        return sum;
    }
}
```

## 8. Singleton Design Pattern
```java
class Singleton {
    private static Singleton instance = new Singleton();

    private Singleton() {}

    public static Singleton getInstance() {
        return instance;
    }
}
```

## 9. Find Duplicates in an Array
```java
Set<Integer> seen = new HashSet<>();
Set<Integer> duplicates = new HashSet<>();
for (int x : arr) {
    if (!seen.add(x)) duplicates.add(x);
}
```

## 10. Reverse Words in a Sentence
```java
String sentence = "Java interview preparation";
String[] words = sentence.split(" ");
for (int i = words.length - 1; i >= 0; i--) {
    System.out.print(words[i] + " ");
}
```

## Interview Tip
Practice these questions by writing code from memory instead of copying from notes.
