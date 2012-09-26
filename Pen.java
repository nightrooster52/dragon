import java.awt.image.*;
import java.awt.*;
import javax.swing.*;

public class Pen{

    private int x,y;
    private String instructions;
    private Cardinal direction = Cardinal.NORTH;

    public int stepSize = 1;
    public int red = 255;
    public int green = 255;
    public int blue = 255;
    public int redShift = 0;
    public int greenShift = 0;
    public int blueShift = 0;
    public Color color = new Color(red, green, blue);

    public Pen(int x, int y, String instructions){
	this.x = x;
	this.y = y;
	this.instructions = instructions;	
    }

    private void forward(){
	int[] dxy = Cardinal.NORTH.getValue();
	for (int i = 0; i < stepSize; i++){
	    //draw in current position
	    x += dxy[0];
	    y += dxy[1];
	}
    }

    private BufferedImage createImage(){
	BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	img.createGraphics();
	Graphics g = img.getGraphics();
	g.setColor(color);

	//draw according to instructions
	return null;
    }

    private void doInstructions(){
	for (int i = 0; i < instructions.length(); i++){
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