import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Inimigo {
	public BufferedImage mankey[];
    public BufferedImage atack[];
	public BufferedImage imgAtual;
	private int timer;
	private int indiceAnime;
	private int indiceAtack;
	private char ESTADO;
    public static double posY;
	public static double posX;
	public static double velY;
	public static double velX;
	public int diferencaX;
	public static double heigth;
	public static double width;

    public Inimigo(){ //Definindo os valores das variáveis no construtor da classe.
        posY = 196;
		posX = 470;
		velY = 0;
		velX = 0;
		width = 96;
		heigth = 96;
		timer = 0;
		diferencaX = 0;
		ESTADO = 'I';
		indiceAnime = 0;
		indiceAtack = 0;

        try {
            mankey = new BufferedImage[2];
            for (int i = 0; i < 2; i++) {
				mankey[i] = ImageIO.read(getClass().getResource("imgs/boss/Mankey" + (i+1) + ".png"));
			}
            atack = new BufferedImage[3];
			for (int i = 0; i < 3; i++) {
				atack[i] = ImageIO.read(getClass().getResource("imgs/boss/MankeyAtack" + (i+1) + ".png"));
			}
        } catch (Exception e) {
            System.out.println("Não foi possível carregar as imagens do inimigo");
			e.printStackTrace();
        }
    }

    public void handlerEvents() {  }

    public void update(){
		tatakae();
		timer++; // incrementa o timer que controla a mudança de frames

			if(ESTADO == 'I'){
				if (timer >= 8) { // quanto maior o valor menor a velocidade da troca de quadros
					timer = 0; // reinicializa o timer
					indiceAnime++;// incrementwa o índice do frame
					if (indiceAnime == 2)
					indiceAnime = 0; // correção de limite (reinicialização da animação)
				}
				imgAtual = mankey[indiceAnime];	
			}else if(ESTADO == 'A'){
				if (timer >= 8) { // quanto maior o valor menor a velocidade da troca de quadros
					timer = 0; // reinicializa o timer
					indiceAtack++;// incrementwa o índice do frame
					if (indiceAtack == 3)
					indiceAtack = 0; // correção de limite (reinicialização da animação)
				}	
				imgAtual = atack[indiceAtack];	
			}

			if(diferencaX <= 42 && diferencaX >= -210) {
				ESTADO = 'A';
			} else {
				ESTADO = 'I';
			}

	}

	private void tatakae(){
		diferencaX = (int) (Inimigo.posX - Naham.posX);
		//System.out.println("Distancia: " + diferencaX);
	}

    public void render(Graphics2D g) { //Desenha inimigo.
		g.drawImage(imgAtual, (int)posX, (int)posY, null);
	}
}
