package jesan.collegeproject01;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Hassan M.Ashraful on 5/7/2016.
 */
public class StudentReportListActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DataBaseHelper dataBaseHelper;
    Cursor cursor;
    StudentReportDataAdapter studentReportDataAdapter;
    private SharedPreferences sharedPreferences;
    TextView nameteacherTV, idteacherTV, classteacherTV, secteacherTV, subteacherTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_report_list_view);


        nameteacherTV = (TextView) findViewById(R.id.nameteacherTV);
        idteacherTV = (TextView) findViewById(R.id.idteacherTV);
        classteacherTV = (TextView) findViewById(R.id.classteacherTV);
        secteacherTV = (TextView) findViewById(R.id.secteacherTV);
        subteacherTV = (TextView) findViewById(R.id.subteacherTV);

        listView = (ListView) findViewById(R.id.report_list_view);

        studentReportDataAdapter = new StudentReportDataAdapter(getApplicationContext(), R.layout.row_list_student_report);
        listView.setAdapter(studentReportDataAdapter);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        cursor = dataBaseHelper.getStudentReport(sqLiteDatabase);
        if (cursor.moveToNext()){
            do {
                String id, date, roll;
                id = cursor.getString(0);
                date = cursor.getString(1);
                roll = cursor.getString(2);

                StudentReportProvider studentReportProvider = new StudentReportProvider(id, date, roll);
                studentReportDataAdapter.add(studentReportProvider);

            }while (cursor.moveToNext());
        }

        sharedPreferences = getSharedPreferences("jesan.collegeproject01.SIGNUP", MODE_PRIVATE);

        LoadPreferences();


    }


    private void LoadPreferences(){
        String name = sharedPreferences.getString("NAME","");
        String id = sharedPreferences.getString("ID","");
        String teacheClass = sharedPreferences.getString("CLASS","");
        String sec = sharedPreferences.getString("SEC","");
        String sub = sharedPreferences.getString("SUB","");
        nameteacherTV.setText(name);
        idteacherTV.setText(id);
        classteacherTV.setText(teacheClass);
        secteacherTV.setText(sec);
        subteacherTV.setText(sub);


    }


}
