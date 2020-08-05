package textdecorators.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import textdecorators.AbstractTextDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.util.InputDetails;



/**
 * @author John Doe
 *
 */
public class Driver {
	private static final int REQUIRED_NUMBER_OF_CMDLINE_ARGS = 5;

	public static void main(String[] args) throws InvalidPathException, SecurityException, CloneNotSupportedException, FileNotFoundException, IOException {

		/*
		 * As the build.xml specifies the arguments as input,output or metrics, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 5) {
			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_CMDLINE_ARGS);
			System.exit(0);
		}
		
		/*
		if(args[0].equals(args[1]))
		{
			System.err.println("Both input files have the same path and name!");
			System.exit(0);
		}
		
		if(args[2].equals(args[3]) || args[3].equals(args[4]) || args[4].equals(args[5]))
		{
			System.err.println("output files have the same path and name!");
			System.exit(0);
		}
		*/
		
		System.out.println("Hello World! Lets get started with the assignment");
		
		
		InputDetails inputD = new InputDetails(args[0], args[1], args[2], args[4]);
		
		inputD.parseInput();
		
		AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
		AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
		AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
		AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);
		
		mostFreqWordDecorator.processInputDetails();
		
		/*
		System.out.println("in driver "+inputD.getWordsList());
		System.out.println("in driver "+inputD.getKeywordsList());
		System.out.println("in driver "+inputD.getMisspelledList());
		*/
		
		/*
		try 
		{

			
			
		} 
		catch (InvalidPathException | SecurityException | FileNotFoundException | CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} 
		catch (IOException ie)
		{
			ie.printStackTrace();
			System.exit(0);
		}
		finally
		{
			
		}
		*/

	}
}
  