package textdecorators.driver;

import java.io.IOException;
import textdecorators.AbstractTextDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.util.FileDisplayInterface;
import textdecorators.util.InputDetails;
import textdecorators.util.SingletonMyLogger;


/**
 * @author John Doe
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 6;

	public static void main(String[] args) throws NumberFormatException, IOException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 6) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		
		System.out.println("Hello World! Lets get started with the assignment");
		
		
		try {
			
			//SingletonMyLogger
			SingletonMyLogger s = SingletonMyLogger.getInstance();
			s.setDebugValue(Integer.parseInt(args[5]));
			
			//Decorator pattern
			InputDetails inputD = new InputDetails(args[0], args[1], args[2]);
			inputD.parseInput();
			inputD.setFilePath(args[4]);
			AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
			AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
			AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
			AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);
		
			mostFreqWordDecorator.processInputDetails();
			
			inputD.setFilePath(args[3]);
			((FileDisplayInterface) inputD).writeToFile("");
		
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(NumberFormatException ne)
		{
			System.err.println("Please privide integer as commend line argument");
			ne.printStackTrace();
		}
	}
}
  