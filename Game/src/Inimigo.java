import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Inimigo {
    public BufferedImage mummy;
    public static double posY;
	public static double posX;
	public static double velY;
	public static double velX;
	public static double heigth;
	public static double width;

    public Inimigo(){ //Definindo os valores das variáveis no construtor da classe.
        posY = 352;
		posX = 700;
		velY = 0;
		velX = 0;
		width = 96;
		heigth = 96;

                   //carrega a imagem.
        try {
            mummy = ImageIO.read(getClass().getResource("imgs/boss/Mummy.png"));
        } catch (Exception e) {
            System.out.println("Não foi possível carregar as imagens");
			e.printStackTrace();
        }
    }

    public void handlerEvents() {}

    public void update(){}

    public void render(Graphics2D g) { //Desenha inimigo.
		g.drawImage(mummy, (int)posX, (int)posY, null);
	}
}
