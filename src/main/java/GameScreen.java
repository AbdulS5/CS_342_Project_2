import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameScreen {

    private Scene scene;

    public GameScreen(JavaFXTemplate mainApp) {
        MenuBar gameMenuBar = new MenuBar();
        Menu gameMenu = new Menu("Menu");
        MenuItem rulesItem2 = new MenuItem("Rules of the Game");
        MenuItem oddsItem2 = new MenuItem("Odds of Winning");
        MenuItem backItem = new MenuItem("Back to Menu");
        gameMenu.getItems().addAll(rulesItem2, oddsItem2, new SeparatorMenuItem(), backItem);
        gameMenuBar.getMenus().add(gameMenu);

        Label gameTitle = new Label("KENO GAME SCREEN");
        VBox gameLayout = new VBox(20, gameTitle);
        gameLayout.setAlignment(Pos.CENTER);

       Label spotTitle = new Label("Pick number of spots");
        GridPane chooseSpots = createButtonTable(1,10, 20);
        chooseSpots.setAlignment(Pos.CENTER);
        Label drawTitle = new Label("Pick number of drawings");
        GridPane numDraws = createButtonTable(1,4, 20);
        numDraws.setAlignment(Pos.CENTER);
        VBox gameLayout_top = new VBox(10, drawTitle, numDraws);
        VBox gameLayout_bottom = new VBox(10, spotTitle,chooseSpots);
        gameLayout_top.setAlignment(Pos.CENTER);
        gameLayout_bottom.setAlignment(Pos.CENTER);


        VBox gameLayout =  new VBox(20, gameLayout_top, gameLayout_bottom);
        gameLayout.setAlignment(Pos.CENTER);
        BorderPane gameRoot = new BorderPane();
        gameRoot.setTop(gameMenuBar);
        gameRoot.setLeft(gameLayout);
        GridPane table = createButtonTable(10,8, 30);
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
