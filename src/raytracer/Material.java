package raytracer;
import VectorD.Vector3d;

public class Material {
	public Vector3d ambientColor;
	public Vector3d specularColor;
	public Vector3d diffuseColor;
	public Material(Vector3d ambientColor, Vector3d specularColor, Vector3d diffuseColor)
	{
		this.ambientColor = ambientColor;
		this.specularColor = specularColor;
		this.diffuseColor = diffuseColor;
	}
}
