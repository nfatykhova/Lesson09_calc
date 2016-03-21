package ru.levelp;

import java.util.Stack;

/**
 * Created by natalie on 20.03.16.
 */

public class CalcModel { // model - часть классов, отвечающая за логику (MVC)

    private View view;
    private String operation;
    private Stack<Double> operands; // числа будем сохранять в стек

    public CalcModel() {
        operands = new Stack<>(); // инициализировать правильнее в конструкторе
    }

    public void operate(String operand1, String operation) {

        double op1 = Double.parseDouble(operand1); // преобразуем operand1 в переменную double
        this.operation = operation; // так мы можем сохранять каждую последнюю операцию
        operands.push(op1);

            if (operation.equals("=")) {
                if (operands.size() > 1) {
                    double result = operands.pop() + operands.pop(); // метод
                    operands.push(result);
                    view.setResult(result);
                } else {
                    this.operation = operation;
                    view.setResult(0);
                }
            }
        }


    public void setView(View view) {
        this.view = view;
    }
}
