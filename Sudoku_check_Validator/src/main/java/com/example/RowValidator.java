package com.example;

public class RowValidator implements SudokuValidator{
    private final int row;
    private final int grid[][];
    public RowValidator(int row,int grid[][]) {
        this.grid = grid;
        this.row = row;
    }
    @Override
    public boolean Validate() {
        boolean[] seen = new boolean[10];
        for (int i = 0; i < 9; i++) {
            int num = this.grid[i][this.row];
            if (seen[num]) return false;
            seen[num] = true;
        }
        return true;
    }
}
