package br.edu.ifsulminas.mch.homeworkhelper.model.persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifsulminas.mch.homeworkhelper.model.Subject;

public class SubjectDAO extends DAO {

    public SubjectDAO(Context context) {
        super(context);
    }

    public void save(Subject subject) {
        SQLiteDatabase db = openToWrite();

        ContentValues values = new ContentValues();
        values.put(TablesData.Subject.SUBJECT_NAME, subject.getName());
        values.put(TablesData.Subject.TEACHER, subject.getTeacher());
        values.put(TablesData.Subject.SCHOOL_YEAR, subject.getSchoolYear());

        db.insert(TablesData.Subject.NAME, null, values);
        db.close();
    }

    public void update(Subject subject) {
        SQLiteDatabase db = openToWrite();

        ContentValues values = new ContentValues();
        values.put(TablesData.Subject.SUBJECT_NAME, subject.getName());
        values.put(TablesData.Subject.TEACHER, subject.getTeacher());
        values.put(TablesData.Subject.SCHOOL_YEAR, subject.getSchoolYear());

        String[] params = {String.valueOf(subject.getId())};
        db.update(TablesData.Subject.NAME, values, TablesData.Subject.PK + "= ?", params);
        db.close();
    }

    public void delete(Subject subject) {
        SQLiteDatabase db = openToWrite();

        String[] params = {String.valueOf(subject.getId())};
        db.delete(TablesData.Subject.NAME, TablesData.Subject.PK + "= ?", params);
        db.close();
    }

    public List<Subject> listAll() {
        List<Subject> subjects = new ArrayList<>();

        SQLiteDatabase db = openToRead();
        String sql = "SELECT * FROM " + TablesData.Subject.NAME +
                " ORDER BY " + TablesData.Subject.PK + ";";

        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(TablesData.Subject.PK));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Subject.SUBJECT_NAME));
            String teacher = cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Subject.TEACHER));
            String schoolYear = cursor.getString(cursor.getColumnIndexOrThrow(TablesData.Subject.SCHOOL_YEAR));

            Subject subject = new Subject();
            subject.setId(id);
            subject.setName(name);
            subject.setTeacher(teacher);
            subject.setSchoolYear(schoolYear);

            subjects.add(subject);
        }

        cursor.close();
        db.close();
        return subjects;
    }
}