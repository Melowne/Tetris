package org.openjfx.Tetris;





import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * JavaFX App
 */
public class App extends Application {
	Timeline timer;
	private matchfield game;
	private StackPane gamepane;
	private VBox root1;
	private StackPane header;
	private void sizegame() {
		gamepane.prefHeightProperty().bind(root1.heightProperty());
		gamepane.heightProperty().addListener((obs,oldVal,newVal)->{
			header.setMinHeight(root1.getHeight()/10)  ;game.sizePane();
		
		});
	}
    @Override
    public void start(Stage stage) {
		
            root1=new VBox();
		var root=new GridPane();
	     game =new matchfield();
		 gamepane=game.givgame();
		
	    header=new StackPane();var headertxt=new Label("Tetris");
		headertxt.setFont(new Font("Arial",32));
		header.getChildren().add(headertxt);
		root1.getChildren().add(header);
		
		var scorepane=new StackPane();var score =new Label("Score: 0");
		score.setFont(new Font("Arial",24));
		scorepane.getChildren().add(score);
		    timer =new Timeline(new KeyFrame(Duration.millis(50), e->{
			String s="Score: "+game.givscore();
			gamepane.requestFocus();
			if(!game.timerenabled) {root1.requestFocus();
			game.givteimer().stop();timer.stop();
			}
			score.setText(s);
		}));timer.setCycleCount(Animation.INDEFINITE);
		
		var controls=new VBox();
		var controlpane=new StackPane();
		var start=new Button("Starten");
		var ende= new Button("  Ende ");
		
		controls.getChildren().add(start);controls.getChildren().add(ende);
		controlpane.getChildren().add(controls);
		controls.setAlignment(Pos.CENTER);
			
		
		root.add(controlpane, 0, 1);
		root.add(gamepane, 1, 1);
		root.add(scorepane, 2, 1);
		
		root1.getChildren().add(root);
		
		Scene scene = new Scene(root1,650,650);
		
	
		controlpane.prefWidthProperty().bind(root.widthProperty());
		scorepane.prefWidthProperty().bind(root.widthProperty());
		header.prefWidthProperty().bind(root.widthProperty());
		controls.setFocusTraversable(true);
	
		
		sizegame();

		
		start.setOnMouseClicked(e->{
			root.getChildren().remove(gamepane);
			
			game=new matchfield();
			gamepane=game.givgame();
			root.add(gamepane, 1, 1);
			sizegame();
			game.givteimer().play();	
			game.timerenabled=true;
			timer.play();
		});
		ende.setOnMouseClicked(e->{
			game=new matchfield();
			gamepane=game.givgame();
			root.add(gamepane, 1, 1);
			sizegame();
			game.givteimer().stop();
			timer.stop();
		});
	
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }

}