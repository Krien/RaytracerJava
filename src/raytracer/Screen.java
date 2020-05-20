package raytracer;

import java.awt.image.BufferedImage;

public class Screen {
	private int xSize,ySize,xPos,yPos;
	private BufferedImage canvas;
	
	public BufferedImage getCanvas()
	{
		return canvas;
	}
	
	public int getX() {return xPos;}
	public int getY() {return yPos;}
	public int getXSize() {return xSize;}
	public int getYSize() {return ySize;}
	
	public Screen(int xPos, int yPos,int xSize, int ySize)
	{
		this.xSize = xSize;
		this.ySize = ySize;
		this.xPos = xPos;
		this.yPos = yPos;
		canvas = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_RGB);
	}
	
	public void setPixel(int x, int y, int color)
	{
		canvas.setRGB(x, y, color);
	}
	
	public void setCircle(int x, int radius)
	{
	
	}
	
	public void drawLine(int xSource, int ySource, int xDest, int yDest)
	{
		
	}
	
}
