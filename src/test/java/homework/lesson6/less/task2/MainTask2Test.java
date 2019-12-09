package homework.lesson6.less.task2;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainTask2Test {
    private static MainTask2 mainTask2;

    @BeforeClass
    public static void init() {
        mainTask2 = new MainTask2();
    }

    @Test
    public void test1() {
        int[] arr = {1, 2, 4, 4, 2, 3, 4, 1, 7};
        int[] i = {1, 7};
        Assert.assertArrayEquals(i, mainTask2.arr(arr));
    }

    @Test
    public void test2() {
        int[] arr = {1, 2, 4, 4, 2, 3, 4, 1, 4};
        int[] i = {};
        Assert.assertArrayEquals(i, mainTask2.arr(arr));
    }

    @Test
    public void test3() {
        int[] arr = {4, 2, 4, 4, 2, 3, 0, 1, 8};
        int[] i = {2, 3, 0, 1, 8};
        Assert.assertArrayEquals(i, mainTask2.arr(arr));
    }

    @Test(expected = RuntimeException.class)
    public void test4() {
        int[] arr = {8, 2, 5, 2, 2, 3, 0, 1, 8};
        int[] i = {};
        Assert.assertArrayEquals(i, mainTask2.arr(arr));
    }
}