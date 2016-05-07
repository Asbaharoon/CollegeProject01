package jesan.collegeproject01;

/**
 * Created by Hassan M.Ashraful on 5/7/2016.
 */
public class StudentReportProvider {
    private String ids;
    private String date;
    private String verdict;
    private String smsOnOff;

    public String getSmsOnOff() {
        return smsOnOff;
    }

    public void setSmsOnOff(String smsOnOff) {
        this.smsOnOff = smsOnOff;
    }

    public StudentReportProvider(String ids, String date, String verdict) {
        this.ids = ids;
        this.date = date;
        this.verdict = verdict;
    }

    public String getDate() {return date; }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
}
