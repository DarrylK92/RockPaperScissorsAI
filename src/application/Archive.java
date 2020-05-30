package application;

import java.util.LinkedList;

public class Archive {
	LinkedList<String> arcList = new LinkedList<String>();
	
	/**
	 * @param s String representing "Rock", "Paper", or "Scissors"
	 */
	public void add(String s) {
		arcList.add(s);
		
		//Ensure LinkedList size stays at 100 or lower
		while(arcList.size() > 100) {
			arcList.poll();
		}
	}
	
	/**
	 * Clears all content from the archive
	 */
	public void clear() {
		while(arcList.size() > 0) {
			arcList.poll();
		}
	}
}
