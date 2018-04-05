/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpad;

/**
 *
 * @author Jacob
 */
public class Point {
    private double x;
    private double y;
    
    public void setX(double xValue){
        this.x=xValue;
    }
    
    public void setY(double yValue){
        this.y=yValue;
    }
    
    public double getX(){
        return this.x;
    }
    
    public double getY(){
        return this.y;
    }
    
    public boolean equalSide(Point secondPoint){
        double x1= this.x;
        double y1 = this.y;
        double x2 = secondPoint.getX();
        double y2 = secondPoint.getY();
        
        if(x1-x2==y1-y2){
            return true;
        }
        else{
            return false;
        }
    }
}
