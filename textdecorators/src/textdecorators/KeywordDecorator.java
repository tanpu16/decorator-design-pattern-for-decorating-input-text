package textdecorators;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		
		/*
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
		*/
		
		for(int i = 0; i < id.getOutputList().size(); i++)
		{
			String str = id.getOutputList().get(i);
			List<String> temp = new ArrayList<String>();
			temp.addAll(Arrays.asList(str.split("\\s")));
			
			
			for(int j=0; j<temp.size(); j++)
			{
				for(int k=0 ; k<id.getKeywordsList().size(); k++)
				{
					if(temp.get(j) !="" && temp.get(j).toLowerCase().equals(id.getKeywordsList().get(k)))
					{
						//id.getOutputList().set(i, PrefixSuffix.KEYWORD_+id.getOutputList().get(i)+PrefixSuffix._KEYWORD);
						temp.set(j, PrefixSuffix.KEYWORD_+temp.get(j)+PrefixSuffix._KEYWORD);
					}
					else if(temp.get(j) !="" && temp.get(j).contains("."))
					{
							String dummy = temp.get(j).replace(".","");
							if(dummy.toLowerCase().equals(id.getKeywordsList().get(k)))
							{
								temp.set(j, PrefixSuffix.KEYWORD_+dummy+PrefixSuffix._KEYWORD+".");
							}
					}
					else if(temp.get(j) !="" && temp.get(j).contains(","))
					{
							String dummy = temp.get(j).replace(",","");
							if(dummy.toLowerCase().equals(id.getKeywordsList().get(k)))
							{
								temp.set(j, PrefixSuffix.KEYWORD_+dummy+PrefixSuffix._KEYWORD+",");
							}
					}
					
				}
			}
			
			String final_output = String.join(" ", temp);
			id.getOutputList().remove(i);
			id.getOutputList().add(i, final_output);	
		}
		
		//System.out.println("in keyword "+id.getOutputList());
		
		if (null != atd) {
			atd.processInputDetails();
		}
	}


}
