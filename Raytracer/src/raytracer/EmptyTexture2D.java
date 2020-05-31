package raytracer;

import VectorD.Vector2d;
import VectorD.Vector3d;

public class EmptyTexture2D extends Texture2D {

	@Override
	public Vector3d GetUV(Vector3d color, Vector2d uv) {
		// TODO Auto-generated method stub
		return color;
	}

}
