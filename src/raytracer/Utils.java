package raytracer;

import javax.vecmath.*;

public final class Utils {
	private Utils () { // private constructor
    }
	public static Vector3d vector3dfloat(Vector3d vec, float val)
	{
		return new Vector3d(vec.x * val, vec.y * val, vec.z * val);
	}
	public static Vector2d vector2dfloat(Vector2d vec, float val)
	{
		return new Vector2d(vec.x * val, vec.y * val);
	}
}
