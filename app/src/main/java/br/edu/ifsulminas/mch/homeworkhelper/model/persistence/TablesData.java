package br.edu.ifsulminas.mch.homeworkhelper.model.persistence;

class TablesData {

    static class Subject {
        static final String NAME = "subject";
        static final String PK = "id";
        static final String SUBJECT_NAME = "name";
        static final String TEACHER = "teacher";
        static final String SCHOOL_YEAR = "school_year";

        static final String CREATE_SQL =
                "CREATE TABLE IF NOT EXISTS " + NAME + " ( " +
                        PK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        SUBJECT_NAME + " VARCHAR(100), " +
                        TEACHER + " VARCHAR(100), " +
                        SCHOOL_YEAR + " DATE );";
    }

    static class Tasks {
        static final String NAME = "tasks";
        static final String PK = "id";
        static final String TASK_NAME = "name";
        static final String DESC = "description";
        static final String DATE_SUBMISSION = "date_submission";
        static final String ACTIVE = "active";
        static final String SUBJECT_ID = "subject_id";

        static final String CREATE_SQL =
                "CREATE TABLE IF NOT EXISTS " + NAME + " ( " +
                        PK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        TASK_NAME + " VARCHAR(100), " +
                        DESC + " VARCHAR(100), " +
                        DATE_SUBMISSION + " DATE, " +
                        ACTIVE + " VARCHAR(1), " +
                        SUBJECT_ID + " INTEGER NOT NULL, " +
                        "FOREIGN KEY (" + SUBJECT_ID + ") REFERENCES " +
                        Subject.NAME + "(" + Subject.PK + ") );";
    }
}