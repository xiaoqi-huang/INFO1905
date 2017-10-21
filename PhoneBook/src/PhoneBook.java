import java.util.HashMap;

public class PhoneBook {

    private HashMap<String, Integer> contacts;

    public PhoneBook() {
        contacts = new HashMap<String, Integer>(50);
    }

    public void addNumber(String name, Integer phoneNumber) {
        contacts.put(name, phoneNumber);
    }

    public Integer getNumber(String name) {
        return contacts.get(name);
    }

    public void deleteContact(String name) {
        contacts.remove(name);
    }
}
