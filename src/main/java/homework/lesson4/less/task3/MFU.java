package homework.lesson4.less.task3;

public class MFU {
    private Object printLock = new Object();
    private Object scanLock = new Object();

    private int print = 1;
    private int scan = 1;

    public void print(int number, Document document) {
        synchronized (printLock) {
            try {
                while (print != number) {
                    printLock.wait();
                }

                for (int i = 1; i <= document.getPagecount(); i++) {
                    System.out.println(document.getName() + ". Отпечатана " + i + " страница");
                    Thread.sleep(50);
                }
                print++;
                printLock.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void scan(int number, Document document) {
        synchronized (scanLock) {
            try {
                while (scan != number) {
                    scanLock.wait();
                }

                for (int i = 1; i <= document.getPagecount(); i++) {
                    System.out.println(document.getName() + ". Отсканирована " + i + " страница");
                    Thread.sleep(50);
                }
                scan++;
                scanLock.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
