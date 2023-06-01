package pro.sky.coursework2_2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import pro.sky.coursework2_2.domain.Question;
import pro.sky.coursework2_2.exceptions.NotFindException;
import pro.sky.coursework2_2.repository.QuestionRepository;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {
    private Question question;

    @Mock
    @Qualifier("mathQuestionRepository")
    private QuestionRepository repository;

    private QuestionService service;

    @BeforeEach
    public void setUp() {
        question = new Question("question", "answer");

        service = new MathQuestionService(repository);
    }

    @Test
    public void add() {
        Mockito.when(repository.add(question)).thenReturn(new Question("question", "answer"));
        assertEquals(question, service.add("question", "answer"));
        assertThrows(NullPointerException.class, () -> {
            service.add(null, "null");
        });
        assertThrows(NullPointerException.class, () -> {
            service.add("null", null);
        });
    }

    @Test
    public void addQuestion() {
        Mockito.when(repository.add(question)).thenReturn(new Question("question", "answer"));
        assertEquals(question, service.add(question));

        Mockito.when(repository.add(null)).thenThrow(NullPointerException.class);
        assertThrows(NullPointerException.class, () -> {
            service.add(null);
        });
    }

    @Test
    public void remove() {
        Mockito.when(repository.add(question)).thenReturn(new Question("question", "answer"));
        service.add(question);

        Question removeQuestion = new Question("question", "answer");

        Mockito.when(repository.remove(removeQuestion)).thenReturn(new Question("question", "answer"));
        assertEquals(removeQuestion, service.remove(removeQuestion));

        Mockito.when(repository.remove(null)).thenThrow(NullPointerException.class);
        assertThrows(NullPointerException.class, () -> {
            service.remove(null);
        });

        Mockito.when(repository.remove(new Question("que", "ans"))).thenThrow(NotFindException.class);
        assertThrows(NotFindException.class, () -> {
            service.remove(new Question("que", "ans"));
        });
    }

    @Test
    public void getAll() {
        Set<Question> actual = new HashSet<>(Set.of(
                new Question("Question_Java_1", "Answer_Java_1"),
                new Question("Question_Java_2", "Answer_Java_2"),
                new Question("Question_Java_3", "Answer_Java_3")
        ));

        Mockito.when(repository.getAll()).thenReturn(
                new HashSet<>(Set.of(
                        new Question("Question_Java_1", "Answer_Java_1"),
                        new Question("Question_Java_2", "Answer_Java_2"),
                        new Question("Question_Java_3", "Answer_Java_3")
                )));

        assertEquals(service.getAll(), actual);
    }

    @Test
    public void getRandomQuestion() {
        Mockito.when(repository.getAll()).thenReturn(
                new HashSet<>(Set.of(
                        new Question("Question_Java_1", "Answer_Java_1"),
                        new Question("Question_Java_2", "Answer_Java_2"),
                        new Question("Question_Java_3", "Answer_Java_3")
                )));

        Question randomQuestion = service.getRandomQuestion();
        assertTrue(service.getAll().contains(randomQuestion));
    }
}
