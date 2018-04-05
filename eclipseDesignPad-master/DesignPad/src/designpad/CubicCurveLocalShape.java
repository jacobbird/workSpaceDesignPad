/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpad;

import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;
import java.awt.geom.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.CubicCurve;
import java.io.Serializable;
/**
 *
 * @author Jacob
 */
public class CubicCurveLocalShape implements Shape, Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String shapeType = "Curve";
    private Stack<CCurveLocal> cCArray;
	private Point2D firstPoint2D;
	private Point2D secondPoint2D;
	private boolean selected;
	private Point2D point;
	private Vector<Point2D> points;
    
    CubicCurveLocalShape(){
        cCArray = new Stack<CCurveLocal>();
    }
    
    public void setX(Double mouseX) {
    	
    }
    
    public void setY(Double mouseY) {
    	
    }
    
    public Vector<Point2D> pointsOnShape(){
    		return points;
    }
    
    public int count(){
        return cCArray.size();
    }
    
    public void clear(){
        cCArray.clear();
    }
    
    public String getShapeType(){
        return this.shapeType;
    }
    
    public void setSelectedTrue() {
    		selected=true;
    }
    
    public void setSelectedFalse() {
    		selected=false;
    }
    
    public void addCurve(CCurveLocal cCL){
        cCArray.push(cCL);
    }
    
    public void removeCurve(){
        cCArray.pop();
    }
    
    public void draw(Graphics2D g){
       
        Iterator it = cCArray.iterator();
        
        while(it.hasNext()){
            CCurveLocal cCL = (CCurveLocal)it.next();
            cCL.draw(g);
        }
    }
    
    public void setPoint2DFirst(Point2D p2D){
        firstPoint2D = p2D;
    }
    
    public void setPoint2DSecond(Point2D p2D){
        secondPoint2D = p2D;
    }
}
