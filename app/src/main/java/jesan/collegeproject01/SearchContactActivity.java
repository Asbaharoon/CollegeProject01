package jesan.collegeproject01;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hassan M.Ashraful on 5/7/2016.
 */
public class SearchContactActivity extends ActionBarActivity {
    TextView nameShowTV, phnShowTV;
    EditText rollSearchET;
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;
    String search_roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_student);
        nameShowTV = (TextView) findViewById(R.id.nameShowTV);
        phnShowTV = (TextView) findViewById(R.id.phnShowTV);
        rollSearchET = (EditText) findViewById(R.id.rollSearchET);
        nameShowTV.setVisibility(View.GONE);
        phnShowTV.setVisibility(View.GONE);
    }

    public void searchBTN(View view){
        search_roll = rollSearchET.getText().toString();
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = dataBaseHelper.getContact(search_roll, sqLiteDatabase);
        if (cursor.moveToFirst()){
            String name = cursor.getString(0);
            String mobile = cursor.getString(1);
            nameShowTV.setText(name);
            phnShowTV.setText(mobile);
            nameShowTV.setVisibility(View.VISIBLE);
            phnShowTV.setVisibility(View.VISIBLE);

        }

    }


    public void updateBTN(View view){

    }

    public void deleteBTN(View view){
        dataBaseHelper = new DataBaseHelper(getApplicationContext());
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        dataBaseHelper.deleteInformation(search_roll, sqLiteDatabase);
        Toast.makeText(getApplicationContext(), "Contact Deleted", Toast.LENGTH_SHORT).show();

    }

}
