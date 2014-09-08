package pl.edu.agh.inz.reactive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager {
    private static final String DEBUG_TAG = "DatebaseManager";

    private static final int DB_VERSION = 1;

    private static final String DB_NAME = "database.db";

    /*Table of users*/
    private static final String DB_USERS_TABLE = "users";
    public static final String LOGIN = "login";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";

    /*Table of users*/
    private static final String DB_LEVELS_TABLE = "levels";
    public static final String GAME = "game";
    public static final String LEVEL = "level";
    public static final String POINTS = "points";

    /*Table of users*/
    private static final String DB_RESULTS_TABLE = "results";
    public static final String DATE = "date";


    private static final String DB_CREATE_USERS_TABLE =
            "create table "+ DB_USERS_TABLE +" ("+
                    LOGIN+" text primary key,"+
                    NAME+" text,"+
                    SURNAME+" text);"+"";

    private static final String DB_CREATE_LEVELS_TABLE =
            "create table "+ DB_LEVELS_TABLE +" ("+
                    LOGIN+" text primary key,"+
                    GAME+" int,"+
                    LEVEL+" int,"+
                    POINTS+" int);"+"";

    private static final String DB_CREATE_RESULTS_TABLE =
            "create table "+ DB_RESULTS_TABLE +" ("+
                    LOGIN+" text primary key,"+
                    GAME+" int,"+
                    DATE+" date,"+
                    POINTS+" int);"+"";

    private static final String DB_DROP_TABLE = "drop table if exists ";

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(DEBUG_TAG, "Database creating...");

            db.execSQL(DB_CREATE_USERS_TABLE);
            Log.d(DEBUG_TAG, "Table " + DB_USERS_TABLE + " ver." + DB_VERSION + " created");
            db.execSQL(DB_CREATE_LEVELS_TABLE);
            Log.d(DEBUG_TAG, "Table " + DB_LEVELS_TABLE + " ver." + DB_VERSION + " created");
            db.execSQL(DB_CREATE_RESULTS_TABLE);
            Log.d(DEBUG_TAG, "Table " + DB_RESULTS_TABLE + " ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.d(DEBUG_TAG, "Database updating...");

            db.execSQL(DB_DROP_TABLE+ DB_USERS_TABLE);
            Log.d(DEBUG_TAG, "Table " + DB_USERS_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            db.execSQL(DB_DROP_TABLE+ DB_LEVELS_TABLE);
            Log.d(DEBUG_TAG, "Table " + DB_LEVELS_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            db.execSQL(DB_DROP_TABLE+ DB_RESULTS_TABLE);
            Log.d(DEBUG_TAG, "Table " + DB_RESULTS_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);

            onCreate(db);
        }
    }

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public DatabaseManager open(){
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    /*Functions for users*/

    public long insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put(LOGIN, user.getLogin());
        values.put(NAME, user.getName());
        values.put(SURNAME, user.getSurname());
        return db.insert(DB_USERS_TABLE, null, values);   //zwraca id ostatnio zapisanego wiersza lub -1 gdy blad
    }

    public boolean updateUser(String login, String name, String surname) {
        String where = LOGIN + "=" + login;
        ContentValues updateValues = new ContentValues();
        updateValues.put(NAME, name);
        updateValues.put(SURNAME, surname);
        return db.update(DB_USERS_TABLE, updateValues, where, null) > 0;
    }

    public boolean deleteUser(User user){
        String where = LOGIN + "=" + user.getLogin();
        return db.delete(DB_USERS_TABLE, where, null) > 0;
    }

    public Cursor getAllUsers() {
        String[] columns = {LOGIN, NAME, SURNAME};
        return db.query(DB_USERS_TABLE, columns, null, null, null, null, null);
    }

    public Collection<User> getCollectionAllUsers() {
        Collection<User> users = new ArrayList<User>();
        Cursor cursor = getAllUsers();
        while (cursor.moveToNext()) {
            User user = new User();
            user.setLogin(cursor.getString(0));
            user.setName(cursor.getString(1));
            user.setSurname(cursor.getString(2));
            users.add(user);
        }
        return users;
    }

    public User getUser(String login) {
        String[] columns = {LOGIN, NAME, SURNAME};
        String where = LOGIN + "=" + login;
        Cursor cursor = db.query(DB_USERS_TABLE, columns, where, null, null, null, null);
        User user = null;
        if(cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            user = new User(login, name, surname);
        }
        return user;
    }


    /*Functions for levels*/

    public int getMaxLevel(String login, int game) {
        int maxLevel = 0, tmpLevel;
        String[] columns = {LOGIN, GAME, LEVEL, POINTS};
        String where = LOGIN + "=" + login + " AND " + GAME + "=" + game;   //game?

        Cursor cursor = db.query(DB_LEVELS_TABLE, columns, where, null, null, null, null);

        while (cursor.moveToNext()) {
            tmpLevel = cursor.getInt(2);
            if(maxLevel < tmpLevel) {
                maxLevel = tmpLevel;
            }
        }

        return maxLevel;
    }

    public boolean saveResult(String login, int game, Date date, int level, int points) {

        return true;
    }



//    public List<User> dbReturnUsers() {
//        List<User> users = new ArrayList<User>();
//        String[] columns = {"login", "name", "surname"};
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.query("users", columns, null, null, null, null, null);
//        while (cursor.moveToNext()) {
//            User user = new User();
//            user.setLogin(cursor.getString(0));
//            user.setName(cursor.getString(1));
//            user.setSurname(cursor.getString(2));
//            users.add(user);
//        }
//
//        return users;
//    }
//
//    public User dbReturnUser(String login) {
//        User user = new User();
//        SQLiteDatabase db = getReadableDatabase();
//        String[] columns = {"login", "name", "surname"};
//        String[] args = {login};
//        Cursor cursor = db.query("users", columns, "login=?", args, null, null, null);
//        if (cursor != null) {
//            cursor.moveToFirst();
//            user.setLogin(cursor.getString(0));
//            user.setName(cursor.getString(1));
//            user.setSurname(cursor.getString(2));
//        }
//
//        return user;
//    }
//
//    public void dbAddUser(User user) {
//        SQLiteDatabase db = getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("login", user.getLogin());
//        values.put("name", user.getName());
//        values.put("surname", user.getSurname());
//        db.insertOrThrow("users", null, values);
//    }
//
//    public void dbRemoveUser(User user) {
//        SQLiteDatabase db = getWritableDatabase();
//        String [] args = {user.getLogin()};
//        db.delete("users", "login=?", args);
//    }
}


