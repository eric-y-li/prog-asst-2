import java.util.Arrays;
import java.util.Random;

public class MatrixOps {

    private MatrixOps(){}

    public static int[][] matrixAdd (int[][] m1, int[][] m2) {
        int[][] result = new int[m1.length][m1[0].length];

        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[0].length; j++) {
                result[i][j] = m1[i][j] + m2[i][j];
            }
        }

        return result;
    }

    public static int[][] matrixScale (int[][] m, int k) {
        int[][] result = new int[m.length][m[0].length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                result[i][j] = k * m[i][j];
            }
        }

        return result;
    }

    public static int[][] matrixMult (int[][] m1, int[][] m2) {
        int m1Rows = m1.length;
        int m1Cols = m1[0].length;
        int m2Cols = m2[0].length;

        int[][] result = new int[m1Rows][m2Cols];

        for (int m1Row = 0; m1Row < m1Rows; m1Row++) {
            for (int m2Col = 0; m2Col < m2Cols; m2Col++) {
                int currentElement = 0;

                for (int k = 0; k < m1Cols; k++) {
                    currentElement += m1[m1Row][k] * m2[k][m2Col];
                }

                result[m1Row][m2Col] = currentElement;
            }
        }

        return result;
    }

    public static int[][] subMatrix (int[][] m, int xStart, int xEnd, int yStart, int yEnd) {
        int[][] res = new int[xEnd-xStart+1][yEnd-yStart+1];
        for (int j = yStart; j <= yEnd; j++) {
            res[j-yStart] = Arrays.copyOfRange(m[j], xStart, xEnd+1);
        }
        return res;
    }

    public static int[][] combineMatrices (int[][] topleft, int[][] topright, int[][] botleft, int[][] botright) {
        int dim = topleft.length;
        int newDim = 2 * dim;
        int[][] res = new int[newDim][newDim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                res[i][j] = topleft[i][j];
                res[i][j+dim] = topright[i][j];
                res[i+dim][j] = botleft[i][j];
                res[i+dim][j+dim] = botright[i][j];
            }
        }
        return res;
    }

    public static int[][] strassenMult (int[][] m1, int[][] m2) {
        if (m1.length <= 16) {
            return matrixMult(m1, m2);
        }

        int orign = m1.length;
        m1 = pad(orign, m1);
        m2 = pad(orign, m2);
        int n = m1.length;

        int[][] A = subMatrix(m1, 0, (n-1)/2, 0, (n-1)/2);
        int[][] B = subMatrix(m1, (n+1)/2, n-1, 0, (n-1)/2);
        int[][] C = subMatrix(m1, 0, (n-1)/2, (n+1)/2, n-1);
        int[][] D = subMatrix(m1, (n+1)/2, n-1, (n+1)/2, n-1);

        int[][] E = subMatrix(m2, 0, (n-1)/2, 0, (n-1)/2);
        int[][] F = subMatrix(m2, (n+1)/2, n-1, 0, (n-1)/2);
        int[][] G = subMatrix(m2, 0, (n-1)/2, (n+1)/2, n-1);
        int[][] H = subMatrix(m2, (n+1)/2, n-1, (n+1)/2, n-1);

        int[][] P1 = strassenMult(A, matrixAdd(F, matrixScale(H, -1)));
        int[][] P2 = strassenMult(matrixAdd(A, B), H);
        int[][] P3 = strassenMult(matrixAdd(C, D), E);
        int[][] P4 = strassenMult(D, matrixAdd(G, matrixScale(E, -1)));
        int[][] P5 = strassenMult(matrixAdd(A, D), matrixAdd(E, H));
        int[][] P6 = strassenMult(matrixAdd(B, matrixScale(D, -1)), matrixAdd(G, H));
        int[][] P7 = strassenMult(matrixAdd(A, matrixScale(C, -1)), matrixAdd(E, F));

        int[][] topleft = matrixAdd(P5, matrixAdd(P4, matrixAdd(matrixScale(P2, -1), P6)));
        int[][] topright = matrixAdd(P1, P2);
        int[][] botleft = matrixAdd(P3, P4);
        int[][] botright = matrixAdd(P5, matrixAdd(P1, matrixScale(matrixAdd(P3, P7), -1)));

        int[][] finalMatrix = combineMatrices(topleft, topright, botleft, botright);

        if (n != orign) {
            int[][] unpad = new int[orign][orign];
            for (int i = 0; i < orign; i++) {
                int[] prow = Arrays.copyOf(finalMatrix[i], orign);
                unpad[i] = prow;
            }
            return unpad;
        }
        return finalMatrix;
    }

    public static int[][] generateMatrix(int dim) {
        Random rnd = new Random();
        int[][] m = new int[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                m[i][j] = rnd.nextInt(2);
            }
        }
        return m;
    }

    public static int[][] pad(int dim, int[][] m) {
        if (dim % 2 == 1) {
            int [][] newMatrix = new int [dim + 1][dim + 1];
            int r = 0;
            for (int[] row : m) {
                int[] prow = Arrays.copyOf(row, dim + 1);
                newMatrix[r] = prow;
                r++;

            }
            int[] line = new int[dim + 1];
            Arrays.fill(line, 0);
            newMatrix[dim] = line;
            return newMatrix;
        }
        return m;
    }

}
