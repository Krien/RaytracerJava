package raytracer;
import java.util.*;
import VectorD.*;

public class Raytracer {
	private Screen debugScreen;
	private Screen raytracedScreen;
	private List<Light> lights;
	private List<Shape> shapes;
	private int xRays, yRays;
	private Camera camera;
	private Window window;
	
	public Raytracer(Window window)
	{
		this.window = window;
		this.xRays = window.getWidth()/2;
		this.yRays = window.getHeight();
		lights = new ArrayList<Light>();
		Light l = new Light(new Vector3d(1,-1,-1),new Vector3d(1.5f));
        Light l2 = new Light(new Vector3d(-1f, -1.5f, 4f), Vector3d.One());
		lights.add(l);lights.add(l2);
		shapes = new ArrayList<Shape>();
		// Spheres
        Sphere n1 = new Sphere(new Vector3d(-1f, -0.5f, 3), 0.25f);
        Sphere n2 = new Sphere(new Vector3d(0, -0.9f, 4), 1);
        Sphere n3 = new Sphere(new Vector3d(1f, -0.9f, 3), 1);
        // Planes
        Plane pDown = new Plane(new Vector3d(0, 1, 0), -0.1f);
        Plane pUp = new Plane(new Vector3d(0, 1, 0), -5f);
        Plane pRight = new Plane(new Vector3d(1, 0, 0), -2f);
        Plane pLeft = new Plane(new Vector3d(-1, 0, 0), -5f);
        Plane pFront =new Plane(new Vector3d(0, 0, 1), -5f);
        Plane pBack = new Plane(new Vector3d(0, 0, -1), -5f);
        shapes.add(n3);shapes.add(n1);shapes.add(n2);
        shapes.add(pDown); shapes.add(pUp);  shapes.add(pRight);   shapes.add(pLeft);
        shapes.add(pFront);  shapes.add(pBack);

		camera = new Camera(new Vector3d(0,0,0), new Vector3d(0, 0, 1));
		raytracedScreen = new Screen(0,0,xRays, yRays);
		debugScreen = new Screen(xRays,0,xRays, yRays);
	}
	
	public void update()
	{
		Vector3d yAdditive = camera.getYDifference();
		yAdditive = yAdditive.mult(1.0f/(float)(yRays-1));
		Vector3d xAdditive = camera.getXDifference();
		xAdditive = xAdditive.mult(1.0f/(float)(xRays-1));
		for (int x=0;x<xRays;x++)
		{
			for (int y=0; y<yRays;y++)
			{
				Vector3d direction = new Vector3d(camera.getTopLeft());
				direction = direction.add(xAdditive.mult(x));
				direction = direction.add(yAdditive.mult(y));
				direction = direction.sub(camera.getPosition());
				direction = direction.normalize();
				int color = 0;
				CollisionInfo hit = getHit(camera.getPosition(), direction, 100);
				if (hit != null)
				{
					color = calculateLight(hit, direction);
				}
				raytracedScreen.setPixel(x, y, color);
			}
		}
		window.drawScreen(raytracedScreen);
	}
	
	protected CollisionInfo getHit(Vector3d position, Vector3d direction, float length)
	{
		CollisionInfo hit = null;
		float hitDistance = length;
		for (Shape s : shapes) {
			CollisionInfo potential = s.checkForHit(position,direction);
			if (potential != null && potential.distance < hitDistance)
			{
				hit = potential;
				hitDistance = hit.distance;
			}
		}
		return hit;
	}
	
	protected int calculateLight(CollisionInfo hit, Vector3d impactDirection)
	{
		Vector3d finalColor = new Vector3d(0,0,0);
		for (Light l : lights)
		{
			 Vector3d lightDist = l.getPosition().sub(hit.hitPost); 
             Vector3d lightV = lightDist.normalize();
             //If we cannot reach this light from the intersection point we go on to the next light.
             if (Vector3d.dot(hit.normal, lightDist) < 0)
                 continue;
             // blocked
             Vector3d shadowOrigin = new Vector3d(hit.hitPost).add(lightV.mult(0.0001f));
             float shadowLength = (float)(lightDist.length() - 2 * 0.0001f);
             if (getHit(shadowOrigin,lightV,shadowLength) != null)
            	 continue;
             // Diffuse
             Vector3d halfVector = lightV.sub(impactDirection);
             halfVector = halfVector.normalize();
             Vector3d diffuse = l.getIntensity().div(lightDist.length());
             // Blinn phong
             Vector3d blinnphong = hit.m.diffuseColor.mult(l.getIntensity()).mult(Math.max(0, Vector3d.dot(hit.normal, lightV))); 
             Vector3d specular = hit.m.diffuseColor.mult(hit.m.specularColor.mult(l.getIntensity()));
             specular = specular.mult( (float)Math.pow(Math.max(0, Vector3d.dot(hit.normal, halfVector)), 100));
             blinnphong = blinnphong.add(specular);
             Vector3d lightColor = diffuse.mult(blinnphong);
             finalColor = finalColor.add(lightColor);
		}
		Vector3d ambient = hit.m.ambientColor;
		finalColor = finalColor.add(ambient);
		finalColor = finalColor.mult(255);
		finalColor.x  = finalColor.x > 255 ? 255 : finalColor.x ;
		finalColor.y  = finalColor.y > 255 ? 255 : finalColor.y ;
		finalColor.z  = finalColor.z > 255 ? 255 : finalColor.z;
		int color = ((int)finalColor.x)<< 16 | ((int)finalColor.y)<<8 | ((int)finalColor.z) ;
		return color;
	}
	
	
	public void DrawDebug()
	{
		
	}
}
