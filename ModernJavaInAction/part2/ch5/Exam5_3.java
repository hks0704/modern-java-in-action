package ModernJavaInAction.part2.ch5;

import ModernJavaInAction.part2.ch4.Employee;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 5.3 매핑
 */
public class Exam5_3 {
    public static void main(String[] args) {
        List<Employee> specialPerson = Arrays.asList(
                new Employee("Choi", true, 2800, Employee.Dept.OTHER),
                new Employee("Park", false, 3000, Employee.Dept.SALESMAN),
                new Employee("Hong", true, 3500, Employee.Dept.OTHER),
                new Employee("Yang", false, 4100, Employee.Dept.RESEARCHER),
                new Employee("Cho", true, 5400, Employee.Dept.OTHER)
        );
        // 5.3.1 스트림의 각 요소에 함수 적용
        // map함수를 사용하면 새로운 요소로 변환된 결과를 반환한다.
        List<String>employeeNames=specialPerson.stream()
                .map(Employee::getName)
                .collect(toList());
        System.out.println(employeeNames); // [Choi, Park, Hong, Yang, Cho]

        // 각 단어의 글자수를 반환하는 예제
        List<String> words = Arrays.asList("banana", "watermelon", "kiwi", "apple");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(wordLengths); // [6, 10, 4, 5]

        // 각 사원의 이름의 길이를 반환하는 예제
        // map을 연결해서 사용
        List<Integer>employeeNameLengths=specialPerson.stream()
                .map(Employee::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(employeeNameLengths); // [4, 4, 4, 4, 3]

        // 5.3.2 스트림 평면화
        // List에 포함된 고유문제로 이루어진 List반환
        words=Arrays.asList("Apple","Orange");

        // 잘못된 방법1 : split + distinct
        // 반환형이 String[] 스트림이 된다.
//        words.stream()
//                .map(word->word.split(""))
//                .distinct()
//                .collect(toList());

        // 잘못된 방법2 : map(Arrays::stream)
        // List<Stream<String>>이 만들어진다.
//        words.stream()
//                .map(word->word.split(""))
//                .map(Arrays::stream)
//                .distinct()
//                .collect(toList());
        // 제대로 된 결과 : flatMap 사용

        List<String> uniqueCharacters =
                words.stream()
                        .map(word->word.split(""))
                        .flatMap(Arrays::stream)
                        .distinct()
                        .collect(toList());
        System.out.println(uniqueCharacters); // [A, p, l, e, O, r, a, n, g]
        // flatMap : 결과를 스트림이 아닌 요소들로 매핑

        // 퀴즈 5-1 필터링
        // (1) 숫자 리스트를 3으로 나눈 나머지를 반환하기
        List<Integer> numbers = Arrays.asList(4,9,13,15,21,32);
        List<Integer> remainders = numbers.stream()
                .map(n -> n%3)
                .collect(toList());
        System.out.println(remainders); // [1, 0, 1, 0, 0, 2]
        // (2) 두 개의 숫자의 모든 순서쌍을 반환
        List<Integer> numbers1 = Arrays.asList(4,5,6,7);
        List<Integer> numbers2 = Arrays.asList(1,2,3);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i->
                    numbers2.stream()
                            .map(j->new int[]{i,j})
                )
                .collect(toList());
        printPair(pairs);
        // [4, 1] [4, 2] [4, 3] [5, 1] [5, 2] [5, 3] [6, 1] [6, 2] [6, 3] [7, 1] [7, 2] [7, 3]

        // (3) 앞 예제에서 5로 나누어 떨어지는 쌍만 반환
        pairs = numbers1.stream()
                .flatMap(i->
                        numbers2.stream()
                                .filter(j->(i+j)%5==0)
                                .map(j->new int[]{i,j})
                )
//                .filter(p->(p[0]+p[1])%5==0)
                .collect(toList());
        printPair(pairs); // [4, 1] [7, 3]
    }
    public static void printPair(List<int[]>pairs){
        for(int[] p : pairs)
            System.out.print(Arrays.toString(p)+" ");
        System.out.println();
    }
}
