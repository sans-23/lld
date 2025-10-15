# Memento Design Pattern

**Purpose:** Lets you save and restore the previous state of an object without revealing the details of its implementation.

**Demand for this pattern:**
When you need to capture and externalize an object's internal state so that the object can be restored to this state later. This is crucial for implementing features like undo/redo, checkpoints in games, or transaction rollback, where you need to revert an object to a previous valid state without breaking its encapsulation.

**Without it:**
Without the Memento pattern, you would typically:
1.  **Expose Internal State:** Break the object's encapsulation by exposing its internal state to save and restore it, leading to tight coupling and potential data corruption.
2.  **Complex State Management:** Implement complex, ad-hoc mechanisms for saving and restoring state, which would be difficult to maintain and extend.

**How it solved the problem:**
The Memento pattern introduces three roles:
*   **Originator:** The object whose state needs to be saved. It creates a memento containing a snapshot of its current state and uses the memento to restore its previous state.
*   **Memento:** A simple object that stores the internal state of the Originator. It has a wide interface for the Originator (to access all state) and a narrow interface for the Caretaker (to prevent tampering).
*   **Caretaker:** Responsible for keeping the memento. It never operates on or examines the contents of the memento.

This pattern allows the Originator to save and restore its state without the Caretaker knowing the internal structure of the Originator, thus preserving encapsulation.

**Is it an exclusive pattern for this kind of problem?**
For saving and restoring an object's internal state without violating encapsulation, Memento is the primary pattern. It is often used in conjunction with Command (for undoable commands) or Iterator (to capture the state of an iteration). It differs from other patterns by focusing specifically on state capture and restoration.

**When to use:**
*   You need to save a snapshot of an object's state to be able to restore it later.
*   Directly exposing the object's internal state would violate its encapsulation.

**Why to use:**
*   **Encapsulation:** Preserves the encapsulation of the Originator by not exposing its internal state to the Caretaker.
*   **Undo/Redo:** Provides a robust mechanism for implementing undo/redo functionality.
*   **State Management:** Simplifies the process of saving and restoring an object's state.

**How to use:**
1.  **Originator:** The object that has an internal state to be saved. It creates a `Memento` and uses it to restore its state.
2.  **Memento:** An object that stores the internal state of the `Originator`. It should have a wide interface for the `Originator` and a narrow interface for the `Caretaker`.
3.  **Caretaker:** Responsible for storing and retrieving `Memento` objects. It never operates on or examines the contents of a `Memento`.

**Example:**
Consider a text editor with undo/redo functionality.

```java
import java.util.Stack;

// Memento
class EditorState {
    private final String content;

    public EditorState(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

// Originator
class Editor {
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public EditorState save() {
        return new EditorState(content);
    }

    public void restore(EditorState state) {
        this.content = state.getContent();
    }
}

// Caretaker
class History {
    private Stack<EditorState> states = new Stack<>();

    public void push(EditorState state) {
        states.push(state);
    }

    public EditorState pop() {
        return states.pop();
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        Editor editor = new Editor();
        History history = new History();

        editor.setContent("a");
        history.push(editor.save());

        editor.setContent("b");
        history.push(editor.save());

        editor.setContent("c");
        System.out.println("Current content: " + editor.getContent());

        editor.restore(history.pop());
        System.out.println("Restored content: " + editor.getContent());

        editor.restore(history.pop());
        System.out.println("Restored content: " + editor.getContent());
    }
}
```