package ru.artembirmin.croc.hw7.service;

import ru.artembirmin.croc.hw7.model.Joke;
import ru.artembirmin.croc.hw7.repository.JokeRepository;

import java.util.List;

public class JokeService implements BaseService<Joke> {

    /**
     * Репозиторий.
     */
    private final JokeRepository repository;

    /**
     * @param jokeRepository репозиторий
     */
    public JokeService(JokeRepository jokeRepository) {
        repository = jokeRepository;
    }

    @Override
    public Joke findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<Joke> findAll() {
        return repository.findAll();
    }

    @Override
    public Joke save(Joke joke) {
        if (joke.getWordsCount() == 0) {
            joke.setWordsCount(countNumberOfWords(joke.getText()));
        }
        return repository.save(joke);
    }

    @Override
    public List<Joke> saveAll(List<Joke> jokes) {
        for (Joke joke : jokes) {
            save(joke);
        }
        return jokes;
    }

    @Override
    public void delete(Joke joke) {
        repository.delete(joke);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public Joke createNew(Joke joke) {
        if (joke.getWordsCount() == 0) {
            joke.setWordsCount(countNumberOfWords(joke.getText()));
        }
        return repository.createNew(joke);
    }

    @Override
    public List<Joke> createNewAll(List<Joke> jokes) {
        for (Joke joke : jokes) {
            createNew(joke);
        }
        return jokes;
    }

    /**
     * Подсчитывает количество слов в тексте.
     *
     * @param text текст, в котором ведется подсчет слов
     * @return количество слов
     */
    private int countNumberOfWords(String text) {
        int count = 0;
        String[] words = text.split("\\s|\\n ");
        for (String word : words) {
            if ((Character.isLetterOrDigit(word.charAt(0))
                    || (word.length() > 1 && Character.isLetterOrDigit(word.charAt(1))))) {
                count++;
            }
        }
        return count;
    }
}
