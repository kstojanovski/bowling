package name.stojanovski.kosta.bowling.test.commons;

import java.io.File;

public class TestCommons {

	private static final String SEP = File.separator;
	public static final String PROJECT_PATH = new File("").getAbsolutePath();
	public static final String RES_PATH = SEP + "src" + SEP + "test" + SEP + "resources" + SEP;
	public static final String TEST_RESOURCE_PATH = PROJECT_PATH + RES_PATH;

	public static final String getProfileFilePath(String profileFilePath) {
		return TEST_RESOURCE_PATH + profileFilePath;
	}
}
