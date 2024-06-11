
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * Class that handles the Plant objects for the Windows vs Linux game
 * @see Game Bundle
 */
public class Plant extends Button{

	private int level = 0;
	private int plantFrame = 0;
	private int plantXPosition = 0;
	private int plantYPosition = 0;
	
	Bullet bullet;

	/**
	 * Creates a new plant object
	 * @param window The window of the game
	 * @param xPosition The value of the x-position of the plant
	 * @param yPosition The value of the y-position of the plant
	 */
	public Plant(Group window, int xPosition, int yPosition) {
		this.plantXPosition = xPosition;
		this.plantYPosition = yPosition;
		this.setPrefSize(75, 64);
		this.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/plant" + this.level + ".png"),
			BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}
	
	/**
	 * Makes the Plant object shoot
	 * @param window The window of the game.
	 */
	public void shoot(Group window, double damageMultiplier) {
		bullet = new Bullet(plantXPosition, plantYPosition, (this.level * damageMultiplier), window);
		window.getChildren().addAll(bullet);
	}
	
	/**
	 * Increment the current level of the Plant object by 1
	 */
	public void incrementLevel() {
		if(this.level < 9) {
			this.level++;
			this.setPlantFrame(200);
			this.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/plant" + this.level + ".png"),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
		}
	}
	
	/**
	 * Get the level of the Plant object
	 * @return the level of the Plant object
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 * Resets the level of the plant
	 */
	public void resetLevel(){
		this.level = 0;
	}
	
	/**
	 * Gets the plantFrame of the current Plant
	 * @return the plantFrame of the current Plant
	 */
	public int getPlantFrame() {
		return this.plantFrame;
	}
	
	/**
	 * Sets the plantFrame of the currentPlant to the value passed in
	 * @param plantFrame
	 */
	public void setPlantFrame(int plantFrame) {
		this.plantFrame = plantFrame;
	}
	
	/**
	 * Increment the plantFrame of the current Plant by 1
	 */
	public void increPlantFrame() {
		this.plantFrame++;
	}
	
	/**
	 * Get the bullets of the Plant object
	 * @return The arraylist of bullets
	 */
	public Bullet getBullet(){
		return this.bullet;
	}

}
