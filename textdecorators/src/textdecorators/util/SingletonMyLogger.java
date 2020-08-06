package textdecorators.util;

public class SingletonMyLogger {
	
		static SingletonMyLogger uniqueInstance;
	
		//private constructor
		private SingletonMyLogger() {}
		
		/*this is static method, which returns unique instance of this class
		@param NA
		@return SingletonMyLogger instance
		@see just return SingletonMyLogger instance
		*/
		public static SingletonMyLogger getInstance()
		{
			if(null == uniqueInstance)
			{
				uniqueInstance = new SingletonMyLogger();
			}
			
			return uniqueInstance;
		}

	    public enum DebugLevel { MOSTFREQUENTWORDDECORATOR, KEYWORDDECORATOR, SPELLCHECKDECORATOR, SENTENCEDECORATOR, CONSTRUCTOR,FILEWRITER,FILEPROCESSORPOLL, PARSER, NONE };

	    private DebugLevel debugLevel;


	    /*this is a setter method which takes 1 parameter from command line for setting debug level
	     and according to that level one switch case is executed
		@param levelIn set from command line
		@return void
		@see just set the debug level
		*/
	    public void setDebugValue (int levelIn) {
		switch (levelIn) {
			case 8: debugLevel = DebugLevel.PARSER; break;
			case 7: debugLevel = DebugLevel.FILEPROCESSORPOLL; break;
			case 6: debugLevel = DebugLevel.FILEWRITER; break;
			case 5: debugLevel = DebugLevel.CONSTRUCTOR; break;
			case 4: debugLevel = DebugLevel.SENTENCEDECORATOR; break;
			case 3: debugLevel = DebugLevel.SPELLCHECKDECORATOR; break;
			case 2: debugLevel = DebugLevel.KEYWORDDECORATOR; break;
			case 1: debugLevel = DebugLevel.MOSTFREQUENTWORDDECORATOR; break;
			default: debugLevel = DebugLevel.NONE; break;
		}
	    }
	    
	    /*this method is used to set the debugLevel from enum listed
		@param enum DebugLevel
		@return void
		@see just set the Debug level
		*/
	    public void setDebugValue (DebugLevel levelIn) {
	    	debugLevel = levelIn;
	    }

	    /*this method is used to get the debugLevel
		@param NA
		@return DebugLevel
		@see just get the Debug level
		*/
	    public DebugLevel getDebugValue()
	    {
	    	return debugLevel;
	    }
	    
	    /*this method is used to write a message to stdout according to the DebugLevel set
		@param String message written
		@param DebugLevel set
		@return void
		@see write message to stdout
		*/
	    public void writeMessage (String     message  ,
	                                     DebugLevel levelIn ) {
		if (levelIn == debugLevel)
		    System.out.println(message);
	    }

	    public String toString() {
		return "The debug level has been set to the following " + debugLevel;
	    }	
}
