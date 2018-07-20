package application;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import javafx.stage.*;

import java.util.Optional;


public class RockPaperScissorsFXGUI extends Application {

	/* Game GUI instance variables (given in the template) */
	private ImageView computerMoveImageView, userMoveImageView;
	private Image rockImage, paperImage, scissorsImage;
	private Text matchOutcomeText, cWinsText, uWinsText, tieText;
	private Text balanceText;
	private Button rockButton, paperButton, scissorsButton;
	private HBox labelBox;

	private RPSGame game;

	private boolean isBetting = false;

	public void start(Stage primaryStage) {

		int betAmount = getBetAmount();

		game = new RPSGame(betAmount);

		/* Load the gameplay GUI objects. */
		/* the image and labels for the computer and user move */
		rockImage = new Image("rock.jpg");
		paperImage = new Image("paper.jpg");
		scissorsImage = new Image("scissors.jpg");

		computerMoveImageView = new ImageView(rockImage);
		userMoveImageView = new ImageView(rockImage);
		HBox imageBox = new HBox(computerMoveImageView, userMoveImageView);
		imageBox.setAlignment(Pos.CENTER);
		imageBox.setSpacing(20);

		Text computerLabel = new Text("Computer's Move");
		computerLabel.setFont(Font.font("Helvetica", 14));
		Text yourLabel = new Text("Your Move");
		yourLabel.setFont(Font.font("Helvetica", 14));
		labelBox = new HBox(computerLabel, yourLabel);
		labelBox.setAlignment(Pos.CENTER);
		labelBox.setSpacing(30);		

		/* the results of each match */
		matchOutcomeText = new Text();
		matchOutcomeText.setFill(Color.GREEN);
		matchOutcomeText.setFont(Font.font("Helvetica", 20));
		
		/* the buttons for the user's play */
		rockButton = new Button("Play Rock");
		rockButton.setOnAction(this::handleUserPlay);
		paperButton = new Button("Play Paper");
		paperButton.setOnAction(this::handleUserPlay);
		scissorsButton = new Button("Play Scissors");
		scissorsButton.setOnAction(this::handleUserPlay);
		HBox buttonBox = new HBox(rockButton, paperButton, scissorsButton);
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.CENTER);

		/* the game statistics */
		cWinsText = new Text();
		cWinsText.setFont(Font.font("Helvetica", 16));
		cWinsText.setFill(Color.BLUE);
		uWinsText = new Text();
		uWinsText.setFont(Font.font("Helvetica", 16));
		uWinsText.setFill(Color.BLUE);
		tieText = new Text();
		tieText.setFont(Font.font("Helvetica", 16));
		tieText.setFill(Color.BLUE);
		HBox statsBox = new HBox(cWinsText, uWinsText, tieText);
		statsBox.setSpacing(10);
		statsBox.setAlignment(Pos.CENTER);
		refreshGameStatistics();

		/* the balance (new) */
		balanceText = new Text();
		balanceText.setFill(Color.PURPLE);
		balanceText.setFont(Font.font("Helvetica", 20));
		if (!isBetting)
			balanceText.setVisible(false);
		refreshBalanceText(); 
		
		/* putting it all together */
		VBox pane = new VBox(imageBox, labelBox, matchOutcomeText, buttonBox, statsBox, balanceText);
		pane.setAlignment(Pos.CENTER);		
		pane.setSpacing(20);
		pane.setStyle("-fx-background-color: white");

		// hide the graphics at the start of the game
		switchDisplayVisibility(false);

