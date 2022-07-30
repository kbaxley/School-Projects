import java.awt.Color;

/**
 * Breakout: A Brick-Blasting Arcade Game
 *
 * @author Kierson Baxley CMS-121
 */
 
public class Breakout {

	// Main Variables
    static final int NUM_ROWS = 6;
    static final int NUM_COLS = 8;
    static Integer totalBonus = 0;
    static Integer score = 0; // 
    static Integer bonus = 0;
    static Color randomColor;
    static int red = 0;
    static int green = 0;
    static int blue = 0;

    public static void main(String[] args) {

        
    	// Initialize ball/paddle
        Ball ball = new Ball();
        Paddle paddle = new Paddle();

        // Array of Bricks
        Brick[] bricks = new Brick[NUM_ROWS * NUM_COLS];
        int numBricks = NUM_ROWS * NUM_COLS;

        // Initialize the bricks
        // Code automatically converts index i into a row-column position
        for (int i = 0; i < bricks.length; i++) {
            int row = i / NUM_COLS;
            int col = i - (NUM_COLS * row);
            
            bricks[i] = new Brick(row, col);
        }

        // Main game loop
        boolean playing = true;
        while (playing == true) {
            
        	// Check winning condition
        	// Display score & bonus points won
        	if (numBricks == 0) {
        		randomColor = new Color(252, 3, 3);
        		StdDraw.clear(randomColor);
        		StdDraw.setPenColor(255, 255, 255);
                StdDraw.text(.5, .5, "You Win!");
                StdDraw.text(.5, .3, "Score: " + score.toString());
                StdDraw.text(.5, .2, "Bonus: " + totalBonus.toString());
                StdDraw.show();
                break;

        	}
        	
        	// Check losing condition
        	// Insult player
        	if (ball.atEdge()) {
        		randomColor = new Color(252, 3, 3);
        		StdDraw.clear(randomColor);
        		StdDraw.setPenColor(255, 255, 255);
                StdDraw.text(.5, .5, "Git Gud Scrub!");
                StdDraw.text(.5, .3, "Score: " + score.toString());
                StdDraw.text(.5, .2, "Bonus: " + totalBonus.toString());
                StdDraw.show();
                break;
        	}

            // Check for ball/paddle collision
            ball.checkPaddleCollision(paddle);
            
            // Check for all brick collisions
            for (int i = 0; i < bricks.length; i++) {
                if (bricks[i] == null) {
                    continue;
                }

                if (ball.checkBrickCollision(bricks[i])) {
                    bricks[i]= null;
                    numBricks--;
                    score ++;
                    score = score + bonus;
                    bonus ++;
                    totalBonus ++;
                }
            }

            // Update Positions
            ball.update();
            paddle.update();

            // Draw Elements
            randomColor = new Color (red, green, blue);
            StdDraw.clear(randomColor);
            StdDraw.setPenColor(255, 255, 255);
            StdDraw.text(.5, .95, "Score: " + score.toString());
            ball.draw();
            paddle.draw();
            
            for (int i = 0; i < bricks.length; i++) {
                if (bricks[i] == null) {
                    continue;
                } else {
                    bricks[i].draw();
                }
            }
            
            if (bonus > 1) {
            	StdDraw.setPenColor(255, 255, 255);
                StdDraw.text(.75, .95, "Bonus: " + bonus.toString());
            } 

            StdDraw.show(20);
         
        } // End of main game loop
    } // End of main method
} // End of class