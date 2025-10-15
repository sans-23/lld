# Builder vs. Decorator Design Patterns

## Common Confusion
Both Builder and Decorator patterns involve constructing or modifying objects. The confusion often arises because both can be used to create objects with varying characteristics. However, their fundamental goals and the stage at which they operate are distinct.

## How They Differ

### 1. Purpose
*   **Builder:** To *construct a complex object step-by-step* and separate its construction from its representation. It focuses on *how an object is created*.
*   **Decorator:** To *dynamically add new responsibilities or behaviors* to an object without altering its structure. It focuses on *enhancing an existing object's capabilities*.

### 2. Stage of Operation
*   **Builder:** Operates during the *object creation phase*. It's about building a new, fully formed object.
*   **Decorator:** Operates on an *already existing object*. It wraps an object to add functionality at runtime.

### 3. Output
*   **Builder:** Produces a *new, complex object* with all its specified parts and configurations.
*   **Decorator:** Produces an *enhanced version of an existing object*, maintaining its original interface while adding new features.

### 4. Relationship
*   **Builder:** The builder object is typically distinct from the product it builds. The client interacts with the builder, and the builder returns the product.
*   **Decorator:** The decorator object wraps the original object and implements the *same interface* as the object it decorates. This allows for transparent wrapping.

### 5. Mutability
*   **Builder:** Often used to create *immutable objects* where all properties are set during construction and cannot be changed afterward.
*   **Decorator:** Adds functionality to an *existing object*, which can be mutable or immutable. The decoration itself is dynamic.

## When to Use

*   **Builder:** Use when:
    *   An object's construction is complex, involving many optional parameters or steps.
    *   You want to create different representations of a complex object using the same construction process.
    *   You want to avoid telescoping constructors and ensure object immutability after creation.

*   **Decorator:** Use when:
    *   You need to add responsibilities to individual objects dynamically and transparently, without affecting other objects.
    *   You want to extend functionality without using subclassing (which can lead to a proliferation of subclasses).
    *   You need to combine multiple behaviors in a flexible way.

## How to Remember What to Use When

*   **Builder = Assemble / Configure:** Think of building a custom car or a complex meal. You assemble various parts and configure options to create a *new, complete product*. It's about the *initial creation*.
    *   *Analogy:* Ordering a custom-built computer. You select components (CPU, RAM, GPU) and the builder assembles them into a single, configured machine.

*   **Decorator = Enhance / Wrap:** Think of adding layers to an existing gift or adding features to a basic coffee. You *wrap an existing item* to *add extra functionality or appearance*. It's about *modifying or extending* an object's behavior.
    *   *Analogy:* Adding whipped cream, syrup, and sprinkles to a plain coffee. The coffee remains coffee, but its taste and appearance are enhanced.

## Key Takeaways
*   **Builder:** Focuses on *constructing* a complex object from scratch.
*   **Decorator:** Focuses on *enhancing* an existing object with new responsibilities.
*   If you're *creating a new object* with many configuration options, think **Builder**.
*   If you're *adding features to an existing object* dynamically, think **Decorator**.
