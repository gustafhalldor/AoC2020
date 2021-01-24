package is.adventofcode2020.solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dec25 {
    public static long solve() {
        List<Integer> input = new ArrayList<>();
        try {
            File file = new File("C:\\Users\\geelo\\Documents\\advent_of_code\\src\\main\\resources\\inputs\\dec25.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                input.add(Integer.parseInt(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        long cardPublicKey = input.get(0);
        long doorPublicKey = input.get(1);

        long cardLoopSize = calcLoopSize(cardPublicKey);
        long doorLoopSize = calcLoopSize(doorPublicKey);

        long encryptionKey = calcEncryptionKey(doorPublicKey, cardLoopSize);

        return encryptionKey;
    }

    static long calcLoopSize(long publicKey) {
        long i = 1;
        long value = 1;

        while (true) {
            value *= 7;
            value = value % 20201227;
            if (value == publicKey) return i;
            i++;
        }
    }

    static long calcEncryptionKey(long subjectNumber, long loopSize) {
        long value = 1;
        for (int i = 0; i < loopSize; i++) {
            value *= subjectNumber;
            value = value % 20201227;
        }

        return value;
    }
}
