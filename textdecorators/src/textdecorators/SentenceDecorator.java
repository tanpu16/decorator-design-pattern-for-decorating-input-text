package textdecorators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import textdecorators.util.FileDisplayInterface;
import textdecorators.util.InputDetails;
import textdecorators.util.SingletonMyLogger;
import textdecorators.util.SingletonMyLogger.DebugLevel;

public class SentenceDecorator extends AbstractTextDecorator{

	private AbstractTextDecorator atd;
	private InputDetails id;

	//constructor
	public SentenceDecorator(AbstractTextDecorator atdIn, InputDetails idIn) {
		atd = atdIn;
		id = idIn;
		SingletonMyLogger.getInstance().writeMessage("SentenceDecorator Constructor called", DebugLevel.CONSTRUCTOR);
	}

	//toString method
	@Override
	public String toString()
	{
		return "Class PopulateMyArrayVisitor [AbstractTextDecorator is -> "+atd+"InputDetails is"+id+"]";
	}
	
	/*this is void method, which process the input and add Sentence
	 decorator to start and end of the sentence
	@param NA 
	@return NA
	@see print nothing, but add Sentence decorator to the sentence
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
				if(0 == i && 0 == j)
				{
					temp.set(j, PrefixSuffix.BEGIN_SENTENCE__+temp.get(j));
				}
				if(temp.get(j) !="" && temp.get(j).contains("."))
				{
					String dummy = temp.get(j).replace(".","");
					
					if((j+1) < temp.size())
					{
						temp.set(j, dummy+PrefixSuffix.__END_SENTENCE+"."+PrefixSuffix.BEGIN_SENTENCE__);
					}
					else
					{
						temp.set(j, dummy+PrefixSuffix.__END_SENTENCE+".");
					}
					
				
				}
				else if(temp.get(j) !="" && temp.get(j).matches(".*[.].*$"))
				{
					String dummy1 = temp.get(j).replaceAll("^|[a-zA-Z0-9]+.$","");
					String dummy2 = temp.get(j).replaceAll("^[a-zA-Z0-9]+.|$","");
					
					String first = dummy1.replace(".", "")+PrefixSuffix.__END_SENTENCE+".";
					String second = dummy2;
					if(dummy2.contains("."))
					{
						second = dummy2.replace(".", "")+PrefixSuffix.__END_SENTENCE+".";
					}
					temp.set(j, first+PrefixSuffix.BEGIN_SENTENCE__+second);
				}
				
			}
			String final_output = String.join(" ", temp);
			id.getOutputList().remove(i);
			id.getOutputList().add(i, final_output);	
			
		}
		
		if(SingletonMyLogger.DebugLevel.SENTENCEDECORATOR == SingletonMyLogger.getInstance().getDebugValue())
		{
			try {
				((FileDisplayInterface) id).writeToFile("SENTENCEDECORATOR");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Forward to the next decorator, if any.
		if (null != atd) {
			atd.processInputDetails();
		}
	}

}
