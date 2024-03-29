import java.util.*;

class Jug implements Ilayout {
    private int[] jugs;
    private int[] jugsLimit;
    private int dim = 3;

    public Jug() {
        this.jugs = new int[dim];
        this.jugsLimit = new int [dim];
    }

    public Jug(int[] j, int[] limits, int size) { // Construtor Jug da Main
        this.dim = size;
        this.jugs = new int[dim];
        this.jugsLimit = new int [dim];
        for(int i = 0; i < dim; i++) {
            this.jugs[i] = j[i];
            this.jugsLimit[i] = limits[i];
        }
    }

    public Jug(Jug b) {
        this.dim = b.dim;
        this.jugs = new int[dim];
        this.jugsLimit = new int [dim];
        for(int i = 0; i < dim; i++) {
            this.jugs[i] = b.jugs[i];
            this.jugsLimit[i] = b.jugsLimit[i];
        }
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

        if(newJug.jugs[j] < this.jugsLimit[j]) { // compara a ver se é menor que o jugLimit
            counter = this.jugsLimit[j] - newJug.jugs[j]; // água necessária pra se mover
            if(newJug.jugs[i] > counter) { // compara água necessária com o que pode levar
                newJug.jugs[j] += counter; // retira de um e mete no outro
                newJug.jugs[i] -= counter;
            }
            else {
                counter = newJug.jugs[i]; // move tudo para o jug recetor
                newJug.jugs[j] += counter;
                newJug.jugs[i] -= counter;
            }
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

    @Override
    public double getH(Ilayout goal) { // Compara o atual com o Goal, se for diferente h++
        double result = 0;
        Jug l = (Jug) goal;
        for(int i = 0; i < dim; i++) {
            if(this.jugs[i] != l.jugs[i]) 
                result++;
        }
        return result;
    }
}