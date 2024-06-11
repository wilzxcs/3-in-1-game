import java.util.ArrayList;
import java.util.Collections;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Class that handles the questionnaire for the 3 games
 * @see Game Bundle
 */
public class Questionnaire {
	
	public ScrollPane question;					// The question
	public GridPane choices;					// The choices
	public ImageView background;				// The background of the questionnaire
	public int playerAttempt = 1;			

	private Music music = new Music();
	
	public ArrayList<ScrollPane> choicesList;	
	public ArrayList<Text> buttonChoices;
	
	public boolean answerIsCorrect = true;		// A boolean if the answer is correct
	public boolean isAnswered = false;			// A boolean if the question was answered
	
	/**
	 * Create the questionnaire
	 * @param window The window of the game.
	 * @param questionnaireType The category of questions
	 * 		questionnaireType (1) --> overview
	 * 		questionnaireType (2) --> process management
	 * 		questionnaireType (3) --> memory management
	 * 		questionnaireType (4) --> storage management
	 * 		questionnaireType (5) --> security management
	 * @param proceedButton The proceed button of the current game
	 * @param lifelines The lifelines of the current game
	 * @param questions The questions of the current game
	 * @param game The game number
	 * 		game (1)  --> Are you smarter than bill
	 * 		game (2)  --> Windows VS Linux
	 * 		game (3)  --> OS Breaker
	 */
	public Questionnaire(Group window, int questionnaireType, Button proceedButton, VBox lifelines, Questions questions, int game) { 
		
		background = new ImageView(new Image("file:images/game" + game + "/game" + game + "Background.jpg"));
		
		if(questionnaireType == 1) {
			addQuestion(questions, 1);
			addChoices(questions, 1, proceedButton);
		}else if(questionnaireType == 2) {
			addQuestion(questions, 2);
			addChoices(questions, 2, proceedButton);
		}else if(questionnaireType == 3) {
			addQuestion(questions, 3);
			addChoices(questions, 3, proceedButton);
		}else if(questionnaireType == 4) {
			addQuestion(questions, 4);
			addChoices(questions, 4, proceedButton);
		}else if(questionnaireType == 5) {
			addQuestion(questions, 5);
			addChoices(questions, 5, proceedButton);
		}
		
		window.getChildren().addAll(background, question, choices, proceedButton, lifelines);
	}
	
	/**
	 * Adds the question to the questionnaire
	 * @param questions The questions to be added to the questionnaire
	 * @param questionnaireType The category of questions
	 * 		questionnaireType (1) --> overview
	 * 		questionnaireType (2) --> process management
	 * 		questionnaireType (3) --> memory management
	 * 		questionnaireType (4) --> storage management
	 * 		questionnaireType (5) --> security management
	 */
	public void addQuestion(Questions questions, int questionnaireType) { 
		
		Text question = new Text(questions.getQuestion(questionnaireType));			
		question.setWrappingWidth(570);
		question.setTranslateX(15);
		question.setTranslateY(15);
		question.setFill(Color.WHITE);
		question.setTextAlignment(TextAlignment.CENTER);
		question.setFont(Font.font("Verdana", FontWeight.NORMAL, FontPosture.REGULAR, 15)); 
		
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(question);
		scrollPane.setStyle("-fx-background: #222A35;"
					+ "-fx-border-color: #FFFFFF"); //222A35
//		}else if(game == 2) {
//			scrollPane.setStyle("-fx-background: #262626;"
//					+ "-fx-border-color: #FFFFFF"); //262626
//		}else {
//			scrollPane.setStyle("-fx-background: #262626;"
//					+ "-fx-border-color: #FFFFFF"); //222A35
//		}
		scrollPane.setPrefSize(600, 200);
		scrollPane.setLayoutX(50);
		scrollPane.setLayoutY(30);
		
		this.question = scrollPane;
	}
	
