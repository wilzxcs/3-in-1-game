import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * The first game of the Game Bundle.
 * A game variant of Are you smarter than a 5th grader?
 * @see Game Bundle
 */
public class AreYouSmarterThanBill  {

	private Music music = new Music();				// The music of the game
	private Questionnaire questionnaire;			// The questionnaire
	private Questions questions = new Questions();	// The questions
	
	private VBox lifelines;							// A column for the lifelines in the questionnaire 
	private Button proceedButton;					// The proceed button for Are you smarter than bill game
	
	private int currentQuestion = 0;				// The index of the current question
	private boolean halfUsed = false;				// A boolean if the lifeline 'half' is used
	private boolean skipUsed = false;				// A boolean if the lifeline 'skip' is used
	private boolean chanceUsed = false;				// A boolean if the lifeline 'chance' is used
	
	private int howToPlayCounter = 1;				// The index counter for the howToPlay slides
	
	public AreYouSmarterThanBill() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Handles the menu window of the Are You Smarter Than Bill Game.
	 * @param window The window of the game.
	 * @param games	The List of games.
	 */
	public void menu(Group window, List<Button> games) {
		
		music.playGame1Background();
		
		ImageView background = new ImageView(new Image("file:images/game1/game1Background.jpg"));
		
		Button playButton = new Button("Play");
		Button howToPlayButton = new Button("How to Play");
		Button quitButton = new Button("Quit");
		Button musicButton = new Button();
		
		playButton.setPrefSize(400, 70);
		playButton.setBackground(null);
		playButton.setTextFill(Color.WHITE);
		playButton.setFont(Font.font("Arial Black", 35));
		playButton.setTextAlignment(TextAlignment.CENTER);
		
		howToPlayButton.setPrefSize(400, 70);
		howToPlayButton.setBackground(null);
		howToPlayButton.setTextFill(Color.WHITE);
		howToPlayButton.setFont(Font.font("Arial Black", 35));
		howToPlayButton.setTextAlignment(TextAlignment.CENTER);
		howToPlayButton.setPrefSize(400, 70);
		
		quitButton.setPrefSize(400, 70);
		quitButton.setBackground(null);
		quitButton.setTextFill(Color.WHITE);
		quitButton.setFont(Font.font("Arial Black", 35));
		quitButton.setTextAlignment(TextAlignment.CENTER);
		quitButton.setPrefSize(400, 70);
		
		musicButton.setPrefSize(50, 50);
		musicButton.setLayoutX(10);
		musicButton.setLayoutY(390);
		if(music.getMusic()) {
			musicButton.setStyle("-fx-background-image: url('file:images/music.png');"
				+ "-fx-background-color: none;");
		}else {
			musicButton.setStyle("-fx-background-image: url('file:images/musicOff.png');"
					+ "-fx-background-color: none;");
		}
		
		
		// Actions
		playButton.setOnAction(e-> {
			music.playClick();
			play(window, games);
		});
		howToPlayButton.setOnAction(e-> {
			music.playClick();
			howToPlay(window);
		});
		quitButton.setOnAction(e-> {
			music.playClick();
			quit(window, games);
		});
		
		musicButton.setOnAction(e->{
			if(music.getMusic()) {
				music.toggleMusic();
				music.stopGame1Background();
				musicButton.setStyle("-fx-background-image: url('file:images/musicOff.png');"
						+ "-fx-background-color: none;");
			}else {
				music.toggleMusic();
				music.playGame1Background();
				musicButton.setStyle("-fx-background-image: url('file:images/music.png');"
						+ "-fx-background-color: none;");
			}
		});
		
		// Hover Effects
		playButton.setOnMouseEntered(e-> {
			playButton.setStyle("-fx-background-color: grey");
		});
		playButton.setOnMouseExited(e-> {
			playButton.setStyle("-fx-background-color: none");
		});
		
		howToPlayButton.setOnMouseEntered(e-> {
			howToPlayButton.setStyle("-fx-background-color: grey");
		});
		howToPlayButton.setOnMouseExited(e-> {
			howToPlayButton.setStyle("-fx-background-color: none");
		});
		
		quitButton.setOnMouseEntered(e-> {
			quitButton.setStyle("-fx-background-color: grey");
		});
		quitButton.setOnMouseExited(e-> {
			quitButton.setStyle("-fx-background-color: none");
		});
		
		//Layout 
		VBox vbox = new VBox(5);
		vbox.setLayoutX(190);
		vbox.setLayoutY(100);
		vbox.getChildren().addAll(playButton, howToPlayButton, quitButton);
		
		window.getChildren().setAll(background, vbox, musicButton);
	}
	
	
	/**
	 * handles the play window of the game.
	 * @param window The window of the game.
	 * @param games	The List of games.
	 */
	public void play(Group window, List<Button> games) {
		
		// reset the lifelines
		halfUsed = false;
		skipUsed = false;
		chanceUsed = false;
		
		// sets/create a new set of questions
		setQuestions(window, games);
		
	}
	
	
	/**
	 * Handles the how to play window of the game.
	 * @param window The window of the game
	 */
	public void howToPlay(Group window) {
		
		ImageView background = new ImageView(new Image("file:images/game1/HowToPlay/howToPlay1.jpg"));
		
		Button next = new Button(); 
		Button back = new Button(); 	
		
		next.setPrefSize(100, 450);
		next.setLayoutX(700);
		next.setLayoutY(0);
		next.setStyle("-fx-background-image: url(file:images/game1/right.png);"
				+ "-fx-opacity: 0;");
		
		back.setPrefSize(100, 450);
		back.setLayoutX(0);
		back.setLayoutY(0);
		back.setStyle("-fx-background-image: url(file:images/game1/left.png);"
				+ "-fx-opacity: 0;");
		
		// Actions
		next.setOnAction(e->{
			music.playClick();
			if(howToPlayCounter < 7) {
				howToPlayCounter++;
				background.setImage(new Image("file:images/game1/HowToPlay/howToPlay" + howToPlayCounter + ".jpg"));
			}else{
				window.getChildren().removeAll(background, next, back);
				howToPlayCounter = 1 ;
			}
		});
		
		back.setOnAction(e->{
			music.playClick();
			if(howToPlayCounter > 1) {
				howToPlayCounter--;
				background.setImage(new Image("file:images/game1/HowToPlay/howToPlay" + howToPlayCounter + ".jpg"));
			}else {
				window.getChildren().removeAll(background, next, back);
				howToPlayCounter = 1 ;
			}
		
		});
		
		// Hover effect transitions
		FadeTransition transition = new FadeTransition();
		transition.setDuration(new Duration(300));
		transition.setFromValue(0);
		transition.setToValue(0.5);
		
		next.setOnMouseMoved(e->{
			next.setOpacity(0.5);
		});
		next.setOnMouseEntered(e->{
			transition.setNode(next);
			transition.play();
		});
		next.setOnMouseExited(e->{
			transition.stop();
			next.setOpacity(0);
		});
		
		back.setOnMouseMoved(e->{
			back.setOpacity(0.5);
		});
		back.setOnMouseEntered(e->{
			transition.setNode(back);
			transition.play();
		});
		back.setOnMouseExited(e->{
			transition.stop();
			back.setOpacity(0);
		});
		
		window.getChildren().addAll(background, next, back);
	}	
	
	
	/**
	 * Handles the quit window of the game.
	 * @param window The window of the game.
	 * @param games	The List of games.
	 */
	public void quit(Group window, List<Button> games) {
		
		ImageView background = new ImageView(new Image("file:images/game1/game1Confirmation.jpg"));
		
		Button YES = new Button("YES");
		Button NO = new Button("NO");
		
		YES.setPrefSize(200, 40);
		YES.setLayoutX(140);
		YES.setLayoutY(350);
		YES.setBackground(null);
		YES.setTextFill(Color.WHITE);
		YES.setFont(Font.font("Verdana", 25));
		YES.setTextAlignment(TextAlignment.CENTER);
		
		NO.setPrefSize(200, 40);
		NO.setLayoutX(440);
		NO.setLayoutY(350);
		NO.setBackground(null);
		NO.setTextFill(Color.WHITE);
		NO.setFont(Font.font("Verdana", 25));
		NO.setTextAlignment(TextAlignment.CENTER);
		
		// actions
		YES.setOnAction(e->{
			music.playClick();
			music.stopGame1Background();
			window.getChildren().setAll(games);
		});
		
		NO.setOnAction(e->{
			music.playClick();
			window.getChildren().removeAll(background, YES, NO);
		});
		
		// hover effects
		YES.setOnMouseEntered(e->{
			YES.setStyle("-fx-background-color: grey;");
		});
		YES.setOnMouseExited(e->{
			YES.setStyle("-fx-background-color: none");
		});
		
		NO.setOnMouseEntered(e->{
			NO.setStyle("-fx-background-color: grey;");
		});
		NO.setOnMouseExited(e->{
			NO.setStyle("-fx-background-color: none");
		});
		
		window.getChildren().addAll(background, YES, NO);
	}
	
	
	/**
	 * Sets the questions of the game.
	 * Shows the different categories of questions.
	 * @param window of the game
	 * @param games The List of games.
	 */
	public void setQuestions(Group window, List<Button> games) {
		
		ImageView background = new ImageView(new Image("file:images/game1/category/categoryBackground.png"));
		
		// creating the buttons 
		Button overview0 = new Button(); 
		Button overview1 = new Button(); 
		Button overview2 = new Button(); 
		Button overview3 = new Button(); 
		
		overview0.setPrefSize(180, 39);
		overview1.setPrefSize(180, 34);
		overview2.setPrefSize(180, 34);
		overview3.setPrefSize(180, 34);
		
		overview0.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/overview.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		overview1.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/overview1.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		overview2.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/overview2.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		overview3.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/overview3.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

		VBox overview = new VBox();
		overview0.setLayoutX(105);
		overview0.setLayoutY(65);
		overview.setLayoutX(105);
		overview.setLayoutY(120);
		overview.getChildren().addAll(overview1, overview2, overview3);
		
		Button processManagement0 = new Button(); 
		Button processManagement1 = new Button(); 
		Button processManagement2 = new Button(); 
		Button processManagement3 = new Button(); 
		
		processManagement0.setPrefSize(180, 39);
		processManagement1.setPrefSize(180, 34);
		processManagement2.setPrefSize(180, 33);
		processManagement3.setPrefSize(180, 33);
		
		processManagement0.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/processManagement.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		processManagement1.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/processManagement1.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		processManagement2.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/processManagement2.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		processManagement3.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/processManagement3.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		VBox processManagement = new VBox();
		processManagement0.setLayoutX(310);
		processManagement0.setLayoutY(65);
		processManagement.setLayoutX(310);
		processManagement.setLayoutY(120);
		processManagement.getChildren().addAll(processManagement1, processManagement2, processManagement3);
		
		Button memoryManagement0 = new Button(); 
		Button memoryManagement1 = new Button(); 
		Button memoryManagement2 = new Button(); 
		Button memoryManagement3 = new Button(); 

		memoryManagement0.setPrefSize(180, 39);
		memoryManagement1.setPrefSize(180, 34);
		memoryManagement2.setPrefSize(180, 34);
		memoryManagement3.setPrefSize(180, 34);
		
		memoryManagement0.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/memoryManagement.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		memoryManagement1.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/memoryManagement1.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		memoryManagement2.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/memoryManagement2.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		memoryManagement3.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/memoryManagement3.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		VBox memoryManagement = new VBox();
		memoryManagement0.setLayoutX(515);
		memoryManagement0.setLayoutY(65);
		memoryManagement.setLayoutX(515);
		memoryManagement.setLayoutY(120);
		memoryManagement.getChildren().addAll(memoryManagement1, memoryManagement2, memoryManagement3);
		
		Button storageManagement0 = new Button(); 
		Button storageManagement1 = new Button(); 
		Button storageManagement2 = new Button(); 
		Button storageManagement3 = new Button(); 
		
		storageManagement0.setPrefSize(180, 39);
		storageManagement1.setPrefSize(180, 34);
		storageManagement2.setPrefSize(180, 34);
		storageManagement3.setPrefSize(180, 34);
		
		storageManagement0.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/storageManagement.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		storageManagement1.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/storageManagement1.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		storageManagement2.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/storageManagement2.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		storageManagement3.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/storageManagement3.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		VBox storageManagement = new VBox();
		storageManagement0.setLayoutX(195);
		storageManagement0.setLayoutY(240);
		storageManagement.setLayoutX(195);
		storageManagement.setLayoutY(295);
		storageManagement.getChildren().addAll(storageManagement1, storageManagement2, storageManagement3);
		
		Button securityManagement0 = new Button(); 
		Button securityManagement1 = new Button(); 
		Button securityManagement2 = new Button(); 
		Button securityManagement3 = new Button(); 
		
		securityManagement0.setPrefSize(180, 39);
		securityManagement1.setPrefSize(180, 34);
		securityManagement2.setPrefSize(180, 34);
		securityManagement3.setPrefSize(180, 34);
		
		securityManagement0.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/securityManagement.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		securityManagement1.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/securityManagement1.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		securityManagement2.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/securityManagement2.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		securityManagement3.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/securityManagement3.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		VBox securityManagement = new VBox();
		securityManagement0.setLayoutX(420);
		securityManagement0.setLayoutY(240);
		securityManagement.setLayoutX(420);
		securityManagement.setLayoutY(295);
		securityManagement.getChildren().addAll(securityManagement1, securityManagement2, securityManagement3);
		
		ArrayList<Button> questions = new ArrayList<Button>();
		
		questions.add(overview1);
		questions.add(overview2);
		questions.add(overview3);
		questions.add(processManagement1);
		questions.add(processManagement2);
		questions.add(processManagement3);
		questions.add(memoryManagement1);
		questions.add(memoryManagement2);
		questions.add(memoryManagement3);
		questions.add(storageManagement1);
		questions.add(storageManagement2);
		questions.add(storageManagement3);	
		questions.add(securityManagement1);
		questions.add(securityManagement2);
		questions.add(securityManagement3);
		
		// add actions for every button -> goes to a specific questions based on the category
		// 0-2   --> overview
		// 3-5   --> process management
		// 6-8   --> memory management
		// 9-11  --> storage management
		// 12-14 --> security management
		int counter = 0;
		for(Button question : questions) {
			if(counter < 3) {
				question.setOnAction(e->{
					music.playClick();
					currentQuestion++;
					question.setDisable(true);
					question.setOpacity(1);
					question.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/overviewDone.png"),
							BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
					questionnaire = new Questionnaire(window, 1, proceedButton(window, games), addLifelines(window, games), this.questions, 1);
				});
			}else if(counter < 6) {
				question.setOnAction(e->{
					music.playClick();
					currentQuestion++;
					question.setDisable(true);
					question.setOpacity(1);
					question.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/processManagementDone.png"),
							BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
					questionnaire = new Questionnaire(window, 2, proceedButton(window, games), addLifelines(window, games), this.questions, 1);
				});
			}else if(counter < 9) {
				question.setOnAction(e->{
					music.playClick();
					currentQuestion++;
					question.setDisable(true);
					question.setOpacity(1);
					question.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/memoryManagementDone.png"),
							BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
					questionnaire = new Questionnaire(window, 3, proceedButton(window, games), addLifelines(window, games), this.questions, 1);
				});
			}else if(counter < 12) {
				question.setOnAction(e->{
					music.playClick();
					currentQuestion++;
					question.setDisable(true);
					question.setOpacity(1);
					question.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/storageManagementDone.png"),
							BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
					questionnaire = new Questionnaire(window, 4, proceedButton(window, games), addLifelines(window, games), this.questions, 1);
				});
			}else {
				question.setOnAction(e->{
					music.playClick();
					currentQuestion++;
					question.setDisable(true);
					question.setOpacity(1);
					question.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/category/securityManagementDone.png"),
							BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
					questionnaire = new Questionnaire(window, 5, proceedButton(window, games), addLifelines(window, games), this.questions, 1);
				});
			}
			counter++;
		}
			
		// add the buttons the the window
		Group categories = new Group();
		categories.getChildren().addAll(overview0, overview, processManagement0, processManagement, memoryManagement0, memoryManagement, storageManagement0, storageManagement, securityManagement0, securityManagement );
		
		window.getChildren().setAll(background, categories);
	}

	
	/**
	 * Handles the proceed button of the game.
	 * @param window The window of the game.
	 * @param games	The List of games.
	 * @return The proceed button for game1.
	 */
	public Button proceedButton(Group window, List<Button> games) {
		
		// create the proceed button
		Button proceedButton = new Button();
		proceedButton.setLayoutX(680);
		proceedButton.setLayoutY(338);
		proceedButton.setPrefSize(83, 70);
		proceedButton.setDisable(true);
		proceedButton.setStyle("-fx-border-color: #FFFFFF;"
				+ "-fx-background-image: url('file:images/game1/gameplay/proceedButton1.png');"
				+ "-fx-background-size: cover;");
		
		// add the action of the proceed button
		proceedButton.setOnAction(e->{
			music.playClick();
			if(questionnaire.answerIsCorrect) {
				if(currentQuestion == 15) {
					result(window,1, games);
				}else {
					window.getChildren().removeAll(questionnaire.background, questionnaire.question, questionnaire.choices, proceedButton , this.lifelines);
				}

			}else {
				currentQuestion = 0;
				result(window, 2, games);
			}

		});
		
		this.proceedButton = proceedButton;
		
		return proceedButton;
	}
	
	
	/**
	 * Handles the lifelines for game1.
	 * @param window The window of the game.
	 * @param games	The List of games.
	 * @return The lifelines for game1.
	 */
	public VBox addLifelines(Group window, List<Button> games) {
		
		// create lifelines
		Button half = new Button();
		half.setPrefSize(50, 50);
		half.setStyle("-fx-background-image: url('file:images/game1/gameplay/half.png')");
		
		Button skip = new Button();
		skip.setPrefSize(50, 50);
		skip.setStyle("-fx-background-image: url('file:images/game1/gameplay/skip.png')");
		
		Button chance = new Button();
		chance.setPrefSize(50, 50);
		chance.setStyle("-fx-background-image: url('file:images/game1/gameplay/chance.png')");
		
		// disable button if lifeline is already used
		if(halfUsed) {
			half.setDisable(true);
		}
		if(skipUsed) {
			skip.setDisable(true);
		}
		if(chanceUsed) {
			chance.setDisable(true);
		}
	
		// lifeline actions
		half.setOnAction(e-> {
			music.playClick();
			if(!halfUsed) {
				half.setDisable(true);
				for(ScrollPane choice : questionnaire.choicesList) {
					if(choice.getContent().equals(questionnaire.buttonChoices.get(2)) || choice.getContent().equals(questionnaire.buttonChoices.get(3))) {
						choice.getParent().setDisable(true);
					}
				}
			
				halfUsed = true;
			}
		});
		
		skip.setOnAction(e-> {
			music.playClick();
			if(!skipUsed) {
				window.getChildren().removeAll(questionnaire.background, questionnaire.question, questionnaire.choices, proceedButton , this.lifelines);
				skipUsed = true;
				questionnaire = new Questionnaire(window, 1, proceedButton(window, games), addLifelines(window, games), this.questions, 1);
			}
		});
		
		chance.setOnAction(e-> {
			music.playClick();
			if(!chanceUsed) {
				chance.setDisable(true);
				questionnaire.playerAttempt++;
				chanceUsed = true;
			}
		});
		
		// layout
		VBox lifelines = new VBox(20);
		lifelines.getChildren().addAll(half, skip, chance);
		lifelines.setLayoutX(680);
		lifelines.setLayoutY(35);
		
		this.lifelines = lifelines;
		
		return lifelines;
	}
	
	
	/**
	 * Handles the result of the game.
	 * @param window The window of the game.
	 * @param resultType The int representation of the result of the game.
	 * @param games	The List of games.
	 */
	public void result(Group window, int resultType, List<Button> games) {
		
		ImageView background = new ImageView(new Image("file:images/game1/category/categoryBackground.png"));
		
		Button playAgain = new Button("Play again");
		Button backToMenu = new Button("Back to Menu");
		
		playAgain.setPrefSize(250, 40);
		playAgain.setLayoutX(140);
		playAgain.setLayoutY(350);
		playAgain.setBackground(null);
		playAgain.setTextFill(Color.WHITE);
		playAgain.setFont(Font.font("Verdana", 25));
		playAgain.setTextAlignment(TextAlignment.CENTER);
		
		backToMenu.setPrefSize(250, 40);
		backToMenu.setLayoutX(440);
		backToMenu.setLayoutY(350);
		backToMenu.setBackground(null);
		backToMenu.setTextFill(Color.WHITE);
		backToMenu.setFont(Font.font("Verdana", 25));
		backToMenu.setTextAlignment(TextAlignment.CENTER);
		
		// actions
		playAgain.setOnAction(e->{
			music.playClick();
			music.stopWin();
			music.stopLost(1);
			currentQuestion = 0;
			halfUsed = false;
			skipUsed = false;
			chanceUsed = false;
			setQuestions(window, games);
		});
		
		backToMenu.setOnAction(e->{
			music.playClick();
			music.stopWin();
			music.stopLost(1);
			music.stopGame1Background();
			menu(window, games);
		});
		
		
		// hover effects
		playAgain.setOnMouseEntered(e->{
			playAgain.setStyle("-fx-background-color: grey;");
		});
		playAgain.setOnMouseExited(e->{
			playAgain.setStyle("-fx-background-color: none");
		});
		
		backToMenu.setOnMouseEntered(e->{
			backToMenu.setStyle("-fx-background-color: grey;");
		});
		backToMenu.setOnMouseExited(e->{
			backToMenu.setStyle("-fx-background-color: none");
		});
		
		// results type
		if(resultType == 1) {
			music.playWin();
			background.setImage(new Image("file:images/game1/game1Congratulations.jpg"));
			window.getChildren().setAll(background, playAgain, backToMenu);
		}else if(resultType == 2) {
			music.playLost(1);
			background.setImage(new Image("file:images/game1/game1Gameover.jpg"));
			window.getChildren().setAll(background, playAgain, backToMenu);
		}
		
	}

}