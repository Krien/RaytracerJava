package raytracer;

public class Key {
	  // Creating the keys as simply variables
	   public static Key up = new Key();
	   public static Key down = new Key();
	   public static Key left = new Key();
	   public static Key right = new Key();
	   public static Key lookLeft = new Key();
	   public static Key lookRight = new Key();
	   public static Key lookUp = new Key();
	   public static Key lookDown = new Key();
	   public static Key lookFront = new Key();
	   public static Key forward = new Key();
	   public static Key backwards = new Key();


	   /* toggles the keys current state*/
	   public void toggle(){
	       isDown =  !isDown;
	   }

	   public boolean isDown;
}
