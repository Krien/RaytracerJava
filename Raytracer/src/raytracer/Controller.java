package raytracer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Controller implements KeyListener{
	//Assigning the variable keys to actual letters
	public Controller(){
	    bind(KeyEvent.VK_SPACE, Key.up);
	    bind(KeyEvent.VK_A, Key.left);
	    bind(KeyEvent.VK_SHIFT, Key.down);
	    bind(KeyEvent.VK_D, Key.right);
	    bind(KeyEvent.VK_E, Key.lookRight);
	    bind(KeyEvent.VK_Q, Key.lookLeft);
	    bind(KeyEvent.VK_X, Key.lookUp);
	    bind(KeyEvent.VK_Z, Key.lookDown);
	    bind(KeyEvent.VK_F, Key.lookFront);
	    bind(KeyEvent.VK_W, Key.forward);
	    bind(KeyEvent.VK_S, Key.backwards);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    other[e.getExtendedKeyCode()] = true;
	    if (isKeyBinded(e.getKeyCode()))
	    	keyBindings.get(e.getKeyCode()).isDown = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    other[e.getExtendedKeyCode()] = false;
	    if (isKeyBinded(e.getKeyCode()))
	    	keyBindings.get(e.getKeyCode()).isDown = false;
	}

	public boolean isKeyBinded(int extendedKey){
	    return keyBindings.containsKey(extendedKey);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}


	public void bind(Integer keyCode, Key key){
	    keyBindings.put(keyCode, key);
	}

	public void releaseAll(){
	    for(Key key : keyBindings.values()){
	        key.isDown = false;
	    }
	}

	public HashMap<Integer, Key> keyBindings = new HashMap<Integer, Key>();
	public static boolean other[] = new boolean[256];

	}
