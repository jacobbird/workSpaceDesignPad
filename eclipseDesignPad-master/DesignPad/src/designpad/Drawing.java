/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpad;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Jacob
 */
public class Drawing implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stack<Shape> sStack;
    private RectangleLocal rectLoc;
    private SquareLocal squaLoc;
    private OvalLocal ovalLoc;
    private CircleLocal circLoc;
    private CubicCurveLocalShape cubLoc;
    private int currentPosition;
    
    public Drawing(){
        sStack = new Stack<Shape>();
    }
    
    public Drawing(Drawing d) {
    	sStack = new Stack<Shape>();
    	Stack<Shape> s = d.sStack;
    	Iterator<Shape> it = s.iterator();
    	while(it.hasNext()) {
    		sStack.add(0, (Shape)it.next());
    	}
    	
    
    }
    
    public void setCurrentPos(int cp) {
    	currentPosition = cp;
    }
    
    public int getCurrentPos() {
    	return currentPosition;
    }
    
    public int count(){
        return sStack.size();
    }
    
    public void addShape(Shape s){
        sStack.push(s);
    }
    
    public void removeShape(){
        sStack.pop();
    }
    
    public Shape selectShape(int x, int y) {
    		Shape shape = null;
    		try {
    			Iterator it = sStack.iterator();
    			while(it.hasNext()) {
    				shape = (Shape)it.next();
    				Vector<Point2D> vec = shape.pointsOnShape();
    				boolean selectFlag=false;
    				for(int z = 0; z < vec.size(); z++) {
    					int a = (int) vec.get(z).getX();
    					int b = (int) vec.get(z).getY();
    					if(x==a && y==b) {
    						shape.setSelectedTrue();
    						selectFlag=true;
    						break;	
    					}
    				}
    				if(selectFlag==true) {
    					break;
    				}
    			}
    		}
    		catch(Exception e) {}
    		return shape;
    }
    
    public void draw(Graphics2D g2d){
        Iterator it = sStack.iterator();
        
        while(it.hasNext()){
            Shape sh = (Shape)it.next();
            if(sh.getShapeType().compareToIgnoreCase("rectangle")==0){
                rectLoc = new RectangleLocal();
                rectLoc = (RectangleLocal)sh;
                rectLoc.draw(g2d);
            }
            else if(sh.getShapeType().compareToIgnoreCase("square")==0){
            	squaLoc = new SquareLocal();
                squaLoc = (SquareLocal)sh;
                squaLoc.draw(g2d);     
            }
            else if(sh.getShapeType().compareToIgnoreCase("oval")==0){
            	ovalLoc = new OvalLocal();
                ovalLoc = (OvalLocal)sh;
                ovalLoc.draw(g2d);
            }
            else if(sh.getShapeType().compareToIgnoreCase("circle")==0){
            	circLoc = new CircleLocal();
                circLoc = (CircleLocal)sh;
                circLoc.draw(g2d);
            }
            else if(sh.getShapeType().compareToIgnoreCase("curve")==0){
            	cubLoc = new CubicCurveLocalShape();
                cubLoc = (CubicCurveLocalShape)sh;
                cubLoc.draw(g2d);
            }
            
        }
    }
    
}
