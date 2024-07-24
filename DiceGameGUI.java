import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/*
 * Idea for GUI:
 * Page 1: Introduction to the Game, Creators
 * 	- Add two buttons: How to Play, Play Game
 * Page 2: How to Play
 * 	- Instructions to the game from the project description
 * 	- Button: Play Game Now
 * 	- Extra: Add graphics to make it look appealing
 * Page 3: Play Game
 * 	- Integrate with the already developed back end now
 * Note:  Each page should have a way to go back to Home
 */

//Things to add: Home Page once game begins

public class DiceGameGUI
{
    //creating private variables 
    private JFrame frame;
    private JPanel panel;
    private CardLayout layout;
    //private int currentPlayer;
    private String[] playerNames;
    private int[] totalScores;
    private JTextArea scoreBoard;
    private int[] rollCounts;

    public static void main (String [] args)
    {
        //creating a main GUI class
        new DiceGameGUI();
    }

    public DiceGameGUI ()
    {
        //creating and designing the JFrame
        frame = new JFrame();
        frame.setTitle("Run for it Dice Game");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Panel and layout
        panel = new JPanel();
        layout = new CardLayout();
        panel.setLayout(layout);
    
        //add all pages - HomePage, HowToPlay, and playGame
        HomePage();
        Instructions();
        PlayGame();
        
        frame.add(panel);
        frame.setVisible(true);
    }
    
