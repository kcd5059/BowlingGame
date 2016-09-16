package ssa;

public class SimulatedBowling {

	static java.util.Random rnd = new java.util.Random();

	private int games;
	// [game][frame]
	private int bowlingScores[][];
	private int seriesTotal = 0;

	public void bowl() {

		for (int game = 0; game < bowlingScores.length; game++) {
			for (int frame = 0; frame < bowlingScores[game].length; frame++) {
				int pinsKnockedDown = getPinsKnockedDown(10);
				int remainingPins = 10 - pinsKnockedDown;
				// Check pins remaining after first throw
				if (remainingPins > 0) {
					// Bowl again if there are pins remaining
					int secondThrow = getPinsKnockedDown(remainingPins);
					pinsKnockedDown += secondThrow;
					remainingPins -= secondThrow;
					// Check if any pins are remaining after second throw
					if (remainingPins > 0) {
						// If there are pins remaining, score frame
						bowlingScores[game][frame] = pinsKnockedDown;
					} else {
						// Spare detected, score frame
						bowlingScores[game][frame] = 12;
					}
				} else {
					// Strike detected, score frame
					bowlingScores[game][frame] = 15;
				}
			}
		}
		// Call the method to print scoreboard
		printScoreBoard();
	}

	private void printScoreBoard() {
		// Print out heading
		System.out.println("Frames    1    2    3    4    5    6    7    8    9   10    Total");

		// Loop through each game
		for (int game = 0; game < bowlingScores.length; game++) {
			// Print game number
			System.out.print("Game " + (game + 1));
			int gameTotal = 0; // total score of current game
			// Loop through each frame of current game
			for (int frame = 0; frame < bowlingScores[game].length; frame++) {
				// Set spacing for double-digit values
				if (bowlingScores[game][frame] > 9) {
					System.out.print("   " + bowlingScores[game][frame]);

				} else {
					System.out.print("    " + bowlingScores[game][frame]);
				}
				// Add frame score to total for game
				gameTotal += bowlingScores[game][frame];
			}

			// Set spacing for triple digit values
			if (gameTotal > 99) {
				System.out.println("     " + gameTotal);
			} else {
				System.out.println("      " + gameTotal);
			}
			// Add game score to series total
			seriesTotal += gameTotal;
		}
		// Print out series total
		System.out.println("Total Series                                                 " + seriesTotal);
	}

	// Generate random number between 0 and # of remaining pins to simulate throw
	private int getPinsKnockedDown(int remainingPins) {

		int randomInt = rnd.nextInt(remainingPins + 1); // zero to remaining # of pins
		return randomInt;
	}

	public SimulatedBowling(int games) {
		this.games = games;
		this.bowlingScores = new int[games][10];
	}

	public SimulatedBowling() {
		this.games = 3;
		this.bowlingScores = new int[games][10];
	}

}
