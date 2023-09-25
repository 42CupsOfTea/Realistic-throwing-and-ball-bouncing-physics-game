import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class HomePage{
    JButton playButton;
    boolean isVisible = true;
    boolean buttonOnScreen = false;
    Screen screen;
    EventTracker tracker;
    
    public HomePage(EventTracker tracker) {
        this.tracker = tracker;
    }
    public void passScreen(Screen screen) {
        this.screen = screen;
    }

    
    public void mainMenuText (Graphics g) {
        //graphics at the start
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.BOLD, 60);
            g.setFont(font);
            g.drawString("Ball Game", 50, 50);

            if (buttonOnScreen == false) {
                homePageButtons();
            }
    }


    public void homePageButtons() {
        //making the home button
        buttonOnScreen = true;

        Main.frame.setLayout(null);
        playButton = button.setButton(200,150,100, 50, "play", Color.red, Color.CYAN,true);
        Main.frame.add(playButton);
        tracker.homePageSetUp(playButton,this);
        
    }

    public void enterGame() {
        //sets all buttons and text to not be visible when the play button is pressed
        isVisible = false;
        playButton.setVisible(isVisible);
        screen.repaint();
        MainGame game = new MainGame(this.screen,this.tracker);

    }
    public void paintBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 18000, 725);
    }
    
}
