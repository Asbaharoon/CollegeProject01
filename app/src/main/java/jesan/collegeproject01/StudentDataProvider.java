package jesan.collegeproject01;

/**
 * Created by Hassan M.Ashraful on 5/4/2016.
 */
public class StudentDataProvider {

    private String name;
    private String mob;
    private String roll;

    public StudentDataProvider(String name, String roll, String mob) {
        this.name = name;
        this.mob = mob;
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMob() {
        return mob;
    }

    public void setMob(String mob) {
        this.mob = mob;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }
}
