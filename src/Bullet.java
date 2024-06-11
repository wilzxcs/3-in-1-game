
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
public class Bullet extends Button {

	private double damage = 0;
	private int bulletFrame = 0;
	
	/**
	 * Creates a bullet for the plant object
	 * Set the damage, initial x-position, and initial y-position of the bullet
	 * @param x The value of the x-position of the bullet
	 * @param y The value of the y-position of the bullet
	 * @param level The level of the bullet
	 */
	public Bullet(int x, int y, double level, Group window) {
		this.setPrefSize(40, 40);
		this.damage = level;
		this.setLayoutX(x);
		this.setLayoutY(y);
		this.setDisable(true);
		this.setStyle("-fx-opacity: 1");
		this.setBackground(new Background(new BackgroundImage( new Image("File:images/game2/gameplay/bullet" + (int)level + ".png"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
	}
	
	/**
	 * Moves the bullet to the right by 7px
	 */
	public void move(Group window) {
		this.setLayoutX(this.getLayoutX() + 7);
	}
	
	/**
	 * Gets the bulletFrame of the current Bullet
	 * @return the bulletFrame of the current Bullet
	 */
	public int getBulletFrame() {
		return this.bulletFrame;
	}
	
	/**
	 * Sets the bulletFrame of the current bullet to the value passed in
	 * @param bulletFrame
	 */
	public void setBulletFrame(int bulletFrame) {
		this.bulletFrame = bulletFrame;
	}
	
	/**
	 * Increment the bulletFrame of the current bullet by 1
	 */
	public void increBulletFrame() {
		this.bulletFrame++;
	}
	
	/**
	 * Get the damage of the bullet
	 * @return The damage of the bullet
	 */
	public double getDamage() {
		return this.damage;
	}
	
	/**
	 * Kill the bullet by setting its damage to 0
	 */
	public void killBullet() {
		this.damage = 0;
	}

}
