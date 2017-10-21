import java.util.HashMap;
import java.util.List;

public class MatchingReadings {

//return the number of readings which were logged between startTime and endTime (inclusive)
	static int countMatchingReadings(List<Report> reportList, int startTime, int endTime) {
		int count = 0;
		for (Report r : reportList) {
			if ((r.getTime() >= startTime) && (r.getTime() <= endTime)) {
				count++;
			}
		}
		return count;
	}

//return the maximum value of any reading which was logged between startTime and endTime (inclusive)
	static int maxMatchingReadings(List<Report> reportList, int startTime, int endTime) {
		  int max = Integer.MIN_VALUE;
		  for (Report r : reportList) {
			  if ((r.getTime() >= startTime) && (r.getTime() <= endTime) && (r.getValue() > max)) {
				  max = r.getValue();
			  }
		  }
		  return max;
	}

//return the mode (most common value) of any reading which was logged between startTime and endTime (inclusive)
	static int modeMatchingReadings(List<Report> reportList, int startTime, int endTime) {

		  HashMap<Integer, Integer> occurrence = new HashMap<Integer, Integer>();

		  for (Report r : reportList) {
			  if ((r.getTime() >= startTime) && (r.getTime() <= endTime)) {
				  if (!occurrence.containsKey(r.getValue())) {
					  occurrence.put(r.getValue(), 1);
				  } else {
					  occurrence.put(r.getValue(), occurrence.get(r.getValue()) + 1);
				  }
			  }
		  }

		  int common = Integer.MIN_VALUE;
		  int max = Integer.MIN_VALUE;
		  Integer[] key = occurrence.keySet().toArray(new Integer[0]);

		  for (int i = 0; i < key.length; i++) {
		  	if (occurrence.get(key[i]) > max) {
		  		common = key[i];
				max = occurrence.get(key[i]);
		  	}
		  }

		  return common;

	}

}
