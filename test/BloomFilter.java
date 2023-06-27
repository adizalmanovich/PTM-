package test;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.BitSet;
import java.util.Collections;
import java.util.ArrayList;

public class BloomFilter {
    private final BitSet bits;
    private final ArrayList<String> algo = new ArrayList<>();

    public BloomFilter(int size, String...algos){
        Collections.addAll(algo, algos);
        bits= new BitSet(size);
    }

    public int hashValue (String method, String word)
    {
        MessageDigest md;
        try {
            // check if the method is correct
            md = MessageDigest.getInstance(method);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        byte[] bts= md.digest(word.getBytes());
        BigInteger bt = new BigInteger(bts);
        int num = Math.abs(bt.intValue());
        return num % bits.size();
    }

    public void add(String word)
    {
        for (String s: algo)
        {
            int digest=hashValue(s,word);
            bits.set(digest);
        }
    }

    public boolean contains(String word)
    {
        for (String s: algo)
        {
            int digest=hashValue(s,word);
            if(!(bits.get(digest)))
            {
                return false;
            }
        }

        return true;
    }

    public String toString()
    {
        StringBuilder s= new StringBuilder();
        for (int i=0; i< bits.length(); i++)
        {
            if(bits.get(i))
            {
                s.append("1");
            }
            else
            {
                s.append("0");
            }
        }

        return s.toString();
    }







}
