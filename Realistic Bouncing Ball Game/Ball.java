import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    int ballX,ballY;
    private int size;
    private Color colour;

    public Ball(int ballX, int ballY, int size, Color colour) {

        this.ballX = ballX;
        this.ballY = ballY;

        this.colour = colour;
        this.size = size;
    }
    public void paint(Graphics g) {  
        g.setColor(colour);
        g.fillOval(ballX,ballY,size,size);
    }
    public int ballGetX() {
        return ballX;
    }
    public int ballGetY() {
        return ballY;
    }
    public int ballGetSize() {
        return size;
    }
}