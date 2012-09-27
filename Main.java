import java.lang.Character;
import java.io.*;
import java.nio.*;

public class Main{

  public void Main(){
    ;
  }

  public static void main(String[] args) throws Exception{
    /*
    for (int i = 18; i < 24; i++){
      String instructions = dragon(i);
      String title = "dragon" + i +".txt";
      saveInstructions(instructions, title);

    }
    */
    String instructions = readInstructions("dragon19.txt");
    
    Pen pen = new Pen(750, 400, instructions);
    pen.red = 120;
    pen.green = 5;
    pen.blue = 00;
    pen.redShift = (float)0.005;
    pen.greenShift = (float)0.005;
    pen.blueShift = (float)0.0004;

    pen.draw();
    

  }
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
    Dictionary dict = new Dictionary();
    dict.takeRule("X", "X+YF");
    dict.takeRule("Y", "FX-Y");
    return instructions(dict, axiom, iterations);
  }

  public static String instructions(Dictionary dict, String axiom, int iterations){
    String instructions = axiom;
    String nextResult = "";
    for (int i = 0; i<iterations; i++){
      for (int j = 0; j<instructions.length(); j++){
        nextResult += (dict.lookUp(Character.toString(instructions.charAt(j))));
        //System.out.println(((float)j)/instructions.length());
      }
      instructions = nextResult;
      nextResult = "";

    }
    return instructions;
  }

}