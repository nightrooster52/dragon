import java.util.Arrays;
import java.lang.Character;
import java.io.*;
import java.nio.*;

public class Main{

  public void Main(){
    ;
  }

  public static void main(String[] args) throws Exception{
    
    
    boolean[] bInstructions = efficientDragon(17); //iterations
    
    //starting x, starting y, instructions
    Pen pen = new Pen(600, 370, bInstructions);

    //starting colors
    pen.red = 255;
    pen.green = 0;
    pen.blue = 255;


    //shift is per entire dragon
    //-1 subtracts 255 over the course of the dragon
    //2 would add 255 twice over the course of the dragon
    pen.redShift = (double)-1;
    pen.greenShift = (double)0;
    pen.blueShift = (double)-1;

    pen.draw();
    
  }

  /* sunset dragon settings
    //starting colors
    pen.red = 255;
    pen.green = 120;
    pen.blue = 0;
    //shift is per entire dragon

    pen.redShift = (double)-1;
    pen.greenShift = (double)-1;
    pen.blueShift = (double)4;
  */
  public static void saveInstructions(String text, String title) throws IOException{
    Writer output = null;
    File file = new File(title);
    output = new BufferedWriter(new FileWriter(file));
    output.write(text);
    output.close();
  }

  public static String readInstructions(String title) throws IOException{
    BufferedReader reader = new BufferedReader(new FileReader (title));
    String line = null;
    StringBuilder stringBuilder= new StringBuilder();
    String ls = System.getProperty("line.separator");

    while((line = reader.readLine()) != null) {
      stringBuilder.append(line);
      stringBuilder.append(ls);
    }
    return stringBuilder.toString();
  }
  public static String dragon(int iterations){
    String axiom = "FX";
    return dragon(iterations, axiom);
  }


  public static String dragon(int iterations, String axiom){
    Dictionary dict = new Dictionary();
    dict.takeRule("X", "X+YF");
    dict.takeRule("Y", "FX-Y");
    return instructions(dict, axiom, iterations);
  }
  //lets do recursion

  public static boolean[] efficientDragon(int iterations){
    boolean[] axiom = {true};
    return efficientDragon(iterations, axiom);
  }

  public static boolean[] efficientDragon(int iterations, boolean[] axiom){ 

    boolean[] instructions = Arrays.copyOf(axiom, (axiom.length*2+1));

    instructions[axiom.length] = true;
    for (int i = 1; i < axiom.length; i++){
      instructions[(axiom.length+i)] = !axiom[axiom.length-(i)];
      System.out.println(((float)i)/axiom.length + iterations);
    }
    iterations--;
    if (iterations >= 0) instructions = efficientDragon(iterations, instructions);
    return instructions;      
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