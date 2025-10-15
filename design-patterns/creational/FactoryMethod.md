# Factory Method Design Pattern

**Purpose:** Defines an interface for creating an object, but lets subclasses decide which class to instantiate. It defers instantiation to subclasses.

**Demand for this pattern:**
When a class cannot anticipate the class of objects it must create, or when a class wants its subclasses to specify the objects it creates. It promotes loose coupling by removing the need to bind application-specific classes into the code.

**Without it:**
Without the Factory Method, the client code would be tightly coupled to concrete product classes. This means that if you need to introduce a new product type, you would have to modify the client code directly, violating the Open/Closed Principle.

**How it solved the problem:**
The Factory Method pattern introduces a `Creator` class with an abstract `factoryMethod()` that returns a `Product` object. Subclasses of `Creator` (Concrete Creators) override this method to return specific `ConcreteProduct` objects. The client code interacts with the `Creator` interface, making it independent of the actual product creation process.

**Is it an exclusive pattern for this kind of problem?**
For deferring object instantiation to subclasses, Factory Method is the primary pattern. It is often confused with Abstract Factory, but Abstract Factory provides an interface for creating *families* of related objects, whereas Factory Method focuses on creating a *single* product. Simple Factory (not a GoF pattern) is a simpler version where a static method creates objects.

**When to use:**
*   A class can't anticipate the class of objects it must create.
*   A class wants its subclasses to specify the objects it creates.
*   You want to localize the knowledge of which concrete class to instantiate.

**Why to use:**
*   **Loose Coupling:** Decouples the client from the concrete product classes.
*   **Extensibility:** Easy to add new product types without modifying existing client code.
*   **Flexibility:** Allows subclasses to control the object creation process.

**How to use:**
1.  **Product Interface:** Declare an interface for the type of objects the factory method will create.
2.  **Concrete Products:** Implement the `Product` interface.
3.  **Creator (Abstract Class/Interface):** Declare the `factoryMethod()`, which returns an object of type `Product`. It may also define other methods that use the factory method.
4.  **Concrete Creators:** Override the `factoryMethod()` to return an instance of a `ConcreteProduct`.

**Example:**

```java
// Product Interface
interface Vehicle {
    void drive();
}

// Concrete Product 1
class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a Car.");
    }
}

// Concrete Product 2
class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a Bike.");
    }
}

// Creator (Abstract Class)
abstract class VehicleFactory {
    public abstract Vehicle createVehicle();

    public void startDriving() {
        Vehicle vehicle = createVehicle();
        vehicle.drive();
    }
}

// Concrete Creator 1
class CarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}

// Concrete Creator 2
class BikeFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Bike();
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
        carFactory.startDriving();

        VehicleFactory bikeFactory = new BikeFactory();
        bikeFactory.startDriving();
    }
}
```