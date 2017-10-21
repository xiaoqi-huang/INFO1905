import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class AdditionalAssignmentTest {

	// Set up JUnit to be able to check for expected exceptions
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// This will make it a bit easier for us to make Date objects
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	// This will make it a bit easier for us to make Date objects
	private static Date getDate(String s) {
		try {
			return df.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}
		// unreachable
		return null;
	}

	// helper method to compare two Submissions using assertions
	private static void testHelperEquals(Submission expected, Submission actual) {
		assertEquals(expected.getUnikey(), actual.getUnikey());
		assertEquals(expected.getTime(), actual.getTime());
		assertEquals(expected.getGrade(), actual.getGrade());
	}

	// helper method to compare two Submissions using assertions
	private static void testHelperEquals(String unikey, Date timestamp, Integer grade, Submission actual) {
		assertEquals(unikey, actual.getUnikey());
		assertEquals(timestamp, actual.getTime());
		assertEquals(grade, actual.getGrade());
	}

	// helper method that adds a new appointment AND checks the return value is correct
	private static Submission testHelperAdd(SubmissionHistory history, String unikey, Date timestamp, Integer grade) {
		Submission s = history.add(unikey, timestamp, grade);
		testHelperEquals(unikey, timestamp, grade, s);
		return s;
	}

	// Helper method to build the trivial example submission history
	private SubmissionHistory buildTinyExample() {
		SubmissionHistory history = new Assignment();
		// submission A:
		history.add("aaaa1234", getDate("2016/09/03 09:00:00"), 66);
		// submission B:
		history.add("aaaa1234", getDate("2016/09/03 16:00:00"), 86);
		// submission C:
		history.add("cccc1234", getDate("2016/09/03 16:00:00"), 73);
		// submission D:
		history.add("aaaa1234", getDate("2016/09/03 18:00:00"), 40);
		return history;
	}

	/* *************************************************************************
	 * testGetBestGrade
	 * ************************************************************************* */
	 @Test(timeout = 100)
	 public void testGetBestGrade_Null() {
		 SubmissionHistory history = new Assignment();
		 Submission a = testHelperAdd(history, "aaa", new Date(100000), 10);
		 Submission b = testHelperAdd(history, "bbb", new Date(200000), 10000);
		 Submission c = testHelperAdd(history, "ccc", new Date(300000), 50);
		 thrown.expect(IllegalArgumentException.class);
		 history.getBestGrade(null);
	 }
	 @Test(timeout = 100)
	 public void testGetBestGrade_NoSubmissions() {
		 SubmissionHistory history = new Assignment();
		 Submission a = testHelperAdd(history, "aaa", new Date(100000), 10);
		 Submission b = testHelperAdd(history, "bbb", new Date(200000), 10000);
		 Submission c = testHelperAdd(history, "ccc", new Date(300000), 50);
		 assertNull(history.getBestGrade("ddd"));
	 }
	 @Test(timeout = 100)
 	 public void testGetBestGrade_OneSubmissions() {
 		 SubmissionHistory history = new Assignment();
 		 Submission a = testHelperAdd(history, "aaa", new Date(100000), 10);
 		 Submission b = testHelperAdd(history, "bbb", new Date(200000), 10000);
 		 Submission c = testHelperAdd(history, "ccc", new Date(300000), 50);
 		 assertEquals(new Integer(10), history.getBestGrade("aaa"));
 	 }
	 @Test(timeout = 100)
 	 public void testGetBestGrade_ThreeSubmissions() {
 		 SubmissionHistory history = new Assignment();
 		 Submission a1 = testHelperAdd(history, "aaa", new Date(100000), 10);
		 Submission a2 = testHelperAdd(history, "aaa", new Date(110000), 78);
		 Submission a3 = testHelperAdd(history, "aaa", new Date(120000), 46);
 		 Submission b = testHelperAdd(history, "bbb", new Date(200000), 10000);
 		 Submission c = testHelperAdd(history, "ccc", new Date(300000), 50);
 		 assertEquals(new Integer(78), history.getBestGrade("aaa"));
 	 }
	 @Test(timeout = 100)
 	 public void testGetBestGrade_SameGrades() {
 		 SubmissionHistory history = new Assignment();
 		 Submission a1 = testHelperAdd(history, "aaa", new Date(100000), 78);
		 Submission a2 = testHelperAdd(history, "aaa", new Date(110000), 78);
		 Submission a3 = testHelperAdd(history, "aaa", new Date(120000), 46);
 		 Submission b = testHelperAdd(history, "bbb", new Date(200000), 10000);
 		 Submission c = testHelperAdd(history, "ccc", new Date(300000), 50);
 		 assertEquals(new Integer(78), history.getBestGrade("aaa"));
 	 }
	 @Test(timeout = 100)
 	 public void testGetBestGrade_Remove() {
 		 SubmissionHistory history = new Assignment();
 		 Submission a1 = testHelperAdd(history, "aaa", new Date(100000), 78);
		 Submission a2 = testHelperAdd(history, "aaa", new Date(110000), 78);
		 Submission a3 = testHelperAdd(history, "aaa", new Date(120000), 46);
 		 Submission b = testHelperAdd(history, "bbb", new Date(200000), 10000);
 		 Submission c = testHelperAdd(history, "ccc", new Date(300000), 50);
 		 assertEquals(new Integer(78), history.getBestGrade("aaa"));
		 history.remove(a1);
		 assertEquals(new Integer(78), history.getBestGrade("aaa"));
		 history.remove(a2);
		 assertEquals(new Integer(46), history.getBestGrade("aaa"));
		 history.remove(a3);
		 assertNull(history.getBestGrade("aaa"));
	 }
	 @Test(timeout = 100)
 	 public void testGetBestGrade_Add() {
 		 SubmissionHistory history = new Assignment();
 		 Submission a1 = testHelperAdd(history, "aaa", new Date(100000), 78);
		 assertEquals(new Integer(78), history.getBestGrade("aaa"));
		 Submission a2 = testHelperAdd(history, "aaa", new Date(200000), 78);
		 assertEquals(new Integer(78), history.getBestGrade("aaa"));
		 Submission a3 = testHelperAdd(history, "aaa", new Date(300000), 46);
		 assertEquals(new Integer(78), history.getBestGrade("aaa"));
		 Submission a4 = testHelperAdd(history, "aaa", new Date(400000), 90);
		 assertEquals(new Integer(90), history.getBestGrade("aaa"));
	 }
	 @Test(timeout = 100)
 	 public void testGetBestGrade_AddAndRemove() {
 		 SubmissionHistory history = new Assignment();
 		 Submission a1 = testHelperAdd(history, "aaa", new Date(100000), 78);
		 assertEquals(new Integer(78), history.getBestGrade("aaa"));
		 Submission a2 = testHelperAdd(history, "aaa", new Date(200000), 78);
		 assertEquals(new Integer(78), history.getBestGrade("aaa"));
		 Submission a3 = testHelperAdd(history, "aaa", new Date(300000), 46);
		 assertEquals(new Integer(78), history.getBestGrade("aaa"));
		 Submission a4 = testHelperAdd(history, "aaa", new Date(400000), 90);
		 assertEquals(new Integer(90), history.getBestGrade("aaa"));
		 history.remove(a1);
		 assertEquals(new Integer(90), history.getBestGrade("aaa"));
		 history.remove(a4);
		 assertEquals(new Integer(78), history.getBestGrade("aaa"));
		 Submission a5 = testHelperAdd(history, "aaa", new Date(500000), 84);
		 assertEquals(new Integer(84), history.getBestGrade("aaa"));
	 }

	 /* ************************************************************************
 	  * testGetSubmissionFinal
 	  * ************************************************************************ */
	 @Test(timeout = 100)
	 public void testGetSubmissionFinal_Null() {
		 SubmissionHistory history = new Assignment();
		 Submission a = testHelperAdd(history, "aaa", new Date(100000), 10);
		 Submission b = testHelperAdd(history, "bbb", new Date(200000), 10000);
		 Submission c = testHelperAdd(history, "ccc", new Date(300000), 50);
		 thrown.expect(IllegalArgumentException.class);
		 history.getSubmissionFinal(null);
	 }
	 @Test(timeout = 100)
	 public void testGetSubmissionFinal_NoSubmissions() {
		 SubmissionHistory history = new Assignment();
		 Submission a = testHelperAdd(history, "aaa", new Date(100000), 10);
		 Submission b = testHelperAdd(history, "bbb", new Date(200000), 10000);
		 Submission c = testHelperAdd(history, "ccc", new Date(300000), 50);
		 assertNull(history.getSubmissionFinal("ddd"));
	 }
	 @Test(timeout = 100)
	 public void testGetSubmissionFinal_OneSubmission() {
		 SubmissionHistory history = new Assignment();
		 Submission a = testHelperAdd(history, "aaa", new Date(100000), 10);
		 Submission b = testHelperAdd(history, "bbb", new Date(200000), 10000);
		 Submission c = testHelperAdd(history, "ccc", new Date(300000), 50);
		 testHelperEquals(a, history.getSubmissionFinal("aaa"));
	 }
	 @Test(timeout = 100)
 	 public void testGetSubmissionFinal_ThreeSubmissions() {
 		 SubmissionHistory history = new Assignment();
 		 Submission a1 = testHelperAdd(history, "aaa", new Date(100000), 10);
		 Submission a2 = testHelperAdd(history, "aaa", new Date(120000), 78);
		 Submission a3 = testHelperAdd(history, "aaa", new Date(110000), 46);
 		 Submission b = testHelperAdd(history, "bbb", new Date(200000), 10000);
 		 Submission c = testHelperAdd(history, "ccc", new Date(300000), 50);
 		 testHelperEquals(a2, history.getSubmissionFinal("aaa"));
 	 }
	 @Test(timeout = 100)
 	 public void testGetSubmissionFinal_Remove() {
 		 SubmissionHistory history = new Assignment();
 		 Submission a1 = testHelperAdd(history, "aaa", new Date(100000), 78);
		 Submission a2 = testHelperAdd(history, "aaa", new Date(200000), 78);
		 Submission a3 = testHelperAdd(history, "aaa", new Date(300000), 46);
		 testHelperEquals(a3, history.getSubmissionFinal("aaa"));
		 history.remove(a1);
		 testHelperEquals(a3, history.getSubmissionFinal("aaa"));
		 history.remove(a3);
		 testHelperEquals(a2, history.getSubmissionFinal("aaa"));
		 history.remove(a2);
		 assertNull(history.getSubmissionFinal("aaa"));
	 }
	 @Test(timeout = 100)
 	 public void testGetSubmissionFinal_Add() {
 		 SubmissionHistory history = new Assignment();
 		 Submission a1 = testHelperAdd(history, "aaa", new Date(100000), 78);
		 testHelperEquals(a1, history.getSubmissionFinal("aaa"));
		 Submission a2 = testHelperAdd(history, "aaa", new Date(200000), 78);
		 testHelperEquals(a2, history.getSubmissionFinal("aaa"));
		 Submission a3 = testHelperAdd(history, "aaa", new Date(150000), 46);
		 testHelperEquals(a2, history.getSubmissionFinal("aaa"));
		 Submission a4 = testHelperAdd(history, "aaa", new Date(400000), 90);
		 testHelperEquals(a4, history.getSubmissionFinal("aaa"));
	 }
	 @Test(timeout = 100)
 	 public void testGetSubmissionFinal_AddAndRemove() {
 		 SubmissionHistory history = new Assignment();
 		 Submission a1 = testHelperAdd(history, "aaa", new Date(100000), 78);
		 testHelperEquals(a1, history.getSubmissionFinal("aaa"));
		 Submission a2 = testHelperAdd(history, "aaa", new Date(200000), 78);
		 testHelperEquals(a2, history.getSubmissionFinal("aaa"));
		 Submission a3 = testHelperAdd(history, "aaa", new Date(150000), 46);
		 testHelperEquals(a2, history.getSubmissionFinal("aaa"));
		 Submission a4 = testHelperAdd(history, "aaa", new Date(120000), 90);
		 testHelperEquals(a2, history.getSubmissionFinal("aaa"));
		 history.remove(a2);
		 testHelperEquals(a3, history.getSubmissionFinal("aaa"));
		 history.remove(a1);
		 testHelperEquals(a3, history.getSubmissionFinal("aaa"));
		 Submission a5 = testHelperAdd(history, "aaa", new Date(140000), 84);
		 testHelperEquals(a3, history.getSubmissionFinal("aaa"));
	 }

	 /* ************************************************************************
	  * testGetSubmissionBefore
	  * ************************************************************************ */
	  @Test(timeout = 100)
	  public void testGetSubmissionBefore_Null() {
		  SubmissionHistory history = new Assignment();
		  Submission a = testHelperAdd(history, "aaa", getDate("2016/09/03 09:00:00"), 10);
		  Submission b = testHelperAdd(history, "bbb", getDate("2016/09/03 10:00:00"), 20);
		  Submission c = testHelperAdd(history, "ccc", getDate("2016/09/03 11:00:00"), 30);
		  thrown.expect(IllegalArgumentException.class);
		  history.getSubmissionBefore(null, getDate("2016/09/03 10:00:00"));
		  thrown.expect(IllegalArgumentException.class);
		  history.getSubmissionBefore("aaa", null);
		  thrown.expect(IllegalArgumentException.class);
		  history.getSubmissionBefore(null, null);
	  }
	  @Test(timeout = 100)
	  public void testGetSubmissionBefore_NoSubmissions() {
		  SubmissionHistory history = new Assignment();
		  Submission a = testHelperAdd(history, "aaa", getDate("2016/09/03 09:00:00"), 10);
		  Submission b = testHelperAdd(history, "bbb", getDate("2016/09/03 10:00:00"), 20);
		  Submission c = testHelperAdd(history, "ccc", getDate("2016/09/03 11:00:00"), 30);
		  assertNull(history.getSubmissionBefore("ddd", getDate("2016/09/03 13:00:00")));
	  }
	  @Test(timeout = 100)
	  public void testGetSubmissionBefore_NoBeforeSubmissions() {
		  SubmissionHistory history = new Assignment();
		  Submission a = testHelperAdd(history, "aaa", getDate("2016/09/03 09:00:00"), 10);
		  Submission b = testHelperAdd(history, "bbb", getDate("2016/09/03 10:00:00"), 20);
		  Submission c = testHelperAdd(history, "ccc", getDate("2016/09/03 11:00:00"), 30);
		  assertNull(history.getSubmissionBefore("aaa", getDate("2016/09/03 08:00:00")));
	  }
	  @Test(timeout = 100)
	  public void testGetSubmissionBefore_OnDeadline() {
		  SubmissionHistory history = new Assignment();
		  Submission a1 = testHelperAdd(history, "aaa", getDate("2016/09/03 09:00:00"), 10);
		  Submission a2 = testHelperAdd(history, "aaa", getDate("2016/09/03 10:00:00"), 10);
		  Submission a3 = testHelperAdd(history, "aaa", getDate("2016/09/03 11:00:00"), 10);
		  testHelperEquals(a2, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:00:00")));
	  }
	  @Test(timeout = 100)
	  public void testGetSubmissionBefore_BeforeDeadline() {
		  SubmissionHistory history = new Assignment();
		  Submission a1 = testHelperAdd(history, "aaa", getDate("2016/09/03 09:00:00"), 10);
		  Submission a2 = testHelperAdd(history, "aaa", getDate("2016/09/03 10:00:00"), 10);
		  Submission a3 = testHelperAdd(history, "aaa", getDate("2016/09/03 11:00:00"), 10);
		  testHelperEquals(a2, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
	  }
	  @Test(timeout = 100)
	  public void testGetSubmissionBefore_Remove_NoSubmissions() {
		  SubmissionHistory history = new Assignment();
		  Submission a1 = testHelperAdd(history, "aaa", getDate("2016/09/03 09:00:00"), 10);
		  Submission a2 = testHelperAdd(history, "aaa", getDate("2016/09/03 10:00:00"), 10);
		  Submission a3 = testHelperAdd(history, "aaa", getDate("2016/09/03 11:00:00"), 10);
		  testHelperEquals(a2, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  history.remove(a3);
		  testHelperEquals(a2, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  history.remove(a2);
		  testHelperEquals(a1, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  history.remove(a1);
		  assertNull(history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
	  }
	  @Test(timeout = 100)
	  public void testGetSubmissionBefore_Remove_NoBeforeSubmissions() {
		  SubmissionHistory history = new Assignment();
		  Submission a1 = testHelperAdd(history, "aaa", getDate("2016/09/03 09:00:00"), 10);
		  Submission a2 = testHelperAdd(history, "aaa", getDate("2016/09/03 10:00:00"), 10);
		  Submission a3 = testHelperAdd(history, "aaa", getDate("2016/09/03 11:00:00"), 10);
		  testHelperEquals(a2, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  history.remove(a2);
		  testHelperEquals(a1, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  history.remove(a1);
		  assertNull(history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
	  }
	  @Test(timeout = 100)
	  public void testGetSubmissionBefore_Add() {
		  SubmissionHistory history = new Assignment();
		  Submission a1 = testHelperAdd(history, "aaa", getDate("2016/09/03 09:00:00"), 10);
		  testHelperEquals(a1, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  Submission a2 = testHelperAdd(history, "aaa", getDate("2016/09/03 10:00:00"), 10);
		  testHelperEquals(a2, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  Submission a3 = testHelperAdd(history, "aaa", getDate("2016/09/03 11:00:00"), 10);
		  testHelperEquals(a2, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  Submission a4 = testHelperAdd(history, "aaa", getDate("2016/09/03 10:20:00"), 10);
		  testHelperEquals(a4, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  Submission a5 = testHelperAdd(history, "aaa", getDate("2016/09/03 10:10:00"), 10);
		  testHelperEquals(a4, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
	  }
	  @Test(timeout = 100)
	  public void testGetSubmissionBefore_AddAndRemove() {
		  SubmissionHistory history = new Assignment();
		  Submission a1 = testHelperAdd(history, "aaa", getDate("2016/09/03 09:00:00"), 10);
		  testHelperEquals(a1, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  Submission a2 = testHelperAdd(history, "aaa", getDate("2016/09/03 10:00:00"), 10);
		  testHelperEquals(a2, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  Submission a3 = testHelperAdd(history, "aaa", getDate("2016/09/03 11:00:00"), 10);
		  testHelperEquals(a2, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  Submission a4 = testHelperAdd(history, "aaa", getDate("2016/09/03 10:30:00"), 10);
		  testHelperEquals(a4, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  Submission a5 = testHelperAdd(history, "aaa", getDate("2016/09/03 10:10:00"), 10);
		  testHelperEquals(a4, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  history.remove(a4);
		  testHelperEquals(a5, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
		  Submission a6 = testHelperAdd(history, "aaa", getDate("2016/09/03 10:25:00"), 10);
		  testHelperEquals(a6, history.getSubmissionBefore("aaa", getDate("2016/09/03 10:30:00")));
	  }

	  /* ***********************************************************************
 	   * testAdd
 	   * *********************************************************************** */
	   @Test(timeout = 100)
	   public void testAdd_Null() {
		   SubmissionHistory history = new Assignment();
		   thrown.expect(IllegalArgumentException.class);
		   history.add(null, getDate("2016/09/03 10:00:00"), 10);
		   thrown.expect(IllegalArgumentException.class);
		   history.add("aaa", null, 10);
		   thrown.expect(IllegalArgumentException.class);
		   history.add("aaa", getDate("2016/09/03 10:00:00"), null);
 	  }
	  @Test(timeout = 100)
	  public void testAdd_OneSubmission() {
		  SubmissionHistory history = new Assignment();
		  testHelperEquals("aaa", getDate("2016/09/03 10:00:00"), 10, history.add("aaa", getDate("2016/09/03 10:00:00"), 10));
		  testHelperEquals("aaa", getDate("2016/09/03 10:00:00"), 10, history.getSubmissionFinal("aaa"));
	 }
	 /* ***********************************************************************
	  * testRemove
	  * *********************************************************************** */
	 @Test(timeout = 100)
	 public void testRemove_Null() {
		 SubmissionHistory history = new Assignment();
		 testHelperAdd(history, "aaa", getDate("2016/09/03 09:00:00"), 10);
		 thrown.expect(IllegalArgumentException.class);
		 history.remove(null);
	 }
	 @Test(timeout = 100)
	 public void testRemove_NoSubmissions() {
		 SubmissionHistory history = new Assignment();
		 Submission a = new MySubmission("aaa", getDate("2016/09/03 10:00:00"), 10);
		 thrown.expect(IllegalArgumentException.class);
		 history.remove(a);
	 }
	 @Test(timeout = 100)
	 public void testRemove_NoExisting() {
		 SubmissionHistory history = new Assignment();
		 Submission a = testHelperAdd(history, "aaa", getDate("2016/09/03 10:00:00"), 10);
		 Submission b = new MySubmission("bbb", getDate("2016/09/03 10:00:00"), 10);
		 thrown.expect(IllegalArgumentException.class);
		 history.remove(b);
	 }
	 @Test(timeout = 100)
	 public void testRemove_NoUnikey() {
		 SubmissionHistory history = new Assignment();
		 Submission a = testHelperAdd(history, "aaa", getDate("2016/09/03 10:00:00"), 10);
		 history.remove(a);
		 assertNull(history.getBestGrade("aaa"));
	 }
	 /* ***********************************************************************
	  * testListTopStudents
	  * *********************************************************************** */
	  @Test(timeout = 100)
	  public void testListTopStudents_NoSubmissions() {
		  SubmissionHistory history = new Assignment();

		  List<String> expected = Arrays.asList();
		  List<String> actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);
	  }
	  @Test(timeout = 100)
	  public void testListTopStudents_Simple() {
		  SubmissionHistory history = new Assignment();

		  history.add("aaa", getDate("2016/09/03 10:00:00"), 10);
		  history.add("bbb", getDate("2016/09/03 11:00:00"), 20);
		  history.add("ccc", getDate("2016/09/03 12:00:00"), 30);
		  history.add("ddd", getDate("2016/09/03 13:00:00"), 20);
		  history.add("aaa", getDate("2016/09/03 14:00:00"), 30);
		  history.add("bbb", getDate("2016/09/03 15:00:00"), 20);

		  List<String> expected = Arrays.asList("ccc", "aaa");
		  List<String> actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);
	  }
	  @Test(timeout = 100)
	  public void testListTopStudents_Duplicated() {
		  SubmissionHistory history = new Assignment();

		  history.add("aaa", getDate("2016/09/03 10:00:00"), 30);
		  history.add("bbb", getDate("2016/09/03 11:00:00"), 20);
		  history.add("ccc", getDate("2016/09/03 12:00:00"), 30);
		  history.add("ddd", getDate("2016/09/03 13:00:00"), 20);
		  history.add("aaa", getDate("2016/09/03 14:00:00"), 30);
		  history.add("bbb", getDate("2016/09/03 15:00:00"), 20);

		  List<String> expected = Arrays.asList("aaa", "ccc");
		  List<String> actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);
	  }
	  @Test(timeout = 100)
	  public void testListTopStudents_Complex() {
		  SubmissionHistory history = new Assignment();

		  List<String> expected = Arrays.asList();
		  List<String> actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);

		  Submission a = testHelperAdd(history, "aaa", getDate("2016/09/03 10:00:00"), 30);
		  expected = Arrays.asList("aaa");
		  actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);

		  Submission b = testHelperAdd(history, "bbb", getDate("2016/09/03 11:00:00"), 20);
		  expected = Arrays.asList("aaa");
		  actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);

		  history.remove(a);
		  expected = Arrays.asList("bbb");
		  actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);

		  Submission c = testHelperAdd(history, "ccc", getDate("2016/09/03 12:00:00"), 30);
		  expected = Arrays.asList("ccc");
		  actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);

		  Submission d1 = testHelperAdd(history, "ddd", getDate("2016/09/03 13:00:00"), 30);
		  expected = Arrays.asList("ccc", "ddd");
		  actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);

		  Submission d2 = testHelperAdd(history, "ddd", getDate("2016/09/03 14:00:00"), 30);
		  expected = Arrays.asList("ccc", "ddd");
		  actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);

		  history.remove(d1);
		  expected = Arrays.asList("ccc", "ddd");
		  actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);

		  history.remove(d2);
		  expected = Arrays.asList("ccc");
		  actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);

		  history.remove(c);
		  expected = Arrays.asList("bbb");
		  actual = history.listTopStudents();
		  Collections.sort(expected);
		  Collections.sort(actual);
		  assertEquals(expected, actual);
	  }
	  /* ***********************************************************************
	   * testListRegressions
 	   * *********************************************************************** */
	   @Test(timeout = 100)
	   public void testListRegressions_NoSubmissions() {
		   SubmissionHistory history = new Assignment();

		   List<String> expected = Arrays.asList();
		   List<String> actual = history.listRegressions();
		   Collections.sort(expected);
		   Collections.sort(actual);
		   assertEquals(expected, actual);
	   }
	   @Test(timeout = 100)
	   public void testListRegressions_NoRegression() {
		   SubmissionHistory history = new Assignment();

		   history.add("aaa", getDate("2016/09/03 10:00:00"), 10);
		   history.add("bbb", getDate("2016/09/03 11:00:00"), 20);
		   history.add("ccc", getDate("2016/09/03 12:00:00"), 30);
		   history.add("aaa", getDate("2016/09/03 13:00:00"), 20);
		   history.add("bbb", getDate("2016/09/03 14:00:00"), 30);
		   history.add("ccc", getDate("2016/09/03 15:00:00"), 40);

		   List<String> expected = Arrays.asList();
		   List<String> actual = history.listRegressions();
		   Collections.sort(expected);
		   Collections.sort(actual);
		   assertEquals(expected, actual);
	   }
	   @Test(timeout = 100)
	   public void testListRegressions_Simple() {
		   SubmissionHistory history = new Assignment();

		   history.add("aaa", getDate("2016/09/03 10:00:00"), 10);
		   history.add("bbb", getDate("2016/09/03 11:00:00"), 20);
		   history.add("ccc", getDate("2016/09/03 12:00:00"), 30);
		   history.add("aaa", getDate("2016/09/03 13:00:00"), 5);
		   history.add("bbb", getDate("2016/09/03 14:00:00"), 30);
		   history.add("ccc", getDate("2016/09/03 15:00:00"), 20);

		   List<String> expected = Arrays.asList("aaa", "ccc");
		   List<String> actual = history.listRegressions();
		   Collections.sort(expected);
		   Collections.sort(actual);
		   assertEquals(expected, actual);
	   }
	   @Test(timeout = 100)
	   public void testListRegressions_Complex() {
		   SubmissionHistory history = new Assignment();

		   Submission a1 = testHelperAdd(history, "aaa", getDate("2016/09/03 10:00:00"), 10);
		   Submission b1 = testHelperAdd(history, "bbb", getDate("2016/09/03 11:00:00"), 20);
		   Submission c1 = testHelperAdd(history, "ccc", getDate("2016/09/03 12:00:00"), 30);
		   List<String> expected = Arrays.asList();
		   List<String> actual = history.listRegressions();
		   Collections.sort(expected);
		   Collections.sort(actual);
		   assertEquals(expected, actual);

		   Submission a2 = testHelperAdd(history, "aaa", getDate("2016/09/03 13:00:00"), 5);
		   expected = Arrays.asList("aaa");
		   actual = history.listRegressions();
		   Collections.sort(expected);
		   Collections.sort(actual);
		   assertEquals(expected, actual);

		   Submission b2 = testHelperAdd(history, "bbb", getDate("2016/09/03 14:00:00"), 30);
		   expected = Arrays.asList("aaa");
		   actual = history.listRegressions();
		   Collections.sort(expected);
		   Collections.sort(actual);
		   assertEquals(expected, actual);

		   Submission c2 = testHelperAdd(history, "ccc", getDate("2016/09/03 15:00:00"), 20);
		   expected = Arrays.asList("aaa", "ccc");
		   actual = history.listRegressions();
		   Collections.sort(expected);
		   Collections.sort(actual);
		   assertEquals(expected, actual);

		   history.remove(a2);
		   expected = Arrays.asList("ccc");
		   actual = history.listRegressions();
		   Collections.sort(expected);
		   Collections.sort(actual);
		   assertEquals(expected, actual);

		   history.remove(c1);
		   expected = Arrays.asList();
		   actual = history.listRegressions();
		   Collections.sort(expected);
		   Collections.sort(actual);
		   assertEquals(expected, actual);

		   Submission b3 = testHelperAdd(history, "bbb", getDate("2016/09/03 16:00:00"), 30);
		   expected = Arrays.asList();
		   actual = history.listRegressions();
		   Collections.sort(expected);
		   Collections.sort(actual);
		   assertEquals(expected, actual);
	   }

}
