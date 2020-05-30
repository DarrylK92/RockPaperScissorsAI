package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			
			//Top borderPane
			BorderPane topB = new BorderPane();
			root.setTop(topB);
			
			//Top left
			VBox winsV = new VBox();
			topB.setLeft(winsV);
			Label winsText = new Label("Wins");
			winsText.setFont(Font.font("Segoe UI", 15));
			winsV.getChildren().add(winsText);
			Label winsPlayer = new Label("Player: 0");
			winsPlayer.setFont(Font.font("Segoe UI", 15));
			winsV.getChildren().add(winsPlayer);
			Label winsAI = new Label("AI: 0");
			winsAI.setFont(Font.font("Segoe UI", 15));
			winsV.getChildren().add(winsAI);
			topB.setMargin(winsV,  new Insets(10, 10, 10, 10));
			
			//Top right
			VBox streaksV = new VBox();
			topB.setRight(streaksV);
			Label winStreakText = new Label("Win streak");
			winStreakText.setFont(Font.font("Segoe UI", 15));
			streaksV.getChildren().add(winStreakText);
			Label winStreak = new Label("None - ");
			winStreak.setFont(Font.font("Segoe UI", 15));
			streaksV.getChildren().add(winStreak);
			topB.setMargin(streaksV,  new Insets(10, 10, 10, 10));
			
			//Center elements
			VBox centerV = new VBox();
			root.setCenter(centerV);
			
			//Choose text
			Label chooseText = new Label("Choose");
			chooseText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
			centerV.getChildren().add(chooseText);
			centerV.setAlignment(Pos.CENTER);
			
			//Create Rock, Paper, Scissors buttons
			HBox choices = new HBox();
			centerV.getChildren().add(choices);
			choices.setAlignment(Pos.CENTER);
			Button btnRock = new Button();
			btnRock.setGraphic(new ImageView("rock.png"));
			choices.getChildren().add(btnRock);
			Button btnPaper = new Button();
			btnPaper.setGraphic(new ImageView("paper.png"));
			choices.getChildren().add(btnPaper);
			Button btnScissors = new Button();
			btnScissors.setGraphic(new ImageView("scissors.png"));
			choices.getChildren().add(btnScissors);
			
			//Player/AI selections HBox
			HBox selections = new HBox();
			centerV.getChildren().add(selections);
			selections.setAlignment(Pos.CENTER);
			
			//Player selection VBox
			VBox playerSelectionV = new VBox();
			selections.getChildren().add(playerSelectionV);
			selections.setMargin(playerSelectionV, new Insets(10, 10, 10, 10));
			playerSelectionV.setAlignment(Pos.CENTER);
			Label playerSelectionText = new Label("Player");
			playerSelectionText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
			playerSelectionV.getChildren().add(playerSelectionText);
			ImageView playerSelectionImage = new ImageView("none.png");
			playerSelectionV.getChildren().add(playerSelectionImage);
			
			//VS text
			Label vsText = new Label("VS");
			vsText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
			selections.getChildren().add(vsText);
			
			//AI selection VBox
			VBox AISelectionV = new VBox();
			selections.getChildren().add(AISelectionV);
			selections.setMargin(AISelectionV, new Insets(10, 10, 10, 10));
			Label AISelectionText = new Label("      AI");
			AISelectionText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
			AISelectionV.getChildren().add(AISelectionText);
			ImageView AISelectionImage = new ImageView("none.png");
			AISelectionV.getChildren().add(AISelectionImage);
			
			//Winner text
			Label winnerText = new Label("");
			winnerText.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
			centerV.getChildren().add(winnerText);
			
			//Restart button
			Button btnRestart = new Button("Restart");
			btnRestart.setVisible(false);
			btnRestart.setFont(Font.font("Segoe UI", FontWeight.BOLD, 15));
			centerV.getChildren().add(btnRestart);
			
			Scene scene = new Scene(root,400,330);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Rock, Paper, Scissors AI");
			primaryStage.show();
			
			//Create objects
			Director director = new Director(winnerText, playerSelectionImage, AISelectionImage,
					winsPlayer, winsAI, winStreak, chooseText , btnRestart, btnRock, btnPaper, btnScissors);
			Rock rock = new Rock();
			Paper paper = new Paper();
			Scissors scissors = new Scissors();
			
			//Rock, Paper, Scissors buttons functions
			btnRock.setOnAction( e -> {
				director.playerSelection(rock);
			});
			
			btnPaper.setOnAction( e-> {
				director.playerSelection(paper);
			});
			
			btnScissors.setOnAction( e-> {
				director.playerSelection(scissors);
			});
			
			//Restart button
			btnRestart.setOnAction( e -> {
				director.restart();
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
