#DiceGame

##Overview
This Java program simulates a dice game where players take turns rolling dice and scoring based on specific sequence patterns.

##Authors
Saanavi Goyal
Srewashi Mondal

##Features
playGame(int numPlayers, String[] playerNames, int diceRolls): Starts the game for the specified number of players, each rolling the dice a specified number of times.
playTurn(int numDiceRolls): Handles a single player's turn, scoring based on sequential patterns in dice rolls.
rollDice(): Simulates a dice roll, returning a random number between 1 and 6.

##How to Use
Setup: Instantiate the DiceGame class.
Start the Game: Call playGame() with the number of players, their names, and the number of dice rolls per player.
Gameplay: Each player's turn is handled sequentially, where dice rolls are evaluated for specific sequential patterns (increasing or decreasing).
Scoring: Scores are accumulated and displayed based on adherence to sequential patterns.

**Notes**
The game expects players to adhere to either an increasing or decreasing sequence in dice rolls. Deviation results in a score of 0 for that turn.
Sequence adherence is checked for each subsequent roll after the first roll.
