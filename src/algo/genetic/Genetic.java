package algo.genetic;

import checkers.Checkers;
import checkers.Util;

import java.util.*;

/**
 * Created by sam on 17/05/17.
 */
public class Genetic {

    private final int NBSOLUTION=6;
    private final int NBCURRENTPOP=6;
    private List<Checkers> currentPop;
    private List<Checkers> choosenPop;
    private Random random;
    private int n;

    public Genetic(int n) {
        this.n = n;
        this.currentPop=new ArrayList<>();
        this.choosenPop=new ArrayList<>();
        this.random=new Random();

        while(true) {
            init();
            reproduction();
            /*croisement();
            mutation();*/

            for (Checkers solution : choosenPop) {
                System.out.println(solution.getFitness());
            }
        }
    }

    public void init(){
        for(int i=0; i<NBCURRENTPOP; i++){
            Checkers solution=new Checkers(n);
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
        fitness.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.getValue()-o1.getValue();
            }
        });
        /** Create a list weight for each solution that depends on their fitness */
        for (int i=0; i<fitness.size(); i++) {
            //System.out.println(fitness.get(i).getKey()+":"+fitness.get(i).getValue()+" - ");
            for(int j=0; j<=i; j++){
                weightList.add(fitness.get(i).getKey());
            }
        }
       /*for (int i=0; i<weightList.size(); i++){
            System.out.println(weightList.get(i));
        }*/

       /** Select the solution from the current population in function of their weight */
        for(int i=0; i<NBSOLUTION;i++){
            int k = random.nextInt(weightList.size());
            choosenPop.add(currentPop.get(weightList.get(k)));
        }

        /*for (int[] aChoosenPop : choosenPop) {
            System.out.println(Arrays.toString(aChoosenPop));
        }*/
    }



    /*public void croisement(){
        for(int i=0; i<choosenPop.size(); i+=2){
            for(int j=random.nextInt(choosenPop.get(i).length); j<choosenPop.get(i).length;j++){
                int x = choosenPop.get(i)[j];
                choosenPop.get(i)[j]=choosenPop.get(i+1)[j];
                choosenPop.get(i+1)[j]=x;
            }
        }
    }


    public void mutation(){
        int rand = random.nextInt(choosenPop.size()-1);
        int rand2 = random.nextInt(choosenPop.get(rand).length-1);
        int rand3 = random.nextInt(n-1);
        choosenPop.get(rand)[rand2]=rand3;
    }*/
}
