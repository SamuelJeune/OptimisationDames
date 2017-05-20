package algo.recuit;

import checkers.Checkers;

import java.util.Arrays;
import java.util.Random;

import static checkers.Util.getRandNeighbor;

/**
 * Created by sam on 15/03/17.
 */
public class RecuitSimule {

    private Checkers solInitial;
    private Checkers solFinal;
    private double t0;
    private int n;
    private int maxIteration;
    private double mu;
    private Random random = new Random();



    public RecuitSimule(int n, int maxIteration, double tempInit, double mu){
        this.n=n;
        solInitial = new Checkers(n);
        this.maxIteration=maxIteration;
        this.t0=tempInit;
        this.mu=mu;
        this.solFinal=solInitial;
    }


    public void algoRecuit(){
        double temp=t0;
        Checkers solActuel = solInitial.clone();
        Checkers solCandidate;
        int fitnessFinal = solFinal.getFitness();
        int dFitness;
        while(temp>0.2){
            for(int l=1; l<maxIteration;l++){
                solCandidate= getRandNeighbor(solActuel);
                dFitness = solCandidate.getFitness()-solActuel.getFitness();
                if(dFitness<=0){
                    solActuel=solCandidate;
                    if(solActuel.getFitness()<fitnessFinal){
                        solFinal=solActuel;
                        fitnessFinal=solFinal.getFitness();
                    }
                }else{
                    if(random.nextDouble()<=Math.exp(-dFitness/temp)){
                        solActuel=solCandidate;
                    }
                }
            }
            temp=temp*mu;

        }
    }

    public void run(){
        algoRecuit();
    }

    public Checkers getSolFinal() {
        return solFinal;
    }
}
