import java.awt.Graphics;
import java.awt.Color;
public class Ramp {
    private int x, y;
    private int height;
    private int width;

    public Ramp(){
        this.x = 0;
        this.height = 150;
        this.width = 50;
        this.y = 630 - height;
        
    }

    public void physics(){
        
    }

    public void draw(Graphics g){
        g.setColor(new Color(142, 142, 142));
        g.fillRect(x, y, 2*width, height);
        int[] xPoints = {x+2*width, x+5*width, x+2*width};
        int[] yPoints = {y, 630, 630};

        g.fillPolygon(xPoints, yPoints, 3);
        
        g.setColor(new Color(225, 250, 250));
        g.fillRoundRect(x, y, 2*width, 15, 5, 5);

        
    }

    public boolean isOnRamp(){
        if(Game.player.getY() > this.y && Game.player.getY() < this.y + this.height && Game.player.getX() > this.x && Game.player.getX() < this.x + 200){
            return true;
        }
        return false;
    }
}
