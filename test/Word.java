package test;


import java.util.Arrays;
import java.util.Objects;

public class Word {
    Tile[] word;
    int row, col;
    boolean vertical;

    public Word(Tile[] word, int row, int col, boolean vertical)
    {
        this.word = word;
        this.col = col;
        this.row = row;
        this.vertical = vertical;
    }

    public Tile[] getWord()
    {
        return word;
    }

    public  int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public boolean getVertical()
    {
        return vertical;
    }

    public Tile[] getTiles() {
        return word;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return row == word1.row && col == word1.col && vertical == word1.vertical && Arrays.equals(word, word1.word);
    }

}
