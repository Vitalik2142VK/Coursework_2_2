package pro.sky.coursework2_2.service;

import pro.sky.coursework2_2.domain.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}
