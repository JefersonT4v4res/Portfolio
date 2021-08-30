import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Estados {
   
    public BufferedImage splashState;
    public BufferedImage startMenu;
	public BufferedImage gameOver;
	public BufferedImage pause;
    public BufferedImage estado;

    public Estados(){
                                        // carrega as imagens a serem utilizadas.
        try{
            splashState = ImageIO.read(getClass().getResource("imgs/estados/Splash1.jpg")); 
            startMenu = ImageIO.read(getClass().getResource("imgs/estados/MenuState.jpg"));
            gameOver = ImageIO.read(getClass().getResource("imgs/estados/GameOverState.jpg"));
            pause = ImageIO.read(getClass().getResource("imgs/estados/PauseMenu.jpg"));

            estado = splashState; // O estado SPLASH é o primeiro a ser executado.
        }catch (Exception e){
            System.out.println("Não foi possível carregar as imagens");
			e.printStackTrace();
        }
    }

    public void update(){
        //Definindo a imagem a ser desenhada.
        if(Game.ESTADO == 'M'){
            estado = startMenu;
        }else if(Game.ESTADO == 'G'){
            estado = gameOver;
        }else if(Game.ESTADO == 'P'){
            estado = pause;
        }    

    }

    public void render(Graphics2D g) {// Faz o desenho da imagem.
		g.drawImage(estado, 0, 0, null);
	}

}
