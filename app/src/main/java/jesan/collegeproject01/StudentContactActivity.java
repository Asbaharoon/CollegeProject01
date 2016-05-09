package jesan.collegeproject01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Hassan M.Ashraful on 5/4/2016.
 */
public class StudentContactActivity extends Activity {
    EditText ContactName, ContactRoll, ContactMob;
    Context context = this;
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentinfromation);
        ContactName = (EditText) findViewById(R.id.nameET);
        ContactRoll = (EditText) findViewById(R.id.rollET);
        ContactMob = (EditText) findViewById(R.id.phnnumET);


    }

    public void addContact(View view){
        String name = ContactName.getText().toString();
        String roll = ContactRoll.getText().toString();
        String phnNum = ContactMob.getText().toString();

        dataBaseHelper = new DataBaseHelper(context);
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        dataBaseHelper.addStudentInfo(name, roll, phnNum, sqLiteDatabase);
        Toast.makeText(getBaseContext(), "data saved", Toast.LENGTH_SHORT).show();
        dataBaseHelper.close();
        Intent intent = new Intent(getApplicationContext(), FeatureWork.class);
        startActivity(intent);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_design, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.logout){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }


}
