package name.stojanovski.kosta.bowling.output;

import java.nio.charset.Charset;

/**
 * Constants for the output format. 
 */
public class GenOutCons {

	protected static final String OUTPUT_FOLDER = "bowlingresults";
	
	protected static final String UNDERSCORE = "_";
	protected static final String UTF_8 = "UTF-8";
	protected static final Charset CHARSET = Charset.forName(GenOutCons.UTF_8);
	
	protected static final String HTML_SUFFUX = ".html";
	protected static final String XML_SUFFUX = ".xml";
	protected static final String RANDOM_BOWLING = "random_bowling";
	protected static final String OUTPUT_PAGE_CONTENT = 
		"<html>"
		+ "<head>"
		+ "<style>"
			+ "table{color:#000078;border-collapse:collapse;table-layout: fixed;font-family: Verdana;font-weight: bold;}"
			+ "th, tr, td{border:2px solid #FF0000;}"
		+ "</style>"
		+ "</head>"
		+ "<body>"
			+ "<table style=\"width:100%%\" border=1>"
			+ "<tr align=\"center\">%s</tr>"
			+ "<tr align=\"center\">%s</tr>"
			+ "</table>"
		+ "</body>"
		+ "</html>";
	
	private GenOutCons() {		
	}	
	
}
