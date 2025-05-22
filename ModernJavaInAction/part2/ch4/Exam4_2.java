package ModernJavaInAction.part2.ch4;

import java.util.Arrays;
import java.util.List;
import static java.util.stream.Collectors.toList;

public class Exam4_2 {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Kim", false, 4000, Employee.Dept.SALESMAN),
                new Employee("Park", false, 4300, Employee.Dept.SALESMAN),
                new Employee("Lee", false, 2800, Employee.Dept.SALESMAN),
                new Employee("Hong", true, 3000, Employee.Dept.OTHER),
                new Employee("Choi", true, 3500, Employee.Dept.OTHER),
                new Employee("Han", false, 5000, Employee.Dept.OTHER),
                new Employee("Jin", true, 6800, Employee.Dept.OTHER),
                new Employee("Bae", true, 7000, Employee.Dept.RESEARCHER),
                new Employee("Yang", false, 6100, Employee.Dept.RESEARCHER)
        );
        List<String> threeHighSalaryEmployeeNames =
                employees.stream()
                        .filter(employee -> employee.getSalary() > 4000)
                        .map(Employee::getName)
                        .limit(3)
                        .collect(toList());
        System.out.println(threeHighSalaryEmployeeNames);
    }
}
