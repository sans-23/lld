# Facade Design Pattern

**Purpose:** Provides a simplified interface to a complex subsystem. It hides the complexities of the system and presents a simpler, unified interface to the client.

**Demand for this pattern:**
When a system consists of many classes and objects that interact in complex ways, clients often need to perform operations that involve multiple components of this subsystem. Directly interacting with all these components can be overwhelming, error-prone, and lead to tight coupling between the client and the subsystem.

**Without it:**
Without the Facade pattern, client code would be directly exposed to the intricate details of the subsystem. This would mean:
1.  **Increased Complexity:** Clients would need to understand and manage numerous objects and their interactions within the subsystem.
2.  **Tight Coupling:** Changes in the subsystem's internal structure would directly impact client code, making maintenance difficult.
3.  **Reduced Readability:** Client code would be cluttered with calls to various subsystem objects, making it harder to understand the overall flow.

**How it solved the problem:**
The Facade pattern introduces a single, high-level interface (the Facade) that acts as a gateway to the subsystem. The Facade delegates client requests to the appropriate objects within the subsystem, handling the complex orchestration internally. This simplifies the client's interaction, reduces coupling, and improves the readability and maintainability of the code.

**Is it an exclusive pattern for this kind of problem?**
For simplifying access to a complex subsystem, Facade is the primary pattern. While other patterns like Adapter (for interface compatibility) or Mediator (for centralizing communication between objects) might involve some level of simplification, they address different core problems. Facade specifically aims to provide a simplified, unified entry point to a complex set of classes.

**When to use:**
*   You want to provide a simple interface to a complex subsystem.
*   You want to decouple a client from a subsystem.
*   You want to layer your subsystem, and a facade can be an entry point to each layer.

**Why to use:**
*   **Simplification:** Reduces the complexity seen by the client.
*   **Decoupling:** Decreases the coupling between clients and the subsystem, making the subsystem more independent and easier to change.
*   **Testability:** Can make the subsystem easier to test by providing a clear entry point.
*   **Readability:** Improves the overall structure and readability of the code.

**How to use:**
1.  **Subsystem Classes:** Identify the complex classes and objects that form the subsystem.
2.  **Facade Class:** Create a Facade class that provides a simplified, high-level interface to the subsystem.
3.  **Delegate Requests:** The Facade class intercepts client requests and delegates them to the appropriate objects within the subsystem, managing their interactions.

**Examples to Remember:**

1.  **Home Theater System:** A `HomeTheaterFacade` can provide methods like `watchMovie(String movie)` which internally turns on the TV, DVD player, amplifier, sets volume, and dims lights. Without the facade, you'd have to interact with each device individually.

2.  **Compiler:** A `CompilerFacade` can provide a `compile(File sourceCode)` method, which internally handles lexical analysis, parsing, code generation, and optimization, all of which are complex sub-processes.

3.  **Online Order Processing:** An `OrderFacade` can have a `placeOrder(Product product, int quantity)` method that internally interacts with `InventoryService`, `PaymentService`, and `ShippingService`.

```java
// Subsystem classes
class Amplifier {
    public void on() { System.out.println("Amplifier on"); }
    public void setDvd(DvdPlayer dvd) { System.out.println("Amplifier setting DVD player"); }
    public void setVolume(int volume) { System.out.println("Amplifier volume set to " + volume); }
    public void off() { System.out.println("Amplifier off"); }
}

class DvdPlayer {
    public void on() { System.out.println("DVD Player on"); }
    public void play(String movie) { System.out.println("DVD Player playing \"" + movie + "\""); }
    public void stop() { System.out.println("DVD Player stop"); }
    public void off() { System.out.println("DVD Player off"); }
}

class Projector {
    public void on() { System.out.println("Projector on"); }
    public void wideScreenMode() { System.out.println("Projector in widescreen mode"); }
    public void off() { System.out.println("Projector off"); }
}

// Facade class
class HomeTheaterFacade {
    Amplifier amp;
    DvdPlayer dvd;
    Projector projector;

    public HomeTheaterFacade(Amplifier amp, DvdPlayer dvd, Projector projector) {
        this.amp = amp;
        this.dvd = dvd;
        this.projector = projector;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        projector.on();
        projector.wideScreenMode();
        amp.on();
        amp.setDvd(dvd);
        amp.setVolume(5);
        dvd.on();
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting down home theater...");
        dvd.stop();
        dvd.off();
        amp.off();
        projector.off();
    }
}

// Client code
public class Main {
    public static void main(String[] args) {
        Amplifier amp = new Amplifier();
        DvdPlayer dvd = new DvdPlayer();
        Projector projector = new Projector();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, dvd, projector);

        homeTheater.watchMovie("The Matrix");
        System.out.println("\n");
        homeTheater.endMovie();
    }
}
```