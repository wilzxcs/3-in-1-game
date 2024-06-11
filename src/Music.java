import java.io.File;
import java.util.ArrayList;

import javafx.scene.media.AudioClip;

/**
 * The class that handles the music of the game.
 * @see Main
 */
public class Music {

	private boolean isMusicOn = true;	// The boolean if music is on
	
	AudioClip bundleBackground;			// Game bundle background music
	AudioClip game1Background;			// Game 1 background music
	AudioClip game2Background;			// Game 2 background music
	AudioClip game3Background;			// Game 3 background music
		
	AudioClip click;					// Gameplay
	AudioClip correct;	
	AudioClip wrong;	
	AudioClip hover;

	AudioClip error;					// game 2 sound effects	
	AudioClip fix;
	AudioClip levelup;					
	AudioClip minds;
	AudioClip stop;					
	AudioClip attack;
	AudioClip eliminate;
	AudioClip hit;
	AudioClip amplify;
	AudioClip unlock;
	
	AudioClip bounce;					// game 3 sound effects
	AudioClip lostlife;
	AudioClip addlife;
	AudioClip grow;
	AudioClip shrink;
	AudioClip reset;
	
	AudioClip win;						//Result
	AudioClip win3;
	AudioClip lost1;
	AudioClip lost2;
	AudioClip lost3;

	/**
	 * This constructor initializes the audio clips.
	 */
	public Music() {
		
		ArrayList<String> songs = new ArrayList<String>();
		
		songs.add("music/game1background.mp3");				//0
		songs.add("music/ClickEffect.mp3");					//1
		songs.add("music/correct.wav");						//2
		songs.add("music/wrong.wav");						//3
		songs.add("music/WIN.mp3");							//4
		songs.add("music/LOST.mp3");						//5
		
		songs.add("music/game2background.mp3");				//6
		songs.add("music/error.mp3");						//7
		songs.add("music/fix.m4a");							//8
		songs.add("music/levelup.mp3");						//9
		songs.add("music/minds.m4a");						//10
		songs.add("music/stop.mp3");						//11
		songs.add("music/attack.m4a");						//12
		songs.add("music/eliminate.mp3");					//13
		songs.add("music/LOST2.mp3");						//14
		songs.add("music/hit.mp3");							//15
		songs.add("music/amplify.mp3");						//16
		songs.add("music/unlock.mp3");						//17
		
		songs.add("music/game3Background.mp3");				//18
		songs.add("music/bounce.mp3");						//19
		songs.add("music/lostlife.mp3");					//20
		songs.add("music/addlife.m4a");						//21
		songs.add("music/grow.mp3");						//22
		songs.add("music/shrink.mp3");						//23
		songs.add("music/reset.mp3");						//24
		songs.add("music/WIN3.mp3");						//25
		songs.add("music/LOST3.mp3");						//26
		
		songs.add("music/hover.mp3");						//27
		songs.add("music/bundleBackground.mp3");			//28
		
		
		game1Background = new AudioClip(new File(songs.get(0)).toURI().toString());	
		game1Background.setVolume(.07);
		game1Background.setCycleCount(20);
		
		click = new AudioClip(new File(songs.get(1)).toURI().toString());
		correct = new AudioClip(new File(songs.get(2)).toURI().toString());
		wrong = new AudioClip(new File(songs.get(3)).toURI().toString());
		win = new AudioClip(new File(songs.get(4)).toURI().toString());
		lost1 = new AudioClip(new File(songs.get(5)).toURI().toString());
		
		game2Background = new AudioClip(new File(songs.get(6)).toURI().toString());	
		game2Background.setVolume(.08);
		game2Background.setCycleCount(20);
		
		error = new AudioClip(new File(songs.get(7)).toURI().toString());
		fix = new AudioClip(new File(songs.get(8)).toURI().toString());
		levelup = new AudioClip(new File(songs.get(9)).toURI().toString());
		minds = new AudioClip(new File(songs.get(10)).toURI().toString());
		stop = new AudioClip(new File(songs.get(11)).toURI().toString());
		attack = new AudioClip(new File(songs.get(12)).toURI().toString());
		eliminate = new AudioClip(new File(songs.get(13)).toURI().toString());
		lost2 = new AudioClip(new File(songs.get(14)).toURI().toString());
		hit = new AudioClip(new File(songs.get(15)).toURI().toString());
		amplify = new AudioClip(new File(songs.get(16)).toURI().toString());
		unlock = new AudioClip(new File(songs.get(17)).toURI().toString());

		game3Background = new AudioClip(new File(songs.get(18)).toURI().toString());	
		game3Background.setVolume(.6);
		game3Background.setCycleCount(20);
		
		bounce = new AudioClip(new File(songs.get(19)).toURI().toString());
		lostlife = new AudioClip(new File(songs.get(20)).toURI().toString());
		addlife = new AudioClip(new File(songs.get(21)).toURI().toString());
		grow = new AudioClip(new File(songs.get(22)).toURI().toString());
		shrink = new AudioClip(new File(songs.get(23)).toURI().toString());
		reset = new AudioClip(new File(songs.get(24)).toURI().toString());
		win3 = new AudioClip(new File(songs.get(25)).toURI().toString());
		lost3 = new AudioClip(new File(songs.get(26)).toURI().toString());
		
		hover = new AudioClip(new File(songs.get(27)).toURI().toString());
		bundleBackground = new AudioClip(new File(songs.get(28)).toURI().toString());
		
	}

