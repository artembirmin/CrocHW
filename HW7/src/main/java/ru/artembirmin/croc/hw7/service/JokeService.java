package ru.artembirmin.croc.hw7.service;

import ru.artembirmin.croc.hw7.model.Joke;
import ru.artembirmin.croc.hw7.repository.JokeRepository;

import java.util.List;

public class JokeService implements BaseService<Joke> {
    private final JokeRepository repository;

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
        return repository.save(joke);
    }

    @Override
    public List<Joke> saveAll(List<Joke> jokes) {
        return repository.saveAll(jokes);
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
            joke.setWordsCount(findWordsCount(joke.getText()));
        }
        return repository.createNew(joke);
    }

    private int findWordsCount(String str) {
        int count = 0;
        String[] strings = str.split("\\s|\\n ");
        for (String string : strings) {
            if ((Character.isLetterOrDigit(string.charAt(0))
                    || (string.length() > 1 && Character.isLetterOrDigit(string.charAt(1))))) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<Joke> createNewAll(List<Joke> jokes) {
        for (Joke joke : jokes) {
            createNew(joke);
        }
        return jokes;
    }
}
