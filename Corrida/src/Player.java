import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Player {

    private BufferedImage spaceShip;
    private int posX;
    private int posY;
    private int velX;
    private int velY;
    private int b;
    private char state;


    public Player(){
        state = 'S';
        b = 0;
        velX = 0;
        velY = 0;

        try {
        spaceShip = ImageIO.read(getClass().getResource("imgs/navesSprites/ship1/Ship1.png"));
        } catch (Exception e) {
            System.out.println("Erro ao carregar as imagens");
        }

    }

    public void handleEvent(boolean k_up, boolean k_down, boolean k_left, boolean k_right, boolean k_shift){
        if(k_shift == true){
            b = 4;
        }else {
            b = 0;
        }
       
        //movimento vertical cima
        if(k_up == true && k_right == true){
            velY = 2 + b;
            velX = 2 + b;
        }else if(k_up == true && k_left == true){
            velY = -2;
            velX = -2;
        }else if(k_down == true && k_right == true){  //movimento vertical baixo
            velY = 2 + b;
            velX = 2 + b;
        }else if(k_down == true && k_left == true){
            velY = 2;
            velX = -2;
        }else {
            velY = 0;
            velX = 0;
        }

        if(k_up == true){
            velY = -2;
        }else if(k_down == true){
            velY = 2;
        }else if(k_left == true){
            velX += -2;
        }else if(k_right == true){
            velX += 2 + b;
        }else {
           velY = 0; 
           velX = 0;
        }

    }

    public void update(){
        posX +=  velX;
	    posY +=  velY;


    }

    public void render(Graphics2D g){
        g.drawImage(spaceShip, posX, posY, null);

    }
}
