import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Game extends JPanel implements Runnable{

    public static final int TILE_SIZE = 64;
    public static final int gravity = 1;
    public static final int FPS = 60;

    public static int scale = 1;

    public static int cols = 16, rows = 12;

    public static int screenWidth = TILE_SIZE * scale * cols, screenHeight = TILE_SIZE * scale * rows;
    
    static Thread gameThread;
    static KeyHandler keyHandler = new KeyHandler();;

    public static Player player = new Player();

    public static boolean running = false;

    public static Snowflake[] snowStorm = new Snowflake[250];
    public static Snowball[] snowballs = new Snowball[10];
    public static Enemy[] enemies= new Enemy[5];

    public static int gameSpeed = 1;

    public static int score = 0;
    public static int highscore = 0;
    public static boolean lose = false, pause = false;

    private Campfire campfire;

    private static FileWriter writer;
    private static BufferedImage winImage, loseImage, pauseImage;




    public Game() {
        System.out.println(screenWidth + ", " + screenHeight);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(0, 204, 255));

        gameThread = new Thread(this);
        gameThread.start();

        player = new Player();
        campfire = new Campfire();

        for (int i = 0; i < snowStorm.length; i++) {
            snowStorm[i] = new Snowflake();
        }

        for (int i = 0; i < snowballs.length; i++) {
            snowballs[i] = new Snowball();
        }

        for (int i = 0; i < enemies.length; i++) {
            enemies[i] = new Enemy();
        }

        try {
			Scanner scan = new Scanner(new FileReader("score/score.txt"));
			
			//reads one word at a time
			while (scan.hasNext()){
			   highscore = scan.nextInt();
			}
			//reads one line at a time
		}
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try{
            winImage = ImageIO.read(this.getClass().getResource("images/winSnowballEffect.png"));
            loseImage = ImageIO.read(this.getClass().getResource("images/loseSnowballEffect.png"));
            pauseImage = ImageIO.read(this.getClass().getResource("images/pauseSnowballEffect.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }


        this.addKeyListener(keyHandler);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }   

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(!running){
            g.setColor(Color.WHITE);
            g.drawImage(winImage, 0, 0, screenWidth, screenHeight, null);
            g.dispose();
        }
        else if(player.getDiameter() > 0 && !pause){
            g.setColor(Color.WHITE);

       
            for (int i = 0; i < snowStorm.length; i++) {
                snowStorm[i].draw(g);
            }
            

            for (int i = 0; i < snowballs.length; i++) {
                snowballs[i].draw(g);
            }

            for (int i = 0; i < enemies.length; i++) {
                enemies[i].draw(g);
            }
            
            campfire.draw(g);

            g.setColor(new Color(155, 118, 83));
            g.fillRect(0, 630, screenWidth, screenHeight-630);  
            g.setColor(new Color(225, 250, 250));
            g.fillRect(0, 630, screenWidth, screenHeight-725);  

            player.draw(g);

            g.drawString("High Score: " + highscore, 10, screenHeight - 10);
            g.drawString("Score: " + score, 10, screenHeight - 30);
            g.drawString("Snow Storm Severity: " + gameSpeed, screenWidth - 235, screenHeight - 10);

            g.dispose();
        }
        else if(pause){
            g.setColor(Color.WHITE);
            g.drawImage(pauseImage, 0, 0, screenWidth, screenHeight, null);
            g.dispose();
        }
        else if(lose){
            g.setColor(Color.black);
            g.drawImage(loseImage, 0, 0, screenWidth, screenHeight, null);
            g.drawString("Score: " + score, 10, screenHeight - 10);
            g.drawString("High Score: " + highscore, 10, screenHeight - 30);
            g.dispose();
        }
    }

    public static void reset(){
        player.reset();

        for (int i = 0; i < snowStorm.length; i++) {
            snowStorm[i].reset();
        }

        for (int i = 0; i < snowballs.length; i++) {
            snowballs[i].reset();
        }

        for (int i = 0; i < enemies.length; i++) {
            enemies[i].reset();
        }


        score = 0;
        gameSpeed = 1;//change to 1
        
        running = true;
        pause = false;
        lose = false;
    }


    public void update(){
        if(running && !pause){

            if(player.getDiameter() <= 0){
                lose = true;
            }

            if(score % 15 == 0 && score != 0 && !lose){
                gameSpeed++;
                score++;
            }

            if(lose){
                try {
                    writer = new FileWriter("score/score.txt");
                    writer.write(Integer.toString(highscore));
                    writer.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                player.melt();
            }

            if(score > highscore){
                highscore = score;
                // try {
                //     writer = new FileWriter("score/score.txt");
                //     writer.write(Integer.toString(highscore));
                //     writer.close();
                // }
                // catch (Exception e) {
                //     e.printStackTrace();
                // }
            }

            player.physics();
            campfire.physics();
        
            for (int i = 0; i < snowStorm.length; i++) {
                snowStorm[i].physics();
            }
            for (int i = 0; i < snowballs.length; i++) {
                snowballs[i].physics();
                if(snowballs[i].didCollide(player)){
                    player.addSnow();
                }
            }

            for (int i = 0; i < enemies.length; i++) {
                enemies[i].physics();
                if(enemies[i].didCollide(player)){
                    lose = true;
                }
            }
            

            //player
            if(keyHandler.isMovementKeyPressed()){
                
                if(keyHandler.isLeftPress()){
                    Player.moving = true;
                    player.move(0);
                }

                else if(keyHandler.isRightPress()){
                    Player.moving = true;
                    player.move(1);   
                }  
                else if(keyHandler.isEscPress()){
                    pause = !pause;
                }                
            }
            else{
                Player.moving = false;
            }
        }

        if(keyHandler.isEnterPress()){
            running = true;
        }
    }      

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        // long timer = 0;
        // int drawCount = 0;

        while(gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            // timer += (currentTime - lastTime);
            lastTime = currentTime;

            while(delta >= 1) {
                update();
                repaint();    
                delta--;
                // drawCount++;
            }

            // if(timer > 1000000000){
            //     System.out.println("FPS: " + drawCount);

            //     drawCount = 0;
            //     timer = 0;
            // }
            
        }
    }
      
}


