package org.example.controllers;

import org.example.models.Subject;
import org.example.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject") // Base path for all subject-related endpoints
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    /**
     * Endpoint to get a list of all subjects.
     * @return A list of all subjects.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    /**
     * Endpoint to get a single subject by its ID.
     * @param id The ID of the subject.
     * @return The subject object or a 404 Not Found error.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Integer id) {
        Subject subject = subjectService.findSubjectById(id);
        if (subject != null) {
            return new ResponseEntity<>(subject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint to add a new subject.
     * @param subject The subject object to create.
     * @return The newly created subject with a 201 Created status.
     */
    @PostMapping("/add")
    public ResponseEntity<Subject> addSubject(@RequestBody Subject subject) {
        Subject newSubject = subjectService.saveOrUpdateSubject(subject);
        return new ResponseEntity<>(newSubject, HttpStatus.CREATED);
    }

    /**
     * Endpoint to update an existing subject.
     * @param id The ID of the subject to update.
     * @param subjectDetails The new details for the subject.
     * @return The updated subject object or 404 Not Found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Integer id, @RequestBody Subject subjectDetails) {
        // First, check if the subject exists
        Subject existingSubject = subjectService.findSubjectById(id);
        if (existingSubject == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    // Set the ID from the path to ensure we update the correct record
    subjectDetails.setId(id); // Use the correct setter from Subject entity
        Subject updatedSubject = subjectService.saveOrUpdateSubject(subjectDetails);
        return new ResponseEntity<>(updatedSubject, HttpStatus.OK);
    }

    /**
     * Endpoint to delete a subject by its ID.
     * @param id The ID of the subject to delete.
     * @return A success message or 404 Not Found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable Integer id) {
        if (subjectService.findSubjectById(id) == null) {
            return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
        }
        subjectService.deleteSubjectById(id);
        return new ResponseEntity<>("Subject deleted successfully", HttpStatus.OK);
    }
}
