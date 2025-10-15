# Adapter Design Pattern

**Purpose:** Allows incompatible interfaces to work together. It acts as a bridge between two incompatible types.

**Demand for this pattern:**
Often, you have existing classes with useful functionality, but their interfaces don't match the requirements of your current system. You can't modify the existing classes (e.g., third-party libraries, legacy code), but you need to integrate them.

**Without it:**
Without the Adapter pattern, you would either have to rewrite the existing classes to match the new interface (which might not be possible or desirable) or implement complex, tightly coupled logic in your client code to handle the interface mismatch, leading to brittle and hard-to-maintain code.

**How it solved the problem:**
The Adapter pattern introduces an intermediate adapter class that converts the interface of one class into another interface that clients expect. This allows classes with incompatible interfaces to collaborate without altering their original code.

**Is it an exclusive pattern for this kind of problem?**
While the Adapter pattern is the primary solution for interface incompatibility, other patterns like Facade (for simplifying complex subsystems) or Bridge (for decoupling abstraction from implementation) might seem related but address different concerns. Adapter specifically focuses on making existing interfaces compatible.

**When to use:**
*   You want to use an existing class, and its interface does not match the one you need.
*   You want to create a reusable class that cooperates with unrelated or unforeseen classes, that is, classes that don't necessarily have compatible interfaces.
*   (Object adapter only) You need to use several existing subclasses, but it's impractical to adapt their interface by subclassing every one. An object adapter can adapt the interface of its parent class.

**Why to use:**
*   **Reusability:** Allows you to reuse existing code without modification.
*   **Flexibility:** Decouples the client from the adapted class, making the system more flexible.
*   **Maintainability:** Centralizes the adaptation logic, making it easier to manage changes.

**How to use:**
1.  **Target Interface:** Define the interface that your client expects.
2.  **Adaptee:** Identify the existing class with the incompatible interface.
3.  **Adapter:** Create an adapter class that implements the Target Interface and contains an instance of the Adaptee (Object Adapter) or inherits from the Adaptee (Class Adapter).
4.  **Adaptation Logic:** In the adapter, translate calls from the Target Interface to the Adaptee's interface.

**Examples to Remember:**

1.  **Legacy Logger:** Your new application uses a `Logger` interface with a `log(String message)` method. You have a legacy `OldLogger` class with a `writeLog(String text)` method. An `OldLoggerAdapter` can implement `Logger` and internally call `oldLogger.writeLog()`.

2.  **Third-Party Payment Gateway:** Your e-commerce platform expects a `PaymentGateway` interface with `processPayment(Amount amount, CardDetails card)`. You integrate with a third-party gateway that has a `makeTransaction(double value, String cardNumber, int expiryMonth, int expiryYear)` method. An `PaymentGatewayAdapter` bridges this gap.

3.  **Power Adapters:** A physical analogy: a power adapter converts one type of electrical plug/voltage to another, allowing a device to work in a different region without modifying the device itself.

```java
// Target Interface (what the client expects)
interface NewLogger {
    void logMessage(String message);
}

// Adaptee (the existing incompatible class)
class LegacyLogger {
    public void writeLog(String text) {
        System.out.println("Legacy Log: " + text);
    }
}

// Adapter
class LegacyLoggerAdapter implements NewLogger {
    private LegacyLogger legacyLogger;

    public LegacyLoggerAdapter(LegacyLogger legacyLogger) {
        this.legacyLogger = legacyLogger;
    }

    @Override
    public void logMessage(String message) {
        // Adapt the call
        legacyLogger.writeLog(message);
    }
}

// Client Code
public class Client {
    public static void main(String[] args) {
        LegacyLogger oldLogger = new LegacyLogger();
        NewLogger adapter = new LegacyLoggerAdapter(oldLogger);

        adapter.logMessage("This message is logged via the adapter.");
    }
}
```