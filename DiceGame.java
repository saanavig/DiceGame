public class DiceGame 
{
    // Method to start the game
    public static int[] playGame(int numPlayers, String[] playerNames, int diceRolls) 
    {
        // Play the game for each player
        int[] totalScores = new int[numPlayers];

        for (int i = 0; i < numPlayers; i++) 
        {
            System.out.println("\n" + playerNames[i] + "'s turn:");
            int score = playTurn(diceRolls);
            totalScores[i] = score;
        }
        return totalScores;
    }

    // Method to handle a player's turn
    public static int playTurn(int numDiceRolls) {
        int score = 0;
        int prevRoll = rollDice();
        boolean increasing = false;
        boolean decreasing = false;
        boolean isFirstRoll = true;

        for (int rollIndex = 0; rollIndex < numDiceRolls; rollIndex++) 
        {
            int diceRoll = rollDice();
            System.out.println("You rolled: " + diceRoll);

            if (!isFirstRoll) 
            {
            	//checks for increasing patterns
                if (prevRoll < diceRoll) 
                {
                    if (decreasing) 
                    {
                        score = 0;
                        System.out.println("Pattern not followed. Your score is 0.");
                        break;
                    }
                    
                    increasing = true;
                    System.out.println("You are in an increasing sequence.");
                } 
                //checks for decreasing pattwens
                else if (prevRoll > diceRoll) 
                {
                    if (increasing) 
                    {
                        score = 0;
                        System.out.println("Pattern not followed. Your score is 0.");
                        break;
                    }
                    
                    decreasing = true;
                    System.out.println("You are in a decreasing sequence.");
                } 
                else 
                {
                    score = 0;
                    System.out.println("Pattern not followed. Your score is 0.");
                    break;
                }
            } 
            else 
            {
                isFirstRoll = false;
            }

            score += diceRoll;
            prevRoll = diceRoll;
        }

        if (score != 0) 
        {
            System.out.println("Sequence followed. Your score is: " + score);
        }

        return score;
    }


    // Function to simulate a dice roll
    public static int rollDice() 
    {
        return (int) (Math.random() * 6) + 1; // Random number between 1 and 6
    }
}