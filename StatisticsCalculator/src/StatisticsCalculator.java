import java.util.ArrayList;

public class StatisticsCalculator {

	class SubjectScore {

		public String subject;
		public int score;

		public SubjectScore(String subject, int score) {
		   this.subject = subject;
		   this.score = score;
		}
	}

	ArrayList<SubjectScore> scores;

	public StatisticsCalculator() {
		scores = new ArrayList<SubjectScore>();
	}

    public void addScore(int score) {
		scores.add(new SubjectScore("Unknown Subject", score));
	}

	public void addScore(String subject, int score) {
		scores.add(new SubjectScore(subject, score));
	}

    public double getAverage() {
		if (scores.size() == 0) {
			return 0.0;
		}
		double total = 0;
		for (SubjectScore ss : scores) {
			total += ss.score;
		}
		return total / scores.size();
	}

    public String getGrade() {
		if (scores.size() == 0) {
			return "IC";
		}
		double avg = getAverage();
		if (avg < 50) {
			return "F";
		} else if (avg < 65) {
			return "P";
		} else if (avg < 75) {
			return "CR";
		} else if (avg < 85) {
			return "D";
		} else {
			return "HD";
		}
	}

	public String getBestSubject() {
		int best = Integer.MIN_VALUE;
		for (SubjectScore ss : scores) {
			if (ss.score > best) {
				best = ss.score;
			}
		}
		for (SubjectScore ss : scores) {
			if (ss.score == best) {
				return ss.subject + ": " + ss.score;
			}
		}
		return null;
	}

	public String getWorstSubject() {
		int worst = Integer.MAX_VALUE;
		for (SubjectScore ss : scores) {
			if (ss.score < worst) {
				worst = ss.score;
			}
		}
		for (SubjectScore ss : scores) {
			if (ss.score == worst) {
				return ss.subject + ": " + ss.score;
			}
		}
		return null;
	}

}
