package org.example;

import java.util.List;

import org.example.dao.impl.SubjectImpl;
import org.example.models.Subject;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class TestSubject {
    public static void main(String[] args) {
        // Setup Hibernate
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        cfg.addAnnotatedClass(org.example.models.Subject.class);
        cfg.addAnnotatedClass(org.example.models.User.class);
        cfg.addAnnotatedClass(org.example.models.Quiz.class);
        cfg.addAnnotatedClass(org.example.models.Question.class);
        cfg.addAnnotatedClass(org.example.models.Result.class);
        SessionFactory sessionFactory = cfg.buildSessionFactory();

        SubjectImpl subjectDao = new SubjectImpl(sessionFactory);

        // CREATE
        Subject sub = new Subject();
        sub.setName("History");
        subjectDao.save(sub);
        System.out.println("Saved Subject id = " + sub.getId());

        // READ
        Subject fetched = subjectDao.findById(sub.getId());
        System.out.println("Fetched Subject: " + fetched.getName());

        // UPDATE
        fetched.setName("World History");
        subjectDao.update(fetched);
        Subject updated = subjectDao.findById(fetched.getId());
        System.out.println("Updated Subject: " + updated.getName());

        // LIST ALL
        List<Subject> all = subjectDao.getAllSubjects();
        System.out.println("All Subjects:");
        for (Subject s : all) {
            System.out.println(" - " + s.getId() + ": " + s.getName());
        }

        // DELETE
        subjectDao.delete(updated);
        Subject afterDelete = subjectDao.findById(updated.getId());
        System.out.println("After delete, findById: " + afterDelete);

        sessionFactory.close();
    }
}
