# Abstract Factory Design Pattern

**Purpose:** Provides an interface for creating families of related or dependent objects without specifying their concrete classes.

**Demand for this pattern:**
When a system needs to be independent of how its products are created, composed, and represented. It's particularly useful when you need to create families of related objects (e.g., UI components for different operating systems) and ensure that the created objects are compatible with each other.

**Without it:**
Without the Abstract Factory, the client code would be tightly coupled to concrete product classes and their specific factories. This would make it difficult to switch between different families of products (e.g., changing from Windows UI to Mac UI) without modifying a lot of client code.

**How it solved the problem:**
The Abstract Factory pattern introduces an abstract `Factory` interface with methods for creating each type of product in the family. Concrete factories implement this interface to produce specific concrete products. The client code interacts only with the abstract factory and product interfaces, making it independent of the actual concrete classes being created.

**Is it an exclusive pattern for this kind of problem?**
For creating families of related objects, Abstract Factory is the primary pattern. It is often used in conjunction with Factory Method (where each method in the Abstract Factory is a Factory Method). It differs from Factory Method in that it creates *multiple* types of products, whereas Factory Method creates a *single* product.

**When to use:**
*   A system should be independent of how its products are created, composed, and represented.
*   A system should be configured with one of multiple families of products.
*   A family of related product objects is designed to be used together, and you need to enforce this constraint.

**Why to use:**
*   **Product Families:** Ensures that products created by a factory are compatible with each other.
*   **Loose Coupling:** Decouples the client from concrete product classes and concrete factories.
*   **Extensibility:** Easy to introduce new families of products without changing client code.
*   **Consistency:** Guarantees that a client uses products from only one family at a time.

**How to use:**
1.  **Abstract Product Interfaces:** Declare interfaces for each distinct product in the family (e.g., `Button`, `Checkbox`).
2.  **Concrete Products:** Implement the `Abstract Product` interfaces for specific variations (e.g., `WindowsButton`, `MacOSButton`).
3.  **Abstract Factory Interface:** Declare an interface with a set of creation methods for each abstract product (e.g., `createButton()`, `createCheckbox()`).
4.  **Concrete Factories:** Implement the `Abstract Factory` interface to create concrete products of a specific family (e.g., `WindowsFactory`, `MacOSFactory`).
5.  **Client:** Uses the `Abstract Factory` and `Abstract Product` interfaces to create and interact with products.

**Example:**

```java
// Abstract Product A
interface Button {
    void paint();
}

// Concrete Product A1
class WindowsButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a button in Windows style.");
    }
}

// Concrete Product A2
class MacOSButton implements Button {
    @Override
    public void paint() {
        System.out.println("Rendering a button in macOS style.");
    }
}

// Abstract Product B
interface Checkbox {
    void paint();
}

// Concrete Product B1
class WindowsCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering a checkbox in Windows style.");
    }
}

// Concrete Product B2
class MacOSCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering a checkbox in macOS style.");
    }
}

// Abstract Factory
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factory 1
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

// Concrete Factory 2
class MacOSFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        GUIFactory factory;
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("mac")) {
            factory = new MacOSFactory();
        } else {
            factory = new WindowsFactory();
        }

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.paint();
        checkbox.paint();
    }
}
```