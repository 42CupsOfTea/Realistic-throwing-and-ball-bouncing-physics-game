public class GameLogic {
    EventTracker tracker;
    MainGame game;
    boolean validThrow = true;
    double velocity; 
    double xVelocity;
    double yVelocity;
    double counter = 0;
    double loopCounter =0;
    boolean stopMoving = false;
    int bounceCount = 0;
    boolean touching;
    GameLogic(EventTracker tracker, MainGame game) {
        this.tracker = tracker;
        this.game = game;
    }
    public boolean touchingPlayer(Ball ball, int size, Player player) {
        boolean touching = false;
        if (ball.ballGetX() > (player.getPlayerX() - ball.ballGetSize()) && ball.ballGetX() < player.getPlayerX() + player.getPlayerHeight()) {
            if (ball.ballGetY() > player.getPlayerY() - ball.ballGetSize() && ball.ballGetY() < player.getPlayerY() + player.getPlayerHeight()*2) {  
                touching = true;
            }

        }
        return touching;
    }

    public void touchingGround (Ball ball , int ground) {
        if (ball.ballGetY() + ball.ballGetSize() > ground && loopCounter > 3) {
            bounceCount++;
            xVelocity = xVelocity * 0.8;
            yVelocity = yVelocity * - 0.6;

            if (loopCounter < 35 && bounceCount > 5) {
                bounceCount = 0;
                stopMoving = true;
            }

            loopCounter = 0;
            
        }
        
    }



    public double[] changeNumbersForFriction(double [] throwingValues) {
        if (stopMoving == false) {
            counter += 0.8;
            loopCounter++;

            if (counter > 60) {
                counter = 60;
            }

            throwingValues[0] = xVelocity * 0.999;
            throwingValues[1] = yVelocity + (0.003 * counter);
            xVelocity = throwingValues[0];
            yVelocity = throwingValues[1];
            return throwingValues;
        }

        else {

            throwingValues[0] = 0; 
            throwingValues[1] = 0;
            game.resetBall();              
            return throwingValues;
        }
        
    }
  
    public double[] getCorrectNumbersForThrow(double[] throwingValues) {

        if (throwingValues[2] >= 200) {

            throwingValues[2] = 10;
            throwingValues[0] = Math.round(10 * Math.cos(Math.toRadians(throwingValues[3])));
            throwingValues[1] = Math.round(10 * Math.sin(Math.toRadians(throwingValues[3])));
            validThrow = true;
        } else if (throwingValues[2] <= 70) {
            validThrow = false;
        } else {


            double velocity = throwingValues[2] / 25.0;
            throwingValues[0] = Math.round(velocity * Math.cos(Math.toRadians(throwingValues[3])));
            throwingValues[1] = Math.round(velocity * Math.sin(Math.toRadians(throwingValues[3])));
            throwingValues[2] = velocity;

            validThrow = true;
        }
        velocity = throwingValues[2];
        xVelocity = throwingValues[0];
        yVelocity = throwingValues[1];
        return throwingValues;
    }

    public double[] calculateThrow(Player player) {
        double[] allValuesNeededForThrow = new double[4];

        double differenceInX = player.getPlayerThrowingPositionX() - tracker.getMouseX();
        double differenceInY = player.getPlayerThrowingPositionY() - tracker.getMouseY();

        double angle = Math.toDegrees(Math.atan2(differenceInY, differenceInX));
        double distanceBetweenMouseAndPlayer = Math.sqrt(differenceInX * differenceInX + differenceInY * differenceInY);

        allValuesNeededForThrow[0] = differenceInX;
        allValuesNeededForThrow[1] = differenceInY;
        allValuesNeededForThrow[2] = distanceBetweenMouseAndPlayer;
        allValuesNeededForThrow[3] = angle;

        return allValuesNeededForThrow;
    }
}
