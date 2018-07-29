package cameronjump.beengo;

import android.util.Log;

/**
 * Created by camer on 4/7/2018.
 */

public class Card {

    private String name;
    private int[][] value;
    private boolean[][] state;
    private boolean cardState;

    public Card(int[][] inputValue, String inputName) {
        value = inputValue.clone();
        name = inputName;
        resetCard();
    }

    public void resetCard() {
        state = new boolean[5][5];
        state[2][2] = true;
        cardState = false;
    }

    public void checkCard(int number) {
        for(int i=0; i<value.length; i++) {
            for(int j=0; j<value.length; j++) {
                if (value[i][j] == number)
                    state[i][j] = true;
            }
        }
        return;
    }

    public boolean checkWinner() {
        //check horizontal
        for(int i=0; i<value.length; i++) {
            for(int j=0; j<value.length; j++) {
                Log.d("i j", ""+i+" "+j +" "+ String.valueOf(state[i][j]));
                if (!state[i][j]) break;
                if(j==4) return true;
            }
        }
        //check vertical
        for(int j=0; j<value.length; j++) {
            for(int i=0; i<value.length; i++) {
                Log.d("i j", ""+i+" "+j +" "+ String.valueOf(state[i][j]));
                if (!state[i][j]) break;
                if(i==4) return true;
            }
        }

        //check diagnal
        while(true) {
            if(state[0][0]) {
                if(state[1][1]) {
                    if(state[2][2]) {
                        if(state[3][3]) {
                            if(state[4][4]) {
                                return true;}
                        }
                    }
                }
            }
            break;
        }
        while(true) {
            if(state[4][0]) {
                if(state[3][1]) {
                    if(state[2][2]) {
                        if(state[1][3]) {
                            if(state[0][4]) {
                                return true;}
                        }
                    }
                }
            }
            break;
        }
        //check corners
        while(true) {
            if(state[0][0]) {
                if(state[0][4]) {
                    if(state[4][0]) {
                        if(state[4][4]){
                            return true;}
                    }
                }
            }
            break;
        }
        return false;
    }

    public int[][] getValue(){
        return value;
    }
    public boolean[][] getState() {
        return state;
    }
    public String getName() {
        return name;
    }

}
