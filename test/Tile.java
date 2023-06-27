package test;

import java.util.Objects;
import java.util.Random;


public class Tile {
    public final char letter;
    public final int score;

    private Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    public char getLetter()
    {
        return  this.letter;
    }
    public int getScore()
    {
        return  this.score;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    public int hashCode() {
        return Objects.hash(letter, score);
    }

    public static class Bag {
        private static Bag _instance = null;
        public static int[] NumOfEachLetters = new int[26];
        public static Tile[] tiles = new Tile[26];
        private int CurrentTilesInBag = 98;
        public static int[] MaxLetterCount = new int[26];


        private Bag()
        {
            populateBag();
            MaxLetterCount = NumOfEachLetters.clone();
        }

        public static Bag getBag()
        {
            if(_instance == null) {
                _instance = new Bag();
                populateBag();


            }
            return _instance;
        }

        public static void populateBag()
        {
            createTile('A', 1,9 );
            createTile('B', 3,2 );
            createTile('C', 3, 2);
            createTile('D', 2, 4);
            createTile('E', 1, 12);
            createTile('F', 4, 2);
            createTile('G', 2, 3);
            createTile('H', 4,2);
            createTile('I', 1, 9);
            createTile('J', 8, 1);
            createTile('K', 5, 1);
            createTile('L', 1,4);
            createTile('M', 3, 2);
            createTile('N', 1, 6);
            createTile('O', 1, 8);
            createTile('P', 3, 2);
            createTile('Q', 10, 1);
            createTile('R', 1, 6);
            createTile('S', 1, 4);
            createTile('T', 1, 6);
            createTile('U', 1, 4);
            createTile('V', 4, 2);
            createTile('W', 4, 2);
            createTile('X', 8,1);
            createTile('Y', 4, 2);
            createTile('Z', 10,1);
        }

        public static void createTile(char letter, int points, int count)
        {
            int location = letter - 'A';
            for(int i = 0; i<tiles.length; i++){
                if(i == location){
                    tiles[i] = new Tile(letter, points);
                    NumOfEachLetters[i] = count;
                }
            }
        }

        public Tile getRand()
        {
            Random r = new Random();
            int minimum = 0;
            int maximum = 25;
            int range = maximum - minimum + 1;
            int randomNum = r.nextInt(range) + minimum;
            while (this.NumOfEachLetters[randomNum] == 0) {
                randomNum = r.nextInt(range) + minimum;
            }

            this.NumOfEachLetters[randomNum]--;
            this.CurrentTilesInBag--;
            return tiles[randomNum];
        }

        public Tile getTile(char letter)
        {
            int location;
            if (Character.isUpperCase(letter))
            {
                location = letter - 'A';
                if(this.NumOfEachLetters[location] != 0)
                {
                    this.NumOfEachLetters[location] --;
                    CurrentTilesInBag --;
                }
            }
            else
            {
                return null;
            }
            return this.tiles[location];

        }

        public int[] getQuantities()
        {
            return this.NumOfEachLetters.clone();
        }


        public void put(Tile tiles)
        {
            int location = tiles.letter - 'A';

            if( CurrentTilesInBag < 98 && this.NumOfEachLetters[location] < MaxLetterCount[location])
            {
                this.NumOfEachLetters[location] ++;
                CurrentTilesInBag ++;
            }

        }
        public int size()
        {
            return CurrentTilesInBag;
        }



    }

}
