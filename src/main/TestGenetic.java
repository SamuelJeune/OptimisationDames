package main;

import algo.genetic.Genetic;

/**
 * Created by hagoterio on 22/05/17.
 */
public class TestGenetic {

    private static int n;
    private static int nbGeneration;
    private static double mutationLuck;
    private static int nbPop;
    private static int nbTest = 20;
    private static int nbFailure;

    public static void main(String[] args){
        int[] tabN = new int[]{20,50,100,200,400,500};



        for(int l=0;l<tabN.length;l++){
            n=tabN[l];
            resetValues();
            System.out.println("test avec n = "+n);
            execute();
        }

    }

    private static void resetValues(){
        nbGeneration = 50;
        nbPop = 100;
        mutationLuck = 0.05;
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
