package is.adventofcode2020.solutions.dec22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Dec22 {
    static int GAME_NR = 0;
    public static long solve() {
        Queue<Integer> player1Queue = new LinkedList<>();
        try {
            File file = new File("src\\main\\java\\is\\adventofcode2020\\solutions\\dec22\\player_1.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                player1Queue.add(Integer.parseInt(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Queue<Integer> player2Queue = new LinkedList<>();
        try {
            File file = new File("src\\main\\java\\is\\adventofcode2020\\solutions\\dec22\\player_2.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                player2Queue.add(Integer.parseInt(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long totalPoints = 0;
        // SOLUTION TO PART I BEGINS
        /*Iterator player1 = player1Queue.iterator();
        Iterator player2 = player2Queue.iterator();

        while (player1.hasNext() && player2.hasNext()) {
            int player1Card = player1Queue.remove();
            int player2Card = player2Queue.remove();

            if (player1Card > player2Card) {
                player1Queue.add(player1Card);
                player1Queue.add(player2Card);
            } else {
                player2Queue.add(player2Card);
                player2Queue.add(player1Card);
            }
        }
        List<Integer> list = new ArrayList<>();
        if (!player1Queue.isEmpty()) list.addAll(player1Queue);
        else list.addAll(player2Queue);

        for (int i = 0, j = list.size(); i < list.size(); i++, j--) {
            totalPoints += list.get(i) * j;
        }*/
        // SOLUTION TO PART I ENDS

        // PART II
        // Recursive combat

        playGame(player1Queue, player2Queue);

        List<Integer> list = new ArrayList<>();
        if (!player1Queue.isEmpty()) list.addAll(player1Queue);
        else list.addAll(player2Queue);

        for (int i = 0, j = list.size(); i < list.size(); i++, j--) {
            totalPoints += list.get(i) * j;
        }

        return totalPoints;
    }

    static String playGame(Queue<Integer> player1Queue,
                           Queue<Integer> player2Queue) {
        GAME_NR++;
        int gameNumber = GAME_NR;
        System.out.println("Playing game number: " + GAME_NR);

        // Bookkeeping for decks, to check if they've been played before
        Set<String> decksUsed = new HashSet<>();

        Iterator player1 = player1Queue.iterator();
        Iterator player2 = player2Queue.iterator();
        int roundNr = 0;
        while (player1.hasNext() && player2.hasNext()) {
            roundNr++;
            System.out.println("Playing round " + roundNr + " of game number: " + gameNumber);

            // If the same decks have been used before then player1 is declared the winner of the game being played
            String decksString = player1Queue.toString() + player2Queue.toString();
            if (decksUsed.contains(decksString)) return "player1";
            else decksUsed.add(decksString);

            playRound(player1Queue, player2Queue, roundNr, gameNumber);
        }

        if (!player1Queue.isEmpty()) {
            System.out.println("player1 won game " + gameNumber);
            return "player1";
        }
        else {
            System.out.println("player2 won game " + gameNumber);
            return "player2";
        }
    }

    static void playRound(Queue<Integer> player1Queue,
                          Queue<Integer> player2Queue,
                          int roundNr,
                          int gameNr) {
        int player1TopCard = player1Queue.remove();
        int player2TopCard = player2Queue.remove();

        if (player1TopCard <= player1Queue.size() && player2TopCard <= player2Queue.size() ) {
            Queue<Integer> player1QueueCopy = player1Queue.stream().limit(player1TopCard).collect(Collectors.toCollection(LinkedList::new));
            Queue<Integer> player2QueueCopy = player2Queue.stream().limit(player2TopCard).collect(Collectors.toCollection(LinkedList::new));
            String winner = playGame(player1QueueCopy, player2QueueCopy);
            switch (winner) {
                case "player1" -> {
                    System.out.println("player1 won round " + roundNr + " of game " + gameNr);
                    player1Queue.add(player1TopCard);
                    player1Queue.add(player2TopCard);
                }
                case "player2" -> {
                    System.out.println("player2 won round " + roundNr + " of game " + gameNr);
                    player2Queue.add(player2TopCard);
                    player2Queue.add(player1TopCard);
                }
            }
        } else {
            if (player1TopCard > player2TopCard) {
                System.out.println("player1 won round " + roundNr + " of game " + gameNr);
                player1Queue.add(player1TopCard);
                player1Queue.add(player2TopCard);
            } else {
                System.out.println("player2 won round " + roundNr + " of game " + gameNr);
                player2Queue.add(player2TopCard);
                player2Queue.add(player1TopCard);
            }
        }
    }
}
