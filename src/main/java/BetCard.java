import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class BetCard {

	public static GridPane createButtonTable(int numRows, int numCols, int size) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10)); // Add some padding around the grid
        gridPane.setHgap(5); // Horizontal gap between buttons
        gridPane.setVgap(5); // Vertical gap between buttons

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                Button button = new Button("" + (row * numCols + col + 1));
                button.setPrefSize(size*2, size);
                int finalRow = row;
                int finalCol = col;
                button.setOnAction(event -> {
                    System.out.println("Button number " + (finalCol + 1 + (finalRow*8)) + " clicked!");
                });
                gridPane.add(button, col, row); // Add button to the grid at specified column and row
            }
        }
        return gridPane;
    }

	public static List<Button> getButtonsFromGridPane(GridPane gridPane, ArrayList buttons) {

        for (Node node : gridPane.getChildren()) {
            if (node instanceof Button) {
                buttons.add((Button) node);
            }
        }
        return buttons;
    }
}
