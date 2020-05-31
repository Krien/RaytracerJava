package raytracer;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.*;

import VectorD.Vector2d;
import VectorD.Vector3d;



public class App {
	private static Window _window;
	private static Raytracer _raytracer;
	private static Camera _camera;
	private static Controller _controller;
	
	public static void main(String[] args) {
		_controller = new Controller();
		_window = new Window(Config.resolutionX, Config.resolutionY, _controller);
		_camera = new Camera(new Vector3d(0,0,0), new Vector3d(0, 0, 1));
		_raytracer = new Raytracer(_window, _camera);
		while (true)
		{
			update();
			manageInput();
		}
	}

	
	private static void manageInput()
	{
		// Move
		if (Key.left.isDown)
		{
			_camera.Move(new Vector3d(-1f,0,0));
		}
		if (Key.right.isDown)
		{
			_camera.Move(new Vector3d(1f,0,0));
		}
		if (Key.up.isDown)
		{
			_camera.Move(new Vector3d(0,-1f,0));
		}
		if (Key.down.isDown)
		{
			_camera.Move(new Vector3d(0,1f,0));
		}
		if (Key.forward.isDown)
		{
			_camera.Move(new Vector3d(0,0,1));
		}
		if (Key.backwards.isDown)
		{
			_camera.Move(new Vector3d(0,0,-1));
		}
		// Look
		if (Key.lookLeft.isDown)
		{
			_camera.rotateInDirection(new Vector2d(0,2));
		}
		if (Key.lookRight.isDown)
		{
			_camera.rotateInDirection(new Vector2d(0,-2));
		}
		if (Key.lookUp.isDown)
		{
			_camera.rotateInDirection(new Vector2d(2,0));
		}
		if (Key.lookDown.isDown)
		{
			_camera.rotateInDirection(new Vector2d(-2,0));
		}
		if (Key.lookFront.isDown)
		{
			_camera.faceForward();;
		}
	}
	
	private static void update()
	{
		_raytracer.update();
		
	}
}
