import java.awt.*;


public class Piece {
    private int x, y;
    private String color;

    Piece(int x, int y, String prevColor) {
        this.x = x;
        this.y = y;
        if (prevColor.equals("black")) {
            this.color = "white";
        } else {
            this.color = "black";
        }
    }

    void draw(Graphics g) {
        if (color.equals("black")) {
            g.setColor(Color.black);
        } else {
            g.setColor(Color.white);
        }

        g.fillOval(x- 12, y- 12, 24, 24);
    }
    
    public String getColor() {
        return this.color;
    }
    

}