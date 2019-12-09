package homework.lesson6.less.task3;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainTask3Test {
    private static MainTask3 mainTask3;

    @BeforeClass
    public static void init(){
        mainTask3 = new MainTask3();
    }

    @Test
    public void test1() {
        int[] arr = {1,1,1};
        Assert.assertFalse(mainTask3.checkArr(arr));
    }

    @Test
    public void test2() {
        int[] arr = {4,4,4};
        Assert.assertFalse(mainTask3.checkArr(arr));
    }

    @Test
    public void test3() {
        int[] arr = {4, 1, 4, 1};
        Assert.assertTrue(mainTask3.checkArr(arr));
    }

    @Test
    public void test4() {
        int[] arr = {1,4,1,4,5,4,1};
        try{
        Assert.assertTrue(mainTask3.checkArr(arr));
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}