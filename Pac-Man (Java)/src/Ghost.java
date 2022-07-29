/**
 * Ghost class initializes each ghost for the given XY coordinates.
 * Along with providing positional info and movement methods,
 *  Ghost also includes two loops for each ghost to follow.
 */
public class Ghost {
	
	// Instance Variables
	private double x, y, radius;
	private double STEP = .01;
	private int red = 200;
	private int green = 25;
	private int blue = 25;
	
	// Constructor
	public Ghost() {
		this.radius = .02;
	}
	
	// Setters
	public void setColor(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	// Draw Method
	public void draw() {
		StdDraw.setPenColor(this.red, this.green, this.blue);
		StdDraw.filledSquare(this.x, this.y, this.radius);
	}
	
	// Get positional info
	public double left() {
		return this.x - this.radius * .5;
	}
	
	public double right() {
		return this.x + this.radius * .5;
	}
	
	public double top() {
		return this.y + this.radius * .5;
	}
	
	public double bottom() {
		return this.y - this.radius * .5;
	}
	
	// Methods to move the ghosts
	public void moveLeft() {
		this.x -= this.STEP;
	}
	
	public void moveRight() {
		this.x += this.STEP;
	}
	
	public void moveUp() {
		this.y += this.STEP;
	}
	
	public void moveDown() {
		this.y -= this.STEP;
	}
	
	// Loop One 
	// Movement script for blinky
	public void loopOne() {
		
		if(this.x >= .04 && this.y >= .95 &&
				this.x <= .55 && this.y <= 1) {
			moveRight();
		}
		if(this.x >= .55 && this.y >= .85 &&
				this.x <= .6 && this.y <= 1) {
			moveDown();
		}
		if(this.x >= .15 && this.y >= .8 &&
				this.x <= .6 && this.y <= .85) {
			moveLeft();
		}
		if(this.x >= .1 && this.y >= .55 &&
				this.x <= .15 && this.y <= .85) {
			moveDown();
		}
		if(this.x >= .1 && this.y >= .5 &&
				this.x <= .25 && this.y <= .55) {
			moveRight();
		}
		if(this.x >= .25 && this.y >= .5 &&
				this.x <= .3 && this.y <= .75) {
			moveUp();
		}
		if(this.x >= .25 && this.y >= .75 &&
				this.x <= .35 && this.y <= .8) {
			moveRight();
		}
		if(this.x >= .35 && this.y >= .65 &&
				this.x <= .4 && this.y <= .8) {
			moveDown();
		}
		if(this.x >= .35 && this.y >= .6 &&
				this.x <= .75 && this.y <= .65) {
			moveRight();
		}
		if(this.x >= .75 && this.y >= .55 &&
				this.x <= .8 && this.y <= .65) {
			moveDown();
		}
		if(this.x >= .35 && this.y >= .5 &&
				this.x <= .8 && this.y <= .55) {
			moveLeft();
		}
		if(this.x >= .3 && this.y >= .25 &&
				this.x <= .35 && this.y <= .55) {
			moveDown();
		}
		if(this.x >= .25 && this.y >= .2 &&
				this.x <= .35 && this.y <= .25) {
			moveLeft();
		}
		if(this.x >= .2 && this.y >= .2 &&
				this.x <= .25 && this.y <= .45) {
			moveUp();
		}
		if(this.x >= .15 && this.y >= .45 &&
				this.x <= .25 && this.y <= .5) {
			moveLeft();
		}
		if(this.x >= .1 && this.y >= .15 &&
				this.x <= .15 && this.y <= .5) {
			moveDown();
		}
		if(this.x >= .1 && this.y >= .1 &&
				this.x <= .35 && this.y <= .15) {
			moveRight();
		}
		if(this.x >= .35 && this.y >= .05 &&
				this.x <= .4 && this.y <= .15) {
			moveDown();
		}
		if(this.x >= .05 && this.y >= .0 &&
				this.x <= .4 && this.y <= .05) {
			moveLeft();
		}
		if(this.x >= .0 && this.y >= .0 &&
				this.x <= .05 && this.y <= .95) {
			moveUp();
		}
	}
	
	// Loop Two
	// Movement script for inky
	public void loopTwo() {
		if(this.x >= .95 && this.y >= .0 &&
				this.x <= 1 && this.y <= .95) {
			moveUp();
		}
		if(this.x >= .85 && this.y >= .95 &&
				this.x <= .95 && this.y <= 1) {
			moveLeft();
		}
		if(this.x >= .8 && this.y >= .85 &&
				this.x <= .85 && this.y <= 1) {
			moveDown();
		}
		if(this.x >= .15 && this.y >= .8 &&
				this.x <= .85 && this.y <= .85) {
			moveLeft();
		}
		if(this.x >= .1 && this.y >= .55 &&
				this.x <= .15 && this.y <= .85) {
			moveDown();
		}
		if(this.x >= .1 && this.y >= .5 &&
				this.x <= .25 && this.y <= .55) {
			moveRight();
		}
		if(this.x >= .25 && this.y >= .5 &&
				this.x <= .3 && this.y <= .75) {
			moveUp();
		}
		if(this.x >= .25 && this.y >= .75 &&
				this.x <= .85 && this.y <= .8) {
			moveRight();
		}
		if(this.x >= .85 && this.y >= .45 &&
				this.x <= .9 && this.y <= .8) {
			moveDown();
		}
		if(this.x >= .65 && this.y >= .4 &&
				this.x <= .9 && this.y <= .45) {
			moveLeft();
		}
		if(this.x >= .6 && this.y >= .35 &&
				this.x <= .65 && this.y <= .45) {
			moveDown();
		}
		if(this.x >= .6 && this.y >= .3 &&
				this.x <= .85 && this.y <= .35) {
			moveRight();
		}
		if(this.x >= .85 && this.y >= .25 &&
				this.x <= .9 && this.y <= .35) {
			moveDown();
		}
		if(this.x >= .55 && this.y >= .2 &&
				this.x <= .9 && this.y <= .25) {
			moveLeft();
		}
		if(this.x >= .5 && this.y >= .25 &&
				this.x <= .55 && this.y <= .45) {
			moveUp();
		}
		if(this.x >= .45 && this.y >= .45 &&
				this.x <= .55 && this.y <= .5) {
			moveLeft();
		}
		if(this.x >= .4 && this.y >= .05 &&
				this.x <= .45 && this.y <= .5) {
			moveDown();
		}
		if(this.x >= .4 && this.y >= .0 &&
				this.x <= 1 && this.y <= .05) {
			moveRight();
		}
	}
}