package chess;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hagoterio on 20/05/17.
 */
public class Chess implements Cloneable{

    private int[] board;
    private int n;
    private int fitness;
    private boolean changed;

    public Chess(int[] board, int n) {
        this.board = board;
        this.n = n;
        changed = true;
    }

    public Chess(int n) {
        this.n = n;
        this.board = new int[n];
        changed = true;
        init();
    }

    public void init(){
        Random rand = new Random();
        int randInt;
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0;i<n;i++){
            list.add(i);
        }
        for(int i = 0;i<n;i++){
            randInt = rand.nextInt(list.size());
            board[i] = list.remove(randInt);
        }
    }

    public int[] getBoard() {
        return board;
    }

    public int getSize() {
        return n;
    }

    public void display(){
        System.out.println(this);
    }

    public int getFitness(){
        if(!changed) return fitness;
        fitness = 0;
        for(int i=0; i<n; i++){
            for(int j=i+1; j<n; j++){
                if(Math.abs(board[i]-board[j])==Math.abs(i-j)){
                    fitness++;
                }
            }
        }
        changed = false;
        return fitness;
    }

    public void applyAction(Action action){
        int A = action.getA();
        int B = action.getB();
        int stamp = board[A];
        board[A]=board[B];
        board[B]=stamp;
        changed = true;
    }

    public Chess clone() {
        return new Chess(board.clone(),n);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(int i=0;i<board.length;i++){
            string.append("[");
            for(int j=0; j<board.length; j++){
                if(board[j]==i){
                    string.append(" Q");
                } else {
                    string.append(" -");
                }
            }
            string.append(" ]\n");
        }
        return string.toString();
    }


}


