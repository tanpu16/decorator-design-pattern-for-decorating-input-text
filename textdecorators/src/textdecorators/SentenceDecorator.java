package textdecorators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import textdecorators.util.FileDisplayInterface;
import textdecorators.util.InputDetails;
import textdecorators.util.SingletonMyLogger;
import textdecorators.util.SingletonMyLogger.DebugLevel;

public class SentenceDecorator extends AbstractTextDecorator{

	private AbstractTextDecorator atd;
	private InputDetails id;

	//constructor
	public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
		SingletonMyLogger.getInstance().writeMessage("SentenceDecorator Constructor called", DebugLevel.CONSTRUCTOR);
	}

	//toString method
	@Override
	public String toString()
	{
		return "Class PopulateMyArrayVisitor [AbstractTextDecorator is -> "+atd+"InputDetails is"+id+"]";
	}
	
	/*this is void method, which process the input and add Sentence
	 decorator to start and end of the sentence
	@param NA 
	@return NA
	@see print nothing, but add Sentence decorator to the sentence
	*/
	@Override
	public void processInputDetails() {
		// Decorate input details.
		List<String> updatedList = new ArrayList<String>();
				
		for(int i=0; i< id.getOutputList().size(); i++)
		{
				String next = id.getOutputList().get(i);
				updatedList.addAll(Arrays.asList(next.split("\\s")));	
				updatedList.add("\n");
		}	
				
	
		for(int j=0; j<updatedList.size(); j++)
		{
			if(0 == j)
			{
				id.getWordList().set(j, PrefixSuffix.BEGIN_SENTENCE__+id.getWordList().get(j));
			}
			else if(updatedList.get(j) !="" && updatedList.get(j).contains("."))
			{
				String dummy = id.getWordList().get(j).replace(".","");
				
				if((j+1) < updatedList.size()-1 && !dummy.equals(""))
				{
					id.getWordList().set(j, dummy+PrefixSuffix.__END_SENTENCE+"."+PrefixSuffix.BEGIN_SENTENCE__);
				}
				else
				{
					id.getWordList().set(j, dummy+PrefixSuffix.__END_SENTENCE+".");
				}	
				
			}

		}
		
		if(SingletonMyLogger.DebugLevel.SENTENCEDECORATOR == SingletonMyLogger.getInstance().getDebugValue())
		{
			try {
				((FileDisplayInterface) id).writeToFile(PrefixSuffix.SENETENCEDECORATOR__,PrefixSuffix.__SENETENCEDECORATOR);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Forward to the next decorator, if any.
		if (null != atd) {
			atd.processInputDetails();
		}
	}

}
