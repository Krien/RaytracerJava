package raytracer;

import java.awt.image.BufferedImage;

import VectorD.*;

public class Sphere extends Shape {
	protected float radius;
	protected float radiussq;
	
	public float getRadius()
	{
		return this.radius;
	}
	
	public Sphere(Vector3d position, float radius) {
		super(position);
		this.radius = radius;
		radiussq = radius * radius;
	}
	
	public Sphere(Vector3d position, float radius, Material mat)
	{
		super(position,mat);
		this.radius = radius;
		radiussq = radius * radius;
	}
	
	public Sphere(Vector3d position, float radius, Material mat, BufferedImage texture2d)
	{
		super(position,mat,texture2d);
		this.radius = radius;
		radiussq = radius * radius;
	}

	@Override
	public CollisionInfo checkForHit(Vector3d origin, Vector3d direction) {
		  Vector3d c = position.sub(origin);
          //In sphere
          if (Vector3d.dot(c,c) < radiussq)
          {
        	  Vector3d normal = origin.add(direction.mult(radius)).sub(position);
        	  normal = normal.normalize();
        	  Vector3d hitPos = origin.add(direction.mult(radius));
        	  return new CollisionInfo(hitPos, radius, normal.neg());
          }
          else
          {
              float t = Vector3d.dot(c, direction);
              c = c.sub(direction.mult(t));
              float pSquared = Vector3d.dot(c,c);
              if (!(pSquared > radiussq))
              {
                  t -= (float)Math.sqrt(radiussq - pSquared);
                  if (t > 0)
                  {
                	  Vector3d normal = (origin.add(direction.mult(t)).sub(position));
                	  normal = normal.normalize();
                	  Vector3d hitPos = origin.add(direction.mult(t));
                	  return new CollisionInfo(hitPos, t, normal);
                  }
              }
          }
		return null;
	}

	@Override
	public Vector3d GetColor(Vector3d position, Vector3d normal) {
		Vector2d uv = new Vector2d(0.5f + ( (float)Math.atan2(normal.z, normal.x) / 4* (float)Math.PI),
				0.5f + ((float)Math.asin(normal.y) /  (float)Math.PI));
		return texture2d.GetUV(mat.diffuseColor, uv);
	}

}
