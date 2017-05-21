package algo.recuit;

import chess.Chess;

import java.util.Random;

import static chess.Util.getRandNeighbor;

/**
 * Created by sam on 15/03/17.
 */
public class RecuitSimule {

    private Chess solInitial;
    private Chess solFinal;
    private double t0;
    private int n;
    private int maxIteration;
    private double mu;
    private Random random = new Random();



    public RecuitSimule(int n, int maxIteration, double tempInit, double mu){
        this.n=n;
        solInitial = new Chess(n);
        this.maxIteration=maxIteration;
        this.t0=tempInit;
        this.mu=mu;
        this.solFinal=solInitial;
    }


    public void algoRecuit(){
        double temp=t0;
        Chess solActuel = solInitial.clone();
        Chess solCandidate;
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

    public Chess getSolFinal() {
        return solFinal;
    }
}
