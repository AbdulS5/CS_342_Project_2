import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameLogicTest {

    @Test
    public void testCalculateWinnings1Spot1Match() {
        assertEquals(2, GameLogic.calculateWinnings(1, 1));
    }

    @Test
    public void testCalculateWinnings1Spot0Match() {
        assertEquals(0, GameLogic.calculateWinnings(1, 0));
    }

    @Test
    public void testCalculateWinnings2Spot2Match() {
        assertEquals(11, GameLogic.calculateWinnings(2, 2));
    }

    @Test
    public void testCalculateWinnings2Spot1Match() {
        assertEquals(0, GameLogic.calculateWinnings(2, 1));
    }

    @Test
    public void testCalculateWinnings3Spot3Match() {
        assertEquals(27, GameLogic.calculateWinnings(3, 3));
    }

    @Test
    public void testCalculateWinnings3Spot2Match() {
        assertEquals(2, GameLogic.calculateWinnings(3, 2));
    }

    @Test
    public void testCalculateWinnings3Spot0Match() {
        assertEquals(0, GameLogic.calculateWinnings(3, 0));
    }

    @Test
    public void testCalculateWinnings10Spot10Match() {
        assertEquals(100_000, GameLogic.calculateWinnings(10, 10));
    }

    @Test
    public void testCalculateWinnings10Spot9Match() {
        assertEquals(4250, GameLogic.calculateWinnings(10, 9));
    }

    @Test
    public void testCalculateWinnings10Spot0Match() {
        assertEquals(5, GameLogic.calculateWinnings(10, 0));
    }

    @Test
    public void testCalculateWinningsInvalidSpots() {
        assertEquals(0, GameLogic.calculateWinnings(0, 0));
        assertEquals(0, GameLogic.calculateWinnings(11, 5));
    }

    @Test
    public void testQuickPickSize() {
        List<Integer> pick = GameLogic.quickPick(5);
        assertEquals(5, pick.size());
    }

    @Test
    public void testQuickPickUniqueNumbers() {
        List<Integer> pick = GameLogic.quickPick(10);
        Set<Integer> set = new HashSet<>(pick);
        assertEquals(10, set.size(), "Quick Pick should generate unique numbers");
    }

    @Test
    public void testQuickPickInRange() {
        List<Integer> pick = GameLogic.quickPick(10);
        for (int n : pick) {
            assertTrue(n >= 1 && n <= 80, "Number should be between 1 and 80");
        }
    }

    @Test
    public void testSystemDrawSize() {
        List<Integer> draw = GameLogic.systemDraw();
        assertEquals(20, draw.size());
    }

    @Test
    public void testSystemDrawUniqueNumbers() {
        List<Integer> draw = GameLogic.systemDraw();
        Set<Integer> set = new HashSet<>(draw);
        assertEquals(20, set.size(), "System draw should generate 20 unique numbers");
    }

    @Test
    public void testSystemDrawInRange() {
        List<Integer> draw = GameLogic.systemDraw();
        for (int n : draw) {
            assertTrue(n >= 1 && n <= 80, "Number should be between 1 and 80");
        }
    }

    @Test
    public void testCountMatchesFull() {
        List<Integer> user = List.of(1, 2, 3, 4);
        List<Integer> drawn = List.of(1, 2, 3, 4);
        assertEquals(4, GameLogic.countMatches(user, drawn));
    }

    @Test
    public void testCountMatchesPartial() {
        List<Integer> user = List.of(1, 2, 3, 4);
        List<Integer> drawn = List.of(3, 4, 5, 6);
        assertEquals(2, GameLogic.countMatches(user, drawn));
    }

    @Test
    public void testCountMatchesNone() {
        List<Integer> user = List.of(1, 2, 3, 4);
        List<Integer> drawn = List.of(5, 6, 7, 8);
        assertEquals(0, GameLogic.countMatches(user, drawn));
    }

    @Test
    public void testCountMatchesEmptyUser() {
        List<Integer> user = List.of();
        List<Integer> drawn = List.of(1, 2, 3);
        assertEquals(0, GameLogic.countMatches(user, drawn));
    }

    @Test
    public void testCountMatchesEmptyDrawn() {
        List<Integer> user = List.of(1, 2, 3);
        List<Integer> drawn = List.of();
        assertEquals(0, GameLogic.countMatches(user, drawn));
    }

    @Test
    public void testCountMatchesBothEmpty() {
        List<Integer> user = List.of();
        List<Integer> drawn = List.of();
        assertEquals(0, GameLogic.countMatches(user, drawn));
    }

    @Test
    public void testQuickPickThenCountMatches() {
        List<Integer> user = GameLogic.quickPick(5);
        List<Integer> drawn = GameLogic.systemDraw();
        int matches = GameLogic.countMatches(user, drawn);
        assertTrue(matches >= 0 && matches <= 5);
    }

    @Test
    public void testSystemDrawThenCountMatches() {
        List<Integer> user = List.of(1, 2, 3, 4, 5);
        List<Integer> drawn = GameLogic.systemDraw();
        int matches = GameLogic.countMatches(user, drawn);
        assertTrue(matches >= 0 && matches <= 5);
    }
}
