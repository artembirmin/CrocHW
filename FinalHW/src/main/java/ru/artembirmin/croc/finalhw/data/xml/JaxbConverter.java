package ru.artembirmin.croc.finalhw.data.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

/**
 * Конвертер объектов в XML.
 */
public class JaxbConverter {

    /**
     * XML маппер.
     */
    private final XmlMapper mapper;

    public JaxbConverter() {
        mapper = new XmlMapper();
        mapper.registerModule(new JaxbAnnotationModule()); // модуль jaxb
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // форматирование вывода
        mapper.setDefaultUseWrapper(false);
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Сериализует объект в xml.
     *
     * @param data объект
     * @return xml
     */
    public String toXml(Object data) throws JsonProcessingException {
        return mapper.writeValueAsString(data);
    }
}