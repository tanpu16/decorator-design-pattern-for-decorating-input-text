package textdecorators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		for(int i = 0; i < id.getOutputList().size(); i++)
		{
			String str = id.getOutputList().get(i);
			List<String> temp = new ArrayList<String>();
			temp.addAll(Arrays.asList(str.split("\\s")));
			
			
			for(int j=0; j<temp.size(); j++)
			{
				for(int k=0 ; k<id.getMisspelledList().size(); k++)
				{
					if(temp.get(j) !="" && temp.get(j).toLowerCase().equals(id.getMisspelledList().get(k)))
					{
						//id.getOutputList().set(i, PrefixSuffix.KEYWORD_+id.getOutputList().get(i)+PrefixSuffix._KEYWORD);
						temp.set(j, PrefixSuffix.SPELLCHECK_+temp.get(j)+PrefixSuffix._SPELLCHECK);
					}
					else if(temp.get(j) !="" && temp.get(j).contains("."))
					{
							String dummy = temp.get(j).replace(".","");
							if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
							{
								temp.set(j, PrefixSuffix.SPELLCHECK_+dummy+PrefixSuffix._SPELLCHECK+".");
							}
					}
					else if(temp.get(j) !="" && temp.get(j).contains(","))
					{
							String dummy = temp.get(j).replace(",","");
							if(dummy.toLowerCase().equals(id.getMisspelledList().get(k)))
							{
								temp.set(j, PrefixSuffix.SPELLCHECK_+dummy+PrefixSuffix._SPELLCHECK+",");
							}
					}
					
				}
			}
			
			String final_output = String.join(" ", temp);
			id.getOutputList().remove(i);
			id.getOutputList().add(i, final_output);	
		}
		
		//System.out.println("in spell check "+id.getOutputList());
		
		if (null != atd) {
			atd.processInputDetails();
		}
	}

}