import java.lang.Character;

public class Main{

    public void Main(){
	;
    }

    public static void main(String[] args){
	dragon();
    }

    public static void dragon(){
	String axiom = "FX";
	Dictionary dict = new Dictionary();
	dict.takeRule("X", "X+YF");
	dict.takeRule("Y", "FX-Y");
	String instructions = parse(dict, axiom, 5);
    }

    public static String parse(Dictionary dict, String axiom, int iterations){
	String instructions = axiom;
	String nextResult = "";
	for (int i = 0; i<iterations; i++){
	    for (int j = 0; j<instructions.length(); j++){
		nextResult += (dict.lookUp(Character.toString(instructions.charAt(j))));
	    }
	    instructions = nextResult;
	    nextResult = "";
	}
	System.out.println(instructions);
	return null;
    }

}