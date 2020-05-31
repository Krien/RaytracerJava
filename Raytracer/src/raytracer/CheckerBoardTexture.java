package raytracer;

import VectorD.Vector2d;
import VectorD.Vector3d;

public class CheckerBoardTexture extends Texture2D {

	@Override
	public Vector3d GetUV(Vector3d color, Vector2d uv) {
		int value = ((int)((2*uv.x+1000)) + (int)(2*uv.y+100)) & 1;
        return new Vector3d(value);
	}

}
