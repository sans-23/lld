# Command Design Pattern

**Purpose:** Turns a request into a stand-alone object that contains all information about the request. This transformation lets you parameterize methods with different requests, delay or queue a request's execution, and support undoable operations.

**Demand for this pattern:**
When you need to decouple the sender of a request from its receiver, or when you need to support undoable operations, logging of requests, or queuing of requests. This is common in GUI applications (menu items, buttons), macro recording, or transaction systems.

**Without it:**
Without the Command pattern, the sender of a request would be directly coupled to the receiver and the action it performs. This would lead to:
1.  **Tight Coupling:** The sender needs to know the specific receiver and the method to call, making it hard to change or extend.
2.  **No Undo/Redo:** Implementing undo/redo functionality would be complex as there's no easy way to store and revert past actions.
3.  **Limited Flexibility:** Difficult to queue requests, log them, or parameterize clients with different actions.

**How it solved the problem:**
The Command pattern encapsulates a request as an object. This object contains all the information needed to perform an action, including the receiver of the request and the method to call. The client creates a `Command` object and passes it to an `Invoker`, which then executes the command. This decouples the invoker from the receiver and the action, allowing for flexible handling of requests.

**Is it an exclusive pattern for this kind of problem?**
For encapsulating requests as objects, Command is the primary pattern. It is often used in conjunction with other patterns like Memento (for undo/redo) or Composite (for macro commands). It differs from Strategy by focusing on encapsulating an action, whereas Strategy focuses on interchangeable algorithms.

**When to use:**
*   You want to parameterize objects by an action to perform.
*   You want to specify, queue, and execute requests at different times.
*   You want to support undoable operations.
*   You want to log changes for a persistent storage update.
*   You want to structure a system around high-level operations built on primitive operations.

**Why to use:**
*   **Decoupling:** Decouples the invoker from the receiver and the action.
*   **Undo/Redo:** Easily supports undo and redo functionality by storing command history.
*   **Queuing/Logging:** Allows requests to be queued, logged, or executed at different times.
*   **Flexibility:** Enables parameterization of clients with different requests.

**How to use:**
1.  **Command Interface:** Declare an interface for executing an operation (e.g., `execute()`, `undo()`).
2.  **Concrete Commands:** Implement the `Command` interface. Each concrete command binds a receiver to an action.
3.  **Receiver:** The object that performs the actual work when the command is executed.
4.  **Invoker:** Asks the command to carry out the request. It holds a `Command` object.
5.  **Client:** Creates a `ConcreteCommand` object and sets its receiver.

**Example:**
Consider a simple remote control for a light.

```java
// Receiver
class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }

    public void turnOff() {
        System.out.println("Light is OFF");
    }
}

// Command Interface
interface Command {
    void execute();
    void undo();
}

// Concrete Command for turning on the light
class TurnOnLightCommand implements Command {
    private Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

// Concrete Command for turning off the light
class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}

// Invoker
class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }

    public void pressUndo() {
        command.undo();
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        RemoteControl remote = new RemoteControl();

        // Turn on the light
        Command turnOn = new TurnOnLightCommand(livingRoomLight);
        remote.setCommand(turnOn);
        remote.pressButton();

        // Turn off the light
        Command turnOff = new TurnOffLightCommand(livingRoomLight);
        remote.setCommand(turnOff);
        remote.pressButton();

        // Undo the last action (turn off -> turn on)
        System.out.println("\nPressing undo:");
        remote.pressUndo();
    }
}
```