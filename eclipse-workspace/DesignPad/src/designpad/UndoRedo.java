package designpad;

import java.util.Iterator;
import java.util.Vector;
import java.io.Serializable;

public class UndoRedo implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<Drawing> undoRedo;
	int currentPosition;
	int count;
	
	public UndoRedo() {
		undoRedo = new Vector<Drawing>();
		currentPosition = 0;
		count=0;
	}
	
	public void push(Drawing d) {
		Iterator<Drawing> it = undoRedo.iterator();
		while(it.hasNext()) {
			Drawing currDrw = (Drawing)it.next();
			currDrw.setCurrentPos(currDrw.getCurrentPos()+1);
		}
		undoRedo.add(0, d);
		undoRedo.get(0).setCurrentPos(0);
		count++;
		if (count==50) {
			undoRedo.remove(50);
		}
		
	}
	
	public boolean getState() {
		if (currentPosition<0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Drawing undo() {
		
		if(currentPosition+1<count) {
			return (Drawing)undoRedo.get(++currentPosition);
		}
		else {
			return new Drawing();
		}
	}
	
	public Drawing redo() {
		if(currentPosition==count-1) {
			return (Drawing)undoRedo.get(currentPosition--);
		}
		else if(currentPosition>0 && currentPosition<count-1) {
			return (Drawing)undoRedo.get(currentPosition--);
		}
		else if (currentPosition==0) {
			return (Drawing)undoRedo.get(currentPosition);
		}
		else {
			return new Drawing();
		}
		
	}
	
	public Drawing getCurrent() {
		return (Drawing)undoRedo.get(currentPosition);
	}
	
	public void noted() {
		undoRedo.removeIf((Drawing d)->currentPosition-- > -1);
		Iterator<Drawing> it = undoRedo.iterator();
		int cp = 0;
		while(it.hasNext()) {
			Drawing d = (Drawing)it.next();
			d.setCurrentPos(cp++);
		}
		
	}
	
}
