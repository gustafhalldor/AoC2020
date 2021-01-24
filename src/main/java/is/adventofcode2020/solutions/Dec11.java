package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Dec11 {
    public static int solve(){
        List<String> inputLines = new ArrayList<>();
        try {
            File myObj = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec11.txt");
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

        // Get length of each line and total lines
        int xLength = inputLines.get(0).length();
        int yLength = inputLines.size();

        // create the 2d character array
        char[][] layout = new char[xLength][yLength];
        char[][] layoutAfterChanges = new char[xLength][yLength];
        for (int i = 0; i < yLength; i++) {
            char[] charArray = inputLines.get(i).toCharArray();
            for (int j = 0; j < xLength; j++) {
                layout[j][i] = charArray[j];
                layoutAfterChanges[j][i] = charArray[j];
            }
        }

        // SOLUTION TO PART I BEGINS
        /*int nrOfChanges = 0;
        while (true) {
            for (int y = 0; y < yLength; y++) {
                for (int x = 0; x < xLength; x++) {
                    char c = layout[x][y];
                    if (c == '.') continue;
                    if (c == 'L'){
                        if (shouldSeatBeOccupied(layout, x, y, xLength, yLength)) {
                            layoutAfterChanges[x][y] = '#';
                            nrOfChanges++;
                        }
                    }
                    if (c == '#') {
                        if (shouldSeatBeEmpty(layout, x, y, xLength, yLength)) {
                            layoutAfterChanges[x][y] = 'L';
                            nrOfChanges++;
                        }
                    }
                }
            }
            if (nrOfChanges == 0) break;

            nrOfChanges = 0;
            layout = deepCopy(layoutAfterChanges);
        }*/
        // SOLUTION TO PART I ENDS

        int nrOfChanges = 0;
        while (true) {
            for (int y = 0; y < yLength; y++) {
                for (int x = 0; x < xLength; x++) {
                    char c = layout[x][y];
                    if (c == '.') continue;
                    if (c == 'L'){
                        if (shouldSeatBeOccupiedPt2(layout, x, y, xLength, yLength)) {
                            layoutAfterChanges[x][y] = '#';
                            nrOfChanges++;
                        }
                        continue;
                    }
                    if (c == '#') {
                        if (shouldSeatBeEmptyPt2(layout, x, y, xLength, yLength)) {
                            layoutAfterChanges[x][y] = 'L';
                            nrOfChanges++;
                        }
                    }
                }
            }
            if (nrOfChanges == 0) break;

            nrOfChanges = 0;
            layout = deepCopy(layoutAfterChanges);
        }

        // USED FOR BOTH PART I AND II, WE COUNT THE OCCUPIED SEATS
        int nrOfOccupiedSeats = 0;
        for (int i = 0; i < yLength; i++) {
            for (int j = 0; j < xLength; j++) {
                if (layoutAfterChanges[j][i] == '#') nrOfOccupiedSeats++;
                System.out.print(layoutAfterChanges[j][i]);
            }
            System.out.println();
        }

        return nrOfOccupiedSeats;
    }

    static boolean shouldSeatBeEmpty(char[][] array, int x, int y, int xLength, int yLength) {
        int occupiedSeatsRequired = 4;
        if (x-1 >= 0) {
            if (array[x-1][y] == '#') occupiedSeatsRequired--;
        }

        if (x-1 >= 0 && y-1 >= 0) {
            if (array[x-1][y-1] == '#') occupiedSeatsRequired--;
        }

        if (y-1 >= 0) {
            if (array[x][y-1] == '#') occupiedSeatsRequired--;
        }

        if (x+1 < xLength && y-1 >= 0) {
            if (array[x+1][y-1] == '#') occupiedSeatsRequired--;
            if (occupiedSeatsRequired == 0) return true;
        }

        if (x+1 < xLength) {
            if (array[x+1][y] == '#') occupiedSeatsRequired--;
            if (occupiedSeatsRequired == 0) return true;
        }

        if (x+1 < xLength && y+1 < yLength) {
            if (array[x+1][y+1] == '#') occupiedSeatsRequired--;
            if (occupiedSeatsRequired == 0) return true;
        }

        if (y+1 < yLength) {
            if (array[x][y+1] == '#') occupiedSeatsRequired--;
            if (occupiedSeatsRequired == 0) return true;
        }

        if (x-1 >= 0 && y+1 < yLength) {
            if (array[x-1][y+1] == '#') occupiedSeatsRequired--;
            if (occupiedSeatsRequired == 0) return true;
        }

        return false;
    }

    static boolean shouldSeatBeOccupied(char[][] array, int x, int y, int xLength, int yLength) {
        int emptySeatsRequired = 8;
        int emptySeats = 0;
        if (x-1 >= 0) {
            if (array[x-1][y] == 'L' || array[x-1][y] == '.') emptySeats++;
        } else emptySeatsRequired--;

        if (x-1 >= 0 && y-1 >= 0) {
            if (array[x-1][y-1] == 'L' || array[x-1][y-1] == '.') emptySeats++;
        } else emptySeatsRequired--;

        if (y-1 >= 0) {
            if (array[x][y-1] == 'L' || array[x][y-1] == '.') emptySeats++;
        } else emptySeatsRequired--;

        if (x+1 < xLength && y-1 >= 0) {
            if (array[x+1][y-1] == 'L' || array[x+1][y-1] == '.') emptySeats++;
        } else emptySeatsRequired--;

        if (x+1 < xLength) {
            if (array[x+1][y] == 'L' || array[x+1][y] == '.') emptySeats++;
        } else emptySeatsRequired--;

        if (x+1 < xLength && y+1 < yLength) {
            if (array[x+1][y+1] == 'L' || array[x+1][y+1] == '.') emptySeats++;
        } else emptySeatsRequired--;

        if (y+1 < yLength) {
            if (array[x][y+1] == 'L' || array[x][y+1] == '.') emptySeats++;
        } else emptySeatsRequired--;

        if (x-1 >= 0 && y+1 < yLength) {
            if (array[x-1][y+1] == 'L' || array[x-1][y+1] == '.') emptySeats++;
        } else emptySeatsRequired--;

        return emptySeats == emptySeatsRequired;
    }

    static boolean shouldSeatBeEmptyPt2(char[][] array, int x, int y, int xLength, int yLength) {
        int occupiedSeatsRequired = 5;
        int occupiedSeats = 0;
        int i = 1;
        while (x-i >= 0) {
            if (array[x-i][y] == '.') {
                i++;
                continue;
            }
            if (array[x-i][y] == '#') {
                occupiedSeats++;
                break;
            }
            if (array[x-i][y] == 'L') {
                break;
            }
            i++;
        }

        i = 1;
        while (x-i >= 0 && y-i >= 0) {
            if (array[x-i][y-i] == '.') {
                i++;
                continue;
            }
            if (array[x-i][y-i] == '#') {
                occupiedSeats++;
                break;
            }
            if (array[x-i][y-i] == 'L') {
                break;
            }
            i++;
        }

        i = 1;
        while (y-i >= 0) {
            if (array[x][y-i] == '.') {
                i++;
                continue;
            }
            if (array[x][y-i] == '#') {
                occupiedSeats++;
                break;
            }
            if (array[x][y-i] == 'L') {
                break;
            }
            i++;
        }

        i = 1;
        while (x+i < xLength && y-i >= 0) {
            if (array[x+i][y-i] == '.') {
                i++;
                continue;
            }
            if (array[x+i][y-i] == '#') {
                occupiedSeats++;
                break;
            }
            if (array[x+i][y-i] == 'L') {
                break;
            }
            i++;
        }

        i = 1;
        while (x+i < xLength) {
            if (array[x+i][y] == '.') {
                i++;
                continue;
            }
            if (array[x+i][y] == '#') {
                occupiedSeats++;
                break;
            }
            if (array[x+i][y] == 'L') {
                break;
            }
            i++;
        }

        i = 1;
        while (x+i < xLength && y+i < yLength) {
            if (array[x+i][y+i] == '.') {
                i++;
                continue;
            }
            if (array[x+i][y+i] == '#') {
                occupiedSeats++;
                break;
            }
            if (array[x+i][y+i] == 'L') {
                break;
            }
            i++;
        }

        i = 1;
        while (y+i < yLength) {
            if (array[x][y+i] == '.') {
                i++;
                continue;
            }
            if (array[x][y+i] == '#') {
                occupiedSeats++;
                break;
            }
            if (array[x][y+i] == 'L') {
                break;
            }
            i++;
        }

        i = 1;
        while (x-i >= 0 && y+i < yLength) {
            if (array[x-i][y+i] == '.') {
                i++;
                continue;
            }
            if (array[x-i][y+i] == '#') {
                occupiedSeats++;
                break;
            }
            if (array[x-i][y+i] == 'L') {
                break;
            }
            i++;
        }

        return occupiedSeats >= occupiedSeatsRequired;
    }

    // empty seats that see no occupied seats become occupied
    static boolean shouldSeatBeOccupiedPt2(char[][] array, int x, int y, int xLength, int yLength) {
        int emptySeatsRequired = 8;
        int emptySeats = 0;
        int i = 1;
        if (x-i < 0) {
            emptySeatsRequired--;
        } else {
            boolean noSeatFound = true;
            while (x-i >= 0) {
                if (array[x-i][y] == '.') {
                    i++;
                    continue;
                }
                if (array[x-i][y] == '#') {
                    return false;
                }
                if (array[x-i][y] == 'L') {
                    emptySeats++;
                    noSeatFound = false;
                    break;
                }
                i++;
            }
            if (noSeatFound) emptySeats++;
        }

        i = 1;
        if (x-i < 0 || y-i < 0){
            emptySeatsRequired--;
        } else {
            boolean noSeatFound = true;
            while (x-i >= 0 && y-i >= 0) {
                if (array[x-i][y-i] == '.') {
                    i++;
                    continue;
                }
                if (array[x-i][y-i] == '#') {
                    return false;
                }
                if (array[x-i][y-i] == 'L') {
                    emptySeats++;
                    noSeatFound = false;
                    break;
                }
                i++;
            }
            if (noSeatFound) emptySeats++;
        }

        i = 1;
        if (y-i < 0) {
            emptySeatsRequired--;
        } else {
            boolean noSeatFound = true;
            while (y-i >= 0) {
                if (array[x][y-i] == '.') {
                    i++;
                    continue;
                }
                if (array[x][y-i] == '#') {
                    return false;
                }
                if (array[x][y-i] == 'L') {
                    emptySeats++;
                    noSeatFound = false;
                    break;
                }
                i++;
            }
            if (noSeatFound) emptySeats++;
        }

        i = 1;
        if (x+i >= xLength || y-i < 0) {
            emptySeatsRequired--;
        } else {
            boolean noSeatFound = true;
            while (x+i < xLength && y-i >= 0) {
                if (array[x+i][y-i] == '.') {
                    i++;
                    continue;
                }
                if (array[x+i][y-i] == '#') {
                    return false;
                }
                if (array[x+i][y-i] == 'L') {
                    emptySeats++;
                    noSeatFound = false;
                    break;
                }
                i++;
            }
            if (noSeatFound) emptySeats++;
        }

        i = 1;
        if (x+i >= xLength) {
            emptySeatsRequired--;
        } else {
            boolean noSeatFound = true;
            while (x+i < xLength) {
                if (array[x+i][y] == '.') {
                    i++;
                    continue;
                }
                if (array[x+i][y] == '#') {
                    return false;
                }
                if (array[x+i][y] == 'L') {
                    emptySeats++;
                    noSeatFound = false;
                    break;
                }
                i++;
            }
            if (noSeatFound) emptySeats++;
        }

        i = 1;
        if (x+i >= xLength || y+i >= yLength) {
            emptySeatsRequired--;
        } else {
            boolean noSeatFound = true;
            while (x+i < xLength && y+i < yLength) {
                if (array[x+i][y+i] == '.') {
                    i++;
                    continue;
                }
                if (array[x+i][y+i] == '#') {
                    return false;
                }
                if (array[x+i][y+i] == 'L') {
                    emptySeats++;
                    noSeatFound = false;
                    break;
                }
                i++;
            }
            if (noSeatFound) emptySeats++;
        }

        i = 1;
        if (y+i >= yLength) {
            emptySeatsRequired--;
        } else {
            boolean noSeatFound = true;
            while (y+i < yLength) {
                if (array[x][y+i] == '.') {
                    i++;
                    continue;
                }
                if (array[x][y+i] == '#') {
                    return false;
                }
                if (array[x][y+i] == 'L') {
                    emptySeats++;
                    noSeatFound = false;
                    break;
                }
                i++;
            }
            if (noSeatFound) emptySeats++;
        }

        i = 1;
        if (x-i < 0 || y+i >= yLength) {
            emptySeatsRequired--;
        } else {
            boolean noSeatFound = true;
            while (x-i >= 0 && y+i < yLength) {
                if (array[x-i][y+i] == '.') {
                    i++;
                    continue;
                }
                if (array[x-i][y+i] == '#') {
                    return false;
                }
                if (array[x-i][y+i] == 'L') {
                    emptySeats++;
                    noSeatFound = false;
                    break;
                }
                i++;
            }
            if (noSeatFound) emptySeats++;
        }

        return emptySeats == emptySeatsRequired;
    }

    public static char[][] deepCopy(char[][] original) {
        if (original == null) {
            return null;
        }

        final char[][] result = new char[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = Arrays.copyOf(original[i], original[i].length);

        }
        return result;
    }
}
