import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * The class that handles the second game of the Game Bundle.
 * A game variant of Plants vs Zombies.
 * @see Game Bundle
 */
public class WindowsVsLinux {

	private Questionnaire questionnaire;			// The questionnaire	
	private Questions questions = new Questions();	// The questions
	private Music music = new Music();
	
	private HBox plantArea;			// The grid for plants
	private VBox lifelines; 		// A c for the power-ups and the questionnaire button(minds)
	private Button minds;			// The minds or the questionnaire button
	private Button mindsText;		// The text in the minds button
	private Button eliminate;		// The eliminate power-up button
	private Button amplify;			// The Amplify power-up button
	private Button halt;			// The Halt power-up button
	private Button unlock;			// The unlock power-up button
	private Button proceedButton; 	// The proceed button for the Window vs Linux game
	private Button notificationBar; // The notification bar
	private Button scoreboard;		// The score board
	private Button scoreboardText;	// The text of the score board
	private Button bossHP;			// The boss zombie HP bar
	private Button bossHPWrapper;	// A wrapper for the boss HP bar
	private Button bossIcon;		// An icon for the boss HP bar
	private VBox column1;			// The first column of plants
	
	private Zombie boss;												// The boss zombie
	private ArrayList<Zombie> zombies = new ArrayList<Zombie>();		// The ArrayList where Zombie objects are stored
	private ArrayList<Plant> plants = new ArrayList<Plant>();			// The ArrayList where plants are stored
	private ArrayList<Shield> shields = new ArrayList<Shield>();		// The ArrayList where shields are stored
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();		// The ArrayList where bullets are stored
	
	private ArrayList<Bullet> bullets2Remove = new ArrayList<Bullet>();	// The ArrayList where dead bullets to remove are stored
	private ArrayList<Zombie> zombies2Remove = new ArrayList<Zombie>();	// The ArrayList where dead zombies to remove are stored
	
	private ArrayList<VBox> columns; 	// The columns for plants and the shields
	private Pane column5;												// The column for the shields
	
	private int mindsCount = 10;			// The number of minds(game currency)
	private double damageMultiplier = 1;	// The damage multiplier when amplify power-up is used
	private int unlocked = 1;				// The number of unlocked columns in the plant area grid
	
	private boolean isPlaying = false;		// A boolean if the the game is ongoing
	private int zombieHealth = 5;			// The health of zombies
	private int howToPlayCounter = 1;		// The index counter for the howToPlay slides
	
	private AnimationTimer timer;			// The animation timer
	private int gameFrame = 0;				
	private int zombieSpawnFrame = 0;		
	private int zombieSpawnRate = 500;
	private int mindsCooldown = 0;
	private int mindsCooldownFrame = 0;
	private int ampDamageCooldown = 0;
	private int ampDamageCooldownFrame = 0;
	private int haltCooldown = 0;
	private int haltCooldownFrame = 0;
	private int notificationFrame = 0;
	
