package textdecorators.util;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;



public class InputDetails implements FileDisplayInterface{

	FileProcessor fpInput,fpMisspelled,fpKeywordFile = null;
	FileDisplayInterface res = null;
	boolean isValid = false;
	
	private List<String> wordsList = new ArrayList<String>();
	private List<String> misspelledList = new ArrayList<String>();
	private List<String> keywordsList = new ArrayList<String>();
	
	public InputDetails(String inputFile, String misspelledFile, String keywordFile,String outputFile)
	{
		try {
			fpInput = new FileProcessor(inputFile);
			fpMisspelled = new FileProcessor(misspelledFile);
			fpKeywordFile = new FileProcessor(keywordFile);
			res = new Results(outputFile);
		} catch (InvalidPathException | SecurityException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parseInput() throws IOException
	{
		String str,keyword,misspell = null;
		try {
				str = fpInput.poll();
				misspell = fpMisspelled.poll();
				keyword = fpKeywordFile.poll();
				
				while(null != str)
				{
					if(str.matches("[a-zA-Z0-9]+$"))
					{
						wordsList.add(str);
						misspelledList.add(misspell);
						keywordsList.add(keyword);
						
					}
					else if(str.matches("^[a-zA-Z0-9]+[,]$"))
					{
							String dummy = str.replace(",","");
							wordsList.add(dummy);
							wordsList.add(",");
					}
					else if(str.matches("^[a-zA-Z0-9]+[.]$"))
                	{
                        	String dummy = str.replace(".","");
                        	wordsList.add(dummy);
                        	wordsList.add(".");
                	}
					else if(str.matches("^.*[.]$") || str.matches("^.*[,]$"))
                	{
							wordsList.add(str);
                	}
					else
					{
						isValid = true;
					}
					
					str = fpInput.poll();
					misspell = fpMisspelled.poll();
					keyword = fpKeywordFile.poll();
					
				}
			
			
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	public void writeToFile() throws IOException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void store(int value) {
		// TODO Auto-generated method stub
		
	}

	public List<String> getWordsList() {
		return wordsList;
	}

	public void setWordsList(List<String> wordsListIn) {
		wordsList = wordsListIn;
	}

	public List<String> getMisspelledList() {
		return misspelledList;
	}

	public void setMisspelledList(List<String> misspelledListIn) {
			misspelledList = misspelledListIn;
	}

	public List<String> getKeywordsList() {
		return keywordsList;
	}

	public void setKeywordsList(List<String> keywordsListIn) {
			keywordsList = keywordsListIn;
	}
}
