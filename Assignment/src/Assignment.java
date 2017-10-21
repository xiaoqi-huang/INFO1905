import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Assignment implements SubmissionHistory {

    TreeMap<String, Student> students;
    TreeMap<Integer, ArrayList<String>> allGrades;

    public Assignment() {
    	students = new TreeMap<String, Student>();
        allGrades = new TreeMap<Integer, ArrayList<String>>();
    }

    @Override
    public Integer getBestGrade(String unikey) {
        // If param is null, throw exception
        if (unikey == null) {
            throw new IllegalArgumentException();
        }
        // If unikey does not exist, return null
        if (!students.containsKey(unikey)) {
            return null;
        }
        // Return the best grade
        Student s = students.get(unikey);
        return s.getBestGrade();
    }

    @Override
    public Submission getSubmissionFinal(String unikey) {
        // If param is null, throw exception
        if (unikey == null) {
            throw new IllegalArgumentException();
        }
        // If unikey does not exist, return null
        if (!students.containsKey(unikey)) {
            return null;
        }
        // Return the final submission
        Student s = students.get(unikey);
        return s.getLastSubmission();
    }

    @Override
    public Submission getSubmissionBefore(String unikey, Date deadline) {
        // If any param is null, throw exception
        if (unikey == null || deadline == null) {
            throw new IllegalArgumentException();
        }
        // If unikey does not exist, return null
        if (!students.containsKey(unikey)) {
            return null;
        }
        // Return the most recent submission before or on the deadline
        Student s = students.get(unikey);
        return s.getSubmissionBefore(deadline);
    }

    @Override
    public Submission add(String unikey, Date timestamp, Integer grade) {
        // If any param is null, throw exception
        if (unikey == null || timestamp == null || grade == null) {
            throw new IllegalArgumentException();
        }
        // Update students
        Submission newSubmission = new MySubmission(unikey, timestamp, grade);
        // If the submission is made by a new student (unikey), make a new Student for the unikey, store the submission to Student and put <unikey, Student> to students
        if (!students.containsKey(unikey)) {
            Student newStudent = new Student();
            newStudent.add(newSubmission);
            students.put(unikey, newStudent);
        }
        // If the unikey exists, add the submission to corresponding Student
        else {
            Student s = students.get(unikey);
            s.add(newSubmission);
        }
        // Update allGrades
        // If it is a new grade, make a new ArrayList for the grade, put <grade, ArrayList> to allGrades
        if (!allGrades.containsKey(grade)) {
            ArrayList<String> first = new ArrayList<String>();
            first.add(unikey);
            allGrades.put(grade, first);
        }
        // If the grade exists
        else {
            allGrades.get(grade).add(unikey);
        }
        return newSubmission;
    }

    @Override
    public void remove(Submission submission) {
        // If param is null or the submission does not exist, throw exception
        if (submission == null || (!students.containsKey(submission.getUnikey()))) {
            throw new IllegalArgumentException();
        }
        // Remove the submission from the Student object
        // If there are no submissions associated to the unikey after previous removal, remove the unikey
        Student s = students.get(submission.getUnikey());
        s.remove(submission);
        if (s.madeNoSubmission()) {
            students.remove(submission.getUnikey());
        }
        // Remove the unikey from the corresponding entry
        // If there are no unikeys associated to the grade after previous removal, remove the grade
        allGrades.get(submission.getGrade()).remove(submission.getUnikey());
        if (allGrades.get(submission.getGrade()).isEmpty()) {
            allGrades.remove(submission.getGrade());
        }
    }

    @Override
    public List<String> listTopStudents() {
        // If there are no submissions, return an empty List
        if (allGrades.isEmpty()) {
            return new ArrayList<String>();
        }
        // Get the ArrayList of unikeys which have achieved the best grade
        // Remove duplicates
        ArrayList<String> topStudents = allGrades.lastEntry().getValue();
        Set<String> cleaner = new HashSet<>();
        cleaner.addAll(topStudents);
        ArrayList<String> cleanTopStudent = new ArrayList<String>();
        cleanTopStudent.addAll(cleaner);
        return cleanTopStudent;
    }

    @Override
    public List<String> listRegressions() {
        // If there are no submissions, return an empty List
        if (students.isEmpty()) {
            return new ArrayList<String>();
        }
        // Go through every entry in students
        // Return students who are regressed
        ArrayList<String> regressed = new ArrayList<String>();
        for(Map.Entry<String, Student> entry : students.entrySet()) {
            Student s = entry.getValue();
            if (s.isRegressed()) {
                regressed.add(entry.getKey());
            }
        }
        return regressed;
    }
}
