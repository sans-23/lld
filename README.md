# LLD - Design Patterns

This repository contains notes and examples related to Low-Level Design (LLD) and various Design Patterns. The goal is to provide concise, revision-friendly material for understanding and applying common software design solutions.

## Design Patterns Overview

Design patterns are formalized best practices that provide typical solutions to common problems in software design. They are categorized into three main types:

### 1. Creational Patterns
These patterns deal with object creation mechanisms, aiming to increase flexibility and reuse of existing code while controlling the object creation process.

*   [**Builder**](design-patterns/creational/Builder.md): Separates the construction of a complex object from its representation. *Use when an object has many optional parameters or requires a step-by-step construction.* (e.g., configuring a complex `Burger` with various toppings).
    *   [Builder vs. Decorator](design-patterns/misc/builderVSdecorator.md)
*   [**Factory Method**](design-patterns/creational/FactoryMethod.md): Defines an interface for creating an object, but lets subclasses decide which class to instantiate. *Use when a class can't anticipate the class of objects it must create.* (e.g., creating different types of `Vehicle` objects based on input).
*   [**Abstract Factory**](design-patterns/creational/AbstractFactory.md): Provides an interface for creating families of related or dependent objects without specifying their concrete classes. *Use when your system needs to be independent of how its products are created, composed, and represented.* (e.g., creating UI components for different operating systems).
*   [**Singleton**](design-patterns/creational/Singleton.md): Ensures a class has only one instance and provides a global point of access to it. *Use when exactly one instance of a class is required to coordinate actions across the system.* (e.g., a `Logger` or `ConfigurationManager`).
*   [**Prototype**](design-patterns/creational/Prototype.md): Specifies the kinds of objects to create using a prototypical instance, and creates new objects by copying this prototype. *Use when creating an object is expensive or complex, and you need to create many similar objects.* (e.g., cloning a pre-configured `Document` object).

### 2. Structural Patterns
These patterns concern class and object composition, describing how to assemble objects and classes into larger structures while keeping these structures flexible and efficient.

*   [**Adapter**](design-patterns/structural/Adapter.md): Allows objects with incompatible interfaces to collaborate. *Use when you want to use an existing class, but its interface doesn't match the one you need.* (e.g., integrating a `LegacyLogger` with a `NewLogger` interface).
*   [**Bridge**](design-patterns/structural/Bridge.md): Decouples an abstraction from its implementation so that the two can vary independently. *Use when you want to avoid a permanent binding between an abstraction and its implementation, allowing both to evolve independently.* (e.g., `Shape` and `Color` can vary without class explosion).
    *   [Strategy vs. Bridge](design-patterns/misc/strategyVSbridge.md)
*   [**Composite**](design-patterns/structural/Composite.md): Composes objects into tree structures to represent part-whole hierarchies, letting clients treat individual objects and compositions uniformly. *Use when you want to represent part-whole hierarchies and clients should ignore the difference between individual objects and compositions.* (e.g., `Files` and `Folders` in a file system).
*   [**Decorator**](design-patterns/structural/Decorator.md): Attaches new responsibilities to objects dynamically. *Use when you need to add responsibilities to individual objects dynamically and transparently, without affecting other objects.* (e.g., adding `Milk`, `Sugar` to a `Coffee`).
*   [**Facade**](design-patterns/structural/Facade.md): Provides a simplified interface to a complex subsystem. *Use when you want to provide a simple, unified interface to a complex set of classes.* (e.g., a `HomeTheaterFacade` to control multiple entertainment devices).
    *   [Facade vs. Proxy](design-patterns/misc/facadeVSproxy.md)
*   [**Flyweight**](design-patterns/structural/Flyweight.md): Lets you fit more objects into the available RAM by sharing common parts of state between multiple objects. *Use when you have a large number of fine-grained objects that share common state.* (e.g., character objects in a text editor).
*   [**Proxy**](design-patterns/structural/Proxy.md): Provides a surrogate or placeholder for another object to control access to it. *Use when you need to control, manage, or enhance access to a single object.* (e.g., `LazyImageProxy` for expensive image loading, `BankAccountProxy` for access control).

### 3. Behavioral Patterns
These patterns are concerned with algorithms and the assignment of responsibilities between objects, describing how objects and classes interact and distribute responsibility.

*   [**Strategy**](design-patterns/behavioral/Strategy.md): Defines a family of algorithms, encapsulates each one, and makes them interchangeable. *Use when an object needs to perform a task using one of several algorithms, and you want to choose the algorithm at runtime.* (e.g., different `PaymentStrategy` for a `ShoppingCart`).
*   [**Chain of Responsibility**](design-patterns/behavioral/ChainOfResponsibility.md): Passes requests along a chain of handlers. *Use when you want to decouple the sender of a request from its receiver, and multiple objects can handle the request.* (e.g., `ApprovalProcess` where requests go through different levels of managers).
*   [**Command**](design-patterns/behavioral/Command.md): Turns a request into a stand-alone object that contains all information about the request. *Use when you want to parameterize objects with operations, queue operations, or support undoable operations.* (e.g., `MenuItem` objects invoking `Command` objects).
*   [**Iterator**](design-patterns/behavioral/Iterator.md): Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation. *Use when you need to traverse elements of a collection without exposing its internal structure.* (e.g., iterating through a `List` or `Tree`).
*   [**Mediator**](design-patterns/behavioral/Mediator.md): Reduces chaotic dependencies between objects by centralizing communication through a mediator object. *Use when a set of objects communicate in complex but well-defined ways, and you want to avoid direct coupling between them.* (e.g., `ChatRoom` mediating communication between `Users`).
*   [**Memento**](design-patterns/behavioral/Memento.md): Lets you save and restore the previous state of an object without revealing the details of its implementation. *Use when you need to capture and externalize an object's internal state so that the object can be restored to this state later.* (e.g., `Undo/Redo` functionality in an editor).
*   [**Observer**](design-patterns/behavioral/Observer.md): Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically. *Use when a change in one object requires changing others, and you don't know how many objects need to be changed.* (e.g., `StockMarket` notifying `Investors` of price changes).
*   [**State**](design-patterns/behavioral/State.md): Lets an object alter its behavior when its internal state changes. The object will appear to change its class. *Use when an object's behavior depends on its state, and it must change its behavior at runtime depending on that state.* (e.g., `TrafficLight` changing behavior based on `Red`, `Yellow`, `Green` states).
*   [**Template Method**](design-patterns/behavioral/TemplateMethod.md): Defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure. *Use when you want to implement the invariant parts of an algorithm once and let subclasses implement the behavior that can vary.* (e.g., `BuildHouse` template with steps like `buildWalls`, `buildRoof`).
*   [**Visitor**](design-patterns/behavioral/Visitor.md): Lets you separate algorithms from the objects on which they operate. *Use when you need to perform an operation on all elements of an object structure, and you want to add new operations without modifying the element classes.* (e.g., `ExportVisitor` to export different `Shape` objects to XML/JSON).