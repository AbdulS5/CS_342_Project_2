import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.util.Duration;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameScreen {

    private Scene gameScreen;
    private int selectedSpots = 0;
    private int selectedDraws = 0;
    private int currentDraw = 0;
    private int totalWinnings = 0;
    private Button pauseBtn;

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
                    b.setStyle("");
                }
                chosenButtons.clear();
            });
        }


        Label drawTitle = new Label("Pick number of drawings");
        GridPane numDraws = BetCard.createButtonTable(1, 4, 20);
        numDraws.setAlignment(Pos.CENTER);

        for (int i = 0; i < numDraws.getChildren().size(); i++) {
            Button drawButton = (Button) numDraws.getChildren().get(i);
            int drawsChosen = i + 1;
            drawButton.setOnAction(e -> {
                selectedDraws = drawsChosen;
                System.out.println("You chose " + selectedDraws + " drawings.");
            });
        }
        
        pauseBtn = new Button("Continue");
        pauseBtn.setDisable(true);

        Button quickPick = new Button("Quick Pick");
        quickPick.setOnAction(e -> {
            if (selectedSpots == 0) {
                System.out.println("Please choose number of spots first!");
                return;
            }

            for (Button b : numberButtons) b.setStyle("");
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
        startDraw.setOnAction(e -> {
            if (chosenButtons.isEmpty() || selectedDraws == 0) {
                System.out.println("Please select your numbers and number of drawings first!");
                return;
            }

            currentDraw = 0;
            totalWinnings = 0;
            doNextDraw();
        });

        VBox gameLayout_top = new VBox(10, drawTitle, numDraws);
        VBox gameLayout_bottom = new VBox(10, spotTitle, chooseSpots);
        gameLayout_top.setAlignment(Pos.CENTER);
        gameLayout_bottom.setAlignment(Pos.CENTER);

        VBox gameLayout = new VBox(20, gameLayout_top, gameLayout_bottom, quickPick, startDraw, pauseBtn);
        gameLayout.setAlignment(Pos.CENTER);

        BorderPane gameRoot = new BorderPane();
        gameRoot.setTop(gameMenuBar);
        gameRoot.setLeft(gameLayout);
        gameRoot.setRight(table);

        gameScreen = new Scene(gameRoot, 1000, 600);

        rulesItem2.setOnAction(e -> mainApp.showRules());
        oddsItem2.setOnAction(e -> mainApp.showOdds());
        backItem.setOnAction(e -> mainApp.showWelcomeScene());
    }


    private void doNextDraw() {
        currentDraw++;

        List<Integer> allNumbers = new ArrayList<>();
        for (int i = 1; i <= 80; i++) allNumbers.add(i);
        Collections.shuffle(allNumbers);
        List<Integer> drawnNumbers = allNumbers.subList(0, 20);

        for (int i = 0; i < numberButtons.size(); i++) {
            Button b = numberButtons.get(i);
            int buttonNumber = i + 1;
            if (drawnNumbers.contains(buttonNumber)) {
                b.setStyle("-fx-background-color: red;");
            }
        }

        int matches = 0;
        for (Button b : chosenButtons) {
            int buttonNumber = Integer.parseInt(b.getText());
            if (drawnNumbers.contains(buttonNumber)) {
                matches++;
                b.setStyle("-fx-background-color: purple;");
            }
        }

        int winnings = calculateWinnings(selectedSpots, matches);
        totalWinnings += winnings;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Keno Results");
        alert.setHeaderText("Drawing " + currentDraw + " of " + selectedDraws);
        alert.setContentText(
            "You matched " + matches + " numbers!\n" +
            "You won $" + winnings + " this round.\n" +
            "Total so far: $" + totalWinnings
        );
        alert.showAndWait();

        if (currentDraw < selectedDraws) {
        	pauseBtn.setDisable(false);
        	pauseBtn.setText("Continue to Draw" + (currentDraw + 1)); 
        	pauseBtn.setOnAction(e -> { 
        		pauseBtn.setDisable(true); 
        		doNextDraw();
        	});
        } else {
            Alert done = new Alert(Alert.AlertType.INFORMATION);
            done.setTitle("Game Over");
            done.setHeaderText("All drawings complete!");
            done.setContentText("You won a total of $" + totalWinnings + "!");
            done.showAndWait();
            pauseBtn.setDisable(true);
            resetTheGame();
           
        }
    }
    
    private int calculateWinnings(int spots, int matches) {
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
                if (matches == 2) return 11;
                else return 0;
            case 1:
                if (matches == 1) return 2;
                else return 0;
            default:
                return 0;
        }
    }
    
    private void resetTheGame() {
    	selectedSpots = 0;
        selectedDraws = 0;
        currentDraw = 0;
        totalWinnings = 0;
        chosenButtons.clear();

        for (Button b : numberButtons) {
            b.setStyle("");
            b.setDisable(true);
        }

        System.out.println("Game has been reset. Please pick spots and drawings again.");
    }

    public Scene getScene() {
        return gameScreen;
    }
}
