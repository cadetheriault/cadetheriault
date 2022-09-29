import java.util.Random;

/**
 * The Game class manages the game. It decides who starts and enforces the rules. 
 * It also shows the results at multiple points during the game.
 * 
 * Created by: Alexandre Marques and Cade Theriault for 
 * a Carleton University coding competition.
 */

public class Game {

    //get creative here, doesn't have to bea specific format you can have as many players as you want and/or change this base code
    public static void main(String[] args) {
        Player[] players = {new Player(1), new Player(2)};
        
        
        Random rand = new Random(); 
        int turn = rand.nextInt(2); //determines who's turn it is
        int round = 0; // game's round number
        boolean marblesRemain = true; //False if one player out of marbles
        
        // Initial state
        //first turn: players[turn]
        //Subsequent turns: turn = turn + 1 % 2
       
        
        while(marblesRemain){ //this condition is here to avoid any errors, change it if you'd like
           //during game
           Player p1 = players[turn];
           Player p2 = players[(turn + 1) %2];
           //1: Grab marbles (P1)
           int marbles1 = players[turn].drawMarbles();
           //2: Bet marbles (P2)
           int marblesBet = players[(turn + 1) %2].drawMarbles();
           //3: Guess marbles
           if(Player.guessEvenOrOdd() == players[turn].getIsEvenOrOdd()) {
               p1.modifyMarbleQuantity(marblesBet * -1);
               p2.modifyMarbleQuantity(marblesBet);
           } else {
               p1.modifyMarbleQuantity(marblesBet);
               p2.modifyMarbleQuantity(marblesBet *-1);
           }
               
           turn = (turn + 1) % 2;
           System.out.println("Player 1 marbles: "+p1.getNumberOfMarblesLeft());
           System.out.println("Player 2 marbles: "+p2.getNumberOfMarblesLeft());
           //add end loop check
           if(p1.getNumberOfMarblesLeft() <= 0 || p2.getNumberOfMarblesLeft() <=0){ 
               marblesRemain = false; 
           }
        }

        // Final State
        
    }
}