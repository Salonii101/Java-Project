package org.example.controller;

import org.example.models.Result;
import org.example.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/result") // Base path for all result-related endpoints
public class ResultController {

    @Autowired
    private ResultService resultService;

    /**
     * Endpoint to get a list of all results.
     * @return A list of all results.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Result>> getAllResults() {
        List<Result> results = resultService.getAllResults();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    /**
     * Endpoint to get a single result by its ID.
     * @param id The ID of the result to retrieve.
     * @return The result object or a 404 Not Found error.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable String id) {
        Result result = resultService.findResultById(id);
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to find all results for a specific user.
     * @param userId The ID of the user.
     * @return A list of results for that user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Result>> getResultsByUserId(@PathVariable String userId) {
        List<Result> results = resultService.getResultsByUser(userId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    /**
     * Endpoint to find all results for a specific quiz.
     * @param quizId The ID of the quiz.
     * @return A list of results for that quiz.
     */
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Result>> getResultsByQuizId(@PathVariable Integer quizId) {
        List<Result> results = resultService.getResultsByQuiz(quizId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    /**
     * Endpoint to add a new result.
     * @param result The result object to create.
     * @return The newly created result with a 201 Created status.
     */
    @PostMapping("/add")
    public ResponseEntity<Result> addResult(@RequestBody Result result) {
        Result newResult = resultService.saveUpdateResult(result);
        return new ResponseEntity<>(newResult, HttpStatus.CREATED);
    }

    /**
     * Endpoint to update an existing result.
     * @param id The ID of the result to update.
     * @param resultDetails The new details for the result.
     * @return The updated result object or 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable String id, @RequestBody Result resultDetails) {
        if (resultService.findResultById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    // Use the correct setter from Result entity
    resultDetails.setId(java.util.UUID.fromString(id));
        Result updatedResult = resultService.saveUpdateResult(resultDetails);
        return new ResponseEntity<>(updatedResult, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a result by its ID.
     * @param id The ID of the result to delete.
     * @return A success message or 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResult(@PathVariable String id) {
        Result result = resultService.findResultById(id);
        if (result == null) {
            return new ResponseEntity<>("Result not found", HttpStatus.NOT_FOUND);
        }
        resultService.deleteResult(result);
        return new ResponseEntity<>("Result deleted successfully", HttpStatus.OK);
    }
}
