import java.util.ArrayList;
import java.util.List;

public class GreedyAlgorithm {
    int[][] scores; // initial matrix (cost matrix)
    Integer matchScore = null;
    private boolean[] lpaired,rpaired;
    private int[] assignment;

    public GreedyAlgorithm(int[][] matrix) {
        if (matrix.length != matrix[0].length) {
            try {
                throw new IllegalAccessException("The matrix is not square!");
            } catch (IllegalAccessException ex) {
                System.err.println(ex);
                System.exit(1);
            }
        }
        this.scores = matrix;
        assignment = new int[matrix.length];
        lpaired = new boolean[matrix.length];
        rpaired = new boolean[matrix.length];
    }
    private static final int lowMatchScore = 0; // Wonder if we ever want negative scores?

    int[] getMatching() {
        for (int k = 0; k < scores.length; k++) {
            int best = 9999999;
            int besti = 0;
            int bestj = 0;
            for ( int i = 0; i < lpaired.length; i++ ) {
                if ( !lpaired[i] ) {
                    for ( int j = 0; j < rpaired.length; j++ ) {
                        if ( !rpaired[j] ) {
                            if ( scores[i][j] <= best ) {
                                besti = i;
                                bestj = j;
                                best = scores[i][j];
                            }
                        }
                    }
                }
            }
            lpaired[besti] = true;
            rpaired[bestj] = true;
            assignment[besti] = bestj;
        }
        return assignment;
    }

}
