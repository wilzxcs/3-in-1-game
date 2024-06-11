
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * Class that handles the Bullet of the Plants objects for the Windows vs Linux game
 * @see Game Bundle
 */
public class Zombie extends Button {

	private double healthPoints;
	private int damage = 1;
	private int zombieFrame = 0;
	
	/**
	 * Creates a bullet for the plant object
	 * Set the damage, initial x-position, and initial y-position of the bullet
	 * @param level The level of the bullet
	 */
	
	/**
	 * Creates a new zombie object
	 * Set the health points to the value passed in
	 * @param window The window of the game
	 * @param healthPoints The health points for the new zombie
	 */
	public Zombie(Group window, int healthPoints) {
		this.setPrefSize(40, 40);
		this.setLayoutX(730);
		this.healthPoints = healthPoints;
		this.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/zombie.png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		
		// Generate the zombie in a random row
		int y = (int)((Math.random() * 100) % 5);
		
		if(y == 0){
			y = 128;
		}else if(y == 1){
			y = 192;
		}else if(y == 2){
			y = 256;
		}else if(y == 3){
			y = 320;
		}else {
			y = 384;
		}
		
		this.setLayoutY(y);
		this.setDisable(true);
		this.setStyle("-fx-opacity: 1");
	}
	
	/**
	 * Moves the zombie to the left by the distance passed in
	 */
	public void move(Group window, int distance) {
		this.setLayoutX(this.getLayoutX() - distance);
	}
	
	/**
	 * Get the health points of the zombie
	 * @return The healtPoints of the zombie
	 */
	public double getHealthPoints() {
		return this.healthPoints;
	}
	
	/**
	 * Reduce the health points of the zombie by the damage taken
	 * @param damage The damage the zombie will take
	 */
	public void reduceHealth(double damage) {
		this.healthPoints -= damage;
	}
	
	/**
	 * Gets the zombieFrame of the current Zombie
	 * @return the zombieFrame of the current Zombie
	 */
	public int getZombieFrame() {
		return this.zombieFrame;
	}
	
	/**
	 * Sets the zombieFrame of the current zombie to the value passed in
	 * @param zombieFrame 
	 */
	public void setZombieFrame(int zombieFrame) {
		this.zombieFrame = zombieFrame;
	}
	
	/**
	 * Increment the zombieFrame of the current zombie by 1
	 */
	public void increZombieFrame() {
		this.zombieFrame++;
	}
	
	/**
	 * Kill the zombie by settting its damage to 0
	 */
	public void killZombie() {
		this.damage = 0;
	}
	
	/**
	 * Get the damage of the zombie
	 * @return The damage of the zombie
	 */
	public int getDamage() {
		return this.damage;
	}

}
