import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class platform {

    public BufferedImage platform;
	public static double posX;
    public static int posY;
	public double velX;


    public platform() { //Definindo o construtor da fase.
        posX = 200;              //   Definição
        posY = 450;             //       das
        velX = 2.2;            //     variáveis.

         //Carregando a imagem a ser utilizada.
        try {
			platform = ImageIO.read(getClass().getResource("imgs/cenario/platform.png"));
		} catch (Exception e) {
			System.out.println("Não foi possível carregar as imagens");
			e.printStackTrace();
		}
    }

    public void update(){   //Definindo movimento
        posX += velX;   //Definindo movimento da plataforma.
        floatPlat();   //Chamada da função de movimento.
        colisao();    //Chamada da função de colisão.
    } 


    public void floatPlat() {

        if(posX <= 180){        //Testa posição 
			velX *= -1;     //Redefine o movimento.
		}else if(posX + 126 >= 904){    //Testa posição.
			velX *= -1;              //Redefine o movimento.

		}   //Essa função é responsável por fazer a plataforma se mover para os lados, indo e voltando.
	}

    public void colisao(){  //Teste de colisão com a plataforma.
        if(Naham.posX + Naham.width > (posX+40) && Naham.posX < (posX + 85)){
            if(Naham.posY + Naham.heigth >= posY && Naham.posY + Naham.heigth <=  posY + 10){	
                Naham.velY = 0;     //Reseta a velocidade Y para zero, parando a atuação da física enquanto o personagem está no chão.
                Naham.posX += velX;  //Estabelece a mesma velocidade de movimento X para o personagem fazendo o personagem se mover junto 
                                    //com a plataforma e evitando que ela se mova sozinha.
                Naham.posY = 354;  //Redefinindo a posição Y do personagem
                Naham.ESTADO = 'I'; // O estado do personagem recebe 'I'(Idle) para que a física deixe de ser aplicada.
            }		
        }
    }

    public void render(Graphics2D g) {
        g.drawImage(platform, (int)posX, posY, null);  //Apenas a posX é convertida em inteiro pois é a 
                                                      //única variável que é atualizada com um valor em double da velocidade.
	}
}
