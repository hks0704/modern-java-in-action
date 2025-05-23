package ModernJavaInAction.part2.ch5;

import ModernJavaInAction.part2.ch4.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 5.7 숫자형 스트림
 */
public class Exam5_7 {
    public static void main(String[] args) {
        List<Employee> specialPerson = Arrays.asList(
                new Employee("Choi", true, 2800, Employee.Dept.OTHER),
                new Employee("Park", false, 3000, Employee.Dept.SALESMAN),
                new Employee("Hong", true, 3500, Employee.Dept.OTHER),
                new Employee("Yang", false, 4100, Employee.Dept.RESEARCHER),
                new Employee("Cho", true, 5400, Employee.Dept.OTHER)
        );
        // 전체 사원 연봉을 구하는 코드
        int salaries = specialPerson.stream()
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);
        System.out.println(salaries); // 18800

        // 아래와 같이 직접 sum 메서드를 호출할 수 있으면 좋을 것 같다.
//        int salaries = specialPerson.stream()
//                .map(Employee::getSalary)
//                .sum();
        // -> 숫자 스트림을 효율적으로 처리할 수 있는 기본형 특화 스트림 제공

        // 5.7.1 기본형 특화 스트림
        // 자바 8에서는 IntStream, DoubleStream, LongStream
        // 이렇게 3가지 기본형 특화 스트림을 제공

        // 숫자 스트림 매핑
        // 스트림을 숫자 스트림으로 변환하고 싶을 때
        // mapToInt, mapToDouble, mapToLong
        salaries = specialPerson.stream()
                .mapToInt(Employee::getSalary)
                .sum();
        // Integer 형식인 연봉을 추출해서 IntStream으로 변환
        // 비어있는 스트림의 경우 sum()은 0을 반환
        System.out.println(salaries); // 18800
        // IntStream은 max, min, average 등의 다양한 유틸리티 메서드도 지원

        // 객체 스트림으로 복원하기
        // 숫자 스트림으로 만든다음 원래 스트림으로 복원하고 싶을 때
        // boxed() : 특화 스트림을 일반 스트림으로 변환
        IntStream intStream = specialPerson.stream().mapToInt(Employee::getSalary); // 스트림을 숫자 스트림으로 변환
        Stream<Integer> stream = intStream.boxed(); // 숫자 스트림을 스트림으로 변환

        // 기본값 : OptionalInt
        // Optional을 사용하면 이전에 값이 존재하는지 여부를 확인할 수 있다.
        // Optional을 Integer, String 등의 참조 형식으로 파라미터화할 수 있다.
        // OptionalInt, OptionalDouble, OptionalLong을 제공

        OptionalInt maxSalary = specialPerson.stream()
                .mapToInt(Employee::getSalary)
                .max();
        System.out.println(maxSalary); // 5400
        // Optional에서 값이 없을 때 기본값을 명시하는 방법
        int max = maxSalary.orElse(1);

        // 5.7.2 숫자 범위
        // 특정 범위의 숫자를 사용하는 상황에서
        // IntStream과 LongStream에서는 range와 rangeClosed를 제공
        IntStream evenNumbers = IntStream.rangeClosed(1, 100) // 1부터 100까지
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count()); // 50개의 짝수가 존재
//        IntStream.range(1, 100) // 이때는 1부터 99까지

        // 5.7.3 숫자 스트림 활요 : 피타고라스 수
        // 피타고라스 수 : 피타고라스 법칙을 만족하는 세 개의 정수

        // 세 수 표현하기

        // 세 수를 표현하는 방법
//        new int[]{3,4,5} 같은 방식으로 표현

        // 좋은 필터링 조합
        // 두 수 a, b가 주어졌을 때 제곱근이 정수인지 확인하는 방법은?
//        int a=3; int b=4;
//        System.out.println(Math.sqrt(a*a+b*b)%1==0); // true
        // 부동 소숫점 수에서 % 1.0을 하면 소숫점 이하 부분을 얻을 수 있다.
        System.out.println(3.4%1); // 0.3999999999999999
        System.out.println(23%1); // 0
        System.out.println(5.0%1); // 0.0
        // (ex) 5.0 % 1.0 을 하면 소숫점 이하는 0

        // filter에 적용한 결과
//        filter(b -> Math.sqrt(a*a+b*b)%1==0);

        // 집합 생성
        // 필터를 이용해 a, b 조합을 선택하고 map을 이용해 피타고라스 수로 변환
//        stream.filter(b -> Math.sqrt(a*a+b*b)%1==0)
//                .map(b -> new int[]{a, b, (int)Math.sqrt(a*a+b*b)});

        // b값 생성
//        IntStream.rangeClosed(1, 100)
//                .filter(b -> Math.sqrt(a*a+b*b)%1==0)
//                .boxed()
//                .map(b -> new int[]{a, b, (int) Math.sqrt(a*a+b*b)});
        // map에서 스트림의 각 요소를 int[] 로 변환시키기 때문에
        // boxed()를 이용해서 IntStream을 Stream<Integer>로 복원

        // 개체값 스트림을 반환하는 mapToObj 사용
//        IntStream.rangeClosed()
//                .filter(b -> Math.sqrt(a*a+b*b)%1==0)
//                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a*a+b*b)});

        // a값 생성하는 부분을 추가하고 최종적으로 완성된 결과
        // flatMap으로 각각의 스트림을 하나의 평준화된 스트림으로 만든다.
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        );
        // 코드 실행
        pythagoreanTriples.limit(5)
                .forEach(t ->
                        System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
        // result
//        3, 4, 5
//        5, 12, 13
//        6, 8, 10
//        7, 24, 25
//        8, 15, 17

        // 개선할 점 : 제곱근 계산을 한 번만 수행하고
        // 성립하는 경우만 필터링한다.
        Stream<double[]> pythagoreanTriples2 =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a -> IntStream.rangeClosed(a, 100)
                                .mapToObj(
                                        b -> new double[]{a, b, Math.sqrt(a * a + b * b)}

                                ).filter(t -> t[2] % 1 == 0)
                        );
    }
}
