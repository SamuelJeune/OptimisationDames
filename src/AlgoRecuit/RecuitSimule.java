package AlgoRecuit;

import java.util.Arrays;
import java.util.Random;

import static AlgoRecuit.Util.affichagejolidetheo;
import static AlgoRecuit.Util.getFitness;
import static AlgoRecuit.Util.getRandNeighbor;

/**
 * Created by sam on 15/03/17.
 */
public class RecuitSimule {

    private int[] solInitial;
    private double t0;
    private int n;
    private int maxIteration;
    private double mu;
    private int[] solFinal;
    private int fitnessFinal;
    private Random random = new Random();



    public RecuitSimule(int n, int maxIteration, double tempInit, double mu){
        this.n=n;
        solInitial=new int[n];
        for(int i=0; i<n; i++){
            solInitial[i]= i;
        }
        this.maxIteration=maxIteration;
        this.t0=tempInit;
        this.mu=mu;
        this.solFinal=solInitial;
        this.fitnessFinal=getFitness(solFinal, n);
        algoRecuit();
    }


    public void algoRecuit(){
        double temp=t0;
        int[] solActuel = solInitial;
        int[] solCandidate;
        while(temp>0.2){
            for(int l=1; l<maxIteration;l++){
                solCandidate= getRandNeighbor(solActuel, n);
                int dFitness = getFitness(solCandidate, n)-getFitness(solActuel, n);
                if(dFitness<=0){
                    solActuel=solCandidate;
                    if(getFitness(solActuel, n)<fitnessFinal){
                        solFinal=solActuel;
                        fitnessFinal=getFitness(solFinal, n);
                    }
                }else{
                    if(random.nextDouble()<=Math.exp(-dFitness/temp)){
                        solActuel=solCandidate;
                    }
                }
            }
            temp=temp*mu;
            System.out.println(temp);
            System.out.println(Arrays.toString(solFinal));

        }
        System.out.println(Arrays.toString(solFinal));
        System.out.println("fitness = " + getFitness(solFinal, n));
        affichagejolidetheo(solFinal);

    }
}
