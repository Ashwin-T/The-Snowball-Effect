import java.awt.Graphics;
import java.awt.Color;

public class Enemy {
    public static int diameter = 35;

    private int x, y;
    
    public Enemy(){
        this.x = (int) (Math.random() * Game.screenWidth);
        this.y = (int) (Math.random() * 1000 + 100) * -1;
    }

    public void physics(){
        if(this.y < 630){
            this.y += 3*Game.gameSpeed;
        }
        else{
            this.x = (int) (Math.random() * Game.screenWidth);
            this.y = (int) (Math.random() * 1000 + 100) * -1;
            if(!Game.lose){
                Game.score++;
            }
        }
    }

    public void draw(Graphics g){
        g.setColor(new Color(255, 255, 0));
        g.fillOval(this.x, this.y, diameter, diameter);
    }

    public boolean didCollide(Player player){
        if(this.y > player.getY() && this.y < player.getY() + player.getDiameter() && this.x > player.getX() && this.x < player.getX() + player.getDiameter()){
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
