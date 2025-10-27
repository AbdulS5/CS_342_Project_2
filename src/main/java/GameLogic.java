import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class GameLogic {

    private static final Random random = new Random();

    // Calculate winnings based on spots and matches
    public static int calculateWinnings(int spots, int matches) {
        switch (spots) {
            case 10:
                switch (matches) {
                    case 10: return 100_000;
                    case 9: return 4250;
                    case 8: return 450;
                    case 7: return 40;
                    case 6: return 15;
                    case 5: return 2;
                    case 0: return 5;
                    default: return 0;
                }
            case 9:
                switch (matches) {
                    case 9: return 30_000;
                    case 8: return 3000;
                    case 7: return 150;
                    case 6: return 25;
                    case 5: return 6;
                    case 4: return 1;
                    default: return 0;
                }
            case 8:
                switch (matches) {
                    case 8: return 10_000;
                    case 7: return 750;
                    case 6: return 50;
                    case 5: return 12;
                    case 4: return 2;
                    default: return 0;
                }
            case 7:
                switch (matches) {
                    case 7: return 4500;
                    case 6: return 100;
                    case 5: return 17;
                    case 4: return 3;
                    case 3: return 1;
                    default: return 0;
                }
            case 6:
                switch (matches) {
                    case 6: return 1100;
                    case 5: return 50;
                    case 4: return 8;
                    case 3: return 1;
                    default: return 0;
                }
            case 5:
                switch (matches) {
                    case 5: return 420;
                    case 4: return 18;
                    case 3: return 2;
                    default: return 0;
                }
            case 4:
                switch (matches) {
                    case 4: return 75;
                    case 3: return 5;
                    case 2: return 1;
                    default: return 0;
                }
            case 3:
                switch (matches) {
                    case 3: return 27;
                    case 2: return 2;
                    default: return 0;
                }
            case 2:
                return matches == 2 ? 11 : 0;
            case 1:
                return matches == 1 ? 2 : 0;
            default:
                return 0;
        }
    }

    // Randomly pick `count` unique numbers from 1-80
    public static List<Integer> quickPick(int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 80; i++) numbers.add(i);
        Collections.shuffle(numbers);
        return numbers.subList(0, count);
    }

    // Random 20 numbers drawn by the system
    public static List<Integer> systemDraw() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 80; i++) numbers.add(i);
        Collections.shuffle(numbers);
        return numbers.subList(0, 20);
    }

    // Count how many numbers match between user and system
    public static int countMatches(List<Integer> userNumbers, List<Integer> drawnNumbers) {
        int count = 0;
        HashSet<Integer> drawnSet = new HashSet<>(drawnNumbers);
        for (int n : userNumbers) {
            if (drawnSet.contains(n)) count++;
        }
        return count;
    }
}
