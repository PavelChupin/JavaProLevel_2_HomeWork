package homework.lesson4.less.task2;

import javax.swing.plaf.TableHeaderUI;

public class Main {
    public static void main(String[] args) {
        FileHelper fileHelper = new FileHelper();
        Thread t1 = new Thread(new FileThread(fileHelper, "Thread 1"));
        Thread t2 = new Thread(new FileThread(fileHelper, "Thread 2"));
        Thread t3 = new Thread(new FileThread(fileHelper, "Thread 3"));

        t1.start();
        t2.start();
        t3.start();
    }
}
