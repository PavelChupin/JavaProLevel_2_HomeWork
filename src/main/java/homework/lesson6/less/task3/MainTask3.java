package homework.lesson6.less.task3;

public class MainTask3 {
    private static final int ONE = 1;
    private static final int FOUR = 4;

    public boolean checkArr(int[] arr) {
        boolean checkOne = false;
        boolean checkFour = false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ONE) {
                checkOne = true;
            } else if (arr[i] == FOUR) {
                checkFour = true;
            }else{
                throw new RuntimeException("В массиве есть элементы отличные от " + ONE + " и " + FOUR);
            }

            if (checkOne &&checkFour){break;}
        }

        return checkOne && checkFour;
    }

    public static void main(String[] args) {

    }

}
