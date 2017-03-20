package AlgoRecuit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static AlgoRecuit.Util.affichagejolidetheo;
import static AlgoRecuit.Util.getFitness;

/**
 * Created by Sam on 20/03/2017.
 */
public class Tabou {
    int i =0;
    int nmax;
    List<Action> T = new ArrayList<>();
    int[] solInitial;
    int[] solFinal;
    int nbvoisin;
    int n;

    public Tabou(int nmax, int n) {
        this.i = 0;
        this.nmax = nmax;
        solInitial=new int[n];
        for(int i=0; i<n; i++){
            solInitial[i]= i;
        }
        this.n=n;
        AlgoTabou();
        this.nbvoisin=n*20;
        solFinal=solInitial;
    }

    public void AlgoTabou(){
        int[] solActuel = solInitial;
        solFinal=solInitial;
        while(i!=nmax){
            Action action = Util.getAction(solActuel, n);
            int[] solCandidate=Util.getVoisinByAction(action, solActuel);
            for(int j=0; j<nbvoisin; j++){
                Action action2=Util.getAction(solActuel, n);
                int[] solCandidate2=Util.getVoisinByAction(action2, solActuel);
                if(Util.getFitness(solCandidate2,n)<Util.getFitness(solCandidate,n)){
                    solCandidate=solCandidate2;
                    action=action2;
                }
            }
            if(!T.contains(action)){
                if(Util.getFitness(solCandidate, n)<=Util.getFitness(solActuel, n)){
                    T.add(action);
                }
                solActuel=solCandidate;
                System.out.println(Arrays.toString(solActuel));
                System.out.println(T.toString());
            }
            if(T.size()>n-2){
                T.remove(0);
            }
            i++;
            if(getFitness(solFinal,n)>getFitness(solActuel,n)){
                solFinal=solActuel;
            }
        }
        System.out.println(Arrays.toString(solFinal));
        affichagejolidetheo(solFinal);
        System.out.println(getFitness(solFinal, n));
    }
}
