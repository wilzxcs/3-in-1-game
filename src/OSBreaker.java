import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * The third game of the game bundle
 * A game variant of Brick Breaker
 * @see Game Bundle
 *
 */
public class OSBreaker {
	
	private Questionnaire questionnaire;			// The questionnaire	
	private Questions questions = new Questions();	// The questions
	private Music music = new Music();				// THe music of the game
	
	private ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();			// The array for the blocks
	private ArrayList<Rectangle> blocks2Remove = new ArrayList<Rectangle>();	// The array for the blocks to be removed
	private Circle ball;							// The ball
	private Rectangle racket;						// The racket
	
	private Button proceedButton;					// The proceed button for the OSBreaker game
	private Label scoreText;						// The label for the score 
	private HBox lifesBox;							// The box for the lifes
	
	private int xDirection = 0;						// The x-position of the ball
	private int yDirection = 1;						// The y-position of the ball
		
	private int speed = 1;							// The speed of the ball
	private int score = 0;							// The score
	private int lifes = 3;							// The number of lifes
	
	private int blockSize = 32;						// The size of 1 side of the block
	private int ballSize = 12;						// The radius of the ball
	private int racketWidth = 200;					// The width of the racket
	private int racketCounter = 0;					// The number of times the ball hit the racket
	private int racketFrame = 1001;					// A frame counter for the extend powerup
	
	private int blocksRemoved = 0;					// The number if blocks removed
	private boolean isLive = false;					// A boolean if the ball is live
	private int howToPlayCounter = 1;				// The index counter for the howToPlay slides
	
	private AnimationTimer timer;					// The animation timer

