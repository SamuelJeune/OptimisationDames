package AlgoRecuit;

import java.util.*;

/**
 * Created by sam on 17/05/17.
 */
public class Genetic {

    private final int NBSOLUTION=2;
    private final int NBCURRENTPOP=6;
    private List<int[]> currentPop;
    private List<int[]> choosenPop;
    private Random random;
    private int n;

    public Genetic(int n) {
        this.n = n;
        this.currentPop=new ArrayList<>();
        this.choosenPop=new ArrayList<>();
        this.random=new Random();

        init();
        reproduction();

    }

    public void init(){
        for(int i=0; i<NBCURRENTPOP; i++){
            int[] solution=new int[n];
            for(int j=0; j<n; j++){
                solution[j]=random.nextInt(n-1);
            }
            currentPop.add(solution);
        }
    }

    public void reproduction(){
        /*List<Integer> pondertionList = new ArrayList<>();
        List<Pair> fitness = new ArrayList<>();
        for (int i=0; i<currentPop.size(); i++) {
            fitness.add(new Pair(i, Util.getFitness(currentPop.get(i), n)));
            System.out.println(fitness.get(i).getKey()+":"+fitness.get(i).getValue()+" - ");
        }
        fitness.sort(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.compareTo(o2);
            }
        });
        for (int i=0; i<fitness.size(); i++) {
            System.out.println(fitness.get(i).getKey()+":"+fitness.get(i).getValue()+" - ");
        }*/


        for(int i=0; i<choosenPop.size();i++){
            int k = random.nextInt(currentPop.size());
            choosenPop.add(currentPop.get(k));
        }
    }


    public void croisement(){

    }


    public void mutation(){
        
    }
}
