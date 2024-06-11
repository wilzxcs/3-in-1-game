import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This class handles the questions of the game
 */
public class Questions {

	private ArrayList<String> question = new ArrayList<String>(); 		// The questions
	private ArrayList<String> choiceA = new ArrayList<String>(); 		// The A choices
	private ArrayList<String> choiceB = new ArrayList<String>(); 		// The B choices
	private ArrayList<String> choiceC = new ArrayList<String>(); 		// The C choices
	private ArrayList<String> choiceD = new ArrayList<String>(); 		// The D choices
	private ArrayList<Integer> takenNumbers = new ArrayList<Integer>(); // The list of taken question numbers
	
	private int random = 0;												//current question number in excel
	
	/**
	 * This constructor takes the questions and choices from the excel sheet and
	 * Stores the question and choices to ArrayLists.
	 */
	public Questions() {
		
		try {
			FileInputStream file = new FileInputStream(new File("Questions/CS125_Questions.xlsx"));
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			for(int i = 1; i < 151; i++) {
				Row row = sheet.getRow(i);
				question.add(row.getCell(2).getStringCellValue());
				choiceA.add(row.getCell(3).getStringCellValue());
				choiceB.add(row.getCell(4).getStringCellValue());
				choiceC.add(row.getCell(5).getStringCellValue());
				choiceD.add(row.getCell(6).getStringCellValue());
			} 
		}catch (Exception e) {
			System.out.println("error in excel reading");
		}
		
		takenNumbers.add(200); // just add trash number above 160 so takenNumbers is not null when used.
	}
	
	/**
	 * Takes a random question from the question ArrayList.
	 * @param categoryNumber The int representation of the category type of the question.
	 * @return the random question taken from the question ArrayList.
	 */
	public String getQuestion(int categoryNumber) {
		
		boolean isTaken = true;
		
		while(isTaken) {
			if(categoryNumber == 1) {
				random = (int)Math.floor(Math.random() * 30);			//Overview
			}else if(categoryNumber == 2) {
				random = (int)Math.floor(Math.random() * (30) + 30);	//Process Management
			}else if(categoryNumber == 3) {
				random = (int)Math.floor(Math.random() * (35) + 60);	//Memory Management
			}else if(categoryNumber == 4) {
				random = (int)Math.floor(Math.random() * (30) + 90);	//Storage Management
			}else if(categoryNumber == 5) {
				random = (int)Math.floor(Math.random() * (30) + 120);	//Security Management
			}
			
			for(int num : takenNumbers) {
				if(num == random) {
					isTaken = true;
					break;
				}
				isTaken = false;
			}
		}
		
		takenNumbers.add(random);
		
		return question.get(random);	
	}
	
	//gives the choices of the current question
	/**
	 * Takes the choices of the current question and stores it into an ArrayList.
	 * @return The ArrayList of choices.
	 */
	public ArrayList<String> getChoices() {
		
		ArrayList<String> choices = new ArrayList<String>(); 
		
		choices.add(choiceA.get(random));
		choices.add(choiceB.get(random));
		choices.add(choiceC.get(random));
		choices.add(choiceD.get(random));
		
		return choices;
	}

}
