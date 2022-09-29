import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

/**
 * A class modelling a tic-tac-toe (noughts and crosses, Xs and Os) game in a
 * GUI window. 
 * 
 * To start playing the game, call the constructor.
 * 
 * @author Lynn Marshall
 * @version November 8, 2012
 * 
 * @author Cade Theriault 101 192 252
 * @version April 1, 2022
 */

public class TicTacToe extends MouseAdapter implements ActionListener
{ 
   // GUI components
   private JFrame frame = new JFrame("TicTacToe");
   private JPanel textPanel = new JPanel();
   private JPanel btnPanel = new JPanel();
   private JButton[] btns = new JButton[9];
   private JLabel turns = new JLabel();
   private JLabel stats = new JLabel();
   private JMenuBar menuBar = new JMenuBar();
   private JMenuItem newGameItem; // Start new game or 'reset'
   private JMenuItem quitItem; // Quit game
   private JMenuItem musicItem; // Turn on and off music (default is on)
   private JMenuItem soundItem; // Turn on and off sound (default is on)
   
   // Game components
   private boolean player_1; // player 1 is x when true
   private int numFreeSquares; // Number of squares left during game (starts at 9, ends at 0)
   private int x_wins; // Counter for wins player 'X' has won
   private int o_wins; // Counter for wins player 'O' has won
   private int ties; // Counter for tie games
   
