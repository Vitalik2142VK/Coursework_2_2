package pro.sky.coursework2_2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.coursework2_2.domain.Question;
import pro.sky.coursework2_2.exceptions.NotEnoughQuestionsInCollectionException;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    private ExaminerService service;

    @BeforeEach
    public void setUp() {
        service = new ExaminerServiceImpl(questionService);
    }

    @Test
    public void getQuestions() {
        Mockito.when(questionService.getAll()).thenReturn(
                new HashSet<>(Set.of(
                        new Question("Question_Java_1", "Answer_Java_1"),
                        new Question("Question_Java_2", "Answer_Java_2"),
                        new Question("Question_Java_3", "Answer_Java_3")
                )));
        Mockito.when(questionService.getRandomQuestion()).thenReturn(
                new Question("Question_Java_1", "Answer_Java_1"),
                new Question("Question_Java_2", "Answer_Java_2")
        );

        List<Question> expected = new ArrayList<>(service.getQuestions(2));

        List<Question> actual = new ArrayList<>(List.of(
                new Question("Question_Java_1", "Answer_Java_1"),
                new Question("Question_Java_2", "Answer_Java_2")
        ));

        assertTrue(actual.containsAll(expected));
    }

    @Test
    public void getQuestionsExceptions() {
        assertThrows(NotEnoughQuestionsInCollectionException.class, () -> {
            service.getQuestions(3);
        });
    }
}
