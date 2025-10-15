# Flyweight Design Pattern

**Purpose:** Lets you fit more objects into the available RAM by sharing common parts of state between multiple objects instead of keeping all of the data in each object.

**Demand for this pattern:**
When an application needs to create a large number of objects, and these objects consume a significant amount of memory. If many objects share common intrinsic (unchanging) state, but have varying extrinsic (context-dependent) state, creating a separate object for each instance becomes inefficient. This is common in scenarios like graphical editors (characters, lines), game development (trees, particles), or document processing.

**Without it:**
Without the Flyweight pattern, you would typically:
1.  **High Memory Consumption:** Create a separate object for every instance, even if many objects share identical data, leading to excessive memory usage.
2.  **Performance Issues:** Increased memory usage can lead to more garbage collection cycles and slower application performance.

**How it solved the problem:**
The Flyweight pattern separates an object's state into two parts: intrinsic (shared, context-independent) and extrinsic (unique, context-dependent). It then shares the intrinsic state among multiple objects by storing it in a single Flyweight object. The extrinsic state is passed to the Flyweight methods by the client. This drastically reduces the number of objects created and thus memory consumption.

**Is it an exclusive pattern for this kind of problem?**
For optimizing memory usage by sharing common state among a large number of objects, Flyweight is the primary pattern. It is often used in conjunction with Factory Method or Abstract Factory to manage the creation and retrieval of flyweight objects. It differs from other patterns by focusing specifically on memory optimization through state sharing.

**When to use:**
*   An application uses a large number of objects.
*   Storage costs are high because of the sheer quantity of objects.
*   Most of the object state can be made extrinsic.
*   Many groups of objects can be replaced by a few shared flyweight objects once extrinsic state is removed.

**Why to use:**
*   **Memory Optimization:** Significantly reduces memory consumption by sharing intrinsic state.
*   **Performance Improvement:** Less memory usage can lead to faster application performance.
*   **Scalability:** Allows handling a larger number of objects than would otherwise be possible.

**How to use:**
1.  **Flyweight Interface:** Declare an interface through which flyweights can receive and act on extrinsic state.
2.  **Concrete Flyweight:** Implement the `Flyweight` interface and store intrinsic state. It must be shareable.
3.  **Flyweight Factory:** Creates and manages flyweight objects. It ensures that flyweights are shared correctly.
4.  **Client:** Maintains references to flyweights and computes or stores the extrinsic state of the flyweights.

**Example:**
Consider a text editor where each character in a document is an object. Instead of storing font, color, and size for every character object, these intrinsic properties can be shared.

```java
import java.util.HashMap;
import java.util.Map;

// Flyweight Interface
interface Character {
    void display(int x, int y); // Extrinsic state (position)
}

// Concrete Flyweight
class ConcreteCharacter implements Character {
    private char character;
    private String font;
    private String color;
    private int size; // Intrinsic state (shared)

    public ConcreteCharacter(char character, String font, String color, int size) {
        this.character = character;
        this.font = font;
        this.color = color;
        this.size = size;
        System.out.println("Creating Flyweight Character: " + character + ", " + font + ", " + color + ", " + size);
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Displaying character '" + character + "' at (" + x + "," + y + ") with font: " + font + ", color: " + color + ", size: " + size);
    }
}

// Flyweight Factory
class CharacterFactory {
    private Map<String, Character> characters = new HashMap<>();

    public Character getCharacter(char character, String font, String color, int size) {
        String key = character + "_" + font + "_" + color + "_" + size;
        if (!characters.containsKey(key)) {
            characters.put(key, new ConcreteCharacter(character, font, color, size));
        }
        return characters.get(key);
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        CharacterFactory factory = new CharacterFactory();

        // Characters with intrinsic state (font, color, size) are shared
        Character c1 = factory.getCharacter('H', "Arial", "Black", 12);
        c1.display(10, 20);

        Character c2 = factory.getCharacter('e', "Arial", "Black", 12);
        c2.display(20, 20);

        Character c3 = factory.getCharacter('l', "Arial", "Black", 12);
        c3.display(30, 20);

        Character c4 = factory.getCharacter('l', "Arial", "Black", 12);
        c4.display(40, 20);

        Character c5 = factory.getCharacter('o', "Arial", "Black", 12);
        c5.display(50, 20);

        // A different character with different intrinsic state
        Character c6 = factory.getCharacter('W', "Times New Roman", "Red", 14);
        c6.display(10, 40);

        // The 'l' character is reused, no new ConcreteCharacter object is created for c4
        System.out.println("\nAre c3 and c4 the same object? " + (c3 == c4));
    }
}
```