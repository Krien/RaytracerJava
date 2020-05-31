package raytracer;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

import VectorD.*;

public class Raytracer {
	private Screen debugScreen;
	private Screen raytracedScreen;
	private List<Light> lights;
	private List<Shape> shapes;
	private int xRays, yRays;
	private Camera camera;
	private Window window;
	private int iteration = 0;
	
	public Raytracer(Window window, Camera camera)
	{
		this.window = window;
		this.xRays = window.getWidth()/2;
		this.yRays = window.getHeight();
		lights = new ArrayList<Light>();
        Light l2 = new Light(new Vector3d(-1f, -10.5f, 4f), new Vector3d(5));
		lights.add(l2);
		shapes = new ArrayList<Shape>();
		// Images
		BufferedImage goldImg = null;
		try {
			goldImg = ImageIO.read(getClass().getClassLoader().getResource("Gold.jpg"));
		} 
		catch (IOException e)
		{
		}
		BufferedImage earthImg = null;
		try {
			earthImg = ImageIO.read(getClass().getClassLoader().getResource("earth.jpg"));
		} 
		catch (IOException e)
		{
		}
		BufferedImage brickImg = null;
		try {
			brickImg = ImageIO.read(getClass().getClassLoader().getResource("brick.jpg"));
		} 
		catch (IOException e)
		{
		}
		// Spheres
		Material m = new Material(new Vector3d(0.1f), Vector3d.One(),new Vector3d(0.5f,0.5f,0.5f),0.95f,0);
        Sphere n1 = new Sphere(new Vector3d(-1f, -0.5f, 3), 0.25f,m,earthImg);
        Sphere n2 = new Sphere(new Vector3d(0, -0.9f, 4), 1);
        Sphere n3 = new Sphere(new Vector3d(1f, -0.9f, 3), 1);
        // Planes
        Material planeMat = new Material(new Vector3d(0.1f), Vector3d.One(),new Vector3d(0.5f,0.5f,0.5f),0,0);
        Plane pDown = new Plane(new Vector3d(0, 1, 0), -0.1f, planeMat, goldImg);
        Plane pUp = new Plane(new Vector3d(0, 1, 0), -5f, planeMat,brickImg);
        Plane pRight = new Plane(new Vector3d(1, 0, 0), -2f, planeMat,brickImg);
        Plane pLeft = new Plane(new Vector3d(-1, 0, 0), -5f, planeMat,brickImg);
        Plane pFront =new Plane(new Vector3d(0, 0, 1), -5f, planeMat,brickImg);
        Plane pBack = new Plane(new Vector3d(0, 0, -1), -5f, planeMat,brickImg);
        shapes.add(n3);shapes.add(n1);shapes.add(n2);
        shapes.add(pDown); shapes.add(pUp);  shapes.add(pRight);   shapes.add(pLeft);
        shapes.add(pFront);  shapes.add(pBack);

		this.camera = camera;
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
				Vector3d color = Trace(camera.getPosition(),direction,100,0);
				raytracedScreen.setPixel(x, y, scaleToInt(color));
			}
		}
		window.drawScreen(raytracedScreen, iteration);
		iteration++;
	}
	
	protected int scaleToInt (Vector3d finalColor) 
	{
		finalColor = finalColor.mult(255);
		finalColor.x  = finalColor.x > 255 ? 255 : finalColor.x ;
		finalColor.y  = finalColor.y > 255 ? 255 : finalColor.y ;
		finalColor.z  = finalColor.z > 255 ? 255 : finalColor.z;
		int color = ((int)finalColor.x)<< 16 | ((int)finalColor.y)<<8 | ((int)finalColor.z) ;
		return color;
	}
	
	protected HitData getHit(Vector3d position, Vector3d direction, float length)
	{
		 HitData hitDat = null;
		float hitDistance = length;
		for (Shape s : shapes) {
			CollisionInfo potential = s.checkForHit(position,direction);
			if (potential != null && potential.distance < hitDistance)
			{
				hitDistance = potential.distance;
				hitDat = new HitData(); hitDat.hit = potential; hitDat.obj = s;
			}
		}
		return hitDat;
	}
	
	protected Vector3d Trace(Vector3d origin, Vector3d direction, float length, int depth)
	{
		if (depth > Utils.recursionDepth)
			return Vector3d.Zero();
		HitData hit = getHit(origin, direction, length);
		if (hit == null)
			return Vector3d.Zero();
		if (hit.obj.getMaterial().mirror == 0 && hit.obj.getMaterial().refracIndex <= 1)
			return calculateLight(hit, direction).mult(hit.obj.mat.diffuseColor);
		else if (hit.obj.getMaterial().refracIndex <= 1)
		{
			Vector3d mirrDir = direction.sub(hit.hit.normal.mult(2 * Vector3d.dot(direction, hit.hit.normal)));
			Vector3d mirrPos = hit.hit.hitPost.add(mirrDir.mult(0.001f));
			Vector3d mirrorColor = Trace(mirrPos, mirrDir,100, depth + 1).mult(hit.obj.mat.diffuseColor).mult(hit.obj.mat.mirror);
			Vector3d ordinaryColor = calculateLight(hit, direction).mult(hit.obj.mat.diffuseColor);
			return mirrorColor.add(ordinaryColor).mult(1-hit.obj.getMaterial().mirror);
		}
		else
		{
			return Vector3d.Zero();
		}
	}
	
	protected Vector3d calculateLight(HitData hitData, Vector3d impactDirection)
	{
		Vector3d finalColor = new Vector3d(0,0,0);
		for (Light l : lights)
		{
			 Vector3d lightDist = l.getPosition().sub(hitData.hit.hitPost); 
             Vector3d lightV = lightDist.normalize();
             //If we cannot reach this light from the intersection point we go on to the next light.
             if (Vector3d.dot(hitData.hit.normal, lightDist) < 0)
                 continue;
             // blocked
             Vector3d shadowOrigin = new Vector3d(hitData.hit.hitPost).add(lightV.mult(0.0001f));
             float shadowLength = (float)(lightDist.length() - 2 * 0.0001f);
             if (getHit(shadowOrigin,lightV,shadowLength) != null)
            	 continue;
             // Diffuse
             Vector3d halfVector = lightV.sub(impactDirection);
             halfVector = halfVector.normalize();
             Vector3d diffuse = l.getIntensity().div(lightDist.length());
             // Blinn phong
             Vector3d blinnphong = hitData.obj.GetColor(hitData.hit.hitPost, hitData.hit.normal).mult(l.getIntensity()).mult(Math.max(0, Vector3d.dot(hitData.hit.normal, lightV))); 
             Vector3d specular = hitData.obj.GetColor(hitData.hit.hitPost, hitData.hit.normal).mult(hitData.obj.mat.specularColor.mult(l.getIntensity()));
             specular = specular.mult( (float)Math.pow(Math.max(0, Vector3d.dot(hitData.hit.normal, halfVector)), 130));
             blinnphong = blinnphong.add(specular);
             Vector3d lightColor = diffuse.mult(blinnphong);
             finalColor = finalColor.add(lightColor);
		}
		Vector3d ambient = hitData.obj.mat.ambientColor;
		finalColor = finalColor.add(ambient);
		return finalColor;
	}
	
	
	public void DrawDebug()
	{
		
	}
	
	
	public class HitData
	{
		CollisionInfo hit; Shape obj;
	} 
}
