package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dec13 {
    public static long solve() {
        List<String> inputLines = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec13.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                inputLines.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // SOLUTION TO PART I BEGINS
        /*int departureTime = Integer.parseInt(inputLines.get(0));
        String[] busSchedule = inputLines.get(1).split(",");
        List<Integer> busesInService = new ArrayList<>();
        for (String s : busSchedule) {
            if (!s.equals("x")) busesInService.add(Integer.parseInt(s));
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < busesInService.size(); i++) {
            int remainder = departureTime%busesInService.get(i);
            int difference = -remainder + busesInService.get(i);
            map.put(busesInService.get(i), difference);
        }

        int busToTake = 0;
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Integer, Integer> bus : map.entrySet()) {
            if (bus.getValue() < min) {
                min = bus.getValue();
                busToTake = bus.getKey();
            }
        }

        return busToTake * min;*/
        // SOLUTION TO PART I ENDS

        String[] busSchedule = inputLines.get(1).split(",");
        int firstBus = Integer.parseInt(busSchedule[0]);
        long numberToCheck = 0;
        boolean found = false;

        //finna stærstu töluna í listanum og í hvaða indexi hún er
        // edit: þetta virkaði ekki til að leysa dæmið á fljótan hátt. Þetta hefði tekið tölvuna einhverja klukkutíma...
        int max = 0;
        int index = 0;
        for (int i = 0; i < busSchedule.length; i++) {
            if (busSchedule[i].equals("x")) continue;
            if (Integer.parseInt(busSchedule[i]) > max) {
                max = Integer.parseInt(busSchedule[i]);
                index = i;
            }
        }

        // Fann þessa lausn eftir ég skoðaði þráð á reddit
        List<Integer> numbersToMultiply = new ArrayList<>();
        while (!found) {
            for (int i = 0; i < busSchedule.length; i++) {
                if (busSchedule[i].equals("x")) {
                    continue;
                }
                if ((numberToCheck+i) % Integer.parseInt(busSchedule[i]) == 0) {
                    if (i == busSchedule.length -1) {
                        found = true;
                        break;
                    } else {
                        if (!numbersToMultiply.contains(Integer.parseInt(busSchedule[i]))) {
                            numbersToMultiply.add(Integer.parseInt(busSchedule[i]));
                            break;
                        }
                    }
                } else break;
            }
            if (numbersToMultiply.size() < 2) numberToCheck += firstBus;
            else {
                long totalToAdd = 1;
                for (long number : numbersToMultiply) {
                    totalToAdd *= number;
                }
                if (!found) numberToCheck += totalToAdd;
            }
        }

        return numberToCheck;
    }
}


/*while (!found) {
        numberToCheck += firstBus;
        if ((numberToCheck + index) % max != 0) continue;
        for (int i = 0; i < busSchedule.length; i++) {
        if (busSchedule[i].equals("x")) {
        continue;
        }
        if ((numberToCheck+i) % Integer.parseInt(busSchedule[i]) == 0) {
        if (i == busSchedule.length -1) {
        found = true;
        break;
        }
        } else break;
        }
        }*/
