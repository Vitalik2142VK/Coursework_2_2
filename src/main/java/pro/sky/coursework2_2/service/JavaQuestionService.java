package pro.sky.coursework2_2.service;

import org.springframework.stereotype.Service;
import pro.sky.coursework2_2.domain.Question;
import pro.sky.coursework2_2.exceptions.NotFindException;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService{
    private final Set<Question> questions = new HashSet<>(Set.of(
            new Question("Question_Java_1", "Answer_Java_1"),
            new Question("Question_Java_2", "Answer_Java_2"),
            new Question("Question_Java_3", "Answer_Java_3"),
            new Question("Question_Java_4", "Answer_Java_4"),
            new Question("Question_Java_5", "Answer_Java_5")
    ));

    @Override
    public Question add(String question, String answer) {
        if (question == null || answer == null) throw new NullPointerException();
        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        return newQuestion;
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

    @Override
    public Question getRandomQuestion() {
        Random random = new Random();
        int iter = random.nextInt(questions.size());
        return new ArrayList<>(questions).get(iter);
    }
}
