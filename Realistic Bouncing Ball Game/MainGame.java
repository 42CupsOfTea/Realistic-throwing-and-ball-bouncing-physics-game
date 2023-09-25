import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.util.Random;
import java.awt.Font;

public class MainGame {
    Random rand = new Random();
    EventTracker tracker;
    GamePhysics physics = new GamePhysics();
    Screen screen;  
    GameLogic logic;
    Ball ball1;
    Ball ball2;
    Player player1;
    Player player2;

    // true = player 1; false = player 2
    private boolean whichPlayerIsIt = true;
    boolean throwingTheBall = false;
    private double [] allValuesNeededForThrow = new double[4];
    private int blowUpCounterP1, blowUpCounterP2 = 0;
    private boolean enteredLoopOfTouchingPlayer = false;
    MainGame(Screen screen, EventTracker tracker) {
        Camera camera = new Camera (0,500,500,500);
        screen.passInGameAndCamera(this, camera);
        this.tracker = tracker;
        logic = new GameLogic(tracker,this);
        this.screen = screen;
        
        
       
        player1 = new Player(rand.nextInt(1600), 662, 25, Color.red);
        player2 = new Player(rand.nextInt(1600), 662, 25, Color.blue);

        int size = 24;
        ball1 = new Ball(player1.getPlayerThrowingPositionX() - size/2 - player1.getPlayerHeight(), player1.getPlayerThrowingPositionY() - size/2, size, Color.red);
        ball2 = new Ball(player2.getPlayerThrowingPositionX() - size/2 - player2.getPlayerHeight(), player2.getPlayerThrowingPositionY() - size/2, size, Color.red);

        //main loop of the program
        Timer timer = new Timer (5, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                screen.update();
                screen.repaint();
                
                if (throwingTheBall) {
                    
                    if (whichPlayerIsIt) {
                        logic.touchingGround(ball1 , 725);
                        if (logic.touchingPlayer(ball1, size,player2) && enteredLoopOfTouchingPlayer == false) {
                            blowUpCounterP2++;
                            player2.changeSize();
                            enteredLoopOfTouchingPlayer = true;
                        }
                    }
                    else {
                        logic.touchingGround(ball2 , 725);
                        if (logic.touchingPlayer(ball2, size, player1) && enteredLoopOfTouchingPlayer == false) {
                            blowUpCounterP1++;
                            player1.changeSize();
                            enteredLoopOfTouchingPlayer = true;
                        }

                    }
                    
                }
                if (tracker.enterThrowingProgram && throwingTheBall == false) {
                    for (int i = 0; i < allValuesNeededForThrow.length; i++) {
                        allValuesNeededForThrow[i] = 0.0;
                    }

                    tracker.enterThrowingProgram = false;
                    if (whichPlayerIsIt) {

                        allValuesNeededForThrow = logic.calculateThrow(player1);
                        allValuesNeededForThrow = logic.getCorrectNumbersForThrow(allValuesNeededForThrow);
                        throwingTheBall = logic.validThrow;

                    }
                    else {

                        allValuesNeededForThrow = logic.calculateThrow(player2);
                        allValuesNeededForThrow = logic.getCorrectNumbersForThrow(allValuesNeededForThrow);
                        throwingTheBall = logic.validThrow;
                        


                    }
                }  
            
            }
            
        });
        timer.start();
    }
    public void resetBall () {
        logic.stopMoving = false;
        tracker.enterThrowingProgram = false;
        throwingTheBall = false;
        enteredLoopOfTouchingPlayer = false;

        if (whichPlayerIsIt) {
            ball1.ballY = player1.getPlayerThrowingPositionY() - ball1.ballGetSize()/2;
            ball1.ballX = player1.getPlayerThrowingPositionX() - ball1.ballGetSize()/2 - player1.getPlayerHeight();
            whichPlayerIsIt = false;
        }
        else {
            ball2.ballY = player2.getPlayerThrowingPositionY() - ball2.ballGetSize()/2;
            ball2.ballX = player2.getPlayerThrowingPositionX() - ball2.ballGetSize()/2 - player2.getPlayerHeight();
            whichPlayerIsIt = true;
        }
        
    }
    public void GetFrictionValuesForThrow() {
        allValuesNeededForThrow = logic.changeNumbersForFriction(allValuesNeededForThrow);
    }
    public boolean getIsBallBeingThrown() {
        return throwingTheBall;
    }
    public boolean getWhichPlayerItIs() {
        return whichPlayerIsIt;
    }
    public double [] getArrayOfThrowingInformation() {
        return allValuesNeededForThrow;
    }
    public void displayStats(Graphics g) {
        if (tracker.isMouseDragged) {
            g.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.BOLD, 20);
            g.setFont(font);
            g.drawString("power = " + getPower() + " angle = "  + getAngle(), 10, 50);
        }
            
    }
    public int getPower() {
        
        int power = 0;

        if (whichPlayerIsIt) {
            double differenceInX = player1.getPlayerThrowingPositionX() - tracker.getMouseX();
            double differenceInY = player1.getPlayerThrowingPositionY() - tracker.getMouseY();
            power = (int) Math.sqrt(differenceInX * differenceInX + differenceInY * differenceInY);
        }
        else {
            double differenceInX = player2.getPlayerThrowingPositionX() - tracker.getMouseX();
            double differenceInY = player2.getPlayerThrowingPositionY() - tracker.getMouseY();
            power = (int) Math.sqrt(differenceInX * differenceInX + differenceInY * differenceInY);
        }

        if (power >= 200) {
            power = 10;
        } 

        else if (power <= 70) {
            power = 0;
        } 

        else {
            power = (int) power/ 25;
        }
        
        return power;
    }
    public int getAngle() {
        int angle = 0;
        double distanceOfMouseAndPlayer;
        double differenceInXMouseAndPlayer;
        double differenceInYMouseAndPlayer;
        double distanceBetweenBothPoints;
        if (whichPlayerIsIt) {
            differenceInXMouseAndPlayer = player1.getPlayerThrowingPositionX() - tracker.getMouseX();
            differenceInYMouseAndPlayer = player1.getPlayerThrowingPositionY() - tracker.getMouseY();

            distanceOfMouseAndPlayer = Math.sqrt(differenceInXMouseAndPlayer * differenceInXMouseAndPlayer + differenceInYMouseAndPlayer * differenceInYMouseAndPlayer);

            distanceBetweenBothPoints = Math.sqrt((player1.getPlayerThrowingPositionX() - tracker.getMouseX()) * (player1.getPlayerThrowingPositionX() - tracker.getMouseX()) +
                                                         (player1.getPlayerThrowingPositionY() + distanceOfMouseAndPlayer - tracker.getMouseY()) * (player1.getPlayerThrowingPositionY() + distanceOfMouseAndPlayer  - tracker.getMouseY()));
        }
        else {
            differenceInXMouseAndPlayer = player2.getPlayerThrowingPositionX() - tracker.getMouseX();
            differenceInYMouseAndPlayer = player2.getPlayerThrowingPositionY() - tracker.getMouseY();

            distanceOfMouseAndPlayer = Math.sqrt(differenceInXMouseAndPlayer * differenceInXMouseAndPlayer + differenceInYMouseAndPlayer * differenceInYMouseAndPlayer);

            distanceBetweenBothPoints = Math.sqrt((player2.getPlayerThrowingPositionX() - tracker.getMouseX()) * (player2.getPlayerThrowingPositionX() - tracker.getMouseX()) +
                                                         (player2.getPlayerThrowingPositionY() + distanceOfMouseAndPlayer - tracker.getMouseY()) * (player2.getPlayerThrowingPositionY() + distanceOfMouseAndPlayer  - tracker.getMouseY()));

            
        }
        double c = distanceBetweenBothPoints;
        double a = distanceOfMouseAndPlayer;
        double b = distanceOfMouseAndPlayer;
        
        angle = (int)Math.toDegrees(Math.acos((a*a + b*b - c*c) / (2*a*b)));
        return angle;
    }
    
    
  
}
