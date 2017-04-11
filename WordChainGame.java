import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordChainGame {
	
	public static String[] englishWords;// = readFile.openFile();

	
	public static void main(String[] args){
		ReadFile readFile = new ReadFile("C:\\Users\\Daniel\\Desktop\\Computer Science\\Intro to Programming\\Assignments\\New folder\\LewisCarrol words\\src\\englishWords.txt");
		try{
			englishWords = readFile.openFile();
		}catch(IOException e){System.out.println(e);}
		
		//System.out.println("first english words: " + englishWords[0] + " "+ englishWords[1] + " "+ englishWords[2] + " "+ englishWords[3]);
		//System.out.println("last english words: "+ englishWords[englishWords.length-4] + " "+ englishWords[englishWords.length-3] + " "+ englishWords[englishWords.length-2] + " "+ englishWords[englishWords.length-1]);
		
		Scanner input = new Scanner(System.in);
		String ans = "";
		System.out.println("Please input a word chain: ");
		boolean invalid = true;
		boolean passed = true;
		while(invalid){
			if(passed == false)System.out.println("Please input a word chain: ");
			passed = true;
			ans = input.nextLine();
			char[] charray = ans.toCharArray();
			for(char character : charray){
				if(character== ' '){
					System.out.println("Please do not include any spaces");
					passed = false;
				}
			}
			if(passed)invalid = false;
		}
			// test: ans = "hello-hells-bells-belts-bolts";
			String[] inputWords = readWords(ans);
			if(!isUnique(inputWords)){
				System.out.println("You aren't allowed to repeat words :");
			}
			if(isWordChain(inputWords))System.out.println(ans + " is a valid word chain!");
			else System.out.println(ans + " is not a valid word chain :(");
			input.close();
	}
	
	public static String[] readWords(String input){
		char[] characters =  input.toCharArray();	//array of the input string's characters
		System.out.println("the string entered was :");
		for(char character:characters)System.out.print(character);
		System.out.println();
		ArrayList<String> output = new ArrayList<String>();//holds the individual/separated strings from the input
		
		ArrayList<Character> wordCharacters = new ArrayList<Character>();	//holds the characters which make up a word
		char[] wordCharactersArray;	//an array which allows us to use the toString method, converting characters to a string
		
		//int numberOfWords = 1;
		for(int i = 0; i<characters.length; i++){
			if(characters[i]=='-' || i==characters.length-1){//if all characters of a word have been added to the temporary arrayList
				//numberOfWords++;
				if(i==characters.length-1)wordCharacters.add(characters[i]);
				
				wordCharactersArray = new char[wordCharacters.size()];//add the characters to an array
				//System.out.println("the characters that should be added to the array:\n");
				for(int index =0; index<wordCharactersArray.length;index++){
					//System.out.print(wordCharacters.get(index));
					wordCharactersArray[index]= wordCharacters.get(index);
				}
				//System.out.println();
				//System.out.println("wordCharacters.toString() gives " + wordCharacters.toString());
				//System.out.println();
				//System.out.println("the wordCharactersArray.toString() gives " + wordCharactersArray.toString());
				String o = new String(wordCharactersArray);
				//System.out.println("the String o = new String(wordCharactersArray) gives " + o);
				//output.add(wordCharactersArray.toString());
				output.add(o);
				wordCharacters = new ArrayList<Character>();//reset the arrayList to load the next set
			}
			else wordCharacters.add(characters[i]); //add character of input to an arrayList
			
		}
		
		String[] outputArray = new String[output.size()]; //convert ArrayList to Array for return
		for(int i=0;i<outputArray.length;i++){
			outputArray[i]=output.get(i);
		}
		System.out.println("the words you entered were:");
		for(String word : outputArray)System.out.println(word);
		return outputArray;
	}
	
	public static boolean isUnique(String[] strings){
		for(int index =0; index<strings.length; index++){
			for(int i = index+1; i<strings.length; i++){
				if( strings[index].equalsIgnoreCase(strings[i]) ){System.out.println("isUnique returned false");return false;}
			}
		}
		return true;
	}
	
	public static boolean isWordChain(String[] wordList){
		if(!isUnique(wordList))return false;
		for(String word : wordList){
			if(!isEnglishWord(word))return false;
		}
		for(int index =0; index<wordList.length; index++){
			for(int i = index+1; i<wordList.length; i++){
				if(!isDifferentByOne(wordList[index],wordList[i]))return false;
			}
		}
		return true;
	}
	
	public static boolean isEnglishWord(String supposedWord){
		if(Arrays.binarySearch(englishWords, supposedWord)>-1)return true;
		System.out.println("isEnglishWord returned false");
		return false;
	}
	
	public static boolean isDifferentByOne(String string1,String string2){
		if(string1.length() == string2.length()){
			int count=0;
			char[] chars1 = new char[string1.length()];
			char[] chars2 = new char[string2.length()];
			for(int index = 0; index<string1.length();index++){
				
					if(chars1[index]!=chars2[index])count++;
				
			}
			if(count>1){
				System.out.println("isDifferentByOne returned false when comparing " + string1 + " to " + string2);
				return false;
				}
			return true;
		}
		System.out.println("isDifferentByOne returned false, words were not of same length");
		return false;
	}
}
