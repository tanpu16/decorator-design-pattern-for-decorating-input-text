package textdecorators.util;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.HashSet;
import java.util.Set;

//import arrayvisitors.util.SingletonMyLogger.DebugLevel;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	String path;
	StringBuffer finalResult= new StringBuffer(); 
	Set<Integer> commonInts = new HashSet<Integer>();
	int index = 1;
	
	//parameterized constructor
	public Results(String FilePath) 
	{
		//SingletonMyLogger.getInstance().writeMessage("Results constructor called", DebugLevel.CONSTRUCTOR);
		path = FilePath;
	}

	@Override
	public String toString()
	{
			return "Class Results [Path is -> "+path+"]";
	}
	
	/*store is void method,in which finalResult stores the final output in Set
	which we can write into the file and stdout
	@param int
	@return void
	@see print nothing but store integers into set
	*/
	public void store(int value)
	{
		commonInts.add(value);

	}
	
	
	/*this is generic void method, which write output to all 3 output.txt file depends on
	the instance used to call this method.
	@param NA
	@return void
	@see write content of the StringBuffer into the file.
	*/
	public void writeToFile() throws IOException,InvalidPathException,SecurityException,FileNotFoundException
	{
		//SingletonMyLogger.getInstance().writeMessage("Write to file method called", DebugLevel.RESULT);
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(path)));
		
		try
		{
			for(int itr : commonInts)
			{
				String str;
				if(itr < 10)
				{
					str = String.format("%02d", itr);
				}
				else
				{
					str = Integer.toString(itr);
				}
				finalResult.append(str);
				finalResult.append("\n");
			}
			bw.write(finalResult.toString());
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
	
	/*this is void method which is used to write output to console.
	@param NA
	@return NA
	@see write content of StringBuffer to stdout
	 */
	public void writeToStdout()
	{
		//SingletonMyLogger.getInstance().writeMessage("Write to stdout method called", DebugLevel.RESULT);
		System.out.println(finalResult);	
	}


}
