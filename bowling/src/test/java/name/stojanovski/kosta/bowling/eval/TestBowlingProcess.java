package name.stojanovski.kosta.bowling.eval;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import name.stojanovski.kosta.bowling.eval.BowlingProcess;
import name.stojanovski.kosta.bowling.input.model.ProfileFrames;
import name.stojanovski.kosta.bowling.input.validation.InvalidProfileException;
import name.stojanovski.kosta.bowling.input.validation.JaxbClientValidator;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.test.commons.TestCommons;

public class TestBowlingProcess {

	@Test
	public void testProfileExample() throws InvalidProfileException {
		// Arrange
		ProfileFrames profileFrames = JaxbClientValidator
				.unmarshalProfile(TestCommons.getProfileFilePath("bowl_profile_example.xml"));

		// Act
		List<Frame> frames = BowlingProcess.eval(profileFrames);

		// Assert
		assertEquals(Integer.valueOf(133), frames.get(frames.size() - 1).getAggResult());
	}

	@Test
	public void testProfilePerfect() throws InvalidProfileException {
		// Arrange
		ProfileFrames profileFrames = JaxbClientValidator
				.unmarshalProfile(TestCommons.getProfileFilePath("bowl_profile_perfect.xml"));

		// Act
		List<Frame> frames = BowlingProcess.eval(profileFrames);

		// Assert
		assertEquals(Integer.valueOf(300), frames.get(frames.size() - 1).getAggResult());
	}
	
	@Test
	public void testProfileBug() throws InvalidProfileException {
		// Arrange
		ProfileFrames profileFrames = JaxbClientValidator
				.unmarshalProfile(TestCommons.getProfileFilePath("bowl_profile_bug.xml"));

		// Act
		List<Frame> frames = BowlingProcess.eval(profileFrames);

		// Assert
		assertEquals(Integer.valueOf(155), frames.get(frames.size() - 1).getAggResult());
	}	
	
}
