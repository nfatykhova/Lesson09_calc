package ru.levelp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

/**
 * Created by natalie on 20.03.16.
 */
public class View extends JFrame { // класс - это окно

    private JLabel display;

    private CalcModel model; // ссылка - делает доступными методы другого класса

    private ActionListener arithmeticBtnListener =      // анонимный объект создаем в ссылку типа AL
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                        model.operate(display.getText(), button.getText());
                            if ((button.getText().equals("+")) ||
                                    (button.getText().equals("-")) ||
                                    (button.getText().equals("*")) ||
                                    (button.getText().equals("/")))
                                display.setText(" ");
                }
            };


    public View(CalcModel model) {

        this.model = model;
        init();
        model.setView(this);
    }

    private void init() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        setTitle("Calculator");

        display = new JLabel("0");
      //  display.setBounds(0, 0, 300, 20);

        JPanel numPanel = new JPanel(new GridLayout(4,3));
        JPanel eastPanel = new JPanel(new GridLayout(2,1));
        JPanel arithmeticPanel = new JPanel(new GridLayout(2,2));
        JPanel supportPanel = new JPanel(new GridLayout(2,2)); // заполнить

        JButton[] numBtns = new JButton[10];
        for (int i = 0; i < numBtns.length; i++) {
            numBtns[i] = new JButton((String.valueOf(i)));
            numBtns[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    if ( ( (model.getOperation() == null) && (display.getText().equals("0")) ) ||
                         ( (model.getOperation() != null) && (model.getOperation().equals("="))
                         && (model.isResultFlag() == true)) ) { // если на дисплее 0 (первый запуск) ИЛИ операция != нулю И равна =
                        //  (model.getOperation() == null) )
                        display.setText(button.getText());
                        model.setResultFlag(false);
                    } else {
                        display.setText(display.getText() + button.getText());
                    }
                }
            });
        }

        for (int i = 7; i < 10; i++) {
            numPanel.add(numBtns[i]);
        }

        for (int i = 4; i < 7; i++) {
            numPanel.add(numBtns[i]);
        }

        for (int i = 1; i < 4; i++) {
            numPanel.add(numBtns[i]);
        }
        numPanel.add(numBtns[0]);

        JButton addBtn = new JButton("+");
        JButton subBtn = new JButton("-");
        JButton mulBtn = new JButton("*");
        JButton divBtn = new JButton("/");
        JButton resultBtn = new JButton("=");

        JButton dotBtn = new JButton(".");
        JButton negateBtn = new JButton("+/-");

        dotBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!display.getText().contains(".") )
                    display.setText(display.getText() + ".");
                if (display.getText().trim().isEmpty() )
                    display.setText("0.");
            }
        });

        negateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double num = Double.parseDouble(display.getText());
                model.negate(num);
            }
        });

        numPanel.add(dotBtn);
        numPanel.add(negateBtn);

        addBtn.addActionListener(arithmeticBtnListener);
        subBtn.addActionListener(arithmeticBtnListener);
        mulBtn.addActionListener(arithmeticBtnListener);
        divBtn.addActionListener(arithmeticBtnListener);
        resultBtn.addActionListener(arithmeticBtnListener);

        arithmeticPanel.add(addBtn);
        arithmeticPanel.add(subBtn);
        arithmeticPanel.add(mulBtn);
        arithmeticPanel.add(divBtn);
        eastPanel.add(arithmeticPanel);
        eastPanel.add(resultBtn);

        add(BorderLayout.CENTER, numPanel);
        add(BorderLayout.EAST, eastPanel);
        add(BorderLayout.NORTH, display);

    }

    public void setResult(double result) {

        display.setText(String.valueOf(result));

    }
}
