package main;

import algo.tabou.Tabou;

/**
 * Created by hagoterio on 22/05/17.
 */
public class TestTabouParameters {
    private static int n;
    private static int nMax;
    private static int tabouSize;
    private static int nbTest = 20;
    private static int nbFailure;

    public static void main(String[] args){
        int[] tabTabouSize = new int[]{1,5,10,50,100};
        int[] tabNMax = new int[]{10,100,500,1000};
        int[] tabN = new int[]{20,50,100,200};

        /** test board size*/
        for(int j = 0;j<tabN.length;j++){
            n=tabN[j];
            System.out.println("taille = "+n);
            resetValues();

            /** test generation number*/
            for(int l=0;l<tabNMax.length;l++){
                nMax=tabNMax[l];
                System.out.println("test avec nMax = "+nMax);
                execute();
            }

            /** test tabou list size */
            resetValues();

            for(int i=0;i<tabTabouSize.length;i++){
                tabouSize=tabTabouSize[i];
                System.out.println("test avec tabouSize = "+tabouSize);
                execute();
            }
        }

    }

    private static void resetValues(){
        nMax = 500;
        tabouSize = 200;
    }

    private static void execute(){
        long meanTime = 0;
        nbFailure = 0;
        for(int k=0;k<nbTest;k++){
            long startTime = System.currentTimeMillis();
            Tabou tabou = new Tabou(nMax,n,tabouSize);
            tabou.run();
            long endTime = System.currentTimeMillis();
            if(!tabou.getSolFinal().isSuccess()) nbFailure++;
            meanTime += endTime - startTime;
        }
        meanTime /= nbTest;
        System.out.println("pourcentage de réussite = "+(100-(double)nbFailure*100/nbTest));
        System.out.println("temps d'execution moyen : " + Util.getTime(meanTime));
    }
}
