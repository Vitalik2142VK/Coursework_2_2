package pro.sky.coursework2_2.repository;

import pro.sky.coursework2_2.domain.Question;

import java.util.Collection;

public interface QuestionRepository {
    public Question add(Question question);

    public Question remove(Question question);

    public Collection<Question> getAll();
}
