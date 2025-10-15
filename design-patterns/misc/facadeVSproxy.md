# Facade vs. Proxy Design Patterns

## Common Confusion
Both Facade and Proxy patterns involve creating a wrapper object around another object or a set of objects. This can lead to confusion because both patterns provide a simplified or controlled access point. However, their *intent* and *scope* are fundamentally different.

## How They Differ

### 1. Purpose
*   **Facade:** To provide a *simplified, unified interface* to a complex subsystem. It aims to make a complex system easier to use.
*   **Proxy:** To provide a *surrogate or placeholder* for another object to *control access* to it. It aims to add a layer of control or management over the real object.

### 2. Scope
*   **Facade:** Works with an *entire subsystem* (multiple classes/objects). It simplifies the interaction with a group of related components.
*   **Proxy:** Works with a *single object*. It acts as a stand-in for one specific real object.

### 3. Relationship
*   **Facade:** Doesn't necessarily implement the same interface as the subsystem classes it wraps. It provides a *new, simpler interface*.
*   **Proxy:** Implements the *same interface* as the real subject it represents. This allows the proxy to be used interchangeably with the real object.

### 4. What They Hide
*   **Facade:** Hides the *complexity of the subsystem's internal workings* and its multiple components.
*   **Proxy:** Hides the *existence or specific details of the real object*, controlling when and how it's accessed.

### 5. When to Use
*   **Facade:** Use when you want to *simplify the client's interaction* with a complex set of classes, providing a higher-level API.
*   **Proxy:** Use when you need to *control, manage, or enhance access* to a single object (e.g., lazy loading, security, remote access, logging).

## How to Remember What to Use When

*   **Facade = Simplification / Unified View:** Think of a building's facade – it's the simple, external view of a complex internal structure. You use it to make a complex system *easier to use*.
    *   *Analogy:* A remote control for a home theater system. It simplifies turning on/off multiple devices and setting inputs with a single button.

*   **Proxy = Control / Stand-in:** Think of a political proxy or a legal proxy – someone who acts *on behalf of* another, often with added responsibilities or restrictions. You use it to *control access* to a specific object.
    *   *Analogy:* A credit card company acting as a proxy for your bank account. It handles transactions and adds security/fraud checks before interacting with your actual funds.

## Key Takeaways
*   **Facade:** Simplifies a *complex subsystem*.
*   **Proxy:** Controls access to a *single object*.
*   If you're dealing with *many objects* and want to make them *easier to use*, think **Facade**.
*   If you're dealing with *one object* and want to *control or enhance its access*, think **Proxy**.
