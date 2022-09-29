
/**
 * The Marbles class represents a bag of marbles
 * 
 * @attribute numberOfMarbles
 *
 * @method addOrSubMarbles(int)
 * 
 * Created by: Alexandre Marques and Cade Theriault for 
 * a Carleton University coding competition.
 */
public class Marbles {
    private int numberOfMarbles;
    
    public Marbles() {
        numberOfMarbles = 10;
    }
    
    public int getNumberOfMarbles() {
        return numberOfMarbles; 
    }
    
    /**
     * Add or substract any earned or lost marbles
     * @param int number of Marbles lost (negative) or marbles earned (positive)
     * @return    Updated number of marbles
     */
    public int addOrSubMarbles(int number) {
        
        numberOfMarbles += number;   
        return numberOfMarbles;
    }
}