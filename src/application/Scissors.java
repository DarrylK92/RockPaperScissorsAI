package application;

public class Scissors extends Selection{
	String winsAgainst = "Paper";
	String losesAgainst = "Rock";
	
	/**
	 * Returns "Scissors". Used for comparing player choice vs AI choice
	 */
	public String toString() {
		return "Scissors";
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
