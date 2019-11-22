package homework.lesson4.less.task3;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        MFU mfu = new MFU();
        //List<Thread> threads = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 1; i < 4; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    mfu.print(finalI,new Document("doc" + finalI,5));
                    mfu.scan(finalI,new Document("doc" + finalI,5));
                }
            });
            /*threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    mfu.print(finalI,new Document("doc" + finalI,5));
                    mfu.scan(finalI,new Document("doc" + finalI,5));
                }
            }));*/
        }

        /*threads.forEach((a)->{
            a.start();
        });*/
    }
}
