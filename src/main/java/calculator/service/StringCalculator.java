package calculator.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 계산
 */
public class StringCalculator {


    /** 문자열 계산 */
    public int sumByDelimiters(List<String> delimiters, String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        // ✅ 1️⃣ 구분자 리스트를 정규식으로 합침
        String regex = delimiters.stream()
                .map(Pattern::quote)
                .reduce((a, b) -> a + "|" + b)
                .orElse(",");

        // ✅ 2️⃣ 문자열을 구분자로 split 후 숫자 변환
        List<Integer> numbers = Arrays.stream(input.split(regex))
                .filter(s -> !s.isEmpty())   // 빈 문자열만 제거 (공백은 유효한 구분자일 수도 있음)
                .map(s -> {
                    if (!s.matches("-?\\d+")) { // 숫자 유효성 검사
                        throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + s);
                    }
                    return Integer.parseInt(s);
                })
                .toList();

        // ✅ 3️⃣ 합계 계산
        return numbers.stream()
                .reduce(0, Integer::sum);
    }
}