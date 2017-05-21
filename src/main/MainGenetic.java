package main;

import algo.genetic.Genetic;

/**
 * Created by hagoterio on 20/05/17.
 */
public class MainGenetic {
    private final static int N = 100;
    private final static int NB_POPULATION = 1000;
    private final static int NB_GENERATION = 500;
    private final static double MUTATION_LUCK = 0.05;

    public static void main(String[] args){
        long startTime = System.currentTimeMillis();
        Genetic gen = new Genetic(N,NB_GENERATION,NB_POPULATION,MUTATION_LUCK);
        gen.run();
        long endTime = System.currentTimeMillis();
        gen.getBest().display();
        System.out.println("fitness = "+gen.getBest().getFitness());
        System.out.println("temps d'execution : " + Util.getTime((endTime - startTime)));
    }
}
