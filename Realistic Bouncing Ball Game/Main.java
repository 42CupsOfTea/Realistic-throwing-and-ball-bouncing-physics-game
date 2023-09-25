import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {
    static JFrame frame = new JFrame("Monkey Throw");
    static int WINDOWWIDTH = 1800;
    static int WINDOWHEIGHT = 1000;
    public static void main(String[] args) {
        EventTracker tracker = new EventTracker();
        
        //setting up the game and allowing home and screen to talk to each other
        HomePage home = new HomePage(tracker);
        Screen screen = new Screen(home);
        home.passScreen(screen);

        //making the frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(WINDOWWIDTH,WINDOWHEIGHT);
        frame.setVisible(true);
        frame.setLayout(null);

        frame.setContentPane(screen);
    }
   
}
