package test;
import java.util.HashMap;

public class DictionaryManager {
    private final HashMap<String, Dictionary> books = new HashMap<>();
    private static DictionaryManager single_instance = null;

    public boolean calculate(String method, String... args) {
        String word = args[args.length - 1];
        for (int i = 0; i < args.length - 1; i++) {
            if (books.containsKey(args[i])) {
                Dictionary newBook = new Dictionary(args[i]);
                books.put(args[i], newBook);
            } else {
                Dictionary dict = books.get(args[i]);
                if (method.equals("query")) {
                    if (dict.query(word)) {
                        return true;
                    }
                }
                if (method.equals("challenge")) {
                    if (dict.challenge(word)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public boolean query(String... args) {
        return calculate("query", args);
    }

    public boolean challenge(String... args) {
        return calculate("challenge", args);
    }

    public int getSize() {
        return books.size();
    }

    public static DictionaryManager get() {
        if (single_instance == null)
            single_instance = new DictionaryManager();
        return single_instance;

    }
}
