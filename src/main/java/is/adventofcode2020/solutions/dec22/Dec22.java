package is.adventofcode2020.solutions.dec22;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Dec22 {
    public static long solve() {
        Queue<Integer> player1Queue = new LinkedList<>();
        try {
            File file = new File("src\\main\\java\\is\\adventofcode2020\\solutions\\dec22\\test_player_1.txt");
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
            File file = new File("src\\main\\java\\is\\adventofcode2020\\solutions\\dec22\\test_player_2.txt");
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

        // TODO PART II
        // Recursive combat
        // Bookkeeping for decks, to check if they've been played before
        List<String> player1Decks = new ArrayList<>();
        List<String> player2Decks = new ArrayList<>();

        player1Decks.add(player1Queue.toString());
        player2Decks.add(player2Queue.toString());
        String winner = playGame(player1Queue, player2Queue, player1Decks, player2Decks);

        return totalPoints;
    }

    static String playGame(Queue<Integer> player1Queue,
                           Queue<Integer> player2Queue,
                           List<String> player1Decks,
                           List<String> player2Decks) {
        String winner = playRound(player1Queue, player2Queue, player1Decks, player2Decks);
        player1Decks.clear();
        player2Decks.clear();
        return winner;
    }

    static String playRound(Queue<Integer> player1Queue,
                            Queue<Integer> player2Queue,
                            List<String> player1Decks,
                            List<String> player2Decks) {
        for (int i = 0; i < player1Decks.size(); i++) {
            if (player1Decks.get(i) == player1Queue.toString() && player2Decks.get(i) == player2Queue.toString()) {
                return "player1"; }
        }

        int player1TopCard = player1Queue.remove();
        int player2TopCard = player2Queue.remove();

        Iterator player1 = player1Queue.iterator();
        Iterator player2 = player2Queue.iterator();
        while (player1.hasNext() && player2.hasNext()) {
            if (player1TopCard >= player1Queue.size() && player2TopCard >= player2Queue.size() ) {
                String winner = playGame(player1Queue, player2Queue, player1Decks, player2Decks);
                switch (winner) {
                    case "player1" -> {
                        player1Queue.add(player1TopCard);
                        player1Queue.add(player2TopCard);
                    }
                    case "player2" -> {
                        player2Queue.add(player2TopCard);
                        player2Queue.add(player1TopCard);
                    }
                }
            } else {
                if (player1TopCard > player2TopCard) {
                    player1Queue.add(player1TopCard);
                    player1Queue.add(player2TopCard);
                } else {
                    player2Queue.add(player2TopCard);
                    player2Queue.add(player1TopCard);
                }
            }
        }

        System.out.println("yoyoyo");

        return "";
    }
}
