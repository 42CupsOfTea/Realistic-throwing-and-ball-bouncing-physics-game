import java.awt.Color;
import java.awt.Graphics;

public class Player {
    private int x,y,height;
    private Color colour;
    private int throwingPositionX;
    private int throwingPositionY;
    Player (int x, int y, int height, Color colour) {

        this.x = x;
        this.y = y;
        this.height = height;
        this.colour = colour;
        throwingPositionX = (int) x + height/2;
        throwingPositionY = (int) y + height;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void paint(Graphics g) {
        g.setColor(colour);
        g.fillRect(x, y, height, height*2);
    }
    public void changeSize() {
        height = height + 15;
    }
    public int getPlayerX() {
        return x;
    }
    public int getPlayerY() {
        return y;
    }
    public int getPlayerHeight() {
        return height;
    }
    public int getPlayerThrowingPositionX() {
        return throwingPositionX;
    }
    public int getPlayerThrowingPositionY() {
        return throwingPositionY;
    }
}
