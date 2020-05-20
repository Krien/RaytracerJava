package raytracer;
import VectorD.Vector3d;

public class Light {
	private Vector3d position;
	private Vector3d intensity;
	public Vector3d getPosition() {return position;}
	public Vector3d getIntensity() {return intensity;}
	public Light(Vector3d position, Vector3d intensity)
	{
		this.position = new Vector3d(position);
		this.intensity = new Vector3d(intensity);
	}
}
