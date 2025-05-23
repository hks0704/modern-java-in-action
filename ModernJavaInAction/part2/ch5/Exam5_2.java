package ModernJavaInAction.part2.ch5;

import ModernJavaInAction.part2.ch4.Employee;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Exam5_2 {
    public static void main(String[] args) {
        // 5.2.1. 프레디케이트를 이용한 슬라이싱
        List<Employee> specialPerson = Arrays.asList(
                new Employee("Choi", true, 2800, Employee.Dept.OTHER),
                new Employee("Park", false, 3000, Employee.Dept.SALESMAN),
                new Employee("Hong", true, 3500, Employee.Dept.OTHER),
                new Employee("Yang", false, 4100, Employee.Dept.RESEARCHER),
                new Employee("Cho", true, 5400, Employee.Dept.OTHER)
        );

        List<Employee> filteredPerson
                =specialPerson.stream()
                .filter(employee -> employee.getSalary()<4000)
                .collect(toList());
        System.out.println(filteredPerson); // [Choi, Park, Hong]

        // TAKEWHILE
        // 연봉이 4000미만인 사원을 선택하는 방법
        // 연봉이 정렬된 경우 4000 이상일 때 반복을 중단하는 방식이
        // 많은 요소를 포함하는 경우 효과적이다.
        List<Employee> slicedPerson1
                =specialPerson.stream()
                .takeWhile(employee -> employee.getSalary()<4000)
                .collect(toList());

        System.out.println(slicedPerson1); // [Choi, Park, Hong]

        // DROPWHILE
        // takeWhile과 반대로 참일 경우 값을 버리고
        // 거짓일 경우 남은 모든 요소를 반환하고 종료
        List<Employee> slicedPerson2
                =specialPerson.stream()
                .dropWhile(employee -> employee.getSalary()<4000)
                .collect(toList());
        System.out.println(slicedPerson2); // [Yang, Cho]

        // 5.2.2 스트림 축소
        // limit(n) 을 활용하면 최대 요소 n개를 반환
        // 정렬되지 않은 경우에도 limit을 쓸 수 있다.
        List<Employee>employees=specialPerson.stream()
                .dropWhile(employee -> employee.getSalary()<4000)
                .limit(1) // 1개만 반환
                .collect(toList());
        System.out.println(employees); // [Yang]

        // 5.2.3 요소 건너뛰기
        // 처음 n개의 요소를 제외하고 나머지를 반환하고 싶다면
        // skip(n) 을 사용한다.
        employees=specialPerson.stream()
                .filter(e -> e.getSalary()<4000)
                .skip(1) // 처음 1개를 제외함
                .collect(toList());
        System.out.println(employees); // [Park, Hong]

        // 퀴즈 5-1 필터링
        // 처음 등장하는 두 명의 OTHER 부서 사원을 반환
        // ans
        employees=specialPerson.stream()
                .filter(e -> e.getDept() == Employee.Dept.OTHER)
                .limit(2)
                .collect(toList());
        System.out.println(employees); // [Choi, Hong]

    }
}
