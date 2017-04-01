package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
 
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		TrieNode current = root;
		String lowerCase = word.toLowerCase();
		char[] charArray = lowerCase.toCharArray();
		for(int i = 0; i < charArray.length; i++){
			TrieNode child = current.getChild(charArray[i]);
			if(child == null){
				current.insert(charArray[i]);	
				current = current.getChild(charArray[i]);
				if(lowerCase.equals(current.getText())){
					current.setEndsWord(true);
					size++;
					return true;
				}
			}else if(lowerCase.equals(child.getText()) && child.endsWord() != true){
				child.setEndsWord(true);
				size++;
				return true;
			}else{
				current = child;
			}
		}
		return false;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	public boolean charInTrie(char c){
		return false;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		if(s == ""){
			return false;
		}
		String lowerCase = s.toLowerCase();
		char[] charArray = lowerCase.toCharArray();
		TrieNode current;
		if(root.getChild(charArray[0]) != null){
			current = root.getChild(charArray[0]);
		}else{
			return false;
		}
		for(int i = 1; i < charArray.length; i++){
			TrieNode child = current.getChild(charArray[i]);
			if(lowerCase.equals(current.getText().toLowerCase())){
				return true;
			}else if (child != null){
				if(lowerCase.equals(child.getText().toLowerCase())){
					return true;
				}else{
					current = child;
				}
			}						
		}
		return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	List<String> completions = new ArrayList<String>();
    	Queue<TrieNode> queue = new LinkedList<TrieNode>();
    	if(numCompletions > 0){
    		if(prefix == ""){
				queue.add(root);
    		}else{
				String lowerCase = prefix.toLowerCase();
				char[] charArray = lowerCase.toCharArray();
				TrieNode current = root.getChild(charArray[0]);
				//TrieNode current = root;
				if(current != null){
					for(int i = 1; i <= charArray.length; i++){
						//TrieNode child = current.getChild(charArray[i]);
							if(lowerCase.equals(current.getText().toLowerCase())){
							if(current.endsWord()){
								completions.add(current.getText());
							}
							queue.add(current);
							break;
						}
						current = current.getChild(charArray[i]);	
					}
				}			
			}
			while(!queue.isEmpty() && completions.size() < numCompletions){
				TrieNode check = queue.remove();
				Set<Character> nextLevelCharacters = check.getValidNextCharacters();
				for(Character letter: nextLevelCharacters){
					if(completions.size() < numCompletions){
						TrieNode next = check.getChild(letter);
						if(next.endsWord()){
							completions.add(next.getText());
						}
						queue.add(next);
					}
				}	
			}
    	}
		return completions;
     }
    
 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}