package ru.artembirmin.croc.hw6.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw6.service.JaxbConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

    import static org.junit.jupiter.api.Assertions.*;

class ZooTest {


    /**
     * Конвертация класса Zoo в xml.
     */
    @Test
    public void testConvertZooToXml() throws Exception {
        final Zoo zoo = new Zoo();
        zoo.setTitle("Название!");
        zoo.setManager("Василий");
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Лев", Arrays.asList("Шерстяной", "Зубастый")));
        animals.add(new Animal("Заяй", Arrays.asList("Шерстяной", "Белый")));
        zoo.setAnimals(animals);

        final JaxbConverter converter = new JaxbConverter();
        final String xml = converter.toXml(zoo);
        System.out.println(xml);

        assertEquals(zoo, converter.fromXml(xml, Zoo.class));
    }
}