package com.example;

import javax.xml.validation.Validator;

public class SubGridValidator implements SudokuValidator {
    private final int[][] grid;
    private final int startRow, startCol;

    public SubGridValidator(int[][] grid, int startRow, int startCol) {
        this.grid = grid;
        this.startRow = startRow;
        this.startCol = startCol;
    }
    @Override
    public boolean Validate() {
        boolean[] seen = new boolean[10];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int num = grid[startRow + i][startCol + j];
                if (seen[num]) return false;
                seen[num] = true;
            }
        }
        return true;
    }
}
