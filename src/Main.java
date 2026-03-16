import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    private HashMap<String, Integer> usernameMap;
    private HashMap<String, Integer> attemptFrequency;

    public Main() {
        usernameMap = new HashMap<>();
        attemptFrequency = new HashMap<>();
    }

    public boolean checkAvailability(String username) {
        return !usernameMap.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {
        List<String> suggestions = new ArrayList<>();

        int count = 1;
        while (suggestions.size() < 5) {
            String alternative = username + count;
            if (!usernameMap.containsKey(alternative)) {
                suggestions.add(alternative);
            }
            count++;
        }

        return suggestions;
    }

    public void registerUsername(String username) {
        if (!usernameMap.containsKey(username)) {
            usernameMap.put(username, username.hashCode());
        }
        attemptFrequency.put(username, attemptFrequency.getOrDefault(username, 0) + 1);
    }

    public String getMostAttempted() {
        String mostAttempted = null;
        int maxAttempts = 0;

        for (Map.Entry<String, Integer> entry : attemptFrequency.entrySet()) {
            if (entry.getValue() > maxAttempts) {
                maxAttempts = entry.getValue();
                mostAttempted = entry.getKey();
            }
        }

        return mostAttempted + " (" + maxAttempts + " attempts)";
    }

    public static void main(String[] args) {
        Main checker = new Main();

        System.out.println(checker.checkAvailability("john_doe")); // false
        System.out.println(checker.checkAvailability("jane_smith")); // true

        System.out.println(checker.suggestAlternatives("john_doe"));

        checker.registerUsername("john_doe");
        checker.registerUsername("admin");
        checker.registerUsername("admin");
        checker.registerUsername("admin");

        System.out.println(checker.getMostAttempted()); // "admin (3 attempts)"
    }
}