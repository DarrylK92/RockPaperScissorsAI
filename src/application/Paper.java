package application;

public class Paper extends Selection{
	String winsAgainst = "Rock";
	String losesAgainst = "Scissors";
	
	/**
	 * Returns "Paper". Used for comparing player choice vs AI choice
	 */
	public String toString() {
		return "Paper";
	}

	@Override
	public String getWinsAgainst() {
		return winsAgainst;
	}

	@Override
	public String getLosesAgainst() {
		return losesAgainst;
	}
}
