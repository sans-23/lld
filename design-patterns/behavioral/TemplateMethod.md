# Template Method Design Pattern

**Purpose:** Defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure.

**Demand for this pattern:**
When you have an algorithm with a fixed overall structure, but some steps of the algorithm can vary. You want to implement the invariant parts of an algorithm once and let subclasses implement the behavior that can vary. This is common in frameworks, build processes, or data processing pipelines.

**Without it:**
Without the Template Method pattern, you would typically:
1.  **Code Duplication:** Duplicate the common parts of the algorithm across multiple subclasses, leading to maintenance issues.
2.  **Tight Coupling:** Subclasses might have to reimplement the entire algorithm if even a small part changes, making the system rigid.
3.  **Inconsistent Algorithms:** Different implementations of the same algorithm might diverge over time.

**How it solved the problem:**
The Template Method pattern defines a `templateMethod()` in an abstract superclass. This method outlines the overall algorithm, calling abstract methods (primitive operations) for the steps that can vary. Subclasses then implement these abstract methods to provide their specific variations of the steps, without altering the overall structure of the algorithm. This ensures consistency in the algorithm's structure while allowing flexibility in its individual steps.

**Is it an exclusive pattern for this kind of problem?**
For defining the skeleton of an algorithm and allowing subclasses to customize specific steps, Template Method is the primary pattern. It is often confused with Strategy, but Strategy focuses on encapsulating *entire algorithms* and making them interchangeable, whereas Template Method focuses on defining the *structure of an algorithm* and allowing *parts of it* to be customized. It differs from other patterns by specifically addressing the problem of algorithm structure and customization through inheritance.

**When to use:**
*   To implement the invariant parts of an algorithm once and let subclasses implement the behavior that can vary.
*   When you want to control the order of operations in an algorithm, but allow specific steps to be customized.
*   To avoid code duplication for common parts of an algorithm.

**Why to use:**
*   **Code Reusability:** Reuses the common parts of an algorithm in the superclass.
*   **Flexibility:** Allows subclasses to customize specific steps without changing the overall algorithm structure.
*   **Inversion of Control:** The superclass controls the flow of the algorithm, while subclasses provide the details.
*   **Consistency:** Ensures that the overall structure of the algorithm remains consistent.

**How to use:**
1.  **Abstract Class:** Define an abstract class that contains the `templateMethod()`. This method defines the skeleton of the algorithm.
2.  **Primitive Operations:** The `templateMethod()` calls abstract or concrete primitive operations. Abstract primitive operations are implemented by subclasses, while concrete ones are implemented in the abstract class.
3.  **Concrete Classes:** Implement the abstract primitive operations to provide specific variations of the algorithm steps.

**Example:**
Consider a process for building a house, where the overall steps are fixed, but the materials or specific construction methods can vary.

```java
// Abstract Class (defines the template method)
abstract class HouseBuilder {
    // The template method defines the sequence of steps
    public final void buildHouse() {
        buildFoundation();
        buildWalls();
        buildRoof();
        installWindows();
        installDoors();
        paintHouse();
        decorateHouse();
    }

    // Abstract methods to be implemented by subclasses
    public abstract void buildFoundation();
    public abstract void buildWalls();
    public abstract void buildRoof();
    public abstract void installWindows();
    public abstract void installDoors();
    public abstract void paintHouse();
    public abstract void decorateHouse();
}

// Concrete Class 1
class WoodenHouseBuilder extends HouseBuilder {
    @Override
    public void buildFoundation() {
        System.out.println("Building wooden foundation.");
    }

    @Override
    public void buildWalls() {
        System.out.println("Building wooden walls.");
    }

    @Override
    public void buildRoof() {
        System.out.println("Building wooden roof.");
    }

    @Override
    public void installWindows() {
        System.out.println("Installing wooden windows.");
    }

    @Override
    public void installDoors() {
        System.out.println("Installing wooden doors.");
    }

    @Override
    public void paintHouse() {
        System.out.println("Painting wooden house with brown color.");
    }

    @Override
    public void decorateHouse() {
        System.out.println("Decorating wooden house with rustic style.");
    }
}

// Concrete Class 2
class BrickHouseBuilder extends HouseBuilder {
    @Override
    public void buildFoundation() {
        System.out.println("Building concrete foundation.");
    }

    @Override
    public void buildWalls() {
        System.out.println("Building brick walls.");
    }

    @Override
    public void buildRoof() {
        System.out.println("Building tiled roof.");
    }

    @Override
    public void installWindows() {
        System.out.println("Installing UPVC windows.");
    }

    @Override
    public void installDoors() {
        System.out.println("Installing steel doors.");
    }

    @Override
    public void paintHouse() {
        System.out.println("Painting brick house with white color.");
    }

    @Override
    public void decorateHouse() {
        System.out.println("Decorating brick house with modern style.");
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        System.out.println("Building a Wooden House:");
        HouseBuilder woodenHouse = new WoodenHouseBuilder();
        woodenHouse.buildHouse();

        System.out.println("\nBuilding a Brick House:");
        HouseBuilder brickHouse = new BrickHouseBuilder();
        brickHouse.buildHouse();
    }
}
```