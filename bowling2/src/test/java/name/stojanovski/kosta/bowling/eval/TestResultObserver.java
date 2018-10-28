package name.stojanovski.kosta.bowling.eval;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import name.stojanovski.kosta.bowling.eval.ResultObserver;
import name.stojanovski.kosta.bowling.model.FrameInfo;
import name.stojanovski.kosta.bowling.model.Results;

@RunWith(MockitoJUnitRunner.class)
public class TestResultObserver {

	private Results Results = new Results();
	
	@Spy
	private ResultObserver ResultObserver = new ResultObserver(Results);
	
	@Test
	public void testAdditionAndMethodsInvokation() {
		//Arrange
		Integer frameIndex = 0;
		Integer attemptValue = 5;
		
		for (int i = 0; i < 5; i++) {
			//Arrange
			FrameInfo frameInfo = new FrameInfo(frameIndex, i, attemptValue);
			
			//Act
			ResultObserver.createModifyResult(frameInfo);
			
			//Assert
			assertEquals(1, Results.getResults().size());
			assertEquals((Integer)((i + 1) * attemptValue), Results.getResults().get(0).getValue());
			if (i == 0) {
				verify(ResultObserver, times(1)).createResult(frameInfo, Results.getResults());
			} else {
				verify(ResultObserver, times(1)).modifyResult(frameInfo, Results.getResults());
			}			
			verify(ResultObserver, times(1)).recalculatePrevious(frameInfo);
		}		
	}	

}
