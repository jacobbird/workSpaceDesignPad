/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package designpad;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.WindowEvent;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Point2D;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.embed.swing.SwingNode;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import java.awt.*;
import java.awt.event.*;
import java.util.Optional;
import java.util.Vector;

import javax.swing.*;

/**
 *
 * @author Jacob
 */
public class DesignPad extends Application {

    private modes currentMode;
    private double mouseStartPointX;
    private double mouseStartPointY;
    private double mouseControlPoint1X;
    private double mouseControlPoint1Y;
    private double mouseControlPoint2X;
    private double mouseControlPoint2Y;
    private double mouseEndPointX;
    private double mouseEndPointY;
    private boolean control1;
    private Drawing drawing;
    private Point2D startPoint;
    private Point2D endPoint;
    private CubicCurveLocalShape cCurveLocalS;
    private Graphics g;
    private RectDraw r;
    private JScrollPane s;
    private UndoRedo undoRedo;
    private String currentFile;
    /*public void start(Stage primaryStage){
        Pane canvas = new Pane();
        RectangleLocal rl = new RectangleLocal();
        rl.draw(canvas);
        
        Scene scene = new Scene(canvas, 700, 600);
        
        primaryStage.setTitle("DesignPad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }*/
    
    public void setTextFieldVis(JTextField tf) {
    	tf.setVisible(true);
    }
    
    public void setTextFieldHidden(JTextField tf) {
    	tf.setVisible(false);
    }
    
    
    
