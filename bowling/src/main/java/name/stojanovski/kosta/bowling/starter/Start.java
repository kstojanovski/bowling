package name.stojanovski.kosta.bowling.starter;

import java.io.File;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import name.stojanovski.kosta.bowling.eval.BowlingProcess;
import name.stojanovski.kosta.bowling.input.model.ProfileFrames;
import name.stojanovski.kosta.bowling.input.validation.InvalidProfileException;
import name.stojanovski.kosta.bowling.input.validation.JaxbClientValidator;
import name.stojanovski.kosta.bowling.model.Constants;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.output.GenerateOutput;

public class Start {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateOutput.class);
	
	/**
	 * Starter (main class) of the bowling program.
	 * 
	 * Three modes are defined for this program:
	 * <ol>
	 * <li>Random bowling. Start the program without parameters (java -jar bowling.jar)</li>
	 * <li>Random bowling with minimal threshold (80 - 210). Start the program with parameters (java -jar bowling.jar -t 150) means the end result have to be at least 150.</li>
	 * <li>With input profile. Start the program with parameters (java -jar bowling.jar -h bowl_profile_input.xml)</li>
	 * </ol>
	 * The profiles should placed into the profiles folder which is in same folder like the jar file.
	 * The results as HTML files can be found in bowlingresults folder which is also placed in same folder like the program jar file.
	 * 
	 * @param args
	 * @throws InvalidProfileException 
	 */
	public static void main(String[] args) throws InvalidProfileException {
		LOGGER.info("Start.main() - BEGIN");
		
		ProfileFrames profileFrames = null; 
		String filename = Constants.EMPTY_STRING;
		Integer threshold = null;
		
		if (args.length == 1 && args[0].equals(StarterConstants.ARGUMENT_H)) {
			printHelpAndExit();
		} else if (args.length == 2 && args[0].equals(StarterConstants.ARGUMENT_P)) {
			filename = args[1];
			profileFrames = JaxbClientValidator.unmarshalProfile(StarterConstants.INPUT_FOLDER + File.separator + filename);
		} else if (args.length == 2 && args[0].equals(StarterConstants.ARGUMENT_T)) {
			threshold = readThreshold(args);
		}
		
		List<Frame> frames = null;		
		if (Objects.nonNull(threshold)) {
			Integer aggResult = 0;
			do {
				frames = BowlingProcess.eval();
				aggResult = frames.get(frames.size() - 1).getAggResult();
			} while (aggResult < threshold);
		} else {
			frames = Objects.isNull(profileFrames) ? BowlingProcess.eval() : BowlingProcess.eval(profileFrames);
		}
		
		GenerateOutput.generateHtml(frames, filename, threshold);
		LOGGER.info("Start.main() - END");
	}

	private static Integer readThreshold(String[] args) {
		Integer threshold = null;
		try {
			threshold = Integer.valueOf(args[1]);
			if (threshold < 80 || threshold > 210) {
				threshold = null;
				throw new IllegalArgumentException("Threshold should be between 80 and 150 and is ignored for this run!");					 
			}
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage(), e);
			LOGGER.error("Threshold wll be ignored!");
		} catch (IllegalArgumentException iae) {
			LOGGER.error(iae.getMessage(), iae);
		}
		return threshold;
	}

	private static void printHelpAndExit() {
		LOGGER.info(StarterConstants.HELP_TEXT);
		System.exit(0);
	}
	
}
