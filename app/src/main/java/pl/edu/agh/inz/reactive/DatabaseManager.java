package pl.edu.agh.inz.reactive;

import java.util.ArrayList;
import java.util.Collection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

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
                    DATE+" long,"+
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

    public void insertUser(User user) {
        ContentValues values = new ContentValues();
        values.put(LOGIN, user.getLogin());
        values.put(NAME, user.getName());
        values.put(SURNAME, user.getSurname());
        db.insert(DB_USERS_TABLE, null, values);
    }

    public void updateUser(String login, String name, String surname) {
        String where = LOGIN + "='" + login + "'";
        ContentValues updateValues = new ContentValues();
        updateValues.put(NAME, name);
        updateValues.put(SURNAME, surname);
        db.update(DB_USERS_TABLE, updateValues, where, null);
    }

    public void deleteUser(User user){
        String where = LOGIN + "='" + user.getLogin() + "'";
        db.delete(DB_USERS_TABLE, where, null);
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
        String where = LOGIN + "='" + login + "'";
        Cursor cursor = db.query(DB_USERS_TABLE, columns, where, null, null, null, null);
        User user = null;
        if(cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            user = new User(login, name, surname);
        }
        return user;
    }


    public int getMaxLevel(String login, int game) {
        int maxLevel = 0;
        String[] columns = {LEVEL};
        String where = LOGIN + "='" + login + "' AND " + GAME + "=" + game;
        String orderBy = LEVEL+" DESC";

        Cursor cursor = db.query(DB_LEVELS_TABLE, columns, where, null, null, null, orderBy);

        if(cursor != null && cursor.moveToFirst()) {
            maxLevel = cursor.getInt(0);
        }

        return maxLevel;
    }

    public int getPointsFromLevel(String login, int game, int level) {
        String[] columns = {POINTS};
        String where = LOGIN + "='" + login + "' AND " + GAME + "=" + game + " AND " + LEVEL + "=" +level;

        Cursor cursor = db.query(DB_LEVELS_TABLE, columns, where, null, null, null, null);

        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }

    public int getPointsFromDate(String login, int game, long date) {
        String[] columns = {POINTS};
        String where = LOGIN + "='" + login + "' AND " + GAME + "=" + game + " AND " + DATE + "=" +date;

        Cursor cursor = db.query(DB_RESULTS_TABLE, columns, where, null, null, null, null);

        if(cursor != null && cursor.moveToFirst()) {
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }

    public void insertLevelResult(String login, int game, int level, int points) {
        System.out.println("INSRT: " + login + " " + game + " " + level + " pts: " + points);
        ContentValues values = new ContentValues();
        values.put(LOGIN, login);
        values.put(GAME, game);
        values.put(LEVEL, level);
        values.put(POINTS, points);
        db.insert(DB_LEVELS_TABLE, null, values);
    }

    public void deleteLevelResult(String login, int game, int level) {
        String where = LOGIN + "='" + login + "' AND " + GAME + "=" + game + " AND " + LEVEL + "=" +level;

        db.delete(DB_LEVELS_TABLE, where, null);
    }

    public void saveLevelResult(String login, int game, int level, int points) {
        String[] columns = {POINTS};
        String where = LOGIN + "='" + login + "' AND " + GAME + "=" + game + " AND " + LEVEL + "=" +level;

        Cursor cursor = db.query(DB_LEVELS_TABLE, columns, where, null, null, null, null);

        if (cursor.moveToFirst()) {
            if (cursor.getInt(0) < points) {
                deleteLevelResult(login, game, level);
                insertLevelResult(login, game, level, points);
            }
        } else {
            insertLevelResult(login, game, level, points);
        }
    }

    public void insertDateResult(String login, int game, long date, int points) {
        ContentValues values = new ContentValues();
        values.put(LOGIN, login);
        values.put(GAME, game);
        values.put(DATE, date);
        values.put(POINTS, points);
        db.insert(DB_RESULTS_TABLE, null, values);
    }

    public void deleteDateResult(String login, int game, long date) {
        String where = LOGIN + "='" + login + "' AND " + GAME + "=" + game + " AND " + DATE + "=" +date;

        db.delete(DB_RESULTS_TABLE, where, null);
    }

    public void saveDateResult(String login, int game, long date, int points) {
        String[] columns = {POINTS};
        String where = LOGIN + "='" + login + "' AND " + GAME + "=" + game + " AND " + DATE + "=" +date;

        Cursor cursor = db.query(DB_RESULTS_TABLE, columns, where, null, null, null, null);

        if (cursor.getCount() == 0) {
            insertDateResult(login, game, date, points);
        } else {
            cursor.moveToFirst();
            if (cursor.getInt(0) < points) {
                deleteDateResult(login, game, date);
                insertDateResult(login, game, date, points);
            }
        }
    }

    public boolean saveResult(String login, int game, long date, int level, int points) {

        saveLevelResult(login, game, level, points);
        saveDateResult(login, game, date, points);
        return true;
    }
}


