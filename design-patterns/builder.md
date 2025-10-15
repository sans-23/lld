# Builder Design Pattern

**Purpose:** Separate the construction of a complex object from its representation so that the same construction process can create different representations.

**Demand for this pattern:**
When an object has many optional parameters or requires a complex, step-by-step construction process, directly using a constructor with many arguments can become unwieldy, error-prone, and hard to read (the "telescoping constructor" anti-pattern). It also makes the object immutable after construction, which is often desirable.

**Without it:**
Without the Builder pattern, you might end up with:
1.  **Telescoping Constructors:** Multiple constructors with increasing numbers of parameters, leading to constructor explosion and difficulty in understanding which parameter is which.
2.  **Setter Methods:** Creating an object with a default constructor and then using many setter methods. This makes the object mutable during construction and doesn't guarantee a fully initialized object until all setters are called, potentially leaving the object in an inconsistent state.

**How it solved the problem:**
The Builder pattern provides a step-by-step approach to construct a complex object. It uses a separate `Builder` object that takes care of the construction details. The client interacts with the builder to specify the desired parts and then asks the builder to return the final product. This makes the construction process more readable, flexible, and ensures the object is fully initialized and immutable once built.

**Is it an exclusive pattern for this kind of problem?**
For constructing complex objects with many optional parameters, the Builder pattern is the most common and effective solution. Other patterns like Factory Method or Abstract Factory are used for creating families of related objects or deferring instantiation to subclasses, but they don't address the complexity of a single object's construction process as elegantly as the Builder.

**When to use:**
*   The construction process for an object is complex and involves many steps or optional parameters.
*   You want to create different representations of a complex object using the same construction process.
*   You want to make an object immutable after it's created.
*   You want to avoid a "telescoping constructor" with a large number of parameters.

**Why to use:**
*   **Readability:** Improves the readability of object creation code, especially for objects with many parameters.
*   **Flexibility:** Allows for different variations of an object to be constructed using the same builder, by simply calling different building methods.
*   **Immutability:** Facilitates the creation of immutable objects, as the object is constructed in a single `build()` call.
*   **Separation of Concerns:** Separates the construction logic from the object's class, making both cleaner.

**How to use:**
1.  **Product:** Define the complex object that needs to be built (e.g., `Car`, `House`, `Meal`).
2.  **Builder Interface (Optional but good practice):** Define an interface for creating parts of the Product object.
3.  **Concrete Builder:** Implement the Builder interface. It provides methods to construct different parts of the Product and a `build()` method to return the final Product.
4.  **Director (Optional):** A `Director` class can be used to encapsulate common construction algorithms, making it easier to construct predefined configurations of the Product.

**Examples to Remember:**

1.  **Pizza Builder:** A `Pizza` object with various toppings, crust types, and sizes. A `PizzaBuilder` can have methods like `withDough()`, `withSauce()`, `addTopping()`, and `build()`.

2.  **Car Configuration:** A `Car` object with optional features like GPS, sunroof, leather seats, and engine type. A `CarBuilder` allows you to specify these features step-by-step.

3.  **SQL Query Builder:** Constructing complex SQL queries with multiple `SELECT`, `FROM`, `WHERE`, `JOIN` clauses. A `QueryBuilder` provides methods for each part of the query.

```java
// Product: The complex object to be built
class Burger {
    private String size;
    private boolean cheese;
    private boolean pepperoni;
    private boolean lettuce;
    private boolean tomato;

    // Private constructor to force use of the Builder
    private Burger(BurgerBuilder builder) {
        this.size = builder.size;
        this.cheese = builder.cheese;
        this.pepperoni = builder.pepperoni;
        this.lettuce = builder.lettuce;
        this.tomato = builder.tomato;
    }

    @Override
    public String toString() {
        return "Burger [size=" + size + ", cheese=" + cheese + ", pepperoni=" + pepperoni +
               ", lettuce=" + lettuce + ", tomato=" + tomato + "]";
    }

    // Builder Class
    static class BurgerBuilder {
        private String size; // Required
        private boolean cheese = false; // Optional, default false
        private boolean pepperoni = false; // Optional, default false
        private boolean lettuce = false; // Optional, default false
        private boolean tomato = false; // Optional, default false

        public BurgerBuilder(String size) {
            this.size = size;
        }

        public BurgerBuilder addCheese() {
            this.cheese = true;
            return this;
        }

        public BurgerBuilder addPepperoni() {
            this.pepperoni = true;
            return this;
        }

        public BurgerBuilder addLettuce() {
            this.lettuce = true;
            return this;
        }

        public BurgerBuilder addTomato() {
            this.tomato = true;
            return this;
        }

        public Burger build() {
            return new Burger(this);
        }
    }
}

// Client Code
public class Main {
    public static void main(String[] args) {
        Burger classicBurger = new Burger.BurgerBuilder("Medium")
                                        .addCheese()
                                        .addLettuce()
                                        .build();
        System.out.println(classicBurger);

        Burger spicyBurger = new Burger.BurgerBuilder("Large")
                                      .addCheese()
                                      .addPepperoni()
                                      .addTomato()
                                      .build();
        System.out.println(spicyBurger);

        Burger simpleBurger = new Burger.BurgerBuilder("Small").build();
        System.out.println(simpleBurger);
    }
}
```