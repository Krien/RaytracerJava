package raytracer;
import VectorD.Vector3d;

public class Material {
	public Vector3d ambientColor;
	public Vector3d specularColor;
	public Vector3d diffuseColor;
	public float mirror;
	public float refracIndex;
	public Material(Vector3d ambientColor, Vector3d specularColor, Vector3d diffuseColor, float mirror, float refracIndex)
	{
		this.ambientColor = ambientColor;
		this.specularColor = specularColor;
		this.diffuseColor = diffuseColor;
		this.mirror = mirror;
		this.refracIndex = refracIndex;
	}
}
