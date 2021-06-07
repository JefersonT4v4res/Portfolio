import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principal {
	//atributos--------------------------------------
	public static final int LARGURA_TELA = 1100; //Definimos o valores da dimensão da janela.
	public static final int ALTURA_TELA = 700;
	
	//construtor------------------------------------
	public Principal() {
		JFrame janelaFrame = new JFrame("Tales of Kahf"); //Jframe representa a janela da aplicação e aqui também setamos o nome da janela.
		Game game = new Game();
		game.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA)); //Passamos as variáveis de dimensionamento da tela a ser instanciada.
		janelaFrame.getContentPane().add(game);
		//configurar os aspectos da janela
		janelaFrame.setResizable(false); //Determina que a janela não será redimensionável.
		janelaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quando apertar no X irá matar o programa e livrar a memória.
		janelaFrame.setLocation(250, 50); //Define posição da janela no monitor
		janelaFrame.setVisible(true);
		janelaFrame.pack();//faz com que a janela seja definido de acordo com o tamanho dos componentes dentro dela
		
		
	}
	
	public static void main(String[] args) {
		new Principal(); //vai criar um objeto anônimo que vai disparar o construtor
	}
	
}
