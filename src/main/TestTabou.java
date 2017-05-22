package main;

import algo.tabou.Tabou;

/**
 * Created by hagoterio on 22/05/17.
 */
public class TestTabou {
    private static int n;
    private static int nMax;
    private static int tabouSize;
    private static int nbTest = 5;
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
        nMax = 5*n;
        tabouSize = n/10;
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
