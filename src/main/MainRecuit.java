package main;

import algo.recuit.RecuitSimule;

/**
 * Created by hagoterio on 20/05/17.
 */
public class MainRecuit {

    private final static int N = 100;
    private final static int MAX_ITERATION = 10000;
    private final static int TEMP_INIT = 10000;
    private final static double MU = 0.7;

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
       RecuitSimule recuitSimule = new RecuitSimule(N,MAX_ITERATION,TEMP_INIT,MU);
       recuitSimule.run();
        long endTime = System.currentTimeMillis();
       System.out.println(recuitSimule.getSolFinal());
       System.out.println("fitness = "+recuitSimule.getSolFinal().getFitness());
        System.out.println("temps d'execution : " + Util.getTime((endTime - startTime)));
    }

}
