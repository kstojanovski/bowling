package name.stojanovski.kosta.bowling.output;

import org.junit.Test;
import org.mockito.Mockito;

import name.stojanovski.kosta.bowling.output.GenerateOutput;
import name.stojanovski.kosta.bowling.process.Bowling;
import name.stojanovski.kosta.bowling.test.generator.PerfBowingAttemptsGenerator;

public class TestOutput {

	@Test
	public void testOutput() {
		Bowling bowling = new Bowling();
		bowling.startTheGame();
		GenerateOutput generateOutput = Mockito.spy(new GenerateOutput(bowling.getFrames(), bowling.getResults()));
		generateOutput.generateTable(false);
		
		Mockito.verify(generateOutput, Mockito.times(10)).createFirstRow(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyBoolean(), Mockito.anyObject());
		Mockito.verify(generateOutput, Mockito.times(10)).createSecondRow(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyBoolean(), Mockito.anyInt());
		Mockito.verify(generateOutput, Mockito.times(1)).createOutputContent(Mockito.anyObject(), Mockito.anyObject());
		Mockito.verify(generateOutput, Mockito.times(1)).createHtmlFile(Mockito.anyObject(), Mockito.anyObject(), Mockito.eq(false));
	}
	
	@Test
	public void testOutputShowHtml() {
		Bowling bowling = new Bowling();
		bowling.startTheGame();
		GenerateOutput generateOutput = Mockito.spy(new GenerateOutput(bowling.getFrames(), bowling.getResults()));
		generateOutput.generateTable();
		
		Mockito.verify(generateOutput, Mockito.times(10)).createFirstRow(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyBoolean(), Mockito.anyObject());
		Mockito.verify(generateOutput, Mockito.times(10)).createSecondRow(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyBoolean(), Mockito.anyInt());
		Mockito.verify(generateOutput, Mockito.times(1)).createOutputContent(Mockito.anyObject(), Mockito.anyObject());
		Mockito.verify(generateOutput, Mockito.times(1)).createHtmlFile(Mockito.anyObject(), Mockito.anyObject(), Mockito.eq(true));
	}
	
	@Test
	public void testOutputShowHtmlPerfect() {
		Bowling bowling = new Bowling(new PerfBowingAttemptsGenerator());
		bowling.startTheGame();
		GenerateOutput generateOutput = Mockito.spy(new GenerateOutput(bowling.getFrames(), bowling.getResults()));
		generateOutput.generateTable();
		
		Mockito.verify(generateOutput, Mockito.times(10)).createFirstRow(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyBoolean(), Mockito.anyObject());
		Mockito.verify(generateOutput, Mockito.times(10)).createSecondRow(Mockito.anyObject(), Mockito.anyObject(), Mockito.anyBoolean(), Mockito.anyInt());
		Mockito.verify(generateOutput, Mockito.times(1)).createOutputContent(Mockito.anyObject(), Mockito.anyObject());
		Mockito.verify(generateOutput, Mockito.times(1)).createHtmlFile(Mockito.anyObject(), Mockito.anyObject(), Mockito.eq(true));
	}
}
