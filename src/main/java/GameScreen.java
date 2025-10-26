import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameScreen {

    private Scene gameScreen;
    private int selectedSpots = 0;
    private List<Button> numberButtons;
    private List<Button> chosenButtons;
    
    private void handleNumberClick(Button b) {
        if (b.isDisabled()) return;

        if (chosenButtons.contains(b)) {
            b.setStyle("");
            chosenButtons.remove(b);
        } 
        else if (chosenButtons.size() < selectedSpots) {
            b.setStyle("-fx-background-color: blue;");
            chosenButtons.add(b);
        } 
        else {
            System.out.println("You can only select " + selectedSpots + " numbers.");
        }
    }

    public GameScreen(JavaFXTemplate mainApp) {
    	
        MenuBar gameMenuBar = new MenuBar();
        Menu gameMenu = new Menu("Menu");
        MenuItem rulesItem2 = new MenuItem("Rules of the Game");
        MenuItem oddsItem2 = new MenuItem("Odds of Winning");
        MenuItem backItem = new MenuItem("Back to Menu");
        gameMenu.getItems().addAll(rulesItem2, oddsItem2, new SeparatorMenuItem(), backItem);
        gameMenuBar.getMenus().add(gameMenu);
        
        GridPane table = BetCard.createButtonTable(10, 8, 30);
        
        numberButtons = new ArrayList<>();
        BetCard.getButtonsFromGridPane(table, (ArrayList) numberButtons);
        for (Button b : numberButtons) {
            b.setDisable(true);
            b.setOnAction(e -> handleNumberClick(b));
        }
        chosenButtons = new ArrayList<>();
     

        Label spotTitle = new Label("Pick number of spots");
        GridPane chooseSpots = BetCard.createButtonTable(1, 10, 20);
        chooseSpots.setAlignment(Pos.CENTER);
        
        for (int i = 0; i < chooseSpots.getChildren().size(); i++) {
            Button spotButton = (Button) chooseSpots.getChildren().get(i);
            int chosenNum = i + 1;

            spotButton.setOnAction(e -> {
                selectedSpots = chosenNum;
                System.out.println("You chose " + selectedSpots + " spots.");

                for (Button b : numberButtons) {
                    b.setDisable(false);
                }

                for (Button b : numberButtons) {
                    b.setStyle("");
                }
                chosenButtons.clear();
            });
        }

        Label drawTitle = new Label("Pick number of drawings");
        GridPane numDraws = BetCard.createButtonTable(1, 4, 20);
        numDraws.setAlignment(Pos.CENTER);

        VBox gameLayout_top = new VBox(10, drawTitle, numDraws);
        VBox gameLayout_bottom = new VBox(10, spotTitle, chooseSpots);
        gameLayout_top.setAlignment(Pos.CENTER);
        gameLayout_bottom.setAlignment(Pos.CENTER);

        Button quickPick = new Button("Quick Pick");
        quickPick.setOnAction(e -> {
            if (selectedSpots == 0) {
                System.out.println("Please choose number of spots first!");
                return;
            }

            for (Button b : numberButtons) {
                b.setStyle("");
            }
            chosenButtons.clear();

 
            List<Button> random = new ArrayList<>(numberButtons);
            Collections.shuffle(random);

            for (int i = 0; i < selectedSpots; i++) {
                Button b = random.get(i);
                b.setStyle("-fx-background-color: blue;");
                chosenButtons.add(b);
            }

            System.out.println("Quick Pick chose " + selectedSpots + " numbers.");
        });
        
        Button startDraw = new Button("Start Drawing");

        VBox gameLayout = new VBox(20, gameLayout_top, gameLayout_bottom, quickPick, startDraw);
        gameLayout.setAlignment(Pos.CENTER);

        BorderPane gameRoot = new BorderPane();
        gameRoot.setTop(gameMenuBar);
        gameRoot.setLeft(gameLayout);
        gameRoot.setRight(table);

        gameScreen = new Scene(gameRoot, 1000, 600);

        // ---- Menu actions ----
        rulesItem2.setOnAction(e -> mainApp.showRules());
        oddsItem2.setOnAction(e -> mainApp.showOdds());
        backItem.setOnAction(e -> mainApp.showWelcomeScene());
    }

    public Scene getScene() {
        return gameScreen;
    }
}
