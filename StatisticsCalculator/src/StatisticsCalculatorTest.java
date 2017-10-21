import static org.junit.Assert.*;

import org.junit.Test;

public class StatisticsCalculatorTest {

	static final double epsilon = 1e-15;

	@Test
	public void testEmpty() {
		StatisticsCalculator sc = new StatisticsCalculator();
		assertEquals(0.0, sc.getAverage(), epsilon);
		assertEquals("IC", sc.getGrade());
	}

	@Test
	public void testOneScoreAverage() {
		StatisticsCalculator sc = new StatisticsCalculator();
		sc.addScore(60);
		assertEquals(60, sc.getAverage(), epsilon);
	}

	@Test
	public void testTwoScoresAverage() {
		StatisticsCalculator sc = new StatisticsCalculator();
		sc.addScore(60);
		sc.addScore(71);
		assertEquals(65.5, sc.getAverage(), epsilon);
	}

	@Test
	public void testThreeScoresAverage() {
		StatisticsCalculator sc = new StatisticsCalculator();
		sc.addScore(60);
		sc.addScore(70);
		sc.addScore(80);
		assertEquals((60 + 70 + 80) / 3, sc.getAverage(), epsilon);
	}

	@Test
	public void testFourScoresAverage() {
		StatisticsCalculator sc = new StatisticsCalculator();
		sc.addScore(60);
		sc.addScore(71);
		sc.addScore(71);
		sc.addScore(71);
		assertEquals(68.25, sc.getAverage(), epsilon);
	}

	@Test
	public void testGrade() {
		StatisticsCalculator sc1 = new StatisticsCalculator();
		sc1.addScore(40);
		assertEquals("F", sc1.getGrade());
		StatisticsCalculator sc2 = new StatisticsCalculator();
		sc2.addScore(55);
		assertEquals("P", sc2.getGrade());
		StatisticsCalculator sc3 = new StatisticsCalculator();
		sc3.addScore(70);
		assertEquals("CR", sc3.getGrade());
		StatisticsCalculator sc4 = new StatisticsCalculator();
		sc4.addScore(80);
		assertEquals("D", sc4.getGrade());
		StatisticsCalculator sc5 = new StatisticsCalculator();
		sc5.addScore(90);
		assertEquals("HD", sc5.getGrade());
	}

	@Test
	public void testEdgeGrade() {
		StatisticsCalculator sc1 = new StatisticsCalculator();
		sc1.addScore(49);
		sc1.addScore(50);
		assertEquals("F", sc1.getGrade());
		StatisticsCalculator sc2 = new StatisticsCalculator();
		sc2.addScore(50);
		assertEquals("P", sc2.getGrade());
		StatisticsCalculator sc3 = new StatisticsCalculator();
		sc3.addScore(64);
		sc3.addScore(65);
		assertEquals("P", sc3.getGrade());
		StatisticsCalculator sc4 = new StatisticsCalculator();
		sc4.addScore(65);
		assertEquals("CR", sc4.getGrade());
		StatisticsCalculator sc5 = new StatisticsCalculator();
		sc5.addScore(74);
		sc5.addScore(75);
		assertEquals("CR", sc5.getGrade());
		StatisticsCalculator sc6 = new StatisticsCalculator();
		sc6.addScore(75);
		assertEquals("D", sc6.getGrade());
		StatisticsCalculator sc7 = new StatisticsCalculator();
		sc7.addScore(84);
		sc7.addScore(85);
		assertEquals("D", sc7.getGrade());
		StatisticsCalculator sc8 = new StatisticsCalculator();
		sc8.addScore(85);
		assertEquals("HD", sc8.getGrade());
	}

	@Test
	public void testBestWorst_NormalScores() {
		StatisticsCalculator sc = new StatisticsCalculator();
		sc.addScore("Physics", 80);
		sc.addScore("Chemistry", 85);
		sc.addScore("Biology", 90);
		sc.addScore("Mathematics", 95);
		assertEquals("Mathematics: 95", sc.getBestSubject());
		assertEquals("Physics: 80", sc.getWorstSubject());
	}

	@Test
	public void testBestWorst_OneScore() {
		StatisticsCalculator sc = new StatisticsCalculator();
		sc.addScore("Physics", 80);
		assertEquals("Physics: 80", sc.getBestSubject());
		assertEquals("Physics: 80", sc.getWorstSubject());
	}

	@Test
	public void testBestWorst_NoScores() {
		StatisticsCalculator sc = new StatisticsCalculator();
		assertNull(sc.getBestSubject());
		assertNull(sc.getWorstSubject());
	}

	@Test
	public void testBestWorst_SameSubjects() {
		StatisticsCalculator sc = new StatisticsCalculator();
		sc.addScore("Biology", 80);
		sc.addScore("Biology", 90);
		assertEquals("Biology: 90", sc.getBestSubject());
		assertEquals("Biology: 80", sc.getWorstSubject());
	}

	@Test
	public void testBestWorst_SameScores() {
		StatisticsCalculator sc = new StatisticsCalculator();
		sc.addScore("Physics", 80);
		sc.addScore("Chemistry", 80);
		sc.addScore("Biology", 90);
		sc.addScore("Mathematics", 90);
		assertEquals("Biology: 90", sc.getBestSubject());
		assertEquals("Physics: 80", sc.getWorstSubject());
	}

	@Test
	public void testOldAddScore() {
		StatisticsCalculator sc = new StatisticsCalculator();
		sc.addScore(80);
		assertEquals("Unknown Subject: 80", sc.getBestSubject());
	}

}
