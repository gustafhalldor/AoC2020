package is.adventofcode2020.solutions;

import lombok.Data;

public class Dec20 {
    public static long solve() {

        return 0;
    }

    @Data
    static class Tile {
        int id;
        String top;
        String bottom;
        String left;
        String right;
        String[][] array;

        void rotateRight90() {
            String topCopy = this.top;
            String bottomCopy = this.bottom;
            String leftCopy = this.left;
            String rightCopy = this.right;

            this.top = rightCopy;
            this.right = bottomCopy;
            this.bottom = leftCopy;
            this.left = topCopy;
        }

        void flipOverXAxis() {

        }

        void flipOverYAxis() {


        }
    }
}
