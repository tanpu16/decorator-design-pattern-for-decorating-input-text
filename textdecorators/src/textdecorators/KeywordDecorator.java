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
			for(int i = 0; i < id.getOutputList().size(); i++)
			{
				String str = id.getOutputList().get(i);
				List<String> temp = new ArrayList<String>();
				temp.addAll(Arrays.asList(str.split("\\s")));
				
				
				for(int j=0; j<temp.size(); j++)
				{
					for(int k=0 ; k<id.getKeywordsList().size(); k++)
					{
						if(temp.get(j) !="" && temp.get(j).toLowerCase().equals(id.getKeywordsList().get(k)))
						{
							temp.set(j, PrefixSuffix.KEYWORD_+temp.get(j)+PrefixSuffix._KEYWORD);
						}
						else if(temp.get(j) !="" && temp.get(j).matches("[a-zA-Z0-9]+[.]"))
						{
								String dummy = temp.get(j).replace(".","");
								if(dummy.toLowerCase().equals(id.getKeywordsList().get(k)))
								{
									temp.set(j, PrefixSuffix.KEYWORD_+dummy+PrefixSuffix._KEYWORD+".");
								}
						}
						else if(temp.get(j) !="" && temp.get(j).matches("[a-zA-Z0-9]+[,]"))
						{
								String dummy = temp.get(j).replace(",","");
								if(dummy.toLowerCase().equals(id.getKeywordsList().get(k)))
								{
									temp.set(j, PrefixSuffix.KEYWORD_+dummy+PrefixSuffix._KEYWORD+",");
								}
						}
						else if(temp.get(j).contains("MOST_FREQUENT") && !temp.get(j).contains("KEYWORD"))
						{
							String dummy = temp.get(j).replaceAll("^MOST_FREQUENT_|_MOST_FREQUENT.*$","");
						
							if(temp.get(j).contains("."))
							{
								if(dummy.toLowerCase().equals(id.getKeywordsList().get(k)))
								{
									temp.set(j, PrefixSuffix.KEYWORD_+"MOST_FREQUENT_"+dummy+"_MOST_FREQUENT"+PrefixSuffix._KEYWORD+".");
								}
							}
							else if(temp.get(j).contains(","))
							{
								if(dummy.toLowerCase().equals(id.getKeywordsList().get(k)))
								{
									temp.set(j, PrefixSuffix.KEYWORD_+"MOST_FREQUENT_"+dummy+"_MOST_FREQUENT"+PrefixSuffix._KEYWORD+",");
								}
							}
							else if(dummy.toLowerCase().equals(id.getKeywordsList().get(k)))
							{
								temp.set(j, PrefixSuffix.KEYWORD_+"MOST_FREQUENT_"+dummy+"_MOST_FREQUENT"+PrefixSuffix._KEYWORD);
							}
							
						}
						
					}
				}
				
				String final_output = String.join(" ", temp);
				id.getOutputList().remove(i);
				id.getOutputList().add(i, final_output);	
			}
		
			if(SingletonMyLogger.DebugLevel.KEYWORDDECORATOR == SingletonMyLogger.getInstance().getDebugValue())
			{
				try {
					((FileDisplayInterface) id).writeToFile("KEYWORDDECORATOR");
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
