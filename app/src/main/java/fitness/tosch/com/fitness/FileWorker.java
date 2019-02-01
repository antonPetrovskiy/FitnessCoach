package fitness.tosch.com.fitness;

import android.os.Environment;

import java.io.File;

public class FileWorker {
    private File clientsFile;

    File sdPath;

    private static FileWorker instance;

    public static synchronized FileWorker getInstance() {
        if (instance == null) {
            instance = new FileWorker();
        }
        return instance;
    }

    private FileWorker(){
        createFile();
    }

    public void createFile(){
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

            return;
        }

        sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + "Alias");
        // создаем каталог
        sdPath.mkdirs();
        // формируем объект File, который содержит путь к файлу
        clientsFile = new File(sdPath, "clients");

    }


}
