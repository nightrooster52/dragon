import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

public class Pen{

  private int x,y;
  private String instructions = null;
  private boolean[] b_instructions;
  private Cardinal direction = Cardinal.WEST;
  private Graphics g;

  public int stepSize = 1;

  public int red = 0;
  public int green = 0;
  public int blue = 0;

  private double redBuff = 256; // 256 is the same effect as 0
  private double greenBuff = 256;
  private double blueBuff = 256;

  public double redShift = 0;
  public double greenShift = 0;
  public double blueShift = 0;

  public Color color = new Color((int)red, (int)green, (int)blue);


  public Pen(int x, int y, String instructions){
    this.x = x;
    this.y = y;
    this.instructions = instructions;
  }
  public Pen(int x, int y, boolean[] b_instructions){
    this.x = x;
    this.y = y;
    this.b_instructions = b_instructions;

  }

  public void draw() throws Exception{
    setShifts();
    createImage("dragon");
  }

  private void setShifts(){
    double multiplier = (instructions == null) ? 255.0/b_instructions.length: 255.0/instructions.length();
    redShift *= multiplier;
    greenShift *= multiplier;
    blueShift *= multiplier;

  }


  private BufferedImage createImage(String fileName) throws IOException{

    BufferedImage img = new BufferedImage(1080, 1440, BufferedImage.TYPE_INT_RGB); //image size
    img.createGraphics();
    this.g = img.createGraphics();
    g.setColor(color);

    if (instructions == null){ doB_Instructions(); }else{doInstructions();}

    Pen.writeImage(img, fileName);

    return img;
  }

  private static void writeImage(BufferedImage image, String fileName) throws IOException{
    if (fileName == null) return;

    int offset = fileName.lastIndexOf(".");
    String type = offset == -1? "bmp" : fileName.substring(offset + 1);

    ImageIO.write(image, type, new File(fileName));
  }
  private void adjustColor(){
    //add the shift amount to the buff amount
    redBuff += redShift;
    greenBuff += greenShift;
    blueBuff += blueShift;
    
    //ratio - based modifiers
    //greenBuff = (blueBuff*blueBuff/redBuff*redBuff)/300;
    //greenBuff = (redBuff*redBuff/blueBuff*blueBuff)/1000;
    //bigRender setting V
    greenBuff = (redBuff*redBuff/blueBuff*blueBuff)/1000 - (blueBuff*blueBuff/redBuff*redBuff)/180;
    
    
    

    //add the buff to the color
    int redPaint = Math.abs(((int)(redBuff)+red));
    int greenPaint = Math.abs(((int)(greenBuff)+green));
    int bluePaint = Math.abs(((int)(blueBuff)+blue));
    //System.out.println(bluePaint);

    //modulo 256
    redPaint%=256;
    greenPaint%=256;
    bluePaint%=256;
      
    color = new Color(redPaint, greenPaint, bluePaint);
    g.setColor(color);


  }
  private void forward(){
    int[] dxy = direction.getValue();
    


    for (int i = 0; i < stepSize; i++){
      g.drawLine(x,y,x+1,y+1);
      x += dxy[0];
      y += dxy[1];
    }
  }
  private void doB_Instructions(){
    boolean b;
    int r;

    for (int i = 0; i < b_instructions.length; i++){
      System.out.println(((float)i)/b_instructions.length);
      adjustColor();
      if (b_instructions[i]){
	forward();
	redBuff *= 1.04;
	blueBuff *= 1/1.04;
	direction=direction.right();
      }else{
	forward();
	redBuff *= 1/1.04;
	blueBuff *= 1.04;
	direction=direction.left();
      }
    }

  }
  
  private void doInstructions(){
 
    for (int i = 0; i < instructions.length(); i++){
      adjustColor();
      System.out.println(((float)i)/instructions.length());
      char c = instructions.charAt(i);
      switch(c){
      case 'F':
	forward(); break;
      case '-':
	//* bigrender 
	redBuff *= 1/1.04;
	blueBuff *= 1.04;
	//*/
        direction = direction.left(); break;
	
      case '+':
	//*
	redBuff *= 1.04;
	blueBuff *= 1/1.04;
	//*/
        direction = direction.right(); break;
      case '1':
	forward();
	redBuff *= 1.04;
	blueBuff *= 1/1.04;
	direction=direction.right();
	break;
      case '0':
	forward();
	redBuff *= 1/1.04;
	blueBuff *= 1.04;
	direction=direction.left();
	break;
      default: break;

      }
    }
  }



}