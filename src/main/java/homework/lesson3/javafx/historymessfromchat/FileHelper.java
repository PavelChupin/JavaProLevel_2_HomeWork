package homework.lesson3.javafx.historymessfromchat;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class FileHelper {
    private static final String FILE_HISTORY_PATH = "file.history.path";
    private File fileHistory;

    public FileHelper(String login){
        if(!checkPathToSave()){
            createPathToSave();
        }
        this.fileHistory = new File(getProperty(FILE_HISTORY_PATH) + "/" + login + ".txt");

        //Если файл не сушествует физически, надо его создать
        if(!fileHistory.exists()){
            try {
                fileHistory.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeToFileHistory(String message){
        if (message == null || message.isEmpty()){
            return;
        }

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.fileHistory,true))){
            bufferedWriter.write( message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Прнеобразовываем сообщение в массив байт
        /*
        message +="\n";
        byte[] mess = message.getBytes();

        try(FileOutputStream fileOutputStream = new FileOutputStream(this.fileHistory,true)){
            fileOutputStream.write(mess);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public List<String> readFromFileHistory(){
        List<String> mess = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileHistory))){
            String text;
            while ((text = bufferedReader.readLine()) != null){
                mess.add(text);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mess;
    }

    public static void createPathToSave() {
        File file = new File(getProperty(FILE_HISTORY_PATH));
        file.mkdirs();
    }

    public static boolean checkPathToSave() {
        File file = new File(getProperty(FILE_HISTORY_PATH));
        return file.exists();
    }

    private static String getProperty(String propertyKey){
        Properties prop = new Properties();
        String value = null;
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/application.properties")){
            prop.load(fileInputStream);
            value = prop.getProperty(propertyKey);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

}
