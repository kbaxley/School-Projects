/** 
 * The Pellet class builds each pellet and checks for 
 * collisions with Pac-Man
 */
public class Pellet {
	
	// Instance Variables
	private double x, y, radius;

	// Constructor
	public Pellet(double newX, double newY) {
		this.x = newX;
		this.y = newY;
		this.radius = .0075;
	}
	
	// Getters
	public double left() {
		return this.x - this.radius;
	}
	
	public double right() {
		return this.x + this.radius;
	}
	
	public double top() {
		return this.y + this.radius;
	}
	
	public double bottom() {
		return this.y - this.radius;
	}
	
	// Setters
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	// Checks for collision with Pacman
	public boolean pacmanCollision(PacMan pacman) {
		if(this.x > pacman.left() &&
				this.x < pacman.right() &&
				this.y < pacman.top() &&
				this.y > pacman.bot()) {
			return true;
		}
		return false;
	}
	
	// Draw
	public void draw() {
		StdDraw.setPenColor(250, 250, 250);
		StdDraw.filledCircle(this.x, this.y, this.radius);
	}
}