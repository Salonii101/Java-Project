package org.example.models;

public class Quiz {

        private int id;
        private int subjectId;  // FK to Subject
        private String title;

        public Quiz() {}
        public Quiz(int id, int subjectId, String title) {
            this.id = id;
            this.subjectId = subjectId;
            this.title = title;
        }

        // getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
