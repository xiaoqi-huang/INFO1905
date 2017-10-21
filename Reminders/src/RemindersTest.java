import static org.junit.Assert.*;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.junit.Test;

public class RemindersTest {

	@Test
	public void testFiveReminders() throws ParseException {
		Reminders r = new Reminders();
		try {
			r.setReminder(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-02 10:00:00"), "Have breakfast");
			r.setReminder(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-02 11:30:00"), "Call Janet about carpet");
			r.setReminder(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-02 14:30:00"), "Pick up drycleaning");
			r.setReminder(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-02 18:00:00"), "Watch 'The Block'");
			r.setReminder(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-03 10:00:00"), "Have a different breakfast");
		} catch (ParseException ex) {
			System.out.println("Invalid date");
		}

		assertEquals(Arrays.asList(), r.getReminders(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-02 08:00:00")));
		assertEquals(Arrays.asList("Have breakfast"), r.getReminders(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-02 10:00:00")));
		assertEquals(Arrays.asList("Call Janet about carpet", "Pick up drycleaning"), r.getReminders(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-02 15:00:00")));
		assertEquals(Arrays.asList("Watch 'The Block'"), r.getReminders(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-03 09:59:59")));
		assertEquals(Arrays.asList("Have a different breakfast"), r.getReminders(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-03 12:00:00")));
		assertEquals(Arrays.asList(), r.getReminders(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2010-01-03 13:00:00")));
		assertEquals(Arrays.asList(), r.getReminders(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-01-03 13:00:00")));
	}

	@Test
	public void testEmpty() throws ParseException {
		Reminders r = new Reminders();
		assertEquals(Arrays.asList(), r.getReminders(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2014-01-03 13:00:00")));
		assertEquals(Arrays.asList(), r.getReminders(new Date()));
		assertEquals(Arrays.asList(), r.getReminders(new Date(0)));
	}

}
