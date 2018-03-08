/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpad;

import java.awt.Graphics2D;
import java.io.Serializable;

import java.awt.geom.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 *
 * @author Jacob
 */
public class SquareLocal implements Shape, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String shapeType = "Square";
    private Point2D firstPoint2D;
    private Point2D secondPoint2D;
    
    public SquareLocal(){
        firstPoint2D = new Point2D.Double(0,0);
        secondPoint2D = new Point2D.Double(0,0);
    }
    
    public SquareLocal(double x1, double y1, double x2, double y2){
        firstPoint2D = new Point2D.Double(x1,y1);
        secondPoint2D = new Point2D.Double(x2,y2);
    }
    
    public SquareLocal(Point2D p1, Point2D p2){
        firstPoint2D = new Point2D.Double(0,0);
        secondPoint2D = new Point2D.Double(0,0);
        this.firstPoint2D.setLocation(p1);
        this.secondPoint2D.setLocation(p2);
    }
    
     public void setPoint2DFirst(Point2D p2D){
        firstPoint2D = p2D;
    }
    
    public void setPoint2DSecond(Point2D p2D){
        secondPoint2D = p2D;
    }
    
    public void squarePoints() {
    	secondPoint2D.setLocation(firstPoint2D.getX()+secondPoint2D.getY(),secondPoint2D.getY());
    }
    /*
    public void setXFirstPoint2D(double x1){
        this.firstPoint2D.setX(x1);
    }
    
    public void setYFirstPoint2D(double y1){
        this.firstPoint2D.setY(y1);
    }
    
    public void setXSecondPoint2D(double x2){
        this.secondPoint2D.;
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
    
    public void draw(Graphics2D g){
    	java.awt.Rectangle rect = new java.awt.Rectangle();
        
        if(((this.secondPoint2D.getX()-this.firstPoint2D.getX())<0)&&((this.secondPoint2D.getY()-this.firstPoint2D.getY())<0)){
           
            rect.x=(int)this.secondPoint2D.getX();
            rect.y=(int)(this.firstPoint2D.getY()-(this.firstPoint2D.getX()-this.secondPoint2D.getX()));
            rect.width=(int)(this.firstPoint2D.getX()-this.secondPoint2D.getX()); 
            rect.height=(int)(this.firstPoint2D.getX()-this.secondPoint2D.getX());
           
        }
        else if (this.secondPoint2D.getX()-this.firstPoint2D.getX()<0){
        	rect.x=(int)this.secondPoint2D.getX();
        	rect.y=(int)this.firstPoint2D.getY();
        	rect.width=(int)(this.firstPoint2D.getX()-this.secondPoint2D.getX()); 
        	rect.height=(int)(this.firstPoint2D.getX()-this.secondPoint2D.getX());
        }
        else if(this.secondPoint2D.getY()-this.firstPoint2D.getY()<0){
        	rect.x=(int)this.firstPoint2D.getX();
        	rect.y=(int)(this.firstPoint2D.getY()-(this.secondPoint2D.getX()-this.firstPoint2D.getX()));
        	rect.width=(int)(this.secondPoint2D.getX()-this.firstPoint2D.getX()); 
        	rect.height=(int)(this.secondPoint2D.getX()-this.firstPoint2D.getX());
        }
        else{
            rect.x=(int)(this.firstPoint2D.getX());
            rect.y=(int)(this.firstPoint2D.getY());
            rect.width=(int)(this.secondPoint2D.getX()-this.firstPoint2D.getX()); 
            rect.height=(int)(this.secondPoint2D.getX()-this.firstPoint2D.getX());
        }
            
        //java.awt.Rectangle rect = new java.awt.Rectangle((int)startPoint.getX(),(int)startPoint.getY(),(int)endPoint.getX()-(int)startPoint.getX(),(int)endPoint.getY()-(int)startPoint.getY());
        //java.awt.Rectangle rect = new java.awt.Rectangle(100,100,100,100);
        g.draw(rect);       
    }
}

