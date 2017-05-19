package AlgoRecuit;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sam on 20/03/2017.
 */
public class Util {

    public static int[] getRandNeighbor(int[] solActuelle, int n){
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
            for(int j=i+1; j<n; j++){
                if(Math.abs(solCandidate[i]-solCandidate[j])==Math.abs(i-j)){
                    fitness++;
                }
            }
        }
        return fitness;
    }

    public static Action getAction(int n){
        Random random = new Random();
        int rand1 = random.nextInt(n-1);
        int rand2 = random.nextInt(n-1);
        return new Action(rand1,rand2);
    }

    public static int[] getNeighborByAction(Action action, int[] currentSol){
        int[] newSol = currentSol.clone();
        int A = action.getA();
        int B = action.getB();
        newSol[A]=currentSol[B];
        newSol[B]=currentSol[A];
        return newSol;
    }

    public static Action getBestAction(int[] solution,int n, ArrayList<Action> actions){
        int[] currentSolution;
        int bestFitness = Integer.MAX_VALUE;
        int currentFitness;
        Action bestAction = null;
        for(Action action : actions){
            currentSolution = getNeighborByAction(action, solution);
            currentFitness = getFitness(currentSolution, n);
            if (currentFitness < bestFitness) {
                bestFitness = currentFitness;
                bestAction = action;
            }
        }
        return bestAction;
    }

    public static ArrayList<Action> getListActions(int n, ArrayList<Action> tabouList){
        Action action;
        ArrayList<Action> actions = new ArrayList<>();
        for(int i = 0;i<n;i++){
            for(int j = i+1;j<n;j++){
                action = new Action(i,j);
                if(!isTabou(tabouList,action)) {
                    actions.add(action);
                }
            }
        }
        return actions;
    }

    public static void affichagejolidetheo(int[] solFinal){
        for(int i=0;i<solFinal.length;i++){
            System.out.print("[");
            for(int j=0; j<solFinal.length; j++){
                if(solFinal[j]==i){
                    System.out.print(" Q");
                } else {
                    System.out.print(" -");
                }
            }
            System.out.println(" ]");
        }
    }

    public static int[] getInitSolution(int n){
        Random rand = new Random();
        int randInt;
        int[] sol = new int[n];
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0;i<n;i++){
            list.add(i);
        }
        for(int i = 0;i<n;i++){
            randInt = rand.nextInt(list.size());
            sol[i] = list.remove(randInt);
        }
        return sol;
    }

    public static boolean isTabou(ArrayList<Action> tabouList, Action action){
        boolean tabou = false;
        int i = 0;
        while(i<tabouList.size() && !tabou){
            if(action.equals(tabouList.get(i))){
                tabou = true;
            }
            i++;
        }
        return tabou;
    }
}
