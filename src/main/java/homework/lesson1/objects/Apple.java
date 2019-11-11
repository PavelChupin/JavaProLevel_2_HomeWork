package homework.lesson1.objects;

public class Apple<T extends Number> extends Fruit<T>{

    public Apple(T weight) {
        super(weight);
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + this.getWeight() +
                '}';
    }
}
