package br.edu.ifsulminas.mch.homeworkhelper.model.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsulminas.mch.homeworkhelper.model.Task;

public class TaskDAO extends DAO {

    public TaskDAO(Context context) {
        super(context);
    }

    public void save(Task task) {
        SQLiteDatabase db = openToWrite();

        ContentValues values = new ContentValues();
        values.put(TablesData.Tasks.TASK_NAME, task.getName());
        values.put(TablesData.Tasks.DESC, task.getDescription());
        values.put(TablesData.Tasks.DATE_SUBMISSION, task.getDateSubmission());
        values.put(TablesData.Tasks.ACTIVE, task.isActive() ? "1" : "0");
        values.put(TablesData.Tasks.SUBJECT_ID, task.getSubjectId());
        values.put(TablesData.Tasks.CALENDAR_EVENT_ID, task.getCalendarEventId());

        db.insert(TablesData.Tasks.NAME, null, values);
        db.close();
    }

    public void update(Task task) {
        SQLiteDatabase db = openToWrite();

        ContentValues values = new ContentValues();
        values.put(TablesData.Tasks.TASK_NAME, task.getName());
        values.put(TablesData.Tasks.DESC, task.getDescription());
        values.put(TablesData.Tasks.DATE_SUBMISSION, task.getDateSubmission());
        values.put(TablesData.Tasks.ACTIVE, task.isActive() ? "1" : "0");
        values.put(TablesData.Tasks.SUBJECT_ID, task.getSubjectId());
        values.put(TablesData.Tasks.CALENDAR_EVENT_ID, task.getCalendarEventId());

        String[] params = {String.valueOf(task.getId())};
        db.update(TablesData.Tasks.NAME, values, TablesData.Tasks.PK + "= ?", params);
        db.close();
    }

    public void delete(Task task) {
        SQLiteDatabase db = openToWrite();
        String[] params = {String.valueOf(task.getId())};
        db.delete(TablesData.Tasks.NAME, TablesData.Tasks.PK + "= ?", params);
        db.close();
    }

    public List<Task> listAll() {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = openToRead();
        String sql = "SELECT * FROM " + TablesData.Tasks.NAME +
                " ORDER BY " + TablesData.Tasks.PK + ";";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getInt(cursor.getColumnIndexOrThrow(TablesData.Tasks.PK)));
            task.setName(cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.TASK_NAME)));
            task.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.DESC)));
            task.setDateSubmission(cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.DATE_SUBMISSION)));
            task.setActive("1".equals(cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.ACTIVE))));
            task.setSubjectId(cursor.getInt(cursor.getColumnIndexOrThrow(TablesData.Tasks.SUBJECT_ID)));
            task.setCalendarEventId(cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.CALENDAR_EVENT_ID)));
            tasks.add(task);
        }

        cursor.close();
        db.close();
        return tasks;
    }

    public List<Task> listAllBySubject(int subjectIdFilter) {
        List<Task> tasks = new ArrayList<>();
        SQLiteDatabase db = openToRead();

        String sql = "SELECT * FROM " + TablesData.Tasks.NAME +
                " WHERE " + TablesData.Tasks.SUBJECT_ID + " = ?" +
                " ORDER BY " + TablesData.Tasks.PK + ";";
        String[] params = {String.valueOf(subjectIdFilter)};
        Cursor cursor = db.rawQuery(sql, params);

        while (cursor.moveToNext()) {
            Task task = new Task();
            task.setId(cursor.getInt(cursor.getColumnIndexOrThrow(TablesData.Tasks.PK)));
            task.setName(cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.TASK_NAME)));
            task.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.DESC)));
            task.setDateSubmission(cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.DATE_SUBMISSION)));
            task.setActive("1".equals(cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.ACTIVE))));
            task.setSubjectId(cursor.getInt(cursor.getColumnIndexOrThrow(TablesData.Tasks.SUBJECT_ID)));
            task.setCalendarEventId(cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.CALENDAR_EVENT_ID)));
            tasks.add(task);
        }

        cursor.close();
        db.close();
        return tasks;
    }
}
