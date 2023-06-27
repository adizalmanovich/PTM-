package test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Dictionary {
    private final CacheManager existsWords;
    private final CacheManager NotexistsWords;
    private final BloomFilter bloomFilter;
    private String[] files;

    public Dictionary(String...filesNames)
    {
        this.existsWords = new CacheManager(400, new LRU());
        this.NotexistsWords = new CacheManager(100, new LFU());
        this.bloomFilter = new BloomFilter(256, "MD5", "SHA1");
        this.files = filesNames;
        Scanner myScaner = null;
        for (String s: filesNames)
        {
            try
            {
                myScaner = new Scanner(new BufferedReader(new FileReader(s)));
            }
            catch (FileNotFoundException e)
            {
                throw new RuntimeException(e);
            }
            // insert every word to the bloomfilter
            while (myScaner.hasNext())
            {
                bloomFilter.add(myScaner.next());
            }
            myScaner.close();

        }
        
    }

    public boolean query(String word)
    {
        if (existsWords.query(word))
        {
            return true;
        }
        else if (NotexistsWords.query(word))
        {
            return false;
        }
        else
        {
            if (bloomFilter.contains(word))
            {
                this.existsWords.add(word);
                return true;
            }
            this.NotexistsWords.add(word);
            return false;
        }
    }

    public boolean challenge(String word)
    {
        if(IOSearcher.search(word,files))
        {
            existsWords.add(word);
            return true;
        }
        NotexistsWords.add(word);
        return false;

    }


}