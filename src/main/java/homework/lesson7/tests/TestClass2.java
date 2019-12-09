package homework.lesson7.tests;

import homework.lesson7.annotation.AfterSuite;
import homework.lesson7.annotation.BeforeSuite;
import homework.lesson7.annotation.Test;

public class TestClass2 {
    @BeforeSuite
    public void init(){
        System.out.println("Класс TestClass1 выполнен метод помеченный BeforeSuite");
    }

    @BeforeSuite
    public void init1(){
        System.out.println("Класс TestClass1 выполнен метод помеченный BeforeSuite");
    }

    @Test(priority = 3)
    public void test1(){
        System.out.println("Выполнен тест1");
    }

    @Test
    public void test2(){
        System.out.println("Выполнен тест2");
    }

    @Test(priority = 2)
    public void test3(){
        System.out.println("Выполнен тест3");
    }

    @AfterSuite
    public void end(){
        System.out.println("Класс TestClass1 выполнен метод помеченный AfterSuite");
    }

}
