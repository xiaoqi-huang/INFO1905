import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.PriorityQueue;

public class Reminders {

    private PriorityQueue<SimpleEntry<Date, String>> pq;

    public Reminders() {
        pq = new PriorityQueue<SimpleEntry<Date, String>>(Comparator.comparing(SimpleEntry::getKey));
    }

    /**
     * Adds a reminder at the given time
     */
    public void setReminder(Date time, String reminder) {
        pq.add(new SimpleEntry<Date, String>(time, reminder));
    }

    /**
     * Retrieves & removes all reminders up to (and including) the given time
     */
    public ArrayList<String> getReminders(Date currentTime) {
        ArrayList<String> al = new ArrayList<String>();
        while (!pq.isEmpty() && !pq.peek().getKey().after(currentTime)) {
            al.add(pq.poll().getValue());
        }
        return al;
    }

}
