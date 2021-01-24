package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Dec24 {
    public static int solve() {
        List<String> inputLines = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec24.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                inputLines.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // SOLUTION TO PART I BEGINS
        int nrOfBlackTiles = 0;
        Map<String, String> tileMap = new HashMap<>();
        for (String line : inputLines) {
            List<String> list = new ArrayList<>();
            String s = "";
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c == 'n') {
                    s += c;
                    continue;
                }

                if (c == 's') {
                    s += c;
                    continue;
                }

                if (c == 'e') {
                    s += c;
                    list.add(s);
                    s = "";
                    continue;
                }

                if (c == 'w') {
                    s += c;
                    list.add(s);
                    s = "";
                }
            }

            float x = 0; float y = 0;
            for (int i = 0; i < list.size(); i++) {
                String direction = list.get(i);
                switch (direction) {
                    case "ne" -> {x += 0.5; y++;}
                    case "e" -> {x++;}
                    case "se" -> {x += 0.5; y--;}
                    case "sw" -> {x -= 0.5; y--;}
                    case "w" -> {x--;}
                    case "nw" -> {x -= 0.5; y++;}
                }
            }
            String tile = x + "," + y;

            if (tileMap.containsKey(tile)) {
                String previousTileColor = tileMap.get(tile);
                if (previousTileColor.equals("black")) {
                    tileMap.replace(tile, "white");
                    nrOfBlackTiles--;
                } else {
                    tileMap.replace(tile, "black");
                    nrOfBlackTiles++;
                }
            } else {
                tileMap.put(tile, "black");
                nrOfBlackTiles++;
            }
        }
        // return nrOfBlackTiles;
        // SOLUTION TO PART I ENDS

        Map<String, String> newTileMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {

            for (Map.Entry<String, String> entry : tileMap.entrySet()) {
                if (entry.getValue().equals("black")) {
                    shouldBlackTileBeFlipped(tileMap, newTileMap, entry.getKey());
                } else {
                    shouldWhiteTileBeFlipped(tileMap, newTileMap, entry.getKey());
                }
            }
            tileMap = Map.copyOf(newTileMap);
            newTileMap.clear();
        }

        int counter = 0;
        for (Map.Entry<String, String> entry : tileMap.entrySet()) {
            if (entry.getValue().equals("black")) counter++;
        }

        return counter;
    }

    static void shouldBlackTileBeFlipped(Map<String, String> tilemap, Map<String, String> newtilemap, String tileString) {
        String[] array = tileString.split(",");
        float x = Float.parseFloat(array[0]);
        float y = Float.parseFloat(array[1]);

        int nrOfBlackTiles = 0;
        // Go clockwise direction and check if there is a tile
        // - If there is a tile and it's white, or if there isn't a tile found, then we need to go deeper
        x += 0.5;
        y += 1;
        String key = x + "," + y;
        boolean foundBlack = false;
        if (tilemap.containsKey(key)) {
            if (tilemap.get(key).equals("black")) {
                nrOfBlackTiles++;
                foundBlack = true;
            }
        }
        if (!foundBlack) {
            // The tile was either white or we hadn't seen it before
            shouldWhiteTileBeFlipped(tilemap, newtilemap, key);
        }

        foundBlack = false;
        x += 0.5;
        y -= 1;
        key = x + "," + y;
        if (tilemap.containsKey(key)) {
            if (tilemap.get(key).equals("black")) nrOfBlackTiles++;
            foundBlack = true;
        }
        if (!foundBlack) {
            // The tile was either white or we hadn't seen it before
            shouldWhiteTileBeFlipped(tilemap, newtilemap, key);
        }

        foundBlack = false;
        x -= 0.5;
        y -= 1;
        key = x + "," + y;
        if (tilemap.containsKey(key)) {
            if (tilemap.get(key).equals("black")) nrOfBlackTiles++;
            foundBlack = true;
        }
        if (!foundBlack) {
            // The tile was either white or we hadn't seen it before
            shouldWhiteTileBeFlipped(tilemap, newtilemap, key);
        }

        foundBlack = false;
        x -= 1;
        key = x + "," + y;
        if (tilemap.containsKey(key)) {
            if (tilemap.get(key).equals("black")) nrOfBlackTiles++;
            foundBlack = true;
        }
        if (!foundBlack) {
            // The tile was either white or we hadn't seen it before
            shouldWhiteTileBeFlipped(tilemap, newtilemap, key);
        }

        foundBlack = false;
        x -= 0.5;
        y += 1;
        key = x + "," + y;
        if (tilemap.containsKey(key)) {
            if (tilemap.get(key).equals("black")) nrOfBlackTiles++;
            foundBlack = true;
        }
        if (!foundBlack) {
            // The tile was either white or we hadn't seen it before
            shouldWhiteTileBeFlipped(tilemap, newtilemap, key);
        }

        foundBlack = false;
        x += 0.5;
        y += 1;
        key = x + "," + y;
        if (tilemap.containsKey(key)) {
            if (tilemap.get(key).equals("black")) nrOfBlackTiles++;
            foundBlack = true;
        }
        if (!foundBlack) {
            // The tile was either white or we hadn't seen it before
            shouldWhiteTileBeFlipped(tilemap, newtilemap, key);
        }

        if (nrOfBlackTiles == 0 || nrOfBlackTiles > 2) {
            newtilemap.put(tileString, "white");
        } else {
            newtilemap.put(tileString, "black");
        }
    }

    private static void shouldWhiteTileBeFlipped(Map<String, String> tilemap, Map<String, String> newtilemap, String tileString) {
        String[] array = tileString.split(",");
        float x = Float.parseFloat(array[0]);
        float y = Float.parseFloat(array[1]);

        int nrOfBlackTiles = 0;
        // Go clockwise direction and check if there is a tile
        // - If there is a tile and it's white, or if there isn't a tile found, then we need to go deeper
        x += 0.5;
        y += 1;
        String key = x + "," + y;
        if (tilemap.containsKey(key)) {
            if (tilemap.get(key).equals("black")) {
                nrOfBlackTiles++;
            }
        }

        x += 0.5;
        y -= 1;
        key = x + "," + y;
        if (tilemap.containsKey(key)) {
            if (tilemap.get(key).equals("black")) nrOfBlackTiles++;
        }

        x -= 0.5;
        y -= 1;
        key = x + "," + y;
        if (tilemap.containsKey(key)) {
            if (tilemap.get(key).equals("black")) nrOfBlackTiles++;
        }

        x -= 1;
        key = x + "," + y;
        if (tilemap.containsKey(key)) {
            if (tilemap.get(key).equals("black")) nrOfBlackTiles++;
        }

        x -= 0.5;
        y += 1;
        key = x + "," + y;
        if (tilemap.containsKey(key)) {
            if (tilemap.get(key).equals("black")) nrOfBlackTiles++;
        }

        x += 0.5;
        y += 1;
        key = x + "," + y;
        if (tilemap.containsKey(key)) {
            if (tilemap.get(key).equals("black")) nrOfBlackTiles++;
        }

        if (nrOfBlackTiles == 2) {
            newtilemap.put(tileString, "black");
        } else {
            newtilemap.put(tileString, "white");
        }
    }
}