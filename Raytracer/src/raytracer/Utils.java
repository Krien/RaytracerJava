package raytracer;

public final class Utils {
	private Utils () { // private constructor
    }
	public static final int recursionDepth = 3;
	public static double Fract(float value)
	{
		double val = (double)value;
		if (val > 0)
			val = val - Math.floor(val);
		else
			val = val - Math.ceil(val);
		return val;
	}
}
