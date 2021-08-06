import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class faseOne {
    public BufferedImage background;
    public BufferedImage background2;
    public int velx;
    public int posX;
    public int posY;

    public faseOne() {
       velx = 0;
       posX = 0;
       posY = 0;

        try {
            background = ImageIO.read(getClass().getResource("imgs/fase1/background.jpg"));
            background2 = ImageIO.read(getClass().getResource("imgs/fase1/background.jpg"));
			} catch (Exception e) {
			System.out.println("Não foi possível carregar as imagens");
			e.printStackTrace();
		}
    }

    public void handleEvent(boolean k_left, boolean k_right){
      if(k_left == true || k_right == true) {
         velx = -1;
       }else {
         velx = 1;
       }
   }

   public void update() {
       posX = posX + (velx * (Player.getVelX()/2));
   }

    public void render(Graphics2D g) { //Desenhando o cenário da fase.
		g.drawImage(background, posX, posY, null);
    g.drawImage(background2, posX+1000, posY, null);
    }
}
