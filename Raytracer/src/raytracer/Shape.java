package raytracer;
import java.awt.image.BufferedImage;

import VectorD.Vector3d;

public abstract class Shape {
	protected Vector3d position;
	protected Material mat;
	protected Texture2D texture2d;
	
	public Material getMaterial()
	{
		return mat;
	}
	
	public Shape(Vector3d position)
	{
		this.position = position;
		mat = new Material(new Vector3d(0.1f),  Vector3d.One(),new Vector3d(1,0,0),0,0);
		this.texture2d =  new CheckerBoardTexture();
	}
	
	public Shape(Vector3d position, Material mat)
	{
		this.position = position;
		this.mat = mat;
		this.texture2d =  new CheckerBoardTexture();
	}
	
	public Shape(Vector3d position, Material mat, BufferedImage texture2d)
	{
		this.position = position;
		this.mat = mat;
		this.texture2d = new Texture2DImage(texture2d);
	}
	
	public abstract CollisionInfo checkForHit(Vector3d origin, Vector3d direction);
	
	public abstract Vector3d GetColor(Vector3d position, Vector3d normal);
}
