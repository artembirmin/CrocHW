package ru.artembirmin.croc.hw5.service;

import ru.artembirmin.croc.hw5.App;
import ru.artembirmin.croc.hw5.menu.Menu;
import ru.artembirmin.croc.hw5.model.Status;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Основная реализация {@link TaskMetadataConsoleReader}.
 */
public class TaskMetadataConsoleReaderImpl implements TaskMetadataConsoleReader {

    /**
     * Отображение введенного числа в статус задачи.
     */
    private final Map<Integer, Status> statusesMap;
    /**
     * Сканнер для ввода данных.
     */
    private final Scanner scanner = new Scanner(System.in);
    /**
     * Класс создающий меню.
     */
    private final Menu menu = new Menu();

    /**
     * Флаг вызова {@link TaskMetadataConsoleReaderImpl#readName()}.
     * Если вызовов еще не было, то {@code true}.
     * Используется для костыля, ибо во время второго и последующих запусков после
     * ввода оставался enter от предыдущего запуска. Его считывал сканнер и ввод названия пропускался.
     * Исправить это в {@link App} не получилось.
     */
    private boolean isFirstReadNameLaunch = true;

    public TaskMetadataConsoleReaderImpl() {
        statusesMap = createStatusesMap();
    }

    @Override
    public String readName() {
        if(!isFirstReadNameLaunch){
            scanner.nextLine();
        }
        isFirstReadNameLaunch = false;
        System.out.println("Input task's name: ");
        return scanner.nextLine();
    }

    @Override
    public String readDescription() {

        System.out.println("Input task's description: ");
        return scanner.nextLine();
    }

    @Override
    public Status readStatus() {
        System.out.println("Input task's status: " + menu.createStatusesMenu());
        return statusesMap.get(scanner.nextInt());
    }

    /**
     * Создает отображение для определния статуса задачи по номеру.
     * Вспмогательный метод для {@link TaskMetadataConsoleReaderImpl#readStatus()}
     *
     * @return отображение для определния статуса задачи по номеру.
     */
    private Map<Integer, Status> createStatusesMap() {
        Map<Integer, Status> statusesMap = new HashMap<>();
        Status[] statuses = Status.values();
        for (int i = 0; i < statuses.length; i++) {
            statusesMap.put(i + 1, statuses[i]);
        }
        return statusesMap;
    }


    @Override
    public long readId() {
        System.out.println("Input task's id: ");
        return scanner.nextLong();
    }


    @Override
    public String readFieldNumberForEdit() {
        System.out.println(menu.createEditMenu());
        return scanner.next();
    }
}
