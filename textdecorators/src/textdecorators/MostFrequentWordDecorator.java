package textdecorators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import textdecorators.util.InputDetails;

public class MostFrequentWordDecorator extends AbstractTextDecorator{
	private AbstractTextDecorator atd;
	private InputDetails id;

	public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
			atd = atdIn;
			id = idIn;
	}

	/**
	 *
	 */
	@Override
	public void processInputDetails() {
		// Decorate input details.
		
		List<String> wordsListDummy = new ArrayList<String>();
		
		for(int i = 0; i < id.getWordsList().size(); i++)
		{
			if(id.getWordsList().get(i) != "")
			{
				wordsListDummy.add(id.getWordsList().get(i));
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
				
			}
			
			String final_output = String.join(" ", temp);
			id.getOutputList().remove(i);
			id.getOutputList().add(i, final_output);	
		}
		
		//System.out.println("in most freq "+id.getOutputList());
		
		if (null != atd) {
			atd.processInputDetails();
		}
	}

}
