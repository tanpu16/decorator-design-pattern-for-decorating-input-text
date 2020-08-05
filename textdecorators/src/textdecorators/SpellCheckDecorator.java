package textdecorators;

import textdecorators.util.InputDetails;

public class SpellCheckDecorator extends AbstractTextDecorator{

	private AbstractTextDecorator atd;
	private InputDetails id;

	public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
			atd = atdIn;
			id = idIn;
	}

	/**
	 *
	 */
	@Override
	public void processInputDetails() {
		// Decorate input details.
		
		for(int i=0; i < id.getWordsList().size() ; i++)
		{
			for(int j=0; j < id.getKeywordsList().size() ; j++)
			{
				if(id.getWordsList().get(i) !="" && id.getWordsList().get(i).toLowerCase().equals(id.getMisspelledList().get(j)))
				{
					id.getOutputList().set(i, PrefixSuffix.SPELLCHECK_+id.getOutputList().get(i)+PrefixSuffix._SPELLCHECK);
				}
				else if(id.getWordsList().get(i) !="" && id.getWordsList().get(i).contains("."))
				{
						String dummy = id.getWordsList().get(i).replace(".","");
						if(dummy.toLowerCase().equals(id.getMisspelledList().get(j)))
						{
							id.getOutputList().set(i, PrefixSuffix.SPELLCHECK_+dummy+PrefixSuffix._SPELLCHECK+".");
						}
				}
				else if(id.getWordsList().get(i) !="" && id.getWordsList().get(i).contains(","))
				{
						String dummy = id.getWordsList().get(i).replace(",","");
						if(dummy.toLowerCase().equals(id.getMisspelledList().get(j)))
						{
							id.getOutputList().set(i, PrefixSuffix.SPELLCHECK_+dummy+PrefixSuffix._SPELLCHECK+",");
						}
				}
			}
		}  
		
		//System.out.println("in spell check "+id.getOutputList());
		
		if (null != atd) {
			atd.processInputDetails();
		}
	}

}