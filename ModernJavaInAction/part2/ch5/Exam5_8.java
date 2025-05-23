package ModernJavaInAction.part2.ch5;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 5.8 스트림 만들기
 */
public class Exam5_8 {
    public static void main(String[] args) {
        // 다양한 방식의 스트림 사용법

        // 5.8.1 값으로 스트림 만들기
        // Stream.of() : 임의의 수를 인수로 받는 정적 메서드로 스트림 생성
        Stream<String> stream = Stream.of("C++", "Python", "Java", "Javascript");
        stream.map(String::toUpperCase).forEach(System.out::println);
        //C++
        //PYTHON
        //JAVA
        //JAVASCRIPT

        // 5.8.2 null이 될 수 있는 객체로 스트림 만들기
        // System.getProperties(key) : 제공된 키에 대응하는 속성이 없으면 null 반환

        // null을 명시적으로 확인하는 경우
//        String homeValue = System.getProperty("home");
//        Stream<String> homeValueStream
//                = homeValue == null ? Stream.empty() : Stream.of(value);
        // Stream.ofNullable()을 활용한 방법
        Stream<String> homeValueStream
                = Stream.ofNullable(System.getProperty("home"));

        // null이 될 수 있는 경우 + flatMap과 함께 사용할 때 효과적
        Stream<String> values =
                Stream.of("config", "home", "user")
                        .flatMap(key -> Stream.ofNullable(System.getProperty(key)));

        // 5.8.3 배열로 스트림 만들기
        // Arrays.stream()을 이용하면 배열로 스트림을 만들 수 있다.
        // int배열을 IntStream으로 변환
        int[] numbers = {1, 2, 3, 4, 5};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum); // 15

        // 5.8.4 파일로 스트림 만들기
        // NIO(비블록) API도 스트림 API를 활용할 수 있도록 업데이트 됨
        // Files.lines() : 파일의 행스트림을 문자열로 반환, 문자열 스트림을 얻을 때 사용
        // 프로그램이 실행중인 디렉토리를 찾고 싶을 때 : System.getProperty("user.dir")
        long uniqueWords = 0;
        try (Stream<String> lines =
                     Files.lines(Paths.get("./src/ModernJavaInAction/part2/ch5/data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("파일을 읽는 도중 예외가 발생했습니다.");
        }
        System.out.println("uniqueWords: "+uniqueWords); // 6
        // Stream 인터페이스는 AutoCloseable 인터페이스를 구현함
        // 따라서 finally를 이용한 자원 close 기능이 자동으로 지원됨
        // flatMap : 여러 개의 스트림이 아닌 한 개의 스트림으로 평면화

        // 5.8.5 함수로 무한 스트림 만들기
        // 스트림을 만들 수 있는 두 정적 메서드
        // Stream.iterate, Stream.generate를 사용하면
        // 무한 스트림을 만들 수 있다. (크기가 고정되지 않은 스트림)
        // 보통을 무한한 값을 출력하지 않도록 limit(n)과 함께 사용

        // iterate 메서드
        // 초깃값과 람다를 인수로 받음
        Stream.iterate(0, n->n+2)
                .limit(10)
                .forEach(System.out::println); // 0부터 18까지 짝수 출력
        // 끝이 없는 스트림을 만들 수 있음 -> 스트림과 컬렉션의 차이점
        // 연속된 일련의 값을 만들 때 iterate를 주로 사용한다.

        // 퀴즈 5-4 피보나치수열 집합
        // iterate를 이용해 피보나치수열 집합 20개 만들기
        // 예시 : (0, 1), (1, 1), (1, 2), (2, 3), (3, 5)...
        // ans
        Stream.iterate(new int[]{0, 1},x->new int[]{x[1],x[0]+x[1]})
                .limit(20)
                .forEach(t-> System.out.printf("(%d, %d)\n",t[0],t[1]));
        // map으로 하나의 값만 추출한 결과
        Stream.iterate(new int[]{0, 1},x->new int[]{x[1],x[0]+x[1]})
                .limit(20)
                .map(t->t[0])
                .forEach(System.out::println);

        // 자바9의 iterate는 프레디케이트를 지원
        // (ex) 0에서 시작해 60보다 크면 숫자 생성을 중단하는 코드

        IntStream.iterate(0, n->n<=60, n->n+4)
                .forEach(System.out::println);

        // filter 메서드는 언제 작업을 중단해야 되는지 알 수 없기 때문에
        // 다음 코드는 끝나지 않고 계속 반복된다.
//        IntStream.iterate(0, n->n+4)
//                .filter(n->n<=60)
//                .forEach(System.out::println);

        // takeWhile을 이용해서 해결
        IntStream.iterate(0, n->n+4)
                .takeWhile(n->n<=20)
                .forEach(System.out::println);

        // generate 메서드
        // ...

    }
}
