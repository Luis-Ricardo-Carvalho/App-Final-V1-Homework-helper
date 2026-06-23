package br.edu.ifsulminas.mch.homeworkhelper.model;

import androidx.annotation.NonNull;
import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private String name;
    private String description;
    private String dateSubmission;
    private boolean active;
    private int subjectId;
    private String calendarEventId;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDateSubmission() { return dateSubmission; }
    public void setDateSubmission(String dateSubmission) { this.dateSubmission = dateSubmission; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }

    public String getCalendarEventId() { return calendarEventId; }
    public void setCalendarEventId(String calendarEventId) { this.calendarEventId = calendarEventId; }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
