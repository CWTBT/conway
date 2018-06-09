package main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class App extends Application {
	
	private Parent createPane() {
		GridPane root = new GridPane();
		root.setPrefSize(600,600);
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 50; j++) {
				Rectangle rec = new Rectangle(12, 12, Color.ALICEBLUE);
				rec.setStroke(Color.BLACK);
				rec.setTranslateX(j * 12);
				rec.setTranslateY(i * 12);
				
				root.getChildren().add(rec);
			}
		}
		
		return root;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(createPane()));
		primaryStage.setTitle("Foo");
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}