	/**
	 * Adds the choices to the questionnaire
	 * @param questions The questions to be added to the questionnaire
	 * @param questionnaireType The category of questions
	 * 		questionnaireType (1) --> overview
	 * 		questionnaireType (2) --> process management
	 * 		questionnaireType (3) --> memory management
	 * 		questionnaireType (4) --> storage management
	 * 		questionnaireType (5) --> security management
	 * @param proceedButton The proceed button of the current button
	 */
	public void addChoices(Questions questions, int questionnaireType, Button proceedButton) {
		
		String[] letterChoices = new String[] {"A", "B", "C", "D"};
		ArrayList<String> strChoices = new ArrayList<String>(questions.getChoices());
		ArrayList<Text> buttonChoices = new ArrayList<Text>();
		ArrayList<HBox> choices = new ArrayList<HBox>();
		ArrayList<ScrollPane> choicesScrollPane = new ArrayList<ScrollPane>();
		
		GridPane choiceGrid = new GridPane();
		choiceGrid.setHgap(26);
		choiceGrid.setVgap(15);
		choiceGrid.setLayoutX(50);
		choiceGrid.setLayoutY(250);
		
		for(int i = 0, j = 0, k = 0; i < 4; i++) {
			
			Text choiceText = new Text();
			choiceText.setWrappingWidth(220);
			choiceText.setTranslateX(7);
			choiceText.setTranslateY(7);
			choiceText.setFill(Color.WHITE);
			choiceText.setTextAlignment(TextAlignment.JUSTIFY);
			choiceText.setFont(Font.font("Verdana", 12));

			buttonChoices.add(choiceText);
			
			ScrollPane choiceScrollPane = new ScrollPane();
			choiceScrollPane.setContent(choiceText);
			choiceScrollPane.setStyle("-fx-background: #222A35"); //222A35
//			}else if(game == 2) {
//				choiceScrollPane.setStyle("-fx-background: #222A35"); //262626
//			}else {
//				choiceScrollPane.setStyle("-fx-background: #222A35"); //222A35
//			}
			choiceScrollPane.setPrefSize(235, 70);
			
			choicesScrollPane.add(choiceScrollPane);
			
			Button letter = new Button(letterChoices[i]);
			letter.setFont(Font.font("Verdana", 18));
			letter.setTextFill(Color.WHITE);
			letter.setPrefSize(50, 70);
			letter.setBackground(null);
			
			HBox choice = new HBox(letter, choiceScrollPane);
			choice.setStyle("-fx-border-color: #FFFFFF"); 		//change to setBackground
			choice.setBackground(new Background(new BackgroundFill(new Color(.1333333, .1647059, 0.207843, 1), new CornerRadii(0), Insets.EMPTY)));
			choices.add(choice);

			choiceScrollPane.setOnMouseClicked(e->{
				
				isAnswered = true;
				
				if(choiceText.getText().equals(strChoices.get(0))) {	// got correct answer	
					music.playCorrect();
					choice.setBackground(new Background(new BackgroundFill(new Color(.219608, .341176, .137255, 1), new CornerRadii(0), Insets.EMPTY)));
					proceedButton.setDisable(false);
					
					for(ScrollPane b : choicesScrollPane) {
						b.getParent().setDisable(true);
					}
					
				}else {													//got wrong answer	
					music.playWrong();
					if(playerAttempt == 2) {
						playerAttempt--;
					}else {
						answerIsCorrect = false;	
						proceedButton.setDisable(false);
						
						for(ScrollPane b : choicesScrollPane) {
							b.getParent().setDisable(true);
						}
					}
					choiceScrollPane.setDisable(true);
					choice.setBackground(new Background(new BackgroundFill(new Color(.752941, 0, 0, 1), new CornerRadii(0), Insets.EMPTY)));
				}
			});
			
			if(i == 1){
				j = 0;
				k = 1;
			}else if(i == 2){
				j = 1;
				k = 0;
			}else if(i == 3){
				j = 1;
				k = 1;
			}
			choiceGrid.add(choice, j, k);
		}
		
		//shuffles the button for the choices on the Grid so we get a random order everytime
		Collections.shuffle(buttonChoices);			
		
		for(int i = 0; i < 4; i++){
			buttonChoices.get(i).setText(strChoices.get(i));
		}
		
		this.choicesList = choicesScrollPane;
		this.buttonChoices = buttonChoices;
		
		this.choices = choiceGrid;
	}
	
}
