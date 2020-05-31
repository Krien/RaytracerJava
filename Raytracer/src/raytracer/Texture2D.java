package raytracer;
import VectorD.*;


public abstract class Texture2D {
	public abstract Vector3d GetUV(Vector3d color, Vector2d uv);
}
