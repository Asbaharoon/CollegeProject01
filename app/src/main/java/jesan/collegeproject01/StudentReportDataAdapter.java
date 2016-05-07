package jesan.collegeproject01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hassan M.Ashraful on 5/7/2016.
 */
public class StudentReportDataAdapter extends ArrayAdapter {
    List list = new ArrayList();

    public StudentReportDataAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class LayoutHandler{
        TextView idTV, dateTV, verdictTV;

    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() { return list.size(); }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LayoutHandler layoutHandler;

        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_list_student_report, parent, false);
            layoutHandler = new LayoutHandler();
            layoutHandler.idTV = (TextView) row.findViewById(R.id.idTV);
            layoutHandler.dateTV = (TextView) row.findViewById(R.id.dateTV);
            layoutHandler.verdictTV = (TextView) row.findViewById(R.id.verdictTV);
            row.setTag(layoutHandler);


        }

        else {
            layoutHandler = (LayoutHandler) row.getTag();

        }

        final StudentReportProvider studentReportProvider = (StudentReportProvider) this.getItem(position);
        layoutHandler.idTV.setText(studentReportProvider.getIds());
        layoutHandler.dateTV.setText(studentReportProvider.getDate());
        layoutHandler.verdictTV.setText(studentReportProvider.getVerdict());

        return row;
    }
}
