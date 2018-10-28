package name.stojanovski.kosta.bowling.output;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import name.stojanovski.kosta.bowling.model.BowlingEnum;
import name.stojanovski.kosta.bowling.model.Constants;
import name.stojanovski.kosta.bowling.model.Frame;

/**
 * Generate HTML output 
 */
public class GenerateOutput {

	private static final Logger LOGGER = LoggerFactory.getLogger(GenerateOutput.class);

	private GenerateOutput() {		
	}
	
	/**
	 * Generate HTML call without profile. 
	 * 
	 * @param frames
	 */
	public static void generateHtml(List<Frame> frames) {
		generateHtml(frames, Constants.EMPTY_STRING, null);
	}
	
	/**
	 * Generate HTML call with profile.
	 * 
	 * @param frames
	 * @param filename
	 * @param threshold 
	 */
	public static void generateHtml(List<Frame> frames, String profileFilename, Integer threshold) {
		StringBuilder firstRow = new StringBuilder();
		StringBuilder secondRow = new StringBuilder();

		int count = 0;
		for (Frame frame : frames) {
			LOGGER.trace("**frame nr** {}", (count + 1));
			LOGGER.trace("{}", frame.getBowls());
			if (LOGGER.isTraceEnabled()) {
				BowlingEnum extraGood = frame.getExtraGood();
				LOGGER.trace("{}", Objects.nonNull(extraGood) ? extraGood.name() : Constants.EMPTY_STRING);
			}
			LOGGER.trace("res: {}", frame.getResult());
			LOGGER.trace("agg-res: {}", frame.getAggResult());

			boolean isLast = count == (frames.size() - 1);
			createFirstRow(firstRow, frame, isLast);
			createSecondRow(secondRow, frame, isLast);
			count++;
		}
		String filename = generateFilename(profileFilename, threshold);
		createHtmlFile(createOutputContent(firstRow, secondRow), filename);
	}

	/**
	 * Formatting the content with static template.
	 * Replacing the place holders with the generated content.
	 * 
	 * @param firstRow
	 * @param secondRow
	 * @return
	 */
	private static String createOutputContent(StringBuilder firstRow, StringBuilder secondRow) {
		return String.format(GenOutCons.OUTPUT_PAGE_CONTENT, firstRow.toString(), secondRow.toString());
	}

	/**
	 * Formatting the second row of the HTML table.
	 * 
	 * @param secondRow
	 * @param frame
	 * @param isLast
	 */
	private static void createSecondRow(StringBuilder secondRow, Frame frame, boolean isLast) {
		secondRow.append(String.format("<td colspan=\"%s\">%s</td>", isLast ? frame.getBowls().size() : 2,
				frame.getAggResult()));
	}

	/**
	 * Formatting the first row of the HTML table.
	 * 
	 * @param firstRow
	 * @param frame
	 * @param isLast
	 */
	private static void createFirstRow(StringBuilder firstRow, Frame frame, boolean isLast) {
		int countBowl = 1;
		for (Integer bowl : frame.getBowls()) {
			if (
				(!isLast && BowlingEnum.STRIKE.equals(frame.getExtraGood()))
				||
				(isLast && BowlingEnum.STRIKE.equals(frame.getExtraGood()) && bowl == 10)
				||
				(isLast && BowlingEnum.SPARE.equals(frame.getExtraGood()) && bowl == 10 && countBowl == 3)
				) {			
				if (!isLast) {
					firstRow.append("<td>&nbsp;</td>");
				}
				firstRow.append("<td style=\"background-color: #000078\">&nbsp;</td>");
			} else if (countBowl == 2 && BowlingEnum.SPARE.equals(frame.getExtraGood())) {
				firstRow.append("<td style=\"background: linear-gradient(to right bottom, white 50%, #000078 50%);\">&nbsp;</td>");
			} else if (bowl == 0) {
				firstRow.append("<td>-</td>");
			}  else {
				firstRow.append("<td>" + bowl + "</td>");
			}
			countBowl++;
		}
	}

	/**
	 * Writing the content into HTML file.
	 * Filename depends on input profile file.
	 * 
	 * @param outputContent
	 * @param profileFilename
	 */
	private static void createHtmlFile(String outputContent, String filename) {
		try {
			File file = new File(GenOutCons.OUTPUT_FOLDER, filename);
			FileUtils.writeStringToFile(file, outputContent,
					GenOutCons.CHARSET);
			Desktop.getDesktop().browse(file.toURI());
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("{} created as result file.", filename);
	}

	/**
	 * Generate file name depend on the profile name or threshold.
	 * 
	 * @param profileFilename
	 * @param threshold
	 * @return
	 */
	private static String generateFilename(String profileFilename, Integer threshold) {
		StringBuilder filename = new StringBuilder(GenOutCons.RANDOM_BOWLING);
		if (Objects.nonNull(profileFilename) && !profileFilename.isEmpty()) {
			filename.append(GenOutCons.UNDERSCORE).replace(0, filename.length(),
					profileFilename.replace(GenOutCons.XML_SUFFUX, Constants.EMPTY_STRING));
		} else if (Objects.nonNull(threshold)) {
			filename.append(GenOutCons.UNDERSCORE).append("moreThan" + threshold);
		}
		filename.append(GenOutCons.UNDERSCORE).append(System.currentTimeMillis()).append(GenOutCons.HTML_SUFFUX);
		return filename.toString();
	}
	
}
