/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpad;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.event.MouseEvent;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



/**
 *
 * @author Jacob
 */
public class RectDraw extends JPanel implements MouseListener, MouseMotionListener  {
            
    private Point2D startPoint;
    private Point2D endPoint;
    private JPanel jPanel;
    private modes currentMode;
    private double mouseStartPointX;
    private double mouseStartPointY;
    private double mouseControlPoint1X;
    private double mouseControlPoint1Y;
    private double mouseControlPoint2X;
    private double mouseControlPoint2Y;
    private double mouseControlPoint3X;
    private double mouseControlPoint3Y;
    private double tempMCP1X;
    private double tempMCP1Y;
    private double tempMCP2X;
    private double tempMCP2Y;
    private double tempMCP3X;
    private double tempMCP3Y;
    private double mouseEndPointX;
    private double mouseEndPointY;
    private boolean control1;
    private boolean control2;
    private Drawing drawing;
    private Shape sha;
    private UndoRedo undoRedo;
    private double currentZoom;
    private double zoomFactor;
    private double scaleFactor;
    private double prevScaleFactor;
    private double zoomRatio;
    
   
    public RectDraw(Point2D startPoint, Point2D endPoint){
       super();
       addMouseMotionListener(this);
       addMouseListener(this);
       control2=true;
       this.startPoint=null;
       this.endPoint=null;
       this.startPoint=startPoint;
       this.endPoint=endPoint;
       currentMode=modes.rectangle;
       drawing = new Drawing();
       undoRedo = new UndoRedo();
       zoomFactor = 1;
       currentZoom = 1;
       scaleFactor = 1;
       zoomRatio=1;
       
    }
    
    public RectDraw(){
    	super();
    	addMouseMotionListener(this);
    	addMouseListener(this);
    	this.startPoint=null;
        this.endPoint=null;
        currentMode=modes.rectangle;
        drawing = new Drawing();
        undoRedo= new UndoRedo();
        zoomFactor = 1;
        currentZoom = 1;
        scaleFactor = 1;
        zoomRatio = 1;
        
    }
    
    
    
    public void clearCCub() {
    	startPoint=null;
    	endPoint=null;
    	mouseStartPointX=Double.NaN;
    	mouseStartPointY=Double.NaN;
    	mouseEndPointX=Double.NaN;
    	mouseEndPointY=Double.NaN;
    	mouseControlPoint1X=Double.NaN;
    	mouseControlPoint1Y=Double.NaN;
    	mouseControlPoint2X=Double.NaN;
    	mouseControlPoint2Y=Double.NaN;
    }
    
    public void setUndoRedo(UndoRedo ur) {
    	undoRedo = ur;
    }
    
    
    public void setMode(modes curMod) {
    	currentMode = curMod;
    }
    
    public void setControl() {
    	control1=true;
    	control2=true;
    	
    }
    
    
    public void addPoints(Point2D startPoint, Point2D endPoint){
        this.startPoint=null;
       this.endPoint=null;
    }
    
    private void calcCP2() {
    	if(mouseControlPoint3X != Double.NaN && mouseControlPoint3Y != Double.NaN && mouseEndPointX != Double.NaN && mouseEndPointY != Double.NaN ) {
        	mouseControlPoint2X = 2*mouseEndPointX - mouseControlPoint3X;// Step 5 and 11; calculate cp2.
        	mouseControlPoint2Y = 2*mouseEndPointY - mouseControlPoint3Y;
        }
    }
    
    public void resetDrawing(Drawing d) {
    	drawing=d;
    	this.repaint();
    	clearCCub();
    	
    }
    public void setZoomFactor(double zoomFactor) {
    	scaleFactor = zoomFactor;
    }
    
    public void zoomIn(double zoom) {
    	scaleFactor=zoom;
    }
    
