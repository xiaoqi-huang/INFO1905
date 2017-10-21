import java.util.Date;

public class MySubmission implements Submission {

    private String unikey;
    private Date timestamp;
    private Integer grade;

    public MySubmission(String unikey, Date timestamp, Integer grade) {
        this.unikey = unikey;
        this.timestamp = timestamp;
        this.grade = grade;
    }

    @Override
    public String getUnikey() {
        return this.unikey;
    }

    @Override
    public Date getTime() {
        return this.timestamp;
    }

    @Override
    public Integer getGrade() {
        return this.grade;
    }

}
