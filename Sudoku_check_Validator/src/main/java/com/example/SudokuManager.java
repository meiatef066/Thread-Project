package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SudokuManager {
    private final int[][] sudoku;
  private final ExecutorService executor= Executors.newFixedThreadPool(5);

    public SudokuManager( int[][] sudoku ) {
        this.sudoku = sudoku;
    }

    public boolean isValidSudokuMultiThreaded() throws ExecutionException, InterruptedException {
        List<Future<Boolean>> futures = new ArrayList<>();

        // Submit row, column, and sub-grid validation tasks
        for (int i = 0; i < 9; i++) {
            futures.add(executor.submit(new RowValidator(i, sudoku)::Validate));
            futures.add(executor.submit(new ColumnValidator(i, sudoku)::Validate));
        }

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                futures.add(executor.submit(new SubGridValidator(sudoku, i, j)::Validate));
            }
        }

        // Collect results
        for (Future<Boolean> future : futures) {
            if (!future.get()) {
                executor.shutdown();
                return false;
            }
        }

        executor.shutdown();
        return true;
    }
}