    @Override
    public void start(Stage primaryStage) {
        MyPanel panel = new MyPanel();
        SwingNode swingNode = new SwingNode();
        startPoint = new Point2D(0,0);
        endPoint = new Point2D(0,0);
        undoRedo = new UndoRedo();
        cCurveLocalS=new CubicCurveLocalShape();
        drawing = new Drawing();
        control1 = true;
        r = new RectDraw();
        r.setPreferredSize(new Dimension(12000,12000));
        r.setUndoRedo(undoRedo);
        JTextField tf = new JTextField("Start a new File or Open an Existing One");
        //r.add(tf);
        
        
        BorderPane root = new BorderPane();

        MenuBar menuBar = new MenuBar();
        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        root.setTop(menuBar);

        // File menu - new, save, exit
        Menu fileMenu = new Menu("File");
        MenuItem newMenuItem = new MenuItem("New");
        newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Select a File to Save to:");
                File file = directoryChooser.showDialog(primaryStage);
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Enter the name of the file.");
                
                dialog.setHeaderText("Enter the desired name of the file.");
                
                 
                
                Optional<String> result = dialog.showAndWait();
                
                String entered = "none.";
               
                if (result.isPresent()) {
                    entered = result.get();
                }
                currentFile = file.getAbsolutePath()+"/"+entered+".dp";
                tf.setVisible(false);
                
            }

        });

        MenuItem saveMenuItem = new MenuItem("Save");
        saveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try {
            	ObjectOutputStream objectOutputStream = 
            		    new ObjectOutputStream(new FileOutputStream(currentFile));
            		objectOutputStream.writeObject(undoRedo);
            		objectOutputStream.close();
            		
            		}
            	catch(Exception e) {
            		System.out.print(e.getMessage());
            	}
            }

        });

        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	FileChooser fc = new FileChooser();
            	fc.setTitle("Select a File:");
            	
            	File file = fc.showOpenDialog(primaryStage);
            	try {
            		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()));
					UndoRedo ur = (UndoRedo)in.readObject();
					undoRedo = ur;
					Drawing d = undoRedo.getCurrent();
            		r.resetDrawing(d);
					in.close();
					tf.setVisible(false);
				} catch (FileNotFoundException e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("File not found");
					alert.setHeaderText(null);
					alert.setContentText("The file you have specified not longer exists at that location!");

					alert.showAndWait();
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            }

        });

        MenuItem exportMenuItem = new MenuItem("Export");
        exportMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });

        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());

        fileMenu.getItems().addAll(newMenuItem, saveMenuItem, openMenuItem,
                exportMenuItem, new SeparatorMenuItem(), exitMenuItem);

        Menu editMenu = new Menu("Edit");

        MenuItem undoMenuItem = new MenuItem("Undo");
        undoMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try{
            		Drawing d = undoRedo.undo();
            		r.resetDrawing(d);
            	}
            	catch(Exception e) {
            		Drawing d = new Drawing();
            		r.resetDrawing(d);
            	}
            }

        });

        editMenu.getItems().add(undoMenuItem);

        MenuItem redoMenuItem = new MenuItem("Redo");
        redoMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	try{
            		Drawing d = undoRedo.redo();
            		r.resetDrawing(d);
            	}
            	catch(Exception e) {
            		
            	}
            	
            }

        });

        editMenu.getItems().add(redoMenuItem);
        editMenu.getItems().add(new SeparatorMenuItem());

        MenuItem cutMenuItem = new MenuItem("Cut");
        cutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });

        editMenu.getItems().add(cutMenuItem);

        MenuItem copyMenuItem = new MenuItem("Copy");
        copyMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });

        editMenu.getItems().add(copyMenuItem);

        MenuItem pasteMenuItem = new MenuItem("Paste");
        pasteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });

        editMenu.getItems().add(pasteMenuItem);

        Menu viewMenu = new Menu("View");

        MenuItem zoomInMenuItem = new MenuItem("Zoom In");
        zoomInMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });

        MenuItem zoomOutMenuItem = new MenuItem("Zoom Out");
        zoomOutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });

        MenuItem fiftyMenuItem = new MenuItem("50%");
        fiftyMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });

        MenuItem seventyFiveMenuItem = new MenuItem("75%");
        seventyFiveMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });

        MenuItem oneHundredMenuItem = new MenuItem("100%");
        oneHundredMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });

        MenuItem oneFiftyMenuItem = new MenuItem("150%");
        oneFiftyMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });

        viewMenu.getItems().addAll(zoomInMenuItem, zoomOutMenuItem,
                new SeparatorMenuItem(), fiftyMenuItem, seventyFiveMenuItem,
                oneHundredMenuItem, oneFiftyMenuItem);

        Menu helpMenu = new Menu("Help");

        MenuItem toolTipMenuItem = new MenuItem("Tool Tip");
        toolTipMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });

        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }

        });

        helpMenu.getItems().addAll(toolTipMenuItem, new SeparatorMenuItem(),
                aboutMenuItem);

        menuBar.getMenus().addAll(fileMenu, editMenu, viewMenu, helpMenu);

        Button squareButton = new Button();
        Button rectangleButton = new Button();
        Button circleButton = new Button();
        Button ovalButton = new Button();
        Button curveButton = new Button();
        Button undoButton = new Button();

        squareButton.setText("Square");
        rectangleButton.setText("Rectangle");
        circleButton.setText("Circle");
        ovalButton.setText("Oval");
        curveButton.setText("Curve");
        undoButton.setText("Undo");

        squareButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                currentMode = modes.square;
                r.clearCCub();
            }
        });

        rectangleButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                currentMode = modes.rectangle;
                r.clearCCub();
            }
        });

        circleButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                currentMode = modes.circle;
                r.clearCCub();
            }
        });

        ovalButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                currentMode = modes.oval;
                r.clearCCub();
            }
        });

        curveButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                currentMode = modes.curve;
                r.setControl();
                r.clearCCub();
                //drawing.addShape(cCurveLocalS);
                //cCurveLocalS.clear();
                
            }
        });

        undoButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            	try{
            		Drawing d = undoRedo.undo();
            		r.resetDrawing(d);
            	}
            	catch(Exception e) {
            		Drawing d = new Drawing();
            		r.resetDrawing(d);
            	}
            }
        });

        HBox hbButtons = new HBox();
        hbButtons.setSpacing(10);
        hbButtons.setPadding(new Insets(10, 20, 10, 20));
        hbButtons.getChildren().addAll(squareButton, rectangleButton,
                circleButton, ovalButton, curveButton, undoButton);

        BorderPane bp2 = new BorderPane();
        bp2.setTop(hbButtons);

        root.setCenter(bp2);

        
        //bp2.setCenter(sp);
        //sp.setCursor(Cursor.CROSSHAIR);
        //Pane canvas = new StackPane();
        
        Pane canvas = new Pane();
        
        startPoint = new Point2D(0,0);
        endPoint = new Point2D(0,0);
        //panel.isRectangle(startPoint, endPoint);
        //panel.createAndSetSwingContent(swingNode);
        //JScrollPane jsp = (JScrollPane)swingNode.getContent();
        //int j = jsp.getComponentCount();
        //System.out.print(j);
        //jp = (RectDraw)jsp.getComponent(0);
        
        SwingNode swingNode2 = new SwingNode();
        
   
        s = new JScrollPane(r);
        JScrollBar hbar=new JScrollBar(JScrollBar.HORIZONTAL);
        JScrollBar vbar=new JScrollBar(JScrollBar.VERTICAL);
        s.setHorizontalScrollBar(hbar);
        s.setVerticalScrollBar(vbar);
        s.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        s.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
               
        createAndSetSwingContent(swingNode);
        

        bp2.setCenter(swingNode);
        final Duration oneFrameAmt = Duration.millis(100);
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        final KeyValue kv = new KeyValue(swingNode.scaleYProperty(), 1);
        
        KeyFrame oneFrame = new KeyFrame(oneFrameAmt, new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent actionEvent) {
            	r.setMode(currentMode);
            	
                
            }
            
         ;},kv);
        
        final Duration twoFrameAmt = Duration.millis(10);
        KeyFrame twoFrame = new KeyFrame(twoFrameAmt, new EventHandler<ActionEvent>() {
            //Pane canvas2 = (Pane)sp.getContent();
            @Override
            public void handle(ActionEvent actionEvent) {
                
             //   canvas2.getChildren().clear();
              
            }
         ;},kv);
        
        timeline.getKeyFrames().addAll(oneFrame);
        timeline.play();

        Scene scene = new Scene(root, 700, 600);
        
        primaryStage.setTitle("DesignPad");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    
    
    private void createAndSetSwingContent(final SwingNode swingNode) {

        
  
        
        SwingUtilities.invokeLater(new Runnable() {
             @Override
             public void run() {
                 
                
                 swingNode.setContent(s);
             }
         });
     }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    /*class RectDraw extends JPanel {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); 
                Graphics2D g2d = (Graphics2D)g;
                
                //if (cs==cs.rectangle){
                    RectangleLocal rect = new RectangleLocal();
                    rect.setPoint2DFirst(startPoint);
                    rect.setPoint2DSecond(endPoint);
                    rect.draw(g2d);
                //java.awt.Rectangle rect = new java.awt.Rectangle((int)startPoint.getX(),(int)startPoint.getY(),(int)endPoint.getX()-(int)startPoint.getX(),(int)endPoint.getY()-(int)startPoint.getY());
                //java.awt.Rectangle rect = new java.awt.Rectangle(100,100,100,100);
                //}
                
                
            }
        }*/
    
     private void BringToFront() {
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                           if(r != null) {
                                r.requestFocusInWindow();
                               
                                r.repaint();
                            }
                        }           
                    }); 
                }
    
    public class MyOwnFocusTraversalPolicy
                  extends FocusTraversalPolicy
    {
        

        public Component getInitialComponent(Window w){
            return r;
        }
        
        public Component getComponentAfter(Container focusCycleRoot,
                                           Component aComponent)
        {
            return r;
        }

        public Component getComponentBefore(Container focusCycleRoot,
                                            Component aComponent)
        {
            return r;
        }

        public Component getDefaultComponent(Container focusCycleRoot) {
            return r;
        }

        public Component getLastComponent(Container focusCycleRoot) {
            return r;
        }

        public Component getFirstComponent(Container focusCycleRoot) {
            return r;
        }
    }

	

}
