package raytracer;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
	private int width, height;
	private JPanel contentPane;
	private JFrame frame;
	private Graphics g;
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public Window(int width, int height, Controller c)
	{
		assert width > 0;
		assert height > 0;
		this.width = width;
		this.height = height;
		setupWindow(c);
	}
	
	private void setupWindow(Controller c)
	{
		contentPane = new JPanel();
		frame= new JFrame("Raytracer");
		frame.setContentPane(contentPane);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		g = contentPane.getGraphics();
		frame.addKeyListener(c);
	}
	
	public void drawScreen(Screen s, int iteration)
	{
		if (g.drawImage(s.getCanvas(), s.getX(), s.getY(), s.getCanvas().getWidth(),s.getCanvas().getHeight(),null))
		{
		}
		g.setColor(Color.RED);
		g.drawString("Raytracer 1.0, Iteration: " + iteration, 10, 10);
		g.drawString("RecursionDepth: " + Utils.recursionDepth , 10, 25);
	}
}
