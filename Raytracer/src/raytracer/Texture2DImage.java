package raytracer;

import java.awt.Color;
import java.awt.image.BufferedImage;

import VectorD.Vector2d;
import VectorD.Vector3d;

public class Texture2DImage extends Texture2D {
	protected BufferedImage image;
	
	public Texture2DImage(BufferedImage image)
	{
		this.image = image;
	}
	
	@Override
	public Vector3d GetUV(Vector3d color, Vector2d uv) {
		Vector2d coords = new Vector2d((float)Utils.Fract(uv.x),(float)Utils.Fract(uv.y));
		if (coords.x < 0) coords.x = 1 + coords.x;
		if (coords.y < 0) coords.y = 1 + coords.y;
		int u = (int)(coords.x * (image.getWidth()-1));
		int v = (int)(coords.y * (image.getHeight()-1));
		int finalColor = image.getRGB(u, v);
		return color.mult(new Vector3d((finalColor & 0x00ff0000) >> 16,(finalColor & 0x0000ff00) >> 8, finalColor & 0x000000ff).div(255));
	}

}
