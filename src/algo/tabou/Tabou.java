package algo.tabou;

import checkers.Action;
import checkers.Checkers;
import checkers.Util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Sam on 20/03/2017.
 */
public class Tabou {
    private int nmax;
    private ArrayList<Action> T = new ArrayList<>();
    private Checkers solInitial;
    private Checkers solFinal;
    private int n;
    private int nbIteration;

    public Tabou(int nmax, int n) {
        this.nmax = nmax;
        this.n=n;
        solInitial= new Checkers(n);
        solFinal=solInitial.clone();
        nbIteration = 0;
    }

    public void algoTabou(){
        solFinal=solInitial;
        int currentFitness = Integer.MAX_VALUE;
        int fitnessMin = Integer.MAX_VALUE;
        int fitness;
        Action action;
        Checkers solCandidate;
        ArrayList<Action> actions = Util.getListActions(n,T);
        while(nbIteration!=nmax && currentFitness>0 && actions.size()>0){
            action = Util.getBestAction(solFinal, actions);
            solCandidate=Util.getNeighborByAction(solFinal, action);
            fitness = solCandidate.getFitness();
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
            nbIteration++;
        }
    }

    public void run(){
        algoTabou();
    }

    public Checkers getSolFinal() {
        return solFinal;
    }

    public int getNbIteration() {
        return nbIteration;
    }
}
