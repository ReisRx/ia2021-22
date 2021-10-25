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

    private int[] modifierLocation(int[][] b) {
        int[] result = new int[2];
        for(int i = 0; i < dim; i++) {
            for(int j = 0; j < dim; j++) {
                if(this.board[i][j] == 0) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result; 
    }

    private int[][] moveNumbers(int[] coords, int modX, int modY) {
        int[][] boardModded = new int[dim][dim];
        int counter = this.board[modX][modY];

        for(int i = 0; i < dim; i++) {
            for(int j = 0; j < dim; j++) {
                if(i == modX && j == modY)
                    boardModded[i][j] = 0;
                else if(i == coords[0] && j == coords[1])
                    boardModded[i][j] = counter;
                else boardModded[i][j] = this.board[i][j];
            }
        }
        return boardModded;
    }

    @Override
    public List<Ilayout> children() {
        List<Ilayout> result = new ArrayList<>();
        int[] coords = new int[2];
        Board newBoard;
        coords = modifierLocation(this.board);
        if(coords[1] - 1 >= 0)
            newBoard.board = moveNumbers(coords, coords[0], coords[1]-1); result.add(newBoard);
        if(coords[1] + 1 <= 3)
            moveNumbers(coords, coords[0], coords[1]+1);
        if(coords[0] - 1 >= 0)
            moveNumbers(coords, coords[0]-1, coords[1]);
        if(coords[1] + 1 <= 3)
            moveNumbers(coords, coords[0], coords[1]+1);

        // TO BE COMPLETED
        return result;
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
