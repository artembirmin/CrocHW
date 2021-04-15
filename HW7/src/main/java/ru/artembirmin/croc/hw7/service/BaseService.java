package ru.artembirmin.croc.hw7.service;

import ru.artembirmin.croc.hw7.model.Joke;

import java.util.List;

public interface BaseService<T> {

    /**
     * Поиск объекта по заданному id.
     *
     * @param id идентификатор объекта
     * @return найденный объект
     */
    T findById(int id);

    /**
     * Возвращает все объекты из базы.
     *
     * @return список всех объектов
     */
    List<T> findAll();

    /**
     * Сохраняет объект в базе.
     * Он должен быть добавлен в базу до сохранения.
     *
     * @param obj сохраняемый объект
     * @return сохраненый объект
     */
    T save(T obj);

    /**
     * Сохраняет объекты в базе.
     * Они должны быть добавлены в базу до сохранения.
     *
     * @param objects список сохраняемых объектов
     * @return список сохраненных объектов
     */
    List<T> saveAll(List<T> objects);

    /**
     * Удаляет передаваеммый объект из таблицы.
     *
     * @param obj удаляемый объект
     */
    void delete(T obj);

    /**
     * Удаляет объект из таблицы по id.
     *
     * @param id идентификатор объекта
     */
    void delete(int id);

    /**
     * Удаляет все содержимое базы.
     */
    void deleteAll();

    /**
     * Добавляет в базу новый объект.
     *
     * @param obj добавляемый объект
     * @return добавленный объект
     */
    Joke createNew(T obj);

    /**
     * Добавялет все переданные объекты.
     *
     * @param objects добавляемый список объектов
     * @return добавляенный список объектов
     */
    List<T> createNewAll(List<T> objects);
}
