# Chain of Responsibility Design Pattern

**Purpose:** Passes requests along a chain of handlers. Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.

**Demand for this pattern:**
When you have multiple objects that can handle a request, but you don't know which one should handle it beforehand. You want to decouple the sender of a request from its receiver, and allow multiple handlers to try processing the request without the sender being aware of the specific handler.

**Without it:**
Without the Chain of Responsibility, you would typically use:
1.  **Direct Invocation:** The sender would need to know all possible handlers and explicitly call them in a specific order, leading to tight coupling.
2.  **Complex Conditional Logic:** A large `if-else` or `switch` statement in the sender to determine which handler to call, making the code hard to maintain and extend when new handlers are added.

**How it solved the problem:**
The Chain of Responsibility pattern creates a chain of processing objects. Each object in the chain has a reference to the next object. When a request arrives, it is passed along the chain until an object handles it. If a handler cannot process the request, it passes it to the next handler in the chain. This decouples the sender from the receiver and allows for flexible configuration of handlers.

**Is it an exclusive pattern for this kind of problem?**
For handling requests by a sequence of potential handlers, Chain of Responsibility is the primary pattern. It is often used in conjunction with Command (where each handler can execute a command) or Composite (where a handler can be a composite of other handlers). It differs from other patterns by focusing on a flexible way to process requests through a series of objects.

**When to use:**
*   More than one object may handle a request, and the actual handler is not known a priori.
*   You want to issue a request to one of several objects without specifying the receiver explicitly.
*   The set of objects that can handle a request should be specified dynamically.

**Why to use:**
*   **Loose Coupling:** Decouples the sender of a request from its receiver.
*   **Flexibility:** Allows for dynamic configuration of handlers and their order.
*   **Extensibility:** Easy to add new handlers to the chain without modifying existing code.
*   **Simplified Client Code:** The client only needs to send the request to the first handler in the chain.

**How to use:**
1.  **Handler Interface:** Declare an interface for handling requests. It typically includes a method for handling the request and a method for setting the next handler in the chain.
2.  **Abstract Handler (Optional):** Provide a base implementation of the `Handler` interface, often including the logic for passing the request to the next handler.
3.  **Concrete Handlers:** Implement the `Handler` interface. Each concrete handler decides whether to process the request or pass it to the next handler.
4.  **Client:** Initiates the request by sending it to the first handler in the chain.

**Example:**
Consider an approval process where requests need to be approved by different levels of authority.

```java
// Handler Interface
interface Approver {
    void setNextApprover(Approver nextApprover);
    void processRequest(int amount);
}

// Concrete Handler 1
class Manager implements Approver {
    private Approver nextApprover;

    @Override
    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    @Override
    public void processRequest(int amount) {
        if (amount <= 1000) {
            System.out.println("Manager approved request for $" + amount);
        } else if (nextApprover != null) {
            System.out.println("Manager cannot approve $" + amount + ". Passing to Director.");
            nextApprover.processRequest(amount);
        } else {
            System.out.println("Request for $" + amount + " cannot be approved.");
        }
    }
}

// Concrete Handler 2
class Director implements Approver {
    private Approver nextApprover;

    @Override
    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    @Override
    public void processRequest(int amount) {
        if (amount <= 5000) {
            System.out.println("Director approved request for $" + amount);
        } else if (nextApprover != null) {
            System.out.println("Director cannot approve $" + amount + ". Passing to VP.");
            nextApprover.processRequest(amount);
        } else {
            System.out.println("Request for $" + amount + " cannot be approved.");
        }
    }
}

// Concrete Handler 3
class VicePresident implements Approver {
    private Approver nextApprover;

    @Override
    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    @Override
    public void processRequest(int amount) {
        if (amount <= 10000) {
            System.out.println("Vice President approved request for $" + amount);
        } else {
            System.out.println("Vice President cannot approve $" + amount + ". Request denied.");
        }
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        Approver manager = new Manager();
        Approver director = new Director();
        Approver vp = new VicePresident();

        manager.setNextApprover(director);
        director.setNextApprover(vp);

        manager.processRequest(500);   // Handled by Manager
        manager.processRequest(2500);  // Handled by Director
        manager.processRequest(7000);  // Handled by Vice President
        manager.processRequest(15000); // Denied
    }
}
```