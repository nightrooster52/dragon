import java.lang.Character;

public class Main{
    
    public void Main(){
	;
    }

    public static void main(String[] args) throws Exception{
	String instructions = dragon();
	Pen pen = new Pen(500, 500, instructions);
	pen.blueShift = (float)0.01;
	pen.redShift = (float)0.02;
	//pen.greenShift = 3;

	pen.draw();

    }

    public static String dragon(){
	String axiom = "FX";
	Dictionary dict = new Dictionary();
	dict.takeRule("X", "X+YF");
	dict.takeRule("Y", "FX-Y");
	return instructions(dict, axiom, 17);
    }

    public static String instructions(Dictionary dict, String axiom, int iterations){
	String instructions = axiom;
	String nextResult = "";
	for (int i = 0; i<iterations; i++){
	    for (int j = 0; j<instructions.length(); j++){
		nextResult += (dict.lookUp(Character.toString(instructions.charAt(j))));
		System.out.println(((float)j)/instructions.length());
	    }
	    instructions = nextResult;
	    nextResult = "";

	}
	return instructions;
    }

}