    //creating a HomePage
    private void HomePage()
    {
        JPanel homePage = new JPanel(new BorderLayout());
            
        JLabel title = new JLabel("<html><div style='text-align: center;'>Welcome to...<br>Run for it Dice Game</html>");
        title.setFont(new Font("Times New Roman", Font.BOLD, 30));
        title.setHorizontalAlignment(JLabel.CENTER);
        homePage.add(title, BorderLayout.NORTH);
            
        
        //button to lead to the instructions of the Game
        JButton instructions = new JButton("Instructions");
        instructions.setFont(new Font("Arial", Font.PLAIN, 14));
        homePage.add(instructions,BorderLayout.CENTER);
        instructions.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                layout.show(panel, "Instructions");
            }
        });

        
        //button to play the game
        JButton gamePlay = new JButton("Play Game");
        gamePlay.setFont(new Font("Arial", Font.PLAIN, 14));
        homePage.add(gamePlay,BorderLayout.SOUTH);
        gamePlay.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                layout.show(panel, "Play Game");
            }
        });
        
        panel.add(homePage, "Home Page");
    }
    
    //creating How To Play Page
    private void Instructions()
    {
        JPanel instructionsPage = new JPanel(new BorderLayout());
        
        JLabel instructionsText = new JLabel();
        
        instructionsText.setText("<html><div style='text-align: center;'>🎲Welcome to the Run For It Dice Game!🎲<br><br>In this game, "
                + "players take turns rolling dice to create increasing or decreasing sequences of numbers. On each turn, a player can roll the dice up "
                + "to six times, deciding whether to continue or stop after each roll.<br>If a sequence breaks, the player scores zero for that round, but "
                + "if they stop with a monotone sequence, their score is the sum of<br>the numbers in the sequence. After a set number of rounds, the player "
                + "with the highest total score wins. <br><br>GOOD LUCK!🎉</div></html>");
        
        instructionsText.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        instructionsText.setHorizontalAlignment(JLabel.CENTER);
        instructionsPage.add(instructionsText, BorderLayout.CENTER);

        
        JButton gamePlay = new JButton("Play Game");
        instructionsPage.add(gamePlay,BorderLayout.SOUTH);
        gamePlay.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                layout.show(panel, "Play Game");
            }
        });
        
        JButton goHome = new JButton("Back to Home");
        instructionsPage.add(goHome,BorderLayout.NORTH);
        goHome.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                layout.show(panel, "Home Page");
            }
        });
        
        panel.add(instructionsPage, "Instructions");
    }
    
    //creating Play Game
    private void PlayGame()
    {
        JPanel playGamePage = new JPanel(new BorderLayout());
            
        JButton startGame = new JButton("Start Game");
        playGamePage.add(startGame, BorderLayout.CENTER);
        startGame.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                StartGame();
            }
        });
        
            
        JButton goHome = new JButton("Back to Home");
        playGamePage.add(goHome,BorderLayout.NORTH);
        goHome.addActionListener(new ActionListener ()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                layout.show(panel, "Home Page");
            }
        });
        
        panel.add(playGamePage, "Play Game");
    }
    
    /*
     * Detailed Steps for building the GUI after the game starts:
     * 1. Number of players is asked, and submitted - DONE
     * 2. Names are asked for each player, each line as one player name - Border, Grid, Flow - DONE
     * 		Names should be stored in an array for a later use. - DONE
     * 3. A submit button again towards the end of the frame, to submit all the names. - DONE
     * 4. Create a score counter for next screen to keep track
     * 	Additonal Feature: Create a leaderboard
     * 5. A new screen begins for each player in an iterative loop
     * 		a. For each player: A roll dice button is given. They click the dice button to begin the game.
     * 			Addtional Feature: A dice animation is shown instead of showing the dice number
			b. The result of the dice is shown on the screen.
				i. The game states "You rolled __" clearly.
			c. The player chooses if they want to continue or not 
				i. If they continue, and they the pattern is continued, they play again iteratively
				ii. If they continue, and the pattern is not continued, they get 0
				iii. If they don't continue, their score is the total number of their dice roll iteratively
		6. Once one player players, the screen goes to the next player, until all players complete their turn
		7. Once all players are done, a final leaderboard is shown
			a. Result name is in title
		8. At South Border, the option is given to play again.
		Addtional Feature: At any point in the game, they can go home.
		Additional Feature: At any point in the game, they can undo. 
			
     */
    

    //Start Game Function
    private void StartGame() 
    {
        // developing the GUI for taking user input to start the game
        JPanel startGamePanel = new JPanel(new FlowLayout());
        JLabel numPlayers = new JLabel("Enter the number of players: ");
        JTextField inputPlayers = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        startGamePanel.add(numPlayers);
        startGamePanel.add(inputPlayers);
        startGamePanel.add(submitButton);

        panel.add(startGamePanel, "Start Game");
        layout.show(panel, "Start Game");

        submitButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String inputNumber = inputPlayers.getText();

                if (inputNumber.matches("\\d+")) 
                {
                    int numPlayers = Integer.parseInt(inputNumber);

                    // Initialize totalScores array with the number of players
                    totalScores = new int[numPlayers];
                    rollCounts = new int[numPlayers];

                    // to ask player's first names + submit button
                    JPanel namesList = new JPanel(new GridLayout(0, 1));
                    namesList.add(new JLabel());
                    JTextField[] names = new JTextField[numPlayers];
                    
                    for (int i = 0; i < numPlayers; i++) 
                    {
                        namesList.add(new JLabel("Player " + (i + 1) + " First Name: "));
                        names[i] = new JTextField(10);
                        namesList.add(names[i]);
                    }

                    JButton submitButton2 = new JButton("Submit");
                    namesList.add(submitButton2);

                    submitButton2.addActionListener(new ActionListener() 
                    {
                        @Override
                        public void actionPerformed(ActionEvent e) 
                        {
                            playerNames = new String[numPlayers];
                            for (int i = 0; i < numPlayers; i++) 
                            {
                                playerNames[i] = names[i].getText();
                            }

                            PlayerTurn(0);

                            panel.revalidate();
                            panel.repaint();
                        }
                    });

                    panel.add(namesList, "Enter Names");
                    layout.show(panel, "Enter Names");
                    panel.revalidate();
                    panel.repaint();
                }
                
                else 
                {
                	//fix this so it no longer an option, but as a text on the GUI panel
                    JOptionPane.showMessageDialog(null, "‼️Invalid input! Please enter a valid number.‼️");
                }
            }
        });
    }
    
    //method for players to start playing the games
    private void PlayerTurn(int currentPlayer) 
    {
        if (currentPlayer < playerNames.length) 
        {
            JPanel playerPanel = new JPanel(new BorderLayout());

            JLabel playerName = new JLabel(playerNames[currentPlayer] + "'s Turn: ");
            playerName.setFont(new Font("Times New Roman", Font.BOLD, 20));
            playerPanel.add(playerName, BorderLayout.WEST);
            
            boolean increaseSequence = Math.random() < 0.5; // 50% chance of true or false
            String sequenceType = increaseSequence ? "increasing" : "decreasing";
            JLabel sequenceTypeLabel = new JLabel("Sequence type: " + sequenceType);
            playerPanel.add(sequenceTypeLabel, BorderLayout.CENTER);

            JTextArea scoreBoard = new JTextArea();
            scoreBoard.setEditable(false);
            playerPanel.add(scoreBoard, BorderLayout.CENTER);
            this.scoreBoard = scoreBoard;

            JButton diceRoll = new JButton("Roll Dice");
            playerPanel.add(diceRoll, BorderLayout.SOUTH);

            JPanel scoreBoardPanel = scoreBoard();
            playerPanel.add(scoreBoardPanel, BorderLayout.EAST);

            //atomic allows the results to be updated automatically when changed
            AtomicInteger rollCount = new AtomicInteger(0);
            AtomicInteger sequenceScore = new AtomicInteger(0);
            AtomicInteger lastRoll = new AtomicInteger(0);
            AtomicBoolean isIncreasing = new AtomicBoolean(increaseSequence); // Set sequence type
            AtomicBoolean sequenceStarted = new AtomicBoolean(false);

            diceRoll.addActionListener(new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                	//disable the Roll Dice button once each player
                    diceRoll.setEnabled(false);
                    
                    //calls logic from Dice Game class
                    int diceNumber = DiceGame.rollDice();
                    scoreBoard.append("You Rolled: " + diceNumber + "\nWould you like to roll again? 🤔 \nPlease indicate using the buttons above.\nPlease Note: You only have 6 turns to max your score!\n");
                    String sequenceFeedback = isIncreasing.get() ? "increasing" : "decreasing";
                    scoreBoard.append("Your sequence is " + sequenceFeedback + "\n");
                    rollCount.incrementAndGet();

                    //ensures that the pattern is being followed
                    if (!sequenceStarted.get()) 
                    {
                        sequenceStarted.set(true);
                        lastRoll.set(diceNumber);
                        sequenceScore.addAndGet(diceNumber);
                    } 
                    
                    else 
                    {
                    	if ((isIncreasing.get() && diceNumber > lastRoll.get()) || (!isIncreasing.get() && diceNumber < lastRoll.get())) 
                    	{
                            // If the current roll continues the sequence, update the sequence score
                            sequenceScore.addAndGet(diceNumber);
                            if (isIncreasing.get()) 
                            {
                                scoreBoard.append("You are currently in an increasing sequence!\n");
                            } 
                            else 
                            {
                                scoreBoard.append("You are currently in a decreasing sequence!\n");
                            }
                        } 
                    	else 
                    	{
                            // If the sequence is broken, reset the sequence score
                            sequenceScore.set(0);
                            scoreBoard.append("Oh no! The sequence is broken. Your score for this turn is 0. \nClick top right button to move on to next player.\n ");
                            diceRoll.setEnabled(false);
                        }
                    }

                    lastRoll.set(diceNumber);
                    scoreBoard.append("Current Score: " + sequenceScore.get() + "\n");
                    
                    //calls the continueGame button
                    JPanel optionsPanel = continueGame(currentPlayer, lastRoll, rollCount, sequenceScore, diceRoll, sequenceStarted, isIncreasing);
                    playerPanel.add(optionsPanel, BorderLayout.NORTH);
                    playerPanel.revalidate();
                    playerPanel.repaint();
                }
            });

            JPanel rollAgain = continueGame(currentPlayer, lastRoll, rollCount, sequenceScore, diceRoll, sequenceStarted, isIncreasing);
            playerPanel.add(rollAgain, BorderLayout.NORTH);

            panel.add(playerPanel, "Player " + (currentPlayer + 1) + "'s Turn");
            layout.show(panel, "Player " + (currentPlayer + 1) + "'s Turn");
        }
    }


    //panel to continue playing the game using Yes or No buttons
    private JPanel continueGame(int currentPlayer, AtomicInteger lastRoll, AtomicInteger rollCount, AtomicInteger sequenceScore, JButton diceRoll, AtomicBoolean sequenceStarted, AtomicBoolean isIncreasing) {
        JPanel continueGame = new JPanel(new BorderLayout());
        JLabel messageLabel = new JLabel();
        continueGame.add(messageLabel, BorderLayout.NORTH);

        JButton yesButton = new JButton("Roll Dice Again");
        AtomicInteger rollAgainCounter = new AtomicInteger(0); // counts the number of yes to ensure 6 turns max

        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rollCount.get() == 0) {
                    messageLabel.setText("Please click Roll Dice at the bottom of the page to start the game!");
                } else {
                    if (rollAgainCounter.get() < 5) {
                        int newDiceRoll = DiceGame.rollDice();

                        // Display the roll result and sequence type
                        scoreBoard.append("You Rolled: " + newDiceRoll + "\n");
                        if (rollCount.get() == 1) {
                            String sequenceFeedback = isIncreasing.get() ? "increasing" : "decreasing";
                            scoreBoard.append("Your sequence is " + sequenceFeedback + "\n");
                        }

                        if (sequenceStarted.get() || rollCount.get() > 0) {
                            if ((isIncreasing.get() && newDiceRoll > lastRoll.get()) || (!isIncreasing.get() && newDiceRoll < lastRoll.get())) {
                                sequenceScore.addAndGet(newDiceRoll);
                                if (isIncreasing.get()) {
                                    scoreBoard.append("You are currently in an increasing sequence!\n");
                                } else {
                                    scoreBoard.append("You are currently in a decreasing sequence!\n");
                                }
                            } else {
                                sequenceScore.set(0);
                                scoreBoard.append("Oh no! The sequence is broken. Your score for this turn is 0.\nClick top right button to move on to next player.\n");
                                yesButton.setEnabled(false);
                            }
                        } else {
                            sequenceStarted.set(true);
                            lastRoll.set(newDiceRoll);
                            sequenceScore.addAndGet(newDiceRoll);
                        }

                        lastRoll.set(newDiceRoll);
                        rollCount.incrementAndGet();
                        rollAgainCounter.incrementAndGet();

                        scoreBoard.append("Current Score: " + sequenceScore.get() + "\n");

                        if (rollAgainCounter.get() == 5) {
                            yesButton.setEnabled(false);
                            diceRoll.setEnabled(false);
                        }
                    }
                }
            }
        });

        JButton noButton = new JButton("No, I am done!");
        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rollCount.get() == 0) {
                    messageLabel.setText("Please click Roll Dice at the bottom of the page to start the game.");
                } else {
                    if (sequenceScore.get() > 0) {
                        totalScores[currentPlayer] = sequenceScore.get();
                    } else {
                        // If the sequence broke, set the score to 0
                        totalScores[currentPlayer] = 0;
                    }
                    updateLeaderboard();
                    playerContinues(currentPlayer + 1);
                    messageLabel.setText("");
                }
            }
        });

        continueGame.add(yesButton, BorderLayout.WEST);
        continueGame.add(noButton, BorderLayout.EAST);

        return continueGame;
    }




    
    //updates the leaderboard whebn needed
    private void updateLeaderboard() 
    {
        scoreBoard.setText("");  // Clear existing text
        for (int i = 0; i < playerNames.length; i++) {
            scoreBoard.append(playerNames[i] + ": " + totalScores[i] + "\n");
        }
    }
    
    //creating a panel for a scoreBoardboard
    private JPanel scoreBoard ()
    {
    	JPanel scoreBoard = new JPanel(new GridLayout(0, 1));
        JLabel scoreBoardLabel = new JLabel("Leaderboard");
        
        scoreBoardLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        scoreBoard.add(scoreBoardLabel);
        
        for (int i = 0; i < playerNames.length; i++) 
        {
            JLabel scoreLabel = new JLabel(playerNames[i] + ": " + totalScores[i]);
            scoreBoard.add(scoreLabel);
        }
        
        return scoreBoard;
    }
 
    //making a function for playerContinues the game
    private void playerContinues (int nextPlayer)
    {
    	if (nextPlayer < playerNames.length)
    	{
    		PlayerTurn(nextPlayer);
    	}
    	else
    	{
    		 displayGameResult(totalScores);
    	}
    }
    
    // Method to display game result
    private void displayGameResult(int[] scores)
    {
        int maxScore = Integer.MIN_VALUE;
        int winnerIndex = -1;
        for (int i = 0; i < scores.length; i++) 
        {
            if (scores[i] > maxScore) 
            {
                maxScore = scores[i];
                winnerIndex = i;
            }
        }
        // Results+score
        JOptionPane.showMessageDialog(null,  playerNames[winnerIndex] + " has won the game with the score of " + maxScore + "🏆");
    }
}