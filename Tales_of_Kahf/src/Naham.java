import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Naham {

	private BufferedImage stopL;
	private BufferedImage stopR;
	private BufferedImage runL[];
	private BufferedImage runR[];
	private BufferedImage jumpL[];
	private BufferedImage jumpR[];
	private BufferedImage atackR[];
	private BufferedImage atackL[];
	private BufferedImage imgAtual;
	public static char ESTADO; // J = jum, R = run, S = stoped, I = idle, A = Atack
	public boolean isRight; // true = right, false = left
	private int timer;
	private int indiceJump;
	private int indiceRun;
	private int indiceAtck;
	public static double posY;
	public static double posX;
	public static double velY;
	public static double velX;
	public static double heigth;
	public static double width;

	public Naham() {
		posY = 353;
		posX = 12;
		velY = 0;
		velX = 0;
		width = 96;
		heigth = 96;
		ESTADO = 'I'; // a princípio, o personagem está parado
		indiceJump = 0;
		indiceRun = 0;
		indiceAtck = 0;
		timer = 0;
		isRight = true; // a princípio o personagem aponta para a direita

		// carrega as imagens (Devemos carregar todas as imagens no contrutor, antes do
		// gameplay se iniciar!)
		try {
			stopL = ImageIO.read(getClass().getResource("imgs/sprites/NahamStoppedLeft.png"));
			stopR = ImageIO.read(getClass().getResource("imgs/sprites/NahamStoppedRight.png"));
			runL = new BufferedImage[4];
			runR = new BufferedImage[4];
			for (int i = 0; i < 4; i++) {
				runL[i] = ImageIO.read(getClass().getResource("imgs/sprites/NahamRunningLeft" + (i + 1) + ".png"));
				runR[i] = ImageIO.read(getClass().getResource("imgs/sprites/NahamRunningRight" + (i + 1) + ".png"));
			}
			jumpL = new BufferedImage[3];
			jumpR = new BufferedImage[3];
			for (int i = 0; i < 3; i++) {
				jumpL[i] = ImageIO.read(getClass().getResource("imgs/sprites/NahamJumpingLeft" + (i + 1) + ".png"));
				jumpR[i] = ImageIO.read(getClass().getResource("imgs/sprites/NahamJumpingRight" + (i + 1) + ".png"));
			}
			atackR = new BufferedImage[3];
			atackL = new BufferedImage[3];
			for (int i = 0; i < 3; i++) {
				atackR[i] = ImageIO.read(getClass().getResource("imgs/sprites/NahamAttackRight" + (i + 1) + ".png"));
				atackL[i] = ImageIO.read(getClass().getResource("imgs/sprites/NahamAtackLeft" + (i + 1) + ".png"));
			}
			imgAtual = stopR; // a primieira imagem é o personagem parado e apontando para a direita
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handlerEvents(boolean k_cima, boolean k_esquerda, boolean k_direita, boolean k_ataque) {

		if (ESTADO == 'J') { // se o personagem estiver pulando
			if (k_direita == true) {
				velX = 3; // velocidade H direita
				isRight = true; // direita
			} else if (k_esquerda == true) {
				velX = -3; // velocidade H esquerda
				isRight = false; // esquerda
			}
		} else { // caso ele não esteja pulando
			// a princípio, o personagem está parado
			velX = 0;
			ESTADO = 'I'; // IDLE (PARADO)
			if (isRight)
				imgAtual = stopR;
			else
				imgAtual = stopL;

			// movimentação horizontal do personagem
			if (k_direita == true) {
				velX = 3; // velocidade H direita
				ESTADO = 'R'; // estado RUN (correndo)
				isRight = true; // direita
				imgAtual = runR[indiceRun];
			} else if (k_esquerda == true) {
				velX = -3; // velocidade H esquerda
				ESTADO = 'R'; // estado RUN (correndo)
				isRight = false; // esquerda
				imgAtual = runL[indiceRun];
			}

			// diparador de pulo do personagem
			if (k_cima == true) { // se pressionou para cima e
				ESTADO = 'J';
				velY = -11; // velocidade para o personagem começar a subir
				if (isRight)
					imgAtual = jumpR[indiceJump];
				else
					imgAtual = jumpL[indiceJump];
			}

			// diparador do atatque do personagem
			if (k_ataque == true) { // se pressionou para cima e
				ESTADO = 'A';
				if (isRight)
					imgAtual = atackR[indiceAtck];
				else
					imgAtual = atackL[indiceAtck];
			}
		}
	}

	public void update() {
		timer++; // incrementa o timer que controla a mudança de frames

		if (ESTADO == 'J') { // se o personagem está pulando
			velY += 0.4; // desacelerador do pulo

			// atualiza o índice da animação de pulo
			if (timer >= 12) { // quanto maior o valor menor a velocidade da troca de quadros
				timer = 0; // reinicializa o timer
				indiceJump++;// incrementwa o índice do frame
				if (indiceJump == 3)
					indiceJump = 0; // correção de limite (reinicialização da animação)
			}

			// atualiza o frame de pulo
			if(isRight) imgAtual = jumpR[indiceJump];
			else imgAtual = jumpL[indiceJump];
		}else if (ESTADO == 'R') { // se o personagem está correndo

			// atualiza o índice da animação do personagem correndo
			if (timer >= 10) { // OBS: diminua esse valor 10 para aumentar a velocidade da animação!!!
				timer = 0; // reinicializa o timer
				indiceRun++;// incrementa o índice do frame
				if (indiceRun == 4)
					indiceRun = 0; // correção de limite (reinicialização da animação)
			}

			// atualiza o frame do personagem correndo
			if(isRight) imgAtual = runR[indiceRun];
			else imgAtual = runL[indiceRun];

		}else if (ESTADO == 'A') { // se o personagem está pulando

			// atualiza o índice da animação de pulo
			if (timer >= 4) { // quanto maior o valor menor a velocidade da troca de quadros
				timer = 0; // reinicializa o timer
				indiceAtck++;// incrementwa o índice do frame
				if (indiceAtck == 3)
				indiceAtck = 0; // correção de limite (reinicialização da animação)
			}

			// atualiza o frame de pulo
			if(isRight) imgAtual = atackR[indiceAtck];
			else imgAtual = atackL[indiceAtck];
		}
		// movimenta o poersonagem
		posX = posX + velX;
		posY = posY + velY;
	}


	public void render(Graphics2D g) {
		g.drawImage(imgAtual, (int)posX, (int)posY, null);
	}
}
