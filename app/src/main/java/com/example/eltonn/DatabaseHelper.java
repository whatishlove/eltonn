package com.example.eltonn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME = "elton.db";

    //User table name
    private static final String TABLE_USER = "user";
    private static final String TABLE_GYM = "gym";
    private static final String TABLE_WR = "workoutroutine";

    //User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_USERNAME = "user_username";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";

    //Gym Table Columns names
    private static final String COLUMN_GYM_ID = "gym_id";
    private static final String COLUMN_GYM_GYMNAME = "gym_gymname";
    private static final String COLUMN_GYM_LOCATION = "gym_location";
    private static final String COLUMN_GYM_ADDRESS = "gym_address";
    private static final String COLUMN_GYM_VACANCIES = "gym_vacancies";
    private static final String COLUMN_GYM_STARS = "gym_stars";

    //Workout Routine Table Columns names
    private static final String COLUMN_WR_ID = "wr_id";
    private static final String COLUMN_WR_NAME = "wr_wrname";
    private static final String COLUMN_WR_DURATION = "wr_duration";
    private static final String COLUMN_WR_URL = "wr_url";

    private static final String createUserTableSql =
            "CREATE TABLE " + TABLE_USER + "("
                    + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_USER_NAME + " TEXT, "
                    + COLUMN_USER_USERNAME + " TEXT, "
                    + COLUMN_USER_EMAIL + " TEXT, "
                    + COLUMN_USER_PASSWORD + " TEXT) ";
    private static final String createGymTableSql = "CREATE TABLE " + TABLE_GYM + "("
            + COLUMN_GYM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_GYM_GYMNAME + " TEXT, "
            + COLUMN_GYM_LOCATION + " TEXT, "
            + COLUMN_GYM_ADDRESS + " TEXT, "
            + COLUMN_GYM_VACANCIES + " TEXT, "
            + COLUMN_GYM_STARS + " INTEGER) ";
    private static final String createWRTableSql =
            "CREATE TABLE " + TABLE_WR + "("
                    + COLUMN_WR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_WR_NAME + " TEXT, "
                    + COLUMN_WR_DURATION + " TEXT, "
                    + COLUMN_WR_URL + " TEXT) ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(createUserTableSql);
     db.execSQL(createGymTableSql); db.execSQL(createWRTableSql);
     Log.i("info", "created tables");

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, "name");
        values.put(COLUMN_USER_USERNAME, "username");
        values.put(COLUMN_USER_EMAIL, "email@email.com");
        values.put(COLUMN_USER_PASSWORD, "password");
        db.insert(TABLE_USER, null, values);

        ContentValues gymvalues1 = new ContentValues();
        gymvalues1.put(COLUMN_GYM_GYMNAME, "Kovan ActiveSG");
        gymvalues1.put(COLUMN_GYM_LOCATION, "Kovan");
        gymvalues1.put(COLUMN_GYM_ADDRESS, "Kovan Singapore");
        gymvalues1.put(COLUMN_GYM_VACANCIES, "100");
        gymvalues1.put(COLUMN_GYM_STARS, 5);
        db.insert(TABLE_GYM, null, gymvalues1);

        ContentValues gymvalues2 = new ContentValues();
        gymvalues2.put(COLUMN_GYM_GYMNAME, "Jurong East ActiveSG");
        gymvalues2.put(COLUMN_GYM_LOCATION, "Jurong East");
        gymvalues2.put(COLUMN_GYM_ADDRESS, "Jurong East Singapore");
        gymvalues2.put(COLUMN_GYM_VACANCIES, "101");
        gymvalues2.put(COLUMN_GYM_STARS, 4);
        db.insert(TABLE_GYM, null, gymvalues2);

        ContentValues wrvalues1 = new ContentValues();
        wrvalues1.put(COLUMN_WR_NAME, "Leg Lunges");
        wrvalues1.put(COLUMN_WR_DURATION, "10 minutes");
        wrvalues1.put(COLUMN_WR_URL, "https://youtu.be/QF0BQS2W80k");
        db.insert(TABLE_WR, null, wrvalues1);

        ContentValues wrvalues2 = new ContentValues();
        wrvalues2.put(COLUMN_WR_NAME, "Push-Ups");
        wrvalues2.put(COLUMN_WR_DURATION, "15 minutes");
        wrvalues2.put(COLUMN_WR_URL, "https://youtu.be/ryncZFQCB8I");
        db.insert(TABLE_WR, null, wrvalues2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GYM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WR);
        onCreate(db);
    }

    public long signUp(String user_name, String user_username, String user_email, String user_password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user_name);
        values.put(COLUMN_USER_USERNAME, user_username);
        values.put(COLUMN_USER_EMAIL, user_email);
        values.put(COLUMN_USER_PASSWORD, user_password);
        long result = db.insert(TABLE_USER, null, values);
        db.close();
        if (result == -1) {
            Log.d("DBHelper", "Insert failed");
        } else {
            Log.d("SQL Insert ", "" + result);
        }

        return result;
    }

    /*------------------------- insert Gym, workoutroutine-------------------------------*/
    public long insertGym(String gymname, String location, String address, String vacancies, int stars) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues gymvalues = new ContentValues();
        gymvalues.put(COLUMN_GYM_GYMNAME, gymname);
        gymvalues.put(COLUMN_GYM_LOCATION, location);
        gymvalues.put(COLUMN_GYM_ADDRESS, address);
        gymvalues.put(COLUMN_GYM_VACANCIES, vacancies);
        gymvalues.put(COLUMN_GYM_STARS, stars);
        long result = db.insert(TABLE_GYM, null, gymvalues);
        db.close();
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        } else{
            Log.d("SQL Insert ",""+ result);
        }
        return result;
    }
    public long insertWR(String wrname, String duration, String url) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues wrvalues = new ContentValues();
        wrvalues.put(COLUMN_WR_NAME, wrname);
        wrvalues.put(COLUMN_WR_DURATION, duration);
        wrvalues.put(COLUMN_WR_URL, url);
        long result = db.insert(TABLE_WR, null, wrvalues);
        db.close();
        if (result == -1){
            Log.d("DBHelper", "Insert failed");
        } else{
            Log.d("SQL Insert ",""+ result);
        }
        return result;
    }

    /*------------------------- get all users, gyms and workoutroutines-------------------------------*/
    public ArrayList<WR> getAllWRs() {
        ArrayList<WR> wrs = new ArrayList<WR>();
        String selectQuery = "SELECT " + COLUMN_WR_ID + "," + COLUMN_WR_NAME + ","
                + COLUMN_WR_DURATION + "," + COLUMN_WR_URL + " FROM " + TABLE_WR;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String wrname = cursor.getString(1);
                String wrduration = cursor.getString(2);
                String wrurl = cursor.getString(3);

                WR wrobj = new WR(id, wrname, wrduration, wrurl);
                wrs.add(wrobj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return wrs;
    }
    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<User>();

        String selectQuery = "SELECT " + COLUMN_USER_ID + "," + COLUMN_USER_NAME + ","
                + COLUMN_USER_USERNAME + "," + COLUMN_USER_EMAIL + "," + COLUMN_USER_PASSWORD + " FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String username = cursor.getString(2);
                String email = cursor.getString(3);
                String password = cursor.getString(1);

                User obj = new User(id, name, username, email, password);
                users.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return users;
    }
    public ArrayList<Gym> getAllGyms() {
        ArrayList<Gym> gyms = new ArrayList<Gym>();
        String selectQuery = "SELECT " + COLUMN_GYM_ID + "," + COLUMN_GYM_GYMNAME + ","
                + COLUMN_GYM_LOCATION + "," + COLUMN_GYM_ADDRESS + "," + COLUMN_GYM_VACANCIES + "," + COLUMN_GYM_STARS + " FROM " + TABLE_GYM;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String gymname = cursor.getString(1);
                String location = cursor.getString(2);
                String address = cursor.getString(3);
                String vacancies = cursor.getString(4);
                int stars = cursor.getInt(5);

                Gym gymobj = new Gym(id, gymname, location, address, vacancies, stars);
                gyms.add(gymobj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gyms;
    }

    /*------------------------- get all users and gyms by name-------------------------------*/
    public ArrayList<String> getAllUsersByUsername() {
        ArrayList<String> username = new ArrayList<>();

        String usernameQuery = "SELECT " + COLUMN_USER_USERNAME + " FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(usernameQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String username1 = String.valueOf(cursor.getInt(0));
                username.add(username1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return username;
    }
    public ArrayList<String> getAllGymsByGymname() {
        ArrayList<String> gymname = new ArrayList<>();

        String gymnameQuery = "SELECT " + COLUMN_GYM_GYMNAME + " FROM " + TABLE_GYM;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(gymnameQuery, null);
        if (cursor.moveToFirst()) {
            do {
                String gymname1 = String.valueOf(cursor.getInt(0));
                gymname.add(gymname1);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return gymname;
    }

    /*------------------------- update users, workoutroutine and gym-------------------------------*/
    public int updateUser(User userdata){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, userdata.getName());
        values.put(COLUMN_USER_USERNAME, userdata.getUsername());
        values.put(COLUMN_USER_EMAIL, userdata.getEmail());
        values.put(COLUMN_USER_PASSWORD, userdata.getPassword());

        String condition = COLUMN_USER_ID + "= ?";
        String[] args = {String.valueOf(userdata.getId())};
        int result = db.update(TABLE_USER, values, condition, args);
        db.close();

        if (result < 1){
            Log.d("DatabaseHelper", "Update Failed");
        }
        return result;
    }
    public int updateGym(Gym gymdata){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_GYM_GYMNAME, gymdata.getGymname());
        values.put(COLUMN_GYM_LOCATION, gymdata.getLocation());
        values.put(COLUMN_GYM_ADDRESS, gymdata.getAddress());
        values.put(COLUMN_GYM_VACANCIES, gymdata.getVacancies());
        values.put(COLUMN_GYM_STARS, gymdata.getStars());

        String condition = COLUMN_GYM_ID + "= ?";
        String[] args = {String.valueOf(gymdata.getId())};
        int result = db.update(TABLE_GYM, values, condition, args);
        db.close();

        if (result < 1){
            Log.d("DatabaseHelper", "Update Failed");
        }
        return result;
    }
    public int updateWR(WR wrdata){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_WR_NAME, wrdata.getWrname());
        values.put(COLUMN_WR_DURATION, wrdata.getDuration());
        values.put(COLUMN_WR_URL, wrdata.getUrl());

        String condition = COLUMN_WR_ID + "= ?";
        String[] args = {String.valueOf(wrdata.getId())};
        int result = db.update(TABLE_WR, values, condition, args);
        db.close();

        if (result < 1){
            Log.d("DatabaseHelper", "Update Failed");
        }
        return result;
    }

    /*------------------------- delete users, workoutroutine and gym-------------------------------*/
    public int deleteUser(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_USER_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_USER, condition, args);
        db.close();

        if (result < 1) {
            Log.d("DatabaseHelper", "Delete failed");
        }
        return result;
    }
    public int deleteGym(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_GYM_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_GYM, condition, args);
        db.close();

        if (result < 1) {
            Log.d("DatabaseHelper", "Delete failed");
        }
        return result;
    }
    public int deleteWR(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_WR_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_WR, condition, args);
        db.close();

        if (result < 1) {
            Log.d("DatabaseHelper", "Delete failed");
        }
        return result;
    }
}