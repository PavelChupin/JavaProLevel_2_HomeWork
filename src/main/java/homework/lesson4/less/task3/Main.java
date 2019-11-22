package homework.lesson4.less.task3;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MFU mfu = new MFU();
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            int finalI = i;
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    mfu.print(finalI,new Document("doc" + finalI,5));
                    mfu.scan(finalI,new Document("doc" + finalI,5));
                }
            }));
        }

        threads.forEach((a)->{
            a.start();
        });
    }
}
