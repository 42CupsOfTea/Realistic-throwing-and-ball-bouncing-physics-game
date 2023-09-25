import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



import java.awt.Color;
import java.awt.Font;

public class Screen extends JPanel{
    HomePage home;
    MainGame game;
    Camera camera;

    private Image backGroundImage;

    Screen (HomePage home) {
        this.home = home;
        backGroundImage = new ImageIcon("C:\\Users\\fussi\\OneDrive\\Desktop\\Realistic Bouncing Ball Game\\BackGround.png").getImage();
    }

    public void passInGameAndCamera (MainGame game, Camera camera) {
        this.game = game;
        this.camera = camera;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backGroundImage, 0, 0, 1800, 1000, null);
        Graphics2D g2d = (Graphics2D) g.create();
        

        if (home.isVisible) {

            home.mainMenuText(g2d);    
        }
        else {

            //g2d.translate(-camera.getX(), -camera.getY());

            game.displayStats(g2d);
            home.paintBorder(g2d);
            paintBalls(g2d);
            paintPlayers(g2d);
        }

    }
    public void update() {
        updateBallsLocation();
        updateCameraLocation();
    }

    public void updateCameraLocation () {
        if (game.getIsBallBeingThrown()) {
            
        
            if (game.getWhichPlayerItIs()) {
                int targetX = game.ball1.ballGetX() + 500;
                int targetY = game.ball1.ballGetY() + 500;
                camera.setPosition(targetX, targetY);
                        

            }
            else {
                int targetX = game.ball2.ballGetX()+ 250;
                int targetY = game.ball2.ballGetY()+ 250;
                camera.setPosition(targetX, targetY);
            }
        
        }
    }
    
    public void paintPlayers(Graphics g2d) {
        
        game.player1.paint(g2d); // Paint player 1
        game.player2.paint(g2d); // Paint player 2



    }
    public void updateBallsLocation () {
        if (game.getIsBallBeingThrown()) {
            double [] allValuesNeededForThrow = game.getArrayOfThrowingInformation();
            System.out.println(game.ball1.ballY);
            if (game.getWhichPlayerItIs()) {
                game.ball1.ballX += allValuesNeededForThrow[0];
                game.ball1.ballY += allValuesNeededForThrow[1];
                game.GetFrictionValuesForThrow();
            }
            else {
                game.ball2.ballX += allValuesNeededForThrow[0];
                game.ball2.ballY += allValuesNeededForThrow[1];
                game.GetFrictionValuesForThrow();


            }
        }
    }
    public void paintBalls(Graphics g) {
        game.ball1.paint(g);
        game.ball2.paint(g);            
    }

}
