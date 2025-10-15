# Singleton Design Pattern

**Purpose:** Ensures a class has only one instance and provides a global point of access to it.

**Demand for this pattern:**
When you need to ensure that a class has only one instance and that this instance is accessible globally throughout the application. This is common for resources like loggers, configuration managers, database connection pools, or thread pools, where having multiple instances could lead to inconsistencies or resource contention.

**Without it:**
Without the Singleton pattern, you would either:
1.  **Multiple Instances:** Allow multiple instances of a class to be created, potentially leading to conflicting states or inefficient resource usage.
2.  **Manual Management:** Rely on developers to manually ensure only one instance is created, which is error-prone and not enforced by the system.

**How it solved the problem:**
The Singleton pattern restricts the instantiation of a class to a single object. It typically involves:
*   A private constructor to prevent direct instantiation.
*   A static private instance of the same class.
*   A static public method that returns the single instance, creating it if it doesn't already exist.

**Is it an exclusive pattern for this kind of problem?**
For ensuring a single instance and global access, Singleton is the primary pattern. While dependency injection frameworks can manage single instances, Singleton provides a self-contained way to enforce this constraint within the class itself. It's often considered an anti-pattern if overused or if it introduces global state that makes testing difficult.

**When to use:**
*   There must be exactly one instance of a class, and it must be accessible to clients from a well-known access point.
*   The sole instance should be extensible by subclassing, and clients should be able to use an extended instance without modifying their code.

**Why to use:**
*   **Controlled Access:** Provides a single point of access to the instance, allowing for better control over its lifecycle.
*   **Resource Management:** Prevents multiple instances from consuming excessive resources (e.g., multiple database connections).
*   **Global State (with caution):** Can manage global application state, though this should be used carefully to avoid tight coupling and testing difficulties.

**How to use:**
1.  **Private Constructor:** Make the constructor of the class private to prevent direct instantiation from outside the class.
2.  **Static Instance:** Create a private static instance of the class within the class itself.
3.  **Static Factory Method:** Provide a public static method that returns the instance. This method checks if the instance already exists; if not, it creates it and then returns it.

**Examples to Remember:**

1.  **Logger:** A single logging instance to write logs to a file or console, ensuring all log messages go through the same channel.

2.  **Configuration Manager:** A single instance to load and manage application configuration settings, ensuring all parts of the application use the same settings.

3.  **Database Connection Pool:** A single pool of database connections to manage and reuse connections efficiently across the application.

```java
// Singleton (Lazy Initialization - Thread Safe)
class DatabaseConnection {
    private static DatabaseConnection instance;
    private String connectionString;

    // Private constructor to prevent direct instantiation
    private DatabaseConnection(String connectionString) {
        this.connectionString = connectionString;
        System.out.println("DatabaseConnection created with: " + connectionString);
    }

    // Public static method to get the single instance
    public static synchronized DatabaseConnection getInstance(String connectionString) {
        if (instance == null) {
            instance = new DatabaseConnection(connectionString);
        }
        return instance;
    }

    public void connect() {
        System.out.println("Connecting to database using: " + connectionString);
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        DatabaseConnection db1 = DatabaseConnection.getInstance("jdbc:mysql://localhost:3306/mydb");
        db1.connect();

        DatabaseConnection db2 = DatabaseConnection.getInstance("jdbc:postgresql://localhost:5432/otherdb");
        db2.connect();

        // Both db1 and db2 refer to the same instance
        System.out.println("Are db1 and db2 the same instance? " + (db1 == db2));
    }
}
```