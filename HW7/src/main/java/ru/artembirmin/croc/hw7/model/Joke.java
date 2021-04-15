package ru.artembirmin.croc.hw7.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Штука, анекдот. Не обязательно смешные.
 */
public class Joke {
    /**
     * Идентификатор.
     */
    private Integer id;
    /**
     * Текст шутки.
     */
    private String text;
    /**
     * Количество слов в шутке.
     */
    private Integer wordsCount = 0;
    /**
     * Смешная ли шутка.
     */
    private Boolean isFunny;
    /**
     * Дата создания шутки.
     */
    private LocalDate creationDate;
    /**
     * Ресурс, где найдена шутка.
     */
    private String source;

    /**
     * @param id идентификатор
     * @param text текст шутки
     * @param wordsCount количество слов
     * @param isFunny смешная ли
     * @param creationDate дата осздания
     * @param source ресурс
     */
    public Joke(int id, String text, int wordsCount, boolean isFunny, LocalDate creationDate, String source) {
        this.id = id;
        this.text = text;
        this.wordsCount = wordsCount;
        this.isFunny = isFunny;
        this.creationDate = creationDate;
        this.source = source;
    }
    /**
     * @param text текст шутки
     * @param wordsCount количество слов
     * @param isFunny смешная ли
     * @param creationDate дата осздания
     * @param source ресурс
     */
    public Joke(String text, int wordsCount, boolean isFunny, LocalDate creationDate, String source) {
        this.text = text;
        this.wordsCount = wordsCount;
        this.isFunny = isFunny;
        this.creationDate = creationDate;
        this.source = source;
    }
    /**
     * @param text текст шутки
     * @param isFunny смешная ли
     * @param creationDate дата осздания
     * @param source ресурс
     */
    public Joke(String text, Boolean isFunny, LocalDate creationDate, String source) {
        this.text = text;
        this.isFunny = isFunny;
        this.creationDate = creationDate;
        this.source = source;
    }

    public String getText() {
        return text;
    }

    /**
     * Изменяет текст шуткию. При зименении текста количество слов обнуляется.
     * @param text новый текст
     */
    public void setText(String text) {
        this.text = text;
        wordsCount = 0;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    public boolean isFunny() {
        return isFunny;
    }

    public void setFunny(boolean funny) {
        isFunny = funny;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joke)) return false;
        Joke joke = (Joke) o;
        return Objects.equals(getId(), joke.getId()) &&
                Objects.equals(getText(), joke.getText()) &&
                Objects.equals(getWordsCount(), joke.getWordsCount()) &&
                Objects.equals(isFunny, joke.isFunny) &&
                Objects.equals(getCreationDate(), joke.getCreationDate()) &&
                Objects.equals(getSource(), joke.getSource());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getWordsCount(), isFunny, getCreationDate(), getSource());
    }

    @Override
    public String toString() {
        return "\nJoke{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", wordsCount=" + wordsCount +
                ", isFunny=" + isFunny +
                ", creationDate=" + creationDate +
                ", source='" + source + '\'' +
                "}";
    }
}
