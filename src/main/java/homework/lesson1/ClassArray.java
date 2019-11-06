package homework.lesson1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ClassArray<T> {
    private T[] arr;

    public ClassArray(T... array){
        this.arr = array;
    }

    public void changePosition(){
        T temp = null;
        /*for (int i = 0;i +1< arr.length;i++){
            temp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = temp;
        }*/
        int indexFrom;
        int indexTo;
        Random r = new Random();
        indexFrom = r.nextInt(arr.length);
        indexTo = r.nextInt(arr.length);

        temp = arr[indexFrom];
        arr[indexFrom] = arr[indexTo];
        arr[indexTo] = temp;
    }

    public List<T> arrayToArrayList(){
        List<T> arrayToArrayList = new ArrayList<T>();
        arrayToArrayList.addAll(Arrays.asList(arr));
        return arrayToArrayList;
    }
}
