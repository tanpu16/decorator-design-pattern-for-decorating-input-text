package textdecorators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import textdecorators.util.FileDisplayInterface;
import textdecorators.util.InputDetails;
import textdecorators.util.SingletonMyLogger;
import textdecorators.util.SingletonMyLogger.DebugLevel;

public class MostFrequentWordDecorator extends AbstractTextDecorator{
	private AbstractTextDecorator atd;
	private InputDetails id;

	public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
			atd = atdIn;
			id = idIn;
			SingletonMyLogger.getInstance().writeMessage("MostFrequentWordDecorator Constructor called", DebugLevel.CONSTRUCTOR);
	}

	//toString method
	@Override
	public String toString()
	{
		return "Class PopulateMyArrayVisitor [AbstractTextDecorator is -> "+atd+"InputDetails is"+id+"]";
	}
	
	/*this is void method, which process the input and add MostFrequentWord
	 decorator to the word which occur maximum time in input file
	@param NA 
	@return NA
	@see print nothing, but add MostFrequentWord decorator to the words
	*/
	@Override
	public void processInputDetails() {
		// Decorate input details.
		
		List<String> wordsList = new ArrayList<String>();
		
		for(int i=0; i<id.getOutputList().size(); i++)
		{
			String next = id.getOutputList().get(i);
			wordsList.addAll(Arrays.asList(next.split("\\s")));					
		}
		
		Map<String, Long> counts =
				wordsList.stream().collect(Collectors.groupingBy(list -> list.toString().toLowerCase(), Collectors.counting()));
		
		Map.Entry<String, Long> freqEntry = null;

		for (Map.Entry<String, Long> entry : counts.entrySet())
		{
		    if (freqEntry == null || entry.getValue().compareTo(freqEntry.getValue()) > 0)
		    {
		    	freqEntry = entry;
		    }
		}
		
		
		for(int i = 0; i < id.getOutputList().size(); i++)
		{
			String str = id.getOutputList().get(i);
			List<String> temp = new ArrayList<String>();
			temp.addAll(Arrays.asList(str.split("\\s")));
			
			
			for(int j=0; j<temp.size(); j++)
			{
				if(temp.get(j).toLowerCase().equals(freqEntry.getKey()))
				{
					temp.set(j, PrefixSuffix.MOST_FREQUENT_+temp.get(j)+PrefixSuffix._MOST_FREQUENT);
				}
				if(temp.get(j).contains("."))
				{
					String dummy = temp.get(j).replace(".","");
					if(dummy.toLowerCase().equals(freqEntry.getKey()))
					{
						temp.set(j, PrefixSuffix.MOST_FREQUENT_+dummy+PrefixSuffix._MOST_FREQUENT+".");
					}
				}
				if(temp.get(j).contains(","))
				{
					String dummy = temp.get(j).replace(",","");
					if(dummy.toLowerCase().equals(freqEntry.getKey()))
					{
						temp.set(j, PrefixSuffix.MOST_FREQUENT_+dummy+PrefixSuffix._MOST_FREQUENT+",");
					}
				}
				
			}
			
			String final_output = String.join(" ", temp);
			id.getOutputList().remove(i);
			id.getOutputList().add(i, final_output);	
		}
		
		if(SingletonMyLogger.DebugLevel.MOSTFREQUENTWORDDECORATOR == SingletonMyLogger.getInstance().getDebugValue())
		{
			try {
				((FileDisplayInterface) id).writeToFile("MOSTFREQUENTWORDDECORATOR");
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
