package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dec05 {
    public static int solve() {
        List<String> inputLines = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec05.txt");
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

        int result = 0;
        // for part 2
        char[] seatIds = new char[971];

        for (String seatLongForm : inputLines) {
            String rowBinary = seatLongForm.substring(0, 7);
            String colBinary = seatLongForm.substring(7, 10);

            int row = getSeatRow(rowBinary);
            int col = getSeatColumn(colBinary);
            int seatId = row * 8 + col;
            if (seatId > result) result = seatId;

            //for part 2
            seatIds[seatId] = 'X';
        }

        return result;
    }

    public static int getSeatRow(String data) {
        char[] chars = data.toCharArray();
        int nrOfRows = (int) Math.pow(2, chars.length);
        int lowerBound = 0;
        int upperBound = nrOfRows -1;

        for (char c : chars) {
            // get middle
            int middle = (upperBound - lowerBound) / 2 + lowerBound;

            if (c == 'F') {
                upperBound = middle;
            } else {
                lowerBound = middle+1;
            }
        }

        return upperBound;
    }

    public static int getSeatColumn(String data) {
        char[] chars = data.toCharArray();
        int nrOfColumns = (int) Math.pow(2, chars.length);
        int lowerBound = 0;
        int upperBound = nrOfColumns -1;

        for (char c : chars) {
            // get middle
            int middle = (upperBound - lowerBound) / 2 + lowerBound;

            if (c == 'L') {
                upperBound = middle;
            } else {
                lowerBound = middle+1;
            }
        }

        return lowerBound;
    }
}
