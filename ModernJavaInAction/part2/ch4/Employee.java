package ModernJavaInAction.part2.ch4;

public class Employee {
    private final String name;
    private final boolean active;
    private final int salary;
    private final Dept dept;

    public Employee(String name, boolean active, int salary, Dept dept) {
        this.name = name;
        this.active = active;
        this.salary = salary;
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public int getSalary() {
        return salary;
    }

    public Dept getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return name;
    }

    public enum Dept {RESEARCHER, SALESMAN, OTHER};
}
