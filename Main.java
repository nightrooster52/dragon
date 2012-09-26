import java.lang.Character;

public class Main{
    
    public void Main(){
	;
    }

    public static void main(String[] args){
	String instructions = dragon();
	Pen pen = new Pen(0, 0, instructions);

    }

    public static String dragon(){
	String axiom = "FX";
	Dictionary dict = new Dictionary();
	dict.takeRule("X", "X+YF");
	dict.takeRule("Y", "FX-Y");
	return instructions(dict, axiom, 5);
    }

    public static String instructions(Dictionary dict, String axiom, int iterations){
	String instructions = axiom;
	String nextResult = "";
	for (int i = 0; i<iterations; i++){
	    for (int j = 0; j<instructions.length(); j++){
		nextResult += (dict.lookUp(Character.toString(instructions.charAt(j))));
	    }
	    instructions = nextResult;
	    nextResult = "";
	}
	return instructions;
    }

}