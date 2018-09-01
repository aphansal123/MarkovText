package textgen;

import java.util.Arrays;
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
	
	private boolean isTrained;
	
	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText) {
		// TODO: Implement this method
		if (!isTrained) {
			String[] text = sourceText.split("[ ]+");
			// System.out.println(Arrays.toString(text));
			starter = text[0];
			String prevWord = starter;
			for (int i = 1; i < text.length; i++) {
				ListNode prevWordNode = getListNode(prevWord);
				if (prevWordNode != null) { 				// add the next String to the nextWords list in the Node containing prevWord, in the wordList
					prevWordNode.addNextWord(text[i]);
				} else {
					wordList.add(new ListNode(prevWord));
					getListNode(prevWord).addNextWord(text[i]);
				}
				prevWord = text[i];
			}
			// add starter to be a next word for the last word in the source text.
			wordList.add(new ListNode(text[text.length - 1]));
			getListNode(text[text.length - 1]).addNextWord(starter);
			isTrained = true;
		}
	}
	
	//  returns a ListNode object if 'word' is in wordList and null otherwise.
	private ListNode getListNode(String word) {
		for (ListNode node : wordList) {
			if (node.getWord().equals(word)) {
				return node;
			}
		}
		return null;
	}
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		//return null;
		if (wordList.isEmpty() || numWords == 0) {
			return "";
		}
		String currWord = starter;
		String output = "";
		output += currWord + " ";
		for (int i = 2; i <= numWords; i++) {
			String random_word = getListNode(currWord).getRandomNextWord(rnGenerator);
			output += random_word + " ";
			currWord = random_word;
		}
		return output.trim();
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString() {
		String toReturn = "";
		for (ListNode n : wordList) {
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText) {
		// TODO: Implement this method.
		String[] text = sourceText.split("[ ]+");
		wordList = new LinkedList<ListNode>();
		starter = text[0];
		String prevWord = starter;
		for (int i = 1; i < text.length; i++) {
			ListNode prevWordNode = getListNode(prevWord);
			if (prevWordNode != null) { 				// add the next String to the nextWords list in the Node containing prevWord, in the wordList
				prevWordNode.addNextWord(text[i]);
			} else {
				wordList.add(new ListNode(prevWord));
				getListNode(prevWord).addNextWord(text[i]);
			}
			prevWord = text[i];
		}
		// add starter to be a next word for the last word in the source text.
		wordList.add(new ListNode(text[text.length - 1]));
		getListNode(text[text.length - 1]).addNextWord(starter);
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args) {
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
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
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode {
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord() {
		return word;
	}

	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator) {
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int rand_num = generator.nextInt(nextWords.size());
		return nextWords.get(rand_num);
	}

	public String toString() {
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


