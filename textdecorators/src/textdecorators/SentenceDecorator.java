package textdecorators;

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
		
		for(int i=0; i < id.getWordsList().size() ; i++)
		{
			if(0 == i)
			{
				id.getOutputList().set(i, PrefixSuffix.BEGIN_SENTENCE__+id.getOutputList().get(i));
			}
			if(id.getWordsList().get(i).contains("."))
			{
				String dummy = id.getOutputList().get(i).replace(".","");
				id.getOutputList().set(i, dummy+PrefixSuffix.__END_SENTENCE+".");
				if((i+1) < id.getWordsList().size())
				{
					id.getOutputList().set(i+1, PrefixSuffix.BEGIN_SENTENCE__+id.getOutputList().get(i+1));
				}
			}
		}  
		
		//System.out.println("in sentence decorator "+id.getOutputList());
		
		// Forward to the next decorator, if any.
		if (null != atd) {
			atd.processInputDetails();
		}
	}


}
