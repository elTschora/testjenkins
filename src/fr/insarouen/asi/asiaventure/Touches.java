package fr.insarouen.asi.asiaventure;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Touches implements KeyListener {
    public Touches() {
	addKeyListener();
    }

    public void keyPressed(KeyEvent evt){}

    public void keyReleased(KeyEvent evt){} 

    public void keyTyped(KeyEvent evt) {
	System.out.println(evt.getKeyChar());
	    }
}
