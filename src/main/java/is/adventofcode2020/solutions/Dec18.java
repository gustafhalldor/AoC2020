package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Dec18 {
    public static long solve() {
        List<String> inputLines = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec18.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                inputLines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long total = 0;
        for (String line : inputLines) {
            char[] input = line.replace(" ", "").toCharArray();
            String sum = findBraces(input);
            total += Long.parseLong(sum);
        }

        return total;
    }

    // fara gegnum allt array-ið og finna opinn sviga
    // - ef ég kemst í gegn án þess að finna sviga, þá bara reikna eðlilega
    // - ef ég finn sviga, þá halda áfram gegnum arrayið og telja svigana, þegar þeir ná jafnvægi, þá búa til subarray og gera þetta aftur
    private static String findBraces(char[] input) {
        StringBuilder string = new StringBuilder();
        int braces = 0;
        int firstIndex = 0;
        boolean firstIndexFound = false;
        int secondIndex;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                braces++;
                if (firstIndexFound == false) {
                    firstIndexFound = true;
                    firstIndex = i;
                }
                continue;
            }

            if (input[i] == ')') {
                braces--;
                if (braces == 0) {
                    secondIndex = i;
                    firstIndexFound = false;
                    string.append(findBraces(Arrays.copyOfRange(input, firstIndex+1, secondIndex)));
                }
                continue;
            }

            if (!firstIndexFound && input[i] != '(' && input[i] != ')') string.append(input[i]);
        }

        String number = "";
        long total = 0;
        for (int i = 0; i < string.toString().length(); i++) {
            if (string.toString().charAt(i) == '*') {
                for (int j = i+1; j < string.toString().length(); j++) {
                    if (string.toString().charAt(j) != '*' && string.toString().charAt(j) != '+') {
                        number += string.toString().charAt(j);
                        i = j;
                    } else break;
                }
                total *= Long.parseLong(number);
                number = "";
                continue;
            }

            if (string.toString().charAt(i) == '+') {
                for (int j = i+1; j < string.toString().length(); j++) {
                    if (string.toString().charAt(j) != '*' && string.toString().charAt(j) != '+') {
                        number += string.toString().charAt(j);
                        i = j;
                    } else break;
                }
                total += Long.parseLong(number);
                number = "";
                continue;
            }

            for (int j = i; j < string.toString().length(); j++) {
                if (string.toString().charAt(j) != '*' && string.toString().charAt(j) != '+') {
                    number += Character.getNumericValue(string.toString().charAt(j));
                    i = j;
                } else break;
            }
            total += Long.parseLong(number);
            number = "";
        }

        return String.valueOf(total);
    }
}
