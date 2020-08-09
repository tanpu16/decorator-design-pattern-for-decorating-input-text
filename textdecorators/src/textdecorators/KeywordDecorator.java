package textdecorators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import textdecorators.util.FileDisplayInterface;
import textdecorators.util.InputDetails;
import textdecorators.util.SingletonMyLogger;
import textdecorators.util.SingletonMyLogger.DebugLevel;

public class KeywordDecorator extends AbstractTextDecorator{

	private AbstractTextDecorator atd;
	private InputDetails id;

	//constructor
	public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
			atd = atdIn;
			id = idIn;
			SingletonMyLogger.getInstance().writeMessage("KeywordDecorator Constructor called", DebugLevel.CONSTRUCTOR);		
	}
	
	//toString method
	@Override
	public String toString()
	{
		return "Class PopulateMyArrayVisitor [AbstractTextDecorator is -> "+atd+"InputDetails is"+id+"]";
	}

	/*this is void method, which process the input and add keyword
	 decorator to the words which are present in keywords file.
	@param NA 
	@return NA
	@see print nothing, but add keyword decorator to the words
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
				for(int k=0 ; k<id.getKeywordsList().size(); k++)
				{
					if(updatedList.get(j) !="" && updatedList.get(j).toLowerCase().equals(id.getKeywordsList().get(k)))
					{
						id.getWordList().set(j, PrefixSuffix.KEYWORD_+id.getWordList().get(j)+PrefixSuffix._KEYWORD);
					}
					else if(updatedList.get(j) !="" && updatedList.get(j).matches("[a-zA-Z0-9]+[.]"))
					{
						String dummy = id.getWordList().get(j).replace(".","");
						if(dummy.toLowerCase().equals(id.getKeywordsList().get(k)))
						{
							id.getWordList().set(j, PrefixSuffix.KEYWORD_+dummy+PrefixSuffix._KEYWORD+".");
						}
					}
					else if(updatedList.get(j) !="" && updatedList.get(j).matches("[a-zA-Z0-9]+[,]"))
					{
						String dummy = id.getWordList().get(j).replace(",","");
						if(dummy.toLowerCase().equals(id.getKeywordsList().get(k)))
						{
							id.getWordList().set(j, PrefixSuffix.KEYWORD_+dummy+PrefixSuffix._KEYWORD+",");
						}
					}
						
				}
		}

			if(SingletonMyLogger.DebugLevel.KEYWORDDECORATOR == SingletonMyLogger.getInstance().getDebugValue())
			{
				try {
					((FileDisplayInterface) id).writeToFile(PrefixSuffix.KEYWORDDECORATOR__,PrefixSuffix.__KEYWORDDECORATOR);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		if (null != atd) {
			atd.processInputDetails();
		}
	}
	
}


