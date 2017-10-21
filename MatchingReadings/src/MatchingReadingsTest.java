import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MatchingReadingsTest {

	@Test
	public void matchingReadingsTest() {

		ArrayList<Report> list = new ArrayList<Report>();
		list.add(new Report(1, -10));
		list.add(new Report(2, 15));
		list.add(new Report(3, -10));
		list.add(new Report(5, 200));

		assertEquals(2, MatchingReadings.countMatchingReadings(list, 2, 4));

	}

	@Test
	public void maxMatchingReadingsTest() {

		ArrayList<Report> list = new ArrayList<Report>();
		list.add(new Report(1, -10));
		list.add(new Report(2, 15));
		list.add(new Report(3, -10));
		list.add(new Report(5, 200));

		assertEquals(15, MatchingReadings.maxMatchingReadings(list, 2, 4));

	}

	@Test
	public void modeMatchingReadingsTest() {

		ArrayList<Report> list = new ArrayList<Report>();
		list.add(new Report(1, -10));
		list.add(new Report(2, 15));
		list.add(new Report(3, -10));
		list.add(new Report(4, 15));
		list.add(new Report(5, 200));

		assertEquals(15, MatchingReadings.modeMatchingReadings(list, 2, 4));

	}

}
