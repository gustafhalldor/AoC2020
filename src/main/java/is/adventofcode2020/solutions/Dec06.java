package is.adventofcode2020.solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Dec06 {
    public static int solve() {
        String data = "";
        try {
            data = new String(Files.readAllBytes(Paths.get("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec06.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] listOfAnswers = data.split("\n\\s*\n");

        int result = 0;
        // SOLUTION TO PART I BEGINS
        /*for (String group : listOfAnswers) {
            String[] array = group.split("\\s+");
            Set<Character> setOfAnswers = new HashSet<>();
            for (String answer : array) {
                char[] chars = answer.toCharArray();
                for (char c : chars) {
                    setOfAnswers.add(c);
                }
            }
            result += setOfAnswers.size();
        }*/
        // SOLUTION TO PART I ENDS

        for (String group : listOfAnswers) {
            String[] individualAnswers = group.split("\\s+");
            // Get set to check against
            Set<Character> set = new HashSet<>();
            Set<Character> settingSun = new HashSet<>();
            for (char c : individualAnswers[0].toCharArray()) {
                set.add(c);
                settingSun.add(c);
            }

            for (String answer : individualAnswers) {
                char[] chars = answer.toCharArray();
                Set<Character> answerSet = new HashSet<>();
                for (char c : chars) {
                    answerSet.add(c);
                }

                for (char c : set) {
                    if (!answerSet.contains(c)) settingSun.remove(c);
                }
            }

            result += settingSun.size();
        }

        return result;
    }
}
