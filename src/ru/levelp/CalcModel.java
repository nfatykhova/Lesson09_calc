package ru.levelp;

import java.util.Stack;

/**
 * Created by natalie on 20.03.16.
 */

public class CalcModel { // model - часть классов, отвечающая за логику (MVC)

    private View view;
    private String operation;
    private Stack<Double> operands; // числа будем сохранять в стек
    private boolean resultFlag;

    public CalcModel() {

        operands = new Stack<>(); // инициализировать правильнее в конструкторе
    }

    public void operate(String operand1, String operation) {

        double op1 = Double.parseDouble(operand1); // преобразуем operand1 в переменную double
        String lastOperation;
        lastOperation = this.operation;
        this.operation = operation; // так мы можем сохранять каждую последнюю операцию

        operands.push(op1);

        double result = 0;
        if (operands.size() == 2) { // ) && (operation != null)
            String tmpOper = operation;
            if (tmpOper.equals("="))
                tmpOper = lastOperation;
            if (tmpOper.equals("+")) result = operands.pop() + operands.pop();
            if (tmpOper.equals("-")) result = -operands.pop() + operands.pop();
            if (tmpOper.equals("*")) result = operands.pop() * operands.pop();
            if (tmpOper.equals("/")) result = 1 / (operands.pop() / operands.pop());

            if (tmpOper.equals("^")) {
                double a = operands.pop();
                double b = operands.pop();
                result = Math.pow(b, a); // число, степень
            }

            if (!operation.equals("=")) operands.push(result);
        }
            view.setResult(result);
            resultFlag = true;
    }

    public void negate(double num) {
        if (num != 0) {
            double negateNum = -num;
            view.setResult(negateNum);
        }
    }

    public void setView(View view) {
        this.view = view;
    }

    public String getOperation() {
        return operation;
    }

    public boolean isResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(boolean resultFlag) {
        this.resultFlag = resultFlag;
    }
}

