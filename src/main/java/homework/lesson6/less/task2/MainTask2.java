package homework.lesson6.less.task2;

public class MainTask2 {
    private static final int NUMBER = 4;

    public int[] arr(int[] arr) {
        int[] newArr = null;
        int index = 0;
        boolean isNumber = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == NUMBER) {
                index = i + 1;
                isNumber = true;
            }
        }

        if (isNumber) {
            newArr = new int[arr.length - index];
            System.arraycopy(arr, index, newArr, 0, newArr.length);
        } else {
            throw new RuntimeException("В массиве отсутствуют значения 4");
        }

        return newArr;
    }

    public static void main(String[] args) {
        MainTask2 mainTask2 = new MainTask2();
        int[] i = null;
        int[] y = {1, 2, 3, 4, 5, 6, 7, 8, 4, 10};
        i = mainTask2.arr(y);

        for (Integer t : i) {
            System.out.println(t + "\t");
        }
    }
}
