package VectorD;

// Shameless copy
public class Vector2d {
	public float x, y;
	
	public static Vector2d Zero() {return new Vector2d(0,0);}
	public static Vector2d One() {return new Vector2d(1,1);}
	public static float dot(Vector3d v1, Vector3d v2) {return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;}
	public static Vector2d add(Vector2d vec1, Vector2d vec2)
	{
		return new Vector2d(vec1.x+vec2.x, vec1.y+vec2.y);
	}
	public static Vector2d sub(Vector2d vec1, Vector2d vec2)
	{
		return new Vector2d(vec1.x-vec2.x, vec1.y-vec2.y);
	}
	public static Vector2d mult(Vector2d vec1, Vector2d vec2)
	{
		return new Vector2d(vec1.x*vec2.x, vec1.y*vec2.y);
	}
	public static Vector2d mult(Vector2d vec, float val)
	{
		return new Vector2d(vec.x*val, vec.y*val);
	}
	public static Vector2d div(Vector2d vec1, Vector2d vec2)
	{
		return new Vector2d(vec1.x/vec2.x, vec1.y/vec2.y);
	}
	
	public float length()
	{
		return (float)Math.sqrt(x*x + y*y);  
	}
	
	public Vector2d(float x, float y)
	{
		this.x = x; this.y = y; 
	}
	
	public Vector2d(float value)
	{
		this.x = value; this.y = value; 
	}
	
	public Vector2d(Vector2d clone)
	{
		this.x = clone.x; this.y = clone.y; 
	}
	
	public void normalize()
	{
		div(length());
	}
	
	public Vector2d returnNormalized()
	{
		Vector2d clone = new Vector2d(this);
		clone.normalize();
		return clone;
	}
	
	public Vector2d add(Vector2d vec2)
	{
		float xNew = x+vec2.x;
		float yNew = y+vec2.y;
		return new Vector2d(xNew, yNew);
	}
	
	public Vector2d add(float val)
	{
		float xNew = x+val;
		float yNew = y+val;
		return new Vector2d(xNew, yNew);
	}
	
	public Vector2d sub(Vector2d vec2)
	{
		float xNew = x-vec2.x;
		float yNew = y-vec2.y;
		return new Vector2d(xNew, yNew);
	}
	
	public Vector2d sub(float val)
	{
		float xNew = x-val;
		float yNew = y-val;
		return new Vector2d(xNew, yNew);
	}
	
	
	public Vector2d mult(float val)
	{
		float xNew = x*val;
		float yNew = y*val;
		return new Vector2d(xNew, yNew);
	}
	
	public Vector2d mult(Vector2d vec2)
	{
		float xNew = x*vec2.x;
		float yNew = y*vec2.y;
		return new Vector2d(xNew, yNew);
	}
	
	public Vector2d div(float val)
	{
		float xNew = x/val;
		float yNew = y/val;
		return new Vector2d(xNew, yNew);
	}
	
	public Vector2d div(Vector2d vec2)
	{
		float xNew = x/vec2.x;
		float yNew = y/vec2.y;
		return new Vector2d(xNew, yNew);
	}
	
	public Vector2d pow(int power)
	{
		float xNew = power(x, power);
		float yNew = power(y, power);
		return new Vector2d(xNew, yNew);
	}
	
	protected float power(float power, int base)
	{
		if (base != 1)
		{
			if ( (base & 1) == 1)
			{
				return power * power(power,base-1);
			}
			else {
				float powered = power(power, base/2);
				return powered*powered;
			}
		}
		else {
			return power;
		}
	}
	
	public Vector2d neg()
	{
		return new Vector2d(-x,-y);
	}
}
