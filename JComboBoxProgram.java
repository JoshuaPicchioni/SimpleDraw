//HOME ASSIGNMENT 1
//By Joshua Picchioni
//Student ID: 110035605
//Jan 27th, 2022


//Import the required libraries
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//Begin the JComboBoxProgram class, which extends JFrame
public class JComboBoxProgram extends JFrame  {

    //Defult constructor of JComboBoxProgram
    public JComboBoxProgram()  {

        //Header of JFrame
        super("Draw Shape Program with JComboBox");

        //Create new ShapeDrawPanel
        ShapeDrawPanel javaBoxSelect = new ShapeDrawPanel();

        //Create string array of shapes
        String[] shapeList = {"oval", "rectangle",};

        //Create the JComboBox for oval and rectangle (using shapeList array)
        JComboBox<String> ovelRectComboBox = new JComboBox<>(shapeList);


        //Item Listener to check active JComboBox value
        ovelRectComboBox.addItemListener(new ItemListener()  {

            //Function to check if item state changed
            public void itemStateChanged(ItemEvent event)  {


                //Check value of item selected
                if (event.getStateChange() == ItemEvent.SELECTED)  {

                    //Set item to current jComboBox value
                    String item = (String)event.getItem();


                    //Check if current item in jCoboBox is oval
                    if(item.equals("oval"))  {

                        //Redirect to Draw Ovals function
                        javaBoxSelect.ovalDraw();
                    }


                    //Check if current item in jComboBox is rectangle
                    if(item.equals("rectangle")){

                        //Redirect to Draw Rectangles function
                        javaBoxSelect.rectangleDraw();
                    }

                }
            }
        });


        //Create new JPanel to hold placement of jComboBox
        JPanel northPanel = new JPanel();

        //Add the jComboBox to the northPanel JPanel
        northPanel.add(ovelRectComboBox);

        //Centre the JComboBox
        getContentPane().add(javaBoxSelect, "Center");

        //Make the JComboBox conform to the top of the JFrame
        getContentPane().add(northPanel, "North");

        //Exit program upon closing it
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Set the size of the window
        setSize(400,400);

        //Make the window visable
        setVisible(true);

    }
  




    //Begin the class that will draw shapes to the screen
    private class ShapeDrawPanel extends JPanel  {

        //Define the variables to be used
        int x, y, xEnd, yEnd;
        int shapeType, OVAL = 0;
        int RECT = 1;
  

        public ShapeDrawPanel()  {

            //Set all inital cord values to 0
            x = y = xEnd = yEnd = 0;

            //Create new listiner for mosue movement
            mouseCheck mouseListen = new mouseCheck();

            //Add the mouse listiners to mouseListen
            addMouseListener(mouseListen);
            addMouseMotionListener(mouseListen);

            //Set the color of the draw JPannel to white
            setBackground(Color.white);

        }


        //Method to set the start point of the mouse
        public void startPoint(int x, int y) {
            this.x = x;
            this.y = y;
        }

        //Method to set the end point of the mouse
        public void endPoint(int x, int y) {
            this.xEnd = (x);
            this.yEnd = (y);
        }
  

        //Method to draw the shape to the screen
        public void paintComponent(Graphics grapic)  {
            super.paintComponent(grapic);
            drawShape(grapic, x, y, xEnd, yEnd, shapeType);
        }

        //Method to draw the shapes to the screen
        public void drawShape(Graphics g, int x, int y, int xEnd, int yEnd, int Shape) {

            //Get min values for x and y, to prevent shape not drawing with certain mouse movements
            int xNew = Math.min(x,xEnd);
            int yNew = Math.min(y,yEnd);

            //Set the width and heights, change to positive if the values are negative
            int width = Math.abs(x-xEnd);
            int height = Math.abs(y-yEnd);


            //Check if current shape in JComboBox is ovel
            if (Shape == 0)  {
                g.drawOval(xNew, yNew, width, height);
            }


            //Else (must be a rect)
            else    {
                g.drawRect(xNew, yNew, width, height);
            }
        }

  
        //Method to draw ovel
        public void ovalDraw()  {

            //Set shape type to OVAL
            shapeType = OVAL;

        }
  

        //Method to draw rectangle
        public void rectangleDraw()  {

            //Set shappe type to RECT
            shapeType = RECT;

        }


    //Begin the mouseCheck class, which monitors mouse activity
    class mouseCheck extends MouseAdapter {


        //Method to see if mouse has been pressed
        public void mousePressed(MouseEvent event) {

            //Set start point
            startPoint(event.getX(), event.getY());

        }

        //Method to see if mouse movement continues while pressed
        public void mouseDragged(MouseEvent event) {

            //Keep setting new end point
            endPoint(event.getX(), event.getY());

            //Paint the shape
            repaint();

        }

        //Method to check when mouse has been released
        public void mouseReleased(MouseEvent event) {

            //Set the final end point
            endPoint(event.getX(), event.getY());

            //Paint the shape
            repaint();

        }

    }

  
}
  

}