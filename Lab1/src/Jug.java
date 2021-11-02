import java.util.*;

class Jug implements Ilayout {
    private int[] jugs;
    private static final int dim = 3;

    public Jug() {
        jugs = new int[dim];
    }

    public Jug(int[] j) {
        this.jugs = new int[dim];
        for(int i = 0; i < dim; i++)
            this.jugs[i] = j[i];
    }

    public Jug(Jug b) {
        this.jugs = new int[dim];
        for(int i = 0; i < dim; i++)
                this.jugs[i] = b.jugs[i];
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < dim; i++) {
            result += this.jugs[i];
            if(i != dim-1)
                result += " ";
        }
        result += "\n";
        return result;
    }

    @Override
    public List<Ilayout> children() {
        List<Ilayout> result = new ArrayList<>();
        Jug newJug;

        // Implement for() for all children

        return result;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jug b = (Jug) o;
        for(int i = 0; i < dim; i++) {
            if(b.jugs[i] != this.jugs[i])
                return false;
        }
        return true;
    }

    @Override
    public boolean isGoal(Ilayout l) {
        return this.equals(l);
    }

    @Override
    public double getG() {
        return 1;
    }
}