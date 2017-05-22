package main;

import algo.genetic.Genetic;
import algo.recuit.RecuitSimule;

/**
 * Created by hagoterio on 22/05/17.
 */
public class TestGeneticParameters {

    private final static int n=100;
    private static int nbGeneration;
    private static double mutationLuck;
    private static int nbPop;
    private static int nbTest = 20;
    private static int nbFailure;

    public static void main(String[] args){
        int[] tabNbPopulation = new int[]{10,50,100,500,1000};
        int[] tabNbGeneration = new int[]{100,500,1000,5000,10000,50000};
        double[] tabMutationLuck = new double[]{0.01,0.05,0.08,0.1,0.2,0.5};

        /** test population number*/
        resetValues();

        for(int l=0;l<tabNbPopulation.length;l++){
            nbPop=tabNbPopulation[l];
            System.out.println("test avec nbPop = "+nbPop);
            execute();
        }

        /** test generation number*/
        resetValues();

        for(int i=0;i<tabNbGeneration.length;i++){
            nbGeneration=tabNbGeneration[i];
            System.out.println("test avec nbGeneration = "+nbGeneration);
            execute();
        }

        /** test mutation luck */
        resetValues();

        for(int i=0;i<tabMutationLuck.length;i++){
            mutationLuck=tabMutationLuck[i];
            System.out.println("test avec mutationLick = "+mutationLuck);
            execute();
        }

    }

    private static void resetValues(){
        nbGeneration = 1000;
        nbPop = 200;
        mutationLuck = 0.1;
    }

    private static void execute(){
        long meanTime = 0;
        long meanFitness = 0;
        nbFailure = 0;
        for(int k=0;k<nbTest;k++){
            long startTime = System.currentTimeMillis();
            Genetic gen = new Genetic(n,nbGeneration,nbPop,mutationLuck);
            gen.run();
            long endTime = System.currentTimeMillis();
            if(!gen.getBest().isSuccess()) nbFailure++;
            meanTime += endTime - startTime;
            meanFitness += gen.getBest().getFitness();
        }
        meanTime /= nbTest;
        meanFitness /= nbTest;
        System.out.println("pourcentage de réussite = "+(100-(double)nbFailure*100/nbTest));
        System.out.println("fitness moyenne = "+meanFitness);
        System.out.println("temps d'execution moyen : " + Util.getTime(meanTime));
    }
}
