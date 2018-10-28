package name.stojanovski.kosta.bowling.input.common;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import name.stojanovski.kosta.bowling.input.model.ProfileFrames;
import name.stojanovski.kosta.bowling.output.GenerateOutput;

public class JaxbClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateOutput.class);
	
	private JaxbClient() {		
	}
	
	/**
	 * Reads the content from the XML into Java object.
	 * 
	 * @param file
	 * @return
	 */
	public static Object unmarshaller(File file) {
		Object outputObject = new Object();
		try {
			JAXBContext jc = JAXBContext.newInstance(ProfileFrames.class);
			Unmarshaller u = jc.createUnmarshaller();
			outputObject = u.unmarshal(file);
		} catch (JAXBException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return outputObject;
	}
}
