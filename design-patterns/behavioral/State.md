# State Design Pattern

**Purpose:** Lets an object alter its behavior when its internal state changes. The object will appear to change its class.

**Demand for this pattern:**
When an object's behavior depends on its state, and it must change its behavior at runtime depending on that state. Directly implementing state-dependent behavior with large conditional statements (`if-else` or `switch`) makes the code complex, hard to maintain, and difficult to add new states.

**Without it:**
Without the State pattern, you would typically have:
1.  **Large Conditional Statements:** A single class with many `if-else` or `switch` statements to handle different behaviors based on the current state. This makes the code hard to read, extend, and prone to errors.
2.  **Tight Coupling:** The object's behavior is tightly coupled to its state, making it difficult to change or add new states without modifying the core logic.

**How it solved the problem:**
The State pattern extracts all state-specific behaviors into separate classes that implement a common `State` interface. The original object (Context) maintains a reference to a `State` object and delegates state-dependent requests to it. When the Context's state changes, it simply changes its `State` object, and its behavior changes accordingly. This makes the code cleaner, more extensible, and easier to maintain.

**Is it an exclusive pattern for this kind of problem?**
For managing state-dependent behavior, State is the primary pattern. It is often confused with Strategy, but Strategy focuses on interchangeable algorithms (how to do something), while State focuses on changing an object's behavior based on its internal state (what it is doing). It differs from other patterns by specifically addressing the problem of state-dependent behavior.

**When to use:**
*   An object's behavior depends on its state, and it must change its behavior at runtime depending on that state.
*   Operations have large, multipart conditional statements that depend on the object's state.
*   You want to avoid conditional logic that switches between multiple states.

**Why to use:**
*   **Encapsulation:** Encapsulates state-specific behavior into separate classes.
*   **Open/Closed Principle:** Easy to add new states without modifying existing Context or State classes.
*   **Cleaner Code:** Replaces complex conditional statements with a cleaner, object-oriented approach.
*   **Runtime Flexibility:** Allows an object's behavior to change dynamically at runtime.

**How to use:**
1.  **Context:** Defines the interface of interest to clients. It maintains an instance of a `ConcreteState` subclass that defines the current state.
2.  **State Interface:** Defines an interface for encapsulating the behavior associated with a particular state of the `Context`.
3.  **Concrete States:** Each subclass implements a behavior associated with a state of the `Context`. A `ConcreteState` object knows its `Context` object and can change the state of the `Context`.

**Example:**
Consider a simple traffic light that changes its behavior based on its current state (Red, Yellow, Green).

```java
// State Interface
interface TrafficLightState {
    void handleRequest(TrafficLight trafficLight);
}

// Concrete State 1
class RedLightState implements TrafficLightState {
    @Override
    public void handleRequest(TrafficLight trafficLight) {
        System.out.println("Traffic Light is RED. Stop!");
        trafficLight.setState(new GreenLightState()); // Transition to Green
    }
}

// Concrete State 2
class YellowLightState implements TrafficLightState {
    @Override
    public void handleRequest(TrafficLight trafficLight) {
        System.out.println("Traffic Light is YELLOW. Prepare to stop or go.");
        trafficLight.setState(new RedLightState()); // Transition to Red
    }
}

// Concrete State 3
class GreenLightState implements TrafficLightState {
    @Override
    public void handleRequest(TrafficLight trafficLight) {
        System.out.println("Traffic Light is GREEN. Go!");
        trafficLight.setState(new YellowLightState()); // Transition to Yellow
    }
}

// Context
class TrafficLight {
    private TrafficLightState currentState;

    public TrafficLight() {
        // Initial state
        currentState = new RedLightState();
    }

    public void setState(TrafficLightState state) {
        this.currentState = state;
    }

    public void request() {
        currentState.handleRequest(this);
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        trafficLight.request(); // Red -> Green
        trafficLight.request(); // Green -> Yellow
        trafficLight.request(); // Yellow -> Red
        trafficLight.request(); // Red -> Green
    }
}
```