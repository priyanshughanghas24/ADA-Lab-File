package ADACodesFiles;

public class NQueen {
    final int N = 4;

    void printSolution(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + (board[i][j] == 1 ? "Q" : ".") + " ");
            System.out.println();
        }
    }

    boolean isSafe(int board[][], int row, int col) {
        int i, j;
        // Check this row on left side
        for (i = 0; i < col; i++)
            if (board[row][i] == 1) return false;

        // Check upper diagonal on left side
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1) return false;

        // Check lower diagonal on left side
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1) return false;

        return true;
    }

    boolean solveNQUtil(int board[][], int col) {
        if (col >= N) return true;

        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1; // Place queen
                if (solveNQUtil(board, col + 1)) return true;
                board[i][col] = 0; // Backtrack
            }
        }
        return false;
    }

    void solve() {
        int board[][] = new int[N][N];
        if (!solveNQUtil(board, 0)) {
            System.out.print("Solution does not exist");
            return;
        }
        printSolution(board);
    }

    public static void main(String args[]) {
        NQueen Queen = new NQueen();
        Queen.solve();
    }
}