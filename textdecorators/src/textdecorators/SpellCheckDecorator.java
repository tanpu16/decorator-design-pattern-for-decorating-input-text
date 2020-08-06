package textdecorators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import textdecorators.util.FileDisplayInterface;
import textdecorators.util.InputDetails;
import textdecorators.util.SingletonMyLogger;
import textdecorators.util.SingletonMyLogger.DebugLevel;

public class SpellCheckDecorator extends AbstractTextDecorator{

	private AbstractTextDecorator atd;
	private InputDetails id;

	//constructor
	public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
			atd = atdIn;
			id = idIn;
			SingletonMyLogger.getInstance().writeMessage("SpellCheckDecorator Constructor called", DebugLevel.CONSTRUCTOR);
	}

	//toString method
	@Override
	public String toString()
	{
		return "Class PopulateMyArrayVisitor [AbstractTextDecorator is -> "+atd+"InputDetails is"+id+"]";
	}
	
	/*this is void method, which process the input and add SpellChwck
	 decorator to the words which are present in misspell file.
	@param NA 
	@return NA
	@see print nothing, but add misspell decorator to the words
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
				for(int k=0 ; k<id.getMisspelledList().size(); k++)
				{
					if(temp.get(j) !="" && temp.get(j).toLowerCase().equals(id.getMisspelledList().get(k)))
					{
						temp.set(j, PrefixSuffix.SPELLCHECK_+temp.get(j)+PrefixSuffix._SPELLCHECK);
					}
					else if(temp.get(j) !="" && temp.get(j).matches("[a-zA-Z0-9]+[.]$"))
					{
						
							String dummy = temp.get(j).replace(".","");
							if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
							{
								temp.set(j, PrefixSuffix.SPELLCHECK_+dummy+PrefixSuffix._SPELLCHECK+".");
							}
					}
					else if(temp.get(j) !="" && temp.get(j).matches("[a-zA-Z0-9]+[,]$"))
					{
						
							String dummy = temp.get(j).replace(",","");
							if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
							{
								temp.set(j, PrefixSuffix.SPELLCHECK_+dummy+PrefixSuffix._SPELLCHECK+",");
							}
					}
					else if(temp.get(j).contains("MOST_FREQUENT") && !temp.get(j).contains("KEYWORD") && !temp.get(j).contains("SPELLCHECK"))
					{
						
						String dummy = temp.get(j).replaceAll("^MOST_FREQUENT_|_MOST_FREQUENT.*$","");

						if(temp.get(j).contains("."))
						{
							if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
							{
								temp.set(j, PrefixSuffix.SPELLCHECK_+"MOST_FREQUENT_"+dummy+"_MOST_FREQUENT"+PrefixSuffix._SPELLCHECK+".");
							}
						}
						else if(temp.get(j).contains(","))
						{
							if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
							{
								temp.set(j, PrefixSuffix.SPELLCHECK_+"MOST_FREQUENT_"+dummy+"_MOST_FREQUENT"+PrefixSuffix._SPELLCHECK+",");
							}
						}
						else if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
						{
							temp.set(j, PrefixSuffix.SPELLCHECK_+"MOST_FREQUENT_"+dummy+"_MOST_FREQUENT"+PrefixSuffix._SPELLCHECK);
						}
						
					}
					else if(temp.get(j).contains("KEYWORD") && !temp.get(j).contains("MOST_FREQUENT") && !temp.get(j).contains("SPELLCHECK"))
					{
						String dummy = temp.get(j).replaceAll("^KEYWORD_|_KEYWORD.*$","");
						
						if(temp.get(j).contains("."))
						{
							if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
							{
								temp.set(j, PrefixSuffix.SPELLCHECK_+"KEYWORD_"+dummy+"_KEYWORD"+PrefixSuffix._SPELLCHECK+".");
							}
						}
						else if(temp.get(j).contains(","))
						{
							if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
							{
								temp.set(j, PrefixSuffix.SPELLCHECK_+"KEYWORD_"+dummy+"_KEYWORD"+PrefixSuffix._SPELLCHECK+",");
							}
						}
						else if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
						{
							temp.set(j, PrefixSuffix.SPELLCHECK_+"KEYWORD_"+dummy+"_KEYWORD"+PrefixSuffix._SPELLCHECK);
						}
					}
					else if(temp.get(j).contains("KEYWORD_MOST_FREQUENT") && !temp.get(j).contains("SPELLCHECK"))
					{
						String dummy = temp.get(j).replaceAll("KEYWORD_MOST_FREQUENT_|_MOST_FREQUENT_KEYWORD.*$", "");

						if(temp.get(j).contains("."))
						{
							if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
							{
								temp.set(j, PrefixSuffix.SPELLCHECK_+"KEYWORD_MOST_FREQUENT_"+dummy+"_MOST_FREQUENT_KEYWORD"+PrefixSuffix._SPELLCHECK+".");
							}
						}
						else if(temp.get(j).contains(","))
						{
							if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
							{
								temp.set(j, PrefixSuffix.SPELLCHECK_+"KEYWORD_MOST_FREQUENT_"+dummy+"_MOST_FREQUENT_KEYWORD"+PrefixSuffix._SPELLCHECK+",");
							}
						}
						else if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
						{
							temp.set(j, PrefixSuffix.SPELLCHECK_+"KEYWORD_MOST_FREQUENT_"+dummy+"_MOST_FREQUENT_KEYWORD"+PrefixSuffix._SPELLCHECK);
						}
					}
						
					
				}
			}
			
			String final_output = String.join(" ", temp);
			id.getOutputList().remove(i);
			id.getOutputList().add(i, final_output);	
		}
		
		
		if(SingletonMyLogger.DebugLevel.SPELLCHECKDECORATOR == SingletonMyLogger.getInstance().getDebugValue())
		{
			try {
				((FileDisplayInterface) id).writeToFile("SPELLCHECKDECORATOR");
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