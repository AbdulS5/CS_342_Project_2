import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class GameLogicTest {

    @Test
    void payout10SpotTable() {
        assertEquals(100000, GameLogic.calculateWinnings(10,10));
        assertEquals(5, GameLogic.calculateWinnings(10,0));
        assertEquals(2, GameLogic.calculateWinnings(10,5));
    }

    @Test
    void payout9SpotTable() {
        assertEquals(30000, GameLogic.calculateWinnings(9,9));
        assertEquals(3000, GameLogic.calculateWinnings(9,8));
        assertEquals(25, GameLogic.calculateWinnings(9,6));
    }

    @Test
    void payout4to1SpotTable() {
        assertEquals(75, GameLogic.calculateWinnings(4,4));
        assertEquals(11, GameLogic.calculateWinnings(2,2));
        assertEquals(2, GameLogic.calculateWinnings(1,1));
    }

    @Test
    void zeroOrInvalid_ReturnsZero() {
        assertEquals(0, GameLogic.calculateWinnings(0,3));
        assertEquals(0, GameLogic.calculateWinnings(11,5));
    }

    @Test
    void winnings_NonNegativeAndWithinBounds() {
        for (int s = 1; s <= 10; s++) {
            for (int m = 0; m <= 10; m++) {
                int val = GameLogic.calculateWinnings(s, m);
                assertTrue(val >= 0);
                assertTrue(val <= 100000);
            }
        }
    }

    @Test
    void drawGeneration_Has20UniqueNumbers() {
        List<Integer> draw = GameLogic.generateDraw();
        assertEquals(20, draw.size());
        assertFalse(GameLogic.hasDuplicates(draw));
        assertTrue(draw.stream().allMatch(n -> n >= 1 && n <= 80));
    }

    @Test
    void payouts_IncreaseWithMatches() {
        assertTrue(GameLogic.calculateWinnings(7,6) >= GameLogic.calculateWinnings(7,5));
        assertTrue(GameLogic.calculateWinnings(8,7) >= GameLogic.calculateWinnings(8,6));
    }

    @Test
    void specialCase_10SpotZeroMatchFiveDollars() {
        assertEquals(5, GameLogic.calculateWinnings(10, 0));
    }

    @Test
    void hasDuplicates_WorksCorrectly() {
        List<Integer> a = Arrays.asList(1,2,3,4,5);
        List<Integer> b = Arrays.asList(1,2,2,3);
        assertFalse(GameLogic.hasDuplicates(a));
        assertTrue(GameLogic.hasDuplicates(b));
    }
}
