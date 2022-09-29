import java.util.Random;

/**
 * The Player class models a player in the game. It maintains information about the player
 * @attribute bag
 * @attribute id
 * @attribute marblesDrawed
 * @attribute isEvenOrOdd
 *
 * @method getID()
 * @method getNumberOfMarblesLeft()
 * @method getIsEvenOrOdd()
 * @method drawMarbles()
 * @method guessEvenOrOdd()
 * @method modifyMarbleQuantity(int)
 * @method toString()
 * 
 * Created by: Alexandre Marques and Cade Theriault for 
 * a Carleton University coding competition.
 */
public class Player{
    private Marbles bag;
    private int player_id;
    private int marblesDrawed;
    private String isEvenOrOdd;
    
    /**
     * Initializes players object with an id and a default marble bag
     * 
     * @param integer sets the player id 
     */
    //Player constructor to initialize a player with an ID
    public Player(int id) {
        player_id = id;
        bag = new Marbles();
    }

    /**
     * Getter for the player ID
     * 
     * @return player id
     */

    public int getID() {
        return player_id; 
    }
    
    /**
     * Getter for the player's number of marbles
     * 
     * @return number of marbles in the player's marble bag
     */
    public int getNumberOfMarblesLeft() {
        return bag.getNumberOfMarbles();
    }

    /**
     * @return A string representing whether the quantity of marbles drawn is even or odd
     */
    public String getIsEvenOrOdd() {
        return isEvenOrOdd;
    }

    /**
     * Draws a random number of marbles based on the amount of marbles available
     * NOTE: This method may be uses to draw marbles for betting as well
     * 
     * @return the number of marbles randomly chosen
     */ 
    public int drawMarbles() {
        Random rand = new Random(); 
        marblesDrawed = rand.nextInt(getNumberOfMarblesLeft() + 1);
        return marblesDrawed; //this line is here to avoid errors
    }
    
    /**
     * @return a guess about whether the quantity of marbles the
     *         other player made is even or odd   
     */
    public static String guessEvenOrOdd() {
        Random rand = new Random();
        int guess = rand.nextInt(2);
        
        if (guess == 0) {
            return "Even";
        } else if (guess == 1) {
            return "Odd";
        }
        return "";
    }
    
    /**
     * Modifies the number of marbles in the player's marble bag
     *
     * @param int representing the numer of marbles to add (positive argument) or to substract (negative argument)
     */
    public void modifyMarbleQuantity(int marbleNumber) {
        bag.addOrSubMarbles(marbleNumber);
    }
    
    /**
     * 
     * Gives a string representation of a player object that includes
     * the id and a representation of the marbles the player has
     * 
     * @return A string representation of the player
     */
    //returns a string representation of the player
    public String toString() {
        int num_mar = getNumberOfMarblesLeft();
        
        return player_id + " has " + num_mar + " marbles.";
    }

}