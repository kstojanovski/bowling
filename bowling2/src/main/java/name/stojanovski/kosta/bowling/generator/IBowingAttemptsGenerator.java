package name.stojanovski.kosta.bowling.generator;

public interface IBowingAttemptsGenerator {

  /**
   * Generates attempt values depend on previous value. 
   */     
	int generateAttemptValue(int frameIndex, int attemptIndex,
			int attemptValue);
}