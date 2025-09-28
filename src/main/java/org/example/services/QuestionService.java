package org.example.services;

import org.example.repository.QuestionRepository;
import org.example.models.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository  ;

    public Question createOrUpdateQuestion(Question question) {
        return questionRepository.save(question);
    }

    public void deleteQuestion(int id) {
        questionRepository.deleteById(id);
    }

    public Question getQuestionById(int id) {
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public List<Question> getQuestionsByQuiz(int quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    public List<Question> getQuestionsBySubject(int subjectId) {
        return questionRepository.findBySubjectId(subjectId);
    }

}
