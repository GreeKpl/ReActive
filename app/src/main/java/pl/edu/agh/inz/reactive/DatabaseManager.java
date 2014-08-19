/*package pl.edu.agh.inz.reactive;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {
	
	public DatabaseManager(Context context) {
		super(context, "users.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(
				"create table users("+
				"login text primary key,"+
				"name text,"+
				"surname text);"+"");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public List<User> dbReturnUsers() {
		List<User> users = new ArrayList<User>();
		String[] columns = {"login", "name", "surname"};
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query("users", columns, null, null, null, null, null);
		while (cursor.moveToNext()) {
			User user = new User();
			user.setLogin(cursor.getString(0));
			user.setName(cursor.getString(1));
			user.setSurname(cursor.getString(2));
			users.add(user);
		}
		
		return users;
	}
	
	public User dbReturnUser(String login) {
		User user = new User();
		SQLiteDatabase db = getReadableDatabase();
		String[] columns = {"login", "name", "surname"};
		String[] args = {login};
		Cursor cursor = db.query("users", columns, "login=?", args, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			user.setLogin(cursor.getString(0));
			user.setName(cursor.getString(1));
			user.setSurname(cursor.getString(2));
		}
		
		return user;
	}
	
	public void dbAddUser(User user) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("login", user.getLogin());
		values.put("name", user.getName());
		values.put("surname", user.getSurname());
		db.insertOrThrow("users", null, values);
	}
	
	public void dbRemoveUser(User user) {
		SQLiteDatabase db = getWritableDatabase();
		String [] args = {user.getLogin()};
		db.delete("users", "login=?", args);
	}
	
	
	
	

}
*/