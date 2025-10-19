package calculator.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 문자열 검사
 */
public class Condition {
    private static final String REGEX_CONDITION = "//(.)\n";
    private final Set<String> regex = new HashSet<>(Set.of(",", ":"));
    private final Pattern pattern = Pattern.compile(REGEX_CONDITION);

    /**
     * 구분자 추가
     * @param input
     */
    public void addCustomCondition(String input) {
        Matcher matcher = pattern.matcher(input);
        if (!matcher.find()) return;
        regex.add(matcher.group(1));
    }

    /**
     * 수식 검증
     * @param input
     */
    public void validateInputByDelimiters(String input) {
        String allowedChars = regex.stream()
                .map(this::escapeForCharClass)
                .collect(Collectors.joining());

        String allowedPattern = "^[0-9" + allowedChars + "]+$";
        if (!input.matches(allowedPattern)) {
            throw new IllegalArgumentException("허용되지 않은 문자가 포함되어 있습니다: " + input);
        }
    }


    private String escapeForCharClass(String d) {
        char c = d.charAt(0);
        return switch (c) {
            case '\\' -> "\\\\";
            case '-' -> "\\-";
            case '^' -> "\\^";
            case ']' -> "\\]";
            default -> String.valueOf(c);
        };
    }

    public List<String> getRegex() {
        return new ArrayList<>(regex);
    }
}
