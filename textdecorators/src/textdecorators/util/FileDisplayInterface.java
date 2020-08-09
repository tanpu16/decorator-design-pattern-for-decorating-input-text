package textdecorators.util;

import java.io.IOException;

import textdecorators.PrefixSuffix;

public interface FileDisplayInterface {
	public void writeToFile(PrefixSuffix prefix, PrefixSuffix suffix) throws IOException;
}
