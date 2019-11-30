package homework.lesson4.less.task2;

public class FileThread implements Runnable {
    private FileHelper fileHelper;
    private String name;

    public FileThread(FileHelper fileHelper, String name) {
        this.fileHelper = fileHelper;
        this.name = name;
    }

    @Override
    public void run() {
        for(int i = 0;i < 10; i++){
            fileHelper.saveTextTofile(name + " " + i + "\n");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
