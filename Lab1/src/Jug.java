import java.util.*;

class Jug implements Ilayout {
    private int[] jugs;
    private static final int dim = 3;
    private static final int lim0 = 8;
    private static final int lim1 = 5;
    private static final int lim2 = 3;

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
        return result;
    }

    private int[] moveWater(Jug newJug, int i, int j) {
        int counter = 0;
        
        switch (j) {
            case 0:
                if(newJug.jugs[j] < lim0) {
                    counter = lim0 - newJug.jugs[j];
                    if(newJug.jugs[i] > counter) {
                        newJug.jugs[j] += counter;
                        newJug.jugs[i] -= counter;
                    }
                    else {
                        counter = newJug.jugs[i];
                        newJug.jugs[j] += counter;
                        newJug.jugs[i] -= counter;
                    }
                }
                break;
        
            case 1:
            if(newJug.jugs[j] < lim1) {
                counter = lim1 - newJug.jugs[j];
                if(newJug.jugs[i] > counter) {
                    newJug.jugs[j] += counter;
                    newJug.jugs[i] -= counter;
                }
                else {
                    counter = newJug.jugs[i];
                    newJug.jugs[j] += counter;
                    newJug.jugs[i] -= counter;
                }
            }
                break;
            
            case 2:
            if(newJug.jugs[j] < lim2) {
                counter = lim2 - newJug.jugs[j];
                if(newJug.jugs[i] > counter) {
                    newJug.jugs[j] += counter;
                    newJug.jugs[i] -= counter;
                }
                else {
                    counter = newJug.jugs[i];
                    newJug.jugs[j] += counter;
                    newJug.jugs[i] -= counter;
                }
            }
                break;
        }
        return newJug.jugs;
    }

    @Override
    public List<Ilayout> children() {
        List<Ilayout> result = new ArrayList<>();
        Jug newJug;

        for(int i = 0; i < dim; i++) {
            for(int j = 0; j < dim; j++) {
                newJug = new Jug(this);
                if(i != j && newJug.jugs[i] != 0) {
                    moveWater(newJug, i, j);
                    if(!Arrays.equals(this.jugs, newJug.jugs))
                        result.add(newJug);
                }
            }
        }
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