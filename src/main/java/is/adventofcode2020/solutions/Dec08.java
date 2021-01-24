package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dec08 {
    public static List<String> getInput() {
        List<String> inputLines = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec08.txt");
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

        return inputLines;
    }

    static String[][] getInstructions(List<String> inputLines) {
        String[][] instructions = new String[inputLines.size()][3];
        for (int i = 0; i < inputLines.size(); i++) {
            String[] array = inputLines.get(i).split(" ");
            instructions[i][0] = array[0];
            instructions[i][1] = array[1];
        }

        return instructions;
    }

    public static int solve(){
        List<String> inputLines = getInput();
        String[][] instructions = getInstructions(inputLines);

        int i = 0;
        int acc = 0;
        // SOLUTION TO PART I BEGINS
        /*while (true) {
            // If we've seen this instruction before we return the accumulated number
            if (instructions[i][2] != null) return acc;

            if (instructions[i][0].equals("nop")) {
                instructions[i][2] = "X"; // Mark it as 'seen'
                i++;
                continue;
            }

            if (instructions[i][0].equals("acc")) {
                int number = Integer.parseInt(instructions[i][1]);
                instructions[i][2] = "X"; // Mark it as 'seen'
                acc += number;
                i++;
                continue;
            }

            if (instructions[i][0].equals("jmp")) {
                int number = Integer.parseInt(instructions[i][1]);
                instructions[i][2] = "X"; // Mark it as 'seen'
                i += number;
            }
        }*/
        // SOLUTION TO PART I ENDS

        int jumpsSeen = 0;
        String lastChanged = "";
        boolean alreadyChanged = false;
        int lastJumpChanged = 0;
        int nopSeen = 0;
        int lastNopChanged = 0;
        while (true) {
            // We've reached the end of the instructions and return the accumulated number
            if (i == inputLines.size()) return acc;

            // If we've seen this instruction before we have run into a loop and we reset a bunch of variables
            if (instructions[i][2] != null) {
                i = 0;
                acc = 0;
                nopSeen = 0;
                jumpsSeen = 0;
                alreadyChanged = false;
                instructions = getInstructions(inputLines);
                if (lastChanged.equals("jmp")) {
                    lastJumpChanged++;
                } else {
                    lastNopChanged++;
                }
                continue;
            }

            if (instructions[i][0].equals("nop")) {
                nopSeen++;
                instructions[i][2] = "X"; // Mark it as 'seen'
                if (nopSeen > lastNopChanged && alreadyChanged == false) {
                    instructions[i][0] = "jmp";
                    lastChanged = "nop";
                    alreadyChanged = true;
                    i += Integer.parseInt(instructions[i][1]);
                    continue;
                }
                i++;
                continue;
            }

            if (instructions[i][0].equals("acc")) {
                int number = Integer.parseInt(instructions[i][1]);
                instructions[i][2] = "X"; // Mark it as 'seen'
                acc += number;
                i++;
                continue;
            }

            if (instructions[i][0].equals("jmp")) {
                jumpsSeen++;
                int number = Integer.parseInt(instructions[i][1]);
                instructions[i][2] = "X"; // Mark it as 'seen'
                if (jumpsSeen > lastJumpChanged && alreadyChanged == false) {
                    instructions[i][0] = "nop";
                    lastChanged = "jmp";
                    alreadyChanged = true;
                    i++;
                    continue;
                }
                i += number;
            }
        }
    }
}
