# Bridge Design Pattern

**Purpose:** Decouple an abstraction from its implementation so that the two can vary independently. It allows you to change the implementation of a class without changing the client code that uses the abstraction.

**Demand for this pattern:**
When you have an abstraction and its implementation tightly coupled, changing one often requires changing the other. This leads to a rigid system where extending either the abstraction or the implementation becomes difficult, resulting in a proliferation of classes (e.g., `RedCircle`, `BlueCircle`, `RedSquare`, `BlueSquare` if you have shapes and colors).

**Without it:**
Without the Bridge pattern, you would typically face:
1.  **Class Explosion:** If you have multiple abstractions and multiple implementations, you would need to create a concrete class for every combination, leading to a large number of classes (e.g., `Shape` and `Color` would result in `ShapeColor` classes).
2.  **Tight Coupling:** The abstraction would be directly dependent on its implementation, making it hard to change or extend either without affecting the other.
3.  **Reduced Flexibility:** It would be difficult to switch implementations at runtime or introduce new implementations without modifying the abstraction.

**How it solved the problem:**
The Bridge pattern separates the abstraction from its implementation by placing them in two separate class hierarchies. The abstraction contains a reference to an object from the implementation hierarchy. Clients interact with the abstraction, which then delegates the actual work to the implementation object. This allows both the abstraction and the implementation to evolve independently, promoting flexibility and reducing class explosion.

**Is it an exclusive pattern for this kind of problem?**
For decoupling an abstraction from its implementation, Bridge is the primary pattern. While other patterns like Adapter (for interface compatibility) or Strategy (for interchangeable algorithms) might seem related, they address different core problems. Bridge specifically focuses on managing two orthogonal dimensions of variation (abstraction and implementation) independently.

**When to use:**
*   You want to avoid a permanent binding between an abstraction and its implementation.
*   Both the abstractions and their implementations should be extensible by subclassing.
*   Changes in the implementation of an abstraction should not impact clients.
*   You have a proliferation of classes resulting from a combination of an abstraction and its implementation (e.g., `WindowIcon`, `MacIcon`, `LinuxIcon`).

**Why to use:**
*   **Decoupling:** Decouples the abstraction from its implementation, allowing them to vary independently.
*   **Extensibility:** Both abstraction and implementation can be extended independently without affecting each other.
*   **Reduced Class Explosion:** Avoids the creation of a large number of classes when dealing with multiple variations of both abstraction and implementation.
*   **Runtime Flexibility:** Allows the implementation to be changed dynamically at runtime.

**How to use:**
1.  **Abstraction:** Define the abstraction's interface. It maintains a reference to an `Implementor` object.
2.  **Refined Abstraction:** Extend the `Abstraction` interface to provide more specific functionality.
3.  **Implementor Interface:** Define the interface for implementation classes. This interface doesn't have to correspond exactly to the `Abstraction`'s interface; it provides primitive operations.
4.  **Concrete Implementors:** Implement the `Implementor` interface, providing concrete implementations for the primitive operations.

**Examples to Remember:**

1.  **Remote Control and Devices:** A `RemoteControl` (Abstraction) can control various `Device`s (Implementor) like `TV`, `Radio`, `DVDPlayer`. You can have `BasicRemoteControl`, `AdvancedRemoteControl` (Refined Abstractions) and `SonyTV`, `LGTV`, `BoseRadio` (Concrete Implementors). The remote control doesn't need to know the specifics of each device.

2.  **Shapes and Colors:** A `Shape` (Abstraction) can be `Circle`, `Square` (Refined Abstractions). A `Color` (Implementor) can be `Red`, `Blue` (Concrete Implementors). A `Circle` can be `Red` or `Blue` without creating `RedCircle` and `BlueCircle` classes.

3.  **Operating System Windows:** A `Window` (Abstraction) can be `IconWindow`, `DialogWindow` (Refined Abstractions). An `OSWindowImplementor` (Implementor) can be `WindowsOS`, `MacOS`, `LinuxOS` (Concrete Implementors). The `Window` abstraction works independently of the underlying operating system's windowing system.

```java
// Implementor Interface
interface DrawAPI {
    void drawCircle(int radius, int x, int y);
}

// Concrete Implementor 1
class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: " + radius + ", x: " + x + ", " + y + "]");
    }
}

// Concrete Implementor 2
class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: " + radius + ", x: " + x + ", " + y + "]");
    }
}

// Abstraction
abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}

// Refined Abstraction
class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
```