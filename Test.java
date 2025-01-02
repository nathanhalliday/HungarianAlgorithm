import java.util.Arrays;

/**
 * Test Class for HungarianAlgorithm.java
 *
 * @author https://github.com/aalmi | march 2014
 * @version 1.0
 */
public class Test {

    static int[][] deepCopy(int[][] matrix) {
      if (matrix == null) {
        return null;
      }

      final int[][] result = new int[matrix.length][];
      for (int i = 0; i < matrix.length; i++) {
        result[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        // For Java versions prior to Java 6 use the next:
        // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
      }
      return result;
    }

    public static void main(String[] args) {
      for (int k = 1; k < 10; k++) {
        int[][] dataMatrix = new int[k][k];
        for (int i = 0; i < k; i++) {
          for (int j = 0; j < k; j++) {
            dataMatrix[i][j] = ((int) (Math.random() * 10)) * 10;
          }
          System.out.println(Arrays.toString(dataMatrix[i]));
        }


        // the problem is written in the form of a matrix
//      int[][] dataMatrix = {
//      //col0  col1  col2  col3
//        {70,  40,   20,   55},  //row0
//        {65,  60,   45,   90},  //row1
//        {30,  45,   50,   75},  //row2
//        {25,  30,   55,   40}   //row3
//      };

        //find optimal assignment
        HungarianAlgorithm ha = new HungarianAlgorithm(deepCopy(dataMatrix));
        GreedyAlgorithm ga = new GreedyAlgorithm(deepCopy(dataMatrix));

        long startTime = System.nanoTime();
        int[][] assignment = ha.findOptimalAssignment();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Hungarian: " + duration);

        startTime = System.nanoTime();
        int[] assignment2 = ga.getMatching();
        endTime = System.nanoTime();
        duration = (endTime - startTime);

        System.out.println("Greedy: " + duration);

        int totalHung = 0;
        if (assignment.length > 0) {
          // print assignment
          for (int i = 0; i < assignment.length; i++) {
            totalHung += dataMatrix[assignment[i][0]][assignment[i][1]];
            System.out.print("Col" + assignment[i][0] + " => Row" + assignment[i][1] + " (" + dataMatrix[assignment[i][0]][assignment[i][1]] + ")");
            System.out.println();
          }
        }
//        } else {
//          System.out.println("no assignment found!");
//        }

        //find optimal assignment
//        int[] assignment2 = ga.getMatching();

        int totalGreed = 0;
        if (assignment2.length > 0) {
          // print assignment
          for (int i = 0; i < assignment2.length; i++) {
            totalGreed += dataMatrix[i][assignment2[i]];
            System.out.print("Col" + i + " => Row" + assignment2[i] + " (" + dataMatrix[i][assignment2[i]] + ")");
            System.out.println();
          }
        }
//        } else {
//          System.out.println("no assignment found!");
//        }
        System.out.println("Hung got a better score by: " + (totalGreed - totalHung));
      }
    }
}
