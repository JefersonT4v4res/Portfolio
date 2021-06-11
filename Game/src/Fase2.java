import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Fase2 {

	public BufferedImage background;
	public BufferedImage groundLeft;
	public BufferedImage groundRight;
	public BufferedImage roof;
	public BufferedImage spike1;
	public BufferedImage spike2;
	
	public Fase2() {
							 // carrega as imagens a serem utilizadas.
		try {
            background = ImageIO.read(getClass().getResource("imgs/cenario/background2.jpg"));
			groundLeft = ImageIO.read(getClass().getResource("imgs/cenario/CaveGround_Lft.png"));
			groundRight = ImageIO.read(getClass().getResource("imgs/cenario/CaveGround_Rht.png"));
			roof = ImageIO.read(getClass().getResource("imgs/cenario/CaveRoof.png"));
			spike1 = ImageIO.read(getClass().getResource("imgs/cenario/groundSpikesBig.png"));
			spike2 = ImageIO.read(getClass().getResource("imgs/cenario/groundSpikesBig.png"));
		} catch (Exception e) {
			System.out.println("Não foi possível carregar as imagens");
			e.printStackTrace();
		}

		
    }

	public void update(){
        colisao(); //chamada da função.
    }

	//Definindo lógica para colisão.
    public void colisao() {	
		if(Naham.posX < 0){	//sistema de voltar a fase.
			Naham.posX = 1000; //Redefinindo a posição do personagem.
			Game.cenario = 1; //redefinindo cenário
		}else if(Naham.posX + Naham.width > 1100){ //sistema para avançar a fase
            Naham.posX = 12;   //Redefinindo a posição do personagem.
			Game.cenario = 3; //redefinindo cenário
        }

		if(Naham.posY >= 630){	//colisão com os espinhos.
			Game.ESTADO = 'G'; // Trocando o Estado do jogo.
			Naham.posX = 12;  //    Redefinindo a 
			Naham.posY = 352;// posição do personagem.
			Sound.getInstance().yamete();
		}
		//colisão com o chão
		if(Naham.posX + Naham.width > 0 && Naham.posX < (143)){
				if(Naham.posY + Naham.heigth >= 452 && Naham.posY + Naham.heigth <= 452 + 20){	
                    Naham.velY = 0; //Reseta a velocidade Y para zero, parando a atuação da física enquanto o personagem está no chão.
					Naham.ESTADO = 'I'; // O estado do personagem recebe 'I'(Idle) para que a física deixe de ser aplicada.
				}
		}else if(Naham.posX + Naham.width > 940 && Naham.posX < 940 + 150){
			if(Naham.posY + Naham.heigth >= 452 && Naham.posY + Naham.heigth <= 452 + 20){
				Naham.velY = 0;
				Naham.ESTADO = 'I';
			}
		}else{ //Caso o personagem não obedeça nenhuma das condições anteriores, ele está no ar. Com isso aplicamos a física.
		Naham.ESTADO = 'J';
		}
	}

	
	public void render(Graphics2D g) {
		//Desenhando o cenário da fase.
		g.drawImage(background, 0, 0, null); //posX, posY
		g.drawImage(roof, 0, -250, null);
		g.drawImage(spike1, 158, 630, null);	
		g.drawImage(spike2, 687, 630, null);
		g.drawImage(groundLeft, -250, 450, null);
		g.drawImage(groundRight, 904, 450, null);

	}
	
}
