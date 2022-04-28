package cn.element.collection.map;

/**
 * A minimalist employee class for testing purposes.
 */
public class Employee {
    private final String name;
    private final double salary;

    /**
     * Constructs an employee with $0 salary.
     */
    public Employee(String name) {
        this.name = name;
        salary = 0;
    }

    public String toString() {
        return "[name=" + name + ", salary=" + salary + "]";
    }
}
