import java.util.ArrayList;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * A game Bundle consisting of 3 games.
 * A game variant of Are you Smarter than a Fifth Grader?
 * A game variant of Plants vs Zombies.
 * A game variant of brick Breaker.
 * The main class of the game.
 * @authors Lester Lascano, John Albert Castillo, William Benedict Dy
 *
 */
public class GameBundle extends Application{

	Stage stage;					// The stage of the game
	Group window = new Group();		// The window of the game
	ArrayList<Button> games;		// The list of buttons for each game and the background
	Music music = new Music();
	
	AreYouSmarterThanBill game1 = new AreYouSmarterThanBill();	// first game
	WindowsVsLinux game2 = new WindowsVsLinux(window);			// second game
	OSBreaker game3 = new OSBreaker();							// third game

	// main function
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Creates the window and start the game
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		stage = primaryStage;
		stage.setTitle("Game Bundle");

		chooseGame();
		
		Scene viewIntroduction = new Scene(window, 800, 450);
		stage.setScene(viewIntroduction);

		stage.setResizable(false);
		stage.show();
	}
	
	/**
	 * Handles the choosing of games window.
	 */
	public void chooseGame() {
	
		music.playBundleBackground();
		
		Button background = new Button();
		background.setPrefSize(800, 450);
		background.setDisable(true);
		background.setOpacity(1);
		background.setStyle("-fx-background-image: url('file:images/BundleBackground.png')");
	
		
		// create buttons for each game and the about section
		Button game1 = new Button("Are You Smarter Than Bill?");		//Are you smarter than Bill
		Button game2 = new Button("Windows vs Linux");				//Windows vs Linux
		Button game3 = new Button("OS Breaker");					//OS Breaker
		Button about = new Button("About");
		
		// create an ArrayList of buttons and add the buttons 
		games = new ArrayList<Button>();
		games.add(background);
		games.add(game1);
		games.add(game2);
		games.add(game3);
		games.add(about);
		
		
		// set the sized of the buttons
		game1.setPrefSize(300, 50);
		game2.setPrefSize(300, 50);
		game3.setPrefSize(300, 50);
		about.setPrefSize(300, 50);
		
		// set text styles
		game1.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		game2.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		game3.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		about.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
		game1.setTextFill(Color.WHITE);
		game2.setTextFill(Color.WHITE);
		game3.setTextFill(Color.WHITE);
		about.setTextFill(Color.WHITE);
		game1.setBackground(null);
		game2.setBackground(null);
		game3.setBackground(null);
		about.setBackground(null);
		game1.setAlignment(Pos.BASELINE_LEFT);
		game2.setAlignment(Pos.BASELINE_LEFT);
		game3.setAlignment(Pos.BASELINE_LEFT);
		about.setAlignment(Pos.BASELINE_LEFT);
		
		
		// action for the 3 game buttons, basically goes into the pressed game
		game1.setOnAction(e->{
			music.stopBundleBackground();
			this.game1.menu(window, games);
		});
		game2.setOnAction(e->{
			music.stopBundleBackground();
			this.game2.menu(window, games);
		});
		game3.setOnAction(e->{
			music.stopBundleBackground();
			this.game3.menu(window, games);
		});
		
		// transitions
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(new Duration(500));
		transition.setFromX(0);
		transition.setToX(30);
		
		// hover effects 
		game1.setOnMouseEntered(e-> {
			music.playHover();
			game1.setStyle("-fx-background-color: rgba(255, 255, 0, 0.7);");
			background.setStyle("-fx-background-image: url('file:images/game1.png')");
			transition.setNode(game1);
			transition.play();
		});
		game1.setOnMouseExited(e-> {
			game1.setStyle("-fx-background-color: none;");
			transition.stop();
			game1.setTranslateX(0);
		});
		
		game2.setOnMouseEntered(e-> {
			music.playHover();
			game2.setStyle("-fx-background-color: rgba(255, 255, 0, 0.7);");
			background.setStyle("-fx-background-image: url('file:images/game2.png')");
			transition.setNode(game2);
			transition.play();
		});
		game2.setOnMouseExited(e-> {
			game2.setStyle("-fx-background-color: none;");
			transition.stop();
			game2.setTranslateX(0);
		});
		
		game3.setOnMouseEntered(e-> {
			music.playHover();
			game3.setStyle("-fx-background-color: rgba(255, 255, 0, 0.7);");
			background.setStyle("-fx-background-image: url('file:images/game3.png')");
			transition.setNode(game3);
			transition.play();
		});
		game3.setOnMouseExited(e-> {
			game3.setStyle("-fx-background-color: none;");
			transition.stop();
			game3.setTranslateX(0);
		});
		
		about.setOnMouseEntered(e-> {
			music.playHover();
			about.setStyle("-fx-background-color: rgba(255, 255, 0, 0.7);");
			background.setStyle("-fx-background-image: url('file:images/BundleBackground.png')");
			transition.setNode(about);
			transition.play();
		});
		about.setOnMouseExited(e-> {
			about.setStyle("-fx-background-color: none;");
			transition.stop();
			about.setTranslateX(0);
		});
		
		// Layout
		
		game1.setLayoutX(20);
		game1.setLayoutY(90);
		
		game2.setLayoutX(20);
		game2.setLayoutY(160);
		
		game3.setLayoutX(20);
		game3.setLayoutY(230);
		
		about.setLayoutX(20);
		about.setLayoutY(300);
		
		// add the buttons to the window to display
		window.getChildren().addAll(background, game1, game2, game3, about);	
	}
}
