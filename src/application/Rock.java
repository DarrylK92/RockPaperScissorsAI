package application;

public class Rock extends Selection{
	String winsAgainst = "Scissors";
	String losesAgainst = "Paper";
	
	/**
	 * Returns "Rock". Used for comparing player choice vs AI choice
	 */
	public String toString() {
		return "Rock";
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
