import java.awt.Graphics;
import java.awt.Color;

public class Player{

    private int x, y;
    public static int diameter = 150;
    private int[] speed = {16, 0};
    public static boolean moving = false;
    public static boolean alive = true;

    Player(){
        this.x = 400;
        this.y = 630-diameter;
    }


    public void draw(Graphics g){
        
        g.setColor(Color.WHITE);
        g.fillOval(x, y, diameter, diameter);

        //hitbox
        // g.drawRect(x, y, diameter, diameter);
    }

    public void physics(){
        speed[1] = 0;
        this.y = 630-diameter;
    }

    public void move(int direction){
        
        if(direction == 0){
            this.x = this.x -  this.speed[0];
            diameter -= 0.05;

        }
        else if(direction == 1){
            this.x = this.x +  this.speed[0];
            diameter -= 0.05;    
        }

    }

    public void addSnow(){
        if(Game.gameSpeed == 7){
            diameter += 10;
        }
        else{
            diameter += 5 * Game.gameSpeed;
        }
    }

    public void reset(){
        diameter = 175;
        this.x = 400;
        this.y = 630-diameter;
    }

    public void melt(){
        diameter -= 0.025;   
        this.y = 630-diameter;
    }

    public int getDiameter(){
        return diameter;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
