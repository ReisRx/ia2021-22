import java.util.*;

class Board implements Ilayout, Cloneable {
    private static final int dim=3;
    private int board [][];
    private int modX;
    private int modY;

    public Board() {
        board = new int [dim][dim];
    }

    public Board(String str) throws IllegalStateException {
        if(str.length() != dim*dim) 
            throw new IllegalStateException("Invalid arg in Board constructor");
        board = new int[dim][dim];
        int si = 0;
        for(int i = 0; i < dim; i++) {
            for(int j = 0; j < dim; j++) {
                board[i][j] = Character.getNumericValue(str.charAt(si++));
                
                if(board[i][j] == 0) {
                    modX = i;
                    modY = j;
                }
            }
        }
    }

    public Board(Board b) {
        this.board = new int[dim][dim];
        for(int i = 0; i < dim; i++) {
            for(int j = 0; j < dim; j++) {
                this.board[i][j] = b.board[i][j];
                if(board[i][j] == 0) {
                    modX = i;
                    modY = j;
                }
            }
        }
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

    private int[][] moveNumbers(Board b, int mod_X, int mod_Y, int sumX, int sumY) {
        int counter = b.board[modX+sumX][modY+sumY];
        b.board[modX+sumX][modY+sumY] = 0;
        b.board[modX][modY] = counter;
        b.modX = modX+sumX;
        b.modY = modY+sumY;
        return b.board;
    }

    @Override
    public List<Ilayout> children() {
        List<Ilayout> result = new ArrayList<>();
        Board newBoard;

        if(modY - 1 >= 0) {
            newBoard = new Board(this);
            newBoard.board = moveNumbers(newBoard, modX, modY, 0, -1);
            result.add(newBoard);
        }
        if(modY + 1 <= 2) {
            newBoard = new Board(this);
            newBoard.board = moveNumbers(newBoard, modX, modY, 0, 1);
            result.add(newBoard);
        }
        if(modX - 1 >= 0) {
            newBoard = new Board(this);
            newBoard.board = moveNumbers(newBoard, modX, modY, -1, 0);
            result.add(newBoard);
        }
        if(modX + 1 <= 2) {
            newBoard = new Board(this);
            newBoard.board = moveNumbers(newBoard, modX, modY, 1, 0);
            result.add(newBoard);
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

    @Override
    public double getH(Ilayout l) {
        // TODO Auto-generated method stub
        return 0;
    }
}
