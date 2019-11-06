package homework.lesson1.objects;

public class Orange<T extends Number> extends Fruit<T>{

    public Orange(T weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Orange{" +
                "weight=" + weight +
                '}';
    }
}
