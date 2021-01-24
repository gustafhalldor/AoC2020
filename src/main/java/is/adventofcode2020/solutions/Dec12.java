package is.adventofcode2020.solutions;

import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dec12 {
    public static int solve() {
        List<String> inputLines = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec12.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                inputLines.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // SOLUTION TO PART I BEGINS
        /*int north = 0; int east = 0; int south = 0; int west = 0;
        int heading = 90; // start facing east
        for (String input : inputLines) {
            char letter = input.charAt(0);
            int number = Integer.parseInt(input.substring(1));

            if (letter == 'R' || letter == 'L') {
                // Turn the boat
                switch (letter) {
                    case 'R' -> heading += number;
                    case 'L' -> heading -= number;
                }
                if (heading >= 360) heading = heading%360;
                if (heading < 0) {
                    while (heading < 0) {
                        heading += 360;
                    }
                }
                char headingDirection = getHeading(heading);

                continue;
            }

            if (letter != 'F') {
                switch (letter) {
                    case 'N' -> north += number;
                    case 'E' -> east += number;
                    case 'S' -> south += number;
                    case 'W' -> west += number;
                }
                continue;
            }

            // letter is 'F' so we move the ship in the heading direction
            char headingDirection = getHeading(heading);
            switch (headingDirection) {
                case 'N' -> north += number;
                case 'E' -> east += number;
                case 'S' -> south += number;
                case 'W' -> west += number;
            }
        }*/
        // SOLUTION TO PART I ENDS


        WayPoint wayPoint = new WayPoint(10, 1, 'E', 'N');
        Ship ship = new Ship(0,0);

        for (String input : inputLines) {
            char letter = input.charAt(0);
            int number = Integer.parseInt(input.substring(1));

            if (letter == 'R' || letter == 'L') {
                // Rotate the waypoint
                int degrees = letter == 'R' ? number : -number;
                wayPoint.rotateWaypoint(degrees);

                continue;
            }

            if (letter != 'F') {
                wayPoint.moveWaypoint(letter, number);
                continue;
            }

            // move ship forward
            if (wayPoint.northSouth == 'N') {
                ship.y += wayPoint.y * number;
            } else {
                ship.y += wayPoint.y * number;
            }

            if (wayPoint.eastWest == 'E') {
                ship.x += wayPoint.x * number;
            } else {
                ship.x += wayPoint.x * number;
            }
        }

        return Math.abs(ship.x) + Math.abs(ship.y);
    }


    static char getHeading(int heading) {
        char direction = ' ';
        switch (heading) {
            case 0 -> direction = 'N';
            case 90 -> direction = 'E';
            case 180 -> direction = 'S';
            case 270 -> direction = 'W';
        }
        return direction;
    }

    @Data
    public static class WayPoint {
        int x;
        int y;
        char northSouth;
        char eastWest;

        WayPoint(int x, int y, char eastWest, char northSouth) {
            this.x = x;
            this.y = y;
            this.northSouth = northSouth;
            this.eastWest = eastWest;
        }

        void rotateWaypoint(int degrees) {
            if (degrees == 90 || degrees == -270) {
                int oldX = this.x;
                this.x = this.y;
                this.y = -oldX;
            }
            if (degrees == 180 || degrees == -180) {
                this.x = -this.x;
                this.y = -this.y;
            }
            if (degrees == 270 || degrees == -90) {
                int oldX = this.x;
                this.x = -this.y;
                this.y = oldX;
            }

            this.eastWest = this.x > 0 ? 'E' : 'W';
            this.northSouth = this.y > 0 ? 'N' : 'S';
        }

        void moveWaypoint(int direction, int value) {
            switch (direction) {
                case 'N' -> this.y += value;
                case 'E' -> this.x += value;
                case 'S' -> this.y -= value;
                case 'W' -> this.x -= value;
            }
        }
    }

    @Data
    public static class Ship {
        int x;
        int y;

        Ship(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

