/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpad;

import java.awt.Graphics2D;

import java.awt.geom.Point2D;
import java.util.Vector;
/**
 *
 * @author Jacob
 */
public interface Shape {
    
    public String getShapeType();
    
    public void setSelectedTrue();
    public void setSelectedFalse();
    public void setX(Double mouseX);
    public void setY(Double mouseY);
    public Vector<Point2D> pointsOnShape();
    public void setPoint2DFirst(Point2D firstPoint);
    public void setPoint2DSecond(Point2D secondPoint);
    public void draw(Graphics2D g2d);
    /*
    public void setXFirstPoint(int x1);
    public void setYFirstPoint(int y1);
    public void setXSecondPoint(int x2);
    public void setYSecondPoint(int y2);
    */
}
