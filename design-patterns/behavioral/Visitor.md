# Visitor Design Pattern

**Purpose:** Lets you separate algorithms from the objects on which they operate. A visitor can be extended to operate on objects of different types without changing the classes of those objects.

**Demand for this pattern:**
When you have an object structure (e.g., a tree of different types of nodes) and you need to perform new operations on these objects frequently, but you don't want to modify the classes of the objects themselves. Adding new operations directly to the object classes would violate the Open/Closed Principle and lead to a proliferation of methods in those classes.

**Without it:**
Without the Visitor pattern, you would typically:
1.  **Pollute Object Classes:** Add new methods to each class in the object structure every time a new operation is needed, making the classes bloated and hard to maintain.
2.  **Conditional Logic in Client:** Use `instanceof` or type-checking in client code to determine the type of object and then perform the appropriate operation, leading to complex and brittle code.

**How it solved the problem:**
The Visitor pattern introduces a `Visitor` interface with a `visit()` method for each concrete element type in the object structure. Each concrete element class implements an `accept()` method that takes a `Visitor` object as an argument and calls the appropriate `visit()` method on the visitor. This allows you to define new operations (new visitors) without changing the element classes, and to apply different operations to different element types in a type-safe manner.

**Is it an exclusive pattern for this kind of problem?**
For separating algorithms from the objects they operate on and adding new operations without modifying object classes, Visitor is the primary pattern. It is often used in conjunction with Composite (to traverse tree structures) or Iterator (to iterate over elements). It differs from other patterns by focusing specifically on adding new operations to an existing object structure through double dispatch.

**When to use:**
*   An object structure contains many classes of objects with differing interfaces, and you want to perform operations on these objects that depend on their concrete classes.
*   You need to perform many distinct and unrelated operations on objects in an object structure, and you want to avoid polluting their classes with these operations.
*   The classes defining the object structure rarely change, but you often want to define new operations over the structure.

**Why to use:**
*   **Separation of Concerns:** Separates algorithms from the objects they operate on.
*   **Extensibility:** Easy to add new operations (new visitors) without modifying the element classes.
*   **Centralized Operations:** Consolidates related operations into a single `Visitor` class.
*   **Type Safety:** Allows for type-safe operations on different element types.

**How to use:**
1.  **Visitor Interface:** Declare a `visit()` operation for each class of `ConcreteElement` in the object structure.
2.  **Concrete Visitors:** Implement each `visit()` operation defined in the `Visitor` interface. Each operation implements a fragment of the algorithm for the corresponding class of object.
3.  **Element Interface:** Declare an `accept()` operation that takes a `Visitor` as an argument.
4.  **Concrete Elements:** Implement the `accept()` operation, which typically calls the corresponding `visit()` operation on the passed `Visitor`.
5.  **Object Structure:** A collection or composite that can enumerate its elements and provides a way for the client to initiate the traversal with a `Visitor`.

**Example:**
Consider a graphical editor with different shapes (Circle, Rectangle) and operations like drawing, exporting to XML, or calculating area.

```java
// Visitor Interface
interface ShapeVisitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
}

// Element Interface
interface Shape {
    void accept(ShapeVisitor visitor);
}

// Concrete Element 1
class Circle implements Shape {
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}

// Concrete Element 2
class Rectangle implements Shape {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}

// Concrete Visitor 1 (e.g., for drawing)
class DrawVisitor implements ShapeVisitor {
    @Override
    public void visit(Circle circle) {
        System.out.println("Drawing a circle with radius: " + circle.getRadius());
    }

    @Override
    public void visit(Rectangle rectangle) {
        System.out.println("Drawing a rectangle with width: " + rectangle.getWidth() + " and height: " + rectangle.getHeight());
    }
}

// Concrete Visitor 2 (e.g., for exporting to XML)
class XmlExportVisitor implements ShapeVisitor {
    @Override
    public void visit(Circle circle) {
        System.out.println("<circle radius=\"" + circle.getRadius() + "\"/>");
    }

    @Override
    public void visit(Rectangle rectangle) {
        System.out.println("<rectangle width=\"" + rectangle.getWidth() + "\" height=\"" + rectangle.getHeight() + "\"/>");
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Circle(10));
        shapes.add(new Rectangle(20, 30));

        ShapeVisitor drawVisitor = new DrawVisitor();
        ShapeVisitor xmlExportVisitor = new XmlExportVisitor();

        System.out.println("Drawing shapes:");
        for (Shape shape : shapes) {
            shape.accept(drawVisitor);
        }

        System.out.println("\nExporting shapes to XML:");
        for (Shape shape : shapes) {
            shape.accept(xmlExportVisitor);
        }
    }
}
```