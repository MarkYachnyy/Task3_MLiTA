package ru.cs.vsu.yachnyy_m_a;

import java.util.Arrays;
import java.util.function.Consumer;

public class Matrix {

    public static int[][] subMatrix(int[][] matrix, int I, int J) {
        int[][] res = new int[matrix.length - 1][matrix.length - 1];
        for (int i = 0; i < matrix.length - 1; i++) {
            for (int j = 0; j < matrix.length - 1; j++) {
                res[i][j] = matrix[i < I - 1? i : i + 1][j < J - 1 ? j : j + 1];
            }
        }
        return res;
    }

    public static long minor(int[][] matrix, int I, int J){
        return Matrix.determinant(subMatrix(matrix, I, J));
    }

    public static long determinant(int[][] matrix) {
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        long res = 0;
        for (int j = 1; j <= matrix.length; j++) {
            res += matrix[0][j - 1] * (1 - 2 * ((j + 1) % 2)) * minor(matrix, 1, j);
        }
        return res;
    }

    public static double[][] invertedMatrix(int[][] matrix){
        if(matrix.length != matrix[0].length) throw new IllegalArgumentException("Матрица должна быть квадратной!");

        long det = determinant(matrix);
        if(det == 0) return null;

        double[][] additions = new double[matrix.length][matrix.length];
        for (int i = 0; i < additions.length; i++) {
            for (int j = 0; j < additions.length; j++) {
                additions[i][j] = 1.0 * (1 - 2 * ((i + j) % 2)) * minor(matrix, i + 1, j + 1) / det;
            }
        }

        return transpose(additions);
    }

    public static double[][] multiply(double[][] A, double[][] B){
        if (A[0].length != B.length) throw new IllegalArgumentException();

        double[][] res = new double[A.length][B[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                double c = 0;
                for (int k = 0; k < B.length; k++) {
                    c += A[i][k] * B[k][j];
                }
                res[i][j] = c;
            }
        }
        return res;
    }

    public static double[][] multiply(double[][] A, int[][] B){
        return multiply(A, castToDouble(B));
    }

    public static double[][] castToDouble(int[][] m){
        double[][] res = new double[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                res[i][j] = (double) m[i][j];
            }
        }
        return res;
    }

    public static int[][] transpose(int[][] matrix){
        int[][] res = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }

    public static double[][] transpose(double[][] matrix){
        double[][] res = new double[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                res[j][i] = matrix[i][j];
            }
        }
        return res;
    }
}
