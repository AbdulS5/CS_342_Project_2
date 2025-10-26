import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameScreen {

    private Scene gameScreen;

    public GameScreen(JavaFXTemplate mainApp) {
    	
        MenuBar gameMenuBar = new MenuBar();
        Menu gameMenu = new Menu("Menu");
        MenuItem rulesItem2 = new MenuItem("Rules of the Game");
        MenuItem oddsItem2 = new MenuItem("Odds of Winning");
        MenuItem backItem = new MenuItem("Back to Menu");
        gameMenu.getItems().addAll(rulesItem2, oddsItem2, new SeparatorMenuItem(), backItem);
        gameMenuBar.getMenus().add(gameMenu);
        
        GridPane table = BetCard.createButtonTable(10, 8, 30);

        Label spotTitle = new Label("Pick number of spots");
        GridPane chooseSpots = BetCard.createButtonTable(1,10, 20);
        chooseSpots.setAlignment(Pos.CENTER);
        
        Label drawTitle = new Label("Pick number of drawings");
        GridPane numDraws = BetCard.createButtonTable(1,4, 20);
        numDraws.setAlignment(Pos.CENTER);
        
        VBox gameLayout_top = new VBox(10, drawTitle, numDraws);
        VBox gameLayout_bottom = new VBox(10, spotTitle, chooseSpots);
        gameLayout_top.setAlignment(Pos.CENTER);
        gameLayout_bottom.setAlignment(Pos.CENTER);

        Button quickPick = new Button("Quick Pick");
        quickPick.setOnAction(e -> {System.out.println("Quick Pick clicked!");});
        Button startDraw = new Button("Start Drawing");
        startDraw.setOnAction(e -> {System.out.println("Start Draw clicked!");});

        VBox gameLayout = new VBox(20, gameLayout_top, gameLayout_bottom, quickPick, startDraw);
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

    public Scene getScene() {
        return gameScreen;
    }
}
