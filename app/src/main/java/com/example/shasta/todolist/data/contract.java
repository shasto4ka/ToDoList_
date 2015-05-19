package com.example.shasta.todolist.data;

import android.provider.BaseColumns;


public class contract {

    public static final class ByeEntry implements BaseColumns {
        public static final String TABLE_NAME = "bye";
        public static final String COLUMN_PRODUCT = "product";
        public static final String COLUMN_PRIORITY = "priority";
    }


    public static final class ReadEntry implements BaseColumns {
	public static final String TABLE_NAME = "read";
        public static final String COLUMN_BOOK = "book";
        public static final String COLUMN_AUTHOR = "author";
        public static final String COLUMN_ZHANR = "zhanr";
        public static final String COLUMN_YES = "i_read_it";     
    }
    public static final class FilmEntry implements BaseColumns {
	public static final String TABLE_NAME = "film";
        public static final String COLUMN_FILM = "film";
        public static final String COLUMN_ZHANR = "zhanr";
        public static final String COLUMN_YES = "i_see_it";     
    }
    public static final class todoEntry implements BaseColumns {
	public static final String TABLE_NAME = "todo";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DATETIME = "datetime";
        public static final String COLUMN_YES = "i_do_it";     
    }
    public static final class togetEntry implements BaseColumns {
	public static final String TABLE_NAME = "toget";
        public static final String COLUMN_GOAL = "goal";
        public static final String COLUMN_YES = "i_get_it";
    }
    public static final class passEntry implements BaseColumns {
	public static final String TABLE_NAME = "pass";
        public static final String COLUMN_SITE = "site";
        public static final String COLUMN_LOGIN = "login";
        public static final String COLUMN_PASS = "pass";     
    }
}