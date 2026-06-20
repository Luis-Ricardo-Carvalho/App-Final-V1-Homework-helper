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
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(TablesData.Tasks.PK));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.TASK_NAME));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.DESC));
            String dateSubmission = cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.DATE_SUBMISSION));
            String activeStr = cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.ACTIVE));
            boolean active = "1".equals(activeStr);
            int subjectId = cursor.getInt(cursor.getColumnIndexOrThrow(TablesData.Tasks.SUBJECT_ID));

            Task task = new Task();
            task.setId(id);
            task.setName(name);
            task.setDescription(desc);
            task.setDateSubmission(dateSubmission);
            task.setActive(active);
            task.setSubjectId(subjectId);

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
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(TablesData.Tasks.PK));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.TASK_NAME));
            String desc = cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.DESC));
            String dateSubmission = cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.DATE_SUBMISSION));
            String activeStr = cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Tasks.ACTIVE));
            boolean active = "1".equals(activeStr);
            int subjectId = cursor.getInt(cursor.getColumnIndexOrThrow(TablesData.Tasks.SUBJECT_ID));

            Task task = new Task();
            task.setId(id);
            task.setName(name);
            task.setDescription(desc);
            task.setDateSubmission(dateSubmission);
            task.setActive(active);
            task.setSubjectId(subjectId);

            tasks.add(task);
        }

        cursor.close();
        db.close();
        return tasks;
    }
}