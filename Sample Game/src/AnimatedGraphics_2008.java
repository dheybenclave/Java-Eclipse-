import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class AnimatedGraphics_2008 extends JComponent implements ActionListener {
    Color startColor = Color.GRAY;  // where we start
    Color endColor = Color.BLACK;         // where we end
    Color currentColor = startColor;
    int animationDuration = 2000;   // each animation will take 2 seconds
    long animStartTime;     // start time for each animation
    
    /**
     * Set up and start the timer
     */
    public AnimatedGraphics_2008() {
        Timer timer = new Timer(30, this);
        // initial delay while window gets set up
        timer.setInitialDelay(1000);
        animStartTime = 1000 + System.nanoTime() / 1000000;
        timer.start();
    }
    
    /**
     * Erase to the background color and fill an oval with the current
     * color (which is being animated elsewhere)
     */
    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(currentColor);
        g.fillOval(0, 0, getWidth(), getHeight());
    }
    
    /**
     * Callback from the Swing Timer. Calculate the fraction elapsed of
     * our desired animation duration and interpolate between our start and
     * end colors accordingly.
     */
    public void actionPerformed(ActionEvent ae) {
        // calculate elapsed fraction of animation
        long currentTime = System.nanoTime() / 1000000;
        long totalTime = currentTime - animStartTime;
        if (totalTime > animationDuration) {
            animStartTime = currentTime;
        }
        float fraction = (float)totalTime / animationDuration;
        fraction = Math.min(1.0f, fraction);
        // interpolate between start and end colors with current fraction
        int red = (int)(fraction * endColor.getRed() + 
                (1 - fraction) * startColor.getRed());
        int green = (int)(fraction * endColor.getGreen() + 
                (1 - fraction) * startColor.getGreen());
        int blue = (int)(fraction * endColor.getBlue() + 
                (1 - fraction) * startColor.getBlue());
        // set our new color appropriately
        currentColor = new Color(red, green, blue);
        // force a repaint to display our oval with its new color
        repaint();
    }
    
    private static void createAndShowGUI() {    
        JFrame f = new JFrame("Animated Graphics");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(200, 200);
        f.add(new AnimatedGraphics_2008());
        f.setVisible(true);
    }
    
    public static void main(String args[]) {
        Runnable doCreateAndShowGUI = new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        };
        SwingUtilities.invokeLater(doCreateAndShowGUI);
    }
}
 