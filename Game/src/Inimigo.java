import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Inimigo {
    public BufferedImage boss;
    public static double posY;
	public static double posX;
	public static double velY;
	public static double velX;
	public static double heigth;
	public static double width;

    public Inimigo(){ //Definindo os valores das variáveis no construtor da classe.
        posY = 192;
		posX = 470;
		velY = 0;
		velX = 0;
		width = 96;
		heigth = 96;

        try {
            boss = ImageIO.read(getClass().getResource("imgs/boss/boss2.png"));
        } catch (Exception e) {
            System.out.println("Não foi possível carregar as imagens");
			e.printStackTrace();
        }
    }

    public void handlerEvents() {}

    public void update(){}

    public void render(Graphics2D g) { //Desenha inimigo.
		g.drawImage(boss, (int)posX, (int)posY, null);
	}
}
