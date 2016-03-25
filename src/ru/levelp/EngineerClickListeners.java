package ru.levelp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Nataly on 25.03.2016.
 */
public class EngineerClickListeners implements ActionListener {

    private View view;
    private CalcModel model;

    public EngineerClickListeners(CalcModel model) {
        this.model = model;
    }

    public EngineerClickListeners(View view) {
        this.view = view;
    }

    // sin, cos, tan, atan, ln, lg, log, x^2, sqrt, ^

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        double num = Double.parseDouble(view.getDisplay().getText());
        if (button.getText().equals("sin")) {
            double countNum = Math.sin(num);
            view.setResult(countNum);
        }
        if (button.getText().equals("cos")) {
            double countNum = Math.cos(num);
            view.setResult(countNum);
        }
        if (button.getText().equals("tan")) {
            double countNum = Math.tan(num);
            view.setResult(countNum);
        }
        if (button.getText().equals("atan")) {
            double countNum = Math.atan(num);
            view.setResult(countNum);
        }
        if (button.getText().equals("ln") || button.getText().equals("log")) {
            double countNum = Math.log(num);
            view.setResult(countNum);
        }
        if (button.getText().equals("lg")) {
            double countNum = Math.log10(num);
            view.setResult(countNum);
        }
        if (button.getText().equals("x^2")) {
            double countNum = Math.pow(num, 2);
            view.setResult(countNum);
        }
        if (button.getText().equals("sqrt")) {
            if (num > 0) {
                double countNum = Math.sqrt(num);
                view.setResult(countNum);
            } else {
                view.setResult(0);
            }
        }
        model.setResultFlag(true);

    }
}
