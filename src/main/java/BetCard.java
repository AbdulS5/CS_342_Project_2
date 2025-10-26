import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BetCard {

	public static GridPane createButtonTable(int numRows, int numCols) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10)); // Add some padding around the grid
        gridPane.setHgap(5); // Horizontal gap between buttons
        gridPane.setVgap(5); // Vertical gap between buttons

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Button button = new Button("" + (row * numCols + col + 1));
                // Optional: Add an action handler to each button
                int finalRow = row;
                int finalCol = col;
                button.setOnAction(event -> {
                    System.out.println("Button at (" + finalRow + ", " + finalCol + ") clicked!");
                });
                gridPane.add(button, col, row); // Add button to the grid at specified column and row
            }
        }
        return gridPane;
    }
}
