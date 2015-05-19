package com.example.shasta.todolist.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class provider{
    private dbhelper mdbhelper;
    private SQLiteDatabase mdb;
    private final Context mCtx;

    public provider(Context ctx) {
        mCtx = ctx;
    }
    public void open() {
        mdbhelper = new dbhelper(mCtx);
        mdb = mdbhelper.getWritableDatabase();
    }
    public void close() {
        if (mdbhelper!=null) mdbhelper.close();
    }

    public Cursor getAllDataBye() {
        return mdb.query(contract.ByeEntry.TABLE_NAME, null, null, null, null, null, null); //!!!!!name_of_table
    }
    public void addRecBye(String txt, int img) {
        ContentValues cv = new ContentValues();
        cv.put(contract.ByeEntry.COLUMN_PRODUCT, txt);
        cv.put(contract.ByeEntry.COLUMN_PRIORITY, img);
        mdb.insert(contract.ByeEntry.TABLE_NAME, null, cv);}

    public Cursor getAllDataHabit() {
        return mdb.query(contract.HabitEntry.TABLE_NAME, null, null, null, null, null, null); //!!!!!name_of_table
    }
    public void addRecHabit(String txt) {
        ContentValues cv = new ContentValues();
        cv.put(contract.HabitEntry.COLUMN_HABIT , txt);
        mdb.insert(contract.HabitEntry.TABLE_NAME, null, cv);}

    public Cursor getAllDataRead() {
        return mdb.query(contract.ReadEntry.TABLE_NAME, null, null, null, null, null, null); //!!!!!name_of_table
    }
    public void addRecRead(String name, String author, String zhanr, boolean yes) {
        ContentValues cv = new ContentValues();
        cv.put(contract.ReadEntry.COLUMN_BOOK, name);
        cv.put(contract.ReadEntry.COLUMN_AUTHOR, author);
        cv.put(contract.ReadEntry.COLUMN_ZHANR , zhanr );
        cv.put(contract.ReadEntry.COLUMN_YES , yes);
        mdb.insert(contract.ReadEntry.TABLE_NAME, null, cv);}

    public Cursor getAllDataFilm() {
        return mdb.query(contract.FilmEntry.TABLE_NAME, null, null, null, null, null, null); //!!!!!name_of_table
    }
    public void addRecFilm(String name, String zhanr, boolean yes) {
        ContentValues cv = new ContentValues();
        cv.put(contract.FilmEntry.COLUMN_FILM , name);
        cv.put(contract.FilmEntry.COLUMN_ZHANR , zhanr );
        cv.put(contract.FilmEntry.COLUMN_YES , yes);
        mdb.insert(contract.FilmEntry.TABLE_NAME, null, cv);}


    public Cursor getAllDatatodo() {
        return mdb.query(contract.todoEntry.TABLE_NAME, null, null, null, null, null, null); //!!!!!name_of_table
    }
    public void addRectodo(String name, String date, boolean yes) {
        ContentValues cv = new ContentValues();
        cv.put(contract.todoEntry.COLUMN_NAME , name);
        cv.put(contract.todoEntry.COLUMN_DATETIME , date);
        cv.put(contract.todoEntry.COLUMN_YES , yes);
        mdb.insert(contract.todoEntry.TABLE_NAME, null, cv);}


    public Cursor getAllDatatoget() {
        return mdb.query(contract.togetEntry.TABLE_NAME, null, null, null, null, null, null); //!!!!!name_of_table
    }
    public void addRectoget(String name, boolean yes) {
        ContentValues cv = new ContentValues();
        cv.put(contract.togetEntry.COLUMN_GOAL , name);
        cv.put(contract.togetEntry.COLUMN_YES , yes);
        mdb.insert(contract.togetEntry.TABLE_NAME, null, cv);}


    public Cursor getAllDatapass() {
        return mdb.query(contract.passEntry.TABLE_NAME, null, null, null, null, null, null); //!!!!!name_of_table
    }
    public void addRecrass(String site, String login, String pass) {
        ContentValues cv = new ContentValues();
        cv.put(contract.passEntry.COLUMN_SITE , site);
        cv.put(contract.passEntry.COLUMN_LOGIN , login);
        cv.put(contract.passEntry.COLUMN_PASS , pass );
        mdb.insert(contract.passEntry.TABLE_NAME, null, cv);}

    public void delRecBye(long id) {
            mdb.delete(contract.ByeEntry.TABLE_NAME ,contract.ByeEntry._ID + " = " + id, null);
        }

    public void delRecFilm(long id) {
        mdb.delete(contract.FilmEntry.TABLE_NAME ,contract.FilmEntry._ID + " = " + id, null);
    }

    public void delRecRead(long id) {
        mdb.delete(contract.ReadEntry.TABLE_NAME ,contract.ReadEntry._ID + " = " + id, null);
    }

    public void delRecPass(long id) {
        mdb.delete(contract.passEntry.TABLE_NAME ,contract.passEntry._ID + " = " + id, null);
    }

    public void delRectoget(long id) {
        mdb.delete(contract.togetEntry.TABLE_NAME ,contract.togetEntry._ID + " = " + id, null);
    }

    public void delRecTodo(long id) {
        mdb.delete(contract.todoEntry.TABLE_NAME ,contract.todoEntry._ID + " = " + id, null);
    }

    private class DBHelper extends SQLiteOpenHelper {


        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        // создаем и заполняем БД
        @Override
        public void onCreate(SQLiteDatabase db) {
            mdbhelper.onCreate(db);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           mdbhelper.onUpgrade(db,oldVersion,newVersion ) ;
            onCreate(db);
        }
    }

    public Cursor getTable(String tbl_name) {
        String sqlQuery = "SELECT * FROM " + tbl_name + ";";
        return mdb.rawQuery(sqlQuery, null);
    }


}

