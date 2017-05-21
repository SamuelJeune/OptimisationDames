package main;

import algo.recuit.RecuitSimule;

/**
 * Created by hagoterio on 21/05/17.
 */
public class TestRecuit {
    private static int n;
    private static int nbIteration;
    private static int tempInit;
    private static double mu;
    private static int nbTest = 20;
    private static long meanTime;
    private static int nbFailure;
    private static double tempFinal;

    public static void main(String[] args){
        int[] tabN = new int[]{20,50,100,200};



        for(int l=0;l<tabN.length;l++){
            n=tabN[l];
            resetValues();
            System.out.println("test avec n = "+n);
            execute();
        }

    }

    private static void resetValues(){
        nbIteration = 200*n;
        tempInit = 10000;
        mu = 0.8;
        tempFinal = 0.08;
    }

    private static void execute(){
        meanTime = 0;
        nbFailure = 0;
        for(int k=0;k<nbTest;k++){
            long startTime = System.currentTimeMillis();
            RecuitSimule recuitSimule = new RecuitSimule(n,nbIteration,tempInit,mu,tempFinal);
            recuitSimule.run();
            long endTime = System.currentTimeMillis();
            if(!recuitSimule.getSolFinal().isSuccess()) nbFailure++;
            meanTime += endTime - startTime;
        }
        meanTime /= nbTest;
        System.out.println("pourcentage de réussite = "+(100-(double)nbFailure*100/nbTest));
        System.out.println("temps d'execution moyen : " + Util.getTime(meanTime));
    }

}
