package com.example.jholl.android08_chess;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Rituraj on 12/13/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "games.db";

    // Contacts table name
    private static final String TABLE_CONTACTS = "replay";

    // Contacts Table Columns names
    private static final String GAME_TITLE = "title";
    private static final String GAME_DATE = "date";
    private static final String MOVES = "moves";

    private static DataBase database = null;

    public static DataBase getInstance(Context ctx) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (database == null) {
            database = new DataBase(ctx.getApplicationContext());
        }
        return database;
    }

    private DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + GAME_TITLE + " TEXT," + GAME_DATE + " TEXT,"
                + MOVES + " TEXT," + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(db);
    }

    public void add(DataBaseInfo DataBaseInfo) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(GAME_TITLE, DataBaseInfo.getTitle());
            values.put(GAME_DATE, DataBaseInfo.getDate());
            values.put(MOVES, DataBaseInfo.getMoves());
            db.insert(TABLE_CONTACTS, null, values);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db!=null && db.isOpen()){
                db.close();
            }
        }

    }

    public DataBaseInfo get(String id) {
        DataBaseInfo DataBaseInfo = null;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = this.getReadableDatabase();
            cursor = db.query(TABLE_CONTACTS, new String[]{GAME_TITLE, GAME_DATE, MOVES},
                    MOVES + "=?",
                    new String[]{String.valueOf(id)}, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    DataBaseInfo = new DataBaseInfo(cursor.getString(0), cursor.getString(1), cursor.getString(2));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            if(db!=null && db.isOpen()){
                db.close();
            }
        }
        return DataBaseInfo;
    }

    // Getting All data
    public List<DataBaseInfo> getAll() {
        List<DataBaseInfo> database = new ArrayList<DataBaseInfo>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        Cursor cursor = null;
        SQLiteDatabase db = null;
        try {
            try {
                db = this.getWritableDatabase();
                cursor = db.rawQuery(selectQuery, null);
            } catch (Exception e) {
                db = this.getWritableDatabase();
                onCreate(db);
                cursor = db.rawQuery(selectQuery, null);
            }

            if (cursor.moveToFirst()) {
                do {
                    DataBaseInfo DataBaseInfo = new DataBaseInfo();
                    DataBaseInfo.setTitle(cursor.getString(0));
                    DataBaseInfo.setDate(cursor.getString(1));
                    DataBaseInfo.setMoves(cursor.getString(2));
                    database.add(DataBaseInfo);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            if(db!=null && db.isOpen()){
                db.close();
            }
        }
        return database;
    }
}