package test;
import java.util.LinkedHashSet;

public class LRU implements CacheReplacementPolicy{
    // key - for the word, value - many times that the word has asked
    private final LinkedHashSet<String> LRUwords = new LinkedHashSet<String>();

    public void add(String word) {
        if (LRUwords.contains(word))
        {
            LRUwords.remove(word);
        }
        LRUwords.add(word);
    }
    public String remove()
    {
        // check if the cache is an empty
        if (LRUwords.isEmpty())
        {
            return null;
        }
        else
        {
            String removedWord = LRUwords.iterator().next();
            LRUwords.remove(removedWord);
            return removedWord;
        }



    }

}
