import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Player {

    private BufferedImage spaceShip;
    private static int posX;
    private static int posY;
    private static int velX;
    private int velY;
    private int b;
    private int interator;
    private int timer;
    private BufferedImage on[];
    private BufferedImage boost[];
    private char state; //O (on), B (boost), E (explosion)


    public Player(){
        state = 'O';
        timer = 0;
        b = 0;
        interator = 0;
        velX = 0;
        velY = 0;

        try {
        spaceShip = ImageIO.read(getClass().getResource("imgs/navesSprites/ship1/Ship1.png"));
            on = new BufferedImage [4];
            for (int i = 0; i < 4; i++) {
				on[i] = ImageIO.read(getClass().getResource("imgs/navesSprites/ship1/flight/Normal_flight/Sp1_normalFlight" + (i + 1) + ".png"));
			}
            boost = new BufferedImage [4];
            for (int i = 0; i < 4; i++) {
                boost[i] = ImageIO.read(getClass().getResource("imgs/navesSprites/ship1/flight/Turbo_flight/Sp1_turbolFlight" + (i + 1) + ".png"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar as imagens");
        }

    }

    public void handleEvent(boolean k_up, boolean k_down, boolean k_left, boolean k_right, boolean k_shift){
        if(k_shift == true){
            b = 4;
            state = 'B';
        }else {
            b = 0;
            state = 'O';
        }
       
        //movimento diagonal cima
        if(k_up == true && k_right == true){
            velY = 2 + b;
            velX = 2 + b;
        }else if(k_up == true && k_left == true){
            velY = -2;
            velX = -2;
        }else if(k_down == true && k_right == true){  //movimento diagonal baixo
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
        timer++;

        posX +=  velX;
	    posY +=  velY;

        if(state == 'O') {
            if(timer >= 10) {
                timer = 0;
                interator++;
                if(interator == 4){
                    interator = 0;
                }
            }
        }else if(state == 'B') {
            if(timer >= 10) {
                timer = 0;
                interator++;
                if(interator == 4){
                    interator = 0;
                }
            }
        }
    }

    public static int getPosX() {
        return posX;
    }

    public static int getPosY(){
        return posY;
    }

    public static int getVelX() {
        return velX;
    }


    public void render(Graphics2D g){
        if(state == 'O'){
        g.drawImage(on[interator], posX-15, posY+15, null);
        } else if(state == 'B') {
        g.drawImage(boost[interator], posX-25, posY, null);
        }
        g.drawImage(spaceShip, posX, posY, null);

    }
}
