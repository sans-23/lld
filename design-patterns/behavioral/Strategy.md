# Strategy Design Pattern

**Purpose:** Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

**Demand for this pattern:**
Often, an application needs to perform a certain task in different ways, and the choice of algorithm might depend on various factors (e.g., user preference, context, performance requirements). Directly embedding all these algorithms within the client or a single class leads to large, complex, and hard-to-maintain code with many conditional statements.

**Without it:**
Without the Strategy pattern, you would typically use:
1.  **Conditional Logic:** Large `if-else` or `switch` statements to select and execute different algorithms based on some condition. This makes the code hard to read, extend, and maintain.
2.  **Subclassing:** Creating subclasses for each variation of an algorithm. This can lead to a proliferation of subclasses and makes it difficult to change algorithms at runtime.

**How it solved the problem:**
The Strategy pattern extracts each algorithm into its own separate class, all implementing a common interface (the Strategy interface). The client (Context) holds a reference to a Strategy object and delegates the execution of the algorithm to it. This allows the client to change the algorithm at runtime by simply swapping the Strategy object, without modifying the client's code. It promotes loose coupling and adherence to the Open/Closed Principle.

**Is it an exclusive pattern for this kind of problem?**
For making algorithms interchangeable and allowing their selection at runtime, Strategy is the primary pattern. While other patterns like Template Method also deal with algorithms, Template Method focuses on defining the skeleton of an algorithm in a superclass and letting subclasses override specific steps, whereas Strategy completely encapsulates different algorithms. Command pattern can also encapsulate actions, but Strategy specifically focuses on interchangeable algorithms.

**When to use:**
*   An object needs to perform a task using one of several algorithms, and you want to choose the algorithm at runtime.
*   You have many related classes that differ only in their behavior.
*   You need different variations of an algorithm within an object.
*   You want to avoid conditional statements that switch between multiple algorithms.

**Why to use:**
*   **Flexibility:** Allows algorithms to be changed independently of the client that uses them.
*   **Extensibility:** Easy to add new algorithms without modifying existing client or context code.
*   **Maintainability:** Encapsulates algorithms, making them easier to understand, test, and maintain.
*   **Avoids Conditional Logic:** Replaces complex conditional statements with a cleaner, object-oriented approach.

**How to use:**
1.  **Strategy Interface:** Declare an interface common to all supported algorithms. The Context uses this interface to call the algorithm defined by a ConcreteStrategy.
2.  **Concrete Strategies:** Implement the Strategy interface, providing a specific algorithm.
3.  **Context:** Is configured with a ConcreteStrategy object. It maintains a reference to a Strategy object and delegates the execution of the algorithm to it.

**Examples to Remember:**

1.  **Payment Methods:** An e-commerce application can support various payment methods (Credit Card, PayPal, Bitcoin). A `PaymentStrategy` interface can have a `pay(amount)` method, and `CreditCardPayment`, `PayPalPayment`, `BitcoinPayment` are concrete strategies. The `ShoppingCart` (Context) uses a chosen `PaymentStrategy`.

2.  **Sorting Algorithms:** A data processing application might need to sort data using different algorithms (QuickSort, MergeSort, BubbleSort). A `SortStrategy` interface can define a `sort(data)` method, and concrete strategies implement specific sorting algorithms. A `Sorter` class (Context) uses a `SortStrategy`.

3.  **Compression Algorithms:** A file archiving tool might support different compression algorithms (ZIP, RAR, GZIP). A `CompressionStrategy` interface can define a `compress(file)` method, and concrete strategies implement specific compression logic. A `FileArchiver` (Context) uses a `CompressionStrategy`.

```java
// Strategy Interface
interface PaymentStrategy {
    void pay(int amount);
}

// Concrete Strategy 1
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String name;

    public CreditCardPayment(String cardNumber, String name) {
        this.cardNumber = cardNumber;
        this.name = name;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid with credit card: " + cardNumber + " (Holder: " + name + ")");
    }
}

// Concrete Strategy 2
class PayPalPayment implements PaymentStrategy {
    private String emailId;

    public PayPalPayment(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public void pay(int amount) {
        System.out.println(amount + " paid using PayPal: " + emailId);
    }
}

// Context
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        if (paymentStrategy == null) {
            System.out.println("No payment strategy selected.");
            return;
        }
        paymentStrategy.pay(amount);
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Pay with Credit Card
        cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456", "John Doe"));
        cart.checkout(100);

        // Pay with PayPal
        cart.setPaymentStrategy(new PayPalPayment("john.doe@example.com"));
        cart.checkout(50);
    }
}
```