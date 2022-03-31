import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class Campfire {

    private int x, y, diameter;
    public Campfire(){
        this.x = 900;
        this.y = 630-50;
        diameter = 125;
    }

    public void melt(){
        if(isWithinRange()){
            Game.player.melt();
        }
    }

    public void physics(){
        melt();
    }

    public boolean isWithinRange(){
        if(Game.player.getX() > this.x - this.diameter && Game.player.getX() < this.x + this.diameter){
            return true;
        }
        return false;
    }

    public void draw(Graphics g){
        g.setColor(new Color(128, 64, 0));
        g.fillRect(x, y, 100, 50);

        g.setColor(new Color(225, 250, 250));
        g.fillRoundRect(x, y, 100, 10, 5, 5);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRoundRect(x+25, y-50, 50, 50, 10, 10);
        g.fillOval(x+12, y-40, 25, 25);

        g.setColor(new Color(255, 95, 31));
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("HOT", x+30, y-20);

    }   

}
