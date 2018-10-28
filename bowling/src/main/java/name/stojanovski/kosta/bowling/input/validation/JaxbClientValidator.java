package name.stojanovski.kosta.bowling.input.validation;

import java.io.File;

import name.stojanovski.kosta.bowling.input.common.JaxbClient;
import name.stojanovski.kosta.bowling.input.model.ProfileFrames;

public class JaxbClientValidator {
	
	private JaxbClientValidator() {		
	}
	
	/**
	 * Reads the content from the JaxbClient and transforms it into ProfileFrames object.
	 * Also validation is made before return.
	 * 
	 * @param path
	 * @return
	 * @throws InvalidProfileException
	 */
	public static ProfileFrames unmarshalProfile(String path) throws InvalidProfileException {
		return ProfileValidator.validate((ProfileFrames)JaxbClient.unmarshaller(new File(path)));
	}

}
