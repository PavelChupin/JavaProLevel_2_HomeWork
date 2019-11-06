package homework.lesson1;

import homework.lesson1.objects.Apple;
import homework.lesson1.objects.Box;
import homework.lesson1.objects.Orange;

public class Main {
    public static void main(String[] args) {

        //Task 1,2
        Integer[] integers = {1,2,3,4,5};
        ClassArray<Integer> classArray = new ClassArray<>(integers);
        classArray.changePosition();

        System.out.println(classArray.arrayToArrayList());
        //End Task 1,2

        //Task3
        Box<Orange> boxOrange = new Box<>();
        boxOrange.addToBox(new Orange(1.0f));
        boxOrange.addToBox(new Orange(1.5f));

        Box<Apple> boxApple = new Box<>();
        boxApple.addToBox(new Apple(1.0f));
        boxApple.addToBox(new Apple(1.5f));

        System.out.println(boxApple.compare(boxOrange));

        boxApple.addToBox(new Apple(1.5f));
        System.out.println(boxApple.compare(boxOrange));
        //Пересыпаем фрукты
        boxApple.changeBox(boxOrange);
        System.out.println("BoxApple = " + boxApple.getFruits());
        System.out.println("BoxOrange = " + boxOrange.getFruits());

        //End Task3
    }
}
