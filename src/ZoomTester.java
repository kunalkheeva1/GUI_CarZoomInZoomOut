import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


/**
 * Car will implement Icon
 */
class Car implements Icon{

    private int carWidth;
    public Car(int aWidth){
        this.carWidth = aWidth;
    }



    @Override
    public int getIconWidth() {
        return carWidth;
    }
    public void setCarWidth(int newWidth){
        this.carWidth = newWidth;
    }

    @Override
    public int getIconHeight() {
        return carWidth/2;
    }

    /**
     * Accepting compoment, Graphics and x and y co-ordinates
     * @param c  a {@code Component} to get properties useful for painting
     * @param g  the graphics context
     * @param x  the X coordinate of the icon's top-left corner
     * @param y  the Y coordinate of the icon's top-left corner
     *
     */
    public void paintIcon(Component c, Graphics g, int x, int y){
        Graphics2D graphics2D = (Graphics2D) g;
        Rectangle2D.Double body = new Rectangle2D.Double(x, y+ carWidth/6, carWidth-1, carWidth/6);
        Ellipse2D.Double frontTire = new Ellipse2D.Double(x+carWidth/6, y+carWidth/3, carWidth/6, carWidth/6);
        Ellipse2D.Double rearTire = new Ellipse2D.Double(x+carWidth*2/3, y+carWidth/3, carWidth/6, carWidth/6);

        Point2D.Double r1 = new Point2D.Double(x+carWidth/6,y+carWidth/6);
        Point2D.Double r2 = new Point2D.Double(x+carWidth/3, y);
        Point2D.Double r3 = new Point2D.Double(x+carWidth/3,y);
        Point2D.Double r4 = new Point2D.Double(x+carWidth*5/6, y+carWidth/6);

        Line2D.Double frontGlass = new Line2D.Double(r1,r2);
        Line2D.Double roof = new Line2D.Double(r2, r3);
        Line2D.Double rearGlass = new Line2D.Double(r3,r4);


        //changing the colors of the tires
        graphics2D.setColor(Color.GREEN);
        graphics2D.fill(frontTire);
        graphics2D.setColor(Color.BLUE);
        graphics2D.fill(rearTire);
        graphics2D.setColor(Color.RED);
        graphics2D.fill(body);
        graphics2D.draw(frontGlass);
        graphics2D.draw(roof);
        graphics2D.draw(rearGlass);
    }
}

// main class that executes the program
public class ZoomTester {

    private static Car car;
    private static JLabel label;

    public static ActionListener createZoomButtonListener(final double factor){
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newWidth = (int)(factor*car.getIconWidth());
                if(newWidth<10){
                    newWidth=10;
                }
                car.setCarWidth(newWidth);
                label.repaint();
            }
        };

    }
    // main method for button creation and frame development and execution
    public static void main(String[] args){
        JFrame frame = new JFrame();
        JButton zoomIn = new JButton("ZoomIn");
        zoomIn.addActionListener(createZoomButtonListener(1.1));

        JButton zoomOut = new JButton("ZoomOut");
        zoomOut.addActionListener(createZoomButtonListener(0.9));

        car = new Car(500);
        label = new JLabel(car);

        frame.setLayout(new FlowLayout());

        // adding buttons and label in the frame, and making it packed and visible
        // also auto close operation
        frame.add(zoomIn);
        frame.add(zoomOut);
        frame.add(label);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }


}