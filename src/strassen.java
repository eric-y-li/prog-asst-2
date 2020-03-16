import java.util.Arrays;

public class strassen {

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
            res[j] = Arrays.copyOfRange(m[j], xStart, xEnd+1);
        }
        return res;
    }

    public static int[][] strassenMult (int[][] m1, int[][] m2) {
        int n = m1.length;
        int[][] A = subMatrix(m1, 0, (n-1)/2, 0, (n-1)/2);
        int[][] B = subMatrix(m1, 0, (n-1)/2, (n+1)/2, n-1);
        int[][] C = subMatrix(m1, (n+1)/2, n-1, 0, (n-1)/2);
        int[][] D = subMatrix(m1, (n+1)/2, n-1, (n+1)/2, n-1);
        int[][] E = subMatrix(m2, 0, (n-1)/2, 0, (n-1)/2);
        int[][] F = subMatrix(m2, 0, (n-1)/2, (n+1)/2, n-1);
        int[][] G = subMatrix(m2, (n+1)/2, n-1, 0, (n-1)/2);
        int[][] H = subMatrix(m2, (n+1)/2, n-1, (n+1)/2, n-1);

        return A;
    }

    public static void main(String[] args) {
        int[][] m = {{1,1,1,1},{2,0,2,4},{0,1,0,3},{1,0,0,1}};
        int[][] prod = matrixMult(m, m);
        System.out.println(Arrays.deepToString(m));
        System.out.println(Arrays.deepToString(prod));

        System.out.println(Arrays.deepToString(subMatrix(m, 0, 1, 0, 1)));
    }

}
