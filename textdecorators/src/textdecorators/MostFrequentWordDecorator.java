package textdecorators;

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
		
		Map<String, Long> counts =
			    id.getWordsList().stream().collect(Collectors.groupingBy(e -> e.toString().toLowerCase(), Collectors.counting()));
		
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
			String str = id.getOutputList().get(i).toLowerCase();
			if(str.equals(freqEntry.getKey()))
			{
				id.getOutputList().set(i, PrefixSuffix.MOST_FREQUENT_+id.getOutputList().get(i)+PrefixSuffix._MOST_FREQUENT);
			}
		}
		
		//System.out.println("in most freq "+id.getOutputList());
		
		if (null != atd) {
			atd.processInputDetails();
		}
	}

}
