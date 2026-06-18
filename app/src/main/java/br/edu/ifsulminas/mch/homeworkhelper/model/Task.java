package br.edu.ifsulminas.mch.homeworkhelper.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String description;
    private boolean active;
    private Discipline discipline;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @NonNull
    @Override
    public String toString() {
        return getDescription();
    }
}
