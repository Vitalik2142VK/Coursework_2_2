package pro.sky.coursework2_2.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.coursework2_2.domain.Question;
import pro.sky.coursework2_2.exceptions.NotFindException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MathQuestionRepositoryTest {
    private Question question;

    Set<Question> questions;

    private QuestionRepository repository;

    @BeforeEach
    public void setUp() {
        question = new Question("question", "answer");

        questions = new HashSet<>(Set.of(
                new Question("Math_Question_1", "Math_answer_1"),
                new Question("Math_Question_2", "Math_answer_2"),
                new Question("Math_Question_3", "Math_answer_3")
        ));

        repository = new MathQuestionRepository(questions);
    }


    @Test
    public void addQuestion() {
        assertEquals(question, repository.add(question));
        assertThrows(NullPointerException.class, () -> {
            repository.add(null);
        });
    }

    @Test
    public void remove() {
        Question removeQuestion = new Question("Math_Question_1", "Math_answer_1");
        assertEquals(removeQuestion, repository.remove(removeQuestion));
        assertThrows(NullPointerException.class, () -> {
            repository.remove(null);
        });
        assertThrows(NotFindException.class, () -> {
            repository.remove(new Question("que", "ans"));
        });
    }

    @Test
    public void removeWorks() {
        Question removeQuestion = new Question("Math_Question_1", "Math_answer_1");

        repository.remove(removeQuestion);

        Set<Question> actual = new HashSet<>(Set.of(
                new Question("Math_Question_2", "Math_answer_2"),
                new Question("Math_Question_3", "Math_answer_3")
        ));

        assertEquals(repository.getAll(), actual);
    }

    @Test
    public void getAll() {
        Set<Question> actual = new HashSet<>(Set.of(
                new Question("Math_Question_1", "Math_answer_1"),
                new Question("Math_Question_2", "Math_answer_2"),
                new Question("Math_Question_3", "Math_answer_3")
        ));

        assertEquals(repository.getAll(), actual);
    }
}
