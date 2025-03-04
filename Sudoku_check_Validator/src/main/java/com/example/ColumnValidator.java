package com.example;

public class ColumnValidator implements SudokuValidator {
  private final int column;
  private final int[][] grid;
  public ColumnValidator(int column, int[][] grid) {
      this.grid = grid;
      this.column = column;
  }
    @Override
    public boolean Validate() {
        boolean[] seen = new boolean[10];
        for (int i = 0; i < 9; i++) {
            int num = this.grid[this.column][i];
            if (seen[num]) return false;
            seen[num] = true;
        }
        return true;
    }
}
