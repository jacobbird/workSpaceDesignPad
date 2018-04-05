/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpad;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Vector;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
/**
 *
 * @author Jacob
 */
public class OvalLocal implements Shape, Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String shapeType = "Oval";
    private Point2D firstPoint2D;
    private Point2D secondPoint2D;
    private Point2D centerPoint;
    private Double centerX;
    private Double centerY;
    private Double x;
    private Double y;
    private Double width;
    private Double height;
    private boolean selected;
    private Ellipse2D ellipse;
    private Vector<Point2D> points;
    private Point2D point;
    
    public OvalLocal(){
        firstPoint2D = new Point2D.Double(0,0);
        secondPoint2D = new Point2D.Double(0,0); 
    }
    
    public OvalLocal(double x1, double y1, double x2, double y2){
        firstPoint2D = new Point2D.Double(0,0);
        secondPoint2D = new Point2D.Double(0,0); 
        this.firstPoint2D.setLocation(x1,y1);
        this.secondPoint2D.setLocation(x2,y2);
    }
    
    public OvalLocal(Point2D p1, Point2D p2){
        firstPoint2D = new Point2D.Double(0,0);
        secondPoint2D = new Point2D.Double(0,0); 
        this.firstPoint2D.setLocation(p1);
        this.secondPoint2D.setLocation(p2);
    }
    
    public void setSelectedTrue() {
    		selected=true;
    }
    public void setSelectedFalse() {
    		selected=false;
    }
    
    public void setX(Double x) {
		this.x=x;
    }

    public void setY(Double y) {
		this.y=y;
    }
    
    public Vector<Point2D> pointsOnShape(){
    		if(ellipse!=null) {
    		for(double t=0; t<=(2*Math.PI); t=t+.0000001)
    			x = x+(width/2*Math.cos(t)); 
    			y = y+(height/2*Math.sin(2));
    		}
    		return points;
    }
    
    /*
    public void setXFirstPoint2D(double x1){
        this.firstPoint2D.setX(x1);
    }
    
    public void setYFirstPoint2D(double y1){
        this.firstPoint2D.setY(y1);
    }
    
    public void setXSecondPoint2D(double x2){
        this.secondPoint2D.setX(x2);
    }
    
    public void setYSecondPoint2D(double y2){
        this.secondPoint2D.setY(y2);
    }
    */
    public String getShapeType(){
        return this.shapeType;
    }
    
    public Point2D getFirstPoint2D(){
        return this.firstPoint2D;
    }
    
    public Point2D getSecondPoint2D(){
        return this.secondPoint2D;
    }
    
    public void setCenterPoint(){
       centerPoint.setLocation((firstPoint2D.getX()+secondPoint2D.getX())/2, (firstPoint2D.getY()+secondPoint2D.getY())/2);
    }
    
    
    
    public void draw(Graphics2D g){
        
        if(((this.secondPoint2D.getX()-this.firstPoint2D.getX())<0)&&((this.secondPoint2D.getY()-this.firstPoint2D.getY())<0)){
            x=(Double)this.secondPoint2D.getX();
            y=(Double)(this.secondPoint2D.getY());
            width=(Double)(this.firstPoint2D.getX()-this.secondPoint2D.getX());
            height=(Double)(this.firstPoint2D.getY()-this.secondPoint2D.getY());
        }
        else if (this.secondPoint2D.getX()-this.firstPoint2D.getX()<0){
            x=(Double)(this.secondPoint2D.getX());
            y=(Double)(this.firstPoint2D.getY());
            width=(Double)(this.firstPoint2D.getX()-this.secondPoint2D.getX());
            height=(Double)(this.secondPoint2D.getY()-this.firstPoint2D.getY());
        }
        else if(this.secondPoint2D.getY()-this.firstPoint2D.getY()<0){
            x=(Double)(this.firstPoint2D.getX());
            y=(Double)(this.secondPoint2D.getY());
            width=(Double)(this.secondPoint2D.getX()-this.firstPoint2D.getX());
            height=(Double)(this.firstPoint2D.getY()-this.secondPoint2D.getY());
        }
        else{
            x=(Double)(this.firstPoint2D.getX());
            y=(Double)(this.firstPoint2D.getY());
            width=(Double)(this.secondPoint2D.getX()-this.firstPoint2D.getX()); 
            height=(Double)(this.secondPoint2D.getY()-this.firstPoint2D.getY());
        }
            
        //java.awt.Rectangle rect = new java.awt.Rectangle((int)startPoint.getX(),(int)startPoint.getY(),(int)endPoint.getX()-(int)startPoint.getX(),(int)endPoint.getY()-(int)startPoint.getY());
        //java.awt.Rectangle rect = new java.awt.Rectangle(100,100,100,100);
        ellipse = new Ellipse2D.Double(x,y,width,height);
        
        g.draw(ellipse);       
        
    }

    public void setPoint2DFirst(Point2D startPoint) {
        this.firstPoint2D=startPoint;
    }

    public void setPoint2DSecond(Point2D endPoint) {
        this.secondPoint2D=endPoint;
    }
    
}

