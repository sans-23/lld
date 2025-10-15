# Strategy vs. Bridge Design Patterns

## Common Confusion
Both Strategy and Bridge patterns involve using interfaces and delegation to achieve flexibility and decouple concerns. This can lead to confusion because both patterns allow for interchangeable behaviors or implementations. However, they address different types of decoupling and operate on different levels of abstraction.

## How They Differ

### 1. Purpose
*   **Strategy:** To *encapsulate a family of algorithms* and make them interchangeable. It focuses on *how an object performs a specific task*.
*   **Bridge:** To *decouple an abstraction from its implementation* so that the two can vary independently. It focuses on *separating what an object is from how it does its work*.

### 2. What They Decouple
*   **Strategy:** Decouples the *client (Context) from the algorithm it uses*. The client doesn't need to know the specifics of the algorithm.
*   **Bridge:** Decouples the *abstraction from its implementation*. Both can be extended independently without affecting the other.

### 3. Relationship to Client
*   **Strategy:** The client (Context) *chooses and uses a specific algorithm* (Strategy) to perform an action. The client is aware of the Strategy interface.
*   **Bridge:** The client interacts with the *abstraction*, which then delegates to its *implementation*. The client is generally unaware of the specific implementation details.

### 4. Number of Hierarchies
*   **Strategy:** Typically involves *two hierarchies*: one for the Context and one for the Strategy algorithms.
*   **Bridge:** Involves *two orthogonal hierarchies*: one for the abstraction and one for the implementation. These hierarchies can evolve independently.

### 5. When to Use
*   **Strategy:** Use when:
    *   You have multiple algorithms for a specific task and want to select one at runtime.
    *   You want to avoid conditional logic for choosing algorithms.
    *   You want to make algorithms easily extensible without modifying client code.

*   **Bridge:** Use when:
    *   You want to avoid a permanent binding between an abstraction and its implementation.
    *   Both the abstraction and its implementation need to be extensible independently.
    *   You have a class explosion problem due to combining different abstractions and implementations.

## How to Remember What to Use When

*   **Strategy = Interchangeable Algorithms / How to Do It:** Think of different strategies to achieve a goal (e.g., different ways to pay, different ways to sort). You pick *one way to do something* from a set of options.
    *   *Analogy:* A GPS navigation app. You can choose different routing strategies (fastest, shortest, avoid tolls) to get to your destination. The app (client) uses a chosen strategy.

*   **Bridge = Separate What from How / Two Dimensions:** Think of a bridge connecting two separate landmasses. It allows them to exist and develop independently. You use it to separate *what something is* (abstraction) from *how it works* (implementation).
    *   *Analogy:* A universal remote control (abstraction) that can operate various brands of TVs (implementations). The remote doesn't care about the TV's internal electronics, only its interface.

## Key Takeaways
*   **Strategy:** Deals with *interchangeable behaviors (algorithms)* for a single task.
*   **Bridge:** Deals with *decoupling an abstraction from its implementation* across two independent hierarchies.
*   If you're changing *how an object behaves* at runtime, think **Strategy**.
*   If you're separating *what an object is from how it's built or operates* to allow independent evolution, think **Bridge**.
