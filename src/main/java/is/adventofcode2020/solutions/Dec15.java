package is.adventofcode2020.solutions;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dec15 {
    public static int solve() {
        String inputString = "";
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec15test.txt");
            Scanner scanner = new Scanner(file);
            inputString = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String[] initialNumbers = inputString.split(",");

        MultiValuedMap<Integer, Integer> numberSpokenMap = new ArrayListValuedHashMap<>();
        Integer numberSpoken = 0;
        for (int i = 0; i < initialNumbers.length; i++) {
                numberSpokenMap.put(Integer.parseInt(initialNumbers[i]), i+1);
                numberSpoken = Integer.parseInt(initialNumbers[i]);
        }

        for (int i = initialNumbers.length+1; i < 2020 + 1; i++) {
            if (numberSpokenMap.containsKey(numberSpoken)) {
                ArrayList<Integer> newList = new ArrayList<>(numberSpokenMap.get(numberSpoken));
                if (newList.size() > 2) {
                    // sigta út lægsta value og eyða
                    int min = Integer.MAX_VALUE;
                    int index = 0;
                    for (int j = 0; j < newList.size(); j++) {
                        if (newList.get(j) < min) {
                            min = newList.get(j);
                            index = j;
                        }
                    }
                    newList.remove(index);
                    numberSpokenMap.remove(numberSpoken);
                    numberSpokenMap.putAll(numberSpoken, newList);
                    numberSpoken = Math.abs(newList.get(0) - newList.get(1));
                    numberSpokenMap.put(numberSpoken, i);
                    continue;
                }

                if (newList.size() == 2) {
                    numberSpoken = Math.abs(newList.get(0) - newList.get(1));
                    numberSpokenMap.put(numberSpoken, i); continue;
                }

                numberSpoken = 0;
                numberSpokenMap.put(numberSpoken, i);
            }
        }

        // PART II: Skila tölu númer 30.000.000 ...
        // Á tæpum 40sek reiknaði tölvan það út, en það á greinilega að finna algorithma sem er ekki eins lengi að keyra


        return numberSpoken;
    }
}
