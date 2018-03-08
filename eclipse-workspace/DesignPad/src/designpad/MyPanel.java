/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpad;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javafx.embed.swing.SwingNode;
import java.awt.geom.Point2D;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;



/**
 *
 * @author Jacob
 */
public class MyPanel extends JPanel{
    private enum currentShape{rectangle,square,oval,circle,cubiccurve};
    private currentShape cs;
    private Point2D startPoint;
    private Point2D endPoint;
    private JPanel jPanel;
    
    public void isRectangle(Point2D startPoint, Point2D endPoint){
       cs=cs.rectangle; 
       this.startPoint=startPoint;
       this.endPoint=endPoint;
    }
    
    public void createAndSetSwingContent(final SwingNode swingNode) {

        /*class RectDraw extends JPanel {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                Graphics2D g2d = (Graphics2D)g;
                
                if (cs==cs.rectangle){
                    RectangleLocal rect = new RectangleLocal();
                    rect.setPoint2DFirst(startPoint);
                    rect.setPoint2DSecond(endPoint);
                    rect.draw(g2d);
                //java.awt.Rectangle rect = new java.awt.Rectangle((int)startPoint.getX(),(int)startPoint.getY(),(int)endPoint.getX()-(int)startPoint.getX(),(int)endPoint.getY()-(int)startPoint.getY());
                //java.awt.Rectangle rect = new java.awt.Rectangle(100,100,100,100);
                }
                
                
            }
        }*/
  
        
        SwingUtilities.invokeLater(new Runnable() {
             @Override
             public void run() {
                 RectDraw jPanel = new RectDraw(startPoint,endPoint);
                 jPanel.setPreferredSize(new Dimension(1200,1200));
                 JScrollPane sPane = new JScrollPane(jPanel);
                 sPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
                 sPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                 
                 
                 
                 swingNode.setContent(sPane);
             }
         });
     }
}
