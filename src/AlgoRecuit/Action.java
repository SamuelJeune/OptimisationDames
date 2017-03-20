package AlgoRecuit;

/**
 * Created by Sam on 20/03/2017.
 */
public class Action {
    private int A;
    private int B;

    public Action(int a, int b) {
        A = a;
        B = b;
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    @Override
    public String toString() {
        return "{" +
                "A=" + A +
                ", B=" + B +
                '}';
    }
}
