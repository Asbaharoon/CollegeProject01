package jesan.collegeproject01;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * Created by Hassan M.Ashraful on 5/7/2016.
 */
public class SettingsActivity extends AppCompatActivity {

    Switch absentSwitch, presentSwitch;

    DataBaseHelper sampleDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        absentSwitch = (Switch) findViewById(R.id.absentOnBTN);
        presentSwitch = (Switch) findViewById(R.id.presentOnBTN);


        SharedPreferences sharedPrefs = getSharedPreferences("jesan.collegeproject01", MODE_PRIVATE);
        presentSwitch.setChecked(sharedPrefs.getBoolean("PresentSMSon", false));

        SharedPreferences sharedPref = getSharedPreferences("jesan.collegeproject01", MODE_PRIVATE);
        absentSwitch.setChecked(sharedPref.getBoolean("AbsentSMSon", false));




        presentSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {




                if (isChecked) {

                    SharedPreferences.Editor editor = getSharedPreferences("jesan.collegeproject01", MODE_PRIVATE).edit();
                    editor.putBoolean("PresentSMSon", true);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "SMS ON", Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferences.Editor editor = getSharedPreferences("jesan.collegeproject01", MODE_PRIVATE).edit();
                    editor.putBoolean("PresentSMSon", false);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "SMS OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });

        absentSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    SharedPreferences.Editor editor = getSharedPreferences("jesan.collegeproject01", MODE_PRIVATE).edit();
                    editor.putBoolean("AbsentSMSon", true);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "SMS ON", Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferences.Editor editor = getSharedPreferences("jesan.collegeproject01", MODE_PRIVATE).edit();
                    editor.putBoolean("AbsentSMSon", false);
                    editor.commit();

                    Toast.makeText(getApplicationContext(), "SMS OFF", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void downCSV(View view){
        exportCSV();
    }


    public void exportCSV() {
        sampleDB = new DataBaseHelper(getApplicationContext());


        File dbFile = getDatabasePath(sampleDB.getDBname());
        DataBaseHelper dbhelper = new DataBaseHelper(getApplicationContext());
        File exportDir = new File(Environment.getExternalStorageDirectory(), "");
        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }

        File file = new File(exportDir, "StudentReport.csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            Cursor curCSV = db.rawQuery("SELECT * FROM student_report", null);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                //Which column you want to exprort
                String arrStr[] = {curCSV.getString(0), curCSV.getString(1), curCSV.getString(2), curCSV.getString(3)};
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
        } catch (Exception sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }


    }


}