	/**
	 * plays the game bundle background music.
	 */
	public void playBundleBackground() {
		if(isMusicOn) {
			bundleBackground.play();
		}
	}
	
	/**
	 * Stop playing the game bundle background music.
	 */
	public void stopBundleBackground() {
		bundleBackground.stop();
	}
	
	/**
	 * plays the game1 background music.
	 */
	public void playGame1Background() {
		if(isMusicOn) {
			game1Background.play();
		}
	}
	
	/**
	 * Stop playing the game1 background music.
	 */
	public void stopGame1Background() {
		game1Background.stop();
	}
	
	/**
	 * plays the game2 background music.
	 */
	public void playGame2Background() {
		if(isMusicOn) {
			game2Background.play();
		}
	}
	
	/**
	 * Stop playing the game2 background music.
	 */
	public void stopGame2Background() {
		game2Background.stop();
	}
	
	/**
	 * plays the game3 background music.
	 */
	public void playGame3Background() {
		if(isMusicOn) {
			game3Background.play();
		}
	}
	
	/**
	 * Stop playing the game3 background music.
	 */
	public void stopGame3Background() {
		game3Background.stop();
	}
	
	/**
	 * plays the Click sound effect.
	 */
	public void playClick() {
		if(isMusicOn) {
			click.play();
		}
	}
	
	/**
	 * plays the correct sound effect.
	 */
	public void playCorrect() {
		if(isMusicOn) {
			correct.play();
		}
	}
	
	/**
	 * plays the wrong sound effect.
	 */
	public void playWrong() {
		if(isMusicOn) {
			wrong.play();
		}
	}
	
	/**
	 * plays the hover sound effect.
	 */
	public void playHover() {
		if(isMusicOn) {
			hover.play();
		}
	}
	
	/**
	 * plays the win sound effect.
	 */
	public void playWin() {
		if(isMusicOn) {
			win.play();
		}
	}
	
	/**
	 * Stop playing the win sound effect.
	 */
	public void stopWin() {
		win.stop();
	}
	
	/**
	 * plays the lost sound effect.
	 */
	public void playLost(int game) {
		if(isMusicOn) {
			if(game == 1) {
				lost1.play();
			}else if(game == 2) {
				lost2.play();
			}else {
				lost3.play();
			}
			
		}
	}
	
	/**
	 * Stop playing the lost sound effect.
	 */
	public void stopLost(int game) {
		if(game == 1) {
			lost1.stop();
		}else if(game == 2) {
			lost2.stop();
		}else {
			lost3.stop();
		}
	}
	
	/**
	 * Plays the error sound effect
	 */
	public void playError() {
		if(isMusicOn) {
			error.play();
		}
	}
	
