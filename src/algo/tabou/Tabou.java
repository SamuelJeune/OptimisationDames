package algo.tabou;

import util.Action;
import util.Util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sam on 20/03/2017.
 */
public class Tabou {
    private int i =0;
    private int nmax;
    private ArrayList<Action> T = new ArrayList<>();
    private int[] solInitial;
    private int[] solFinal;
    private int n;

    public Tabou(int nmax, int n) {
        this.i = 0;
        this.nmax = nmax;
        solInitial= Util.getInitSolution(n);
        this.n=n;
        AlgoTabou();
        solFinal=solInitial;
    }

    public void AlgoTabou(){
        solFinal=solInitial;
        int currentFitness = Integer.MAX_VALUE;
        int fitnessMin = Integer.MAX_VALUE;
        int fitness;
        Action action;
        int[] solCandidate;
        ArrayList<Action> actions = Util.getListActions(n,T);
        while(i!=nmax && currentFitness>0 && actions.size()>0){
            action = Util.getBestAction(solFinal, n,actions);
            solCandidate=Util.getNeighborByAction(action,solFinal);
            fitness = Util.getFitness(solCandidate,n);
            if(fitness<=currentFitness){
                T.add(new Action(action.getA(),action.getB()));
            }
            if(T.size()>n-2){
                T.remove(0);
            }
            if(fitness<=fitnessMin){
                fitnessMin = fitness;
                solFinal=solCandidate;
            }
            currentFitness = fitness;
            actions = Util.getListActions(n,T);
            i++;
        }
        System.out.println("nombre d'itération : "+i);
        System.out.println(Arrays.toString(solFinal));
        Util.affichagejolidetheo(solFinal);
        System.out.println(Util.getFitness(solFinal, n));
    }


}
