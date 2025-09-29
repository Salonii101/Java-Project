package org.example.controllers;

import org.example.models.Question;
import org.example.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question") // Base path for all question-related endpoints
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * Endpoint to get a list of all questions.
     * @return A list of all questions.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /**
     * Endpoint to get a single question by its ID.
     * @param id The ID of the question to retrieve.
     * @return The question object or a 404 Not Found error.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Integer id) {
        Question question = questionService.getQuestionById(id);
        if (question != null) {
            return new ResponseEntity<>(question, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to find all questions associated with a specific quiz.
     * @param quizId The ID of the quiz.
     * @return A list of questions for that quiz.
     */
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getQuestionsByQuizId(@PathVariable Integer quizId) {
        List<Question> questions = questionService.getQuestionsByQuiz(quizId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /**
     * Endpoint to find all questions associated with a specific subject.
     * @param subjectId The ID of the subject.
     * @return A list of questions for that subject.
     */
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<Question>> getQuestionsBySubjectId(@PathVariable Integer subjectId) {
        List<Question> questions = questionService.getQuestionsBySubject(subjectId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    /**
     * Endpoint to add a new question.
     * @param question The question object to create.
     * @return The newly created question with a 201 Created status.
     */
    @PostMapping("/add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question newQuestion = questionService.createOrUpdateQuestion(question);
        return new ResponseEntity<>(newQuestion, HttpStatus.CREATED);
    }

    /**
     * Endpoint to update an existing question.
     * @param id The ID of the question to update.
     * @param questionDetails The new details for the question.
     * @return The updated question object or 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Integer id, @RequestBody Question questionDetails) {
        if (questionService.getQuestionById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    // No setId call needed; Question entity does not have a setId setter
        Question updatedQuestion = questionService.createOrUpdateQuestion(questionDetails);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a question by its ID.
     * @param id The ID of the question to delete.
     * @return A success message or 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id) {
        if (questionService.getQuestionById(id) == null) {
            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
        }
        questionService.deleteQuestion(id);
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
    }
}
