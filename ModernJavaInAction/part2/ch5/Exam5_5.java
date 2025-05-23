package ModernJavaInAction.part2.ch5;

import ModernJavaInAction.part2.ch4.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *  5.5 리듀싱
 */
public class Exam5_5 {
    public static void main(String[] args) {
        List<Employee> specialPerson = Arrays.asList(
                new Employee("Choi", true, 2800, Employee.Dept.OTHER),
                new Employee("Park", false, 3000, Employee.Dept.SALESMAN),
                new Employee("Hong", true, 3500, Employee.Dept.OTHER),
                new Employee("Yang", false, 4100, Employee.Dept.RESEARCHER),
                new Employee("Cho", true, 5400, Employee.Dept.OTHER)
        );

        // 리듀싱 연산 : 모든 스트림 요소를 처리해서 값으로 도출

        // 5.5.1 요소의 합
        // 모든 요소를 더한 값을 가져오고 싶을 때
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        // (1) 스트림을 사용하기 전
//        int sum = 0;
//        for(int x : numbers) {
//            sum += x;
//        }

        // (2) 반복 패턴을 추상화
        // reduce(초기값, 반복패턴)
        int sum1 = numbers.stream().reduce(0, (a, b) -> a + b);
        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        // 스트림의 값이 하나로 줄어들 때까지 반복이 진행됨
        System.out.println("sum: "+sum1); // 15
        System.out.println("product: "+product); // 20

        // 자바8의 Integer메서드 참조를 이용
        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println("sum: "+sum2); // 15

        // 초기값을 받지 않은 경우 Optional을 반환
        // 메서드 오버로딩
        // (스트림에 요소가 없는 경우)
        Optional<Integer> sum = numbers.stream().reduce((a, b) -> a + b);
        Optional<Integer> emptySum = new ArrayList<Integer>().stream().reduce((a, b) -> a + b);
        System.out.println("empty list result: "+emptySum); // Optional.empty

        // 5.5.2 최댓값과 최솟값을 찾을 때
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        // (x, y) -> x<y ? x:y 보다 직관적
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        System.out.println("max: "+max); // 5
        System.out.println("min: "+min); // 1

        // 퀴즈 5-3 리듀스
        // map과 reduce를 활용한 스트림의 사원 명수 계산
        int count = specialPerson.stream()
                .map(e -> 1)
                .reduce(0, Integer::sum); // (a, b) -> a + b
        System.out.println(count); // 5
        // cf : ch4에서 배운 요소 수 반환
        long longCount = specialPerson.stream().count(); // 5
        System.out.println(longCount);

        /*
         * reduce 메서드의 장점과 병렬화
         * 내부 반복이 추상화 되면서 내부 구현에서 병렬로 reduce를 실행할 수 있게된다.
         */

        /*
         * 스트림 연산 : 상태 없음과 상태 있음
         * 손쉽게 병렬성을 얻을 수 있지만 각각의 연산의 내부 상태를 고려해야 한다.
         * 내부 상태를 갖지 않는 연산 : map, filter
         * 내부 상태를 갖는 연산 : reduce, sum, max (결과를 누적할 필요가 있을 때)
         * sorted, distinct는 내부 상태를 갖는 연산이다. (정렬을 위해서는 이전상태를 기억해 둘 필요가 있음)
         */

    }
}
