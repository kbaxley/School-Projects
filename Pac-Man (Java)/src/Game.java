import java.awt.Font;

// The main class for the Pac-Man project
// CMS 121, Spring 2022
// Author Kierson Baxley

public class Game {
	
	public static void main(String[] args) {
		
		// Initialize objects
		PacMan pacman = new PacMan();
		Ghost blinky = new Ghost();
		blinky.setXY(.05, .95);
		Ghost inky = new Ghost();
		inky.setXY(.95, .05);
		
		// Initialize Win/Lose & Score
		Integer score = 0;
		boolean gamewon = false;
		boolean gamelost = false;
		boolean playing = true;
		
		// Build Walls
		// Walls are placed on whole numbers
		Wall[] walls = new Wall[25];
		
		// Left
		walls[0] = new Wall(0, .5, .01, .5);
		
		// Right
		walls[1] = new Wall(1, .5, .01, .5);
		
		// Top
		walls[2] = new Wall(.5, 1.02, .51, .03);
		
		// Bottom
		walls[3] = new Wall(.5, 0, .51, .01);
		
		// Maze
		// Horizontal's
		walls[4] = new Wall(.3, .9, .2, .01);
		walls[5] = new Wall(.7, .9, .1, .01);
		walls[6] = new Wall(.5, .8, .3, .01);
		walls[7] = new Wall(.6, .7, .2, .01);
		walls[8] = new Wall(.5, .6, .2, .01);
		walls[9] = new Wall(.2, .5, .1, .01);
		walls[10] = new Wall(.6, .5, .2, .01);
		walls[11] = new Wall(.8, .4, .1, .01);
		walls[12] = new Wall(.3, .2, .1, .01);
		walls[13] = new Wall(.7, .2, .2, .01);
		walls[14] = new Wall(.2, .1, .1, .01);
		walls[15] = new Wall(.7, .1, .2, .01);
		
		// Vertical's
		walls[16] = new Wall(.1, .5, .01, .41);
		walls[17] = new Wall(.2, .7, .01, .11);
		walls[18] = new Wall(.2, .3, .01, .11);
		walls[19] = new Wall(.3, .5, .01, .21);
		walls[20] = new Wall(.4, .2, .01, .21);
		walls[21] = new Wall(.5, .3, .01, .11);
		walls[22] = new Wall(.6, .4, .01, .11);
		walls[23] = new Wall(.8, .6, .01, .11);
		walls[24] = new Wall(.9, .6, .01, .31);

		// Pellets
		Pellet[] pellets = new Pellet[100];
		
		// For loop is used to initialize each
		// pellet and place them throughout the
		// maze between whole numbers to avoid
		// being placed "inside" the walls
		double y = .05;
		double x = .05;
		for(int i = 0; i < 100; i++) {
			pellets[i] = new Pellet(x, y);
			x += .1;
			
			if(x > .95 ) {
				x = 0.05;
				y += .1;
			}
		}

		// Main Loop
		while (playing) {
			
			// Position Updates
			pacman.update();
			blinky.loopOne();
			inky.loopTwo();
			
			// Checking Collisions
			for (Wall b : walls) {
				pacman.wallCollision(b);
			}
			
			// Checking Collision
			// Setting losing condition
			if(pacman.ghostCollision(blinky)) {
				System.out.println("You Died!");
				gamelost = true;
				playing = false;
			}
			if(pacman.ghostCollision(inky)) {
				System.out.println("You Died!");
				gamelost = true;
				playing = false;
			}


			// Drawing Elements
			StdDraw.clear();
			StdDraw.setPenColor(0, 0, 0);
			StdDraw.filledRectangle(.5, .5, .5, .5);
			pacman.draw();
			
			blinky.setColor(200, 25, 25);
			blinky.draw();
			
			inky.setColor(0, 255, 255);
			inky.draw();
			
			// Drawing Pellets
			// using solely the null method to 
			// satisfy drawing condition wasn't
			// working so I set them to null and
			// moved them off of the board instead
			// This loop draws each pellet while
			// also checking for collisions
			for (Pellet p: pellets) {
				
				if(p.pacmanCollision(pacman)) {
					p.setXY(0.0, 0.0);
					p = null;
					score ++;
				}
				
				if(p != null) {
					p.draw();
				}
			}
			
			// Drawing walls
			for (Wall w : walls) {
				w.draw();
			}
			
			// Drawing Scoreboard
			StdDraw.setPenColor(0, 0, 0);
            StdDraw.text(.5, 1.01, "Score: " + score.toString());
            
            // Checking winning condition
            if(score == 100) {
            	gamewon = true;
                break;
            }
			StdDraw.show(20);
		}
		
		// Win Loop
		while(gamewon) {
			int red = (int) (Math.random() * 256);
			int green = (int) (Math.random() * 256);
			int blue = (int) (Math.random() * 256);
			Font winFont = new Font("SansSerif", Font.BOLD, 20);
			StdDraw.clear();
			StdDraw.setPenColor(red, green, blue);
			StdDraw.filledRectangle(.5, .5, .5, .5);
			StdDraw.setPenColor(255, 255, 255);
			StdDraw.setFont(winFont);
			StdDraw.text(.5, .5, "YOU WIN!");			
			StdDraw.show(250);
		}
		
		// Loss Loop
		while(gamelost) {
			int red = (int) (Math.random() * 256);
			int green = (int) (Math.random() * 256);
			int blue = (int) (Math.random() * 256);
			Font winFont = new Font("SansSerif", Font.BOLD, 20);
			StdDraw.clear();
			StdDraw.setPenColor(red, green, blue);
			StdDraw.filledRectangle(.5, .5, .5, .5);
			StdDraw.setPenColor(255, 255, 255);
			StdDraw.setFont(winFont);
			StdDraw.text(.5, .5, "YOU LOSE!");
			StdDraw.text(.5, .4, "git gud skrub");
			StdDraw.show(250);
		}
	}
}