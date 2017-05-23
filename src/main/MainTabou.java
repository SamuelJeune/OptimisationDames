package main;

import algo.tabou.Tabou;

import java.util.Arrays;

/**
 * Created by hagoterio on 19/05/17.
 */
public class MainTabou {


    private final static int N = 15; // taille de l'echiquier
    private final static int N_MAX = 5*N; // nb max d'itération
    private final static int T_SIZE = Math.max(N/10,1); // taille de la liste tabou

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        Tabou tabou = new algo.tabou.Tabou(N_MAX, N, T_SIZE);
        tabou.run();
        long endTime = System.currentTimeMillis();
        System.out.println(tabou.getSolFinal());
        System.out.println(Arrays.toString(tabou.getSolFinal().getBoard()));
        System.out.println("nombre d'iterations = "+tabou.getNbIteration());
        System.out.println("fitness = "+tabou.getSolFinal().getFitness());
        System.out.println("temps d'execution : " + Util.getTime((endTime - startTime)));
    }

}
