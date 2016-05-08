package jesan.collegeproject01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * Created by Hassan M.Ashraful on 5/4/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String STUDENT_INFO_DATABASE_NAME = "USERINFO.DB";
    private static final int STUDENT_INFO_DATABASE_VERSION = 3;
    private static final String STUDENT_INFO_CREATE_QUERY = "CREATE TABLE "+ StudentInfo.NewUserInfo.STUDENT_INFO_TABEL_NAME +"("+ StudentInfo.NewUserInfo.STUDENT_NAME+" TEXT,"+ StudentInfo.NewUserInfo.STUDENT_ROLL +" TEXT,"+ StudentInfo.NewUserInfo.STUDENT_MOB +" TEXT);";

    private static final String STUDENT_REPORT_CREATE_QUERY = "CREATE TABLE "+ StudentReport.StudentReportData.STUDENT_REPORT_TABEL_NAME +"("+ StudentReport.StudentReportData.STUDENT_NAME+" TEXT,"+ StudentReport.StudentReportData.STUDENT_IDS+" TEXT,"+ StudentReport.StudentReportData.CURRENT_DATE +" TEXT,"+ StudentReport.StudentReportData.STUDENT_VERDICT +" TEXT);";



    public DataBaseHelper(Context context){
        super(context, STUDENT_INFO_DATABASE_NAME, null, STUDENT_INFO_DATABASE_VERSION);
        Log.e("DATABASE OPERATION", "DATABASE craeted");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(STUDENT_INFO_CREATE_QUERY);
        db.execSQL(STUDENT_REPORT_CREATE_QUERY);
        Log.e("DATABASE OPERATION","Table created");

    }


    public void addStudentInfo(String name, String roll, String mob, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentInfo.NewUserInfo.STUDENT_NAME,name);
        contentValues.put(StudentInfo.NewUserInfo.STUDENT_ROLL,roll);
        contentValues.put(StudentInfo.NewUserInfo.STUDENT_MOB, mob);
        db.insert(StudentInfo.NewUserInfo.STUDENT_INFO_TABEL_NAME, null, contentValues);
        Log.e("DATABASE OPERATION","one row inserted ");

    }


    public Cursor getStudentInfo(SQLiteDatabase db){
        Cursor cursor;
        String[] projection = {StudentInfo.NewUserInfo.STUDENT_NAME, StudentInfo.NewUserInfo.STUDENT_ROLL, StudentInfo.NewUserInfo.STUDENT_MOB};

        cursor = db.query(StudentInfo.NewUserInfo.STUDENT_INFO_TABEL_NAME, projection, null, null, null, null, null);
        return cursor;

    }


    public void addStudentReport(String name, String id, String date, String verdict, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(StudentReport.StudentReportData.STUDENT_NAME, name);
        contentValues.put(StudentReport.StudentReportData.STUDENT_IDS, id);
        contentValues.put(StudentReport.StudentReportData.CURRENT_DATE, date);
        contentValues.put(StudentReport.StudentReportData.STUDENT_VERDICT, verdict);
        db.insert(StudentReport.StudentReportData.STUDENT_REPORT_TABEL_NAME, null, contentValues);
        Log.e("DATABASE REPORT","ONE ROW INSERTED");
    }

    public Cursor getStudentReport(SQLiteDatabase db){
        Cursor cursor;
        String[] projection = {StudentReport.StudentReportData.STUDENT_NAME, StudentReport.StudentReportData.STUDENT_IDS, StudentReport.StudentReportData.CURRENT_DATE, StudentReport.StudentReportData.STUDENT_VERDICT};

        cursor = db.query(StudentReport.StudentReportData.STUDENT_REPORT_TABEL_NAME, projection, null, null, null, null, null);
        return cursor;
    }


    public Cursor getContact(String roll, SQLiteDatabase sqLiteDatabase){
        String[] projection = {StudentInfo.NewUserInfo.STUDENT_NAME, StudentInfo.NewUserInfo.STUDENT_MOB};
        String selection = StudentInfo.NewUserInfo.STUDENT_ROLL +" LIKE ?";
        String[] selection_args = {roll};
        Cursor cursor = sqLiteDatabase.query(StudentInfo.NewUserInfo.STUDENT_INFO_TABEL_NAME, projection, selection, selection_args,null,null,null);
        return cursor;
    }


    public void deleteInformation(String roll, SQLiteDatabase sqLiteDatabase){
        String selection = StudentInfo.NewUserInfo.STUDENT_ROLL +" LIKE ?";
        String[] selection_args = {roll};
        sqLiteDatabase.delete(StudentInfo.NewUserInfo.STUDENT_INFO_TABEL_NAME, selection, selection_args);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+StudentInfo.NewUserInfo.STUDENT_INFO_TABEL_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+StudentReport.StudentReportData.STUDENT_REPORT_TABEL_NAME);
        onCreate(db);
    }



    public void exportDB(){
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source=null;
        FileChannel destination=null;
        String currentDBPath = "/data/"+ "jesan.collegeproject01" +"/databases/"+STUDENT_INFO_DATABASE_NAME;
        String backupDBPath = STUDENT_INFO_DATABASE_NAME ;
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Log.e("DATABASE SAVED", "SAVED");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String getDBname(){
        return STUDENT_INFO_DATABASE_NAME;
    }












}
