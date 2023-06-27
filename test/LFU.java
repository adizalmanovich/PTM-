package test;
import java.util.LinkedHashMap;
import java.util.Map;
public class LFU implements CacheReplacementPolicy{
    // key - for the word, value - many times that the word has asked
    private final LinkedHashMap<String,Integer> LFUwords = new LinkedHashMap<>();



    public void add(String word) {
        if (LFUwords.containsKey(word))
        {
            LFUwords.put(word, LFUwords.get(word) +1 );
        }
        else {
            // if the word does not exist - add it to the LinkedHashMap
            LFUwords.put(word, 1);
        }
    }

    public String remove()
    {
        int leastUsedRemoved = 1000000;
        String wordToRemove = null;
        for (Map.Entry<String, Integer> e : LFUwords.entrySet()){
            if (e.getValue() < leastUsedRemoved)
                wordToRemove = e.getKey();
            leastUsedRemoved = e.getValue();
        }
        // remove word from itself
        LFUwords.remove(wordToRemove);
        return wordToRemove;
    }
}
