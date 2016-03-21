package ru.levelp;

/**
 * Created by natalie on 20.03.16.
 */
public class Main {

    public static void main(String[] args) {

        CalcModel model = new CalcModel();
        new View(model).setVisible(true);

    }
}
