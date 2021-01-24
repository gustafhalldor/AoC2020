package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dec09 {
    static int PREAMBLE = 5;

    public static long solve() {
        List<Long> inputLines = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec09.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputLines.add(Long.parseLong(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // SOLUTION TO PART I BEGINS
        // Initialize the numbers to check
        /*List<Long> numbersToCheck = new ArrayList<>();
        for (int i = 0; i < PREAMBLE; i++) {
            for (int j = i+1; j < PREAMBLE; j++) {
                numbersToCheck.add(inputLines.get(i) + inputLines.get(j));
            }
        }

        for (int i = PREAMBLE; i < inputLines.size(); i++) {
            boolean nrFound = false;
            long nrToCheck = inputLines.get(i);

            for (long number : numbersToCheck) {
                if (nrToCheck == number) {
                    nrFound = true;
                    break;
                }
            }

            if (!nrFound) return nrToCheck;

            numbersToCheck.clear();
            for (int j = i-PREAMBLE+1; j < i+1; j++) {
                for (int k = j+1; k < PREAMBLE+j; k++) {
                    numbersToCheck.add(inputLines.get(j) + inputLines.get(k));
                }
            }
        }*/
        // SOLUTION TO PART I ENDS
        // Svarið í part I var 1398413738 og það er svo notað í part II

        long exactNr = 1398413738;
        List<Long> collectionOfSummedNumbers = new ArrayList<>();
        boolean breakOut = false;
        for (int i = 0; i < inputLines.size(); i++) {
            if (inputLines.get(i) == exactNr) continue; // væri hægt að losa sig við þetta tjekk með því hreinlega að fjarlægja töluna úr listanum...

            long acc = 0;
            for (int j = i; j < inputLines.size(); j++) {
                collectionOfSummedNumbers.add(inputLines.get(j));
                acc += inputLines.get(j);
                if (acc > exactNr) break;
                if (acc == exactNr) {
                    breakOut = true;
                    break;
                }
            }
            if (breakOut) break;
            collectionOfSummedNumbers.clear();
        }

        long min = Long.MAX_VALUE;
        long max = 0;

        for (long number : collectionOfSummedNumbers) {
            if (number > max) max = number;
            if (number < min) min = number;
        }

        return min + max;
    }
}