    public void zoomOut(double zoom) {
    	scaleFactor=zoom;
    }
    protected void paintComponent(Graphics g) {
    	 
    	Graphics2D g2d = (Graphics2D)g;
    	g2d.scale(scaleFactor, scaleFactor);
    	setSize((int)(this.getWidth()*scaleFactor),(int)(this.getHeight()*scaleFactor));
    	validate();
    	
        
    	
    	super.paintComponent(g);
    	if(undoRedo.getState()==true) {
    		undoRedo.noted();
    	}
    	
        
       
        if (drawing.count() > 0) {
        	drawing.draw(g2d);
        }
        
        if(currentMode == modes.rectangle && startPoint != null && endPoint != null) {
        	sha = new RectangleLocal();
            sha.setPoint2DFirst(startPoint);
            sha.setPoint2DSecond(endPoint);
            sha.draw(g2d);
        }
        else if(currentMode == modes.square && startPoint != null && endPoint != null) {
        	sha = new SquareLocal();
        	sha.setPoint2DFirst(startPoint);
        	sha.setPoint2DSecond(endPoint);
        	sha.draw(g2d);
        
        }
        else if(currentMode == modes.circle && startPoint != null && endPoint != null) {
        	sha = new CircleLocal();
        	sha.setPoint2DFirst(startPoint);
        	sha.setPoint2DSecond(endPoint);
        	sha.draw(g2d);
        }
        else if(currentMode == modes.oval && startPoint != null && endPoint != null) {
        	sha = new OvalLocal();
        	sha.setPoint2DFirst(startPoint);
        	sha.setPoint2DSecond(endPoint);
        	sha.draw(g2d);
        }
        else if(currentMode == modes.curve && startPoint != null && endPoint != null) {
        	CCurveLocal cub;
        	if(control1==false) {
        		cub = new CCurveLocal(startPoint.getX(),startPoint.getY(),endPoint.getX(),endPoint.getY(),mouseControlPoint1X,mouseControlPoint1Y, mouseControlPoint2X, mouseControlPoint2Y);
        		cub.draw(g2d);// Step 6 and 12: draw cubic curve.
        		calcCP2();
        	}
        	
        	
        }
        if(currentMode == modes.curve && control1==true) {
        	Line2D line = new Line2D.Double(mouseControlPoint1X,mouseControlPoint1Y,-mouseControlPoint1X+2*mouseStartPointX, -mouseControlPoint1Y+2*mouseStartPointY);
        	g2d.draw(line);
        }
        else if(currentMode == modes.curve && control1==false) {
        	Line2D line = new Line2D.Double(mouseControlPoint2X,mouseControlPoint2Y,-mouseControlPoint2X+2*mouseEndPointX, -mouseControlPoint2Y+2*mouseEndPointY);
        	g2d.draw(line);
        }
        
        
        
    	
        repaint();
                
                
    }
    
    public void scaleMultiplier(double ratio) {
    		zoomRatio=ratio;
    }
    
    @Override
	public void mouseDragged(java.awt.event.MouseEvent me) {
		if (currentMode == currentMode.curve) {
            if (control1 == true ) {
                mouseControlPoint1X = me.getX()*zoomRatio; //Step 2: Get first mouse Control Point.
                mouseControlPoint1Y = me.getY()*zoomRatio;
            }
            else if (control1 == false) {
            	mouseControlPoint3X = me.getX()*zoomRatio; //Step 4 and 10: Get control points 3.
                mouseControlPoint3Y = me.getY()*zoomRatio; 
            }
        } else {
            mouseEndPointX = me.getX()/scaleFactor;
            mouseEndPointY = me.getY()/scaleFactor;
        }
        
        
        startPoint = new Point2D.Double(mouseStartPointX, mouseStartPointY);
        endPoint = new Point2D.Double(mouseEndPointX, mouseEndPointY);
        
		
	}
    
    @Override
	public void mousePressed(MouseEvent me) {
		if (currentMode == currentMode.curve) {
            if (control1 == true) {
                mouseStartPointX = me.getX()*zoomRatio; // Step 1: First press recorded.
                mouseStartPointY = me.getY()*zoomRatio;

            } else {
                mouseEndPointX = me.getX()*zoomRatio; // Step 3 and 9: Second point recorded.
                mouseEndPointY = me.getY()*zoomRatio;
                
            }
        } else {
        	
        	if (control2==true) {
        		control2 = false;
        	}
        	else {
        		control2 = true;
        	}
            mouseStartPointX = me.getX()/scaleFactor;
            mouseStartPointY = me.getY()/scaleFactor;
        }
		
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
		
			drawing.addShape(sha);
			Drawing d2 = new Drawing(drawing);
			undoRedo.push(d2);
		
		
	    control1=false;
	}
}

