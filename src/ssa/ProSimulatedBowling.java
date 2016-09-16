package ssa;

import java.util.ArrayList;
import java.util.List;

public class ProSimulatedBowling {
	static java.util.Random rnd = new java.util.Random();

	private int games;
	private int gameCounter = 1;
	List<Frame> frames = new ArrayList<>();
	private int seriesTotal = 0;

	public void setup() {
		// Builds the ArrayList of frames
		for (int frameCounter = 1; frameCounter <= 10; frameCounter++) {
			frames.add(new Frame(gameCounter, frameCounter));
		}

	}

	public void bowl() {
		setup();
		for (int game = 1; game <= games; game++) {
			for (Frame frame : frames) {
				frame.clear();
				if (frame.getFrameNumber() == 10) {
					int remainingPins = 10;
					frame.setThrow1(getPinsKnockedDown(remainingPins));
					remainingPins -= frame.getThrow1();
					if (frame.getThrow1() == 10) {
						frame.setStrike(true);
						frame.setScore(10);
						remainingPins = 10;
						frame.setThrow2(getPinsKnockedDown(remainingPins));
						remainingPins -= frame.getThrow2();
						if (frame.getThrow2() == 10) {
							frame.setScore(20);
							remainingPins = 10;
							frame.setThrow3(getPinsKnockedDown(remainingPins));
							frame.setScore(frame.getScore() + frame.getThrow3());
						} else {
							frame.setThrow3(remainingPins);
							frame.setScore(frame.getScore() + frame.getThrow3());

						}
					} else {
						frame.setScore(frame.getThrow1());
						frame.setThrow2(getPinsKnockedDown(remainingPins));
						remainingPins -= frame.getThrow2();
						if (remainingPins == 0) {
							frame.setScore(frame.getScore() + frame.getThrow2());
							remainingPins = 10;
							frame.setThrow3(getPinsKnockedDown(remainingPins));
							frame.setScore(frame.getScore() + frame.getThrow3());
						} else {
							frame.setScore(frame.getScore() + frame.getThrow2());
						}
					}
				} else {
					int pinsRemaining = 10;
					frame.setThrow1(getPinsKnockedDown(pinsRemaining));
					pinsRemaining -= frame.getThrow1();
					if (frame.getThrow1() == 10) {
						frame.setStrike(true);
						frame.setScore(10);
					} else {
						frame.setThrow2(getPinsKnockedDown(pinsRemaining));
						pinsRemaining -= frame.getThrow2();
						if (pinsRemaining == 0) {
							frame.setSpare(true);
							frame.setScore(10);
						} else {
							frame.setScore(frame.getThrow1() + frame.getThrow2());
						}
					}
				}
			}
			calculateTrueScore();
			printScoreBoard(game);
		}
	}

	private int getPinsKnockedDown(int remainingPins) {

		int randomInt = rnd.nextInt(remainingPins + 1); // zero to remaining #
														// of pins
		return randomInt;
	}

	private void calculateTrueScore() {
		
		for (int i = 0; i < frames.size(); i++) {
			if (frames.get(i).isStrike() && i < 8) {
				if (frames.get(i+1).isStrike()) {
					frames.get(i).setScore(30);
				} else {
					frames.get(i).setScore(frames.get(i).getScore() + frames.get(i+1).getScore());
				}
			} else if (frames.get(i).isSpare() && i < 8) {
				frames.get(i).setScore(frames.get(i).getScore() + frames.get(i+1).getThrow1());
			}
		}
		if (frames.get(8).isStrike()) {
			frames.get(8).setScore(frames.get(8).getScore() + frames.get(9).getThrow1() + frames.get(9).getThrow2());
		}
	}

	private void printScoreBoard(int gameNumber) {
		
		if (gameNumber == 1) {
			// Print out heading
			System.out.println("Frames    1    2    3    4    5    6    7    8    9   10    Total");
		}

		// Print game number
		System.out.print("Game " + gameNumber);
		int gameTotal = 0; // total score of current game

		for (Frame frame : frames) {			
			System.out.print(String.format("%5d", frame.getScore()));
			// Add frame score to total for game
			gameTotal += frame.getScore();
		}
		//Print game total score
		System.out.println(String.format("%8d", gameTotal));
		// Add game score to series total
		seriesTotal += gameTotal;

		 if (gameNumber == games) {
			// Print out series total
			System.out.println("Total Series                                                 " + String.format("%-4d", seriesTotal));
		} 
	}

	public ProSimulatedBowling(int games) {
		this.games = games;
	}

	public ProSimulatedBowling() {
		this.games = 3;
	}

	public int getSeriesTotal() {
		return seriesTotal;
	}

}
