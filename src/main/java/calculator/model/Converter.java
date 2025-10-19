package calculator.model;

public class Converter {

    /**
     * 문자열에서 수식만 가져오는 함수
     * @param input
     * @return
     */
    public String getPureMath(String input) {
        if (!input.startsWith("//")) {
            return input;
        }

        int index = input.indexOf("\n");
        return input.substring(index + 1);
    }

    public String convert(String input) {
        return input.replace("\\n", "\n");
    }
}
