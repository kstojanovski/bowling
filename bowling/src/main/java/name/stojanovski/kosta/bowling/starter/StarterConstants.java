package name.stojanovski.kosta.bowling.starter;

public class StarterConstants {

	public static final String INPUT_FOLDER = "profiles";
	
	public static final String ARGUMENT_P = "-p";
	public static final String ARGUMENT_H = "-h";
	public static final String ARGUMENT_T = "-t";
	
	public static final String HELP_TEXT =  "---" + '\n' +
	
	"Three modes are defined for this program:" + '\n'
	+ "1. Random bowling. Start the program without parameters (java -jar bowling.jar)" + '\n'
	+ "2. Random bowling with minimal threshold (80 - 210). Start the program with parameters (java -jar bowling.jar -t 150) means the end result have to be at least 150." + '\n'
	+ "3. With input profile. Start the program with parameters (java -jar bowling.jar -h profile_bowling_0.xml)" + '\n' + '\n'
	+ "The profiles should placed into the profiles folder which is in same folder like the jar file." + '\n'
	+ "The results as HTML files can be found in bowlingresults folder which is also placed in same folder like the program jar file." + '\n';
	
	private StarterConstants() {		
	}	
	
}
