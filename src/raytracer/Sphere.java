package raytracer;

import VectorD.Vector3d;

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

	@Override
	public CollisionInfo checkForHit(Vector3d origin, Vector3d direction) {
		  Vector3d c = position.sub(origin);
          //In sphere
          if (Vector3d.dot(c,c) < radiussq)
          {
        	  Vector3d normal = origin.add(direction.mult(radius)).sub(position);
        	  normal = normal.normalize();
        	  Vector3d hitPos = origin.add(direction.mult(radius));
        	  return new CollisionInfo(hitPos, radius, normal.neg(),mat);
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
                	  return new CollisionInfo(hitPos, t, normal,mat);
                  }
              }
          }
		return null;
	}

}
