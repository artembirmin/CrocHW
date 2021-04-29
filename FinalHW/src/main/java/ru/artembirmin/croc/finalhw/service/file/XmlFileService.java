package ru.artembirmin.croc.finalhw.service.file;

import java.util.List;

/**
 * Интерфейс сервиса для работы с конвертацией списков объектов в XML, записью и чтением XML из файла.
 *
 * @param <T> тип объекта
 */
public interface XmlFileService<T> {
    /**
     * Конвертирует переданный список объектов в XML.
     *
     * @param objects список объектов
     * @return XML, иначе "", в случае ошибки конвертации
     */
    String convertToXml(List<T> objects);

    /**
     * Записывает XML в файл.
     *
     * @param xml XML
     */
    void writeXmlToFile(String xml);

    /**
     * Читает XML из файла.
     *
     * @return XML
     */
    String readXmlFromFile();
}
