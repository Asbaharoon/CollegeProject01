package jesan.collegeproject01;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Hassan M.Ashraful on 5/4/2016.
 */
public class StudentDataListActivity extends AppCompatActivity{
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    DataBaseHelper dataBaseHelper;
    Cursor cursor;
    private SharedPreferences sharedPreferences;

    StudentListDataAdapter studentListDataAdapter;
    TextView nameteacherTV, idteacherTV, classteacherTV, secteacherTV, subteacherTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_info_list_view);
        listView = (ListView) findViewById(R.id.info_list_view);

        nameteacherTV = (TextView) findViewById(R.id.nameteacherTV);
        idteacherTV = (TextView) findViewById(R.id.idteacherTV);
        classteacherTV = (TextView) findViewById(R.id.classteacherTV);
        secteacherTV = (TextView) findViewById(R.id.secteacherTV);
        subteacherTV = (TextView) findViewById(R.id.subteacherTV);

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
