package jesan.collegeproject01;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * Created by Hassan M.Ashraful on 5/7/2016.
 */
public class SettingsActivity extends AppCompatActivity {

    Switch absentSwitch, presentSwitch;

    boolean presentsmsOnOff, absentsmsOnOff;

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


}
