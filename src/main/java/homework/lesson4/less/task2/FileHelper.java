package homework.lesson4.less.task2;

import java.io.*;

public class FileHelper {

    public synchronized void saveTextTofile(String text) {
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter("c:/Temp/text.txt", true))) {
            fileWriter.write(text + "\n");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
