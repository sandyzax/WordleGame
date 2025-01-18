import java.util.ArrayList;
import java.util.List;

public class WordleModel {

    private String word;
    private int currentRow;
    public final static int SIZE = 5;
    private char[][] grid;
    private char[][] result;
    private List<WordleView> views;



    public WordleModel(String word) {

        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
        result = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                result[i][j] = ' ';
            }
        }
        this.word = word;
        currentRow = 0;

        views = new ArrayList<WordleView>();

    }

    public void addWord(String Guessed) {

        Guessed = Guessed.toLowerCase();


        for (int i = 0; i <Guessed.length() ; i++) {

            grid[currentRow][i] = Guessed.charAt(i);
            if(Guessed.charAt(i) == word.charAt(i)) { //checking for position
                result[currentRow][i] = 'G';
            }
            else if(word.indexOf(Guessed.charAt(i))!=-1){//checking if the letter is present
                result[currentRow][i] = 'Y';
            }
            else {
                result[currentRow][i] = 'X';//the letter is not present
            }

        }
        currentRow++;
        notifyViews();


    }
    public boolean checkWinner() {
        for (int i = 0; i < SIZE; i++) {
            if (result[currentRow - 1][i] != 'G') {
                return false;
            }
        }
        return true;
    }
    public void addWordleView(WordleView view) {
        views.add(view);
    }
    public void removeWordleView(WordleView view) {
        views.remove(view);
    }
    public int getCurrentRow(){
        return currentRow;
    }
    public char[][] getGrid() {
        return grid;
    }
    public char[][] getResult() {
        return result;
    }


    public void notifyViews(){
        for (WordleView view : views) {
            view.handleWordleDisplay(this,result);
        }
    }

}
