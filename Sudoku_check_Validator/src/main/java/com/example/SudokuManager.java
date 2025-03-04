package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SudokuManager {
    private int SudokuPuzzle[][];
    private final int rows;
    private final int cols;
    public SudokuManager(int SudokuPuzzle[][]){
        this.SudokuPuzzle=SudokuPuzzle;
        this.rows=9;
        this.cols=9;
    }
    public boolean checkColumn(int col) {
        int visited[] = new int[10];
        for (int i = 0; i < rows; i++) { // FIX: Iterate over all rows
            int num = SudokuPuzzle[i][col];
            if (num < 1 || num > 9 || visited[num] == 1) { // FIX: Correct condition
                return false;
            }
            visited[num] = 1;
        }
        return true;
    }


    public boolean checkRow(int row ){
        int visited[] = new int[10];
        for (int i = 0; i < cols; i++) {
            int num = SudokuPuzzle[row][i];
            if (num < 1 || num > 9 || visited[num] == 1) {
                return false;
            }
            visited[num] = 1;
        }
        return true;
    }
    public boolean checkSubPuzzle(int startRow, int startCol) {
        int visited[] = new int[10]; // Reset for each subgrid
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                int num = SudokuPuzzle[i][j];
                if (num < 1 || num > 9 || visited[num] == 1) {
                    return false;
                }
                visited[num] = 1;
            }
        }
        return true;
    }
    public boolean isValidSudokuSingleThreaded() {
        // Check all rows and columns
        for (int i = 0; i < 9; i++) {
            if (!checkRow(i) || !checkColumn(i)) {
                return false;
            }
        }

        // Check all 3×3 sub-grids
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                if (!checkSubPuzzle(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidSudokuMultiThreaded() throws ExecutionException, InterruptedException {
        ExecutorService executor= Executors.newFixedThreadPool(1);
        List<Future<Boolean>> futures = new ArrayList<>();
        // Submit row and column checks
        for (int i = 0; i < 9; i++) {
            final int index = i;
            futures.add(executor.submit(() -> checkRow(index)));
            futures.add(executor.submit(() -> checkColumn(index)));
        }

        for(int i=0;i<9;i+=3){
            for(int j=0;j<9;j+=3){
                final int row = i, col = j;
                futures.add(executor.submit(() -> checkSubPuzzle(row, col)));
            }
        }

        for(Future<Boolean> future:futures){
            if (!future.get()){
                executor.shutdown();
                return false;
            }
        }
        executor.shutdown();
        return true;
    }
}
