package homework.lesson3.javafx;

import homework.lesson3.javafx.historymessfromchat.FileHelper;

public class Main {

    public static void main(String[] args) {

        FileHelper fileHelper = new FileHelper("ret");

        System.out.println(fileHelper.readFromFileHistory());

    }
}
