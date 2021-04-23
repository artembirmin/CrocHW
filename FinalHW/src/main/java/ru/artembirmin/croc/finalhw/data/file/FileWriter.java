package ru.artembirmin.croc.finalhw.data.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Класс для работы с записью строк в файл.
 */
public class FileWriter {

    /**
     * Записывает переданную строку в файл.
     *
     * @param str строка
     * @param file файл, в который будет осуществляться запись строки
     * @throws IOException ошибка при открытии, записи, закрытии файла
     */
    public void write(String str, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
        writer.write(str, 0, str.length() - 1);
        writer.close();
    }
}
