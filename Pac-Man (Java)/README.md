#Pac-Man

Summary: 

You know it, you love it, it's classic Pac-Man. No frills with this joint, just the basics. The assignment was to create Pac-Man within the boundaries of what was possible in two weeks for first year students. My version is extremely bare-bones, I was originally planning a very intricate maze but due to time constraints I opted for a more basic design.

Technical Details:

This program is broken up into four classes and utilizes StdDraw.java to create each visual element.

Game Class: The main class. It uses arrays to create each wall and the pellets that smatter the board before entering the 36 chambe.. I mean before entering the main loop that updates the moving elements, checks for collisions and re-draws each moving element then checks for win/lose conditions. 

Ghost Class: This class creates each ghost and contains the movement script for each one. 

PacMan Class: This class creates the main star of the show, moves them around, and checks for collisions with the wall and the ghosts. 

Pellet Class: Creates/draws each pellet, contains getters/setters, and checks for collisions with PacMan.

Wall Class: The simplest of all the classes, wall provides getters and the Wall object used in Game.java. 
