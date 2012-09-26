public class Main{

    public void Main(){
	;
    }

    public static void main(String[] args){

	dragon();
	
    }

    public static void dragon(){

	Dictionary dict = new Dictionary();
	dict.takeRule("X", "X+YF");
	dict.takeRule("Y", "FX-Y");
    }

}