package checkers;

import checkers.Action;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sam on 20/03/2017.
 */
public class Util {

    public static Checkers getRandNeighbor(Checkers sol){
        Random random = new Random();
        int rand1 = random.nextInt(sol.getSize()-1);
        int rand2 = random.nextInt(sol.getSize()-1);
        Action action = new Action(rand1,rand2);
        return  getNeighborByAction(sol,action);
    }

    public static Checkers getNeighborByAction(Checkers currentSol, Action action){
        Checkers newSol = currentSol.clone();
        newSol.applyAction(action);
        return newSol;
    }

    public static Action getBestAction(Checkers solution, ArrayList<Action> actions){
        Checkers currentSolution;
        int bestFitness = Integer.MAX_VALUE;
        int currentFitness;
        Action bestAction = null;
        for(Action action : actions){
            currentSolution = getNeighborByAction(solution, action);
            currentFitness = currentSolution.getFitness();
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