   // ImageIcons for 'X' and 'O' scaled to fit the buttons
   private static ImageIcon X_ICON = new ImageIcon(new ImageIcon(TicTacToe.class.getResource("x_icon.png")).getImage().getScaledInstance(210, 210, Image.SCALE_DEFAULT));
   private static ImageIcon O_ICON = new ImageIcon(new ImageIcon(TicTacToe.class.getResource("o_icon.png")).getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT));
   
   // Sound components
   private AudioClip clip, loop;
   
   /* Main background song that is looped until game is closed. */
   private URL backMusicUrl = TicTacToe.class.getResource("loop.wav");
   
   /* Sound of a mark being placed on the board. */
   private URL clickUrl = TicTacToe.class.getResource("click.wav");
   
   /* Sound when a player wins. */
   private URL winUrl = TicTacToe.class.getResource("win.wav");

   /* Sound of a tie game. */
   private URL tieUrl = TicTacToe.class.getResource("tie.wav");
   
   private boolean musicOn = true; // Default is on
   private boolean soundOn = true; // Default is on
   
   /** 
    * Constructs a new Tic-Tac-Toe board and sets up the basic
    * JFrame containing JPanels, JButtons, a JMenu Bar and a JLabel.
    */
   public TicTacToe()
   { 
       // frame 
       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       frame.setPreferredSize(new Dimension(600, 700));
       frame.setLayout(new BorderLayout());
       frame.setVisible(true);
       
       // menu bar
       frame.setJMenuBar(menuBar);
       JMenu fileMenu = new JMenu("Game");
       menuBar.add(fileMenu);
       fileMenu.addMouseListener(this);
       newGameItem = new JMenuItem("New Game");
       fileMenu.add(newGameItem);
       musicItem = new JMenuItem("Music");
       fileMenu.add(musicItem);
       soundItem = new JMenuItem("Sound");
       fileMenu.add(soundItem);
       quitItem = new JMenuItem("Quit");
       fileMenu.add(quitItem);
       
       // Allow for shortcuts for menu actions (Ctrl+N for 'new game' and Ctrl+Q for 'quit')
       final int SHORTCUT_MASK = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask(); // to save typing
       newGameItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, SHORTCUT_MASK));
       quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, SHORTCUT_MASK));// listen for menu selections
       newGameItem.addActionListener(this);
       quitItem.addActionListener(this); 
       
       musicItem.addActionListener(new ActionListener() // create an anonymous inner class
         { // start of anonymous subclass of ActionListener
            // this allows us to put the code for this action here  
            public void actionPerformed(ActionEvent event)
            {
                if (musicOn) { // music is already on, turn it off
                    stopMusic();
                    musicOn = false;
                } else if (!musicOn) { // music is already off, turn it on
                    loopMusic(backMusicUrl);
                    musicOn = true;
                }
            }
         } // end of anonymous subclass
       ); // end of addActionListener parameter list and statement
       
       soundItem.addActionListener(new ActionListener() // create an anonymous inner class
         { // start of anonymous subclass of ActionListener
            // this allows us to put the code for this action here  
            public void actionPerformed(ActionEvent event)
            {
                if (soundOn) { // sound is already on, turn it off
                    soundOn = false;
                } else if (!soundOn) { // sound is already off, turn it on
                    soundOn = true;
                }
            }
         } // end of anonymous subclass
       ); // end of addActionListener parameter list and statement
       
       
       // text panels and labels
       turns.setFont(new Font("Arial", Font.PLAIN , 50));
       turns.setHorizontalAlignment(JLabel.CENTER);
       
       stats.setFont(new Font("Arial", Font.PLAIN , 25));
       stats.setHorizontalAlignment(JLabel.CENTER);
       
       textPanel.setLayout(new BorderLayout());
       textPanel.setBounds(0,0, 600, 100);
       textPanel.add(stats, BorderLayout.WEST);
       textPanel.add(turns, BorderLayout.EAST);
       
       // button panel
       btnPanel.setLayout(new GridLayout(3,3));
       
       // Set up buttons
       for (int i =0; i < 9; i++) {
           btns[i] = new JButton();
           btnPanel.add(btns[i]);
           btns[i].setBackground(Color.WHITE);
           btns[i].setFocusable(false);
           btns[i].addActionListener(this);
       }
       
       // finish set up
       frame.add(textPanel, BorderLayout.SOUTH);
       frame.add(btnPanel);
       frame.pack();
       frame.setResizable(true);
              
       loopMusic(backMusicUrl); // Start music
       playGame(); // Start game
   }
   
   /**
    * Plays one game of Tic Tac Toe.
    */
   private void playGame()
   {
      clearBoard(); // clear the board
      player_1 = true; // player x starts by default
   } 
   
   /**
    * Once the current game is over, will prompt an option window to ask the players if they want 
    * to restart and play another game or to close the game.
    */
   private void gameOver(String s) {
       numFreeSquares = 9;
       Object[] option={"Restart","Quit"};
       int n=JOptionPane.showOptionDialog(frame, "Game Over\n"+s,"Game Over",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
       if(n==0){ // restart
           clearBoard();
       } else{ // quit
           System.exit(0);
       }
   }
   
   /**
    * Checks if the currently selected buttons means one of the players has won or
    * if the game is a tie.
    */
   private boolean haveWinner() 
   {
       // unless at least 5 squares have been filled, we don't need to go any further
       // (the earliest we can have a winner is after player X's 3rd move).

       if (numFreeSquares>4) return false;
       
       // a winner
       if ( btns[0].getIcon() == btns[1].getIcon() && btns[0].getIcon() == btns[2].getIcon() && btns[0].getIcon() != null ) {
           wins(0, 1, 2);
           return true;
       } else if ( btns[0].getIcon() == btns[4].getIcon() && btns[0].getIcon() == btns[8].getIcon() && btns[0].getIcon() != null ) {
           wins(0, 4, 8);
           return true;
       } else if ( btns[0].getIcon() == btns[3].getIcon() && btns[0].getIcon() == btns[6].getIcon() && btns[0].getIcon() != null ) {
           wins(0, 3, 6);
           return true;
       } else if ( btns[1].getIcon() == btns[4].getIcon() && btns[1].getIcon() == btns[7].getIcon() && btns[1].getIcon() != null ) {
           wins(1, 4, 7);
           return true;
       } else if ( btns[2].getIcon() == btns[4].getIcon() && btns[2].getIcon() == btns[6].getIcon() && btns[2].getIcon() != null ) {
           wins(2, 4, 6);
           return true;
       } else if ( btns[2].getIcon() == btns[5].getIcon() && btns[2].getIcon() == btns[8].getIcon() && btns[2].getIcon() != null ) {
           wins(2, 5, 8);
           return true;
       } else if ( btns[3].getIcon() == btns[4].getIcon() && btns[3].getIcon() == btns[5].getIcon() && btns[3].getIcon() != null ) {
           wins(3, 4, 5);
           return true;
       } else if ( btns[6].getIcon() == btns[7].getIcon() && btns[6].getIcon() == btns[8].getIcon() && btns[6].getIcon() != null ) {
           wins(6, 7, 8);
           return true;
       }
       
       // Tie game
       else if(numFreeSquares==0) {
            turns.setText("Match Tie");
            ties += 1;
            playSound(tieUrl);
            gameOver("Match Tie");
            return true;    // Counts as a win cause game over, 
                            // and reset to player_x happens.
       }
       
       return false; // No winner, no tie, and at least 5 squares filled.
   }
   
   /**
    * When a player wins, all buttons are disabled and the winning stats are updated. 
    * If the winning player is player 'X': the winning buttons will be highlighted with red.
    * If the winning player is player 'O': the winning buttons will be highlighted with blue.
    */
   private void wins(int i1, int i2, int i3) {
        for (int i = 0; i < 9; i++) {
            btns[i].setEnabled(false);
        }
        if (player_1) { // 'X' wins
            btns[i1].setBackground(Color.RED);
            btns[i2].setBackground(Color.RED);
            btns[i3].setBackground(Color.RED);
        
            turns.setText("X wins");
            x_wins += 1;
            playSound(winUrl);
            gameOver("X Wins");
        } else if(!player_1) { // 'O' wins
            btns[i1].setBackground(Color.BLUE);
            btns[i2].setBackground(Color.BLUE);
            btns[i3].setBackground(Color.BLUE);
       
            turns.setText("O Wins");
            o_wins += 1;
            playSound(winUrl);
            gameOver("O Wins");
        }
    }
    
   /**
    * Sets everything up for a new game.  Marks all squares in the Tic Tac Toe board as empty,
    * and indicates no winner yet, 9 free squares and the current player is player X.
    */
   private void clearBoard()
   {
      for (int i = 0; i < 9; i++) {
          btns[i].setIcon(null);
          btns[i].setEnabled(true);
          btns[i].setBackground(Color.WHITE);
      }
      
      // Player X always has the first turn.
      player_1 = true;     
      turns.setText("X turn"); 
      stats.setText("X wins: " + x_wins + " O wins: " + o_wins + " Ties: " + ties); // Updates the stats from previous game
      numFreeSquares = 9; // Counter of free squares
      
   }
   
   /**
    * Overrides the actionPerformed() from ActionListener. When a button is selected, it  will change
    * to the corresponding mark of the player. When a menu item is selected, it will perform its 
    * corresponding action. (i.e. newGameItem clears the board and resets to a new game).
    */
   public void actionPerformed(ActionEvent e) {
       Object o = e.getSource();
       if (o instanceof JButton) {
           for (int i = 0; i < 9; i++) {
               JButton btn = (JButton)o;
               if (btn == btns[i]) {
                   if (player_1) { // player 'x'
                       if (btns[i].getIcon() == null) {
                           btns[i].setIcon(X_ICON);
                           playSound(clickUrl);
                           numFreeSquares--;
                           if (!haveWinner()) { // no winner yet, change to next player
                               player_1 = false; // change player to 'o'
                               turns.setText("O turn");
                           }
                       }
                   } else { // player 'o'
                       if (btns[i].getIcon() == null) {
                           btns[i].setIcon(O_ICON);
                           playSound(clickUrl);
                           numFreeSquares--;
                           if (!haveWinner()) { // no winner yet, change to next player
                               player_1 = true; // change player to 'x'
                               turns.setText("X turn");
                           }
                       }
                   }
               } 
           }
       } else { // JMenuItem
           JMenuItem item = (JMenuItem)o;
            
           if (item == newGameItem) { 
               clearBoard();
           } else if (item == quitItem) { // quit
               System.exit(0);
           }
       }
   }
   
   /**
    * Loops an audio file found with the name parameter.
    * 
    * @param url The url of an audio file
    */
   private void loopMusic(URL url) {
       loop = Applet.newAudioClip(url);
       loop.loop();
   }
   
   /**
    * Plays an audio file found with the name parameter.
    * 
    * @param url The url of an audio file
    */
   private void playSound(URL url) {
       if (soundOn) {
           clip = Applet.newAudioClip(url);
           clip.play();
       }
   }
   
   /**
    * Stops the looped audio file.
    */
   private void stopMusic() {
       loop.stop();
   }
}

   
   
   

   
