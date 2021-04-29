package ru.artembirmin.croc.finalhw.repository.file;

import ru.artembirmin.croc.finalhw.data.file.ResourcesFileManager;

import java.io.File;
import java.io.IOException;

/**
 * Реализация репозитория для записи и чтения строк из файла.
 */
public class FileRepositoryImpl implements FileRepository {

    /**
     * Менеджер файлов в ресурсах.
     */
    private final ResourcesFileManager resourcesFileManager;

    /**
     * @param file файл, с котороым будет происходить работа
     */
    public FileRepositoryImpl(File file) {
        resourcesFileManager = new ResourcesFileManager(file);
    }

    @Override
    public void writeToFile(String str) throws IOException {
        resourcesFileManager.writeStringToFile(str);
    }

    @Override
    public String readFromFile() throws IOException {
        return resourcesFileManager.readStringFromFile();
    }

    @Override
    public File getFile() {
        return resourcesFileManager.getCurrentFile();
    }
}
