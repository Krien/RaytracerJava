package raytracer;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.*;



public class App {
	private static Window _window;
	private static Raytracer _raytracer;
	public static void main(String[] args) {
		_window = new Window(Config.resolutionX, Config.resolutionY);
		_raytracer = new Raytracer(_window);
		while (true)
		{
			update();
		}
	}

	
	private static void setupInput()
	{

	}
	
	private static void update()
	{
		_raytracer.update();
		
	}
}
