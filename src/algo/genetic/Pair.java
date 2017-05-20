package algo.genetic;

/**
 * Created by sam on 17/05/17.
 */
public class Pair implements Comparable<Pair>{

    private int key;
    private int value;

    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Pair o) {
        if(o.getValue()<this.value) return 1;
        return 0;
    }
}