	/**
	 * Handles the menu window of the OS Breaker game.
	 * @param window The window of the game
	 * @param games The list of games
	 */
	public void menu(Group window, List<Button> games) {
		
		music.playGame3Background();
		ImageView background = new ImageView(new Image("file:images/game3/game3Background.jpg"));
		
		Button playButton = new Button("Play");
		Button howToPlayButton = new Button("How to Play");
		Button quitButton = new Button("Quit");
		Button musicButton = new Button();
		
		playButton.setPrefSize(400, 70);
		playButton.setBackground(null);
		playButton.setTextFill(Color.WHITE);
		playButton.setFont(Font.font("Verdana", 40));
		playButton.setTextAlignment(TextAlignment.CENTER);
		
		howToPlayButton.setPrefSize(400, 70);
		howToPlayButton.setBackground(null);
		howToPlayButton.setTextFill(Color.WHITE);
		howToPlayButton.setFont(Font.font("Verdana", 40));
		howToPlayButton.setTextAlignment(TextAlignment.CENTER);
		
		quitButton.setPrefSize(400, 70);
		quitButton.setBackground(null);
		quitButton.setTextFill(Color.WHITE);
		quitButton.setFont(Font.font("Verdana", 40));
		quitButton.setTextAlignment(TextAlignment.CENTER);
		
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
				music.stopGame3Background();
				musicButton.setStyle("-fx-background-image: url('file:images/musicOff.png');"
						+ "-fx-background-color: none;");
			}else {
				music.toggleMusic();
				music.playGame3Background();
				musicButton.setStyle("-fx-background-image: url('file:images/music.png');"
						+ "-fx-background-color: none;");
			}
		});
		
		
		// Hover Effects
		playButton.setOnMouseEntered(e-> {
			playButton.setStyle("-fx-background-color: F07622");
		});
		playButton.setOnMouseExited(e-> {
			playButton.setStyle("-fx-background-color: none");
		});
		
		howToPlayButton.setOnMouseEntered(e-> {
			howToPlayButton.setStyle("-fx-background-color: F07622");
		});
		howToPlayButton.setOnMouseExited(e-> {
			howToPlayButton.setStyle("-fx-background-color: none");
		});
		
		quitButton.setOnMouseEntered(e-> {
			quitButton.setStyle("-fx-background-color: F07622");
		});
		quitButton.setOnMouseExited(e-> {
			quitButton.setStyle("-fx-background-color: none");
		});

		
		// Layout
		VBox vbox = new VBox(5);
		vbox.setLayoutX(190);
		vbox.setLayoutY(100);
		vbox.getChildren().addAll(playButton, howToPlayButton, quitButton);
		
		window.getChildren().setAll(background, vbox, musicButton);
	}
	
	/**
	 * Handles the play window of the game
	 * @param window The window of the game
	 * @param games The list of games
	 */
	public void play(Group window, List<Button> games) {
		ImageView background = new ImageView(new Image("file:images/game3/game3Background.jpg"));
		
		// initialize new game
		isLive = false;
		blocks.clear();			
		blocksRemoved = 0;
		speed = 1;
		lifes = 3;
		score = 0;
		racketCounter = 0;
		
		// Racket
		racket = new Rectangle(145, 430, racketWidth, 15);
		racket.setFill(Color.web("F07622"));
		
		// Ball
		ball = new Circle(225, 429-ballSize, ballSize);
		ball.setFill(new ImagePattern(new Image("file:images/game3/gameplay/ball.png")));
		
		// Score
		scoreText = new Label("Score:  ");
		scoreText.setPrefSize(150, 30);
		scoreText.setLayoutX(10);
		scoreText.setLayoutY(415);
		scoreText.setStyle("-fx-background: none;"
				+ "-fx-font-family: Verdana;"
				+ "-fx-font-size: 20px;"
				+ "-fx-text-fill: white;"
				+ "-fx-text-alignment: left;"
				+ "-fx-opacity: 0.8;");
		
		// Lifes
		lifesBox = new HBox(5);
		for(int i = 0; i < lifes; i++) {
			Label life = new Label();
			life.setPrefSize(30, 30);
			life.setStyle("-fx-background-image: url('file:images/game3/gameplay/heart.png');"
					+ "-fx-opacity: 0.6");
			lifesBox.getChildren().add(life);
		}
		lifesBox.setLayoutX(695);
		lifesBox.setLayoutY(415);

		
		window.getChildren().setAll(background, ball, racket, scoreText, lifesBox);
		
		// row blocks
		for(int row = 0; row < 7 ; row++) {
			for(int i = 0 ; i < 800 ; i+=blockSize) {
				int random = (int)(((Math.random() * 100) % 4) + 1);
				Rectangle block = new Rectangle(i, blockSize*row, blockSize, blockSize);
				block.setFill(new ImagePattern(new Image("file:images/game3/gameplay/block" + random + ".png")));
				blocks.add(block);
				window.getChildren().add(block);
			}
		}
		
		// actions when mouse is moved
		background.setOnMouseMoved(e->{
			if(!isLive) {
				ball.setCenterX(racket.getX() + 100);
			}
			
			if(e.getX() >= 60 && e.getX() <= 740) {
				racket.setX(e.getX() - 80);
			}
		});
		
		// Action when mouse is clicked
		background.setOnMouseClicked(e->{
			if(!isLive) {
				int random = (int)(((Math.random() * 100) % 2));
				isLive = true;
				xDirection = random;
				timer.start();
			}
			
		});
		
		// Animation --> Just one animation thread where clock animation function is executed every centisecond
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				clockAnimation(window, games);
			}
		};
	}
	
	/**
	 * The function called in every centisecond to render the animation
	 * Handles when ball hits racket, walls, blocks and other interactions
	 * @param window The window of the game.
	 * @param games The list of games.
	 */
	public void clockAnimation(Group window, List<Button> games) {
		
		ballMove();
		racketFrame++;
		
		// racket Extension timer
		if(racketFrame == 1000) {
			music.playShrink();
			ScaleTransition scaleTransition = new ScaleTransition();
			scaleTransition.setDuration(new Duration(1000));
			scaleTransition.setFromX(2.0);
			scaleTransition.setToX(1.0);
			scaleTransition.setNode(racket);
			scaleTransition.play();
		}
		
		// remove block
		if(!blocks2Remove.isEmpty()) {
			score += 20;
			scoreText.setText("Score: " + score);
			blocksRemoved ++;
			blocks.removeAll(blocks2Remove);
			blocks2Remove.clear();
		} 
		
		// power-up questionnaire
		if(blocksRemoved == 140) {
			int type = (int)(((Math.random() * 100) % 5) + 1);
			blocksRemoved = 141;
			timer.stop();
			questionnaire = new Questionnaire(window, type, proceedButton(window, games), 
					addLifelines(window, games), this.questions, 3);
		}else if(blocksRemoved == 110) {
			int type = (int)(((Math.random() * 100) % 5) + 1);
			blocksRemoved = 111;
			timer.stop();
			questionnaire = new Questionnaire(window, type, proceedButton(window, games), 
					addLifelines(window, games), this.questions, 3);
		}else if(blocksRemoved == 85) {
			int type = (int)(((Math.random() * 100) % 5) + 1);
			blocksRemoved = 86;
			timer.stop();
			questionnaire = new Questionnaire(window, type, proceedButton(window, games), 
					addLifelines(window, games), this.questions, 3);
		}else if(blocksRemoved == 50) {
			int type = (int)(((Math.random() * 100) % 5) + 1);
			blocksRemoved = 51;
			timer.stop();
			questionnaire = new Questionnaire(window, type, proceedButton(window, games), 
					addLifelines(window, games), this.questions, 3);
		}else if(blocksRemoved == 30) {
			int type = (int)(((Math.random() * 100) % 5) + 1);
			blocksRemoved = 31;
			timer.stop();
			questionnaire = new Questionnaire(window, type, proceedButton(window, games), 
					addLifelines(window, games), this.questions, 3);
		}else if(blocksRemoved == 15) {
			int type = (int)(((Math.random() * 100) % 5) + 1);
			blocksRemoved = 3;
			timer.stop();
			questionnaire = new Questionnaire(window, type, proceedButton(window, games), 
					addLifelines(window, games), this.questions, 3);
		}
		
		
		// if no more blocks (game win)
		if(blocks.isEmpty()) {
			music.playWin3();
			timer.stop();
			gameOver(window, games, 2);
		}
		
		// if ball goes below the wall (game over)
		if(ball.intersects(0, 450, 800, 0)) {
			music.playLostlife();
			
			// remove life
			lifes--;
			lifesBox.getChildren().remove(0);
			lifesBox.setLayoutX(lifesBox.getLayoutX() + 35);
			
			timer.stop();
			isLive = false;
			if(lifes == 0) {
				music.playLost(3);
				System.out.println("Game Over");
				gameOver(window, games, 1);
			}else {
				ball.setCenterX(racket.getX() + 80);
				ball.setCenterY(429-ballSize);
			}
		}
	
		// if ball collides with racket
		if(ball.intersects(racket.getX() - ((racket.getScaleX()-1)*100),racket.getY(), 
				racket.getWidth()*racket.getScaleX(), 10)) {
			System.out.println("ball collided with racket");			
			// change ball y direction
			yDirection = 1;
			music.playBounce();
			
			// increase ball speed after 10 hits with the racket
			racketCounter++;
			if(racketCounter == 10) {
				if(speed <= 10) {
					speed++;
				}
				racketCounter = 0;
			}
		}
		
		// if ball hits the left side of the walls
		if(ball.intersects(0, 0, 0, 450)) {	
			System.out.println("ball hits the left wall");
			// change ball x direction					
			xDirection = 1;
			music.playBounce();
		}
		
		// if ball hits the right side of the walls
		if(ball.intersects(800, 0, 0, 450)) {
			System.out.println("ball hits the right wall");
			// change ball x direction
			xDirection = 0;
			music.playBounce();
		}
		
		// if ball hits the top side of the walls
		if(ball.intersects(0, 0, 800, 0)) {
			System.out.println("ball hits the top wall");	
			// change ball y direction
			yDirection = 0;
			music.playBounce();
		}
		
		// ball collides with a block
		for(Rectangle block : blocks) {
			if(ball.intersects(block.getBoundsInLocal())) {
				window.getChildren().remove(block);
				blocks2Remove.add(block);
				music.playBounce();
				
				if(((block.getX() +  blockSize) >= ball.getCenterX() - 12)
					&& block.getX() < ball.getCenterX()
					&& block.getY() < ball.getCenterY()
					&& block.getY()+blockSize > ball.getCenterY()) {
//					// change ball x direction
//					System.out.println("ball hits the left ");						
					xDirection = 1;
				}else if(block.getX() <= ball.getCenterX() + 12
					&& block.getY() < ball.getCenterY()
					&& block.getY()+blockSize > ball.getCenterY()) {
					// change ball x direction
//					System.out.println("ball hits the right");	
					xDirection = 0;	
				}else {
					// change ball y direction
//					System.out.println("ball hits the top or bottom");	
					if(yDirection == 0) {
						yDirection = 1;
					}else {
						yDirection = 0;
					}
				}
				break;
			}
		}
	}
	
	/**
	 * Makes the ball move
	 */
	public void ballMove() {
		if(xDirection == 0) {
			ball.setCenterX(ball.getCenterX() - speed);	// go left
		}else {
			ball.setCenterX(ball.getCenterX() + speed);	// go right
		}
		
		if(yDirection == 0) {
			ball.setCenterY(ball.getCenterY() + speed);	// go down
		}else {
			ball.setCenterY(ball.getCenterY() - speed);	// go up
		}
	}
	
	/**
	 * Handles the how to play window of the game.
	 * @param window The window of the game
	 */
	public void howToPlay(Group window) {
		
		ImageView background = new ImageView(new Image("file:images/game3/HowToPlay/howToPlay1.png"));
		
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
			if(howToPlayCounter < 9) {
				howToPlayCounter++;
				background.setImage(new Image("file:images/game3/HowToPlay/howToPlay" + howToPlayCounter + ".png"));
			}else{
				window.getChildren().removeAll(background, next, back);
				howToPlayCounter = 1 ;
			}
		});
		
		back.setOnAction(e->{
			music.playClick();
			if(howToPlayCounter > 1) {
				howToPlayCounter--;
				background.setImage(new Image("file:images/game3/HowToPlay/howToPlay" + howToPlayCounter + ".png"));
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
	 * @param games The list of games
	 */
	public void quit(Group window, List<Button> games) {
		ImageView background = new ImageView(new Image("file:images/game3/game3Confirmation.png"));
		
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
			music.stopGame3Background();
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
	 * Handles the power-ups of the games
	 * @param window The window of the game
	 * @param games The list of games
	 */
	public void powerUp(Group window, List<Button> games) {
		
		ImageView background = new ImageView(new Image("file:images/game3/gameplay/powerup.png"));
		
		Button EXTEND = new Button();
		Button EXTRA = new Button();
		Button RESET = new Button();
		
		// extend racket
		EXTEND.setPrefSize(241, 210);
		EXTEND.setLayoutX(28);
		EXTEND.setLayoutY(135);
		EXTEND.setBackground(null);
		EXTEND.setTextFill(Color.WHITE);
		EXTEND.setFont(Font.font("Verdana", 25));
		EXTEND.setTextAlignment(TextAlignment.CENTER);
		
		EXTEND.setOnAction(e->{
			music.playGrow();
			
			ScaleTransition scaleTransition = new ScaleTransition();
			scaleTransition.setDuration(new Duration(1000));
			scaleTransition.setFromX(1.0);
			scaleTransition.setToX(2.0);
			scaleTransition.setNode(racket);
			scaleTransition.play();
			
			System.out.println("I choose extend racket");
			racketFrame = 0;
			timer.start();
			window.getChildren().removeAll(background, EXTEND, EXTRA, RESET);
		});
		
		EXTEND.setOnMouseEntered(e->{
			EXTEND.setStyle("-fx-background-color: white;"
				+ "-fx-opacity: 0.5;");
		});
		EXTEND.setOnMouseExited(e->{
			EXTEND.setStyle("-fx-background-color: none;"
					+ "-fx-opacity: 0;");
		});
		
		// add additional life
		EXTRA.setPrefSize(241, 210);
		EXTRA.setLayoutX(280);
		EXTRA.setLayoutY(135);
		EXTRA.setBackground(null);
		EXTRA.setTextFill(Color.WHITE);
		EXTRA.setFont(Font.font("Verdana", 25));
		EXTRA.setTextAlignment(TextAlignment.CENTER);
		
		EXTRA.setOnAction(e->{
			music.playAddlife();
			System.out.println("I choose extra life");
			lifes++;
			Label life = new Label();
			life.setPrefSize(30, 30);
			life.setStyle("-fx-background-image: url('file:images/game3/gameplay/heart.png');"
					+ "-fx-opacity: 0.6");
			lifesBox.setLayoutX(lifesBox.getLayoutX() - 35);
			lifesBox.getChildren().add(life);
			
			timer.start();
			window.getChildren().removeAll(background, EXTEND, EXTRA, RESET);
		});
		
		EXTRA.setOnMouseEntered(e->{
			EXTRA.setStyle("-fx-background-color: white;"
				+ "-fx-opacity: 0.5;");
		});
		EXTRA.setOnMouseExited(e->{
			EXTRA.setStyle("-fx-background-color: none;"
					+ "-fx-opacity: 0;");
		});
		
		
		// reset ball speed
		RESET.setPrefSize(241, 210);
		RESET.setLayoutX(532);
		RESET.setLayoutY(135);
		RESET.setBackground(null);
		RESET.setTextFill(Color.WHITE);
		RESET.setFont(Font.font("Verdana", 25));
		RESET.setTextAlignment(TextAlignment.CENTER);
		
		RESET.setOnAction(e->{
			music.playReset();
			System.out.println("I choose reset ball speed");
			speed = 1;
			timer.start();
			window.getChildren().removeAll(background, EXTEND, EXTRA, RESET);
		});
		
		RESET.setOnMouseEntered(e->{
			RESET.setStyle("-fx-background-color: white;"
				+ "-fx-opacity: 0.5;");
		});
		RESET.setOnMouseExited(e->{
			RESET.setStyle("-fx-background-color: none;"
					+ "-fx-opacity: 0;");
		});
		
		window.getChildren().addAll(background, EXTEND, EXTRA, RESET);
		
	}
	
	/**
	 * Handles the procced button of the game
	 * @param window The window of the game
	 * @param games The list of games
	 * @return The proceed button for game 2
	 */
	public Button proceedButton(Group window, List<Button> games) {
		
		// create the proceed button
		proceedButton = new Button();
		proceedButton.setLayoutX(680);
		proceedButton.setLayoutY(338);
		proceedButton.setPrefSize(83, 70);
		proceedButton.setBackground(new Background(new BackgroundImage( new Image("File:images/game1/gameplay/proceedButton1.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		proceedButton.setStyle("-fx-border-color: #FFFFFF");
		
		// add the action of the proceed button
		proceedButton.setOnAction(e->{
			if(questionnaire.answerIsCorrect) {
				window.getChildren().removeAll(questionnaire.background, questionnaire.question, questionnaire.choices, proceedButton);
				powerUp(window, games);
			}else {
				window.getChildren().removeAll(questionnaire.background, questionnaire.question, questionnaire.choices, proceedButton);
				timer.start();
			}

		});
		return proceedButton;
	}
	
	/**
	 * Handles the result window of the game
	 * @param window The window of the game
	 * @param games The list of games
	 * @param result The integer representatin of the result of the game
	 */
	public void gameOver(Group window, List<Button> games, int result) {
		
		music.stopGame3Background();;
		
		ImageView background = new ImageView(new Image("file:images/game3/result1.png"));
		if(result == 2){
			background = new ImageView(new Image("file:images/game3/result2.png"));
		}
			
		Button exit = new Button();
		Button score = new Button();
		
		score.setPrefSize(150, 60);
		score.setLayoutX(340);
		score.setLayoutY(230);
		score.setText("" + this.score);
		score.setStyle("-fx-text-fill: White;"
				+ "-fx-font-family: 'Arial Black';"
				+ "-fx-font-size: 30px;");
		score.setBackground(null);
		
		exit.setPrefSize(250, 40);
		exit.setLayoutX(440);
		exit.setLayoutY(350);
		exit.setBackground(null);
		
		exit.setOnAction(e->{
			music.stopLost(3);
			music.stopWin3();
			menu(window, games);
		});
		
		window.getChildren().setAll(background, score, exit);
	}
	
	/**
	 * Handles the lifeslifes of the game
	 * @param window The window of the game
	 * @param games The list of games
	 * @return A VBox with nothing on it since game 3 have no lifelines
	 */
	public VBox addLifelines(Group window, List<Button> games) {
		
		VBox box = new VBox();
		
		Button trash = new Button();
		trash.setPrefSize(20, 20);
		trash.setVisible(false);
		
		box.getChildren().addAll(trash);
		
		return box;
		
	}
}
