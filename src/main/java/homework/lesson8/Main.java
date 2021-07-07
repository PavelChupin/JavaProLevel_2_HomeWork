package homework.lesson8;

public class Main {
    public static final int X = 1;
    public static final int Y = 3;

    public static void main(String[] args) {
        new ArrOutput().outPutArr(2, 3);
        new ArrOutput().outPutArr(4, 4);
        new ArrOutput().outPutArr(3, 1);
        new ArrOutput().outPutArr(3, 3);
    }
}

class ArrOutput {
    public void outPutArr(int x, int y) {
        int[][] arr = new int[x][y];
        int c = 1;
        int count = 1;
        int y1 = 0, x1 = 0;

        arr[0][0] = c;
        for (int t = 0; t < x * y; t++) {
            if (count == 1) {
                if (x1 == x - 1 || arr[x1 + 1][y1] != 0) {
                    count = 2;
                } else {
                    x1++;c++;
                }
            }

            if (count == 2) {
                if (y1 == y - 1 || arr[x1][y1 + 1] != 0) {
                    count = 3;
                } else {
                    y1++;c++;
                }
            }

            if (count == 3) {
                if (x1 == 0 || arr[x1 - 1][y1] != 0) {
                    count = 4;
                } else {
                    x1--;c++;
                }
            }

            if (count == 4) {
                if (y1 == 0 || arr[x1][y1 - 1] != 0) {
                    count = 1;
                } else {
                    y1--;c++;
                }
            }

            arr[x1][y1] = c;
        }
        print(arr,x,y);
        System.out.println();
    }

    private void print(int[][] arr,int x, int y) {
        String s = "";
        int O = new Integer(x*y).toString().length();

        for (int i = 0; i < O ; i++) {
            s +="0";
        }

        String result = "";
        for (int i = 0; i < arr[0].length; i++) {
            for (int j = 0; j < arr.length; j++) {
                result = s + arr[j][i];
                System.out.print(result.substring(result.length() - (s.length() + 1)) + " ");
            }
            System.out.println();
        }
    }
}
