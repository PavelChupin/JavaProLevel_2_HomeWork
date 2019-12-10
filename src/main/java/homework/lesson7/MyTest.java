package homework.lesson7;

import homework.lesson7.annotation.AfterSuite;
import homework.lesson7.annotation.BeforeSuite;
import homework.lesson7.annotation.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MyTest {
    private static List<Method> methodsTest = new ArrayList<>();
    private static Method methodBefor;
    private static Method methodAfter;

    public static void start(Class testClass) throws RuntimeException, InvocationTargetException, InstantiationException, IllegalAccessException {
        initParams(testClass);
        //Запускаем тесты
        startTest(testClass);
    }

    private static void startTest(Class testClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        //Создадим объект тестируемого класса
        //Constructor constructor = testClass.getConstructor();
        Object o = testClass.newInstance();

        if (methodBefor != null) {
            methodBefor.setAccessible(true);
            methodBefor.invoke(o);
        }

        //Сортируем по приоритетам
        Collections.sort(methodsTest, new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority();
            }
        });

        for (Method m : methodsTest) {
            m.setAccessible(true);
            m.invoke(o);
        }

        if (methodAfter != null) {
            methodAfter.setAccessible(true);
            methodAfter.invoke(o);
        }
    }

    private static void initParams(Class testClass) throws RuntimeException {
        //Получаем методы класса
        Method[] methods = testClass.getDeclaredMethods();
        int countMethodBefor = 0;
        int countMethodAfter = 0;

        //Проходим по методам и собираем какие методы будет выполнять
        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterSuite.class)) {
                methodAfter = method;
                countMethodAfter++;
            } else if (method.isAnnotationPresent(BeforeSuite.class)) {
                methodBefor = method;
                countMethodBefor++;
            } else if (method.isAnnotationPresent(Test.class)) {
                //Проверяем приоритет
                int priority = method.getAnnotation(Test.class).priority();
                if (priority < 1 || priority > 10) {
                    throw new RuntimeException("Method " + method.getName() + " помеченный аннотацией "
                            + Test.class.getSimpleName() + " в класе " + testClass.getSimpleName() + " имеет не корректный приоритет "
                            + priority + ". Приоритет должен быть от 1 до 10");
                }
                methodsTest.add(method);
            }


            if (countMethodAfter > 1) {
                throw new RuntimeException("Method помеченных аннотацией " + AfterSuite.class.getSimpleName()
                        + " в класе " + testClass.getSimpleName() + " более одного");
            }

            if (countMethodBefor > 1) {
                throw new RuntimeException("Method помеченных аннотацией " + BeforeSuite.class.getSimpleName()
                        + " в класе " + testClass.getSimpleName() + " более одного");
            }
        }
    }

}
