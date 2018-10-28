package name.stojanovski.kosta.bowling.starter;

import name.stojanovski.kosta.bowling.output.GenerateOutput;
import name.stojanovski.kosta.bowling.process.Bowling;

/**
 * Starter/Main class of the program
 */
public class BowlingStarter {

	public static void main(String[] args) {
		Bowling bowling = new Bowling();
		bowling.startTheGame();
		bowling.print();
		
		GenerateOutput generateOutput = new GenerateOutput(bowling.getFrames(), bowling.getResults());
		generateOutput.generateTable();
	}
}
