package ModernJavaInAction.part2.ch5;

import ModernJavaInAction.part2.ch4.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * 5.4 검색과 매칭
 */
public class Exam5_4 {
    public static void main(String[] args) {
        List<Employee> specialPerson = Arrays.asList(
                new Employee("Choi", true, 2800, Employee.Dept.OTHER),
                new Employee("Park", false, 3000, Employee.Dept.SALESMAN),
                new Employee("Hong", true, 3500, Employee.Dept.OTHER),
                new Employee("Yang", false, 4100, Employee.Dept.RESEARCHER),
                new Employee("Cho", true, 5400, Employee.Dept.OTHER)
        );
        // 5.4.1 anyMatch : 주어진 스트림에서 적어도 하나의 요소와 일치하는지 알고 싶을 때 사용
        if(specialPerson.stream().anyMatch(Employee::isActive)) {
            System.out.println("적어도 1명의 사원은 재직중");
        }
        // 5.4.2
        // allMatch : 주어진 스트림의 모든 요소와 일치하는지 알고 싶을 때 사용
        boolean hasLessMoney = specialPerson.stream()
                .allMatch(employee -> employee.getSalary()<8000);
        // noneMatch : allMatch와 반대 기능
        hasLessMoney = specialPerson.stream()
                .noneMatch(employee -> employee.getSalary()>=8000);

        // [쇼트서킷 평가]
        // and 연산에서 하나만 false, or 연산에서 하나만 true일 때
        // short circuit이 적용된다.

        // 5.4.3 요소선택
        // findAny : 임의의 하나를 선택할 때 사용
        Optional<Employee> employee =
                specialPerson.stream()
                        .filter(Employee::isActive)
                        .findAny();

        /*
         * Optional 클래스
         * 자바8에서 등장. 값이 없을 때 처리하는 기능을 강제함.
         * - isPresent() : 값의 포함여부에 따라 boolean 반환
         * - ifPresent(Consumer<T> block) 값이 있으면 블록을 실행
         * - T get() 값이 있으면 반환, 없으면 런타임 에러
         * - T orElse(T other) 값이 있으면 반환, 없으면 기본값 반환
         */

        specialPerson.stream()
                .filter(Employee::isActive)
                .findAny() // Optional<Employee> 반환
                .ifPresent(e -> System.out.println(e.getName())); // 값이 있을때만 출력
        // Choi

        // 5.4.4 첫 번째 요소 찾기
        // findFirst : 첫 번째 요소를 찾고싶을 때 사용
        List<Integer> someNumbers = Arrays.asList(3,4,2,5,1);
        Optional<Integer> firstCubeDivisibleByThree =
                someNumbers.stream()
                        .map(n->n*n*n)
                        .filter(n->n%3==0)
                        .findFirst();
        System.out.println(firstCubeDivisibleByThree); // Optional[27]

        /*
         * findFirst와 findAny
         * 병렬스트림에서는 findAny 그렇지 않은 경우 findFirst를 사용
         */
    }
}
