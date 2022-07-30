/**
 * Paddle class builds a paddle, updates it, draws it, and provides
 * a random color to change it to.
 */
import java.awt.event.KeyEvent;

public class Paddle {

	// Instance Variables
    private double x, y;
    private double halfWidth, halfHeight;
    private final double STEP = .02;
    private static Integer red = 52;
    private static Integer green = 94;
    private static Integer blue = 235;
    
    // Constructor
    public Paddle() {
        this.x = .5;
        this.y = .01;
        this.halfWidth = .10;
        this.halfHeight = .01;
    }

    // Update position
    public void update() {

        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT) && this.x - this.halfWidth >= 0){
            this.x -= STEP;    
        }
        
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT) && this.x + this.halfWidth <= 1) {
            this.x += STEP;    
        }
    }

    // Getters to calculate outside boundaries
    public double right() {
        return this.x + this.halfWidth;
    }
    public double left() {
  	  return this.x - this.halfWidth;
    }
    public double top() {
  	  return this.y + this.halfHeight;
    }
    public double bottom() {
  	  return this.y - this.halfHeight;
    }

    // Draw
    public void draw() {
  	  	StdDraw.setPenColor(red, green, blue);
        StdDraw.filledRectangle(this.x, this.y, this.halfWidth, this.halfHeight);
    }
    
    // Random color generator
    public static void randomColor() {
    	red = (int) (Math.random() * 256);
        green = (int) (Math.random() * 256);
        blue = (int) (Math.random() * 256);
    }

}