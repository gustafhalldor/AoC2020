package is.adventofcode2020.solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dec04 {
    static List<String> EYECOLORS = new ArrayList<>() {
        {
            add("amb");
            add("blu");
            add("brn");
            add("gry");
            add("grn");
            add("hzl");
            add("oth");
        }
    };

    public static int solve() {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec04.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Each passport gets its own index in the passport array
        String[] listOfPassports = data.split("\n\\s*\n");

        // SOLUTION TO PART 1 BEGINS
        int result = 0;
        /*for (String passport : listOfPassports) {
            String[] array = passport.split("\\s+");
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < array.length; i++) {
                String[] keyValue = array[i].split(":");
                map.put(keyValue[0], keyValue[1]);
            }

            if (map.containsKey("byr") && map.containsKey("iyr") && map.containsKey("eyr") && map.containsKey("hgt") &&
                    map.containsKey("hcl") && map.containsKey("ecl") && map.containsKey("pid")) {
                result++;
            }
        }*/
        // SOLUTION TO PART 1 ENDS

        for (String passport : listOfPassports) {
            String[] array = passport.split("\\s+");
            HashMap<String, String> map = new HashMap<>();
            for (int i = 0; i < array.length; i++) {
                String[] keyValue = array[i].split(":");
                map.put(keyValue[0], keyValue[1]);
            }

            if (map.containsKey("byr") && map.containsKey("iyr") && map.containsKey("eyr") && map.containsKey("hgt") &&
                    map.containsKey("hcl") && map.containsKey("ecl") && map.containsKey("pid")) {
                // Passport has all the necessary fields so now we need to validate each field
                if (!byrValidation(map.get("byr"))) continue;
                if (!iyrValidation(map.get("iyr"))) continue;
                if (!eyrValidation(map.get("eyr"))) continue;
                if (!hgtValidation(map.get("hgt"))) continue;
                if (!hclValidation(map.get("hcl"))) continue;
                if (!eclValidation(map.get("ecl"))) continue;
                if (!pidValidation(map.get("pid"))) continue;

                result++;
            }
        }

        return result;
    }

    public static boolean pidValidation(String data) {
        if (data.length() != 9) return false;

        try {
            Integer.parseInt(data);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static boolean eclValidation(String data) {
        return EYECOLORS.contains(data);
    }

    public static boolean hclValidation(String data) {
        return data.matches("^#[0-9|a-f]{6}$");
    }

    public static boolean hgtValidation(String data) {
        if (data.length() < 4 || data.length() > 5) return false;

        String lastTwo = data.substring(data.length()-2);

        if (!lastTwo.equals("cm") && !lastTwo.equals("in")) return false;

        if (lastTwo.equals("cm")) {
            String cmHeight = data.substring(0, 3);
            try {
                Integer.parseInt(cmHeight);
            } catch (Exception e) {
                return false;
            }
            if (Integer.parseInt(cmHeight) < 150 || Integer.parseInt(cmHeight) > 193) return false;
        }

        if (lastTwo.equals("in")) {
            String inHeight = data.substring(0, 2);
            try {
                Integer.parseInt(inHeight);
            } catch (Exception e) {
                return false;
            }
            if (Integer.parseInt(inHeight) < 59 || Integer.parseInt(inHeight) > 76) return false;
        }

        return true;
    }

    public static boolean eyrValidation(String data) {
        try {
            Integer.parseInt(data);
        } catch (Exception e) {
            return false;
        }

        int year = Integer.parseInt(data);

        return year >= 2020 && year <= 2030;
    }

    public static boolean iyrValidation(String data) {
        try {
            Integer.parseInt(data);
        } catch (Exception e) {
            return false;
        }

        int year = Integer.parseInt(data);

        return year >= 2010 && year <= 2020;
    }

    public static boolean byrValidation(String data) {
        try {
            Integer.parseInt(data);
        } catch (Exception e) {
            return false;
        }

        int year = Integer.parseInt(data);

        return year >= 1920 && year <= 2002;
    }


}
