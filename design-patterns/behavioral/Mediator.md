# Mediator Design Pattern

**Purpose:** Reduces chaotic dependencies between objects. The pattern restricts direct communications between the objects and forces them to collaborate only via a mediator object.

**Demand for this pattern:**
When a set of objects interact in complex ways, and these interactions become difficult to manage. Direct communication between many objects leads to tight coupling, making the system hard to understand, maintain, and extend. Changes in one object might require changes in many others.

**Without it:**
Without the Mediator pattern, you would typically have:
1.  **Spaghetti Code:** Objects directly communicating with each other, leading to a complex web of dependencies where every object knows about many others.
2.  **Tight Coupling:** Changes in one object's interaction logic would require modifying all other objects that communicate with it.
3.  **Reduced Reusability:** Individual objects would be hard to reuse in different contexts due to their strong dependencies.

**How it solved the problem:**
The Mediator pattern introduces a `Mediator` object that centralizes communication between a group of objects (colleagues). Instead of colleagues communicating directly, they communicate only with the mediator. The mediator then handles the routing of messages and coordinates the actions of the colleagues. This reduces direct dependencies between colleagues, promoting loose coupling and making the system more manageable.

**Is it an exclusive pattern for this kind of problem?**
For centralizing communication and reducing direct dependencies between objects, Mediator is the primary pattern. It is often used in conjunction with Observer (where the mediator acts as a subject and colleagues are observers) or Command (where the mediator executes commands). It differs from Facade by focusing on managing interactions between *peers*, whereas Facade simplifies access to a *subsystem*.

**When to use:**
*   A set of objects communicate in complex but well-defined ways.
*   You want to avoid direct coupling between objects.
*   You want to centralize the control of complex interactions.
*   You want to promote loose coupling between objects.

**Why to use:**
*   **Loose Coupling:** Reduces direct dependencies between colleagues.
*   **Centralized Control:** Simplifies the management of complex interactions by centralizing them in the mediator.
*   **Improved Reusability:** Colleagues become more independent and easier to reuse.
*   **Simplified Object Logic:** Each colleague's logic is simpler as it only needs to communicate with the mediator.

**How to use:**
1.  **Mediator Interface:** Declare an interface for communicating with `Colleague` objects.
2.  **Concrete Mediator:** Implements the `Mediator` interface and coordinates communication between `Colleague` objects. It knows and maintains its colleagues.
3.  **Colleague Interface (Optional):** Declare an interface for `Colleague` objects.
4.  **Concrete Colleagues:** Each `Concrete Colleague` class communicates with its `Mediator` whenever it would otherwise have communicated with another colleague.

**Example:**
Consider a chat room where users (colleagues) communicate through a chat mediator.

```java
import java.util.ArrayList;
import java.util.List;

// Mediator Interface
interface ChatMediator {
    void sendMessage(String message, User user);
    void addUser(User user);
}

// Concrete Mediator
class ChatRoom implements ChatMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String message, User sender) {
        for (User user : users) {
            // Don't send message back to the sender
            if (user != sender) {
                user.receive(message);
            }
        }
    }
}

// Colleague Abstract Class
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public abstract void send(String message);
    public abstract void receive(String message);
}

// Concrete Colleague
class ChatUser extends User {
    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println(name + " sends: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message) {
        System.out.println(name + " receives: " + message);
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        User user1 = new ChatUser(chatRoom, "Alice");
        User user2 = new ChatUser(chatRoom, "Bob");
        User user3 = new ChatUser(chatRoom, "Charlie");

        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        user1.send("Hi everyone!");
        user2.send("Hello Alice!");
    }
}
```