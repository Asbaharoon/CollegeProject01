package jesan.collegeproject01;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

/**
 * Created by Hassan M.Ashraful on 5/7/2016.
 */
public class StudentReportListActivity extends ActionBarActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DataBaseHelper dataBaseHelper;
    Cursor cursor;
    StudentReportDataAdapter studentReportDataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_report_list_view);

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

    }
}
