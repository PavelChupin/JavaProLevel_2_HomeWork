package homework.lesson1.objects;

public abstract class Fruit<T extends Number> {
    private T weight;

    public T getWeight() {
        return weight;
    }

    protected Fruit(T weight){
        this.weight = weight;
    }
}
