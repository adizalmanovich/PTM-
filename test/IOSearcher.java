package test;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class IOSearcher {
    public static boolean search (String word, String...fileNames) {
        Scanner fileScanner;
        for (String fileName: fileNames)
        {
            try
            {
                fileScanner = new Scanner(new BufferedReader(new FileReader(fileName)));
            } catch (FileNotFoundException ex) {
                return false;
            }
            while (fileScanner.hasNext())
            {
                if (fileScanner.next().equals(word))
                {
                    return true;
                }
            }
            fileScanner.close();
        }
        return false;
    }
}
