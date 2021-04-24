package ru.artembirmin.croc.finalhw.data.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileReader {

    /**
     * Считывет все строки из файла.
     *
     * @param file файл, из которого будет считываение
     * @return считанные строки
     */
    public String readString(File file) throws IOException {
        return Files.readString(file.toPath());
    }
}
