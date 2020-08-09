package textdecorators.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import textdecorators.PrefixSuffix;
import textdecorators.util.SingletonMyLogger.DebugLevel;

public class InputDetails implements FileDisplayInterface {

	FileProcessor fpInput,fpMisspelled,fpKeywordFile;
	String resultFile;
	boolean isInValid;
	boolean isEmpty;
	
	private List<String> wordList = new ArrayList<String>();
	private List<String> outputList = new ArrayList<String>();
	private List<String> misspelledList = new ArrayList<String>();
	private List<String> keywordsList = new ArrayList<String>();
	
	//constructor
	public InputDetails(String inputFile, String keywordFile,String misspelledFile) throws IOException 
	{
		try {
			fpInput = new FileProcessor(inputFile);
			fpMisspelled = new FileProcessor(misspelledFile);
			fpKeywordFile = new FileProcessor(keywordFile);
		} catch (InvalidPathException | SecurityException  | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resultFile = null;
		isInValid = false;
		isEmpty= false;
		
		SingletonMyLogger.getInstance().writeMessage("InputDetails Constructor called", DebugLevel.CONSTRUCTOR);
	}
	
	//toString method
	@Override
	public String toString()
	{
		return "Class InputDetails [isInValid is -> "+isInValid+" isEmpty is ->"+isEmpty+"]";
	}
	
	/*this is void method, which set the respective output path
	@param String file path 
	@return void
	@see set the respective filepath
	*/
	public void setFilePath(String pathIn)
	{
		resultFile=pathIn;
	}
	
	/*this is void method, which parse all the 3 input files and
	store sentences/words into respective lists.
	@param NA
	@return void
	@see parse the input and stores it into list.
	*/
	public void parseInput() throws IOException
	{
		SingletonMyLogger.getInstance().writeMessage("Input parser method called", DebugLevel.PARSER);
		String str,keyword,misspell = null;
		try {
				str = fpInput.poll();
				misspell = fpMisspelled.poll();
				keyword = fpKeywordFile.poll();
				
				if(null == str)
				{
					isEmpty = true;
				}
				else if(null == misspell)
				{
					isEmpty = true;
				}
				else if(null == keyword)
				{
					isEmpty = true;
				}
				while(null != str)
				{
					
					if(str.matches("[a-zA-Z0-9.,\s\n\t\r\f]+$"))
					{
						outputList.add(str);
						isInValid= false;
					}
					else
					{
						isInValid= true;
					}
					try
					{
						if(isInValid)
						{
							throw new IOException("Invalid word in input file ! Exiting!!!");
						}

					}
					catch(IOException ie)
					{
						ie.printStackTrace();
						System.exit(0);
					}
					str = fpInput.poll();
				}
				
				
				for(int i=0; i< outputList.size(); i++)
				{
					String next = outputList.get(i);
					wordList.addAll(Arrays.asList(next.split("\\s")));	
					if(i < outputList.size()-1)
					{
						wordList.add("\n");
					}
				}
				
				while(null != misspell)
				{
					if(misspell.matches("[a-zA-Z0-9]+$"))
					{
						misspelledList.add(misspell.toLowerCase());
						isInValid = false;
					}
					else
					{
						isInValid = true;
					}
					try
					{
						if(isInValid)
						{
							throw new IOException("Invalid Input misspell file! Exiting!!!");
						}
					}
					catch(IOException ie)
					{
						ie.printStackTrace();
						System.exit(0);
					}
					misspell = fpMisspelled.poll();
				}
				
				while(null != keyword)
				{
					if(keyword.matches("[a-zA-Z0-9]+$"))
					{
						keywordsList.add(keyword.toLowerCase());
						isInValid = false;
					}
					else
					{
						isInValid = true;
					}
					try
					{
						if(isInValid)
						{
							throw new IOException("Invalid word in keyword file! Exiting!!!");
						}
					}
					catch(IOException ie)
					{
						ie.printStackTrace();
						System.exit(0);
					}
					keyword = fpKeywordFile.poll();
				}

		}
		catch(IOException e)
		{
			System.err.println("IOException occured! Exiting!");
			e.printStackTrace();
			System.exit(0);
		}
		try
		{
			if(isEmpty)
			{
				throw new IOException("File is empty! Exiting!!!");
			}
		}
		catch(IOException ie)
		{
			ie.printStackTrace();
			System.exit(0);
		}
		finally
		{
			fpInput.close();
			fpMisspelled.close();
			fpKeywordFile.close();
		}
		
	}

	/*this is generic void method, which write output to all output files depends on
	the instance used to call this method.
	@param NA
	@return void
	@see write content into the output file.
	*/
	@Override
	public void writeToFile(PrefixSuffix prefix, PrefixSuffix suffix) throws IOException {
		// TODO Auto-generated method stub
		SingletonMyLogger.getInstance().writeMessage("writeToFile method called", DebugLevel.FILEWRITER);
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(resultFile)));
		//String final_output = null;
		try
		{
			if(prefix != PrefixSuffix.NONE)
			{
				bw.write(prefix.toString());
			}
			for(int i=0; i< wordList.size(); i++)
			{
				bw.write(wordList.get(i));
				if(!wordList.get(i).equals("\n"))
				{
					bw.write(" ");
				}
				else
				{
					bw.write("");
				}
			}
			if(suffix != PrefixSuffix.NONE)
			{
				bw.write(suffix.toString());
			}
			bw.close();
		}
		catch (InvalidPathException | SecurityException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		} 
		catch(IOException  e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		finally
		{
			bw.close();
		}
	}

	/*getter method which returns private member misspelledList
	@param NA
	@return String List
	@see print nothing but return misspelledList
	*/
	public List<String> getMisspelledList() {
		return misspelledList;
	}

	/*set value of private member misspelledList
	@param misspelledList
	@return NA
	@see print nothing but set current misspelledList
	*/
	public void setMisspelledList(List<String> misspelledListIn) {
			misspelledList = misspelledListIn;
	}

	/*getter method which returns private member keywordsList
	@param NA
	@return String List
	@see print nothing but return keywordsList
	*/
	public List<String> getKeywordsList() {
		return keywordsList;
	}

	/*set value of private member keywordsList
	@param keywordsList
	@return NA
	@see print nothing but set current keywordsList
	*/
	public void setKeywordsList(List<String> keywordsListIn) {
			keywordsList = keywordsListIn;
	}

	/*getter method which returns private member outputList
	@param NA
	@return String List
	@see print nothing but return outputList
	*/
	public List<String> getOutputList() {
		return outputList;
	}

	/*set value of private member outputList
	@param outputList
	@return NA
	@see print nothing but set current outputList
	*/
	public void setOutputList(List<String> outputListIn) {
			outputList = outputListIn;
	}

	/*getter method which returns private member wordList
	@param NA
	@return String List
	@see print nothing but return wordList
	*/
	public List<String> getWordList() {
		return wordList;
	}

	/*set value of private member wordList
	@param wordList
	@return NA
	@see print nothing but set current wordList
	*/
	public void setWordList(List<String> wordListIn) {
			wordList = wordListIn;
	}
}
