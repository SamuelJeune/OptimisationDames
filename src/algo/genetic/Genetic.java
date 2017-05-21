package algo.genetic;

import chess.Action;
import chess.Chess;

import java.util.*;

/**
 * Created by sam on 17/05/17.
 */
public class Genetic {

    private int nbPop;
    private List<Chess> currentPop;
    private List<Chess> choosenPop;
    private final int nbIteration;
    private Random random;
    private int n;
    private double mutationLuck;
    private Chess best;
    private int bestFitness;


    public Genetic(int n, int nbIteration, int nbPop, double mutationLuck) {
        this.n = n;
        this.nbPop = nbPop;
        this.nbIteration = nbIteration;
        this.currentPop=new ArrayList<>();
        this.choosenPop=new ArrayList<>();
        this.random=new Random();
        this.mutationLuck = mutationLuck;
        this.best=null;
        this.bestFitness = Integer.MAX_VALUE;
    }

    public void algoGenetic(){
        init();
        for(int i=0;i<nbIteration && getBest().getFitness()>0;i++) {
            reproduction();
            croisement();
            mutation();
        }
        /*int i=0;
        while(getBest().getFitness()!=0){
            reproduction();
            croisement();
            mutation();
            System.out.println("iteration : " + i + " ; fitness : " + getBest().getFitness());
            i++;
        }*/
    }

    public void init(){
        for(int i=0; i<nbPop; i++){
            Chess solution=new Chess(n);
            currentPop.add(solution);
        }
    }

    /**
     * Reproduction
     * a method to reproduce some solution based on natural selection
     * that eliminate the solution with the greatest fitness
     * */
    public void reproduction(){
        List<Integer> weightList = new ArrayList<>();
        List<Pair> fitness = new ArrayList<>();
        /** Create a list with the fitness of the current population */
        for (int i=0; i<currentPop.size(); i++) {
            fitness.add(new Pair(currentPop.get(i).getFitness(), i));
            //System.out.println(fitness.get(i).getKey()+":"+fitness.get(i).getValue()+" - ");
        }
        /** Sort the fitness list in a reverse order */
        fitness.sort((o1, o2) -> o2.getKey()-o1.getKey());
        /** Create a list weight for each solution that depends on their fitness */
        for (int i=0; i<fitness.size(); i++) {
            //System.out.println(fitness.get(i).getKey()+":"+fitness.get(i).getValue()+" - ");
            for(int j=0; j<=i; j++){
                weightList.add(fitness.get(i).getValue());
            }
        }
       /*for (int i=0; i<weightList.size(); i++){
            System.out.println(weightList.get(i));
        }*/

       /** Select the solution from the current population in function of their weight */
        for(int i=0; i<2*nbPop;i++){
            int k = random.nextInt(weightList.size());
//            System.out.println(weightList.get(k));
            choosenPop.add(currentPop.get(weightList.get(k)));
        }

        /*for (int[] aChoosenPop : choosenPop) {
            System.out.println(Arrays.toString(aChoosenPop));
        }*/
    }



    public void croisement(){
        int[] board;
        int k;
        /**
         * this list will stock all the values already used to be sure
         * that we don't use  twice the same value which can create horizontals conflicts
         * that are not taken in account in our fitness function
         */
        ArrayList<Integer> usedList = new ArrayList<>();
        currentPop.clear();
        for(int i=0;i<nbPop*2;i+=2){
            usedList.clear();
            board = new int[n];
            /**we draw the number of value we'll take on the first relative */
            int rand1 = random.nextInt(n);
            /**then we draw the first index of the values we'll take on the first relative
            (all the other values will came from the second relative*/
            int rand2 = random.nextInt(n-rand1);
            for(int j=rand2;j<rand1+rand2;j++){
                board[j]=choosenPop.get(i).getBoard()[j];
                usedList.add(board[j]);
            }
            k=0;
            for(int j=0;j<rand2;j++){
                do{
                    board[j]=choosenPop.get(i+1).getBoard()[k];
                    k++;
                } while(usedList.contains(board[j]));
                usedList.add(board[j]);
            }
            k=n-1;
            for(int j=n-1;j>=rand1+rand2;j--){
                do{
                    board[j]=choosenPop.get(i+1).getBoard()[k];
                    k--;
                } while(usedList.contains(board[j]));
            }
            currentPop.add(new Chess(board,n));
        }
    }


    public void mutation(){
        for(int i = 0; i<nbPop;i++){
            double rand = random.nextDouble();
            if(rand<mutationLuck){
                int rand1 = random.nextInt(n);
                int rand2 = random.nextInt(n);
                currentPop.get(i).applyAction(new Action(rand1,rand2));
            }
        }
    }

    public Chess getBest(){
        int currentFitness;
        for(int i=0;i<nbPop;i++){
            currentFitness = currentPop.get(i).getFitness();
            if(currentFitness<bestFitness){
                //System.out.println("current : " + currentFitness + " ; best : " + bestFitness);
                bestFitness = currentFitness;
                best = currentPop.get(i);
            }
        }
        return best;
    }

    public void run(){
        algoGenetic();
    }
}
