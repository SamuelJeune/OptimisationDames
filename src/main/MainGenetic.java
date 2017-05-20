package main;

import algo.genetic.Genetic;

/**
 * Created by hagoterio on 20/05/17.
 */
public class MainGenetic {
    private final static int N = 10;
    private final static int NB_POPULATION = 1000;
    private final static int NB_GENERATION = 100;
    private final static double MUTATION_LUCK = 0.05;

    public static void main(String[] args){
        Genetic gen = new Genetic(N,NB_GENERATION,NB_POPULATION,MUTATION_LUCK);
        gen.run();
        gen.getBest().display();
        System.out.println("fitness = "+gen.getBest().getFitness());
    }
}
