/**
 * Brick builds a brick and draws it
 */
public class Brick {

    // Instance Variables
    private double x, y;
    private double halfWidth, halfHeight;
    private int red, green, blue;

    // Constructor
    public Brick(int row, int col) {
    
        // Calculate the starting position based on the row and column
        this.halfHeight = .30 / Breakout.NUM_ROWS / 2;
        this.halfWidth = 1.0 / Breakout.NUM_COLS / 2;

        this.x = col * (2 * halfWidth) + halfWidth;
        this.y = .90 - row * (2 * halfHeight) - halfHeight;

        // Random color
        this.red = (int) (Math.random() * 256);
        this.green = (int) (Math.random() * 256);
        this.blue = (int) (Math.random() * 256);

    }


    // Getters
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
        StdDraw.setPenColor(this.red, this.green, this.blue);
        StdDraw.filledRectangle(this.x, this.y, this.halfWidth, this.halfHeight);
        StdDraw.setPenColor(0, 0, 0);
    }

}