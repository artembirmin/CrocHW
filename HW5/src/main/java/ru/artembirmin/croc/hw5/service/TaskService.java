package ru.artembirmin.croc.hw5.service;

import ru.artembirmin.croc.hw5.model.Executor;
import ru.artembirmin.croc.hw5.model.Task;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Производит базовые операции над файлом задач.
 */
public class TaskService {

    /**
     * Производит операции записи задач в файл.
     */
    private final TaskFileWriter taskFileWriter = new TaskFileWriter();
    /**
     * Производит операции считываения задач из файла.
     */
    private final TaskFileReader taskFileReader = new TaskFileReader();
    /**
     * Считывает метаданные задач из консоли.
     */
    private TaskMetadataConsoleReader taskMetadataConsoleReader = new TaskMetadataConsoleReaderImpl();
    /**
     * Текущий файл с задачами.
     */
    private File currentFile;
    /**
     * Текущий исполнитель задач.
     */
    private Executor executor;

    /**
     * @param executor    текущий исполнтель задач
     * @param currentFile текущий файл с задачами
     */
    public TaskService(Executor executor, File currentFile) {
        this.executor = executor;
        this.currentFile = currentFile;
    }

    /**
     * Вспомогательный конструктор для тестирования.
     *
     * @param executor                  текущий исполнтель задач
     * @param currentFile               текущий файл с задачами
     * @param taskMetadataConsoleReader считыватель метаданных задач из консоли.
     */
    public TaskService(Executor executor, File currentFile, TaskMetadataConsoleReader taskMetadataConsoleReader) {
        this.executor = executor;
        this.currentFile = currentFile;
        this.taskMetadataConsoleReader = taskMetadataConsoleReader;
    }

    /**
     * Меняет текущий файл с задачами.
     *
     * @param newCurrentFile новый файл с задачами
     */
    public void changeFile(File newCurrentFile) {
        currentFile = newCurrentFile;
    }

    /**
     * Меняет текущего исполнителя задач.
     *
     * @param newExecutor новый текущий исполнитель задач
     */
    public void changeExecutor(Executor newExecutor) {
        executor = newExecutor;
    }

    /**
     * Добавляет задачу в конец файла.
     *
     * @param task добавляемая задача.
     * @return {@code true}, если задача добавлена
     */
    public boolean appendTask(Task task) {
        List<Task> tasks = taskFileReader.readAllTasksFromFile(currentFile);
        taskFileWriter.clear(currentFile);
        tasks.add(task);
        return taskFileWriter.setTasks(currentFile, tasks);
    }

    /**
     * Заменяет задачу по заданному id на новую задачу.
     *
     * @param newTask новая задача
     * @param id      идентификатор задачи, на место которой встанет новая
     * @return {@code true}, если задача с таким id найдена в файле
     */
    public boolean replaceTask(Task newTask, long id) {
        List<Task> tasks = taskFileReader.readAllTasksFromFile(currentFile);
        taskFileWriter.clear(currentFile);
        Iterator<Task> taskIterator = tasks.iterator();
        int i = 0;
        while (taskIterator.hasNext()) {
            if (taskIterator.next().getId() == id) {
                break;
            }
            i++;
        }
        try {
            tasks.set(i, newTask);
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
        return taskFileWriter.setTasks(currentFile, tasks);
    }

    /**
     * Удаляет запись по заданному иднетификатору.
     *
     * @param id идентификатор
     * @return {@code true}, если задача с таким id найдена с удалена
     */
    public boolean remove(long id) {
        List<Task> tasks = taskFileReader.readAllTasksFromFile(currentFile);
        // Если файл не найден или в файле другой тип данных.
        if (tasks == null) {
            return false;
        }
        for (Task task : tasks) {
            // Ищем задачу по заданному id.
            if (task.getId() == id) {
                tasks.remove(task);
                // Если очистить файл не удалось.
                if (!taskFileWriter.clear(currentFile)) {
                    return false;
                }
                return taskFileWriter.setTasks(currentFile, tasks);
            }
        }
        // Если задача по такому id не найдена.
        return false;
    }

    /**
     * Читает и возвращает все задачи из {@link TaskService#currentFile}
     *
     * @return список всех задач, если файл найден и чтение завершилось без ошибок, иначе null
     */
    public List<Task> readAllTasks() {
        return taskFileReader.readAllTasksFromFile(currentFile);
    }

    /**
     * Находит и возвращает задачу по id.
     *
     * @param id идентификатор, по которому производится поиск
     * @return найденная задача или {@code null}, если задача с таким id не найдена
     */
    public Task findById(long id) {
        return taskFileReader.findById(currentFile, id);
    }

    public TaskMetadataConsoleReader getTaskMetadataConsoleReader() {
        return taskMetadataConsoleReader;
    }

    /**
     * //TODO🎂
     * Берет id последнего элемента и добавляет 1.
     *
     * @return
     */
    public long generateId() {
        return (long) (Math.random() * 1000);
    }

    public Executor getExecutor() {
        return executor;
    }

}
