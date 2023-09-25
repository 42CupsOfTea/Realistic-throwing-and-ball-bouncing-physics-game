public class Camera {
    private int x, y, width, height;

    public Camera(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public int getHeight () {
        return height;
    }
    public int getWidth () {
        return width;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

