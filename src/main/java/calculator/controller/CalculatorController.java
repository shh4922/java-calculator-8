package calculator.controller;

import calculator.model.Converter;
import calculator.service.StringCalculator;
import calculator.model.Input;
import calculator.model.Condition;

import java.util.List;

public class CalculatorController {

    private final StringCalculator calculator;
    private final Condition condition;
    private final Input input;
    private final Converter converter;

    public CalculatorController() {
        this.calculator = new StringCalculator();
        this.condition = new Condition();
        this.input = new Input();
        this.converter = new Converter();
    }

    /**
     * 조건식 여부 체크
     * 조건식 있으면 문자열 나눔.
     * 커스텀 조건문 add
     * 문자열상수 더하는 로직 수행
     *
     */
    public void start() {
        try {
            String input = this.input.inputString();
            if(input.isEmpty()) {
                System.out.println("결과 : " + 0);
                return;
            }

            // 커스텀 구분자 추가
            input = this.converter.convert(input);
            this.condition.addCustomCondition(input);

            // 조건식 제외 후 수식 추출
            String mathString = this.converter.getPureMath(input);
            List<String> conditionList = this.condition.getRegex();


            // 허용되지 않은 문자 검사
            this.condition.validateInputByDelimiters(mathString);

            // 계산 수행
            int result = this.calculator.sumByDelimiters(conditionList, mathString);

            System.out.println("결과 : " + result);  // ✅ 테스트에서 이 문구를 검증함
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}
