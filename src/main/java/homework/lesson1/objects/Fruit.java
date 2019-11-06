package homework.lesson1.objects;

public abstract class Fruit<T extends Number> {
    protected T weight;

    public T getWeight() {
        return weight;
    }
}