	/**
	 * Stop playing the error sound effect
	 */
	public void stopError() {
		error.stop();
	}
	
	/**
	 * plays the fix sound effect
	 */
	public void playFix() {
		if(isMusicOn) {
			fix.play();
		}
	}
	
	/**
	 * stop playing the fix sound effect
	 */
	public void stopFix() {
		fix.stop();
	}
	
	/**
	 * plays the level up sound effect
	 */
	public void playLevelup() {
		if(isMusicOn) {
			levelup.play();
		}
	}
	
	/**
	 * stops playing the level up sound effect
	 */
	public void stoplevelup() {
		levelup.stop();
	}
	
	/**
	 * plays the minds sound effect
	 */
	public void playMinds() {
		if(isMusicOn) {
			minds.play();
		}
	}
	
	/**
	 * stops playing the minds sound effect
	 */
	public void stopMinds() {
		minds.stop();
	}
	
	/**
	 * plays the stop sound effect
	 */
	public void playStop() {
		if(isMusicOn) {
			stop.play();
		}
	}
	
	/**
	 * stops playing the stop sound effect
	 */
	public void stopStop() {
		stop.stop();
	}
	
	/**
	 * plays the attack sound effect
	 */
	public void playAttack() {
		if(isMusicOn) {
			attack.play();
		}
	}
	
	/**
	 * stops playing the attack sound effect
	 */
	public void stopAttack() {
		attack.stop();
	}
	
	/**
	 * plays the eliminate sound effect
	 */
	public void playEliminate() {
		if(isMusicOn) {
			eliminate.play();
		}
	}
	
	/**
	 * stops playing the eliminate sound effect
	 */
	public void stopEliminate() {
		eliminate.stop();
	}

	/**
	 * plays the hit sound effect
	 */
	public void playHit() {
		if(isMusicOn) {
			hit.play();
		}
	}
	
	/**
	 * stops playing the hit sound effect
	 */
	public void stopHit() {
		hit.stop();
	}

	/**
	 * plays the amplify sound effect
	 */
	public void playAmplify() {
		if(isMusicOn) {
			amplify.play();
		}
	}
	
	/**
	 * stops playing the amplify sound effect
	 */
	public void stopAmplify() {
		amplify.stop();
	}	
	
	/**
	 * plays the unlock sound effect
	 */
	public void playUnlock() {
		if(isMusicOn) {
			unlock.play();
		}
	}
	
	/**
	 * stops playing the unlock sound effect
	 */
	public void stopUnlock() {
		unlock.stop();
	}
	
	
	/**
	 * plays the ball bounce sound effect
	 */
	public void playBounce() {
		if(isMusicOn) {
			bounce.play();
		}
	}
	
	/**
	 * plays the lost life sound effect
	 */
	public void playLostlife() {
		if(isMusicOn) {
			lostlife.play();
		}
	}
	
	/**
	 * plays the add life sound effect
	 */
	public void playAddlife() {
		if(isMusicOn) {
			addlife.play();
		}
	}

	/**
	 * plays the racket grow sound effect
	 */
	public void playGrow() {
		if(isMusicOn) {
			grow.play();
		}
	}
	
	/**
	 * plays the racket shrink sound effect
	 */
	public void playShrink() {
		if(isMusicOn) {
			shrink.play();
		}
	}
	
	/**
	 * plays the ball speed reset sound effect
	 */
	public void playReset() {
		if(isMusicOn) {
			reset.play();
		}
	}
	
	/**
	 * plays the win sound effect for game 3
	 */
	public void playWin3() {
		if(isMusicOn) {
			win3.play();
		}
	}
	
	/**
	 * stops playing the win sound effect of game 3
	 */
	public void stopWin3() {
		win3.stop();
	}
	

	/**
	 * Toggles the music off if the music is on.
	 * Toggles the music on if the music is off.
	 */
	public void toggleMusic() {
		if(isMusicOn) {
			isMusicOn = false;
		}else {
			isMusicOn = true;
		}
	}
	
	/**
	 * A getter method for the current state of music.
	 * @return isMusicOn
	 */
	public Boolean getMusic() {
		return isMusicOn;
	}
	
}
