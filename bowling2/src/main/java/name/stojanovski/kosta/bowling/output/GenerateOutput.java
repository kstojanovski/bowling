package name.stojanovski.kosta.bowling.output;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import name.stojanovski.kosta.bowling.model.BowlingEnum;
import name.stojanovski.kosta.bowling.model.Frame;
import name.stojanovski.kosta.bowling.model.Frames;
import name.stojanovski.kosta.bowling.model.Result;
import name.stojanovski.kosta.bowling.model.Results;

/**
 * Generate HTML output as result table and optional open the content into a browser.
 */
public class GenerateOutput {

	private static  Logger LOGGER = LoggerFactory.getLogger(GenerateOutput.class);

	private Frames frames;
	private Results results;
	
	public GenerateOutput(Frames frames, Results results) {
		this.frames = frames;
		this.results = results;
	}
	
  /**
   * Generating the HTML table which opens the result in an web browser.
   */     
	public void generateTable() {
		generateTable(true);
	}

  /**
   * Generating the HTML table which optionaly opens the result in an web browser.
   */	
	public void generateTable(boolean showInBrowser) {
		StringBuilder firstRow = new StringBuilder();
		StringBuilder secondRow = new StringBuilder();
		
		int size = frames.getFrameCollection().size();
		for (Frame frame : frames.getFrameCollection()) {
			int index = frames.getFrameCollection()
					.indexOf(frame);
			Result result = results
					.getResults().get(index);
			boolean isLast = index == (size - 1);			
			createFirstRow(firstRow, frame, isLast, result.getBowlingEnum());
			createSecondRow(secondRow, result, isLast, frame.getAttempts().size());
		}
		createHtmlFile(createOutputContent(firstRow, secondRow), System.currentTimeMillis() + ".html", showInBrowser);
	}

	/**
	 * creating the second row of the table where the results are displayed
	 * 
	 * @param secondRow
	 * @param result
	 * @param isLast
	 * @param attemptSize
	 */
	protected void createSecondRow(StringBuilder secondRow, Result result, boolean isLast, int attemptSize) {
		secondRow.append(String.format("<td colspan=\"%s\">%s</td>", isLast ? attemptSize : 2,
				result.getAggValue()));		
	}

	/**
	 * creating the first row of the table where the attempts are displayed
	 * 
	 * @param firstRow
	 * @param frame
	 * @param isLast
	 * @param bowlingEnum
	 */
	protected void createFirstRow(StringBuilder firstRow, Frame frame, boolean isLast, BowlingEnum bowlingEnum) {
		int countBowl = 1;
		for (Integer attempt : frame.getAttempts()) {
			if (BowlingEnum.STRIKE.equals(bowlingEnum)) {
				if (!isLast) {
					firstRow.append("<td>&nbsp;</td>");
					firstRow.append("<td style=\"background-color: #000078\">&nbsp;</td>");
				} else if (attempt == 10) {
					firstRow.append("<td style=\"background-color: #000078\">&nbsp;</td>");
				} else {
					firstRow.append("<td>" + attempt + "</td>");
				}
			} else if (countBowl == 2 && BowlingEnum.SPARE.equals(bowlingEnum)) {
				firstRow.append("<td style=\"background: linear-gradient(to right bottom, white 50%, #000078 50%);\">&nbsp;</td>");
			} 
			else {
				firstRow.append("<td>" + attempt + "</td>");
			}
			countBowl++;
		}		
	}
	
	/**
	 * Formatting the content with static template.
	 * Replacing the place holders with the generated content.
	 * 
	 * @param firstRow
	 * @param secondRow
	 * @return
	 */
	protected String createOutputContent(StringBuilder firstRow, StringBuilder secondRow) {
		return String.format(GenOutCons.OUTPUT_PAGE_CONTENT, firstRow.toString(), secondRow.toString());
	}

	/**
	 * Writing the content into HTML file.
	 * Filename depends on input profile file.
	 * 
	 * @param outputContent
	 * @param showInBrowser 
	 * @param profileFilename
	 */
	protected void createHtmlFile(String outputContent, String filename, boolean showInBrowser) {
		try {
			File file = new File(GenOutCons.OUTPUT_FOLDER, filename);
			FileUtils.writeStringToFile(file, outputContent,
					GenOutCons.CHARSET);
			if (showInBrowser) {
				Desktop.getDesktop().browse(file.toURI());
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
		}
		LOGGER.info("{} created as result file.", filename);
	}
	
}
