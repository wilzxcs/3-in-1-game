
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

/**
 * Class that handles the Shield objects for the Windows vs Linux game
 * @see Game Bundle
 */
public class Shield extends Button{

	private int healthPoints = 10;

	/**
	 * Creates a new Shield object
	 * @param window The window of the game.
	 */
	public Shield(Group window, int layoutX, int layoutY) {
		this.setLayoutX(layoutX);
		this.setLayoutY(layoutY);
		this.setPrefSize(64, 64);
		this.setStyle("-fx-font-family: Arial;"
				+ "-fx-font-weight: bolder;");
		this.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/shield.png"),
			BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}
	
	/**
	 * Checks the status of the shield if it is dead or not
	 * @return TRUE if shield is dead, else FALSE
	 */
	public boolean checkStatus() {
		if(healthPoints == 0) {
			System.out.println("Shield died, game over!");
			return true;
		}
		return false;
	}
	
	/**
	 * Resets the health points of the shield
	 */
	public void resetHealthPoints() {
		this.healthPoints = 10;
		this.setText("" + this.healthPoints);
	}
	
	/**
	 * Reduces the health points of the shield by the damage
	 * @param damage The damage to the shield
	 */
	public void reduceHealth(int damage) {
		this.healthPoints -= damage;
	}
	
	/**
	 * Increases the health points of the shield by 1
	 */
	public void increHealthPoints() {
		this.healthPoints++;
	}
	
	/**
	 * Get the health points of the shield object 
	 * @return the healthPoints of the shield object
	 */
	public int getHealthPoints() {
		return this.healthPoints;
	}

}
