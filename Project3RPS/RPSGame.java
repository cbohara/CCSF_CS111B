package application;

import java.security.SecureRandom;

// enums moved outside of the class so that the GUI can refer to them directly
enum Outcome { USER_WIN, COMPUTER_WIN, TIE };
enum MoveType { ROCK, PAPER, SCISSORS };
enum WinType { ROCK_BEATS_SCISSORS, PAPER_BEATS_ROCK, SCISSORS_BEATS_PAPER, TIE };

public class RPSGame {
	private static final SecureRandom randomNumbers = new SecureRandom();	
		
	private int userWinCount;
	private int computerWinCount;
	private int tieCount;
	private int betAmount;
	private int currentBalance;

	public RPSGame() {}
	
	public RPSGame(int betAmount) {
		this.betAmount = betAmount;
	}
	
	public int getUserWinCount() {
		return userWinCount;
	}
	
	public int getComputerWinCount() {
		return computerWinCount;
	}
	
	public int getTieCount() {
		return tieCount;
	}
	
	public int getBetAmount() {
		return betAmount;
	}
	
	public int getCurrentBalance() {
		return currentBalance;
	}
		
	public void setUserWinCount(int userWinCount) {
		this.userWinCount = userWinCount;
	}
	
	public void setComputerWinCount(int computerWinCount) {
		this.computerWinCount = computerWinCount;
	}
	
	public void setTieCount(int tieCount) {
		this.tieCount = tieCount;
	}
	
	public void setBetAmount(int betAmount) {
		this.betAmount = betAmount;
	}
	
	public void setCurrentBalance(int currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	public MoveType generateComputerPlay() {
		MoveType move = null;
		int random = randomNumbers.nextInt(3);
		switch (random) {
			case 0:
				move = MoveType.ROCK;
				break;
			case 1:
				move = MoveType.PAPER;
				break;
			case 2:
				move = MoveType.SCISSORS;
				break;
		}
		return move;
	}
	
	private Outcome userWin() {
		userWinCount++;
		currentBalance += betAmount;
		return Outcome.USER_WIN;
	}
	
	private Outcome computerWin() {
		computerWinCount++;
		currentBalance -= betAmount;
		return Outcome.COMPUTER_WIN;
	}
		
	public Outcome findWinner(MoveType userMove, MoveType computerMove) {
		Outcome outcome = null;
		
		if (userMove == computerMove) {
			tieCount++;
			outcome = Outcome.TIE;
		} 
		
		if (userMove == MoveType.ROCK) {
			if (computerMove == MoveType.SCISSORS) {
				outcome = userWin();
			} else if (computerMove == MoveType.PAPER) {
				outcome = computerWin();
			}
		}
		
		if (userMove == MoveType.SCISSORS) {
			if (computerMove == MoveType.PAPER) {
				outcome = userWin();
			} else if (computerMove == MoveType.ROCK) {
				outcome = computerWin();
			}
		}
		
		if (userMove == MoveType.PAPER) {
			if (computerMove == MoveType.ROCK) {
				outcome = userWin();
			} else if (computerMove == MoveType.SCISSORS) {
				outcome = computerWin();
			}
		}
		return outcome;
	}

	private boolean movesMatch(
			MoveType moveA,
			MoveType moveB,
			MoveType type1,
			MoveType type2) {
		// do moves A and B match types 1 and 2, in either order? 
		return ((moveA == type1 && moveB == type2)
				|| (moveA == type2 && moveB == type1));
	}
	
	public WinType findWinType(MoveType userMove, MoveType computerMove) {
		// Identify the type of win.
		if (userMove == computerMove) {
			return WinType.TIE;			
		}
		else if (movesMatch(userMove, computerMove, MoveType.ROCK, MoveType.SCISSORS)) {
			return WinType.ROCK_BEATS_SCISSORS;
		}
		else if (movesMatch(userMove, computerMove, MoveType.SCISSORS, MoveType.PAPER)) {
			return WinType.SCISSORS_BEATS_PAPER;
		}
		else {
			return WinType.PAPER_BEATS_ROCK;
		}
	}
	
	private String outcomeTypeStr(Outcome o) {
		switch (o) {
			case TIE:			return "It's a tie!";
			case COMPUTER_WIN:	return "You lose!";
			case USER_WIN:		return "You win!";
			default:			return "";
		}
	}
	
	private String winTypeStr(WinType w) {
		switch (w) {
			case ROCK_BEATS_SCISSORS: 	return "Rock smashes Scissors!";
			case SCISSORS_BEATS_PAPER: 	return "Scissors shreds Paper!";
			case PAPER_BEATS_ROCK: 		return "Paper smothers Rock!";
			default:					return "";
		}
	}
	
	public String outcomeStr(Outcome o, WinType w) {
		switch (o) {
			case TIE:	return outcomeTypeStr(o);
			default:	return String.join(" ", winTypeStr(w), outcomeTypeStr(o)); 
		}			 
	}
}