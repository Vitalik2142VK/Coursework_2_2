package pro.sky.coursework2_2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.coursework2_2.domain.Question;
import pro.sky.coursework2_2.repository.QuestionRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

@Service
public class MathQuestionService implements QuestionService{
    public final QuestionRepository questions;

    public MathQuestionService(@Qualifier("mathQuestionRepository") QuestionRepository questions) {
        this.questions = questions;
    }

    @Override
    public Question add(String question, String answer) {
        if (question == null || answer == null) throw new NullPointerException();
        Question newQuestion = new Question(question, answer);
        return questions.add(newQuestion);
    }

    @Override
    public Question add(Question question) {
        return questions.add(question);
    }

    @Override
    public Question remove(Question question) {
        return questions.remove(question);
    }

    @Override
    public Collection<Question> getAll() {
        return questions.getAll();
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        Collection<Question> q = questions.getAll();
        int iter = random.nextInt(q.size());
        return new ArrayList<>(q).get(iter);
    }
}
