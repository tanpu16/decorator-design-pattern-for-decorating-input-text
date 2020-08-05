package textdecorators;



import textdecorators.util.InputDetails;

public class KeywordDecorator extends AbstractTextDecorator{

	private AbstractTextDecorator atd;
	private InputDetails id;

	public KeywordDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
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
				if(id.getWordsList().get(i) !="" && id.getWordsList().get(i).toLowerCase().equals(id.getKeywordsList().get(j)))
				{
					id.getOutputList().set(i, PrefixSuffix.KEYWORD_+id.getOutputList().get(i)+PrefixSuffix._KEYWORD);
				}
				else if(id.getWordsList().get(i) !="" && id.getWordsList().get(i).contains("."))
				{
						String dummy = id.getWordsList().get(i).replace(".","");
						if(dummy.toLowerCase().equals(id.getKeywordsList().get(j)))
						{
							id.getOutputList().set(i, PrefixSuffix.KEYWORD_+dummy+PrefixSuffix._KEYWORD+".");
						}
				}
				else if(id.getWordsList().get(i) !="" && id.getWordsList().get(i).contains(","))
				{
						String dummy = id.getWordsList().get(i).replace(",","");
						if(dummy.toLowerCase().equals(id.getKeywordsList().get(j)))
						{
							id.getOutputList().set(i, PrefixSuffix.KEYWORD_+dummy+PrefixSuffix._KEYWORD+",");
						}
				}
			}
		}  
		
		//System.out.println("in keyword "+id.getOutputList());
		
		if (null != atd) {
			atd.processInputDetails();
		}
	}


}
