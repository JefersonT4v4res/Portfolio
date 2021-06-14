import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Fase1{
	//Declaração das variáveis.
    public BufferedImage background;
    public BufferedImage ground;
	public BufferedImage groundBig;
	public BufferedImage roof;
	public BufferedImage spikes;
	public BufferedImage hill;
	public BufferedImage platform;
    

    public Fase1() { 
	
					// carrega as imagens a serem utilizadas.
		try {
            background = ImageIO.read(getClass().getResource("imgs/cenario/BgCave.png"));
			ground = ImageIO.read(getClass().getResource("imgs/cenario/groundRedu.png"));
			groundBig = ImageIO.read(getClass().getResource("imgs/cenario/groundReduMax.png"));
			roof = ImageIO.read(getClass().getResource("imgs/cenario/CaveRoof.png"));
			spikes = ImageIO.read(getClass().getResource("imgs/cenario/groundMaxSpikes.png"));
			hill = ImageIO.read(getClass().getResource("imgs/cenario/hill.png"));
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
		if(Naham.posX < 0){  //Colisão com a parede da Esquerda
			Naham.posX += 3; //Desfaz o movimento do personagem.
		}else if (Naham.posX + Naham.width > 1100){ //sistema para avançar a fase
			Naham.posX = 12;   //Redefinindo a posição do personagem.
			Game.cenario = 2; //redefinindo cenário.
		}

		if(Naham.posY >= 541){ 	//colisão espinhos.
			Game.ESTADO = 'G'; // Trocando o Estado do jogo.
			Naham.posX = 12;  //    Redefinindo a 
			Naham.posY = 352;// posição do personagem.
			Sound.getInstance().died();
		}
		//colisão com o morrinho
		if(Naham.posX + Naham.width >= (435+45) && Naham.posX < (435 + 80)){
            if(Naham.posY + Naham.heigth >= 370 && Naham.posY + Naham.heigth <= 370 + 10){	
              Naham.velY = 0; //Reseta a velocidade Y para zero, parando a atuação da física enquanto o personagem está no chão.
              Naham.ESTADO = 'I'; // O estado do personagem recebe 'I'(Idle) para que a física deixe de ser aplicada.
            }	
        }else{
		    Naham.ESTADO = 'J'; //Caso o personagem não obedeça nenhuma das condições anteriores, ele está no ar. Com isso aplicamos a física.
        }
		//Na colisão com o chão seguimos a mesma lógica para a colisão com o morrinho.
		if(Naham.posX + Naham.width > 0 && Naham.posX < (0 + 163)){
				if(Naham.posY + Naham.heigth >= 450 && Naham.posY + Naham.heigth <= 450 + 20){	
                    Naham.velY = 0;
					Naham.ESTADO = 'I';
				}
		}else if(Naham.posX + Naham.width > 380 && Naham.posX < 380 + 135){
			if(Naham.posY + Naham.heigth >= 450 && Naham.posY + Naham.heigth <= 450 + 20){
				Naham.velY = 0;
				Naham.ESTADO = 'I';
			}
		}else if(Naham.posX + Naham.width > 735 && Naham.posX < 735 + 400){
			if(Naham.posY + Naham.heigth >= 450 && Naham.posY + Naham.heigth <= 450 + 20){
				Naham.velY = 0;
				Naham.ESTADO = 'I';
				}
	}else{
		Naham.ESTADO = 'J';
	}

	}



    public void render(Graphics2D g) { //Desenhando o cenário da fase.
		g.drawImage(background, 0, -51, null);
		g.drawImage(roof, 0, -254, null);
		g.drawImage(ground, 0, 450, null);
		g.drawImage(hill, 435, 370, null);
		g.drawImage(ground, 350, 448, null);
		g.drawImage(groundBig, 704, 448, null);
		g.drawImage(spikes, 190, 541, null);
		g.drawImage(spikes, 545, 545, null);
	}

}
