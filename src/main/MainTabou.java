package main;

import algo.tabou.Tabou;

/**
 * Created by hagoterio on 19/05/17.
 */
public class MainTabou {


    private final static int N = 100;
    private final static int N_MAX = 500;

    public static void main(String[] args){
        algo.tabou.Tabou tabou = new algo.tabou.Tabou(N_MAX, N);
        tabou.run();
        System.out.println(tabou.getSolFinal());
        System.out.println("nombre d'iterations = "+tabou.getNbIteration());
        System.out.println("fitness = "+tabou.getSolFinal().getFitness());
    }

}
