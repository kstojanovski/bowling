package name.stojanovski.kosta.bowling.eval;

import org.junit.Test;

import name.stojanovski.kosta.bowling.input.validation.InvalidProfileException;
import name.stojanovski.kosta.bowling.input.validation.JaxbClientValidator;
import name.stojanovski.kosta.bowling.test.commons.TestCommons;

public class TestProfileValidity {

	/**
	 * Not last frame but double 10
	 * 
	 * @throws InvalidProfileException
	 */
	@Test(expected = InvalidProfileException.class)
	public void testProfileExampleInvalidsize() throws InvalidProfileException {
		JaxbClientValidator.unmarshalProfile(TestCommons.getProfileFilePath("bowl_profile_invalid_size.xml"));
	}

	/**
	 * Not last frame but double 10
	 * 
	 * @throws InvalidProfileException
	 */
	@Test(expected = InvalidProfileException.class)
	public void testProfileExampleInvalidd10() throws InvalidProfileException {
		JaxbClientValidator.unmarshalProfile(TestCommons.getProfileFilePath("bowl_profile_invalid_d10.xml"));
	}

	/**
	 * Not last frame but 3 bowling values
	 * 
	 * @throws InvalidProfileException
	 */
	@Test(expected = InvalidProfileException.class)
	public void testProfileExampleInvalidnl3() throws InvalidProfileException {
		JaxbClientValidator.unmarshalProfile(TestCommons.getProfileFilePath("bowl_profile_invalid_nl3.xml"));
	}
	
	/**
	 * Value 23
	 * 
	 * @throws InvalidProfileException
	 */
	@Test(expected = InvalidProfileException.class)
	public void testProfileExampleInvalidv23() throws InvalidProfileException {
		JaxbClientValidator.unmarshalProfile(TestCommons.getProfileFilePath("bowl_profile_invalid_v23.xml"));
	}
	
	/**
	 * Last frame but 1 bowling values 
	 * 
	 * @throws InvalidProfileException
	 */
	@Test(expected = InvalidProfileException.class)
	public void testProfileExampleInvalidl1() throws InvalidProfileException {
		JaxbClientValidator.unmarshalProfile(TestCommons.getProfileFilePath("bowl_profile_invalid_l1.xml"));
	}
	
	/**
	 * Last frame but 4 bowling values 
	 * 
	 * @throws InvalidProfileException
	 */
	@Test(expected = InvalidProfileException.class)
	public void testProfileExampleInvalidl4() throws InvalidProfileException {
		JaxbClientValidator.unmarshalProfile(TestCommons.getProfileFilePath("bowl_profile_invalid_l4.xml"));
	}
	
	/**
	 * Last frame has low values  
	 * 
	 * @throws InvalidProfileException
	 */
	@Test(expected = InvalidProfileException.class)
	public void testProfileExampleInvalidllow() throws InvalidProfileException {
		JaxbClientValidator.unmarshalProfile(TestCommons.getProfileFilePath("bowl_profile_invalid_llow.xml"));
	}
	
	/**
	 * Last frame invalid sum 
	 * 
	 * @throws InvalidProfileException
	 */
	@Test(expected = InvalidProfileException.class)
	public void testProfileExampleInvalidlsum() throws InvalidProfileException {
		JaxbClientValidator.unmarshalProfile(TestCommons.getProfileFilePath("bowl_profile_invalid_lsum.xml"));
	}
}
