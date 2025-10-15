# Decorator Design Pattern

**Purpose:** Attaches new responsibilities to objects dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.

**Demand for this pattern:**
When you need to add responsibilities to individual objects, not to an entire class, and do so dynamically and transparently. Subclassing can lead to a class explosion if you have many independent features that can be combined in various ways. It also makes it difficult to remove a responsibility once it has been added.

**Without it:**
Without the Decorator pattern, you would typically:
1.  **Subclassing:** Create a new subclass for every combination of features. This leads to a large number of classes and a rigid hierarchy that is hard to manage and extend.
2.  **Static Composition:** Add all possible features to the base class, making it bloated and difficult to maintain, even if most instances don't need all features.

**How it solved the problem:**
The Decorator pattern allows you to wrap an existing object (the component) with a decorator object. Both the component and the decorator implement the same interface. The decorator adds its own behavior before or after delegating the request to the wrapped component. This allows you to add responsibilities dynamically and combine multiple decorators, providing a flexible way to extend functionality without modifying the original object's code.

**Is it an exclusive pattern for this kind of problem?**
For dynamically adding responsibilities to objects, Decorator is the primary pattern. It is often confused with Proxy, but Proxy focuses on controlling access to an object, while Decorator focuses on adding new behaviors. Adapter changes an interface, while Decorator preserves it. Composite can also involve wrapping, but its goal is to represent part-whole hierarchies.

**When to use:**
*   To add responsibilities to individual objects dynamically and transparently, without affecting other objects.
*   For responsibilities that can be withdrawn.
*   When extension by subclassing is impractical. Sometimes a large number of independent extensions are possible, and this would produce an explosion of subclasses to support every combination.

**Why to use:**
*   **Flexibility:** Adds functionality at runtime, allowing for dynamic combinations of behaviors.
*   **Avoids Class Explosion:** Reduces the need for many subclasses to support various feature combinations.
*   **Open/Closed Principle:** Allows extending functionality without modifying existing code.
*   **Transparency:** Clients can treat decorated objects and original objects uniformly.

**How to use:**
1.  **Component Interface:** Define an interface for objects that can have responsibilities added to them.
2.  **Concrete Component:** Implement the `Component` interface. This is the original object to which responsibilities will be added.
3.  **Decorator (Abstract Class):** Maintain a reference to a `Component` object and implement the `Component` interface. It typically delegates all requests to the wrapped component.
4.  **Concrete Decorators:** Extend the `Decorator` and add specific responsibilities before or after delegating to the wrapped component.

**Examples to Remember:**

1.  **Coffee Shop:** A `Coffee` (Component) can be decorated with `Milk`, `Sugar`, `Caramel` (Concrete Decorators). Each decorator adds a cost and a description to the coffee.

2.  **Window System:** A `Window` (Component) can be decorated with `Scrollbar`, `Border` (Concrete Decorators). Each decorator adds visual elements or behavior to the window.

3.  **Input/Output Streams:** In Java, `FileInputStream` (Concrete Component) can be wrapped by `BufferedInputStream`, `DataInputStream` (Concrete Decorators) to add buffering or data reading capabilities.

```java
// Component Interface
interface Coffee {
    String getDescription();
    double getCost();
}

// Concrete Component
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }

    @Override
    public double getCost() {
        return 2.0;
    }
}

// Decorator (Abstract Class)
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee decoratedCoffee) {
        this.decoratedCoffee = decoratedCoffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// Concrete Decorator 1
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.5;
    }
}

// Concrete Decorator 2
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.2;
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        System.out.println(coffee.getDescription() + " Cost: $" + coffee.getCost());

        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " Cost: $" + coffee.getCost());

        coffee = new SugarDecorator(coffee);
        System.out.println(coffee.getDescription() + " Cost: $" + coffee.getCost());

        Coffee customCoffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println(customCoffee.getDescription() + " Cost: $" + customCoffee.getCost());
    }
}
```