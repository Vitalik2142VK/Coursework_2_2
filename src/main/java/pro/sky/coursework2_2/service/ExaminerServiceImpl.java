package pro.sky.coursework2_2.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pro.sky.coursework2_2.domain.Question;
import pro.sky.coursework2_2.exceptions.NotEnoughQuestionsInCollectionException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private Random random;
    private final QuestionService javaQuestionService;
    private final QuestionService mathQuestionService;

    public ExaminerServiceImpl(@Qualifier("javaQuestionService") QuestionService javaQuestionService,
                               @Qualifier("mathQuestionService") QuestionService mathQuestionService) {
        this.javaQuestionService = javaQuestionService;
        this.mathQuestionService = mathQuestionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        final int SIZE = javaQuestionService.getAll().size() + mathQuestionService.getAll().size();
        if (SIZE < amount) throw new NotEnoughQuestionsInCollectionException("Запрашиваемых вопросов больше, чем есть в коллекциях");
        if (SIZE == amount) return javaQuestionService.getAll();

        List<Question> result = new ArrayList<>();
        Question question;
        random = new Random();
        int randomNum;

        while (result.size() < amount) {
            randomNum = random.nextInt();

            if (randomNum % 2 == 0) {
                question = javaQuestionService.getRandomQuestion();
            } else {
                question = mathQuestionService.getRandomQuestion();
            }

            if (!result.contains(question))
                result.add(question);
        }

        return result;
    }
}
