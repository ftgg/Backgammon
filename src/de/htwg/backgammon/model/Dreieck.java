package de.htwg.backgammon.model;

import java.util.EmptyStackException;
import java.util.Set;
import java.util.TreeSet;
import java.util.Stack;
public class Dreieck {
	
	public static final int None = -1;
	private Stack<Stein> content;
	private int size;
	private int color;
	private boolean unsecure = true;
	
	Dreieck(){
		content = new Stack<Stein>();
		size = 0;
		color = None;
	}
	
   /**
	* @return a the stone for bar or null
	*/
	public Stein add(Stein s){
		// Unsicherers Feld und anderer Stein.
		Stein old;
		if(unsecure() && s.getColor() != color && !content.isEmpty()){
			old = content.pop();
			content.push(s);
			color = s.getColor();
			return old;
		}
		
		//Sicher und Fremdes Feld
		if(!unsecure() && s.getColor() != color)
			return s;
		
		//Sicher und Eigenes Feld | freies Feld
		content.push(s);
		color = s.getColor();
		size++;
		if(size >= 2)
			unsecure = false;
		return null;
	}
	
	/*
	 *@return false if two or more stones are on the field 
	 */
	public boolean unsecure(){
		return unsecure;
	}
	
	/*
	 *@returns a removed stone or null
	 */ 
	public Stein remove(){
		Stein r; 
		try {
			r = content.pop();
		}catch (EmptyStackException e){
			r = null;
		}
		if(r == null){
			return null;
		}else{
			size--;
			if(size < 2)
				unsecure = true;
			if(content.isEmpty())
				color = None;
			return r;
		}
	}
	/*
	 * @returns the Amount of stones in Field
	 */
	public int count(){
		return size;
	}
	/*
	 * @returns the color of Stones on the Field 
	 */
	public int getColor(){
		return color;
	}
	
	/*
	 * @returns true if Dreieck is Empty
	 */
	public boolean isEmpty(){
		return content.isEmpty();
	}
	
	/*
	 * clears the Dreieck
	 * returns a Set of Stones
	 */
	
	public void clear (){
		size = 0;
		unsecure = true;
		content.clear();
	}
	
	
	
	
}