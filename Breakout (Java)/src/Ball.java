/**
 * Ball class creates a ball, checks for collisions with brick,
 * wall, and paddle. Moves ball and draws ball.
 *
 */
public class Ball {

    // Instance Variables
    private double x, y;
    private double radius;
    private double dx, dy;
    private Integer red = 52;
    private Integer green = 94;
    private Integer blue = 235;

    // Constructor
    public Ball() {
        this.x = .5;
        this.y = .10;
        this.radius = .01;
        this.dx = .005;
        this.dy = .005;
    }
    

    // Update ball position, keeps ball in bounds
    public void update() {
    	if (this.x + this.radius > 1.0 || this.x - this.radius < 0.0) {
            this.dx = -this.dx;
        }
        
        if (this.y + this.radius > 1.0 || this.y - this.radius < 0.0) {
            this.dy = -this.dy;
        }
        
        this.x += this.dx;
        this.y += this.dy;
    }


    // Check ball collision with paddle
    // reverse Y if it does
    // randomly reverse X if it does
    public void checkPaddleCollision(Paddle paddle) {
     double left = this.x - this.radius;
   	 double right = this.x + this.radius;
   	 double top = this.y + this.radius;
   	 double bottom = this.y - this.radius;
   	 double random = Math.random();
   	 
   	 if (paddle.right() < left) {
   		 return;
   	 }
   	 if (paddle.left() > right) {
   		 return;
   	 }
     if (paddle.top() < bottom) {
       	return;
     }
     if (paddle.bottom() > top) {
       	return;
     }
     
     // Increase ball velocity each
     // time it hits the paddle
     this.dy *= 1.05; 
     if (random <= .5) {
       this.dx = -this.dx;
     }
     
     // Reset bonus each paddle hit
     Breakout.bonus = 0;
     
     // Change paddle color each hit
     Paddle.randomColor();
       
     Breakout.red = (int) (Math.random() * 256);
     Breakout.green = (int) (Math.random() * 256);
     Breakout.blue = (int) (Math.random() * 256);
     this.dy = -this.dy;
   }
    
    // Check brick collision
    // Change ball color each brick hit
    public boolean checkBrickCollision(Brick brick) {
    	double left = this.x - this.radius;
      	double right = this.x + this.radius;
      	double top = this.y + this.radius;
      	double bottom = this.y - this.radius;
      	double random = Math.random();
      	 
      	if (brick.right() < left) {
      		 return false;
      	}
      	
      	if (brick.left() > right) {
      		 return false;
      	}
        
      	if (brick.top() < bottom) {
          	return false;
        }
        
      	if (brick.bottom() > top) {
          	return false;
        }
        
        this.dy = -this.dy;
        
        if (random <= .5) {
        this.dx = -this.dx;
        }
        
        this.red = (int) (Math.random() * 256);
        this.green = (int) (Math.random() * 256);
        this.blue = (int) (Math.random() * 256);
        
        return true; 
    }
    
    // Check if ball hits bottom edge
    // Game is ended in main if true
    public boolean atEdge() {
  	  if (this.y - this.radius < 0) {
  		  return true;
  	  }
      return false;
    }
    
    // Draw
    public void draw() {
    	StdDraw.setPenColor(red, green, blue);
        StdDraw.filledCircle(this.x, this.y, this.radius);
    }
}