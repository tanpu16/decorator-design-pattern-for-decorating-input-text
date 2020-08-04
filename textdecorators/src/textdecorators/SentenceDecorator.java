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
		
		for(int i = 0; i < id.getWordsList().size() ; i++)
		{
			if(0 == i)
			{
				String str = PrefixSuffix.BEGIN_SENTENCE__.toString()+id.getWordsList().get(i);
				System.out.println(str);
			}
			if(id.getWordsList().get(i).toString().equals("."))
			{
				
			}
		}
		
		// Forward to the next decorator, if any.
		if (null != atd) {
			atd.processInputDetails();
		}
	}


}
