package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dec07 {
    public static int solve() {
        List<String> inputLines = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec07.txt");
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

        HashMap<String, HashMap<String, String>> map = new HashMap<>();
        for (String input  : inputLines) {
            String[] array = input.split(" bags contain ");
            HashMap<String, String> subMap = new HashMap<>();

            // contains more than 1 color of bags
            if (array[1].contains(",")) {
                String[] multiBagArray = array[1].split(", ");
                for(String bag : multiBagArray) {
                    populateSubMap(bag, subMap);
                }
                map.put(array[0], subMap);
                continue;
            }

            // contains no more bags
            if (array[1].contains("no other")) {
                map.put(array[0], subMap);
                continue;
            }

            // contains only 1 color of bags
            populateSubMap(array[1], subMap);
            map.put(array[0], subMap);
        }

        int result = 0;
        // SOLUTION TO PART I BEGINS
        // recursion á hvern key í map-inu og submappinu
        /*for (Map.Entry<String, HashMap<String, String>> entry : map.entrySet()) {
            result += calcColoredBags(map, entry.getKey());
        }*/
        // SOLUTION TO PART I ENDS

        result = calcNumberOfBags(map, "shiny gold");

        return result;
    }

    static int calcNumberOfBags(HashMap<String, HashMap<String, String>> map, String key) {
        HashMap<String, String> values = map.get(key);
        if (values.isEmpty()) return 0;

        int count = 0;
        for (Map.Entry<String, String> entry : values.entrySet()) {
            count += Integer.parseInt(entry.getValue());
            count += Integer.parseInt(entry.getValue()) * calcNumberOfBags(map, entry.getKey());
        }

        return count;
    }

    static int calcColoredBags(HashMap<String, HashMap<String, String>> map, String key) {
        HashMap<String, String> values = map.get(key);

        if (values.containsKey("shiny gold")) return 1;
        for (Map.Entry<String, String> entry : values.entrySet()) {
            if (calcColoredBags(map, entry.getKey()) == 1) return 1;
        }
        return 0;
    }

    static void populateSubMap(String bag, HashMap<String, String> map) {
        String[] oneBagArray = bag.split(" ");
        for (int i = 0; i < oneBagArray.length; i++) {
            map.put(oneBagArray[1] + " " + oneBagArray[2], oneBagArray[0]);
        }
    }
}
