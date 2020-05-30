package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class Analyzer {
	Queue<String> randomQueue = new LinkedList<String>();
	Map<String, String> predictions = new HashMap<>();
	
	//Constructor
	public Analyzer() {
		//Set up initial random queue
		for(int i = 0; i < 10; i++) {
			addToRandomQueue();
		}
	}
	
	/** 
	 * Adds a random answer to the random queue
	 */
	public void addToRandomQueue() {
		Random rand = new Random();
		int randSelection = rand.nextInt(3) + 1;
		switch(randSelection) {
		case 1:
			randomQueue.add("Rock");
			break;
		case 2:
			randomQueue.add("Paper");
			break;
		case 3:
			randomQueue.add("Scissors");
			break;
		}
	}
	
	/**
	 * @param a The archive to use
	 * @return Returns the AIs selection
	 */
	public String analyze(Archive a) {
		//Set blank values in map
		predictions.put("Single", "");
		predictions.put("Double", "");
		predictions.put("Triple", "");
		
		//Start predicted single answer thread
		Runnable singleA = () -> {
			//Create copy of archive list
			LinkedList<String> arcList = (LinkedList<String>) a.arcList.clone();
			
			//Check if there are enough entries in the archive to do a prediction
			if (arcList.size() < 2) {
				//If not, pick a random value
				predictions.put("Single", randomQueue.poll());
				//Refill queue
				addToRandomQueue();
			} else {
				int rock = 0;
				int paper = 0;
				int scissors = 0;
				
				//Find what player last picked
				String lastPick = arcList.peekLast();
				
				//Loop through cloned archive linkedList
				while(arcList.size() > 1) {
					//Find next value and remove from LinkedList
					String next = arcList.pollFirst();
					
					//Add to prediction if it matches the players last pick
					if (lastPick.equals(next)) {
						switch(arcList.peekFirst()) {
						case "Rock":
							rock++;
							break;
						case "Paper":
							paper++;
							break;
						case "Scissors":
							scissors++;
							break;
						}
					}
				}
				
				//Create ArrayList for storing possible predictions
				ArrayList<String> possiblePredictions = new ArrayList<String>();
				
				while(rock > 0) {
					possiblePredictions.add("Paper"); //Need to play paper to beat rock
					rock--;
				}
				
				while(paper > 0) {
					possiblePredictions.add("Scissors"); //Need to play scissors to beat paper
					paper--;
				}
				
				while(scissors > 0) {
					possiblePredictions.add("Rock"); //Need to play rock to beat scissors
					scissors--;
				}
				
				if (possiblePredictions.size() > 0) {
					//Pick final prediction
					Random rand = new Random();
					int r = rand.nextInt(possiblePredictions.size());
					
					//Set map with final prediction
					predictions.put("Single", possiblePredictions.get(r));
				} else {
					//Pick a random value
					predictions.put("Single", randomQueue.poll());
					//Refill queue
					addToRandomQueue();
				}
			}
		};
		Thread t1 = new Thread(singleA);
		t1.start();
		
		//Start predicted double answer thread
		Runnable doubleA = () -> {
			//Create copy of archive list
			LinkedList<String> arcList = (LinkedList<String>) a.arcList.clone();
			
			//Check if there are enough entries in the archive to do a prediction
			if (arcList.size() < 3) {
				//If not, pick a random value
				predictions.put("Double", randomQueue.poll());
				//Refill queue
				addToRandomQueue();
			} else {
				int rock = 0;
				int paper = 0;
				int scissors = 0;
				
				//Find what player last picked
				//String lastPick = arcList.peekLast();
				String lastPick = arcList.get(arcList.size() - 2);
				String lastPickSecond = arcList.peekLast();
				
				//Loop through cloned archive linkedList
				while(arcList.size() > 3) {
					//Find next value and remove from LinkedList
					String next = arcList.pollFirst();
					
					//Add to prediction if it matches the players last pick
					if (lastPick.equals(next) && lastPickSecond.equals(arcList.get(1))) { 
						switch(arcList.get(2)) {
						case "Rock":
							rock++;
							break;
						case "Paper":
							paper++;
							break;
						case "Scissors":
							scissors++;
							break;
						}
					}
				}
				
				//Create ArrayList for storing possible predictions
				ArrayList<String> possiblePredictions = new ArrayList<String>();
				
				while(rock > 0) {
					possiblePredictions.add("Paper"); //Need to play paper to beat rock
					rock--;
				}
				
				while(paper > 0) {
					possiblePredictions.add("Scissors"); //Need to play scissors to beat paper
					paper--;
				}
				
				while(scissors > 0) {
					possiblePredictions.add("Rock"); //Need to play rock to beat scissors
					scissors--;
				}
				
				if (possiblePredictions.size() > 0) {
					//Pick final prediction
					Random rand = new Random();
					int r = rand.nextInt(possiblePredictions.size());
					
					//Set map with final prediction
					predictions.put("Double", possiblePredictions.get(r));
				} else {
					//Pick a random value
					predictions.put("Double", randomQueue.poll());
					//Refill queue
					addToRandomQueue();
				}
			}
		};
		Thread t2 = new Thread(doubleA);
		t2.start();
		
		//Start predicted triple answer thread
		Runnable tripleA = () -> {
			//Create copy of archive list
			LinkedList<String> arcList = (LinkedList<String>) a.arcList.clone();
			
			//Check if there are enough entries in the archive to do a prediction
			if (arcList.size() < 4) {
				//If not, pick a random value
				predictions.put("Triple", randomQueue.poll());
				//Refill queue
				addToRandomQueue();
			} else {
				int rock = 0;
				int paper = 0;
				int scissors = 0;
				
				//Find what player last picked
				//String lastPick = arcList.peekLast();
				String lastPick = arcList.get(arcList.size() - 3);
				String lastPickSecond = arcList.get(arcList.size() - 2);
				String lastPickThird = arcList.peekLast();
				
				//Loop through cloned archive linkedList
				while(arcList.size() > 4) {
					//Find next value and remove from LinkedList
					String next = arcList.pollFirst();
					
					//Add to prediction if it matches the players last pick
					if (lastPick.equals(next) && lastPickSecond.equals(arcList.get(1)) && lastPickThird.equals(arcList.get(2))) { 
						switch(arcList.get(3)) {
						case "Rock":
							rock++;
							break;
						case "Paper":
							paper++;
							break;
						case "Scissors":
							scissors++;
							break;
						}
					}
				}
				
				//Create ArrayList for storing possible predictions
				ArrayList<String> possiblePredictions = new ArrayList<String>();
				
				while(rock > 0) {
					possiblePredictions.add("Paper"); //Need to play paper to beat rock
					rock--;
				}
				
				while(paper > 0) {
					possiblePredictions.add("Scissors"); //Need to play scissors to beat paper
					paper--;
				}
				
				while(scissors > 0) {
					possiblePredictions.add("Rock"); //Need to play rock to beat scissors
					scissors--;
				}
				
				if (possiblePredictions.size() > 0) {
					//Pick final prediction
					Random rand = new Random();
					int r = rand.nextInt(possiblePredictions.size());
					
					//Set map with final prediction
					predictions.put("Triple", possiblePredictions.get(r));
				} else {
					//Pick a random value
					predictions.put("Triple", randomQueue.poll());
					//Refill queue
					addToRandomQueue();
				}
			}
		};
		Thread t3 = new Thread(tripleA);
		t3.start();
		
		//Wait till threads have finished
		while(t1.isAlive() || t2.isAlive() || t3.isAlive()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		//Pick which prediction to use and return it
		Random rand = new Random();
		int r = rand.nextInt(100) + 1;
		
		if (r <= 30) {
			return predictions.get("Single");
		} else if (r <= 60) {
			return predictions.get("Double");
		} else if (r <= 90) {
			return predictions.get("Triple");
		} else {
			//Pick a random value
			String randPick = randomQueue.poll();
			//Refill queue
			addToRandomQueue();
			return randPick;
		}
	}
}
