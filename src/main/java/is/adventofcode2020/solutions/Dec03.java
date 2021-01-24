package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dec03 {
    public static Long solve() {
        List<String> inputLines = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec03.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputLines.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Get length of each line and total lines
        int xLength = inputLines.get(0).length();
        int yLength = inputLines.size();

        // create the 2d character array
        char[][] inputArray = new char[xLength][yLength];
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                char[] charArray = inputLines.get(i).toCharArray();
                inputArray[j][i] = charArray[j];
            }
        }

        int result = 0;

        // SOLUTION TO PART 1 BEGINS
        // Go through array and wrap around when it reaches end of x axis
        for (int y = 1, x = 0; y < yLength; y++) {
            x += 3;
            // Wrap around if needed
            if (x >= xLength) {
                x = x - xLength;
            }

            if (inputArray[x][y] == '#') {
                result++;
            }
        }
        //return result;
        // SOLUTION TO PART 1 ENDS

        Long result1 = calcTrees(inputArray, xLength, yLength, 1, 1);
        Long result2 = calcTrees(inputArray, xLength, yLength, 3, 1);
        Long result3 = calcTrees(inputArray, xLength, yLength, 5, 1);
        Long result4 = calcTrees(inputArray, xLength, yLength, 7, 1);
        Long result5 = calcTrees(inputArray, xLength, yLength, 1, 2);

        return result1 * result2 * result3 * result4 * result5;
    }

    static Long calcTrees(char[][] inputArray, int xLength, int yLength, int xInc, int yInc) {
        Long result = 0L;
        for (int y = yInc, x = xInc; y < yLength; y += yInc, x += xInc) {
            // Wrap around if needed
            if (x >= xLength) {
                x = x % xLength;
            }

            if (inputArray[x][y] == '#') {
                result++;
            }
        }
        return result;
    }
}
