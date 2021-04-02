package ru.artembirmin.croc.hw5.menu;

import ru.artembirmin.croc.hw5.model.Status;
import ru.artembirmin.croc.hw5.model.command.Command;

import java.util.Arrays;
import java.util.List;

/**
 * Генерирует меню для взаимодействия в консоли.
 */
public class Menu {

    /**
     * Меню для выбора статуса.
     */
    private String statusesMenu;

    /**
     * Создает меню для выбора команд на основе массива команд.
     *
     * @param commands команды импользуемые для
     * @return готовое меню
     */
    public String createMainMenu(List<Command> commands) {
        StringBuilder mainMenu = new StringBuilder("\n" + "\n");
        for (Command command : commands) {
            mainMenu.append(command.getCode()).append(" - ").append(command.getTranscription()).append("    ");
        }
        char[] chars = new char[mainMenu.length()];
        Arrays.fill(chars, '_');
        //System.out.println(chars);
        mainMenu.insert(1, chars);
        return mainMenu.toString();
    }

    /**
     * Создает меню для выбора статуса.
     *
     * @return готовое меню
     */
    public String createStatusesMenu() {
        if (statusesMenu == null) {
            StringBuilder statusesMenu = new StringBuilder();
            Status[] statuses = Status.values();
            for (int i = 0; i < statuses.length; i++) {
                statusesMenu.append(i + 1).append(" - ").append(statuses[i]).append("   ");
            }
            this.statusesMenu = statusesMenu.toString();
        }
        return statusesMenu;
    }

    /**
     * Создает меню для выбора изменяемого состояния задачи и выхода из редактирования.
     *
     * @return готовое меню
     */
    public String createEditMenu() {
        return "1 - name    2 - description    3 - status   other - exit";
    }
}
