package jesan.collegeproject01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Hassan M.Ashraful on 5/6/2016.
 */
public class FeatureWork extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.featurelayout);
    }

    public void addStudentBTN(View view){
        Intent intent = new Intent(getApplicationContext(), StudentContactActivity.class);
        startActivity(intent);
    }

    public void attendenceBTN(View view){
        Intent intent = new Intent(getApplicationContext(), StudentDataListActivity.class);
        startActivity(intent);
    }

    public void reportBTN(View view){
        Intent intent = new Intent(getApplicationContext(), StudentReportListActivity.class);
        startActivity(intent);
    }

    public void searchStudentBTN(View view){
        Intent intent = new Intent(getApplicationContext(), SearchContactActivity.class);
        startActivity(intent);


    }
}
