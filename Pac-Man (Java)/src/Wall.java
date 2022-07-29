/**
 * Build Wall
 */
public class Wall {
	
	// Instance Variables
	private double x, y, halfWidth, halfHeight;
	
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

	public Wall(double newX, double newY, double newWidth, double newHeight) {
		this.x = newX;
		this.y = newY;
		this.halfWidth = newWidth;
		this.halfHeight = newHeight;
	}
	
	// Draw
	public void draw() {
		StdDraw.setPenColor(20, 100, 225);
		StdDraw.filledRectangle(this.x, this.y, this.halfWidth, this.halfHeight);
	}
}