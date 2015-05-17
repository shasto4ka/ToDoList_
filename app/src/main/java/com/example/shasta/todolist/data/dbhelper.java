package com.example.shasta.todolist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.shasta.todolist.data.contract.ByeEntry;
import com.example.shasta.todolist.data.contract.FilmEntry ;
import com.example.shasta.todolist.data.contract.HabitEntry ;
import com.example.shasta.todolist.data.contract.passEntry ;
import com.example.shasta.todolist.data.contract.ReadEntry ;
import com.example.shasta.todolist.data.contract.todoEntry ;
import com.example.shasta.todolist.data.contract.togetEntry ;

public class dbhelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 2;

    static final String DATABASE_NAME = "lists.db";

    public dbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        
	final String SQL_CREATE_BYE_TABLE = "CREATE TABLE " + 
		ByeEntry.TABLE_NAME + " (" +
                ByeEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ByeEntry.COLUMN_PRODUCT + " TEXT NOT NULL, " +
                ByeEntry.COLUMN_PRIORITY + " INTEGER);";

        final String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + 
		HabitEntry.TABLE_NAME + " (" +
                HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                HabitEntry.COLUMN_HABIT + " TEXT NOT NULL);";

	final String SQL_CREATE_READ_TABLE = "CREATE TABLE " + 
		ReadEntry.TABLE_NAME + " (" +
                ReadEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                ReadEntry.COLUMN_BOOK + " TEXT NOT NULL, " +
                ReadEntry.COLUMN_AUTHOR + " TEXT NOT NULL, " +
                ReadEntry.COLUMN_ZHANR + " TEXT NOT NULL, " +
                ReadEntry.COLUMN_YES + " BOOLEAN);";

	final String SQL_CREATE_FILM_TABLE = "CREATE TABLE " + 
		FilmEntry.TABLE_NAME + " (" +
                FilmEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FilmEntry.COLUMN_FILM + " TEXT NOT NULL, " +
                FilmEntry.COLUMN_ZHANR + " TEXT NOT NULL, " +
                FilmEntry.COLUMN_YES + " BOOLEAN);";

	final String SQL_CREATE_TODO_TABLE = "CREATE TABLE " + 
		todoEntry.TABLE_NAME + " (" +
                todoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                todoEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                todoEntry.COLUMN_DATETIME + " DATETIME, " +
                todoEntry.COLUMN_YES + " BOOLEAN);";

	final String SQL_CREATE_TOGET_TABLE = "CREATE TABLE " + 
		togetEntry.TABLE_NAME + " (" +
                togetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                togetEntry.COLUMN_GOAL + " TEXT NOT NULL, " +
                todoEntry.COLUMN_YES + " BOOLEAN);";

        final String SQL_CREATE_PASS_TABLE = "CREATE TABLE " + 
		passEntry.TABLE_NAME + " (" +
                passEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                passEntry.COLUMN_SITE + " TEXT NOT NULL, " +
                passEntry.COLUMN_LOGIN + " TEXT NOT NULL, " +
                passEntry.COLUMN_PASS + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_BYE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_HABIT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_READ_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_FILM_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TODO_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TOGET_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PASS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ByeEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ReadEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FilmEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + todoEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + togetEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + passEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}