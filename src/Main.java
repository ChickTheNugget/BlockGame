
import com.Controller.BlockGame;

/**
 * Main class, starts the Game.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Title: Block Game");
        System.out.println("Project by Nguyen Jean and Majerus Mil");
        System.out.println("CBL Project for 2IP90 in Eindhoven University of Technology");

        BlockGame game = new BlockGame();

        game.run();
    }
}