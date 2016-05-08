package jesan.collegeproject01;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Hassan M.Ashraful on 5/4/2016.
 */
public class StudentListDataAdapter extends ArrayAdapter {

    List list = new ArrayList();

    DataBaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;
    StudentReportProvider studentReportProvider;
    SettingsActivity settingsActivity;
    private static SharedPreferences sharedPreferences;


    public StudentListDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler{
        TextView nameTV, rollTV, numberTV;
        Button presentBTN, absentBTN;
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {





        View row = convertView;
        LayoutHandler layoutHandler;
        sharedPreferences = getContext().getSharedPreferences("jesan.collegeproject01", 0);

        settingsActivity = new SettingsActivity();
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_list_student_info, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.nameTV = (TextView) row.findViewById(R.id.nameTV);
            layoutHandler.rollTV = (TextView) row.findViewById(R.id.rollTV);
            layoutHandler.numberTV = (TextView) row.findViewById(R.id.numberTV);
            layoutHandler.presentBTN = (Button) row.findViewById(R.id.present);
            layoutHandler.absentBTN = (Button) row.findViewById(R.id.absent);
            row.setTag(layoutHandler);


        }

        else {
            layoutHandler = (LayoutHandler) row.getTag();

        }

        final StudentDataProvider studentDataProvider = (StudentDataProvider) this.getItem(position);

        layoutHandler.nameTV.setText(studentDataProvider.getName());
        layoutHandler.rollTV.setText(studentDataProvider.getRoll());
        layoutHandler.numberTV.setText(studentDataProvider.getMob());
        layoutHandler.numberTV.setVisibility(View.INVISIBLE);


        layoutHandler.presentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sharedPreferences.getBoolean("PresentSMSon", false)){
                    //sendMsg(studentDataProvider.getMob(), "Present Today");
                    Toast.makeText(getContext(),"MESSEGE SEND", Toast.LENGTH_SHORT).show();
                }



                Toast.makeText(getContext(), studentDataProvider.getMob(), Toast.LENGTH_SHORT).show();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = df.format(c.getTime());
                //String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                studentReportProvider = new StudentReportProvider(studentDataProvider.getName(), studentDataProvider.getRoll(), date, "Absent");
                dataBaseHelper = new DataBaseHelper(getContext());
                sqLiteDatabase = dataBaseHelper.getWritableDatabase();
                dataBaseHelper.addStudentReport(studentDataProvider.getName(), studentDataProvider.getRoll(), date, "Present", sqLiteDatabase);
                dataBaseHelper.close();
            }
        });


        layoutHandler.absentBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (sharedPreferences.getBoolean("AbsentSMSon", false)){
                    sendMsg(studentDataProvider.getMob(), "Absent Today");
                    Toast.makeText(getContext(),"MESSEGE 2 SEND", Toast.LENGTH_SHORT).show();
                }


                Toast.makeText(getContext(), studentDataProvider.getMob(), Toast.LENGTH_SHORT).show();
                //String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date2 = df.format(c.getTime());
                studentReportProvider = new StudentReportProvider(studentDataProvider.getName(),studentDataProvider.getRoll(), date2, "Absent");
                dataBaseHelper = new DataBaseHelper(getContext());
                sqLiteDatabase = dataBaseHelper.getWritableDatabase();
                dataBaseHelper.addStudentReport(studentDataProvider.getName(), studentDataProvider.getRoll(), date2, "Absent", sqLiteDatabase);
                dataBaseHelper.close();
            }
        });


        return row;
    }

    protected void sendMsg(String number, String messege) {
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number, null, messege, null, null);

    }




}
