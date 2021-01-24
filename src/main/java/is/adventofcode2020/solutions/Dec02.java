package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dec02 {
    public static Integer solve() {
        List<String> inputLines = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec02.txt");
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

        // SOLUTION TO PART 1 BEGINS
        /*for (String inputLine : inputLines) {
            String[] array = inputLine.split(" ");
            // get lower and upper bounds
            String[] lowerUpper = array[0].split("-");
            int lower =  Integer.parseInt(lowerUpper[0]);
            int upper =  Integer.parseInt(lowerUpper[1]);

            // get character to check
            char character = array[1].charAt(0);

            // get string to check for character occurrences
            String password = array[2];

            int occurrences = 0;
            for (char c : password.toCharArray()) {
                if(c == character) {
                    occurrences++;
                }
            }

            if (occurrences >= lower && occurrences <= upper) {
                result++;
            }
        }*/
        // SOLUTION TO PART 1 ENDS

        // SOLUTION TO PART 2 BEGINS
        for (String inputLine : inputLines) {
            String[] array = inputLine.split(" ");

            // get both indices where the character must occur. Have to deduct 1 so it starts with a zero index
            String[] both = array[0].split("-");
            int firstOcc = Integer.parseInt(both[0]) - 1;
            int secondOcc = Integer.parseInt(both[1]) - 1;

            // get character to check
            char character = array[1].charAt(0);

            // get string to check for character occurrences
            String password = array[2];

            int occurrences = 0;

            char[] charArray = password.toCharArray();

            if (charArray[firstOcc] == character) occurrences++;
            if (charArray[secondOcc] == character) occurrences++;

            if (occurrences == 1) {
                result++;
            }
        }
        // SOLUTION TO PART 2 ENDS

        return result;
    }
}
