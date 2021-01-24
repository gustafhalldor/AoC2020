package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dec19 {
    public static int solve() {
        List<String> inputLines = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec19_input.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                inputLines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> rulesLines = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec19_rules.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                rulesLines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Map<String, String> rulesMap = new HashMap<>();
        for (String rule : rulesLines) {
            String[] array = rule.split(": ");
            rulesMap.put(array[0], array[1]);
        }

        String regex = createRegex(rulesMap, rulesMap.get("0"));
        System.out.println(regex);
        int total = 0;
        for (String line : inputLines) {
            if (line.matches(regex)) total++;
        }

        return total;
    }

    private static String createRegex(Map<String, String> map, String rule) {
        if (rule.contains("\"")) {
            return rule.substring(1, 2);
        }

        StringBuilder builder = new StringBuilder();

        if (rule.contains("|")) {
            String[] array = rule.split(" \\| ");
            builder.append("(");
            for (int i = 0; i < array.length; i++) {
                String[] subRules = array[i].split(" ");
                for (int j = 0; j < subRules.length; j++) {
                    builder.append(createRegex(map, map.get(subRules[j])));
                }
                if (i != array.length -1 ) builder.append("|");
            }
            builder.append(")");
        } else {
            String[] array = rule.split(" ");
            for (int i = 0; i < array.length; i++) {
                builder.append(createRegex(map, map.get(array[i])));
            }
        }

        return builder.toString();
    }
}
