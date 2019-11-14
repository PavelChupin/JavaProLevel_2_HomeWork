package homework.lesson1.objects;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {

    List<T> fruits = new ArrayList<>();

    public void addToBox(T fruit){
        this.fruits.add(fruit);
    }

    private double getWeight(){
        double weight = 0;
        for (T fruit: fruits) {
            weight+=fruit.getWeight().doubleValue();
        }
        return weight;
    }

    public boolean compare(Box<?> box){
        return this.getWeight() == box.getWeight();
    }

    public void changeBox(Box<T> box){
        List<T> tempFruit = new ArrayList<>(this.fruits);
        this.fruits.clear();
        this.fruits.addAll(box.getFruits());
        box.setFruits(tempFruit);
    }

    public List<T> getFruits() {
        return fruits;
    }

    public void setFruits(List<T> fruits) {
        this.fruits = fruits;
    }
}
