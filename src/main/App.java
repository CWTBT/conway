package main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class App extends Application {
	
	GridPane root = new GridPane();
	Grid  gameBoard = new Grid(50, 50);
	boolean playing = false;
	
	private Parent createPane() {
		root.setPrefSize(600,600);
		drawBoard();
		return root;
	}
	
	private void drawBoard() {
		root.getChildren().clear();
		for(int i = 0; i < 50; i++) {
			for(int j = 0; j < 50; j++) {
				Rectangle rec = new Rectangle(12, 12);
				if (gameBoard.getCellVal(j, i)) rec.setFill(Color.DARKBLUE);
				else rec.setFill(Color.ALICEBLUE);
				rec.setStroke(Color.BLACK);
				rec.setTranslateX(j * 12);
				rec.setTranslateY(i * 12);
				
				rec.setOnMousePressed(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent me) {
						swapFill(rec);
						gameBoard.switchCell((int)me.getSceneX()/12, (int)me.getSceneY()/12);
					}
				});
				
				root.getChildren().add(rec);
			}
		}
		
	}
	
	private void swapFill(Rectangle r ) {
		if (r.getFill() == Color.ALICEBLUE) r.setFill(Color.DARKBLUE);
		else r.setFill(Color.ALICEBLUE);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene s = new Scene(createPane());
		primaryStage.setScene(s);
		primaryStage.setTitle("Foo");
		primaryStage.setResizable(false);
		primaryStage.show();
		s.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.SPACE) {
					gameBoard.update();
					drawBoard();
				}
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}