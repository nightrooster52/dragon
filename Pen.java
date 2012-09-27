import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;

public class Pen{

  private int x,y;
  private String instructions;
  private Cardinal direction = Cardinal.NORTH;
  private Graphics g;

  public int stepSize = 1;
  public float red = 255;
  public float green = 255;
  public float blue = 255;
  private float redBuff = 0;
  private float greenBuff = 0;
  private float blueBuff = 0;
  public float redShift = 0;
  public float greenShift = 0;
  public float blueShift = 0;
  public Color color = new Color((int)red, (int)green, (int)blue);


  public Pen(int x, int y, String instructions){
    this.x = x;
    this.y = y;
    this.instructions = instructions;
  }

  public void draw() throws Exception{
    createImage("dragon");
  }


  private BufferedImage createImage(String fileName) throws IOException{

    BufferedImage img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
    img.createGraphics();
    this.g = img.createGraphics();
    g.setColor(color);

    doInstructions();
    Pen.writeImage(img, fileName);

    return img;
  }

  private static void writeImage(BufferedImage image, String fileName) throws IOException{
    if (fileName == null) return;

    int offset = fileName.lastIndexOf(".");
    String type = offset == -1? "bmp" : fileName.substring(offset + 1);

    ImageIO.write(image, type, new File(fileName));
  }
  private void forward(){
    int[] dxy = direction.getValue();
    redBuff+=redShift;
    greenBuff += greenShift;
    blueBuff += blueShift;
    color = new Color((int)(red+redBuff)%255, (int)(green+greenBuff)%255, (int)(blue+blueBuff)%255);
    g.setColor(color);

    for (int i = 0; i < stepSize; i++){
      g.drawLine(x,y,x+1,y+1);
      x += dxy[0];
      y += dxy[1];
    }
  }
  private void doInstructions(){
    for (int i = 0; i < instructions.length(); i++){
      System.out.println(i/instructions.length());
      char c = instructions.charAt(i);
      switch(c){

      case 'F':  forward(); break;
      case '-': direction = direction.left(); break;
      case '+': direction = direction.right(); break;
      default: break;

      }
    }
  }



}