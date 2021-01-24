package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dec14 {
    public static long solve() {
        List<String> inputLines = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec14.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                inputLines.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String mask = "";
        Map<Integer, Character> maskMap = new HashMap<>();
        Map<Integer, String> resultMap = new HashMap<>();
        for (String line : inputLines) {
            // Set mask
            String[] array = line.split(" ");
            if (line.contains("ma")) {
                mask = array[2];
                char[] maskChars = mask.toCharArray();
                for (int i = 0; i < maskChars.length; i++) {
                    if (maskChars[i] != 'X') {
                        maskMap.put(i, maskChars[i]);
                    }
                }
            } else {
                // Apply mask
                int memIndex = Integer.parseInt(array[0].replaceAll("[^0-9,]",""));
                String number = Integer.toBinaryString(Integer.parseInt(array[2]));
                number = padWithLeadingZeros(number);
                String ret = "";
                for (int i = 0; i < mask.length(); i++) {
                    if (mask.charAt(i) == 'X') {
                        ret += number.charAt(i);
                    } else {
                        ret += mask.charAt(i);
                    }
                }

                resultMap.put(memIndex, ret);
            }
        }

        long total = 0;
        for (Map.Entry<Integer, String> result : resultMap.entrySet()) {
            total += Long.parseLong(result.getValue(), 2);
        }

        return total;
    }

    private static String padWithLeadingZeros(String binaryNr) {
        return  "0".repeat(Math.max(0, 36 - binaryNr.length())) + binaryNr;
    }
}

    /*String mask = "";
    Map<Integer, Character> maskMap = new HashMap<>();
    Map<Integer, Long> resultMap = new HashMap<>();
        for (String line : inputLines) {
                // Set mask
                String[] array = line.split(" ");
                if (line.contains("ma")) {
                mask = array[2];
                char[] maskChars = mask.toCharArray();
                for (int i = 0; i < maskChars.length; i++) {
        if (maskChars[i] != 'X') {
        maskMap.put(i, maskChars[i]);
        }
        }
        } else {
        // Apply mask
        int memIndex = Integer.parseInt(array[0].replaceAll("[^0-9,]",""));
        long nrToMask = Integer.parseInt(array[2]);
        String binaryNr = Long.toBinaryString(nrToMask);

        StringBuilder builder = new StringBuilder();
        builder.append(padWithLeadingZeros(binaryNr));
        String yo = "";
        for (Map.Entry<Integer, Character> entry : maskMap.entrySet()) {
        int i = entry.getKey();
        yo = builder.replace(i, i+1, entry.getValue().toString()).toString();
        }

        resultMap.put(memIndex, Long.parseLong(yo, 2));
        }
        }*/