package ModernJavaInAction.part2.ch5;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * 5.6 실전 연습
 */
public class Exam5_6 {
    public static void main(String[] args) {
        Author park = new Author("Park", "Korea");
        Author brian = new Author("Brian", "USA");
        Author choi = new Author("Choi", "Korea");
        Author hwang = new Author("Hwang", "Korea");
        List<Book> books = Arrays.asList(
                new Book(hwang, "my brand new car", 2011, 300),
                new Book(park, "Good Food", 2012, 1000),
                new Book(park, "Language Study", 2011, 400),
                new Book(choi, "three colored cat", 2012, 710),
                new Book(choi, "Story of unhappy dog", 2012, 700),
                new Book(brian, "Speaking", 2012, 950)
        );

        // 1. 2011년에 출간된 모든 책을 찾아 값을 오름차순으로 정리
        List<Book> b2011 = books.stream()
                .filter(book -> book.getYear() == 2011) // 필터링 하도록 프레디케이트를 넘겨줌
                .sorted(comparing(Book::getPages)) // 페이지 개수로 요소 정렬
                .collect(toList());
        System.out.println("1번 문제의 결과");
        for(Book book : b2011)
            System.out.println(book);

        // 2. 작가가 거주하는 모든 국가를 중복 없이 나열
        List<String> nations = books.stream()
                .map(book -> book.getAuthor().getNationality()) // 책의 저자가 거주하는 국가 추출
                .distinct() // 고유 국가만 선택
                .collect(toList());
        System.out.println("2번 문제의 결과");
        System.out.println(nations);

        // distinct 대신 toSet()을 사용하는 방법도 있다 (ch6)
        Set<String> nationSet = books.stream()
                .map(book -> book.getAuthor().getNationality())
                .collect(toSet());

        // 3. 한국에 거주하는 모든 작가를 찾아서 이름 순으로 나열
        List<Author>authors = books.stream()
                .map(Book::getAuthor) // 책의 모든 작가 추출
                .filter(author -> author.getNationality().equals("Korea")) // 한국 거주 작가만 선택
                .distinct() // 중복 제거
                .sorted(comparing(Author::getName)) // 결과 스트림의 작가를 작가이름으로 정렬
                .collect(toList());
        System.out.println("3번 문제의 결과");
        System.out.println(authors);

        // 4. 모든 작가의 이름을 알파벳 순으로 정렬해서 반환
        String authorStr = books.stream()
                .map(book -> book.getAuthor().getName()) // 모든 작가 이름을 문자열 스트림으로 추출
                .distinct() // 중복 제거
                .sorted() // 알파벳 순 정렬
                .reduce("", (n1, n2) -> n1 + n2); // 하나의 문자열로 연결

        System.out.println("4번 문제의 결과");
        System.out.println(authorStr);

        // joining()을 활용하면 더 효율적인 처리가 가능하다 (ch6)
        authorStr = books.stream()
                .map(book -> book.getAuthor().getName())
                .distinct()
                .sorted()
                .collect(joining());

        // 5. 미국 작가가 쓴 책이 있는가?
        boolean chkUSAAuthor = books.stream()
                        .anyMatch(book -> book.getAuthor().getNationality().equals("USA"));
        // 프레디케이트를 전달해 미국 작가가 쓴 책이 있는지 확인
        System.out.println("5번 문제의 결과");
        System.out.println(chkUSAAuthor);

        // 6. 한국에 거주하는 작가의 모든 책의 페이지 수를 출력
        System.out.println("6번 문제의 결과");
        books.stream()
                .filter(b -> "Korea".equals(b.getAuthor().getNationality())) // 한국 거주 작가의 책을 선택
                .map(Book::getPages) // 책의 페이지 값을 추출
                .forEach(System.out::println); // 각 값을 출력

        // 7. 전체 책 중에서 가장 긴 페이지는
        Optional<Integer> maxPageNum = books.stream()
                .map(Book::getPages) // 책 페이지수 추출
                .reduce(Integer::max); // 최댓값 계산
        System.out.println("7번 문제의 결과");
        System.out.println(maxPageNum);

        // 8. 전체 책 중에서 가장 페이지 수가 작은 책은
        Optional<Book> minPageBook = books.stream()
                .reduce((b1, b2) ->
                        b1.getPages() < b2.getPages() ? b1 : b2); // 반복 비교해서 가장 작은 결과를 검색
        System.out.println("8번 문제의 결과");
        System.out.println(minPageBook);

    }
}

