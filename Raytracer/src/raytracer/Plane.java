package raytracer;
import java.awt.image.BufferedImage;

import VectorD.*;

public class Plane extends Shape {
	Vector3d normal;
	float distance;
    public Vector3d uDirection;
    public Vector3d vDirection;
	
	
	public Plane(Vector3d normal,float distance) {
		super(Vector3d.Zero());
        this.distance = distance;
        this.normal = new Vector3d(normal);
        init();
	}
	
	public Plane(Vector3d normal,float distance, Material mat)
	{
		super(Vector3d.Zero(),mat);
        this.distance = distance;
        this.normal = new Vector3d(normal);
        init();
	}
	
	public Plane(Vector3d normal,float distance, Material mat, BufferedImage texture2d)
	{
		super(Vector3d.Zero(),mat,texture2d);
        this.distance = distance;
        this.normal = new Vector3d(normal);
        init();
	}
	
	protected void init()
	{
        uDirection = new Vector3d(-1, 0, 0);
        vDirection = new Vector3d(0, 1, 0);
        if (normal.y == vDirection.y)
        {
            vDirection = Vector3d.cross(normal, uDirection);
            vDirection = vDirection.normalize();
            uDirection = Vector3d.cross(normal, vDirection);
            uDirection = uDirection.normalize();
        }
        else
        {
            uDirection = Vector3d.cross(normal, vDirection);
            uDirection = uDirection.normalize();
            vDirection = Vector3d.cross(normal, uDirection);
            vDirection = vDirection.normalize();
        }
	}

	@Override
	public CollisionInfo checkForHit(Vector3d origin, Vector3d direction) {
		// TODO Auto-generated method stub
        float t = -(Vector3d.dot(origin, normal) + distance) / (Vector3d.dot(direction, normal));
        if (t >= 0)
        {
        	Vector3d hitPos = origin.add(direction.mult(t));
            return new CollisionInfo(hitPos, t, normal.neg());
        }
        return null;
	}

	@Override
	public Vector3d GetColor(Vector3d position, Vector3d normal) {
		Vector2d uv = new Vector2d(Vector3d.dot(position, uDirection), Vector3d.dot(position, vDirection));
		return texture2d.GetUV(mat.diffuseColor, uv);
	}

}
