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

	//constructor
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
		
		List<String> wordsListDummy = new ArrayList<String>();
		
		for(int i = 0; i < id.getWordList().size(); i++)
		{
			if(id.getWordList().get(i) != "")
			{
				wordsListDummy.add(id.getWordList().get(i));
			}
		}
		
		Map<String, Long> counts =
				wordsListDummy.stream().collect(Collectors.groupingBy(list -> list.toString().toLowerCase(), Collectors.counting()));
		
		Map.Entry<String, Long> freqEntry = null;

		for (Map.Entry<String, Long> entry : counts.entrySet())
		{
		    if (freqEntry == null || entry.getValue().compareTo(freqEntry.getValue()) > 0)
		    {
		    	freqEntry = entry;
		    }
		}
		
		
		List<String> updatedList = new ArrayList<String>();
		
		for(int i=0; i< id.getOutputList().size(); i++)
		{
			String next = id.getOutputList().get(i);
			updatedList.addAll(Arrays.asList(next.split("\\s")));	
			updatedList.add("\n");
		}
		
		
		//System.out.println("in inpu1 "+updatedList);
	
		for(int j=0; j<updatedList.size(); j++)
		{
			if(updatedList.get(j).toLowerCase().equals(freqEntry.getKey()))
			{
				id.getWordList().set(j, PrefixSuffix.MOST_FREQUENT_+id.getWordList().get(j)+PrefixSuffix._MOST_FREQUENT);
			}
			else if(updatedList.get(j).contains("."))
			{
				String dummy = updatedList.get(j).replace(".","");
				if(dummy.toLowerCase().equals(freqEntry.getKey()))
				{
					id.getWordList().set(j, PrefixSuffix.MOST_FREQUENT_+dummy+PrefixSuffix._MOST_FREQUENT+".");
				}
			}
			else if(updatedList.get(j).contains(","))
			{
				String dummy = updatedList.get(j).replace(",","");
				if(dummy.toLowerCase().equals(freqEntry.getKey()))
				{
					id.getWordList().set(j, PrefixSuffix.MOST_FREQUENT_+dummy+PrefixSuffix._MOST_FREQUENT+",");
				}
			}
				
		}
			
		
		if(SingletonMyLogger.DebugLevel.MOSTFREQUENTWORDDECORATOR == SingletonMyLogger.getInstance().getDebugValue())
		{
			try {
				((FileDisplayInterface) id).writeToFile(PrefixSuffix.MOSTFREQUENTWORDDECORATOR__,PrefixSuffix.__MOSTFREQUENTWORDDECORATOR);
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
