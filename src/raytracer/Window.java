package raytracer;

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
	
	public Window(int width, int height)
	{
		assert width > 0;
		assert height > 0;
		this.width = width;
		this.height = height;
		setupWindow();
	}
	
	private void setupWindow()
	{
		contentPane = new JPanel();
		frame= new JFrame("Raytracer");
		frame.setContentPane(contentPane);
		frame.setSize(width, height);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		g = contentPane.getGraphics();
	}
	
	public void drawScreen(Screen s)
	{
		g.drawString("Raytracer 1.0", 10, 10);
		if (g.drawImage(s.getCanvas(), s.getX(), s.getY(), s.getCanvas().getWidth(),s.getCanvas().getHeight(),null))
		{
		}
		g.dispose();
	}
}