	/**
	 * A constructor for the WindowsVsLinux class
	 * Creates and initialize the plants, shields, and the main boss zombie
	 * @param window The window of the game
	 */
	public WindowsVsLinux(Group window) {
		
		// Plants column 1
		Plant plant1_1 = new Plant(window, 90, 128);
		Plant plant1_2 = new Plant(window, 90, 192);
		Plant plant1_3 = new Plant(window, 90, 256);
		Plant plant1_4 = new Plant(window, 90, 320);
		Plant plant1_5 = new Plant(window, 90, 384);
		
		column1 = new VBox();
		column1.getChildren().addAll(plant1_1, plant1_2, plant1_3, plant1_4, plant1_5);
		
		
		// Plants column 2
		Plant plant2_1 = new Plant(window, 165, 128);
		Plant plant2_2 = new Plant(window, 165, 192);
		Plant plant2_3 = new Plant(window, 165, 256);
		Plant plant2_4 = new Plant(window, 165, 320);
		Plant plant2_5 = new Plant(window, 165, 384);
		
		VBox column2 = new VBox();
		column2.getChildren().addAll(plant2_1, plant2_2, plant2_3, plant2_4, plant2_5);
		
		
		//Plants column 3
		Plant plant3_1 = new Plant(window, 240, 128);
		Plant plant3_2 = new Plant(window, 240, 192);
		Plant plant3_3 = new Plant(window, 240, 256);
		Plant plant3_4 = new Plant(window, 240, 320);
		Plant plant3_5 = new Plant(window, 240, 384);
		
		VBox column3 = new VBox();
		column3.getChildren().addAll(plant3_1, plant3_2, plant3_3, plant3_4, plant3_5);
		
		
		// Plants column 4
		Plant plant4_1 = new Plant(window, 315, 128);
		Plant plant4_2 = new Plant(window, 315, 192);
		Plant plant4_3 = new Plant(window, 315, 256);
		Plant plant4_4 = new Plant(window, 315, 320);
		Plant plant4_5 = new Plant(window, 315, 384);
		
		VBox column4 = new VBox();
		column4.getChildren().addAll(plant4_1, plant4_2, plant4_3, plant4_4, plant4_5);
		
		this.columns = new ArrayList<VBox>();
		this.columns.add(column1);
		this.columns.add(column2);
		this.columns.add(column3);
		this.columns.add(column4);
		
		this.plants.add(plant1_1);
		this.plants.add(plant1_2);
		this.plants.add(plant1_3);
		this.plants.add(plant1_4);
		this.plants.add(plant1_5);
		this.plants.add(plant2_1);
		this.plants.add(plant2_2);
		this.plants.add(plant2_3);
		this.plants.add(plant2_4);
		this.plants.add(plant2_5);
		this.plants.add(plant3_1);
		this.plants.add(plant3_2);
		this.plants.add(plant3_3);
		this.plants.add(plant3_4);
		this.plants.add(plant3_5);
		this.plants.add(plant4_1);
		this.plants.add(plant4_2);
		this.plants.add(plant4_3);
		this.plants.add(plant4_4);
		this.plants.add(plant4_5);
		
		
		// Shields column 5
		Shield shield5_1 = new Shield(window, 320, 128);
		Shield shield5_2 = new Shield(window, 320, 192);
		Shield shield5_3 = new Shield(window, 320, 256);
		Shield shield5_4 = new Shield(window, 320, 320);
		Shield shield5_5 = new Shield(window, 320, 384);
		
		this.shields.add(shield5_1);
		this.shields.add(shield5_2);
		this.shields.add(shield5_3);
		this.shields.add(shield5_4);
		this.shields.add(shield5_5);
		
		this.column5 = new Pane(shield5_1, shield5_2, shield5_3, shield5_4, shield5_5);
		this.column5.setLayoutY(-10);
		
		// The plant area
		this.plantArea = new HBox();
		this.plantArea.setLayoutX(22);
		this.plantArea.setLayoutY(115);
		
		// The boss zombie
		boss = new Zombie(window, 200);
		boss.setPrefSize(120, 300);
		boss.setLayoutX(680);
		boss.setLayoutY(125);
		boss.setStyle("-fx-opacity: 1");
		boss.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/boss.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		// Notification bar
		notificationBar = new Button();
		notificationBar.setPrefSize(248, 40);
		notificationBar.setLayoutX(435);
		notificationBar.setLayoutY(15);
		notificationBar.setStyle("-fx-font-size: 9px;"
				+ "-fx-font-family: Verdana");
		notificationBar.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/notification.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		// Score Board
		scoreboard = new Button();
		scoreboard.setPrefSize(84, 90);
		scoreboard.setLayoutX(700);
		scoreboard.setLayoutY(10);
		scoreboard.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/scoreboard.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		scoreboardText = new Button();
		scoreboardText.setPrefSize(84, 30);
		scoreboardText.setLayoutX(700);
		scoreboardText.setLayoutY(22);
		scoreboardText.setStyle("-fx-font-family: 'Arial Black';"
				+ "-fx-background-color: none;"
				+ "-fx-font-size: 15px;"
				+ "-fx-text-fill: White;");
		
	}
	
