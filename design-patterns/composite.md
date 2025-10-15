# Composite Design Pattern

**Purpose:** Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly.

**Demand for this pattern:**
Many systems deal with hierarchical data or structures where individual objects and groups of objects need to be treated in the same way. For example, file systems (files and directories), graphical user interfaces (widgets and panels), or organizational charts (employees and departments).

**Without it:**
Without the Composite pattern, clients would have to differentiate between individual objects (leaves) and compositions of objects (composites). This would lead to complex conditional logic (`if-else` or `switch` statements) to handle each type, making the client code tightly coupled to the structure and difficult to extend when new types of leaves or composites are introduced.

**How it solved the problem:**
The Composite pattern introduces a common interface (Component) that both individual objects (Leaf) and groups of objects (Composite) implement. This allows clients to interact with both leaves and composites uniformly through the Component interface, simplifying client code and making it more flexible to changes in the object structure.

**Is it an exclusive pattern for this kind of problem?**
While Composite is the primary pattern for representing part-whole hierarchies and treating them uniformly, other patterns might be used in conjunction or for related problems. For instance, Iterator can traverse composite structures, and Decorator can add responsibilities to components. However, for the specific problem of uniform treatment of individual and composite objects in a hierarchy, Composite is the most direct and suitable solution.

**When to use:**
*   You want to represent part-whole hierarchies of objects.
*   Clients should be able to ignore the difference between compositions of objects and individual objects. Clients will treat all objects in the composite structure uniformly.
*   The structure can be recursively defined (a composite can contain other composites).

**Why to use:**
*   **Uniformity:** Simplifies client code by treating individual objects and compositions uniformly.
*   **Flexibility:** Makes it easy to add new types of components (leaves or composites) without changing client code.
*   **Hierarchy Representation:** Provides a clear and natural way to model tree-like structures.

**How to use:**
1.  **Component:** Declare an interface (or abstract class) for objects in the composition. This interface should define methods common to both leaves and composites, including operations for accessing and managing child components (e.g., `add`, `remove`, `getChild`).
2.  **Leaf:** Implement the Component interface for primitive objects in the composition. A Leaf has no children and thus, child-management operations might throw an exception or do nothing.
3.  **Composite:** Implement the Component interface for objects that have children. A Composite stores child components and implements the child-related operations from the Component interface. It typically delegates operations to its children.

**Examples to Remember:**

1.  **File System:** Files are leaves, and directories are composites. Both files and directories can be listed, moved, or deleted. A directory can contain files and other directories.

2.  **Graphical Shapes:** Individual shapes (Circle, Square) are leaves, and a Group of Shapes is a composite. Both can be drawn, moved, or resized. A group can contain individual shapes and other groups.

3.  **Organizational Chart:** Individual employees are leaves, and departments are composites. Both can have their salaries calculated or be assigned tasks. A department can contain employees and sub-departments.

```java
// Component
interface Graphic {
    void draw();
    // Methods for managing children (optional for Leaf, implemented by Composite)
    void add(Graphic graphic);
    void remove(Graphic graphic);
}

// Leaf
class Circle implements Graphic {
    private String color;

    public Circle(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle.");
    }

    // Leaf operations for child management (can be empty or throw UnsupportedOperationException)
    @Override
    public void add(Graphic graphic) { /* Not supported */ }
    @Override
    public void remove(Graphic graphic) { /* Not supported */ }
}

// Leaf
class Square implements Graphic {
    private String color;

    public Square(String color) {
        this.color = color;
    }

    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " square.");
    }

    // Leaf operations for child management
    @Override
    public void add(Graphic graphic) { /* Not supported */ }
    @Override
    public void remove(Graphic graphic) { /* Not supported */ }
}

// Composite
import java.util.ArrayList;
import java.util.List;

class GraphicGroup implements Graphic {
    private List<Graphic> graphics = new ArrayList<>();
    private String name;

    public GraphicGroup(String name) {
        this.name = name;
    }

    @Override
    public void add(Graphic graphic) {
        graphics.add(graphic);
    }

    @Override
    public void remove(Graphic graphic) {
        graphics.remove(graphic);
    }

    @Override
    public void draw() {
        System.out.println("Drawing group: " + name);
        for (Graphic graphic : graphics) {
            graphic.draw();
        }
    }
}

// Client
public class Main {
    public static void main(String[] args) {
        Circle redCircle = new Circle("red");
        Square blueSquare = new Square("blue");
        Circle greenCircle = new Circle("green");

        GraphicGroup group1 = new GraphicGroup("Primary Group");
        group1.add(redCircle);
        group1.add(blueSquare);

        GraphicGroup group2 = new GraphicGroup("Secondary Group");
        group2.add(greenCircle);

        GraphicGroup mainGroup = new GraphicGroup("Main Drawing");
        mainGroup.add(group1);
        mainGroup.add(group2);

        mainGroup.draw();
    }
}
```