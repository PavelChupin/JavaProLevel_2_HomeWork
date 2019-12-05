package homework.lesson7;

import homework.lesson7.tests.TestClass1;
import homework.lesson7.tests.TestClass2;
import homework.lesson7.tests.TestClass3;
import homework.lesson7.tests.TestClass4;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) {
        try {
            //Тест 1
            Class test1 = TestClass1.class;
            MyTest.start(test1);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        //Тест 2
        try {
            Class test2 = TestClass2.class;
            MyTest.start(test2);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        //Тест 3
        try {
            Class test3 = TestClass3.class;
            MyTest.start(test3);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        //Тест 4
        try {
            Class test4 = TestClass4.class;
            MyTest.start(test4);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
