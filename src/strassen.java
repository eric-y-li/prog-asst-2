import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class strassen {

    public static void tests() {
        int[][] m = {{1,1,1,1},{2,0,2,4},{0,1,0,3},{1,0,0,1}};
        int[][] randm = MatrixOps.generateMatrix(256);

        //int[][] prod = matrixMult(m, m);
        //int[][] prod2 = strassenMult(m, m);
        long startTime = System.nanoTime();
        int[][] randprod = MatrixOps.matrixMult(randm, randm);
        long endTime = System.nanoTime();
        System.out.println("Regular: " + (double)(endTime-startTime)/1000000000);

        long start2 = System.nanoTime();
        int[][] randprod2 = MatrixOps.strassenMult(randm, randm);
        long end2 = System.nanoTime();
        System.out.println("Strassen: " + (double)(end2-start2)/1000000000);

        // System.out.println(Arrays.deepEquals(randprod, randprod2));

        //System.out.println(Arrays.deepToString(prod));
        //System.out.println(Arrays.deepToString(prod2));

        //System.out.println(Arrays.deepToString(randm));
        //System.out.println(Arrays.deepToString(randprod));
        //System.out.println(Arrays.deepToString(randprod2));

        //int[][] r = {{1,1,1},{2,0,2},{0,1,0}};
        //System.out.println(Arrays.deepToString(pad(3, r)));

        /*int[][] k = {{1,1,1,1,1},{2,0,2,4,1},{0,1,0,3,1},{1,0,0,1,1}, {2,3,1,4,3}};
        int[][] prod3 = strassenMult(k, k);
        System.out.println(Arrays.deepToString(prod3));*/
    }

    public static void graphTest() {
        double p = 0.01;

        RandomGraph g = new RandomGraph(1024, p);
        int[][] A = g.getAdjacency();
        int[][] A3 = MatrixOps.strassenMult(A, MatrixOps.strassenMult(A, A));
        int sumDiag = 0;
        for (int i = 0; i < A3.length; i++) {
            sumDiag += A3[i][i];
        }
        System.out.println("Number of triangles: "+(sumDiag/6));

        System.out.println("Expected number of triangles: "+((binom(1024,3).longValue())*Math.pow(p, 3)));
    }

    public static BigInteger binom(final int N, final int K) {
        ArrayList<BigInteger> arr = new ArrayList<BigInteger>();
        arr.add(BigInteger.ONE);
        for (int k = 1; k <= K; k++) {
            arr.add(arr.get(k-1).multiply(BigInteger.valueOf(N-k+1)).divide(BigInteger.valueOf(k)));
        }
        return arr.get(K);
    }

    public static void main(String[] args) throws FileNotFoundException {
        int dim = Integer.parseInt(args[1]);
        String filename = args[2];

        File file = new File("./"+filename);
        Scanner fileScanner = new Scanner(file);

        int[][] m = new int[dim][dim];
        int[][] m2 = new int[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                m[i][j] = fileScanner.nextInt();
            }
        }

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                m2[i][j] = fileScanner.nextInt();
            }
        }

        int[][] prod = MatrixOps.strassenMult(m, m2);

        for (int i = 0; i < dim; i++) {
            System.out.println(prod[i][i]);
        }

        // graphTest();

    }

}
