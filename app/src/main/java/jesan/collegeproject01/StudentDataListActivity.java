package jesan.collegeproject01;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

/**
 * Created by Hassan M.Ashraful on 5/4/2016.
 */
public class StudentDataListActivity extends ActionBarActivity{
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DataBaseHelper dataBaseHelper;
    Cursor cursor;

    StudentListDataAdapter studentListDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info_list_view);
        listView = (ListView) findViewById(R.id.info_list_view);

        studentListDataAdapter = new StudentListDataAdapter(getApplicationContext(), R.layout.row_list_student_info);
        listView.setAdapter(studentListDataAdapter);
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        cursor = dataBaseHelper.getStudentInfo(sqLiteDatabase);
        if (cursor.moveToNext()){
            do {
                String name, mob, roll;
                name = cursor.getString(0);
                roll = cursor.getString(1);
                mob = cursor.getString(2);

                StudentDataProvider studentDataProvider = new StudentDataProvider(name,roll,mob);
                studentListDataAdapter.add(studentDataProvider);

            }while (cursor.moveToNext());
        }


    }


}
