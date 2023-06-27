package test;
import java.util.HashSet;


public class CacheManager {
    private int maxSizeCache;
    private HashSet<String> cachedWords;
    private CacheReplacementPolicy crp;

    public CacheManager(int maxSizeCache, CacheReplacementPolicy crp) {
        this.maxSizeCache = maxSizeCache;
        this.crp = crp;
        this.cachedWords = new HashSet<String>();
    }

    public boolean query(String word)
    {
        return cachedWords.contains(word);
    }

    public void add(String word)
    {
        if(cachedWords.size() == maxSizeCache)
        {
            // return the word that needs to be removed
            String wordToRemove = crp.remove();
            cachedWords.remove(wordToRemove);
        }
        crp.add(word);
        cachedWords.add(word);
    }





}
