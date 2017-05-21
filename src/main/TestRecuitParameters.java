package main;

import algo.recuit.RecuitSimule;

/**
 * Created by hagoterio on 21/05/17.
 */
public class TestRecuitParameters {

    private static int n;
    private static int nbIteration;
    private static int tempInit;
    private static double mu;
    private static int nbTest = 20;
    private static long meanTime;
    private static int nbFailure;
    private static double tempFinal;

    public static void main(String[] args){
        int[] tabIteration = new int[]{1000,5000,10000,20000,50000};
        int[] tabTempInit = new int[]{100,500,1000,5000,10000,50000};
        double[] tabMu = new double[]{0.2,0.5,0.7,0.8,0.9};
        double[] tabTempFinal = new double[]{0.01,0.05,0.08,0.1,0.2,0.5};

        /** test mu*/
        resetValues();;

        for(int l=0;l<tabMu.length;l++){
            mu=tabMu[l];
            System.out.println("test avec mu = "+tabMu[l]);
            execute();
        }

        /** test nbIteration*/
        resetValues();

        for(int i=0;i<tabIteration.length;i++){
            nbIteration=tabIteration[i];
            System.out.println("test avec nbIteration = "+nbIteration);
            execute();
        }

        /** test initial temperature */
        resetValues();

        for(int i=0;i<tabTempInit.length;i++){
            tempInit=tabTempInit[i];
            System.out.println("test avec tempInit = "+tempInit);
            execute();
        }

        /** test finale temperature */
        resetValues();

        for(int i=0;i<tabTempFinal.length;i++){
            tempFinal=tabTempFinal[i];
            System.out.println("test avec tempFinal = "+tempFinal);
            execute();
        }

        /** test nbIteration with n=100*/
        resetValues();
        n = 100;
        for(int i=0;i<tabIteration.length;i++){
            nbIteration=tabIteration[i];
            System.out.println("test avec nbIteration = "+nbIteration);
            execute();
        }

    }

    private static void resetValues(){
        n=50;
        nbIteration = 5000;
        tempInit = 1000;
        mu = 0.7;
        tempFinal = 0.2;
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
