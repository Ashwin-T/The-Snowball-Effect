import javax.swing.JFrame;

public class Main{
    public static void main(String args[]) {
        JFrame frame = new JFrame("The Snowball Effect");
        Game game = new Game();

        frame.add(game);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
