import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;


public class JavaFXTemplate extends Application {

    Button b1;
    TextField t1;
    VBox root;
    HBox bbox;
    EventHandler<ActionEvent> myhandler;
    MenuBar menu;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Welcome to JavaFX");
		
		 //Rectangle rect = new Rectangle (100, 40, 100, 100);
	     //rect.setArcHeight(50);
	     //rect.setArcWidth(50);
	     //rect.setFill(Color.VIOLET);

	     //RotateTransition rt = new RotateTransition(Duration.millis(5000), rect);
	     //rt.setByAngle(270);
	     //rt.setCycleCount(4);
	     //rt.setAutoReverse(true);
	     //SequentialTransition seqTransition = new SequentialTransition (
	         //new PauseTransition(Duration.millis(500)),
	         //rt
	     //);
	     //seqTransition.play();
	     
	     //FadeTransition ft = new FadeTransition(Duration.millis(5000), rect);
	     //ft.setFromValue(1.0);
	     //ft.setToValue(0.3);
	     //ft.setCycleCount(4);
	     //ft.setAutoReverse(true);

	     //ft.play();
	     //BorderPane root = new BorderPane();
	     //root.setCenter(rect);

        b1 = new Button("Start");
        b1.setMinWidth(100);

        t1 = new TextField();

        Region r1 = new Region();
        r1.setMinWidth(30);

        //b2.setOnAction(new EventHandler<ActionEvent>() {
        //    public void handle(ActionEvent e) {
        //        t1.clear();
        //    }
        //});

        b1.setOnAction(e->t1.setText("I love lambda expressions!!!"));

        menu = new MenuBar();
        Menu mOne = new Menu("Menu");

        MenuItem item = new MenuItem("click me");
        item.setOnAction(e->t1.setText("item was clicked"));
        mOne.getItems().add(item);

        menu.getMenus().addAll(mOne);

        bbox = new HBox(b1,r1);
        root = new VBox(menu, bbox, t1);

        Scene scene = new Scene(root, 700,700);
			primaryStage.setScene(scene);
			primaryStage.show();
		
				
		
	}

}
