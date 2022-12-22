package utils;

import java.io.File;

public class FileUtils {
    public boolean isDownloaded(String expectedFileName){
        File directory = new File("/Users/techtorialacademy/Downloads");
        File[] dirFiles = directory.listFiles();
        for (int i=0;i< dirFiles.length;i++){
            if (dirFiles[i].getName().equals(expectedFileName)){
                dirFiles[i].delete();
                return true;
            }
        }
        return false;
    }
}
