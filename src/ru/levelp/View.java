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
                            if (button.getText().equals("^")) display.setText(display.getText() + button.getText());
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
        display.setPreferredSize(new Dimension(300, 40) );
        display.setFont(new Font("Arial", Font.PLAIN, 20));
      //  display.setBounds(0, 0, 300, 20);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel numPanel = new JPanel(new GridLayout(4,3));
        JPanel eastPanel = new JPanel(new GridLayout(2,1));
        JPanel arithmeticPanel = new JPanel(new GridLayout(2,2));
        JPanel supportPanel = new JPanel(new GridLayout(1, 6)); // заполнить
        JPanel engineerPanel = new JPanel(new GridLayout(5, 2));

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

        addBtn.addActionListener(arithmeticBtnListener);
        subBtn.addActionListener(arithmeticBtnListener);
        mulBtn.addActionListener(arithmeticBtnListener);
        divBtn.addActionListener(arithmeticBtnListener);
        resultBtn.addActionListener(arithmeticBtnListener);

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

        JButton mcBtn = new JButton("MC");
        JButton mrBtn = new JButton("MR");
        JButton mplusBtn = new JButton("M+");
        JButton mminBtn = new JButton("M-");
        JButton cBtn = new JButton("C");
        JButton delBtn = new JButton("<-");

        cBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display.setText(" ");
            }
        });

// sin, cos, tan, atan, ln, lg, log2, x^2, sqrt, ^

        JButton sinBtn = new JButton("sin");
        JButton cosBtn = new JButton("cos");
        JButton tanBtn = new JButton("tan");
        JButton atanBtn = new JButton("atan");
        JButton lnBtn = new JButton("ln");
        JButton lgBtn = new JButton("lg");
        JButton log2Btn = new JButton("log");
        JButton x2Btn = new JButton("x^2");
        JButton sqrtBtn = new JButton("sqrt");
        JButton degreeBtn = new JButton("^");

        EngineerClickListeners engineerListener = new EngineerClickListeners(model, this);
        sinBtn.addActionListener(engineerListener);
        cosBtn.addActionListener(engineerListener);
        tanBtn.addActionListener(engineerListener);
        atanBtn.addActionListener(engineerListener);
        lnBtn.addActionListener(engineerListener);
        lgBtn.addActionListener(engineerListener);
        log2Btn.addActionListener(engineerListener);
        x2Btn.addActionListener(engineerListener);
        sqrtBtn.addActionListener(engineerListener);
        degreeBtn.addActionListener(arithmeticBtnListener);

        engineerPanel.add(sinBtn);
        engineerPanel.add(cosBtn);
        engineerPanel.add(tanBtn);
        engineerPanel.add(atanBtn);
        engineerPanel.add(lnBtn);
        engineerPanel.add(lgBtn);
        engineerPanel.add(log2Btn);
        engineerPanel.add(x2Btn);
        engineerPanel.add(sqrtBtn);
        engineerPanel.add(degreeBtn);

        supportPanel.add(mcBtn);
        supportPanel.add(mrBtn);
        supportPanel.add(mplusBtn);
        supportPanel.add(mminBtn);
        supportPanel.add(cBtn);
        supportPanel.add(delBtn);

        arithmeticPanel.add(addBtn);
        arithmeticPanel.add(subBtn);
        arithmeticPanel.add(mulBtn);
        arithmeticPanel.add(divBtn);

        eastPanel.add(arithmeticPanel);
        eastPanel.add(resultBtn);

        mainPanel.add(BorderLayout.NORTH, supportPanel);
        mainPanel.add(BorderLayout.CENTER, numPanel);
        mainPanel.add(BorderLayout.WEST, engineerPanel);
        mainPanel.add(BorderLayout.EAST, eastPanel);

        add(BorderLayout.NORTH, display);
        add(BorderLayout.CENTER, mainPanel);
    }

    public void setResult(double result) {
        display.setText(String.valueOf(result));
    }

    public JLabel getDisplay() {
        return display;
    }
}

// удалить панель и вызвать перерисовку