import javafx.application.Application;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {

    private Stage primaryStage;
    private WelcomeScreen welcomeScreen;
    private GameScreen gameScreen;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        primaryStage = stage;

        welcomeScreen = new WelcomeScreen(this);
        gameScreen = new GameScreen(this);

        primaryStage.setTitle("Keno Lottery Game");
        primaryStage.setScene(welcomeScreen.getScene());
        primaryStage.show();
    }

    public void showGameScene() {
        primaryStage.setScene(gameScreen.getScene());
    }

    public void showWelcomeScene() {
        primaryStage.setScene(welcomeScreen.getScene());
    }

    public void exitGame() {
        primaryStage.close();
    }

    public void showRules() {
    	SceneRules.showRules();
    }

    public void showOdds() {
        SceneRules.showOdds();
    }
}
