package ModernJavaInAction.part2.ch5;

import java.util.Arrays;
import java.util.List;

/**
 * 5.1 필터링
 */
public class Exam5_1 {
    public static void main(String[] args) {
        // 5.1.1 프레디케이트로 필터링
        // filter에서는 predicate 메서드를 받아서 일치하는 요소만 거른다.
        // predicate의 반환형은 boolean

        // 5.1.2 고유 요소 필터링
        // distinct 키워드 활용
        List<String> names = Arrays.asList("박민재","김철수","최민영","윤재환","김철수","김정호");
        names.stream()
                .filter(name -> name.startsWith("김"))
                .distinct() // 김철수가 중복되는 결과를 필터링한다.
                .forEach(System.out::println); // 김철수, 김정호

    }
}
