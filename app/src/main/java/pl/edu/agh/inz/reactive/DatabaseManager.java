package pl.edu.agh.inz.reactive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager {
    private static final String DEBUG_TAG = "DatabaseManager";

    private static final int DB_VERSION = 4;

    private static final String DB_NAME = "database.db";

    /*Table of users*/
    private static final String DB_USERS_TABLE = "users";
    public static final String LOGIN = "login";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";

    /*Table of lavels*/
    private static final String DB_LEVELS_TABLE = "levels";
    public static final String GAME = "game";
    public static final String LEVEL = "level";
    public static final String POINTS = "points";

    /*Table of dates*/
    private static final String DB_RESULTS_TABLE = "results";
    public static final String DATE = "date";

    private static final String DB_GLOBALS_TABLE = "globals";
    public static final String KEY = "key";
    public static final String VALUE = "value";


    private static final String DB_CREATE_USERS_TABLE =
            "create table "+ DB_USERS_TABLE +" ("+
                    LOGIN+" TEXT PRIMARY KEY,"+
                    NAME+" TEXT,"+
                    SURNAME+" TEXT);";

    private static final String DB_CREATE_LEVELS_TABLE =
            "create table "+ DB_LEVELS_TABLE +" ("+
                    LOGIN+" TEXT,"+
                    GAME+" int,"+
                    LEVEL+" int,"+
                    POINTS+" int, primary key (" + LOGIN + ", " + GAME + ", " + LEVEL + "));";

    private static final String DB_CREATE_RESULTS_TABLE =
            "create table "+ DB_RESULTS_TABLE +" ("+
                    LOGIN + " TEXT,"+
                    GAME + " int," +
                    DATE + " long," +
                    POINTS +" int, PRIMARY KEY (" + LOGIN + ", " + GAME + ", " + DATE + "));";

    private static final String DB_CREATE_GLOBALS_TABLE =
            "create table "+ DB_GLOBALS_TABLE +" ("+
                    KEY +" TEXT PRIMARY KEY,"+
                    VALUE +" TEXT);";

    private static final String DB_DROP_TABLE = "drop table if exists ";
    private static final String ACTIVE_USER = "activeUser";
    private static final int MSEC_PER_DAY = 24 * 60 * 60 * 1000;

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
            db.execSQL(DB_CREATE_GLOBALS_TABLE);
            Log.d(DEBUG_TAG, "Table " + DB_GLOBALS_TABLE + " ver." + DB_VERSION + " created");
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
            db.execSQL(DB_DROP_TABLE + DB_GLOBALS_TABLE);
            Log.d(DEBUG_TAG, "Table " + DB_GLOBALS_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);

            onCreate(db);
        }
    }

    public DatabaseManager (Context context) {
        this.context = context;
    }

    public DatabaseManager open() {
        Log.d(DEBUG_TAG, "Database open...");
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        Log.d(DEBUG_TAG, "Database close...");
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

    public void deleteUser(User user){
        String where = LOGIN + "='" + user.getLogin() + "'";
        db.delete(DB_LEVELS_TABLE, where, null);
        db.delete(DB_RESULTS_TABLE, where, null);
        db.delete(DB_USERS_TABLE, where, null);
    }

    public Cursor getAllUsers() {
        String[] columns = {LOGIN, NAME, SURNAME};
        return db.query(DB_USERS_TABLE, columns, null, null, null, null, null);
    }

    public Collection<User> getAllUsersCollection() {
        Collection<User> users = new ArrayList<User>();
        Cursor cursor = getAllUsers();
        while (cursor.moveToNext()) {
            User user = new User();
            user.setLogin(cursor.getString(0));
            user.setName(cursor.getString(1));
            user.setSurname(cursor.getString(2));
            users.add(user);
        }
        cursor.close();
        return users;
    }

    public User getUser(String login) {
        String[] columns = {LOGIN, NAME, SURNAME};
        String where = LOGIN + "='" + login + "'";
        Cursor cursor = db.query(DB_USERS_TABLE, columns, where, null, null, null, null);
        User user = null;
        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            user = new User(login, name, surname);
        }
        cursor.close();

        return user;
    }

    public User getActiveUser() {
        setupDefaultUser();
        String[] columns = {VALUE};
        String where = KEY + " = '" + ACTIVE_USER + "'";
        Cursor cursor = db.query(DB_GLOBALS_TABLE, columns, where, null, null, null, null);

        String login = context.getString(R.string.default_user_login);
        if (cursor.moveToFirst()) {
            login = cursor.getString(0);
        }
        cursor.close();
        return getUser(login);
    }

    public void setActiveUser(String login) {
        ContentValues values = new ContentValues();
        values.put(KEY, ACTIVE_USER); // todo! check if user does exist
        values.put(VALUE, login);
        db.replace(DB_GLOBALS_TABLE, null, values);
    }

    public void setupDefaultUser() {
        String[] columns = { LOGIN };
        String select = LOGIN + " = '" + context.getString(R.string.default_user_login) + "'";
        Cursor cursor = db.query(DB_USERS_TABLE, columns, select, null, null, null, null);
        if (!cursor.moveToFirst()) {
            insertUser(new User(context.getString(R.string.default_user_login), context.getString(R.string.default_user_name), context.getString(R.string.default_user_surname)));
        }
        cursor.close();
    }

    public int getMaxLevel(String login, int game) {
        int maxLevel = 0;
        String[] columns = {LEVEL};
        String where = LOGIN + "='" + login + "' AND " + GAME + "=" + game;
        String orderBy = LEVEL+" DESC";

        Cursor cursor = db.query(DB_LEVELS_TABLE, columns, where, null, null, null, orderBy);

        if (cursor.moveToFirst()) {
            maxLevel = cursor.getInt(0);
        }
        cursor.close();

        return maxLevel;
    }

    public int getPointsFromLevel(String login, int game, int level) {
        String[] columns = {POINTS};
        String where = LOGIN + "='" + login + "' AND " + GAME + "=" + game + " AND " + LEVEL + "=" +level;

        Cursor cursor = db.query(DB_LEVELS_TABLE, columns, where, null, null, null, null);

        int pointsOnLevel = 0;
        if (cursor.moveToFirst()) {
            pointsOnLevel = cursor.getInt(0);
        }
        cursor.close();

        return pointsOnLevel;
    }

    public long getMinDate(String login) {
        String[] columns = {DATE};
        String where = LOGIN + "='" + login + "'";
        String orderBy = DATE;

        Cursor cursor = db.query(DB_RESULTS_TABLE, columns, where, null, null, null, orderBy);

        long date = 0;
        if (cursor.moveToFirst()) {
            date = cursor.getInt(0);
        }
        cursor.close();

        return date;
    }

    public Map<Long, Integer> getAchievements(String login, int game) {
        String[] columns = {DATE, POINTS};
        String where = LOGIN + "='" + login + "' AND " + GAME + "=" + game;
        String orderBy = DATE;
        Cursor cursor = db.query(DB_RESULTS_TABLE, columns, where, null, null, null, orderBy);
        Map<Long, Integer> achievements = new TreeMap<Long, Integer>();

        long minDate = getMinDate(login);

        while (cursor.moveToNext()) {
            achievements.put(cursor.getLong(0) - minDate + 1, cursor.getInt(1));
        }
        cursor.close();
        return achievements;

    }

    public void updateLevelResult(String login, int game, int level, int points) {
        ContentValues values = new ContentValues();
        values.put(LOGIN, login);
        values.put(GAME, game);
        values.put(LEVEL, level);
        values.put(POINTS, points);
        db.replace(DB_LEVELS_TABLE, null, values);
    }

    public void saveLevelResult(String login, int game, int level, int points) {
        String[] columns = { POINTS };
        String where = LOGIN + "='" + login + "' AND " + GAME + "=" + game + " AND " + LEVEL + "=" +level;
        Cursor cursor = db.query(DB_LEVELS_TABLE, columns, where, null, null, null, null);

        boolean alreadyExists = cursor.moveToFirst();
        if (!alreadyExists || cursor.getInt(0) < points) {
            updateLevelResult(login, game, level, points);
        }
        cursor.close();
    }

    public void updateDateResult(String login, int game, long date, int points) {
        ContentValues values = new ContentValues();
        values.put(LOGIN, login);
        values.put(GAME, game);
        values.put(DATE, date);
        values.put(POINTS, points);
        db.replace(DB_RESULTS_TABLE, null, values);
    }

    public void saveDateResult(String login, int game, long date, int points) {
        long day = date / (MSEC_PER_DAY);
        System.out.println("DATE: "+date+" DAY: "+day);
        String[] columns = {POINTS};
        String where = LOGIN + "='" + login + "' AND " + GAME + "=" + game + " AND " + DATE + "=" +day;
        Cursor cursor = db.query(DB_RESULTS_TABLE, columns, where, null, null, null, null);

        boolean alreadyExists = cursor.moveToFirst();
        if (!alreadyExists || cursor.getInt(0) < points) {
            updateDateResult(login, game, day, points);
        }
        cursor.close();
    }

    public boolean prepareToSaveDateResult(String login, int game, long date, int level, int points) {
        int allPoints = 0;
        for (int prevLevel = 0; prevLevel < level; prevLevel++) {
            allPoints += getPointsFromLevel(login, game, prevLevel);
        }
        saveDateResult(login, game, date, allPoints + points);
        return true;
    }

    public void printUsersTable() {
        String[] columns = {LOGIN, NAME, SURNAME};
        Cursor cursor = db.query(DB_USERS_TABLE, columns, null, null, null, null, null);
        System.out.println();
        System.out.println("START PRINT USERS TABLE");
        while (cursor.moveToNext()) {
            System.out.println(cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2));
        }
        cursor.close();
        System.out.println("END PRINT USERS TABLE");
    }

    public void printLevelsTable() {
        String[] columns = {LOGIN, GAME, LEVEL, POINTS};
        Cursor cursor = db.query(DB_LEVELS_TABLE, columns, null, null, null, null, null);
        System.out.println();
        System.out.println("START PRINT LEVELS TABLE");
        while (cursor.moveToNext()) {
            System.out.println(cursor.getString(0) + " " + cursor.getInt(1) + " " + cursor.getInt(2) + " " + cursor.getInt(3));
        }
        cursor.close();
        System.out.println("END PRINT LEVELS TABLE");
    }

    public void printResultsTable() {
        String[] columns = {LOGIN, GAME, DATE, POINTS};
        Cursor cursor = db.query(DB_RESULTS_TABLE, columns, null, null, null, null, null);
        System.out.println();
        System.out.println("START PRINT RESULTS TABLE");
        while (cursor.moveToNext()) {
            System.out.println(cursor.getString(0) + " " + cursor.getInt(1) + " " + cursor.getLong(2) + " " + cursor.getInt(3));
        }
        cursor.close();
        System.out.println("END PRINT RESULTS TABLE");
    }
}


