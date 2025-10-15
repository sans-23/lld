# Proxy Design Pattern

**Purpose:** Provides a surrogate or placeholder for another object to control access to it. It acts as an intermediary, allowing you to add extra logic (e.g., lazy initialization, access control, logging) before or after forwarding the request to the real object.

**Demand for this pattern:**
There are situations where direct access to an object is not always desirable or possible. This could be due to:
1.  **Resource Intensive Objects:** The real object is expensive to create or load (e.g., a large image, a remote service).
2.  **Access Control:** You need to restrict access to the real object based on certain conditions (e.g., user permissions).
3.  **Remote Objects:** The real object resides in a different address space or on a remote server.
4.  **Logging/Monitoring:** You want to log interactions with the real object without modifying its core logic.

**Without it:**
Without the Proxy pattern, you would either:
1.  **Directly instantiate expensive objects:** Leading to performance issues or unnecessary resource consumption.
2.  **Embed access control/logging logic directly:** This would clutter the real object's code, violate the Single Responsibility Principle, and make it harder to maintain.
3.  **Handle remote communication manually:** Making client code complex and tightly coupled to network details.

**How it solved the problem:**
The Proxy pattern introduces a `Proxy` object that has the same interface as the `RealSubject` (the actual object). The client interacts with the `Proxy` as if it were the `RealSubject`. The `Proxy` then controls access to the `RealSubject`, performing additional actions (like lazy loading, security checks, or remote communication) before or after delegating the request to the `RealSubject`.

**Is it an exclusive pattern for this kind of problem?**
For controlling access to an object and adding supplementary logic, Proxy is the primary pattern. While patterns like Decorator also wrap objects, Decorator focuses on adding responsibilities dynamically, whereas Proxy focuses on controlling access and managing the lifecycle of the real object. Facade simplifies a subsystem, but Proxy specifically acts as a stand-in for a single object.

**When to use:**
*   **Lazy Initialization (Virtual Proxy):** When an object is expensive to create, and you only need it when it's actually accessed.
*   **Access Control (Protection Proxy):** When you want to control who can access a particular object and what operations they can perform.
*   **Remote Proxy:** When an object resides in a different address space (e.g., on a remote server), and you need to provide a local representation.
*   **Logging/Caching (Smart Reference):** When you need to add logging, caching, or other management tasks around an object's lifecycle or method calls.

**Why to use:**
*   **Performance:** Improves performance by delaying object creation or providing caching.
*   **Security:** Enforces access control and permissions.
*   **Decoupling:** Decouples the client from the complexities of the real object's creation, location, or additional concerns.
*   **Maintainability:** Keeps the `RealSubject` focused on its core responsibility, adhering to the Single Responsibility Principle.

**How to use:**
1.  **Subject Interface:** Define an interface that both the `RealSubject` and the `Proxy` will implement. This ensures that the `Proxy` can be used interchangeably with the `RealSubject`.
2.  **RealSubject:** The actual object that the `Proxy` will represent and to which it will delegate requests.
3.  **Proxy:** Implement the `Subject` interface. It maintains a reference to the `RealSubject` and controls access to it. The `Proxy` can perform additional operations before or after forwarding requests to the `RealSubject`.

**Examples to Remember:**

1.  **Image Loading (Virtual Proxy):** When displaying a list of images, you might only load the full image data when a user actually clicks on an image. A `LazyImageProxy` can hold the image file path and only load the `RealImage` when its `display()` method is called.

2.  **Bank Account Access (Protection Proxy):** A `BankAccountProxy` can check user credentials before allowing access to sensitive operations like `withdraw()` or `deposit()` on a `RealBankAccount` object.

3.  **Remote Service Call (Remote Proxy):** When calling a method on a remote object, a `RemoteServiceProxy` handles the network communication, serialization, and deserialization, making the remote call appear as a local one to the client.

```java
// Subject Interface
interface Image {
    void display();
}

// Real Subject
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadImageFromServer(); // Simulate expensive loading
    }

    private void loadImageFromServer() {
        System.out.println("Loading " + filename + " from server...");
        try {
            Thread.sleep(2000); // Simulate delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        System.out.println("Displaying " + filename);
    }
}

// Proxy
class ProxyImage implements Image {
    private String filename;
    private RealImage realImage; // Reference to the real subject

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            // Lazy initialization: create RealImage only when needed
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        // Image is not loaded until display() is called
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        System.out.println("First call to display image1:");
        image1.display(); // Loads and displays photo1.jpg

        System.out.println("\nSecond call to display image1:");
        image1.display(); // Displays photo1.jpg (already loaded)

        System.out.println("\nFirst call to display image2:");
        image2.display(); // Loads and displays photo2.jpg
    }
}
```