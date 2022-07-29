/**
 * PacMan class builds a Pac-Man and checks for wall 
 * and ghost collisions
 */

import java.awt.event.KeyEvent;

public class PacMan {
	
	// Instance Variables
	private double x, y, radius;
	private double STEP = .02;
	private double oldPositX;
	private double oldPositY;
	private double[] polyX = new double[3];
	private double[] polyY = new double[3];
	
	// Constructor
	public PacMan() {
		this.x = .7;
		this.y = .15;
		this.radius = .02;
	}
	
	// Getters
	public double left() {
		return this.x - this.radius;
	}
	
	public double right() {
		return this.x + this.radius;
	}
	
	public double top() {
		return this.y +  this.radius;
	}
	
	public double bot() {
		return this.y - this.radius;
	}
	public double centerX() {
		return this.x;
	}
	public double centerY() {
		return this.y;
	}
	
	// Setters
	public void setMoveLeft() {
		polyX[0] = this.centerX() - this.STEP;
		polyX[1] = this.centerX() - this.STEP - this.radius;
		polyX[2] = this.centerX() - this.STEP - radius;
		
		polyY[0] = this.centerY();
		polyY[1] = this.centerY() + radius;
		polyY[2] = this.centerY() - radius;
		
	}
	public void setMoveRight() {
		polyX[0] = this.centerX() + this.STEP;
		polyX[1] = this.centerX() + radius + this.STEP;
		polyX[2] = this.centerX() + radius + this.STEP;
		
		polyY[0] = this.centerY();
		polyY[1] = this.centerY() + radius;
		polyY[2] = this.centerY() - radius;
		
	}
	public void setMoveUp() {
		polyX[0] = this.centerX();
		polyX[1] = this.centerX() - radius;
		polyX[2] = this.centerX() + radius;
		
		polyY[0] = this.centerY() + this.STEP;
		polyY[1] = this.centerY() + radius + this.STEP;
		polyY[2] = this.centerY() + radius + this.STEP;
		
	}
	public void setMoveDown() {
		polyX[0] = this.centerX();
		polyX[1] = this.centerX() + this.radius;
		polyX[2] = this.centerX() - this.radius;
		
		polyY[0] = this.centerY() - this.STEP;
		polyY[1] = this.centerY() - this.radius - this.STEP;
		polyY[2] = this.centerY() - this.radius - this.STEP;
		
	}
	
	// Draw Method
	public void draw() {
		StdDraw.setPenColor(250, 250, 50);
		StdDraw.filledCircle(this.x, this.y, this.radius);
		StdDraw.setPenColor(0, 0, 0);
		StdDraw.filledPolygon(polyX, polyY);
	}

	// Wall Collision Check
	public void wallCollision(Wall wall) {
		double left = this.x - this.radius;
		double right = this.x + this.radius;
		double top = this.y + this.radius;
		double bottom = this.y - this.radius;
  	
		if (wall.right() < left) {
  		 return;
		}
  	 
		if (wall.left() > right) {
  		 return;
		}
  	 
		if (wall.top() < bottom) {
 		 return;
		}
  	
		if (wall.bottom() > top) {
 		 return;
		}
		
		// If PacMan hits a wall, its
		// returned to previous position
		// using the reset methods
		this.undoX();
		this.undoY();
	}
	
	// Ghost Collision Check
	public boolean ghostCollision(Ghost ghost) {
		double left = this.x - this.radius;
		double right = this.x + this.radius;
		double top = this.y + this.radius;
		double bottom = this.y - this.radius;
  	
		if (ghost.right() < left) {
  		 return false;
		}
  	 
		if (ghost.left() > right) {
  		 return false;
		}
  	 
		if (ghost.top() < bottom) {
 		 return false;
		}
  	
		if (ghost.bottom() > top) {
 		 return false;
		}
		
		// If PacMan runs into a ghost
		// this method returns true
		// and ends the game in the main
		// class
		return true;
	}
	
	// Update Position
	public void update() {
		
		// Saving previous position
	  	oldPositX = this.x;
	  	oldPositY = this.y;
	    
	  	// Move Left
        if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
        	this.setMoveLeft();
            this.x -= STEP;    
        }
        
        // Move Right
        if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
        	this.setMoveRight();
            this.x += STEP;    
        }
        
        // Move Up
        if (StdDraw.isKeyPressed(KeyEvent.VK_UP)){
        	this.setMoveUp();
            this.y += STEP;    
        }
        
        // Move Down
        if (StdDraw.isKeyPressed(KeyEvent.VK_DOWN)) {
        	this.setMoveDown();
            this.y -= STEP;    
        }
    }
	
	// Reset methods return PacMan to previous position
	public void undoX() {
		this.x = oldPositX;
	}
	
	public void undoY() {
		this.y = oldPositY;
	}
}