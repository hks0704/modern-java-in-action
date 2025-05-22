package ModernJavaInAction.part2.ch4;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

// 스트림 연산
public class Exam4_4 {
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
        // 파이프라인 중간과정을 확인하기 위한 print
        // 실제 제품 코드에는 추가하지 않는 것이 좋다
        List<String> names =
                employees.stream()
                        .filter(employee -> {
                            System.out.println("filtering: "+employee.getName());
                            return employee.getSalary()>4000;
                        })
                        .map(employee -> {
                            System.out.println("mapping: "+employee.getName());
                            return employee.getName();

                        })
                        .limit(3)
                        .collect(toList());
        System.out.println(names);
        // 결과
        // 처음 3개만 선택되고 끝난다 : short circuit
        // 서로다른 filter와 map 연산이 병합된다 : loop fusion
    }
}
