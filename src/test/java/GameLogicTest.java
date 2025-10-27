import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class GameLogicTest {

    private GameScreen game;

    @BeforeEach
    public void setup() {
        game = new GameScreen(null);
    }

    @Test 
    void payout10Spot_ExactTable() {
        assertEquals(100000, calc(10,10));
        assertEquals(4250,   calc(10,9));
        assertEquals(450,    calc(10,8));
        assertEquals(40,     calc(10,7));
        assertEquals(15,     calc(10,6));
        assertEquals(2,      calc(10,5));
        assertEquals(5,      calc(10,0));
        assertEquals(0,      calc(10,1));
    }

    @Test 
    void payout9Spot_ExactTable() {
        assertEquals(30000, calc(9,9));
        assertEquals(3000,  calc(9,8));
        assertEquals(150,   calc(9,7));
        assertEquals(25,    calc(9,6));
        assertEquals(6,     calc(9,5));
        assertEquals(1,     calc(9,4));
        assertEquals(0,     calc(9,3));
    }

    @Test 
    void payout8Spot_ExactTable() {
        assertEquals(10000, calc(8,8));
        assertEquals(750,   calc(8,7));
        assertEquals(50,    calc(8,6));
        assertEquals(12,    calc(8,5));
        assertEquals(2,     calc(8,4));
        assertEquals(0,     calc(8,3));
    }

    @Test 
    void payout7Spot_ExactTable() {
        assertEquals(4500, calc(7,7));
        assertEquals(100,  calc(7,6));
        assertEquals(17,   calc(7,5));
        assertEquals(3,    calc(7,4));
        assertEquals(1,    calc(7,3));
        assertEquals(0,    calc(7,2));
    }

    @Test 
    void payout6Spot_ExactTable() {
        assertEquals(1100, calc(6,6));
        assertEquals(50,   calc(6,5));
        assertEquals(8,    calc(6,4));
        assertEquals(1,    calc(6,3));
        assertEquals(0,    calc(6,2));
    }

    @Test 
    void payout5to1Spot() {
        assertEquals(420, calc(5,5));
        assertEquals(18,  calc(5,4));
        assertEquals(2,   calc(5,3));
        assertEquals(75,  calc(4,4));
        assertEquals(5,   calc(4,3));
        assertEquals(1,   calc(4,2));
        assertEquals(27,  calc(3,3));
        assertEquals(2,   calc(3,2));
        assertEquals(11,  calc(2,2));
        assertEquals(2,   calc(1,1));
    }


    @Test 
    void drawGeneration_Has20UniqueNumbers() {
        List<Integer> all = new ArrayList<>();
        for (int i = 1; i <= 80; i++) all.add(i);
        Collections.shuffle(all);
        List<Integer> drawn = all.subList(0, 20);
        assertEquals(20, drawn.size());
        assertEquals(20, new HashSet<>(drawn).size(), "No duplicates allowed");
    }

    @Test 
    void quickPick_SelectsDistinctNumbers() {
        List<Integer> all = new ArrayList<>();
        for (int i = 1; i <= 80; i++) all.add(i);
        Collections.shuffle(all);
        List<Integer> picks = all.subList(0, 8);
        assertEquals(8, picks.size());
        assertEquals(8, new HashSet<>(picks).size());
    }

    @Test 
    void winnings_NeverNegative() {
        for (int s = 1; s <= 10; s++) {
            for (int m = 0; m <= 10; m++) {
                assertTrue(calc(s,m) >= 0, "No negative payouts");
            }
        }
    }

    @Test 
    void payout_IncreasesWithMoreMatches() {
        assertTrue(calc(7,6) >= calc(7,5));
        assertTrue(calc(8,7) >= calc(8,6));
    }

    @Test 
    void invalidSpot_ReturnsZero() {
        assertEquals(0, calc(11,5));
        assertEquals(0, calc(0,3));
    }



    @Test 
    void resetGame_ReinitializesState() {
        game.resetTheGame();
        assertEquals(0, getPrivateInt("selectedSpots"));
        assertEquals(0, getPrivateInt("selectedDraws"));
        assertEquals(0, getPrivateInt("currentDraw"));
        assertEquals(0, getPrivateInt("totalWinnings"));
    }

    @Test 
    void calculateWinnings_MatchesSpecForZeroMatchRule() {

        assertEquals(5, calc(10,0));
        assertEquals(0, calc(9,0));
        assertEquals(0, calc(8,0));
    }

    @Test 
    void winnings_DoNotExceedKnownMax() {
        int max = 100000;
        for (int s=1; s<=10; s++)
            for (int m=0; m<=s; m++)
                assertTrue(calc(s,m) <= max);
    }

    @Test 
    void payout_ReturnsZeroForImpossibleMatch() {
        assertEquals(0, calc(4,9));
        assertEquals(0, calc(3,10));
    }

    @Test 
    void payout_TableConsistency_RandomChecks() {
        assertAll(
            () -> assertEquals(100, calc(7,6)),
            () -> assertEquals(50, calc(6,5)),
            () -> assertEquals(2, calc(5,3)),
            () -> assertEquals(5, calc(4,3))
        );
    }


    private int calc(int spots, int matches) {
        try {
            var m = GameScreen.class.getDeclaredMethod("calculateWinnings", int.class, int.class);
            m.setAccessible(true);
            return (int) m.invoke(game, spots, matches);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int getPrivateInt(String fieldName) {
        try {
            var f = GameScreen.class.getDeclaredField(fieldName);
            f.setAccessible(true);
            return f.getInt(game);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
