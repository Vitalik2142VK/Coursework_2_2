package pro.sky.coursework2_2.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.coursework2_2.domain.Question;
import pro.sky.coursework2_2.exceptions.NotFindException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JavaQuestionServiceTest {
    private Question question;

    private QuestionService service;

    @BeforeEach
    public void setUp() {
        question = new Question("question", "answer");

        service = new JavaQuestionService();
        service.remove(new Question("Question_Java_4", "Answer_Java_4"));
        service.remove(new Question("Question_Java_5", "Answer_Java_5"));

    }

    @Test
    public void add() {
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
        assertEquals(question, service.add(question));
        assertThrows(NullPointerException.class, () -> {
            service.add(null);
        });
    }

    @Test
    public void remove() {
        Question removeQuestion = new Question("Question_Java_1", "Answer_Java_1");
        assertEquals(removeQuestion, service.remove(removeQuestion));
        assertThrows(NullPointerException.class, () -> {
           service.remove(null);
        });
        assertThrows(NotFindException.class, () -> {
            service.remove(new Question("que", "ans"));
        });
    }

    @Test
    public void removeWorks() {
        Question removeQuestion = new Question("Question_Java_1", "Answer_Java_1");

        service.remove(removeQuestion);

        Set<Question> actual = new HashSet<>(Set.of(
                new Question("Question_Java_2", "Answer_Java_2"),
                new Question("Question_Java_3", "Answer_Java_3")
        ));

        assertEquals(service.getAll(), actual);
    }

    @Test
    public void getAll() {
        Set<Question> actual = new HashSet<>(Set.of(
                new Question("Question_Java_1", "Answer_Java_1"),
                new Question("Question_Java_2", "Answer_Java_2"),
                new Question("Question_Java_3", "Answer_Java_3")
        ));

        assertEquals(service.getAll(), actual);
    }

    @Test
    public void getRandomQuestion() {
        Question randomQuestion = service.getRandomQuestion();
        assertEquals(randomQuestion, service.remove(randomQuestion));
        assertEquals(service.getAll().size(), 2);
    }
}
