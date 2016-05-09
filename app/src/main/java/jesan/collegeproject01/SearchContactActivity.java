package jesan.collegeproject01;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Hassan M.Ashraful on 5/7/2016.
 */
public class SearchContactActivity extends AppCompatActivity {

    EditText nameEdit, rollEdit, phnEdit;
    EditText rollSearchET;
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_student);

        rollSearchET = (EditText) findViewById(R.id.rollSearchET);

        nameEdit = (EditText) findViewById(R.id.nameEdit);
        rollEdit = (EditText) findViewById(R.id.rollEdit);
        phnEdit = (EditText) findViewById(R.id.phnEdit);

        nameEdit.setVisibility(View.GONE);
        rollEdit.setVisibility(View.GONE);
        phnEdit.setVisibility(View.GONE);

    }

    public void searchBTN(View view){
        search_roll = rollSearchET.getText().toString();
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = dataBaseHelper.getContact(search_roll, sqLiteDatabase);
        String new_roll = search_roll;
        if (cursor.moveToFirst()){
            String name = cursor.getString(0);
            String mobile = cursor.getString(1);


            nameEdit.setText(name);
            rollEdit.setText(new_roll);
            phnEdit.setText(mobile);
            nameEdit.setVisibility(View.VISIBLE);
            rollEdit.setVisibility(View.VISIBLE);
            phnEdit.setVisibility(View.VISIBLE);


        }



    }


    public void updateBTN(View view){

        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        String name, roll, mobile;
        name = nameEdit.getText().toString();
        roll = rollEdit.getText().toString();
        mobile = phnEdit.getText().toString();
        int count = dataBaseHelper.updateStudent(search_roll, roll, name, mobile, sqLiteDatabase);
        Toast.makeText(getApplicationContext(), count+" contact updated", Toast.LENGTH_SHORT).show();

        finish();


    }

    public void deleteBTN(View view){
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        dataBaseHelper.deleteInformation(search_roll, sqLiteDatabase);

        Intent intent = new Intent(getApplicationContext(), FeatureWork.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Contact Deleted", Toast.LENGTH_SHORT).show();

    }

}