		Scene scene = new Scene(pane, 400, 400, Color.TRANSPARENT);
		primaryStage.setTitle("Rock, Paper, Scissors, Go!");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	private void handleUserPlay(ActionEvent event) {
		MoveType userMove;
		MoveType computerMove;
		Outcome outcome;
		WinType winType;

		/* Play a round of the game. */
		userMove = getUserMove(event);
		computerMove = game.generateComputerPlay();
		outcome = game.findWinner(userMove, computerMove);
		winType = game.findWinType(userMove, computerMove);

		/* Refresh the GUI.
		 * This includes showing the graphics if they're not visible already.
		 */
		switchDisplayVisibility(true);
		refreshImage(userMove, userMoveImageView);
		refreshImage(computerMove, computerMoveImageView);
		refreshMatchOutcomeText(outcome, winType);
		refreshGameStatistics();
		refreshBalanceText();		

	}
	
	
	/* private methods to load and show betting dialogs */
	private Optional<ButtonType> loadAndShowConfirmationAlert() {
		// N.B. A confirmation Alert includes OK and Cancel buttons by default.
		// Override the default buttons with a fancy constructor.
		Alert betConfirmationAlert = new Alert(
				AlertType.CONFIRMATION,
				"Do you want to bet?",
				ButtonType.YES,
				ButtonType.NO
				);
		return betConfirmationAlert.showAndWait();
	}

	private Optional<String> loadAndShowBetAmountDialog() {
		// N.B. A TextInputDialog includes OK and Cancel buttons by default.
		TextInputDialog betAmountDialog = new TextInputDialog();
		betAmountDialog.setTitle("Bet Amount");
		betAmountDialog.setContentText("Enter the bet amount (a whole number):");
		return betAmountDialog.showAndWait();
	}

	private void loadAndShowAmountNotANumberAlert() {
		// N.B. An error Alert includes an OK button by default.
		Alert amountNotANumberAlert = new Alert(AlertType.ERROR);
		amountNotANumberAlert.setTitle("Error");
		amountNotANumberAlert.setContentText("Error: amount not a whole number. Betting not enabled.");
		amountNotANumberAlert.showAndWait();
	}

	private void loadAndShowNoAmountEnteredAlert() {
		// N.B. An error Alert includes an OK button by default.
		Alert noAmountEnteredAlert = new Alert(AlertType.ERROR);
		noAmountEnteredAlert.setTitle("Error");
		noAmountEnteredAlert.setContentText("Error: no amount entered. Betting not enabled.");
		noAmountEnteredAlert.showAndWait();
	}

	private MoveType getUserMove(ActionEvent e) {
		Object sourceButton = e.getSource();
		if (sourceButton == rockButton) {
			return MoveType.ROCK;
		} else if (sourceButton == paperButton) {
			return MoveType.PAPER;
		} else {
			return MoveType.SCISSORS;
		}
	}

	private int getBetAmount() {

		Optional<ButtonType> betConfirmationResponse = loadAndShowConfirmationAlert();
		if (betConfirmationResponse.get() == ButtonType.YES) {
			// Aha! The user wants to bet.
			Optional<String> betAmountResponse = loadAndShowBetAmountDialog();
			if (betAmountResponse.isPresent()) {
				try {
					// Try casting the user-input string to an int.
					// If this works, turn on betting.
					int betAmount = Integer.valueOf(betAmountResponse.get());
					isBetting = true;
					return betAmount;
				} catch(Exception e) {
					// The user-input String isn't int-castable.
					loadAndShowAmountNotANumberAlert();
				}
			} else {
				// The user changed his or her mind and canceled.
				loadAndShowNoAmountEnteredAlert();
			}
		}
		return 0;
	}

	private void switchDisplayVisibility(boolean isVisible) {
		userMoveImageView.setVisible(isVisible);
		computerMoveImageView.setVisible(isVisible);
		labelBox.setVisible(isVisible);
	}

	/* private methods to refresh the gameplay GUI */ 
	private void refreshImage(MoveType m, ImageView i) {
		switch (m) {
			case ROCK:		i.setImage(rockImage);
							break;
			case PAPER:		i.setImage(paperImage);
							break;
			case SCISSORS: 	i.setImage(scissorsImage);
							break;
		}
	}

	private void refreshMatchOutcomeText(Outcome o, WinType w) {
		matchOutcomeText.setText(game.outcomeStr(o, w));
	}
	
	private void refreshGameStatistics() {
		cWinsText.setText("Computer Wins: " + game.getComputerWinCount());
		uWinsText.setText("User Wins: " + game.getUserWinCount());
		tieText.setText("Ties: " + game.getTieCount());
	}
	
	private void refreshBalanceText() {
		balanceText.setText("Balance: " + game.getCurrentBalance());
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
