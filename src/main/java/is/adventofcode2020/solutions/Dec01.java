package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dec01 {
    public static Integer solve() {
        List<Integer> input = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec01.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                input.add(Integer.parseInt(data));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();
        for (Integer number : input) {
            if (number%2 == 0) evens.add(number);
            else odds.add(number);
        }

        // Lausn á þraut 1. Þurfti ekki að fara gegnum odds listann, því svarið var í evens listanum.
        /*for (int i = 0; i < evens.size(); i++) {
            for (int j = 1; j < evens.size(); j++) {
                if (evens.get(i) + evens.get(j) == 2020) {
                    return evens.get(i) * evens.get(j);
                }
            }
        }*/

        for (int i = 0; i < input.size(); i++) {
            for (int j = 1; j < input.size(); j++) {
                for (int k = 2; k < input.size(); k++) {
                    if (input.get(i) + input.get(j) + input.get(k) == 2020) {
                        return input.get(i) * input.get(j) * input.get(k);
                    }
                }
            }
        }

        return 0;
    }
}
