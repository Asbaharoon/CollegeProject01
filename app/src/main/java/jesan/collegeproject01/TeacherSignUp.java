package jesan.collegeproject01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Hassan M.Ashraful on 4/5/2016.
 */
public class TeacherSignUp extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teachersignup);
    }

    public void signUp(View view){
        Intent intent = new Intent(getApplicationContext(), DataBaseCSVExport.class);
        startActivity(intent);
    }
}
