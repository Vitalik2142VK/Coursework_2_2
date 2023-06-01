package pro.sky.coursework2_2.repository;

import org.springframework.stereotype.Repository;
import pro.sky.coursework2_2.domain.Question;
import pro.sky.coursework2_2.exceptions.NotFindException;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Set;

@Repository
public class JavaQuestionRepository implements QuestionRepository{
    private final Set<Question> questions;

    public JavaQuestionRepository(Set<Question> questions) {
        this.questions = questions;
    }

    @PostConstruct
    public void init() {
        for (int i = 1; i <= 5; i++) {
            questions.add(new Question("Java_Question_" + i, "Java_answer_"  + i));
        }
    }

    @Override
    public Question add(Question question) {
        if (question == null) throw new NullPointerException();
        questions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (question == null) throw new NullPointerException();
        if (!questions.contains(question)) throw new NotFindException();
        questions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }
}
