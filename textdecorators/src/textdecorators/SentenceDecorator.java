package textdecorators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import textdecorators.util.InputDetails;

public class SentenceDecorator extends AbstractTextDecorator{

	private AbstractTextDecorator atd;
	private InputDetails id;

	public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
	}

	@Override
	public void processInputDetails() {
		// Decorate input details.
		
		/*
		for(int i=0; i < id.getWordsList().size() ; i++)
		{
			if(0 == i && id.getWordsList().get(i) !="")
			{
				id.getOutputList().set(i, PrefixSuffix.BEGIN_SENTENCE__+id.getOutputList().get(i));
			}
			if(id.getWordsList().get(i) !="" && id.getWordsList().get(i).contains("."))
			{
				String dummy = id.getOutputList().get(i).replace(".","");
				id.getOutputList().set(i, dummy+PrefixSuffix.__END_SENTENCE+".");
				if((i+1) < id.getWordsList().size())
				{
					id.getOutputList().set(i+1, PrefixSuffix.BEGIN_SENTENCE__+id.getOutputList().get(i+1));
				}
			}
		}  
		
		*/
		
		for(int i = 0; i < id.getOutputList().size(); i++)
		{
			String str = id.getOutputList().get(i);
			List<String> temp = new ArrayList<String>();
			temp.addAll(Arrays.asList(str.split("\\s")));
			
			
			for(int j=0; j<temp.size(); j++)
			{
				if(0 == j && temp.get(j) !="")
				{
					temp.set(j, PrefixSuffix.BEGIN_SENTENCE__+temp.get(j));
				}
				if(temp.get(j) !="" && temp.get(j).contains("."))
				{
					String dummy = temp.get(j).replace(".","");
					temp.set(j, dummy+PrefixSuffix.__END_SENTENCE+".");
					if((j+1) < id.getWordsList().size())
					{
						temp.set(j+1, PrefixSuffix.BEGIN_SENTENCE__+temp.get(j+1));
					}
				}
				
			}
			String final_output = String.join(" ", temp);
			id.getOutputList().remove(i);
			id.getOutputList().add(i, final_output);	
			
		}
		
		System.out.println("in sentence decorator "+id.getOutputList());
		
		// Forward to the next decorator, if any.
		if (null != atd) {
			atd.processInputDetails();
		}
	}

}
