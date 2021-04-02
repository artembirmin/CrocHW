package ru.artembirmin.croc.hw5;

import ru.artembirmin.croc.hw5.menu.Menu;
import ru.artembirmin.croc.hw5.model.Executor;
import ru.artembirmin.croc.hw5.model.command.*;
import ru.artembirmin.croc.hw5.service.TaskService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class App {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Command> commands = new ArrayList<>();
        commands.add(new AddTask("1", "add task"));
        commands.add(new EditTask("2", "edit task"));
        commands.add(new DeleteTask("3", "delete task"));
        commands.add(new PrintAllTasks("4", "print tasks"));
        commands.add(new SaveAndExit("5", "exit"));
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);

        String mainMenu = menu.createMainMenu(commands);
        Executor executor = new Executor("artembirmin", "Artem", "Petrosyan");
        TaskService taskService = new TaskService(executor, new File("D:\\tasks2.txt"));

        while (true) {
            System.out.println(mainMenu);
            String commandCode = scanner.next();
            Boolean isExecuted = null;
            for (Command command : commands) {
                if (command.getCode().equals(commandCode)) {
                    isExecuted = command.execute(taskService);
                }
            }
            if (isExecuted == null) {
                System.out.println("Command not found");
            } else if (!isExecuted) {
                System.out.println("Failure");
            } else {
                System.out.println("Success");
            }
        }

    }
}