import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Student {

    private TreeMap<Date, Submission> timeSubmissions;
    private TreeMap<Integer, Integer> gradeCount;

    public Student() {
    	timeSubmissions = new TreeMap<Date, Submission>();
        gradeCount = new TreeMap<Integer, Integer>();
    }

    public void add(Submission submission) {
        // Update timeSubmissions
		timeSubmissions.put(submission.getTime(), submission);
        // Update gradeCount
        if (!gradeCount.containsKey(submission.getGrade())) {
            gradeCount.put(submission.getGrade(), 1);
        } else {
            Integer oldCount = gradeCount.get(submission.getGrade());
            gradeCount.replace(submission.getGrade(), (oldCount + 1));
        }
	}

    public void remove(Submission submission) {
        // Update timeSubmissions
        timeSubmissions.remove(submission.getTime());
        // Update gradeCount
        // If the grade is only one, remove the grade
        // Otherwise, decrease the count
        Integer oldCount = gradeCount.get(submission.getGrade());
        if (oldCount == 1) {
            gradeCount.remove(submission.getGrade());
        } else {
            gradeCount.replace(submission.getGrade(), (oldCount - 1));
        }
	}

    public Submission getLastSubmission() {
		return timeSubmissions.lastEntry().getValue();
	}

    public Submission getSubmissionBefore(Date deadline) {
        Map.Entry<Date, Submission> entry = timeSubmissions.floorEntry(deadline);
        // If there are no submissions or no submissions before or on the deadline, return null
        if (entry == null) {
            return null;
        }
		return entry.getValue();
	}

    public Integer getBestGrade() {
        return gradeCount.lastEntry().getKey();
    }

	public boolean isRegressed() {
		Integer bestSubmission = getBestGrade();
        Integer finalSubmission = getLastSubmission().getGrade();
		return (finalSubmission < bestSubmission);
	}

	public boolean madeNoSubmission() {
		return timeSubmissions.isEmpty();
	}

}
