package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		if(sourceText.length() != 0 || sourceText != null){
			String words[] = sourceText.split(" +");
			starter = words[0];
			String previous = starter;	
			for(int i = 0; i < words.length; i++){
				if(i == 0){
					ListNode node = new ListNode(words[0]);
					node.addNextWord(words[1]);
					wordList.add(node);
					previous = words[1];
				}else{
					for(int j = 0; j < wordList.size(); j++){
						ListNode currentNode = wordList.get(j);
						if(previous.equals(currentNode.getWord())){
							currentNode.addNextWord(words[i+1]);
							break;
						}else if(!previous.equals(currentNode.getWord()) && j == wordList.size()-1){
							ListNode newNode = new ListNode(previous);
							if(i < words.length - 1){
								newNode.addNextWord(words[i+1]);
							}else{
								newNode.addNextWord(starter);
							}
							wordList.add(newNode);
							break;
						}
					}
				}
				if(i < words.length - 1){
					previous = words[i+1];
				}
			}
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		//System.out.println(wordList.size());
		String output = "";
		if(numWords != 0){
			if(wordList.size() != 0){
				String currWord = wordList.get(0).getWord();
				output = output+" "+currWord;
				//String newString = output + currWord;
				int wordCount = 1;
				while(wordCount < numWords){
					for(int i = 0; i < wordList.size(); i++){
						ListNode currentNode = wordList.get(i);
						if(currWord.equals(currentNode.getWord())){
							String w = currentNode.getRandomNextWord(rnGenerator);
							output = output+" "+w;
							currWord = w;
							wordCount++;
							break;
						}
					}
				}
			}
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		if(sourceText.length() != 0){
			train(sourceText);
		}
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		//gen.train("");
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		//System.out.println(textString);
		gen.train(textString);
		//gen.train("");
		//System.out.println(gen);
		System.out.println(gen.generateText(20));
		/*String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);*/
		//System.out.println(gen);
		//System.out.println(gen.generateText(20));
		gen.retrain("");
		gen.retrain(null);
		System.out.println(gen);
		System.out.println(gen.generateText(30));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int a = generator.nextInt(nextWords.size());
	    return nextWords.get(a);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


