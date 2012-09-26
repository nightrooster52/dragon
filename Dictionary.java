import java.util.LinkedList;


public class Dictionary{

    private LinkedList<String> words = new LinkedList();
    private LinkedList<String> definitions = new LinkedList();

    public void takeRule(String key, String definition){
	words.add(key);
	definitions.add(definition);
    }

    public String lookUp(String key){
	int index = words.indexOf(key);
	if (index == -1)
	    return null;
	return definitions.get(index);
	       

    }


}