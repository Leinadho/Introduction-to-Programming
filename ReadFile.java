
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class ReadFile{

	public String file_path;
	public String[] Input;
	
	ReadFile(String file_path){
		this.file_path = file_path;
		
	}


	public String[] openFile() throws IOException{
		FileReader fr = new FileReader(file_path);
		BufferedReader textReader = new BufferedReader(fr);
		
		int numberOfLines = readLines();
		String[] textData = new String[numberOfLines];
		for(int i = 0; i<numberOfLines;i++){
			textData[i] =textReader.readLine();
		}
		textReader.close();
		return textData;
	}
	
	public int readLines() throws IOException{
		
		FileReader fr = new FileReader(file_path);
		BufferedReader textReader = new BufferedReader(fr);
		//String line;
		int numberOfLines = 0;
		
		while(textReader.readLine() != null)numberOfLines++;
		textReader.close();
		return numberOfLines;
	}
}
