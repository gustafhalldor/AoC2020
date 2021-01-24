package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dec05alt {
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
        // character array to use for part 2
        char[] seatIds = new char[971];

        for (String seatLongForm : inputLines) {
            String rowString = seatLongForm.substring(0, 7);
            String colString = seatLongForm.substring(7, 10);

            String rowBinary = rowString.replace('F', '0').replace('B', '1');
            String colBinary = colString.replace('L', '0').replace('R', '1');

            int row = Integer.parseInt(rowBinary, 2);
            int col = Integer.parseInt(colBinary, 2);
            int seatId = row * 8 + col;
            if (seatId > result) result = seatId;

            //for part 2
            seatIds[seatId] = 'X';
        }

        // PART 2 BEGINS
        char bitch = seatIds[0]; // veit ekki hvernig ég á annars að bera saman við tómt char stak...

        for (int i = 1; i < seatIds.length; i++) {
            if (seatIds[i] == bitch && seatIds[i-1] == 'X' && seatIds[i+1] == 'X') return i;
        }
        // Part 2 ENDS

        return result;
    }
}
