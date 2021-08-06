import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class shot {
    public BufferedImage shot[];
    private int timer;
    public int posX;
    public int posY;
    public int velx;
    public int dano;
    public int interator;


    public shot() {
        posX = Player.getPosX();
        posY = Player.getPosY();
        velx = 0;
        dano = 10;
        timer = 0;

        try {
            shot = new BufferedImage[10];
            for (int i = 0; i < 10; i++) {
            shot[i] = ImageIO.read(getClass().getResource("imgs/navesSprites/ship1/Shot/shot1_" + (i + 1) + ".png"));
            }
        } catch (Exception e) {
            System.out.println("Não foi possível carregar as imagens");
			e.printStackTrace();
        }
    }

    public void handleEvent(boolean k_shot) {
        if(k_shot == true) {
            velx = 2;
            posX = Player.getPosX() + 60;
            posY = Player.getPosY() + 15;
        }else {
            velx = 0;
        }


    }

    public void update(boolean k_shot) {
        timer++;

        posX += velx; 

        if(k_shot == true) {
            if(timer >= 4) {
                timer = 0;
                interator++;
                if(interator == 10){
                    interator = 0;
                }
            }
        }
    }

    public void render(Graphics2D g) { //Desenhando o cenário da fase.
		g.drawImage(shot[interator], posX, posY, null);
	}
}
