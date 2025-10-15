# Design Patterns

## What are Design Patterns?
Design patterns are typical solutions to common problems in software design. They are not a finished design that can be directly transformed into code; rather, they are descriptions or templates for how to solve a problem that can be used in many different situations. They are formalized best practices that a programmer can use to solve common problems when designing an application or system.

## Broad Types of Design Patterns
Design patterns are generally categorized into three main types based on their purpose:

### 1. Creational Patterns
These patterns provide various object creation mechanisms, which increase flexibility and reuse of existing code. They deal with object creation in a manner suitable to the situation. The basic form of object creation could result in design problems or added complexity to the design. Creational design patterns solve this problem by controlling the object creation process.

**Patterns under Creational:**
*   **Singleton:** Ensures a class has only one instance and provides a global point of access to it.
*   **Factory Method:** Defines an interface for creating an object, but lets subclasses alter the type of objects that will be created.
*   **Abstract Factory:** Provides an interface for creating families of related or dependent objects without specifying their concrete classes.
*   **Builder:** Separates the construction of a complex object from its representation so that the same construction process can create different representations.
*   **Prototype:** Specifies the kinds of objects to create using a prototypical instance, and creates new objects by copying this prototype.

### 2. Structural Patterns
These patterns concern class and object composition. They describe how to assemble objects and classes into larger structures while keeping these structures flexible and efficient. Structural patterns explain how to assemble objects and classes into larger structures, while keeping these structures flexible and efficient.

**Patterns under Structural:**
*   **Adapter:** Allows objects with incompatible interfaces to collaborate.
*   **Bridge:** Decouples an abstraction from its implementation so that the two can vary independently.
*   **Composite:** Composes objects into tree structures to represent part-whole hierarchies. Lets clients treat individual objects and compositions of objects uniformly.
*   **Decorator:** Attaches new responsibilities to objects dynamically. Provides a flexible alternative to subclassing for extending functionality.
*   **Facade:** Provides a simplified interface to a complex subsystem.
*   **Flyweight:** Lets you fit more objects into the available RAM by sharing common parts of state between multiple objects instead of keeping all of the data in each object.
*   **Proxy:** Provides a surrogate or placeholder for another object to control access to it.

### 3. Behavioral Patterns
These patterns are concerned with algorithms and the assignment of responsibilities between objects. They describe how objects and classes interact and distribute responsibility. Behavioral patterns describe how objects and classes interact and distribute responsibility.

**Patterns under Behavioral:**
*   **Chain of Responsibility:** Passes requests along a chain of handlers. Upon receiving a request, each handler decides either to process the request or to pass it to the next handler in the chain.
*   **Command:** Turns a request into a stand-alone object that contains all information about the request. This transformation lets you parameterize methods with different requests, delay or queue a request's execution, and support undoable operations.
*   **Iterator:** Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation.
*   **Mediator:** Reduces chaotic dependencies between objects. The pattern restricts direct communications between the objects and forces them to collaborate only via a mediator object.
*   **Memento:** Lets you save and restore the previous state of an object without revealing the details of its implementation.
*   **Observer:** Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
*   **State:** Lets an object alter its behavior when its internal state changes. The object will appear to change its class.
*   **Strategy:** Defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.
*   **Template Method:** Defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure.
*   **Visitor:** Lets you separate algorithms from the objects on which they operate. A visitor can be extended to operate on objects of different types without changing the classes of those objects.
