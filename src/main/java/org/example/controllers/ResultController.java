package org.example.controllers;

import org.example.models.Result;
import org.example.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/result") // Base path for all result-related endpoints
public class ResultController {

    @Autowired
    private ResultService resultService;

    // ===== Get all results =====
    @GetMapping("/all")
    public ResponseEntity<List<Result>> getAllResults() {
        List<Result> results = resultService.getAllResults();
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    // ===== Get result by UUID =====
    @GetMapping("/{id}")
    public ResponseEntity<Result> getResultById(@PathVariable String id) {
        Result result = resultService.findResultById(UUID.fromString(id));
        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ===== Get results by user UUID =====
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Result>> getResultsByUserId(@PathVariable String userId) {
        List<Result> results = resultService.getResultsByUser(userId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    // ===== Get results by quiz ID =====
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Result>> getResultsByQuizId(@PathVariable Integer quizId) {
        List<Result> results = resultService.getResultsByQuiz(quizId);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    // ===== Add a new result =====
    @PostMapping("/add")
    public ResponseEntity<Result> addResult(@RequestBody Result result) {
        Result newResult = resultService.saveUpdateResult(result);
        return new ResponseEntity<>(newResult, HttpStatus.CREATED);
    }

    // ===== Update existing result =====
    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable String id, @RequestBody Result resultDetails) {
        Result existingResult = resultService.findResultById(UUID.fromString(id));
        if (existingResult == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Set the UUID to ensure the entity updates instead of creating new
        resultDetails.setId(UUID.fromString(id));
        Result updatedResult = resultService.saveUpdateResult(resultDetails);
        return new ResponseEntity<>(updatedResult, HttpStatus.OK);
    }

    // ===== Delete result by UUID =====
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResult(@PathVariable String id) {
        Result result = resultService.findResultById(UUID.fromString(id));
        if (result == null) {
            return new ResponseEntity<>("Result not found", HttpStatus.NOT_FOUND);
        }
        resultService.deleteResult(result);
        return new ResponseEntity<>("Result deleted successfully", HttpStatus.OK);
    }
}
