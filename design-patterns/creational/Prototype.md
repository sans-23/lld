# Prototype Design Pattern

**Purpose:** Specifies the kinds of objects to create using a prototypical instance, and creates new objects by copying this prototype.

**Demand for this pattern:**
When creating new objects is expensive or complex, especially when many similar objects are needed. Instead of instantiating new objects from scratch, which might involve complex initialization logic, the Prototype pattern allows you to clone existing objects. This is particularly useful when the object creation process involves database queries, network calls, or heavy computations.

**Without it:**
Without the Prototype pattern, you would typically:
1.  **Direct Instantiation:** Create new objects using constructors, which can be verbose and resource-intensive if the object has many parameters or complex setup.
2.  **Subclassing for Variations:** Create a new subclass for each variation of an object, leading to a class explosion and rigid hierarchies.

**How it solved the problem:**
The Prototype pattern defines a `clone()` method (or similar) in a common interface or base class. Concrete product classes implement this method to create a copy of themselves. The client then creates new objects by asking a prototype object to clone itself, rather than directly instantiating a new object. This decouples the client from the concrete classes and the object creation process.

**Is it an exclusive pattern for this kind of problem?**
For creating new objects by copying existing ones, Prototype is the primary pattern. It is often used as an alternative to Abstract Factory or Factory Method when the cost of creating a new object is high, or when you need to create objects from a set of predefined, customizable instances. It relies on the concept of deep or shallow copying.

**When to use:**
*   The system should be independent of how its products are created, composed, and represented.
*   The classes to instantiate are specified at runtime, for example, by dynamic loading.
*   You want to avoid building a class hierarchy of factories that parallels the class hierarchy of products.
*   Instances of a class can have one of only a few different combinations of state. It might be more convenient to pre-instantiate these and clone them as needed.

**Why to use:**
*   **Reduced Object Creation Cost:** Avoids expensive object creation by cloning existing instances.
*   **Flexibility:** Allows for dynamic creation of new objects without knowing their concrete classes.
*   **Avoids Subclassing:** Reduces the need for parallel class hierarchies for different product variations.
*   **Runtime Configuration:** Enables objects to be configured at runtime and then cloned.

**How to use:**
1.  **Prototype Interface:** Declare an interface (or abstract class) with a `clone()` method.
2.  **Concrete Prototypes:** Implement the `Prototype` interface and define the actual cloning logic. This typically involves calling the superclass's `clone()` method and then copying any mutable fields.
3.  **Client:** Creates new objects by calling the `clone()` method on a prototype instance.

**Examples to Remember:**

1.  **Document Editor:** In a document editor, creating a new document with predefined templates (e.g., a resume template, a letter template) can be done by cloning a prototype template object.

2.  **Game Development (Enemy Spawning):** In a game, spawning many similar enemy characters with slight variations can be efficiently done by cloning a prototype enemy object and then modifying its specific properties.

3.  **Database Connection Objects:** If establishing a database connection is expensive, you can create a prototype connection object and then clone it for each new request, modifying only the session-specific details.

```java
import java.util.HashMap;
import java.util.Map;

// Prototype Interface
interface Shape extends Cloneable {
    void draw();
    Shape clone();
}

// Concrete Prototype 1
class Circle implements Shape {
    private String color;

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle.");
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// Concrete Prototype 2
class Rectangle implements Shape {
    private String color;

    public Rectangle(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " rectangle.");
    }

    @Override
    public Shape clone() {
        try {
            return (Shape) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

// Prototype Registry (Optional, for managing prototypes)
class ShapeCache {
    private static Map<String, Shape> shapeMap = new HashMap<>();

    static {
        shapeMap.put("circle", new Circle("red"));
        shapeMap.put("rectangle", new Rectangle("blue"));
    }

    public static Shape getShape(String shapeName) {
        Shape cachedShape = shapeMap.get(shapeName);
        return cachedShape.clone();
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        Shape redCircle = ShapeCache.getShape("circle");
        redCircle.draw();

        Shape blueRectangle = ShapeCache.getShape("rectangle");
        blueRectangle.draw();

        // Modify the cloned object (demonstrates independent copies)
        Circle anotherRedCircle = (Circle) ShapeCache.getShape("circle");
        // In a real scenario, you might have a setColor method or similar
        // For simplicity, let's assume we can cast and modify if needed, though often prototypes are immutable after cloning
        // If deep copy is needed, clone() implementation would handle it.
        System.out.println("\nAnother red circle (cloned):");
        anotherRedCircle.draw();
    }
}
```