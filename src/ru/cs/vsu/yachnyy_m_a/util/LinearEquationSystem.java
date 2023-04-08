package ru.cs.vsu.yachnyy_m_a.util;

import ru.cs.vsu.yachnyy_m_a.Matrix;

public class LinearEquationSystem {

    private int[][] expanded_matrix;
    private int var_count;

    public LinearEquationSystem(int[][] expanded_matrix) {
        this.expanded_matrix = expanded_matrix;
        if(expanded_matrix.length != expanded_matrix[0].length - 1) throw new IllegalArgumentException("matrix must be n x (n-1) size");
        var_count = expanded_matrix.length;
    }

    public double[] solveReverseMatrixMethod(){
        int[][] coef_m = new int[var_count][var_count];
        int[][] right_m = new int[var_count][1];

        for (int i = 0; i < expanded_matrix.length; i++) {
            for (int j = 0; j < expanded_matrix[0].length - 1; j++) {
                coef_m[i][j] = expanded_matrix[i][j];
            }
            right_m[i][0] = expanded_matrix[i][var_count];
        }

        double[][] inv = Matrix.invertedMatrix(coef_m);

        if(inv == null)return null;
        return Matrix.transpose(Matrix.multiply(inv, right_m))[0];
    }

}
