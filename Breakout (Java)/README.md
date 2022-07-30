# Breakout

Summary: 

   My version of Breakout is a little different than the OG. The assignment was to code up a traditional Breakout game with our own twist added. I had fun playing around with random colors so I amplified that feature and made everything change color at some point. But to me, a visual upgrade wasn't good enough. While the ball bounces around and changes the colors of itself, the paddle, the bricks, and the background. The ball also increases in speed when it bounces off of a brick. It keeps those longer games interesting. I also added "inspirational" messages for when the player wins or loses.

Technical Description:

  On the technical side the game is broken up into four main classes with Breakout.java being the main class and utilizing StdDraw.java to draw each element. 

Ball Class: Creates the ball using the StdDraw class. It also updates the balls position as it bounces around, keeps the ball in bounds, and checks for any collisions with the paddle or bricks. 

Brick Class: Each brick is drawn in Breakout.java using an array of Brick objects that are created in this class. It also provides getters for Ball.java to check for collisions. 

Paddle Class: Draws the paddle and updates its posit. Also provides getters for Ball.java to check for collisions. 

Breakout Class: This is where the magic happens. Ball and paddle are initialized, bricks are initialized using an array. Then the main game loop runs, first checking for win/loss conditions then checking collisions, updating posits, and finally re-drawing everything. 
