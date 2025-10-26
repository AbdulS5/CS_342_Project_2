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

        BorderPane gameRoot = new BorderPane();
        gameRoot.setTop(gameMenuBar);
        gameRoot.setCenter(gameLayout);

        GridPane table = BetCard.createButtonTable(10, 8);
        gameRoot.setRight(table);

        scene = new Scene(gameRoot, 800, 600);

        rulesItem2.setOnAction(e -> mainApp.showRules());
        oddsItem2.setOnAction(e -> mainApp.showOdds());
        backItem.setOnAction(e -> mainApp.showWelcomeScene());
    }

    public Scene getScene() {
        return scene;
    }
}
