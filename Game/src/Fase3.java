import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Fase3 {

    public BufferedImage background;
	public BufferedImage ground;
	public BufferedImage roof;
	public BufferedImage platform1;
	public BufferedImage platform2;

    public Fase3(){
							// carrega as imagens a serem utilizadas.
        try {
            background = ImageIO.read(getClass().getResource("imgs/cenario/bgCave3.jpg"));
			ground = ImageIO.read(getClass().getResource("imgs/cenario/CaveGround.png"));
			roof = ImageIO.read(getClass().getResource("imgs/cenario/CaveRoof.png"));
			platform1 = ImageIO.read(getClass().getResource("imgs/cenario/platform.png"));
			platform2 = ImageIO.read(getClass().getResource("imgs/cenario/platform.png"));

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
		if(Naham.posX < 0){ //sistema para voltar a fase
			Naham.posX = 1000; //Redefinindo a posição do personagem.
			Game.cenario = 2; //redefinindo cenário.
		}else if(Naham.posX + Naham.width > 1100){ //Colisão com a parede da Direita
            Naham.posX -= 3; //Desfaz o movimento do personagem.
        }

		//Seguindo a mesma linha de raciocínio das outras fases sobre o sistena de colisão.
        if(Naham.posX + Naham.width >= (190+45) && Naham.posX < (190 + 80)){
            if(Naham.posY + Naham.heigth >= 320 && Naham.posY + Naham.heigth <= 320 + 10){	
              Naham.velY = 0;
                Naham.ESTADO = 'I';
            }	
        }else{
		    Naham.ESTADO = 'J';
        }
        if(Naham.posX + Naham.width > (704+30) && Naham.posX < (704 + 85)){
            if(Naham.posY + Naham.heigth >= 320 && Naham.posY + Naham.heigth <=  320 + 10){	
              Naham.velY = 0;
                Naham.ESTADO = 'I';
            }
        }		
		
		if(Naham.posX + Naham.width > 0 && Naham.posX < (0 + 1100)){
			if(Naham.posY + Naham.heigth >= 450 && Naham.posY + Naham.heigth <= 450 + 20){	
                Naham.velY = 0;
			    Naham.ESTADO = 'I';
			}
		}else{
		    Naham.ESTADO = 'J';
        }
        
	}



    public void render(Graphics2D g) { //Desenhando o cenário da fase.
		g.drawImage(background, 0, -70, null);
		g.drawImage(ground, 0, 450, null);
		g.drawImage(platform1, 190, 320, null);
		g.drawImage(platform2, 704, 320, null);
	}
}
