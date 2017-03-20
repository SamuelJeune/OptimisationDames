package AlgoRecuit;

import java.util.Random;

/**
 * Created by Sam on 20/03/2017.
 */
public class Util {

    public static int[] getVoisinage(int[] solActuelle, int n){
        Random random = new Random();
        int rand1 = random.nextInt(n-1);
        int rand2 = random.nextInt(n-1);
        int[] solNew = solActuelle.clone();
        solNew[rand1]=solActuelle[rand2];
        solNew[rand2]=solActuelle[rand1];
        return solNew;
    }

    public static int getFitness(int[] solCandidate, int n){
        int fitness = 0;
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                if(Math.abs(solCandidate[i]-solCandidate[j])==Math.abs(i-j)){
                    fitness++;
                }
            }
        }
        return fitness;
    }

    public static Action getAction(int[] solActuelle, int n){
        Random random = new Random();
        int rand1 = random.nextInt(n-1);
        int rand2 = random.nextInt(n-1);
        return new Action(rand1,rand2);
    }

    public static int[] getVoisinByAction(Action action, int[] solActuelle){
        int[] solNew = solActuelle.clone();
        int rand1 = action.getA();
        int rand2 = action.getB();
        solNew[rand1]=solActuelle[rand2];
        solNew[rand2]=solActuelle[rand1];
        return solNew;
    }
}
