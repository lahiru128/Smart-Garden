package com.example.smartgarden.FBDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.smartgarden.MODELS.PTrack;
import com.example.smartgarden.MODELS.SinglePTrack;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDB extends SQLiteOpenHelper {

    private static SQLiteDB sInstance;

    public static final String DBNAME = "smartgarden";

    public static final String TABLEPT = "planttrack";
    public static final String TABLEALLPT = "allplanttrack";

    private static final int DATABASE_VERSION = 1;

    public static final String C1 = "ptid";
    public static final String C2 = "pid";
    public static final String C3 = "initialdate";
    public static final String C4 = "lastcompletedstep";
    public static final String C5 = "laststepdate";

    public static final String C1_ALL = "id";
    public static final String C2_ALL = "ptid";
    public static final String C3_ALL = "pid";
    public static final String C4_ALL = "completedstep";
    public static final String C5_ALL = "completeddate";

    public static final String CREATE_TPT = "CREATE TABLE " + TABLEPT + "("
            + C2 + " TEXT, "
            + C3 + " TEXT, "
            + C4 + " TEXT, "
            + C5 + " TEXT, "
            + C1 + " INTEGER PRIMARY KEY AUTOINCREMENT);";

    public static final String CREATE_TPT_ALL = "CREATE TABLE " + TABLEALLPT + "("
            + C2_ALL + " TEXT, "
            + C3_ALL + " TEXT, "
            + C4_ALL + " TEXT, "
            + C5_ALL + " TEXT, "
            + C1_ALL + " INTEGER PRIMARY KEY AUTOINCREMENT);";

    public static SQLiteDB getInstance(Context context) {

        if (sInstance == null) {
            sInstance = new SQLiteDB(context.getApplicationContext());
        }
        return sInstance;
    }

    public SQLiteDB(Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TPT);
        sqLiteDatabase.execSQL(CREATE_TPT_ALL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i1 > i) {
            Log.w("MyAppTag", "Updating database from version " + i + " to "
                    + i1 + " .Existing data will be lost.");

            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLEPT);

            // Create tables again
            onCreate(sqLiteDatabase);
        }
    }

    public void addPT(String j, String k) {
        // getting db instance for writing the user
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // cv.put(User_id,usr.getId());
        cv.put(C2, j);
        cv.put(C3, k);
        cv.put(C4, "ns0");
        cv.put(C5, k);
        //inserting row
        db.insert(TABLEPT, null, cv);
        System.out.println("newly added entry date: " + k);
        //close the database to avoid any leak
        db.close();
    }

    public void addPTALL(int p, String pi, String c, String d) {
        // getting db instance for writing the user
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        // cv.put(User_id,usr.getId());
        cv.put(C2_ALL, p);
        cv.put(C3_ALL, pi);
        cv.put(C4_ALL, c);
        cv.put(C5_ALL, d);
        //inserting row
        db.insert(TABLEALLPT, null, cv);
        //close the database to avoid any leak
        db.close();
    }

    public List<PTrack> getAllTasks() {
        List<PTrack> taskList = new ArrayList<PTrack>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLEPT;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                PTrack task2 = new PTrack();
                task2.setId(cursor.getInt(4));
                task2.setPid(cursor.getString(0));
                task2.setInitialdate(cursor.getString(1));
                task2.setLastcompletedstep(cursor.getString(2));
                task2.setLaststepdate(cursor.getString(3));

                taskList.add(task2);
                System.out.println("DB OUTPUT " + cursor.getString(0) + " "
                        + cursor.getString(1) + " "
                        + cursor.getString(2) + " "
                        + cursor.getString(3) + " "
                        + cursor.getInt(4));
            } while (cursor.moveToNext());
        }

        // return contact list
        return taskList;
    }

    public List<SinglePTrack> getTrackofSinglePlant(int ptid) {
        List<SinglePTrack> taskList = new ArrayList<SinglePTrack>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLEALLPT + " WHERE " + C2_ALL + "=" + String.valueOf(ptid);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SinglePTrack task2 = new SinglePTrack();
                task2.setSpid(cursor.getInt(4));
                task2.setPtid(cursor.getInt(0));
                task2.setPid(cursor.getString(1));
                task2.setCompletedstep(cursor.getString(2));
                task2.setCompleteddate(cursor.getString(3));
                taskList.add(task2);
                System.out.println("DB OUTPUT " + cursor.getString(0) + " "
                        + cursor.getString(1) + " "
                        + cursor.getString(2) + " "
                        + cursor.getString(3));
            } while (cursor.moveToNext());
        }

        // return contact list
        return taskList;
    }

    public List<SinglePTrack> getLastEntryofAll() {
        List<SinglePTrack> taskList = new ArrayList<SinglePTrack>();
        // Select All Query
        String selectQuery = "SELECT *, MAX(id) as lastid FROM allplanttrack GROUP BY ptid";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SinglePTrack task2 = new SinglePTrack();
                task2.setSpid(cursor.getInt(4));
                task2.setPtid(cursor.getInt(0));
                task2.setPid(cursor.getString(1));
                task2.setCompletedstep(cursor.getString(2));
                task2.setCompleteddate(cursor.getString(3));
                taskList.add(task2);
                System.out.println("DB OUTPUT " + cursor.getString(0) + " "
                        + cursor.getString(1) + " "
                        + cursor.getString(2) + " "
                        + cursor.getString(3));
            } while (cursor.moveToNext());
        }

        // return contact list
        return taskList;
    }

    //get one plant track details
    public PTrack getTrack(int ptid) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLEPT, new String[]{C1, C2, C3, C4, C5}, C1 + "=?", new String[]{String.valueOf(ptid)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        System.out.println("single track :" + cursor.getInt(0) + ","
                + cursor.getString(1) + "," + cursor.getString(2)
                + ", " + cursor.getString(3) + ", " + cursor.getString(4));
        return null;

    }

    public int updateTask(PTrack pTrack, String step, String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(C4, step);
        values.put(C5, date);

        System.out.println("new out: " + String.valueOf(pTrack.getId()));
        System.out.println("new out1: " + step + " " + date);

        // updating row
        return db.update(TABLEPT, values, C1 + " = ?", new String[]{String.valueOf(pTrack.getId())});
    }

    public void truncateTable(String tblName, String crt) {
        String truncQuery1 = "DROP TABLE " + tblName;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(truncQuery1);
        db.execSQL(crt);

        db.close();
    }

    public int getPlantCount(String pid) {
        int count = 0;
        String countquery = "SELECT COUNT("+pid+") FROM planttrack GROUP BY pid";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(countquery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                count = cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return count;
    }
}