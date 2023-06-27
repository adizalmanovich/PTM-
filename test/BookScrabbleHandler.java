package test;
import java.io.*;
import java.util.Arrays;

public class BookScrabbleHandler implements ClientHandler {
    BufferedReader userinput;
    PrintWriter output;
    DictionaryManager dictonary = DictionaryManager.get();

    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient)
    {
        try{
            userinput = new BufferedReader(new InputStreamReader(inFromclient));
            boolean found = false;
            String lines = userinput.readLine();
            String [] split = lines.split(",");
            String [] books = Arrays.copyOfRange(split,1,split.length-1);

            if (lines.startsWith("C"))
            {
                found = dictonary.challenge(books);
            }
            else if (lines.startsWith("Q"))
            {
                found = dictonary.query(books);
            }
            output = new PrintWriter(outToClient,true);
            if(found)
            {
                output.println("true\n");
            }
            else
            {
                output.println("false\n");
            }


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            output.close();
            userinput.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
