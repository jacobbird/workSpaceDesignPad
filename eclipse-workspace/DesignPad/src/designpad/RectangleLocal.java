/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpad;

import java.awt.Graphics;
import java.io.Serializable;
import java.awt.Graphics2D;
import javafx.scene.shape.Rectangle;
import java.awt.geom.Point2D;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import javax.swing.JPanel;

/**
 *
 * @author Jacob
 */
public class RectangleLocal implements Shape, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String shapeType = "Rectangle";
    private Point2D firstPoint2D;
    private Point2D secondPoint2D;
    
    public RectangleLocal(){
        firstPoint2D = new Point2D.Double(0,0);
        secondPoint2D = new Point2D.Double(0,0); 
    }
    
    public RectangleLocal(int x1, int y1, int x2, int y2){
        firstPoint2D = new Point2D.Double(0,0);
        secondPoint2D = new Point2D.Double(0,0); 
        this.firstPoint2D.setLocation(x1,y1);
        this.secondPoint2D.setLocation(x2,y2);
    }
    
    public RectangleLocal(Point2D p1, Point2D p2){
        firstPoint2D = new Point2D.Double(0,0);
        secondPoint2D = new Point2D.Double(0,0); 
        this.firstPoint2D.setLocation(p1.getX(),p1.getY());
        this.secondPoint2D.setLocation(p2.getX(),p2.getY());
    }
    
    /*public void setXFirstPoint2D(Double x1){
        this.firstPoint2D.x=x1;
    }
    
    public void setYFirstPoint2D(Double y1){
        this.firstPoint2D.y=y1;
    }
    
    public void setXSecondPoint2D(Double x2){
        this.secondPoint2D.x=x2;
    }
    
    public void setYSecondPoint2D(Double y2){
        this.secondPoint2D.y=y2;
    }
    */
    
    public void setPoint2DFirst(Point2D p2D){
        firstPoint2D = p2D;
    }
    
    public void setPoint2DSecond(Point2D p2D){
        secondPoint2D = p2D;
    }
    
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
            rect.y=(int)(this.secondPoint2D.getY());
            rect.width=(int)(this.firstPoint2D.getX()-this.secondPoint2D.getX());
            rect.height=(int)(this.firstPoint2D.getY()-this.secondPoint2D.getY());
        }
        else if (this.secondPoint2D.getX()-this.firstPoint2D.getX()<0){
            rect.x=(int)(this.secondPoint2D.getX());
            rect.y=(int)(this.firstPoint2D.getY());
            rect.width=(int)(this.firstPoint2D.getX()-this.secondPoint2D.getX());
            rect.height=(int)(this.secondPoint2D.getY()-this.firstPoint2D.getY());
        }
        else if(this.secondPoint2D.getY()-this.firstPoint2D.getY()<0){
            rect.x=(int)(this.firstPoint2D.getX());
            rect.y=(int)(this.secondPoint2D.getY());
            rect.width=(int)(this.secondPoint2D.getX()-this.firstPoint2D.getX());
            rect.height=(int)(this.firstPoint2D.getY()-this.secondPoint2D.getY());
        }
        else{
            rect.x=(int)(this.firstPoint2D.getX());
            rect.y=(int)(this.firstPoint2D.getY());
            rect.width=(int)(this.secondPoint2D.getX()-this.firstPoint2D.getX()); 
            rect.height=(int)(this.secondPoint2D.getY()-this.firstPoint2D.getY());
        }
            
        //java.awt.Rectangle rect = new java.awt.Rectangle((int)startPoint.getX(),(int)startPoint.getY(),(int)endPoint.getX()-(int)startPoint.getX(),(int)endPoint.getY()-(int)startPoint.getY());
        //java.awt.Rectangle rect = new java.awt.Rectangle(100,100,100,100);
        g.draw(rect);       
    }
    
}
