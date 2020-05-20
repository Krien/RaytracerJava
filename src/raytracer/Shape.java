package raytracer;
import VectorD.Vector3d;

public abstract class Shape {
	protected Vector3d position;
	protected Material mat;
	
	public Material getMaterial()
	{
		return mat;
	}
	
	public Shape(Vector3d position)
	{
		this.position = position;
		mat = new Material(new Vector3d(0.1f),  Vector3d.One(),new Vector3d(1,0,0));
	}
	
	public Shape(Vector3d position, Material mat)
	{
		this.position = position;
		this.mat = mat;
	}
	
	public abstract CollisionInfo checkForHit(Vector3d origin, Vector3d direction);
}
