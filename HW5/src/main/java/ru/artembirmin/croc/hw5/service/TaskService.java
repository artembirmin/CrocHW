package ru.artembirmin.croc.hw5.service;

import ru.artembirmin.croc.hw5.model.Executor;
import ru.artembirmin.croc.hw5.model.Task;
import ru.artembirmin.croc.hw5.model.exceptions.TaskNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.prefs.Preferences;

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
     * Текущий файл с задачами.
     */
    private File currentFile;
    /**
     * Текущий исполнитель задач.
     */
    private Executor executor;
    /**
     * Идентификатор последней задачи. Производится сквозная нумерация всех задач.
     */
    private long lastId = 0;

    /**
     * @param executor    текущий исполнтель задач
     * @param currentFile текущий файл с задачами
     */
    public TaskService(Executor executor, File currentFile) {
        Preferences preferences = Preferences.userNodeForPackage(TaskService.class);
        lastId = preferences.getLong("lastId", 0);
        this.executor = executor;
        this.currentFile = currentFile;
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
     * @throws IOException            если файл не найден или какие-то доругие проблемы с файлом
     * @throws ClassNotFoundException в файле другой объект
     */
    public boolean appendTask(Task task) throws IOException, ClassNotFoundException {
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
     * @throws IOException            если файл не найден или какие-то доругие проблемы с файлом
     * @throws ClassNotFoundException в файле другой объект
     */
    public boolean replaceTask(Task newTask, long id) throws IOException, ClassNotFoundException {
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
     * @throws IOException            если файл не найден или какие-то доругие проблемы с файлом
     * @throws ClassNotFoundException в файле другой объект
     * @throws TaskNotFoundException  задача не найдена в файле
     */
    public boolean remove(long id) throws IOException, ClassNotFoundException, TaskNotFoundException {
        List<Task> tasks = taskFileReader.readAllTasksFromFile(currentFile);
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
        throw new TaskNotFoundException("Task not found");
    }

    /**
     * Читает и возвращает все задачи из {@link TaskService#currentFile}
     *
     * @return список всех задач, если файл найден и чтение завершилось без ошибок, иначе null
     * @throws IOException            если файл не найден или какие-то доругие проблемы с файлом
     * @throws ClassNotFoundException в файле другой объект
     */
    public List<Task> readAllTasks() throws IOException, ClassNotFoundException {
        return taskFileReader.readAllTasksFromFile(currentFile);
    }

    /**
     * Находит и возвращает задачу по id.
     *
     * @param id идентификатор, по которому производится поиск
     * @return найденная задача или {@code null}, если задача с таким id не найдена
     * @throws IOException            если файл не найден или какие-то доругие проблемы с файлом
     * @throws ClassNotFoundException в файле другой объект
     * @throws TaskNotFoundException  задача не найдена в файле
     */
    public Task findById(long id) throws IOException, ClassNotFoundException, TaskNotFoundException {
        return taskFileReader.findById(currentFile, id);
    }

    /**
     * Берет id последнего элемента и добавляет 1.
     *
     * @return id для нового элемента
     */
    public long generateId() {
        return lastId++;
    }

    public Executor getExecutor() {
        return executor;
    }

    /**
     * Сохданяет данные, необходимые для последующей работы.
     */
    public void save() {
        Preferences preferences = Preferences.userNodeForPackage(TaskService.class);
        preferences.putLong("lastId", lastId);
    }

    public boolean clear() throws IOException {
        lastId = 0;
        return taskFileWriter.clear(currentFile);
    }
}
