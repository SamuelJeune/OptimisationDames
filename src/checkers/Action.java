package checkers;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Action action = (Action) o;

        if (A != action.A) return false;
        return B == action.B;
    }

    @Override
    public int hashCode() {
        int result = A;
        result = 31 * result + B;
        return result;
    }


}
