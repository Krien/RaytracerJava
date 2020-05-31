package raytracer;
import VectorD.Vector3d;

public class CollisionInfo {
	public Vector3d hitPost;
	public float distance;
	public Vector3d normal;
	
	public CollisionInfo(Vector3d hitPost, float distance, Vector3d normal)
	{
		this.hitPost = hitPost;
		this.distance = distance;
		this.normal = normal;
	}
}
