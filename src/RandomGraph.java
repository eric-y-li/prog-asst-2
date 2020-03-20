import java.util.Random;

public class RandomGraph {

    int[][] adjacency;

    public RandomGraph(int dim, double p) {
        Random rnd = new Random();

        int[][] adj = new int[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = i; j < dim; j++) {
                double r = rnd.nextDouble();
                if (r <= p && i != j) {
                    adj[i][j] = 1;
                    adj[j][i] = 1;
                }
                else {
                    adj[i][j] = 0;
                    adj[j][i] = 0;
                }
            }
        }

        adjacency = adj;

    }

    public int[][] getAdjacency() {
        return this.adjacency;
    }

}
