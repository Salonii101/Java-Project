package org.example.controller;

import org.example.models.Quiz;
import org.example.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz") // Base path for all quiz-related endpoints
public class QuizController {

    @Autowired
    private QuizService quizService;

    /**
     * Endpoint to get a list of all quizzes.
     * @return A list of all quizzes.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    /**
     * Endpoint to get a single quiz by its ID.
     * @param id The ID of the quiz to retrieve.
     * @return The quiz object or a 404 Not Found error.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Integer id) {
        Quiz quiz = quizService.findQuizById(id);
        if (quiz != null) {
            return new ResponseEntity<>(quiz, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to find all quizzes associated with a specific subject.
     * @param subjectId The ID of the subject.
     * @return A list of quizzes for that subject.
     */
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<Quiz>> getQuizzesBySubjectId(@PathVariable Integer subjectId) {
        List<Quiz> quizzes = quizService.getQuizzesBySubject(subjectId);
        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }

    /**
     * Endpoint to add a new quiz.
     * @param quiz The quiz object to create.
     * @return The newly created quiz with a 201 Created status.
     */
    @PostMapping("/add")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        Quiz newQuiz = quizService.saveOrUpdateQuiz(quiz);
        return new ResponseEntity<>(newQuiz, HttpStatus.CREATED);
    }

    /**
     * Endpoint to update an existing quiz.
     * @param id The ID of the quiz to update.
     * @param quizDetails The new details for the quiz.
     * @return The updated quiz object or 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Integer id, @RequestBody Quiz quizDetails) {
        if (quizService.findQuizById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    // Use the correct setter from Quiz entity
    quizDetails.setId(id);
        Quiz updatedQuiz = quizService.saveOrUpdateQuiz(quizDetails);
        return new ResponseEntity<>(updatedQuiz, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a quiz by its ID.
     * @param id The ID of the quiz to delete.
     * @return A success message or 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Integer id) {
        if (quizService.findQuizById(id) == null) {
            return new ResponseEntity<>("Quiz not found", HttpStatus.NOT_FOUND);
        }
        quizService.deleteQuizById(id);
        return new ResponseEntity<>("Quiz deleted successfully", HttpStatus.OK);
    }
}