	/**
	 * Handles the menu window of the Windows vs Linux Game.
	 * @param window The window of the game.
	 * @param games The List of games.
	 */
	public void menu(Group window, List<Button> games) {
		
		music.stopGame2Background();
		music.playGame2Background();
		
		ImageView background = new ImageView(new Image("file:images/game2/game2Background.jpg"));
		
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
				music.stopGame2Background();
				musicButton.setStyle("-fx-background-image: url('file:images/musicOff.png');"
						+ "-fx-background-color: none;");
			}else {
				music.toggleMusic();
				music.playGame2Background();
				musicButton.setStyle("-fx-background-image: url('file:images/music.png');"
						+ "-fx-background-color: none;");
			}
		});
		
		// Hover Effects
		playButton.setOnMouseEntered(e-> {
			playButton.setStyle("-fx-background-color: #DEA900");
		});
		playButton.setOnMouseExited(e-> {
			playButton.setStyle("-fx-background-color: none");
		});
		
		howToPlayButton.setOnMouseEntered(e-> {
			howToPlayButton.setStyle("-fx-background-color: #DEA900");
		});
		howToPlayButton.setOnMouseExited(e-> {
			howToPlayButton.setStyle("-fx-background-color: none");
		});
		
		quitButton.setOnMouseEntered(e-> {
			quitButton.setStyle("-fx-background-color: #DEA900");
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
	 * Handles the play area of the game
	 * @param window The window of the game.
	 * @param games The List of games.
	 */
	public void play(Group window, List<Button> games) {

		ImageView background = new ImageView(new Image("file:images/game2/gameplayBackground.png"));
		
		// reset the zombies and bullets for new game
		zombies.clear();
		bullets.clear();
		unlocked = 1;
		mindsCount = 10;
		
		this.plantArea.getChildren().clear();
		this.plantArea.getChildren().addAll(column1); // Add the first column of plants to the plant area
		
		
		// reset frames for new game
		gameFrame = 0;				
		zombieSpawnFrame = 0;		
		zombieSpawnRate = 500;
		mindsCooldown = 0;
		mindsCooldownFrame = 0;
		ampDamageCooldown = 0;
		ampDamageCooldownFrame = 0;
		haltCooldown = 0;
		haltCooldownFrame = 0;
		
		// Plants
		for(Plant plant : plants) {
			// reset level of plants for the new game
			plant.resetLevel();
			plant.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/plant" + plant.getLevel() + ".png"),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
			
			// Add actions to the plants everytime it is clicked
			plant.setOnAction(e->{
				if(mindsCount <= plant.getLevel()) {
					music.stopError();
					music.playError();
					notificationBar.setVisible(true);
					notificationBar.setText("Not enough minds to upgrade plant");
					notificationFrame = 200;
//					System.out.println("Not enough minds to upgrade plant");
				}else {
					music.playLevelup();
					plant.incrementLevel();				// increment plant level	
					mindsCount -= plant.getLevel();		// decrement the mindsCount depending on the plant level as a cost for upgrading
					mindsText.setText("" + mindsCount);	// change the text in the minds button
				}
			});
		}
		
		/// Shields
		for(Shield shield : shields) {
			// reset health points of shields for the new game
			shield.resetHealthPoints();
			
			// Adds life to the shield everytime it is clicked
			shield.setOnAction(e->{
				if(mindsCount < 2) {
					music.stopError();
					music.playError();
					notificationBar.setVisible(true);
					notificationBar.setText("Not enough minds to increase shield health points");
					notificationFrame = 200;
//					System.out.println("Not enough minds to increase shield health points");
				}else {
					music.stopFix();
					music.playFix();
					shield.increHealthPoints();						// increment shield health points
					mindsCount -= 2;								// decrement mindsCount by 2
					shield.setText("" + shield.getHealthPoints());// change text in the shield
					mindsText.setText("" + mindsCount);	// change the text in the minds button
				}
				
			});
		}
		
		// Boss zombie HP
		bossHPWrapper = new Button();
		bossHPWrapper.setPrefSize(202, 30);
		bossHPWrapper.setLayoutX(485);
		bossHPWrapper.setLayoutY(70);
		bossHPWrapper.setStyle("-fx-color: white");
		
		bossHP = new Button();
		bossHP.setPrefSize(200, 28);
		bossHP.setLayoutX(486);
		bossHP.setLayoutY(71);
		bossHP.setMinWidth(0);
		bossHP.setStyle("-fx-color: #00FF00");

		bossIcon = new Button();
		bossIcon.setPrefSize(30, 30);
		bossIcon.setLayoutX(445);
		bossIcon.setLayoutY(70);
		bossIcon.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/icon.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		//First zombie
		Zombie zombie1 = new Zombie(window, 5);
		zombies.add(zombie1);
		
		// Animation --> Just one animation thread where clock animation function is executed every centisecond
		timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				clockAnimation(window, games);
			}
		};
		
		timer.start();

		// Change the window
		window.getChildren().setAll(background, column5, plantArea, minds(window, games), eliminate(window), 
				amplifyDamage(), halt(), unlock(columns), zombie1, boss, notificationBar, scoreboard,
				bossIcon, bossHPWrapper, bossHP, mindsText, scoreboardText);	
	}
	
	/**
	 * The function called in every centisecond to render the animation
	 * Hanldes the firing of the Plants and the motion of the bullets,
	 * spanwing of zombies, movement of zombies, and other interactions
	 * @param window The window of the game.
	 */
	public void clockAnimation(Group window, List<Button> games){

		gameFrame++;
		zombieSpawnFrame++;
		mindsCooldownFrame++;
		ampDamageCooldownFrame++;
		haltCooldownFrame++;
		notificationFrame--;
		
		// update scoreboard
		scoreboardText.setText("" + gameFrame);
		
		// make notification disappear after 2 sec
		if(notificationFrame < 0) {
			notificationBar.setVisible(false);
		}
		
		// remove the zombies from the arraylist
		if(zombies.containsAll(zombies2Remove)) {
			zombies.removeAll(zombies2Remove);
			zombies2Remove.clear();
		}
		
		// changes the spawn rate of zombies and their health as the game goes on
		if(gameFrame > 6000) {
			zombieHealth = 15;
			zombieSpawnRate = 400;
		} else if(gameFrame > 12000) {
			zombieHealth = 20;
			zombieSpawnRate = 350;
		} else if(gameFrame > 18000) {
			zombieHealth = 25;
			zombieSpawnRate = 250;
		} else if(gameFrame > 24000) {
			zombieHealth = 30;
			zombieSpawnRate = 150;
		} else if(gameFrame > 30000) {
			zombieHealth = 35;
			zombieSpawnRate = 100;
		}
		
		
		// Making plants shoot
    	for(Plant plant : plants) {
			if(plant.getLevel() > 0) {
				if(plant.getPlantFrame() > 200) {
					music.playHit();
					if(ampDamageCooldownFrame < ampDamageCooldown){
						damageMultiplier = 1.5;
						plant.shoot(window, damageMultiplier);
					}else {
						damageMultiplier = 1;
						amplify.setDisable(false);
						plant.shoot(window, damageMultiplier);
					}
					plant.setPlantFrame(0);
					bullets.add(plant.getBullet());
					isPlaying = true;
				}
				plant.increPlantFrame();
			}
		}
		

    	// Bullets interaction
		if(isPlaying) {
			for(Bullet bullet : bullets) {
				// Make bullets move every 5 centisecond
				if(bullet.getBulletFrame() > 5) {
					bullet.move(window);
					bullet.setBulletFrame(0);
				}
				bullet.increBulletFrame();
				
				// Bullet colliding with the boss zombie
				if(bullet.getLayoutX() > 710) {
					if(boss.getHealthPoints() < 0) {
						music.playWin();
						System.out.println("Boss is dead");
						timer.stop();
						gameOver(window, games, 2);
					}else {
						music.playHit();
						boss.reduceHealth(bullet.getDamage());
						bossHP.setPrefWidth(boss.getHealthPoints() - bullet.getDamage());
						System.out.println("Boss got hit     " + boss.getHealthPoints());
						
						if(boss.getHealthPoints() < 25) {
							bossHP.setStyle("-fx-color: #FF0000");
						}else if(boss.getHealthPoints() < 50) {
							bossHP.setStyle("-fx-color: #FF8000");
						}else if(boss.getHealthPoints() < 100) {
							bossHP.setStyle("-fx-color: #FFFF00");
						}else if(boss.getHealthPoints() < 150) {
							bossHP.setStyle("-fx-color: #80FF00");
						}else {
							bossHP.setStyle("-fx-color: #00FF00");
						}
					}
					
					window.getChildren().remove(bullet);
					bullet.killBullet();
					bullets2Remove.add(bullet);
				}
				
				// Bullet collides with a zombie
				for(Zombie zombie : zombies) {
					if(bullet.getLayoutX() > (zombie.getLayoutX()-20) && bullet.getLayoutY() == zombie.getLayoutY()) {
						music.playHit();
						zombie.reduceHealth(bullet.getDamage());
						System.out.println("I got hit    " + zombie.getHealthPoints());
						window.getChildren().remove(bullet);
						bullet.killBullet();
						
						// if zombie hp falls below zero remove from group
						if(zombie.getHealthPoints() <= 0) {
								window.getChildren().remove(zombie);
								zombies2Remove.add(zombie);
						}
					}
				}
				
				// remove the zombies from the arraylist
				if(zombies.containsAll(zombies2Remove)) {
					zombies.removeAll(zombies2Remove);
					zombies2Remove.clear();
				}
				
				// add dead bullets to the arraylist of bullets to remove
				if(bullet.getDamage() == 0) {
					bullets2Remove.add(bullet);
				}
			}
			
			//remove the bullets from the arraylist
			if(bullets.containsAll(bullets2Remove)) {
				bullets.removeAll(bullets2Remove);
				bullets2Remove.clear();
			}
		}
		
		// Zombies
		if(gameFrame > 200) {
			isPlaying = true;
			zombiesLoop:{
				for(Zombie zombie : zombies) {
					// zombie colliding with shields
					for(Shield shield : shields) {
						if(zombie.getLayoutX() < 380 && zombie.getLayoutY() == shield.getLayoutY()) {
							music.stopAttack();
							music.playAttack();
							shield.reduceHealth(zombie.getDamage());
							shield.setText("" + shield.getHealthPoints());
							zombie.killZombie();
							window.getChildren().remove(zombie);
							zombies2Remove.add(zombie);
							
							// Game over
							if(shield.checkStatus()) { 
								music.playLost(2);
								timer.stop();
								gameOver(window, games, 1);
							}
							break zombiesLoop;
						}
					}
					
					// Halt power-up
					if(zombie.getZombieFrame() > 5) {
						if(haltCooldownFrame < haltCooldown){
							zombie.move(window, 0);
						}else {
							halt.setDisable(false);
							zombie.move(window, 1);
						}
						zombie.setZombieFrame(0);
					}
					zombie.increZombieFrame();
				}
				
				// Spawning zombies
				if(zombieSpawnFrame > zombieSpawnRate) {
					Zombie zombie = new Zombie(window, zombieHealth);
					zombies.add(zombie);
					window.getChildren().addAll(zombie);
					zombieSpawnFrame = 0;
					System.out.println("New zombie spawn:    " + zombieHealth);
				}
			}
		}
		
		// Minds
		if(mindsCooldownFrame > mindsCooldown){
			minds.setDisable(false);
			mindsCooldown = 0;
		}
		
	}
	
	/**
	 * Handles the minds button in the play area
	 * @param window The window of the game.
	 * @param games The List of games.
	 * @return The minds button
	 */
	public Button minds(Group window, List<Button> games) {
		
		minds = new Button();
		mindsText = new Button("" + mindsCount);
		
		minds.setPrefSize(61, 80);
		minds.setLayoutX(40);
		minds.setLayoutY(13);
		
		mindsText.setPrefSize(61, 30);
		mindsText.setLayoutX(38);
		mindsText.setLayoutY(65);
		mindsText.setDisable(true);
		mindsText.setStyle("-fx-font-family: 'Arial Black';"
				+ "-fx-background-color: none;"
				+ "-fx-font-size: 15px;"
				+ "-fx-text-fill: FFC000;"
				+ "-fx-opacity: 1");
		minds.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/minds.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		// Generate a random questionnaireType 
		int type = (int)(((Math.random() * 100) % 5) + 1);
		
		minds.setOnAction(e->{
			music.playMinds();
			questionnaire = new Questionnaire(window, type, proceedButton(window, games), addLifelines(window, games), this.questions, 2);
		});
		
		return minds;	
	}
	
	/**
	 * Handles the eliminate button in the play area
	 * @return The eliminate button
	 */
	public Button eliminate(Group window) {
		
		eliminate = new Button();
		
		eliminate.setPrefSize(54, 80);
		eliminate.setLayoutX(125);
		eliminate.setLayoutY(13);
		eliminate.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/eliminate.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		eliminate.setOnAction(e->{
			if(!zombies.isEmpty()) {
				if(mindsCount > 0) {
					music.playEliminate();
					mindsCount--;
					mindsText.setText("" + mindsCount);
					window.getChildren().remove(zombies.get(0));
					zombies.remove(0);
				}else {
					music.stopError();
					music.playError();
					notificationBar.setVisible(true);
					notificationBar.setText("Not enough minds to eliminate");
					notificationFrame = 200;
//					System.out.println("Not enough minds to eliminate");
				}
				
			}else {
				music.stopError();
				music.playError();
				notificationBar.setVisible(true);
				notificationBar.setText("No more zombies to eliminate");
				notificationFrame = 200;
//				System.out.println("No more zombies to eliminate");
			}
		});
		
		return eliminate;
	}
	
	/**
	 * Handles the amplifyDamage button in the play area
	 * @return The amplifyDamage button
	 */
	public Button amplifyDamage() {
		
		amplify = new Button();
		
		amplify.setPrefSize(53, 80);
		amplify.setLayoutX(200);
		amplify.setLayoutY(13);
		amplify.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/amplify.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		amplify.setOnAction(e->{
			if(mindsCount >= 10) {
				music.stopAmplify();
				music.playAmplify();
				ampDamageCooldown = 1000;
				ampDamageCooldownFrame = 0;
				mindsCount -= 10;
				amplify.setDisable(true);
				mindsText.setText("" + mindsCount);
				notificationBar.setVisible(true);
				notificationBar.setText("Damage amplified");
				notificationFrame = 200;
//				System.out.println("Damage amplified");
			}else {
				music.stopError();
				music.playError();
				notificationBar.setVisible(true);
				notificationBar.setText("Not enough minds to amplify");
				notificationFrame = 200;
//				System.out.println("Not enough minds to amplify");
			}
			
		});
		
		return amplify;
	}
	
	/**
	 * Handles the halt button in the play area
	 * @return The halt button
	 */
	public Button halt() {
		
		halt = new Button();
		
		halt.setPrefSize(56, 80);
		halt.setLayoutX(275);
		halt.setLayoutY(13);
		halt.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/halt.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		halt.setOnAction(e->{
			if(mindsCount >= 5) {
				music.stopStop();
				music.playStop();
				haltCooldown = 300;
				haltCooldownFrame = 0;
				mindsCount -= 5;
				halt.setDisable(true);
				mindsText.setText("" + mindsCount);
				notificationBar.setVisible(true);
				notificationBar.setText("Zombies Halted");
				notificationFrame = 200;
//				System.out.println("Zombies Halted");
			}else {
				music.stopError();
				music.playError();
				notificationBar.setVisible(true);
				notificationBar.setText("Not enough minds to halt");
				notificationFrame = 200;
//				System.out.println("Not enough minds to halt");
			}
			
		});
		
		return halt;
	}
	
	/**
	 * Handles the unlock button in the play area
	 * @param columns The columns in the play area
	 * @return The unlock button
	 */
	public Button unlock(ArrayList<VBox> columns) {
		
		unlock = new Button();
		
		unlock.setPrefSize(54, 80);
		unlock.setLayoutX(350);
		unlock.setLayoutY(13);
		unlock.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/unlock.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		unlock.setOnAction(e->{
			if(this.unlocked < 4) {
				if(mindsCount >= 15) {
					music.stopUnlock();
					music.playUnlock();
					mindsCount -= 15;
					this.plantArea.getChildren().addAll(columns.get(this.unlocked));
					this.unlocked++;
					mindsText.setText("" + mindsCount);
					notificationBar.setVisible(true);
					notificationBar.setText("New column for plants added");
					notificationFrame = 200;
//					System.out.println("New column for plants added");
				}else {
					music.stopError();
					music.playError();
					notificationBar.setVisible(true);
					notificationBar.setText("Not enough minds to unlock another column");
					notificationFrame = 200;
//					System.out.println("Not enoght minds to unlock another column");
				}
				
			}
		});
		
		return unlock;
	}
	
	/**
	 * Handles the proceed button of the game.
	 * @param window The window of the game.
	 * @param games	The List of games.
	 * @return The proceed button for game2.
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
			music.playClick();
			if(!questionnaire.isAnswered) {
				// add 10 seconds cooldown
				mindsCooldown = 1000;
				minds.setDisable(true);
				mindsCooldownFrame = 0;
				notificationBar.setVisible(true);
				notificationBar.setText("10 seconds cooldown");
				notificationFrame = 200;
//				System.out.println("10 seconds cooldown");
				window.getChildren().removeAll(questionnaire.background, questionnaire.question, questionnaire.choices, proceedButton , this.lifelines);
			}else {
				if(questionnaire.answerIsCorrect) {
					// add 10 points
					mindsCount += 10;
					mindsText.setText("" + mindsCount);
					window.getChildren().removeAll(questionnaire.background, questionnaire.question, questionnaire.choices, proceedButton , this.lifelines);
				}else {
					// add 20 seconds cooldown
					notificationBar.setVisible(true);
					notificationBar.setText("20 seconds cooldown");
					notificationFrame = 200;
//					System.out.println(" 20 seconds cooldown");
					mindsCooldown = 2000;
					minds.setDisable(true);
					mindsCooldownFrame = 0;
					window.getChildren().removeAll(questionnaire.background, questionnaire.question, questionnaire.choices, proceedButton , this.lifelines);
				}
			}

		});
		
		return proceedButton;
	}
	
	/**
	 * Handles the lifelines for game2 questionnaire. (not necessarily lifelines but the current number of minds)
	 * @param window The window of the game.
	 * @param games	The List of games.
	 * @return The lifelines for game2 questionnaire.
	 */
	public VBox addLifelines(Group window, List<Button> games) {
		
		Label minds = new Label(); // might change to button and disable
		minds.setPrefSize(61, 80);
		minds.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/minds.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		Button mindsText = new Button(); // might change to button and disable
		mindsText.setPrefSize(61, 30);
		mindsText.setTranslateY(50);
		mindsText.setText("" + mindsCount);
		mindsText.setDisable(true);
		mindsText.setStyle("-fx-font-family: 'Arial Black';"
				+ "-fx-background-color: none;"
				+ "-fx-font-size: 15px;"
				+ "-fx-text-fill: FFC000;"
				+ "-fx-opacity: 1");
		
		Pane pane = new Pane();
		pane.getChildren().addAll(minds, mindsText);
		
		VBox lifelines = new VBox();
		lifelines.getChildren().addAll(pane);
		lifelines.setLayoutX(680);
		lifelines.setLayoutY(35);
		
		this.lifelines = lifelines;
		
		return lifelines;
	}
	
	/**
	 * Handles the how to play window of the game.
	 * @param window The window of the game
	 */
	public void howToPlay(Group window) {
		
		ImageView background = new ImageView(new Image("file:images/game2/HowToPlay/howToPlay1.png"));
		
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
			if(howToPlayCounter < 15) {
				howToPlayCounter++;
				background.setImage(new Image("file:images/game2/HowToPlay/howToPlay" + howToPlayCounter + ".png"));
			}else{
				window.getChildren().removeAll(background, next, back);
				howToPlayCounter = 1 ;
			}
		});
		
		back.setOnAction(e->{
			music.playClick();
			if(howToPlayCounter > 1) {
				howToPlayCounter--;
				background.setImage(new Image("file:images/game2/HowToPlay/howToPlay" + howToPlayCounter + ".png"));
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
		
		ImageView background = new ImageView(new Image("file:images/game2/game2Confirmation.jpg"));
		
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
			music.stopGame2Background();
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
	 * Handles the result of the game.
	 * @param window The window of the game.
	 * @param games The List of games.
	 */
	public void gameOver(Group window, List<Button> games, int result) {
		
		ImageView background = new ImageView(new Image("file:images/game2/result.png"));
		if(result == 2){
			background = new ImageView(new Image("file:images/game2/result2.png"));
		}
			
		Button exit = new Button();
		Button score = new Button();
		
		score.setPrefSize(150, 60);
		score.setLayoutX(340);
		score.setLayoutY(230);
		score.setText("" + gameFrame);
		score.setStyle("-fx-text-fill: White;"
				+ "-fx-font-family: 'Arial Black';"
				+ "-fx-font-size: 30px;");
		score.setBackground(null);
		
		exit.setPrefSize(250, 40);
		exit.setLayoutX(440);
		exit.setLayoutY(350);
		exit.setBackground(null);
		
		exit.setOnAction(e->{
			music.playClick();
			music.stopWin();
			music.stopLost(2);
			menu(window, games);
		});
		
		window.getChildren().setAll(background, score, exit);
	}
}
