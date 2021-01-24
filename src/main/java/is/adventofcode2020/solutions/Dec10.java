package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Dec10 {
    public static long solve() {
        List<Integer> inputLines = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec10.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputLines.add(Integer.parseInt(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        List<Integer> sortedInput = inputLines.stream().sorted().collect(Collectors.toList());
        sortedInput.add(0, 0);
        sortedInput.add(sortedInput.size(), sortedInput.get(sortedInput.size()-1) + 3);

        // SOLUTION TO PART I BEGINS
        /*int oneJoltDiff = 0;
        int twoJoltDiff = 0;
        int threeJoltDiff = 0;

        int i = 0;
        while (i < sortedInput.size() - 1) {
            int number = sortedInput.get(i+1) - sortedInput.get(i);
            if (number == 1) {
                oneJoltDiff++;
                i++;
            }
            if (number == 2) {
                twoJoltDiff++;
                i++;
            }
            if (number == 3) {
                threeJoltDiff++;
                i++;
            }
        }

        return  oneJoltDiff * threeJoltDiff;*/
        // SOLUTION TO PART I ENDS

        // if difference between numbers is 3 we can't make any other arrangements
        // Það eru engir með 2ja jolta mun í mínu input-i, svo ég einbeit mér bara að 1 jolti og röðum af þeim
        // Ef það eru 4 í röð, þá eru umraðanir 7
        // Ef það eru 3 í röð, þá eru umraðanir 4
        // Ef það eru 2 í röð, þá eru umraðanir 2

        int nrOfPreviousOnes = 0;
        long totalDifferentArrangements = 1;
        for (int i = 0; i < sortedInput.size() -1; i++) {
            int number = sortedInput.get(i+1) - sortedInput.get(i);
            if (number == 3) {
                System.out.println("Number of ones in a row: " + nrOfPreviousOnes);
                if (nrOfPreviousOnes == 2) totalDifferentArrangements *= 2;
                if (nrOfPreviousOnes == 3) totalDifferentArrangements *= 4;
                if (nrOfPreviousOnes == 4) totalDifferentArrangements *= 7;
                nrOfPreviousOnes = 0;
                continue;
            }
            if (number == 1) {
                nrOfPreviousOnes++;
            }

        }

        return totalDifferentArrangements;
    }
}
