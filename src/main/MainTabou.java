package main;

import algo.tabou.Tabou;

import java.util.Arrays;

/**
 * Created by hagoterio on 19/05/17.
 */
public class MainTabou {


    private final static int N = 8;
    private final static int N_MAX = 500;

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        Tabou tabou = new algo.tabou.Tabou(N_MAX, N);
        tabou.run();
        long endTime = System.currentTimeMillis();
        System.out.println(tabou.getSolFinal());
        System.out.println(Arrays.toString(tabou.getSolFinal().getBoard()));
        System.out.println("nombre d'iterations = "+tabou.getNbIteration());
        System.out.println("fitness = "+tabou.getSolFinal().getFitness());
        System.out.println("temps d'execution : " + Util.getTime((endTime - startTime)));
    }

}
