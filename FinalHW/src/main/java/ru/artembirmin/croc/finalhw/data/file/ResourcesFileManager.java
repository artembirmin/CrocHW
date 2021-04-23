package ru.artembirmin.croc.finalhw.data.file;

import java.io.File;
import java.io.IOException;

public class ResourcesFileManager {

    /**
     * Текущий файл, с которым осуществялется взаимодействие.
     */
    private File currentFile;

    /**
     * Записывает данные в файл.
     */
    private final FileWriter fileWriter;

    /**
     * Читает данные с файла.
     */
    private final FileReader fileReader;

    /**
     * @param file файл
     */
    public ResourcesFileManager(File file) {
        currentFile = file;
        fileWriter = new FileWriter();
        fileReader = new FileReader();
    }

    public void writeToResources(String str) throws IOException {
        fileWriter.write(str, currentFile);
    }

    public String readStringFromFile() throws IOException {
        return fileReader.readString(currentFile);
    }

    public File getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(File currentFile) {
        this.currentFile = currentFile;
    }
}
