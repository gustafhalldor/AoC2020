package is.adventofcode2020.solutions;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dec16 {
    public static long solve() {
        String myTicketLine = "";
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec16_ticket.txt");
            Scanner scanner = new Scanner(file);
            myTicketLine = scanner.nextLine();
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> otherTicketLines = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec16_other_tickets.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                otherTicketLines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<String> conditionLines = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec16_conditions.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                conditionLines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // SOLUTION TO PART I BEGINS
        // Create sufficiently big enough array which can be filled in such a way that when an index reads 1, it means
        // that number is within valid range. If it reads 0, it is not within valid range.
        int[] validNumbers = new int[1000];
        for (String line : conditionLines) {
            String[] lineSplitInHalf = line.split(": ");
            String[] conditionsOnEitherSide = lineSplitInHalf[1].split(" or ");
            String[] firstCondition = conditionsOnEitherSide[0].split("-");
            int firstConditionLow = Integer.parseInt(firstCondition[0]);
            int firstConditionHigh = Integer.parseInt(firstCondition[1]);

            for (int i = firstConditionLow; i <= firstConditionHigh; i++) {
                if (validNumbers[i] != 1) {
                    validNumbers[i] = 1;
                }
            }

            String[] secondCondition = conditionsOnEitherSide[1].split("-");
            int secondConditionLow = Integer.parseInt(secondCondition[0]);
            int SecondConditionHigh = Integer.parseInt(secondCondition[1]);

            for (int i = secondConditionLow; i <= SecondConditionHigh; i++) {
                if (validNumbers[i] != 1) {
                    validNumbers[i] = 1;
                }
            }
        }

        long ticketScanningErrorRate = 0;
        List<String> validTicketLines = new ArrayList<>(); // used for part II
        for (int i = 0; i < otherTicketLines.size(); i++) {
            boolean isInvalid = false;
            String[] values = otherTicketLines.get(i).split(",");
            for (int j = 0; j < values.length; j++) {
                int value = Integer.parseInt(values[j]);
                if (validNumbers[value] != 1) {
                    isInvalid = true;
                    ticketScanningErrorRate += value;
                }
            }
            if (!isInvalid) validTicketLines.add(otherTicketLines.get(i));
        }
        //return ticketScanningErrorRate;
        // SOLUTION TO PART I ENDS

        // create 2D array of values, such that the first value of each ticket gets its own row, same for the second, third etc...
        String[][] conditionValues = new String[conditionLines.size()][validTicketLines.size()];
        for (int i = 0; i < conditionLines.size(); i++) {
            for (int j = 0; j < validTicketLines.size(); j++) {
                String[] array = validTicketLines.get(j).split(",");
                conditionValues[i][j] = array[i];
            }
        }

        Map<String, String> conditionMap = new HashMap<>();
        for (String line : conditionLines) {
            String[] array = line.split(": ");
            conditionMap.put(array[0], array[1]);
        }

        // Það sem ég vil gera:
        // Fyrir hverja röð í conditionValues, rúlla í gegnum hvert entry í conditionMap
        // Ef við komumst í gegnum alla röðina í conditionValues þá héldu skilyrðin og þetta tiltekna condition í conditionMap
        // átti við þessa röð
        // Það er þá óhætt að skrá það niður einhversstaðar og svo eyða þessu entry úr mappinu svo það þurfi ekki að fara í gegnum það aftur

        String[] myTicket = myTicketLine.split(",");
        long returnValue = 1;
        MultiValuedMap<Integer, String> allConditionMatches = new ArrayListValuedHashMap<>();

        for (int i = 0; i < conditionValues.length; i++) {
            for (Map.Entry<String, String> entry : conditionMap.entrySet()) {
                String[] conditions = entry.getValue().split(" or ");
                String[] lowConditions = conditions[0].split("-");
                String[] highConditions = conditions[1].split("-");
                int lowLow = Integer.parseInt(lowConditions[0]);
                int lowHigh = Integer.parseInt(lowConditions[1]);
                int highLow = Integer.parseInt(highConditions[0]);
                int highHigh = Integer.parseInt(highConditions[1]);

                boolean notRightCondition = false;
                for (int j = 0; j < conditionValues[i].length; j++) {
                    int value = Integer.parseInt(conditionValues[i][j]);
                    if ((value >= lowLow && value <= lowHigh) || (value >= highLow && value <= highHigh)) {
                        continue;
                    }
                    notRightCondition = true;
                    break;
                }

                // Þetta er ekki rétt. Fæ út of háa tölu. Það eru runur af tölum sem matcha við fleiri en eitt condition og ég er ekki að taka á því tilviki...
                if (!notRightCondition) {
                    System.out.println(entry.getKey() + " er málið fyrir stak " + i);
                    allConditionMatches.put(i, entry.getKey());
                    /*if (entry.getKey().contains("departure")) returnValue *= Long.parseLong(myTicket[i]);
                    conditionMap.remove(entry.getKey());*/
                    //break;
                }
            }
        }

        Map<String, Integer> finalMap = new HashMap<>();
        for (int i = 0; i < allConditionMatches.size(); i++) {
            // find smallest array of values each time
            int min = Integer.MAX_VALUE;
            int entryToGet = 0;

            for (Integer key : allConditionMatches.keySet()) {
                System.out.println(key);
                Collection<String> list = allConditionMatches.get(key);
                if (list.size() < min && list.size() > i) {
                    min = list.size();
                    entryToGet = key;
                }
            }
            // insert into finalMap after trimming
            Collection<String> list = allConditionMatches.get(entryToGet);
            for (String value : list) {
                if (finalMap.containsKey(value)) continue;
                finalMap.put(value, i);
            }
        }

        for (Map.Entry<String, Integer> entry : finalMap.entrySet()) {
            if (entry.getKey().contains("departure")) {
                returnValue *= Long.parseLong(myTicket[entry.getValue()]);
            }
        }

        return returnValue;
    }
}
