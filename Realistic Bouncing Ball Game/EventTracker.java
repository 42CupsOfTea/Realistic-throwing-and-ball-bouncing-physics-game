import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

public class EventTracker implements ActionListener, MouseMotionListener, MouseListener{
    
    
    JButton playButton;
    HomePage homePage;
    boolean isMouseDragged = false;
    boolean enterThrowingProgram = false;
    private int mouseX,mouseY;
    
    EventTracker() {
        

        Main.frame.addMouseMotionListener(this);
        Main.frame.addMouseListener(this);


    }
    public void homePageSetUp (JButton playButton, HomePage homePage) {
        this.playButton = playButton;
        this.homePage = homePage;
        playButton.addActionListener(this);
    }

    public void actionPerformed (ActionEvent e) {
        if (e.getSource() == playButton) {
            homePage.enterGame();
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseY = e.getY();
        mouseX = e.getX();
        isMouseDragged = true;
        //System.out.println("moo");
    }

    public void mouseReleased(MouseEvent e) {

        if (isMouseDragged) {
            isMouseDragged = false;
            enterThrowingProgram = true;
        }
    }

    public int getMouseX() {
        return mouseX;
    }
    public int getMouseY() {
        return mouseY;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
