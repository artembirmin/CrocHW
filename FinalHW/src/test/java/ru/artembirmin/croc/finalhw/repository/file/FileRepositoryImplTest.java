package ru.artembirmin.croc.finalhw.repository.file;

import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.finalhw.BaseFlightTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileRepositoryImplTest extends BaseFlightTest {

    /**
     * Репозиторий по работе с файлами.
     */
    protected final FileRepositoryImpl fileRepository = new FileRepositoryImpl(
            file
    );

    public FileRepositoryImplTest() throws IOException {
    }

    @Test
    void writeToFile() throws IOException {
        fileRepository.writeToFile("String");
    }

    @Test
    void readFromFile() throws IOException {
        assertEquals("String", fileRepository.readFromFile());
    }

    @Test
    void getFile() {
        assertEquals(file, fileRepository.getFile());
    }
}