# Iterator Design Pattern

**Purpose:** Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.

**Demand for this pattern:**
When you need to traverse the elements of a collection (like a list, array, or tree) without exposing its internal structure. Different collections might have different internal structures, and clients shouldn't need to know these details to iterate over them. Also, you might need different traversal algorithms for the same collection.

**Without it:**
Without the Iterator pattern, you would typically:
1.  **Expose Internal Structure:** Force clients to understand the internal structure of the collection to traverse it, leading to tight coupling and making it difficult to change the collection's implementation.
2.  **Duplicate Traversal Logic:** Duplicate traversal logic across multiple client classes if different clients need to iterate over the same collection.
3.  **Limited Traversal Options:** Make it hard to support different traversal algorithms (e.g., pre-order, in-order, post-order for a tree).

**How it solved the problem:**
The Iterator pattern separates the traversal logic from the collection (aggregate) object. It defines an `Iterator` interface with methods like `hasNext()` and `next()`. Concrete iterators implement this interface for specific collection types. The collection provides a method to create an iterator. Clients use the `Iterator` interface to traverse the collection, remaining independent of the collection's internal structure.

**Is it an exclusive pattern for this kind of problem?**
For sequential access to collection elements, Iterator is the primary pattern. It is often used in conjunction with Composite (to traverse tree structures) or Factory Method (to create different types of iterators). It differs from other patterns by focusing specifically on providing a standardized way to traverse collections.

**When to use:**
*   To access the contents of an aggregate object without exposing its internal representation.
*   To support multiple traversals of aggregate objects.
*   To provide a uniform interface for traversing different aggregate structures.

**Why to use:**
*   **Decoupling:** Decouples the client from the internal representation of the collection.
*   **Flexibility:** Allows for different traversal algorithms to be used with the same collection.
*   **Extensibility:** Easy to add new collection types or new traversal algorithms.
*   **Simplified Client Code:** Clients can iterate over various collections using a common interface.

**How to use:**
1.  **Iterator Interface:** Declare an interface for accessing and traversing elements (e.g., `hasNext()`, `next()`).
2.  **Concrete Iterator:** Implement the `Iterator` interface for a specific aggregate. It keeps track of the current position in the traversal.
3.  **Aggregate Interface:** Declare an interface for creating an `Iterator` object.
4.  **Concrete Aggregate:** Implement the `Aggregate` interface and return an instance of the `Concrete Iterator` that is appropriate for its internal structure.
5.  **Client:** Uses the `Aggregate` to create an `Iterator` and then uses the `Iterator` to traverse the elements.

**Example:**
Consider iterating over a simple list of names.

```java
import java.util.ArrayList;
import java.util.List;

// Iterator Interface
interface CustomIterator<T> {
    boolean hasNext();
    T next();
}

// Aggregate Interface
interface CustomCollection<T> {
    CustomIterator<T> createIterator();
}

// Concrete Aggregate
class NameCollection implements CustomCollection<String> {
    private List<String> names = new ArrayList<>();

    public void addName(String name) {
        names.add(name);
    }

    @Override
    public CustomIterator<String> createIterator() {
        return new NameIterator();
    }

    // Concrete Iterator
    private class NameIterator implements CustomIterator<String> {
        private int position = 0;

        @Override
        public boolean hasNext() {
            return position < names.size();
        }

        @Override
        public String next() {
            if (hasNext()) {
                return names.get(position++);
            }
            return null; // Or throw NoSuchElementException
        }
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        NameCollection names = new NameCollection();
        names.addName("Alice");
        names.addName("Bob");
        names.addName("Charlie");

        CustomIterator<String> iterator = names.createIterator();

        System.out.println("Iterating through names:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```