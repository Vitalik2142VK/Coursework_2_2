package pro.sky.coursework2_2.service;

import org.springframework.stereotype.Service;
import pro.sky.coursework2_2.domain.Question;
import pro.sky.coursework2_2.exceptions.NotEnoughQuestionsInCollectionException;

import java.util.*;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private Random random;
    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        final int SIZE = questionService.getAll().size();
        if (SIZE < amount) throw new NotEnoughQuestionsInCollectionException("Запрашиваемых вопросов больше, чем есть в коллекции");
        if (SIZE == amount) return questionService.getAll();

        List<Question> result = new ArrayList<>();
        Question question;
        while (result.size() < amount) {
            question = questionService.getRandomQuestion();
            if (!result.contains(question))
                result.add(question);
        }
//        for (int i = 0; i < amount; i++) {
//            Question question = questionService.getRandomQuestion();
//            if (result.contains(question)) {
//                i--;
//            } else {
//                result.add(question);
//            }
//        }
        return result;
    }
}
