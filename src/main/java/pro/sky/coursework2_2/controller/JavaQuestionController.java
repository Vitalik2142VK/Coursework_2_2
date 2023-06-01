package pro.sky.coursework2_2.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pro.sky.coursework2_2.domain.Question;
import pro.sky.coursework2_2.service.QuestionService;

import java.util.Collection;

@RestController
@RequestMapping("/java")
public class JavaQuestionController {
    private final QuestionService service;

    public JavaQuestionController(@Qualifier("javaQuestionService") QuestionService service) {
        this.service = service;
    }

    @GetMapping(path = "/add")
    public Question add(@RequestParam String question,@RequestParam String answer) {
        return service.add(question,answer);
    }

    @GetMapping(path = "/remove")
    public Question remove(@RequestParam String question,@RequestParam String answer) {
        return service.remove(new Question(question, answer));
    }

    @GetMapping
    public Collection<Question> getQuestions() {
        return service.getAll();
    }
}
