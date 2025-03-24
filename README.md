Mancala Game - Team Abstractionists 

**Overview**   
This is Mancala Game, a Java-based project developed by Team Abstractionists. 
This project implements a digital version of the ancient two-player board game Mancala using Java Swing.  
The game follows the MVC (Model-View-Controller) pattern and employs the Strategy pattern to allow different board styles.

**Game Rules**
- The board consists of two rows of pits, each. 
- Three pieces of stones are placed in each of the 12 holes.
- Each player has a large store called Mancala to the right side of the board.
- One player starts the game by picking up all of the stones in any one of his own pits.
- Moving counter-clock wise, the player places one in each pit starting with the next pit until the stones run out.
- If you run into your own Mancala, place one stone in it.
- If there are more stones to go past your own Mancala, continue placing them into the opponent's pits.
- However, skip your opponent's Mancala. If the last stone you drop is your own Mancala, you get a free turn .
- If the last stone you drop is in an empty pit on your side, you get to take that stone and all of your opponents stones that are in the opposite pit.
- Place all captured stones in your own Mancala. The game ends when all six pits on one side of the Mancala board are empty.
- The player who still has stones on his side of the board when the game ends captures all of those pieces and place them in his Mancala. 
- The player who has the most stones in his Mancala wins.

**Features** 

Interactive GUI: Players can click pits to make moves.
Configurable Stone Count: Players choose between 3 or 4 stones per pit at the start.
Undo Functionality: Allows players to undo their last move up to 3 times per turn.
Multiple Board Styles: Different visual styles for the board using the Strategy pattern.
Strictly Original Code: No drag-and-drop GUI tools, external libraries, or copied code.

**Technical Implementation** 

- Design Patterns Used: MVC (Model-View-Controller): Separates game logic (Model), user interface (View), and interactions (Controller).

-Strategy Pattern: Allows switching between different board styles dynamically.

**How to Run the Project**

Clone the repository:
git clone https://github.com/d2blepeace/CS151_Final_Project.git

**How to Play**

***IN PROGRESS***

**Contributions**

Follow the coding rules: No drag-and-drop GUI placement or external code.

Ensure MVC and Strategy pattern adherence.

Commit messages should be clear and concise.

