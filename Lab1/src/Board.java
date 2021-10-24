import java.util.*;

class Board implements Ilayout, Cloneable {
    private static final int dim=3;
    private int board [][];

    public Board() {
        board = new int [dim][dim];
    }

    public Board(String str) throws IllegalStateException {
        if(str.length() != dim*dim) 
            throw new IllegalStateException("Invalid arg in Board constructor");
        board = new int[dim][dim];
        int si = 0;
        for(int i = 0; i < dim; i++)
            for(int j = 0; j < dim; j++)
                board[i][j] = Character.getNumericValue(str.charAt(si++));
    }

    public String toString() {
        String result = "";
        for(int i = 0; i < dim; i++) {
            for(int j = 0; j < dim; j++) {
                if(this.board[i][j] == 0)
                    result += " ";
                else result += this.board[i][j];
            }
            result += "\n";
        }
        return result;
    }

    @Override
    public List<Ilayout> children() {
        List<Ilayout> result = new ArrayList<Ilayout>();
        
    }

    @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Board b = (Board) o;
            for(int i = 0; i < dim; i++) {
                for(int j = 0; j < dim; j++) {
                    if(b.board[i][j] != this.board[i][j])
                        return false;
                }
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
