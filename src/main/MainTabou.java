package main;

import algo.tabou.Tabou;

/**
 * Created by hagoterio on 19/05/17.
 */
public class MainTabou {


    private final static int N = 1000; // taille de la grille
    private final static int N_MAX = 500; // nb max d'itération
    private final static int T_SIZE = 100; // taille de la liste tabou

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        Tabou tabou = new algo.tabou.Tabou(N_MAX, N, T_SIZE);
        tabou.run();
        long endTime = System.currentTimeMillis();
        System.out.println(tabou.getSolFinal());
        System.out.println("nombre d'iterations = "+tabou.getNbIteration());
        System.out.println("fitness = "+tabou.getSolFinal().getFitness());
        System.out.println("temps d'execution : " + Util.getTime((endTime - startTime)));
    }

}
