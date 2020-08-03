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
		
		// Forward to the next decorator, if any.
		if (null != atd) {
			atd.processInputDetails();
		}
	}


}
