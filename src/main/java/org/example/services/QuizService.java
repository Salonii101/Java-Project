//package org.example.services;
//
//import org.example.dao.QuizDAO;
//import org.example.models.Quiz;
//
//import java.util.List;
//
//public class QuizService {
//
//    private final QuizDAO quizDAO;
//
//    public QuizService(QuizDAO quizDAO) {
//        this.quizDAO = quizDAO;
//    }
//
//    /**
//     * Save a new quiz.
//     */
//    public void saveQuiz(Quiz quiz) {
//        quizDAO.save(quiz);
//    }
//
//    /**
//     * Update an existing quiz.
//     */
//    public void updateQuiz(Quiz quiz) {
//        quizDAO.update(quiz);
//    }
//
//    /**
//     * Delete a quiz.
//     */
//    public void deleteQuiz(Quiz quiz) {
//        quizDAO.delete(quiz);
//    }
//
//    /**
//     * Find a quiz by its ID.
//     */
//    public Quiz findQuizById(int id) {
//        return quizDAO.findById(id);
//    }
//
//    /**
//     * Get all quizzes.
//     */
//    public List<Quiz> findAllQuizzes() {
//        return quizDAO.findAll();
//    }
//
//    /**
//     * Get quizzes filtered by subject ID.
//     * This assumes you either implement this in DAO or filter it here.
//     */
//    public List<Quiz> findQuizzesBySubject(int subjectId) {
//        // Get all quizzes from the DAO
//        List<Quiz> allQuizzes = quizDAO.findAll();
//
//        // Filter by subjectId
//        return allQuizzes.stream()
//                .filter(quiz -> quiz.getSubject() != null && quiz.getSubject().getId() == subjectId)
//                .toList();
//    }
//
//}
