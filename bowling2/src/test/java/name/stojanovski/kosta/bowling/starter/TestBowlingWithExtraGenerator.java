package name.stojanovski.kosta.bowling.starter;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import name.stojanovski.kosta.bowling.eval.ResultObserver;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.model.FrameFactory;
import name.stojanovski.kosta.bowling.model.Frames;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.Results;
import name.stojanovski.kosta.bowling.process.Bowling;
import name.stojanovski.kosta.bowling.test.generator.PerfBowingAttemptsGenerator;
import name.stojanovski.kosta.bowling.test.generator.RandomBowingAttemptsGenerator;
import name.stojanovski.kosta.bowling.test.generator.SpecBowingAttemptsGenerator;

public class TestBowlingWithExtraGenerator {
	
	Frame frameAttempts;
	Results Results = new Results();
	Frames frames;
	
	@Before
	public void init() {
		Results = new Results(); 
		 ResultObserver ResultObserver = new ResultObserver(Results);
		frameAttempts = FrameFactory.build(ResultObserver);
		frames = new Frames(ResultObserver);
	}
	
	@Test
	public void testBolwingPerfect() {
		//Arrange - creating the expected
		Map<Integer, Integer> expectedResults = new LinkedHashMap<Integer, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7501338796629156377L;
		{
			int distance = 30;
			for (int i = distance; i < 310; i = i + distance) {
				put(i, distance);
			}
		}};
		List<List<Integer>> expectedAttempts = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			List<Integer> list = new ArrayList<>();
			list.add(10);
			expectedAttempts.add(list);
		}
		List<Integer> lastlist = expectedAttempts.get(expectedAttempts.size() - 1);
		lastlist.add(10);
		lastlist.add(10);
		
		//Act
		Bowling bowling = new Bowling(new PerfBowingAttemptsGenerator());
		bowling.startTheGame();
		
		//Assert - compare
		compareFrameAttempts(expectedAttempts, bowling.getFrames().getFrameCollection());
		compareResults(expectedResults, bowling.getResults());
		assertEquals((Integer)300, bowling.getResults().getLastResult().getAggValue());
	}
	
	@Test
	public void testBolwingRandom() {
		//Arrange - creating the expected
		Map<Integer, Integer> expectedResults = new LinkedHashMap<Integer, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7501338796629156377L;
		{
			int value = 14;
			int frameNr = 10;
			for (int i = 0; i < frameNr; i++) {
				int key = (i + 1) * value;
				if (i == frameNr - 1) {
					key += 2;
					value += 2;
				}
				put(key, value);
			}
		}};		
		List<List<Integer>> expectedAttempts = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			List<Integer> list = new ArrayList<>();
			list.add(4);
			list.add(6);
			expectedAttempts.add(list);
		}
		List<Integer> lastlist = expectedAttempts.get(expectedAttempts.size() - 1);
		lastlist.add(6);
				
		//Act
		Bowling bowling = new Bowling(new RandomBowingAttemptsGenerator());
		bowling.startTheGame();
		
		//Assert - compare
		compareFrameAttempts(expectedAttempts, bowling.getFrames().getFrameCollection());
		compareResults(expectedResults, bowling.getResults());
		assertEquals((Integer)142, bowling.getResults().getLastResult().getAggValue());
	}
	
	@Test
	public void testBolwingSpec() {
		//Arrange - creating the expected
		Map<Integer, Integer> expectedResults = new LinkedHashMap<Integer, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 7501338796629156377L;
		{
			put(5, 5);
			put(14, 9);
			put(29, 15);
			put(49, 20);
			put(60, 11);
			put(61, 1);
			put(77, 16);
			put(97, 20);
			put(117, 20);
			put(133, 16);
		}};		
		List<List<Integer>> expectedAttempts = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			List<Integer> list = new ArrayList<>();
			switch (i) {
			case 0:
				list.add(1);
				list.add(4);
				break;
			case 1:
				list.add(4);
				list.add(5);
				break;
			case 2:
				list.add(6);
				list.add(4);
				break;
			case 3:
				list.add(5);
				list.add(5);
				break;
			case 4:
				list.add(10);
				break;
			case 5:
				list.add(0);
				list.add(1);
				break;
			case 6:
				list.add(7);
				list.add(3);
				break;
			case 7:
				list.add(6);
				list.add(4);
				break;
			case 8:
				list.add(10);
				break;
			case 9:
				list.add(2);
				list.add(8);
				list.add(6);
				break;
			}
			expectedAttempts.add(list);
		}
				
		//Act
		Bowling bowling = new Bowling(new SpecBowingAttemptsGenerator());
		bowling.startTheGame();
		
		//Assert - compare
		compareFrameAttempts(expectedAttempts, bowling.getFrames().getFrameCollection());
		compareResults(expectedResults, bowling.getResults());
		assertEquals((Integer)133, bowling.getResults().getLastResult().getAggValue());
	}
	
	private void compareResults(Map<Integer, Integer> expected, Results Results) {
		Iterator<Result> actualIter = Results.getResults().iterator();
		Iterator<Integer> expectedIter = expected.keySet().iterator();
		while (expectedIter.hasNext()) {
			Integer expAggValue = (Integer) expectedIter.next();
			Result Result = actualIter.next();
			assertEquals(expected.get(expAggValue), Result.getValue());
			assertEquals(expAggValue, Result.getAggValue());
		}
	}
	
	private void compareFrameAttempts(List<List<Integer>> expectedFrameAttempts,
			List<Frame> frameCollection) {
		for (List<Integer> expectedAttempts : expectedFrameAttempts) {
			int index = expectedFrameAttempts.indexOf(expectedAttempts);
			assertEquals(expectedAttempts, frameCollection.get(index).getAttempts());
		}
		
	}
}
