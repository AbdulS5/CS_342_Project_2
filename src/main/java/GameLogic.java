import java.util.*;

public class GameLogic {

    public static int calculateWinnings(int spots, int matches) {
        switch (spots) {
            case 10:
                switch (matches) {
                    case 10: return 100000;
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
                    case 9: return 30000;
                    case 8: return 3000;
                    case 7: return 150;
                    case 6: return 25;
                    case 5: return 6;
                    case 4: return 1;
                    default: return 0;
                }
            case 8:
                switch (matches) {
                    case 8: return 10000;
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
                return (matches == 2) ? 11 : 0;
            case 1:
                return (matches == 1) ? 2 : 0;
            default:
                return 0;
        }
    }

    public static List<Integer> generateDraw() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 80; i++) numbers.add(i);
        Collections.shuffle(numbers);
        return new ArrayList<>(numbers.subList(0, 20));
    }

    public static boolean hasDuplicates(List<Integer> list) {
        return new HashSet<>(list).size() != list.size();
    }
}
