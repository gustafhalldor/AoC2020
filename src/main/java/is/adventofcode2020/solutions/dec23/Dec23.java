package is.adventofcode2020.solutions.dec23;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dec23 {
    final static int NR_OF_MOVES = 100;
    final static int MILLION = 1000000;
    
    public static long solve() {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int[] array = new int[1000000];
        try {
            File file = new File("src\\main\\java\\is\\adventofcode2020\\solutions\\dec23\\input.txt");
            Scanner s = new Scanner(file);
            int index = 0;
            while (s.hasNextInt()) {
                int number = s.nextInt();
                list.add(number);
                array[index++] = number;
            }
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // SOLUTION FOR PART I STARTS
        /*for (int i = 0; i < NR_OF_MOVES; i++) {
            int currentCup = list.get(0);
            List<Integer> subList = List.copyOf(list.subList(1, 4));
            list.remove(1);
            list.remove(1);
            list.remove(1);

            // Locate destination cup
            boolean destinationCupFound = false;
            int destinationCup = currentCup - 1;
            while (!destinationCupFound) {
                if (destinationCup == 0) destinationCup = list.size() + 3;  // Add 3 as we've already removed 3 elements

                if (subList.contains(destinationCup)) {
                    destinationCup--;
                    continue;
                }

                int indexOfDestinationCup = list.size()-1;
                for (int j = indexOfDestinationCup; j > 0; j--) {
                    if (list.get(j) == destinationCup) {
                        destinationCupFound = true;
                        indexOfDestinationCup = j;
                        break;
                    }
                }

                list.addAll(indexOfDestinationCup + 1, subList);

                list.remove(0);
                list.add(currentCup);
            }
        }

        int indexOfOne = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 1) indexOfOne = i;
        }

        List<Integer> listAfterOne = new ArrayList<>();
        listAfterOne.addAll(list.subList(indexOfOne + 1, list.size()));
        listAfterOne.addAll(list.subList(0, indexOfOne));

        String result = "";
        for (int i = 0; i < listAfterOne.size(); i++) {
            result += listAfterOne.get(i);
        }

        return result;*/
        // SOLUTION FOR PART I ENDS


        // PART II
        for (int i = 0; i < list.size(); i++) {
            if (i+1 >= list.size()) break;
            map.put(list.get(i), list.get(i+1));
        }
        map.put(list.get(list.size()-1), 10);

        // populate the map up until 1 million is reached
        for (int i = 10; i <= MILLION + 1; i++) {
            if (i+1 > MILLION) {
                break;
            }
            map.put(i, i+1);
        }
        // Add the final touch for the wrap-around
        map.put(MILLION, list.get(0));

        int currentCup = list.get(0);
        for (int i = 0; i < MILLION * 10; i++) {
            int oneAfterCurrent = map.get(currentCup);
            int twoAfterCurrent = map.get(oneAfterCurrent);
            int threeAfterCurrent = map.get(twoAfterCurrent);

            int destinationCup = currentCup - 1;
            if (destinationCup == 0) destinationCup = MILLION;
            while (destinationCup == oneAfterCurrent || destinationCup == twoAfterCurrent || destinationCup == threeAfterCurrent) {
                destinationCup--;
                if (destinationCup == 0) destinationCup = MILLION;
            }

            map.put(currentCup, map.get(threeAfterCurrent));
            currentCup = map.get(threeAfterCurrent);
            int tempDest = map.get(destinationCup);
            map.put(threeAfterCurrent, tempDest);
            map.put(destinationCup, oneAfterCurrent);
        }

        return (long) map.get(1) * (long) map.get(map.get(1));
    }
}
