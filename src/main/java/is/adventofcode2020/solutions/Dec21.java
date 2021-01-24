package is.adventofcode2020.solutions;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.util.Arrays.asList;

public class Dec21 {
    public static int solve() {
        List<String> inputLines = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec21test.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                inputLines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<String, Integer> wordCountMap = new HashMap<>();
        MultiValuedMap<String, String> possibleAllergensMap = new ArrayListValuedHashMap<>();
        for (String line : inputLines) {
            String[] splitInTwo = line.split(" \\(contains ");
            String[] ingredients = splitInTwo[0].split(" ");
            for (String ingredient : ingredients) {
                if (wordCountMap.containsKey(ingredient)) {
                    int count = wordCountMap.get(ingredient);
                    wordCountMap.put(ingredient, ++count);
                    continue;
                }

                wordCountMap.put(ingredient, 1);
            }

            List<String> allergens = new ArrayList<>();
            String trimmed = splitInTwo[1].replace(")", "");
            possibleAllergensMap.putAll(trimmed, Arrays.asList(ingredients));
            /*if (trimmed.contains(",")) {
                String[] array = trimmed.split(", ");
                Collections.addAll(allergens, array);
            } else allergens.add(trimmed);*/

            // ítra yfir allergens
            /*for (String allergen : allergens) {
                for (String ingredient : ingredients) {
                    boolean foundIngredient = false;
                    if (possibleAllergensMap.containsKey(allergen)) {
                        List<String> list = (List<String>) possibleAllergensMap.get(allergen);
                        for (String item : list) {
                            if (item.equals(ingredient)) {
                                foundIngredient = true;
                                break;
                            }
                        }
                    }
                    if (foundIngredient) continue;
                    possibleAllergensMap.put(allergen, ingredient);
                }
            }*/
        }

        // Put together a "might contain" list
        List<String> mightContainAllergens = new ArrayList<>();
        for (String line : inputLines) {
            String[] splitInTwo = line.split(" \\(contains ");
            String trimmed = splitInTwo[1].replace(")", "");
            if (!trimmed.contains(",")) {
                // See if the ingredient is a part of a multi allergen key
                boolean multiAllergen = false;
                for (Map.Entry<String, String> entry : possibleAllergensMap.entries()) {
                    if (entry.getKey().contains(",") && entry.getKey().contains(trimmed)) multiAllergen = true;
                }
                if (!multiAllergen) mightContainAllergens.addAll(possibleAllergensMap.get(trimmed));
            }
        }

        // Check only lines that contain more than 1 allergen
        for (String line : inputLines) {
            String[] splitInTwo = line.split(" \\(contains ");
            String[] ingredientsToCheck = splitInTwo[0].split(" ");

            List<String> ingredientListToCheck = Arrays.asList(ingredientsToCheck);
            String trimmed = splitInTwo[1].replace(")", "");
            if (trimmed.contains(",")) {
                String[] array = trimmed.split(", ");
                List<String> allIngredients = new ArrayList<>();
                for (String allergen : array) {
                    List<String> ingredients = (List<String>) possibleAllergensMap.get(allergen);
                    for (int i = 0; i < ingredients.size(); i++) {
                        if (!allIngredients.contains(ingredients.get(i))) allIngredients.add(ingredients.get(i));
                    }
                }

                List<String> mightNotContainAllergens = new ArrayList<>();
                for (String ingredient : allIngredients) {
                    if (!ingredientListToCheck.contains(ingredient)) mightNotContainAllergens.add(ingredient);
                }

                // gera það sama í hina áttina
                for (String ingredient : ingredientListToCheck) {
                    if (!allIngredients.contains(ingredient)) mightNotContainAllergens.add(ingredient);
                }

                List<String> notAllergens = new ArrayList<>();
                for (String ing : mightNotContainAllergens) {
                    if (mightContainAllergens.contains(ing)) continue;
                    notAllergens.add(ing);
                }

                int total = 0;
                for (String notAllergen : notAllergens) {
                    total += wordCountMap.get(notAllergen);
                }
                return total;
            }
        }

        return 0;
    }
}
