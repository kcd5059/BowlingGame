package ssa;

public class Frame {

	private int gameNumber;
	private int frameNumber;
	private int score = 0;
	private int throw1 = 0;
	private int throw2 = 0;
	private int throw3 = 0;
	private boolean isStrike = false;
	private boolean isSpare = false;
	
	public Frame(int gameNumber, int frameNumber) {
		this.gameNumber = gameNumber;
		this.frameNumber = frameNumber;
	}
	
	public void clear() {
		this.score = 0;
		this.throw1 = 0;
		this.throw2 = 0;
		this.throw3 = 0;
		this.isStrike = false;
		this.isSpare = false;
	}

	public int getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(int frameNumber) {
		this.frameNumber = frameNumber;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getThrow1() {
		return throw1;
	}

	public void setThrow1(int throw1) {
		this.throw1 = throw1;
	}

	public int getThrow2() {
		return throw2;
	}

	public void setThrow2(int throw2) {
		this.throw2 = throw2;
	}

	public int getThrow3() {
		return throw3;
	}

	public void setThrow3(int throw3) {
		this.throw3 = throw3;
	}

	public boolean isStrike() {
		return isStrike;
	}

	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}

	public boolean isSpare() {
		return isSpare;
	}

	public void setSpare(boolean isSpare) {
		this.isSpare = isSpare;
	}

	
	
}
