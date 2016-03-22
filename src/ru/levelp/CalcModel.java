package ru.levelp;

import java.util.Stack;

/**
 * Created by natalie on 20.03.16.
 */

public class CalcModel { // model - часть классов, отвечающая за логику (MVC)

    private View view;
    private String operation;

    public void popOperands() {
        operands.pop();
    }

    public String getOperation() {
        return operation;
    }

    private Stack<Double> operands; // числа будем сохранять в стек

    public CalcModel() {
        operands = new Stack<>(); // инициализировать правильнее в конструкторе
    }

    public void operate(String operand1, String operation) {

        double op1 = Double.parseDouble(operand1); // преобразуем operand1 в переменную double
        String lastOperation;
//        if (operation.equals("=")) {
            lastOperation = this.operation;
            this.operation = operation; // так мы можем сохранять каждую последнюю операцию
//        } else {
//            this.operation = operation;
//            lastOperation = operation;
//        }

        operands.push(op1);

        double result = 0;
        if (operands.size() == 2) { // ) && (operation != null)
            String tmpOper = operation;
            if ( tmpOper.equals("=") )
                tmpOper = lastOperation;
            if (tmpOper.equals("+")) result = operands.pop() + operands.pop();
            if (tmpOper.equals("-")) result = -operands.pop() + operands.pop();
            if (tmpOper.equals("*")) result = operands.pop() * operands.pop();
            if (tmpOper.equals("/")) result = 1 / (operands.pop() / operands.pop());
            operands.push(result);

        }
            if (operation.equals("=")) {
                view.setResult(result);
                operands.pop();
            }

    }

    public void setView(View view) {
        this.view = view;
    }
}
