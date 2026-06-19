package br.edu.ifsulminas.mch.homeworkhelper.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String name;
    private String description;
    private String dateSubmission;
    private boolean active;
<<<<<<< HEAD
    private int subjectId;
=======
    private Discipline discipline;
>>>>>>> 55ee532b95ca6608b680e507b8aaf7e9b16443cb

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateSubmission() {
        return dateSubmission;
    }
    public void setDateSubmission(String dateSubmission) {
        this.dateSubmission = dateSubmission;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

<<<<<<< HEAD
    public int getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
=======
    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
>>>>>>> 55ee532b95ca6608b680e507b8aaf7e9b16443cb
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}