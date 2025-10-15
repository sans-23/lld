# Observer Design Pattern

**Purpose:** Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

**Demand for this pattern:**
When a change in one object (the Subject) requires changing others (the Observers), and you don't know how many objects need to be changed, or which specific objects need to be changed. This is common in GUI programming (event handling), stock market applications, or news feeds, where multiple components react to changes in a central source.

**Without it:**
Without the Observer pattern, you would typically:
1.  **Tight Coupling:** The Subject would be tightly coupled to its Observers, needing to know about each one and explicitly call their update methods. This makes it hard to add or remove observers.
2.  **Spaghetti Code:** The Subject's code would be cluttered with update logic for all dependents, violating the Single Responsibility Principle.
3.  **Reduced Flexibility:** Difficult to change the notification mechanism or introduce new types of observers.

**How it solved the problem:**
The Observer pattern introduces a `Subject` (also known as Publisher) and `Observer` (also known as Subscriber) interface. The `Subject` maintains a list of `Observer`s and provides methods to attach and detach them. When the `Subject`'s state changes, it notifies all registered `Observer`s by calling their `update()` method. This decouples the Subject from its Observers, allowing them to interact without knowing each other's concrete classes.

**Is it an exclusive pattern for this kind of problem?**
For implementing a one-to-many dependency where changes in one object trigger updates in others, Observer is the primary pattern. It is often used in conjunction with Mediator (where the mediator acts as a subject) or Command (where the update method triggers a command). It differs from other patterns by focusing specifically on notification and automatic updates.

**When to use:**
*   When a change to one object requires changing others, and you don't know how many objects need to be changed.
*   When an object should be able to notify other objects without making assumptions about who these objects are.
*   When the objects that need to be changed are not known at design time.

**Why to use:**
*   **Loose Coupling:** Decouples the Subject from its Observers.
*   **Extensibility:** Easy to add new Observers without modifying the Subject.
*   **Flexibility:** Allows for dynamic registration and unregistration of Observers.
*   **Broadcast Communication:** Enables a one-to-many communication mechanism.

**How to use:**
1.  **Subject Interface:** Define an interface for attaching, detaching, and notifying observers.
2.  **Concrete Subject:** Implements the `Subject` interface. It stores the state of interest to `Concrete Observer`s and notifies them when its state changes.
3.  **Observer Interface:** Define an interface for objects that should be notified of changes in the subject.
4.  **Concrete Observers:** Implement the `Observer` interface. Each `Concrete Observer` registers with a `Concrete Subject` and updates its state when notified.

**Example:**
Consider a stock market application where investors (observers) are notified of stock price changes (subject).

```java
import java.util.ArrayList;
import java.util.List;

// Subject Interface
interface Stock {
    void attach(Investor investor);
    void detach(Investor investor);
    void notifyInvestors();
}

// Concrete Subject
class StockMarket implements Stock {
    private List<Investor> investors = new ArrayList<>();
    private String stockName;
    private double price;

    public StockMarket(String stockName, double price) {
        this.stockName = stockName;
        this.price = price;
    }

    public void setPrice(double price) {
        System.out.println("Stock: " + stockName + " price changed from " + this.price + " to " + price);
        this.price = price;
        notifyInvestors();
    }

    @Override
    public void attach(Investor investor) {
        investors.add(investor);
    }

    @Override
    public void detach(Investor investor) {
        investors.remove(investor);
    }

    @Override
    public void notifyInvestors() {
        for (Investor investor : investors) {
            investor.update(stockName, price);
        }
    }
}

// Observer Interface
interface Investor {
    void update(String stockName, double price);
}

// Concrete Observer
class IndividualInvestor implements Investor {
    private String name;

    public IndividualInvestor(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println(name + " received update: " + stockName + " is now $" + price);
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        StockMarket googleStock = new StockMarket("GOOG", 1500.00);

        Investor investor1 = new IndividualInvestor("Alice");
        Investor investor2 = new IndividualInvestor("Bob");

        googleStock.attach(investor1);
        googleStock.attach(investor2);

        googleStock.setPrice(1510.50);
        System.out.println("\n");
        googleStock.detach(investor1);
        googleStock.setPrice(1505.25);
    }
}
```