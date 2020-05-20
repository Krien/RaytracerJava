package raytracer;
import VectorD.*;

// Modification of an old camera made for the raytracer project for the course Graphics.

public class Camera {
	// Current state
	private Vector3d position;
	private Vector3d direction;
	private Vector2d angle;
	private CameraRectangle cameraRectangle = new CameraRectangle( new Vector3d(-1,-1,0),
			 new Vector3d(1,-1,0), new Vector3d(-1,1,0), new Vector3d(1,1,0));
	// Important vars that need to be set.
	private Vector3d up = new Vector3d(0,1,0);
	private Vector3d right = new Vector3d(1,0,0);
	private final float fov = 90;
	private float screenDistance;
	private final float screenDimension = 1;
	
	public Vector3d getTopLeft()
	{
		return Vector3d.add(position, cameraRectangle.topLeft);
	}
	public Vector3d getTopRight()
	{
		return Vector3d.add(position, cameraRectangle.topRight);
	}
	public Vector3d getBottomLeft()
	{
		return Vector3d.add(position, cameraRectangle.bottomLeft);
	}
	public Vector3d getBottomRight()
	{
		return Vector3d.add(position, cameraRectangle.topLeft);
	}
	
	public Vector3d getPosition()
	{
		return position;
	}
	
	public Vector3d getXDifference()
	{
		return Vector3d.sub(cameraRectangle.topRight, cameraRectangle.topLeft);
	}
	
	public Vector3d getYDifference()
	{
		return Vector3d.sub(cameraRectangle.bottomRight, cameraRectangle.topRight);
	}
	
	public Camera(Vector3d position,Vector3d direction)
	{
		this.position = position;
		this.direction = direction;
		setCamera();
	}
	
	protected void setCamera()
	{
		screenDistance = 1/(float)Math.tan( (fov*Math.PI/180) / 2);
		rotateTowards(new Vector2d(0,0));
		angle = new Vector2d(getAngleFromDirection(direction));
	}
	
	// Reset camera to face forward
	public void faceForward()
    {
		Vector2d targetAngle = new Vector2d(90,90);
		targetAngle = targetAngle.sub(targetAngle.neg());
		rotateTowards(targetAngle);
        // Correct just in case.
        if (direction != new Vector3d(0, 0, 1))
        {
        	angle = new Vector2d(90, 90);
            direction = new Vector3d(0, 0, 1);
            up = new Vector3d(0, 1, 0);
            right = new Vector3d(-1, 0, 0);
            setCameraBounds();
          }
    }
	
	public void rotateTowards(Vector2d rotationVector)
	{
		rotate(rotationVector);
        angle = new Vector2d(rotationVector);
	}
	
	public void rotateInDirection(Vector2d rotationVector)
	{
		rotate(rotationVector);
		angle = angle.add(rotationVector);
        if (angle.x > 360) {angle.x = 360 - angle.x;}
        else if (angle.x < 0) {angle.x = 360+angle.x;}
        if (angle.y > 360) {angle.y = 360 - angle.y;}
        else if (angle.y < 0) {angle.y = 360+angle.y;}
	}
	
	private void rotate(Vector2d rotationVector)
	{
		direction = rotateVector(direction, rotationVector);
		direction = direction.normalize();
        up = up.normalize();
        right = Vector3d.cross(direction, up);
        right = right.normalize();
        up = Vector3d.cross(right, direction);
        //Set screencorners
        setCameraBounds();
	}
	
	//Rotate camerapoint
    protected Vector3d rotateVector(Vector3d rotatedVector, Vector2d targetAngle)
    {
        //Degrees to radian
    	targetAngle = targetAngle.mult((float)(Math.PI / 180));
        //Rotate around x-axis
        rotatedVector.y = (float)(rotatedVector.y * Math.cos(targetAngle.x) - rotatedVector.z * Math.sin(targetAngle.x));
        rotatedVector.z = (float)(rotatedVector.z * Math.cos(targetAngle.x) + rotatedVector.y * Math.sin(targetAngle.x));
        //Rotate around y-axis
        rotatedVector.x = (float)(rotatedVector.x * Math.cos(targetAngle.y) - rotatedVector.z * Math.sin(targetAngle.y));
        rotatedVector.z = (float)(rotatedVector.z * Math.cos(targetAngle.y) + rotatedVector.x * Math.sin(targetAngle.y));
        return rotatedVector;
    }

	
	protected void setCameraBounds()
	{
        // TopLeft
        cameraRectangle.topLeft = Vector3d.sub(new Vector3d(right), Vector3d.mult(up, screenDimension));
        cameraRectangle.topLeft = cameraRectangle.topLeft.add(Vector3d.mult(direction, screenDistance)); 
        // TopRight
        cameraRectangle.topRight = Vector3d.sub(new Vector3d(right).neg(), Vector3d.mult(up, screenDimension));
        cameraRectangle.topRight = cameraRectangle.topRight.add(Vector3d.mult(direction, screenDistance)); 
        // bottomleft
        cameraRectangle.bottomLeft = Vector3d.add(new Vector3d(right), Vector3d.mult(up, screenDimension));
        cameraRectangle.bottomLeft = cameraRectangle.bottomLeft.add(Vector3d.mult(direction, screenDistance)); 
        // bottomright
        cameraRectangle.bottomRight = Vector3d.add(new Vector3d(right).neg(), Vector3d.mult(up, screenDimension));
        cameraRectangle.bottomRight = cameraRectangle.bottomRight.add(Vector3d.mult(direction, screenDistance));
	}
	
    public Vector2d getAngleFromDirection(Vector3d direction)
    {
        Vector2d tempAngle = new Vector2d(0, 0);
        // Ensure that direction is normalized.
        direction = direction.normalize();
        tempAngle.x = (float)Math.atan2(Math.sqrt(direction.z*direction.z + direction.y*direction.y), direction.x);
        tempAngle.y = (float)Math.atan2(Math.sqrt(direction.z*direction.z + direction.x*direction.x), direction.y);
        tempAngle.x = tempAngle.x *180 / (float)(Math.PI);
        tempAngle.y = tempAngle.y * 180 / (float)(Math.PI);
        return tempAngle;
    }

	
	
	public class CameraRectangle{
		public Vector3d topLeft;
		public Vector3d topRight;
		public Vector3d bottomLeft;
		public Vector3d bottomRight;
		public CameraRectangle(Vector3d topLeft, Vector3d topRight,Vector3d bottomLeft,Vector3d bottomRight)
		{
			this.topLeft = topLeft;
			this.topRight = topRight;
			this.bottomLeft = bottomLeft;
			this.bottomRight = bottomRight;
		}
	}
}
