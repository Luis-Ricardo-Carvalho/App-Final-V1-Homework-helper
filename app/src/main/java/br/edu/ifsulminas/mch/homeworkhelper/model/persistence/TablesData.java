package br.edu.ifsulminas.mch.homeworkhelper.model.persistence;

class TablesData {
    class Tasks{
        static final String NAME = "tasks";
        static final String PK = "id";
        static final String DESC = "description";
        static final String ACTIVE = "active";
        static final String CREATE_SQL =
                " CREATE TABLE IF NOT EXISTS " + NAME + " ( " +
                        " " + PK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        " " + DESC + " TEXT, " +
                        " " + ACTIVE + " VARCHAR(1) ); ";
    }
}
