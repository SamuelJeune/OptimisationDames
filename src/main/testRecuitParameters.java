package main;

import algo.recuit.RecuitSimule;

/**
 * Created by hagoterio on 21/05/17.
 */
public class testRecuitParameters {

    private static int n;
    private static int nbIteration;
    private static int tempInit;
    private static double mu;
    private static int nbTest = 20;
    private static long meanTime;
    private static int nbFailure;

    public static void main(String[] args){
        int[] tabN = new int[]{20,50,100,200};
        int[] tabIteration = new int[]{100,500,1000,10000};
        int[] tabTempInit = new int[]{100,500,1000,10000};
        double[] tabMu = new double[]{0.2,0.5,0.7,0.8,0.9};
        int nbTest = 20;

        /** test mu*/
        resetValues();;

        for(int l=0;l<tabMu.length;l++){
            mu=tabMu[l];
            System.out.println("test avec mu = "+tabMu[l]);
            nbFailure = 0;
            meanTime = 0;
            execute();
            meanTime /= nbTest;
            System.out.println("pourcentage de réussite = "+(100-(double)nbFailure*100/nbTest));
            System.out.println("temps d'execution moyen : " + Util.getTime(meanTime));
        }

        /** test nbIteration*/
        resetValues();

        for(int i=0;i<tabIteration.length;i++){
            nbIteration=tabIteration[i];
            System.out.println("test avec nbIteration = "+nbIteration);
            nbFailure = 0;
            meanTime = 0;
            execute();
            meanTime /= nbTest;
            System.out.println("pourcentage de réussite = "+(100-(double)nbFailure*100/nbTest));
            System.out.println("temps d'execution moyen : " + Util.getTime(meanTime));
        }

/*        for(int i=0;i<tabN.length;i++){
            System.out.println("test avec n = "+tabN[i]);
            for(int j=0;j<tabIteration.length;j++){
                System.out.println("test avec nbIterations = "+tabIteration[j]);
                for(int k=0;k<tabTempInit.length;k++){
                    System.out.println("test avec température initiale = "+tabTempInit[k]);
                    for(int l=0;l<tabMu.length;l++){
                        System.out.println("test avec mu = "+tabMu[l]);
                        nbFailure = 0;
                        meanTime = 0;
                        for(int m=0;m<5;m++){
                            long startTime = System.currentTimeMillis();
                            RecuitSimule recuitSimule = new RecuitSimule(tabN[i],tabIteration[j],tabTempInit[k],tabMu[l]);
                            recuitSimule.run();
                            long endTime = System.currentTimeMillis();
                            if(!recuitSimule.getSolFinal().isSuccess()) nbFailure++;
                            meanTime += endTime - startTime;
                        }
                        meanTime /= 5;
                        System.out.println("pourcentage de réussite = "+(100-(double)nbFailure*100/5));
                        System.out.println("temps d'execution moyen : " + Util.getTime(meanTime));
                    }
                }
            }
        }*/

    }

    private static void resetValues(){
        n=50;
        nbIteration = 5000;
        tempInit = 1000;
        mu = 0.7;
    }

    private static void execute(){
        meanTime = 0;
        nbFailure = 0;
        for(int k=0;k<nbTest;k++){
            long startTime = System.currentTimeMillis();
            RecuitSimule recuitSimule = new RecuitSimule(n,nbIteration,tempInit,mu);
            recuitSimule.run();
            long endTime = System.currentTimeMillis();
            if(!recuitSimule.getSolFinal().isSuccess()) nbFailure++;
            meanTime += endTime - startTime;
        }
    }
}
