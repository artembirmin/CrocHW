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

    /**
     * Записывает строку в файл.
     *
     * @param str записывыемая строка
     * @throws IOException оишбка при взаимодействии с файлом
     */
    public void writeStringToFile(String str) throws IOException {
        fileWriter.write(str, currentFile);
    }

    /**
     * Считывает содержимое файла в виде строки.
     *
     * @return считанная строка
     * @throws IOException оишбка при взаимодействии с файлом
     */
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
