package ru.artembirmin.croc.finalhw.repository.file;

import java.io.File;
import java.io.IOException;

/**
 * Интерфейс репозитория для записи и чтения строк из файла.
 * Подразумевается, что работа в основном происходит с одним файлом.
 */
public interface FileRepository {
    /**
     * Записывает сроку с файл.
     *
     * @param str записываемая строка
     * @throws IOException ошибка при поиске, записи в файл
     */
    void writeToFile(String str) throws IOException;

    /**
     * Читает все строки из файла.
     *
     * @return строковое содержимое файла
     * @throws IOException ошибка при поиске, чтении файла
     */
    String readFromFile() throws IOException;

    File getFile();
}
