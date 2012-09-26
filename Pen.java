import java.awt.image.*;
import java.awt.*;
import javax.swing.*;

public class Pen{

    private int x,y;
    private String instructions;
    private char facing = 'N';

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

    private BufferedImage createImage(){
	BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
	img.createGraphics();
	Graphics g = img.getGraphics();
	g.setColor(color);

	//draw according to instructions
	return null;
    }
    
    
    
}