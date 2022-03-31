import java.awt.Graphics;
import java.awt.Color;

public class Snowflake{
    private int x;
    private int y;

    public Snowflake(){
        this.x = (int) (Math.random() * Game.screenWidth);
        this.y = (int) (Math.random() * 1000 + 100) * -1;
    }

    public void physics(){
        if(this.y < Game.screenHeight){
            this.y += 2*Game.gameSpeed;
        }
        else{
            this.x = (int) (Math.random() * Game.screenWidth);
            this.y = (int) (Math.random() * 1000 + 100) * -1;
        }
    }

    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(this.x, this.y, 5, 5);
    }

    public void reset(){
        this.x = (int) (Math.random() * Game.screenWidth);
        this.y = (int) (Math.random() * 1000 + 100) * -1;
    }

}