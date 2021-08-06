import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class Principal {
    public static final int LARGURA_TELA = 1000; //Definimos o valores da dimensão da janela.
	public static final int ALTURA_TELA = 300;

    public Principal(){
        JFrame janelaFrame = new JFrame("SpaceRace");
        Game game = new Game();
        game.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        janelaFrame.getContentPane().add(game);
        janelaFrame.setResizable(false);
        janelaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaFrame.pack();
        janelaFrame.setLocationRelativeTo(null);
        janelaFrame.setVisible(true);
    }

    public static void main(String[] args){
        new Principal();
    }
}