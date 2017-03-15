package AlgoRecuit;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by sam on 15/03/17.
 */
public class RecuitSimule {

    int[] solInitial;
    double t0;
    int n;
    int maxIteration;
    double mu;
    int[] solFinal;
    int fitnessFinal;
    Random random = new Random();



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
        this.fitnessFinal=getFitness(solFinal);
        algoRecuit();
    }





    public int[] getVoisinage(int[] solActuelle){
        int rand1 = random.nextInt(n-1);
        int rand2 = random.nextInt(n-1);
        int[] solNew = solActuelle.clone();
        solNew[rand1]=solActuelle[rand2];
        solNew[rand2]=solActuelle[rand1];
        return solNew;
    }

    public int getFitness(int[] solCandidate){
        int fitness = 0;
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                if(Math.abs(solCandidate[i]-solCandidate[j])==Math.abs(i-j)){
                    fitness++;
                }
            }
        }
        return fitness;
    }

    public void algoRecuit(){
        double temp=t0;
        int[] solActuel = solInitial;
        int[] solCandidate;
        while(temp>0.2){
            for(int l=1; l<maxIteration;l++){
                solCandidate=getVoisinage(solActuel);
                int dFitness = getFitness(solCandidate)-getFitness(solActuel);
                if(dFitness<=0){
                    solActuel=solCandidate;
                    if(getFitness(solActuel)<fitnessFinal){
                        solFinal=solActuel;
                        fitnessFinal=getFitness(solFinal);
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
    }
}
