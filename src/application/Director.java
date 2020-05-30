package application;

import java.util.Random;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Director {
	int playerWins = 0;
	int aiWins = 0;
	boolean streakIsPlayer = true;
	int streakCount = 0;
	
	Label winnerText;
	ImageView playerSelectionImage;
	ImageView AISelectionImage;
	Label winsPlayer;
	Label winsAI;
	Label winStreak;
	Label chooseText;
	Button btnRestart;
	Button btnRock;
	Button btnPaper;
	Button btnScissors;
	
	boolean gameInProgress = true;
	
	Archive archive = new Archive();
	Analyzer analyzer = new Analyzer();
	
	//Constructor
	public Director(Label wt, ImageView psi, ImageView asi, Label wp, Label wa, Label ws, Label ct, Button rs, Button r, Button p, Button s) {
		winnerText = wt;
		playerSelectionImage = psi;
		AISelectionImage = asi;
		winsPlayer = wp;
		winsAI = wa;
		winStreak = ws;
		chooseText = ct;
		btnRestart = rs;
		btnRock = r;
		btnPaper = p;
		btnScissors = s;
	}
	
	/**
	 * @return Returns the player's current wins
	 */
	public int getPlayerWins() {
		return playerWins;
	}
	
	/**
	 * @return Returns the AI's current wins
	 */
	public int getAIWins() {
		return aiWins;
	}
	
	/**
	 * Adds 1 to the player's current wins
	 */
	public void incrementPlayerWins() {
		playerWins += 1;
		winsPlayer.setText("Player: " + playerWins);
	}
	
	/**
	 * Adds 1 to the AI's current wins
	 */
	public void incrementAIWins() {
		aiWins += 1;
		winsAI.setText("AI: " + aiWins);
	}
	
	/**
	 * Increments the streak count if the owner of the current streak won the last game.
	 * Otherwise changes the streak owner and sets the streak to 1.
	 * Afterwards, updates the UI.
	 * @param isPlayer Whether the player was the last winner or not
	 */
	public void incrementStreak(boolean isPlayer) {
		//Set streakCount based on who wins
		if (streakIsPlayer != isPlayer) {
			streakIsPlayer = isPlayer;
			streakCount = 1;
		} else {
			streakCount += 1;
		}
		
		//Update UI
		if (isPlayer) {
			winStreak.setText("Player - " + streakString(streakCount));
		} else {
			winStreak.setText("AI - " + streakString(streakCount));
		}
	}
	
	/**
	 * @param n The size of the win streak
	 * @return Returns a string of tallies equal to the win streak.
	 */
	public String streakString(int n) {
		//validation
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		
		//base case
		if (n == 1) {
			return "|";
		} else {
			String s = streakString(n - 1);
			return s + "|";
		}
	}
	
	/**
	 * Resets the win streak if a tie happens
	 */
	public void resetStreak() {
		streakCount = 0;
		winStreak.setText("None - ");
	}
	
	/**
	 * @param selection The selection made by the player
	 */
	public void playerSelection(Selection selection) {
		//Check if the game is over or not
		if (!gameInProgress) {
			return;
		}
		
		//Get AI answer
		String aiSelection = "";
		aiSelection = analyzer.analyze(archive);
		
		//Set imageViews
		playerSelectionImage.setImage(new Image(selection + ".png"));
		AISelectionImage.setImage(new Image(aiSelection + ".png"));
		
		//Find winner
		if (aiSelection.equals(selection.getWinsAgainst())) {
			//Player wins
			incrementPlayerWins();
			incrementStreak(true);
			winnerText.setText("Player wins!");
		} else if (aiSelection.equals(selection.getLosesAgainst())) {
			//Player loses
			incrementAIWins();
			incrementStreak(false);
			winnerText.setText("AI wins!");
		} else {
			//Tie
			resetStreak();
			winnerText.setText("It's a Tie!");
		}
		
		//Add player answer to archive
		archive.add(selection.toString());
		
		//Check for final winner
		checkForFinalWinner();
	}
	
	/**
	 * Checks if there is a final winner and stops the game if so
	 */
	public void checkForFinalWinner() {
		if (getPlayerWins() >= 20) {
			chooseText.setText("Final Winner: Player!");
			btnRestart.setVisible(true);
			gameInProgress = false;
		}else if (getAIWins() >= 20) {
			chooseText.setText("Final Winner: AI!");
			btnRestart.setVisible(true);
			gameInProgress = false;
		}
	}
	
	/**
	 * Restarts the game
	 */
	public void restart() {
		chooseText.setText("Choose");
		btnRestart.setVisible(false);
		gameInProgress = true;
		playerWins = 0;
		aiWins = 0;
		streakIsPlayer = true;
		streakCount = 0;
		playerSelectionImage.setImage(new Image("none.png"));
		AISelectionImage.setImage(new Image("none.png"));
		winnerText.setText("");
		winStreak.setText("None - ");
		winsPlayer.setText("Player: 0");
		winsAI.setText("AI: 0");
	}
}
