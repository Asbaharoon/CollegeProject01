package jesan.collegeproject01;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Hassan M.Ashraful on 4/5/2016.
 *
 * This project is my property. Not for general use.
 */
public class TeacherSignUp extends AppCompatActivity{

    EditText teacherName, teacherid, teacherSub, teacherClass, teacherSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachersignup);
        teacherName = (EditText) findViewById(R.id.teacherName);
        teacherid = (EditText) findViewById(R.id.teacherid);
        teacherClass = (EditText) findViewById(R.id.teacherClass);
        teacherSec = (EditText) findViewById(R.id.teacherSec);
        teacherSub = (EditText) findViewById(R.id.teacherSub);



    }

    public void signUp(View view){

        SavePreferences("NAME",teacherName.getText().toString());
        SavePreferences("ID", teacherid.getText().toString());
        SavePreferences("CLASS", teacherClass.getText().toString());
        SavePreferences("SEC", teacherSec.getText().toString());
        SavePreferences("SUB", teacherSub.getText().toString());
        Toast.makeText(getApplicationContext(), "Teacher Data Inserted", Toast.LENGTH_SHORT).show();

    }


    private void SavePreferences(String key, String value){
        SharedPreferences sharedPreferences = getSharedPreferences("jesan.collegeproject01.SIGNUP",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }



}
