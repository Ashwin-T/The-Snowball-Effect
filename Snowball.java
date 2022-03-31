import java.awt.Graphics;
import java.awt.Color;

public class Snowball {
    public static double diameter = 25;

    private int x, y;
    
    public Snowball(){
        this.x = (int) (Math.random() * Game.screenWidth);
        this.y = (int) (Math.random() * 1000 + 100) * -1;
    }

    public void physics(){
        if(this.y < Game.screenHeight){
            this.y += 3*Game.gameSpeed;
        }
        else{
            this.x = (int) (Math.random() * Game.screenWidth);
            this.y = (int) (Math.random() * 1000 + 100) * -1;
        }
    }

    public void draw(Graphics g){
        g.setColor(new Color(225, 250, 250));
        g.fillOval(this.x, this.y, (int)diameter, (int)diameter);
    }

    public boolean didCollide(Player player){
        if(this.y >= player.getY() && this.y < player.getY() + player.getDiameter() + 5 && this.x > player.getX() -5 && this.x < player.getX() + player.getDiameter() +5){
            this.x = (int) (Math.random() * Game.screenWidth);
            this.y = (int) (Math.random() * 1000 + 100) * -1;            
            return true;
        }
        return false;
    }

    public void reset(){
        this.x = (int) (Math.random() * Game.screenWidth);
        this.y = (int) (Math.random() * 1000 + 100) * -1;
    }


}
