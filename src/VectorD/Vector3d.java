package VectorD;

public class Vector3d {
	public float x, y,z;
	
	public static Vector3d Zero() {return new Vector3d(0,0,0);}
	public static Vector3d One() {return new Vector3d(1,1,1);}
	public static float dot(Vector3d v1, Vector3d v2) {return v1.x*v2.x + v1.y*v2.y + v1.z*v2.z;}
	public static Vector3d cross(Vector3d v1, Vector3d v2)
	{
		return new Vector3d(v1.y * v2.z - v1.z * v2.y,
				v1.z * v2.x - v1.x * v2.z,
				v1.x * v2.y - v1.y * v2.x);
	}
	public static Vector3d add(Vector3d vec1, Vector3d vec2)
	{
		return new Vector3d(vec1.x+vec2.x, vec1.y+vec2.y, vec1.z+vec2.z);
	}
	public static Vector3d sub(Vector3d vec1, Vector3d vec2)
	{
		return new Vector3d(vec1.x-vec2.x, vec1.y-vec2.y, vec1.z-vec2.z);
	}
	public static Vector3d mult(Vector3d vec1, Vector3d vec2)
	{
		return new Vector3d(vec1.x*vec2.x, vec1.y*vec2.y, vec1.z*vec2.z);
	}
	public static Vector3d mult(Vector3d vec, float val)
	{
		return new Vector3d(vec.x*val, vec.y*val, vec.z*val);
	}
	public static Vector3d div(Vector3d vec1, Vector3d vec2)
	{
		return new Vector3d(vec1.x/vec2.x, vec1.y/vec2.y, vec1.z/vec2.z);
	}
	
	public float length()
	{
		return (float)Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vector3d(float x, float y, float z)
	{
		this.x = x; this.y = y; this.z = z;
	}
	
	public Vector3d(float value)
	{
		this.x = value; this.y = value; this.z = value;
	}
	
	public Vector3d(Vector3d clone)
	{
		this.x = clone.x; this.y = clone.y; this.z = clone.z;
	}
	
	public Vector3d normalize()
	{
		return div(length());
	}
	
	public Vector3d returnNormalized()
	{
		Vector3d clone = new Vector3d(this);
		clone.normalize();
		return clone;
	}
	
	public void add(float val)
	{
		x+=val;
		y+=val;
		z+=val;
	}
	
	public Vector3d add(Vector3d vec2)
	{
		float xNew = x+vec2.x;
		float yNew = y+vec2.y;
		float zNew = z+vec2.z;
		return new Vector3d(xNew, yNew, zNew);
	}
	
	public Vector3d sub(float val)
	{
		float xNew = x-val;
		float yNew = y-val;
		float zNew = z-val;
		return new Vector3d(xNew, yNew, zNew);
	}
	
	public Vector3d sub(Vector3d vec2)
	{
		float xNew = x-vec2.x;
		float yNew = y-vec2.y;
		float zNew = z-vec2.z;
		return new Vector3d(xNew, yNew, zNew);
	}
	
	public Vector3d mult(float val)
	{
		float xNew = x*val;
		float yNew = y*val;
		float zNew = z*val;
		return new Vector3d(xNew, yNew, zNew);
	}
	
	public Vector3d mult(Vector3d vec2)
	{
		float xNew = x*vec2.x;
		float yNew = y*vec2.y;
		float zNew = z*vec2.z;
		return new Vector3d(xNew, yNew, zNew);
	}
	
	public Vector3d div(float val)
	{
		float xNew = x/val;
		float yNew = y/val;
		float zNew = z/val;
		return new Vector3d(xNew, yNew, zNew);
	}
	
	public Vector3d div(Vector3d vec2)
	{
		float xNew = x/vec2.x;
		float yNew = y/vec2.y;
		float zNew = z/vec2.z;
		return new Vector3d(xNew, yNew, zNew);
	}
	
	public Vector3d pow(int power)
	{
		float xNew = power(x, power);
		float yNew = power(y, power);
		float zNew = power(z, power);
		return new Vector3d(xNew, yNew, zNew);
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
	
	public Vector3d neg()
	{
		return new Vector3d(-x,-y,-z);
	}
}
