import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class JavaFXTemplate extends Application {

    private Stage primaryStage;
    private Scene gameScreen;
    private Scene welcomeScreen;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        primaryStage = stage;
        stage.setTitle("Keno Lottery Game");

        MenuBar welcomeMenuBar = new MenuBar();
        Menu welcomeMenu = new Menu("Menu");
        MenuItem rulesItem1 = new MenuItem("Rules of the Game");
        MenuItem oddsItem1 = new MenuItem("Odds of Winning");
        MenuItem exitItem = new MenuItem("Exit Game");
        welcomeMenu.getItems().addAll(rulesItem1, oddsItem1, new SeparatorMenuItem(), exitItem);
        welcomeMenuBar.getMenus().add(welcomeMenu);

        MenuBar gameMenuBar = new MenuBar();
        Menu gameMenu = new Menu("Menu");
        MenuItem rulesItem2 = new MenuItem("Rules of the Game");
        MenuItem oddsItem2 = new MenuItem("Odds of Winning");
        MenuItem backItem = new MenuItem("Back to Menu");
        gameMenu.getItems().addAll(rulesItem2, oddsItem2, new SeparatorMenuItem(), backItem);
        gameMenuBar.getMenus().add(gameMenu);

        Label title = new Label("Welcome to KENO");
        Button startBtn = new Button("Start Game");
        VBox welcomeSc = new VBox(20, title, startBtn);
        welcomeSc.setAlignment(Pos.CENTER);
        BorderPane welcomeRoot = new BorderPane();
        welcomeRoot.setTop(welcomeMenuBar);
        welcomeRoot.setCenter(welcomeSc);
        welcomeScreen = new Scene(welcomeRoot, 800, 600);
        
        Label gameTitle = new Label("KENO GAME SCREEN");
        VBox gameLayout = new VBox(20, gameTitle);
        gameLayout.setAlignment(Pos.CENTER);
        BorderPane gameRoot = new BorderPane();
        gameRoot.setTop(gameMenuBar);
        gameRoot.setCenter(gameLayout);
        gameScreen = new Scene(gameRoot, 800, 600);
        
        startBtn.setOnAction(e -> primaryStage.setScene(gameScreen));
        rulesItem1.setOnAction(e -> showRules());
        oddsItem1.setOnAction(e -> showOdds());
        exitItem.setOnAction(e -> primaryStage.close());

        rulesItem2.setOnAction(e -> showRules());
        oddsItem2.setOnAction(e -> showOdds());
        backItem.setOnAction(e -> primaryStage.setScene(welcomeScreen));
        
        primaryStage.setScene(welcomeScreen);
        primaryStage.show();
        

    }

    private void showRules() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Rules of Keno");
        alert.setHeaderText("Keno Lottery Game Rules");
        TextArea textArea = new TextArea("Keno is a fast-paced lottery draw-style game that's easy to play. For each Keno drawing, 20 numbers out of 80 will be selected as winning numbers. You can decide how many of these numbers (called Spots) and exactly which numbers you will try to match.\n\n" +
                "   1. Select how many consecutive draws to play. Pick up to 4.\n\n" +
                "   2. Select how many numbers to match from 1 to 10. In Keno, these are called Spots. The number of Spots you choose and the amount you play per draw will determine the amount you could win. (See the Odds of winning)\n\n" +
                "   3. Pick as many numbers as you did Spots. You can select numbers from 1 to 80 or choose Quick Pick and let the computer terminal randomly pick some or all of these numbers for you.\n");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefRowCount(10);
        textArea.setPrefColumnCount(40);

        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }
    
    private void showOdds() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Odds of Winning");
        alert.setHeaderText("Keno Odds Table");
        TextArea textArea = new TextArea(
                "10 Spot Game\n" +
                "Match          Prize\n" +
                "10         $100,000*\n" +
                "9             $4,250\n" +
                "8               $450\n" +
                "7                $40\n" +
                "6                $15\n" +
                "5                 $2\n" +
                "0                 $5\n" +
                "Overall Odds: 1 in 9.05\n" +
                "\n" +
                "\n" +
                "9 Spot Game\n" +
                "Match          Prize\n" +
                "9           $30,000*\n" +
                "8             $3,000\n" +
                "7               $150\n" +
                "6                $25\n" +
                "5                 $6\n" +
                "4                 $1\n" +
                "Overall Odds: 1 in 6.53\n" +
                "\n" +
                "\n" +
                "8 Spot Game\n" +
                "Match          Prize\n" +
                "8           $10,000*\n" +
                "7               $750\n" +
                "6                $50\n" +
                "5                $12\n" +
                "4                 $2\n" +
                "Overall Odds: 1 in 9.77\n" +
                "\n" +
                "\n" +
                "7 Spot Game\n" +
                "Match          Prize\n" +
                "7             $4,500\n" +
                "6               $100\n" +
                "5                $17\n" +
                "4                 $3\n" +
                "3                 $1\n" +
                "Overall Odds: 1 in 4.23\n" +
                "\n" +
                "\n" +
                "6 Spot Game\n" +
                "Match          Prize\n" +
                "6             $1,100\n" +
                "5                $50\n" +
                "4                 $8\n" +
                "3                 $1\n" +
                "Overall Odds: 1 in 6.19\n" +
                "\n" +
                "\n" +
                "5 Spot Game\n" +
                "Match          Prize\n" +
                "5               $420\n" +
                "4                $18\n" +
                "3                 $2\n" +
                "Overall Odds: 1 in 10.34\n" +
                "\n" +
                "\n" +
                "4 Spot Game\n" +
                "Match          Prize\n" +
                "4                $75\n" +
                "3                 $5\n" +
                "2                 $1\n" +
                "Overall Odds: 1 in 3.86\n" +
                "\n" +
                "\n" +
                "3 Spot Game\n" +
                "Match          Prize\n" +
                "3                $27\n" +
                "2                 $2\n" +
                "Overall Odds: 1 in 6.55\n" +
                "\n" +
                "\n" +
                "2 Spot Game\n" +
                "Match          Prize\n" +
                "2                $11\n" +
                "Overall Odds: 1 in 16.63\n" +
                "\n" +
                "\n" +
                "1 Spot Game\n" +
                "Match          Prize\n" +
                "1                 $2\n" +
                "Overall Odds: 1 in 4.00\n" +
                "\n");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefRowCount(10);
        textArea.setPrefColumnCount(40);

        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }
}
