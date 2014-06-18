import java.util.Map;


public class OperatorPriority {
	private static Map<Character, Integer> dictionary;
	
	private static void InitializeDictionary(){
		dictionary.put('-', 1);
		dictionary.put('+', 1);
		dictionary.put('*', 2);
		dictionary.put('/', 2);
		dictionary.put('(', 3);
		dictionary.put(')', 3);
	}
	
	public static int Priority(char ch){
		if(dictionary == null){
			InitializeDictionary();
		}
		
		return dictionary.get(ch);
	}
}
