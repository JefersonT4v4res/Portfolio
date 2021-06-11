import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.imageio.ImageIO;
import javax.naming.LinkLoopException;
import javax.swing.JPanel;
import java.awt.image.BufferedImage;



public class Game extends JPanel {
	//atributos --------------------------------
	Naham heroi;	//Atribuição das classes em variáveis.
	Fase1 fase1;
	Fase2 fase2;
	Fase3 fase3;
	platform plat;
	Inimigo boss;
	Estados states;


	static int cenario;	//Declarando a variável cenário como static para poder ser utlizada por outras classes com o valor atual da variável.
	boolean k_enter = false;	//Declarando variáveis.
	boolean k_escape = false;
	boolean k_cima = false;
	boolean k_esquerda = false;
	boolean k_direita = false;

	public static char ESTADO; // (S)Splash, (M)StartMenu, (E)Executando, (P)Pausado,
							  // (G)Game Over, (R)Reiniciando.

	
	//construtor --------------------------------
	public Game() {									//função vai definir o escutador de eventos de teclado.
		addKeyListener( new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) { //Escuta quando a tecla é solta.
					switch (e.getKeyCode()) {
					case KeyEvent.VK_W: // tecla para Cima.
						k_cima = false; break;
					case KeyEvent.VK_A: // tecla para esquerda.
						k_esquerda = false; break;
					case KeyEvent.VK_D: // tecla para direita.
						k_direita = false; break;
					case KeyEvent.VK_ENTER: // tecla enter.
						k_enter = false; break;
					case KeyEvent.VK_ESCAPE: // tecla escape.
						k_escape = false; break;					
					}
			}
	
			@Override
			public void keyPressed(KeyEvent e) { //Escuta quando a tecla é pressionada.
				if(ESTADO == 'M'){
					switch (e.getKeyCode()) {
					case KeyEvent.VK_ENTER: // tecla para enter.
						k_enter = true; break;				
					}
				}	
				
				if(ESTADO == 'R'){
					switch (e.getKeyCode()) {
					case KeyEvent.VK_ENTER: // tecla para enter.
						k_enter = true; break;				
					}
				}	

				if(ESTADO == 'E'){
					switch (e.getKeyCode()) {
					case KeyEvent.VK_W: // tecla para Cima.
						k_cima = true; break;
					case KeyEvent.VK_A: // tecla para esquerda.
						k_esquerda = true; break;
					case KeyEvent.VK_D: // tecla para direita.
						k_direita = true; break;
					case KeyEvent.VK_ESCAPE: // tecla escape.
						ESTADO = 'P'; break;	
					
					}
				}else if(ESTADO == 'P'){
					switch (e.getKeyCode()) {
					case KeyEvent.VK_ESCAPE: // tecla escape.
						k_escape = true; break;
					}	
				} 
			}
			
		});
		
		//Instanciando variáveis
		Sound.getInstance().init();
		Sound.getInstance().startMenu();
		ESTADO = 'S';
		states = new Estados();
		agendarTransicao(4000, 'M'); //Agendando transição entre estados. (Tempo / Estado)
		cenario = 1;
		heroi = new Naham();
		fase1 = new Fase1();
		fase2 = new Fase2();
		plat = new platform();
		fase3 = new Fase3();
		boss = new Inimigo();

		setFocusable(true); //Esse painel tem a capacidade de receber foco
		setLayout(null);   //passando null estou dizendo que não vou organizar nada em layout, vou organizar tudo em pixel.
		
		new Thread(new Runnable() { //Definindo uma Thread para execução do processo principal em um game, o gameloop.
			@Override
			public void run() {
				//coloco o código a ser executado pela nova Thread
				gameloop(); //  invocado em uma nova unidade de execução.
			}
		}).start();//inicializa uma nova thread
	}
	
	//métodos gameloop --------------------------
	public void gameloop() { //Dentro desse método será executado as funções que compõem um gameloop.
		while (true) { //loop infinito
			handlerEvents(); //Gerencia os eventos de entrada, capturando as ações do mouse, teclado ou joystick...
			update();  // Atualiza o estado do jogo (código, variávevis, etc).
			render(); // Renderiza/Desenha os elementos gráficos na tela.
		
			try {
				Thread.sleep(17); //pausa a thread em 60 quadros por segundo
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void handlerEvents() {
		if(ESTADO == 'E'){
			heroi.handlerEvents(k_cima, k_esquerda, k_direita); //Passa para a classe heroi(Naham) as informações do teclado.
		}

	}
	
	private void update() { //Atualiza o estado do jogo
		states.update(); //chamada da função update da classe Estados para a definição da imagem referente ao estado do jogo.
		
		 if(ESTADO == 'G'){
			Sound.getInstance().stopCaveTheme();
		}

		if(ESTADO == 'M' && k_enter == true){ //Definindo a funcionalidade para iniciar o jogo, saíndo do menu inicial para o Estado Executando do jogo.
			ESTADO = 'E';
			Sound.getInstance().stopMenu();
			Sound.getInstance().startCaveTheme();
		}else if(ESTADO == 'R' && k_enter == true){//Definindo a funcionalidade para voltar ao jogo, saíndo do Estado Reiniciando/Game over para o Estado Executando.
			cenario = 1;
			heroi.isRight = true;	
			ESTADO = 'E';
			Sound.getInstance().startCaveTheme();
		}else  if(ESTADO == 'P' && k_escape == true){ //Definindo a funcionalidade para voltar ao jogo, saíndo do menu de pause para o Estado Executando do jogo.
			ESTADO = 'E';
		}

		if(ESTADO == 'E'){ //Definição do que fazer durante o Estado Executando.
			heroi.update();//Chamada da atualização do estado do personagem
			//Definição dos cenários
		if(cenario == 1){
			fase1.update();
		}else if(cenario == 2){
			fase2.update();
			plat.update();
		}else if(cenario == 3){
			fase3.update();
		}

		}else if(ESTADO == 'G'){
				ESTADO = 'R';
		}		
		
	}
	
	private void render() { // Responsável por desenhar os elementos na tela.
		repaint(); //detalhe técnico do JAVA 
	}
	
	//outros métodos ----------------------------
	public void agendarTransicao(int tempo, char novoEstado){ //Definindo uma nova Thread responsável pela transição entre Estados do game.
		Thread thread = new Thread(new Runnable() {
			@Override
		  public void run() {
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) { 
				e.printStackTrace();
			}	
			ESTADO = novoEstado; 
		}
	});
	thread.start();
	}


	@Override //sobrescrever o método Paint component do JPanel
	protected void paintComponent(Graphics g2) { //Método de renderização JAVA SE
		super.paintComponent(g2); //desenhar coisas na tela
		
		Graphics2D g = (Graphics2D) g2.create();
		
		if(ESTADO == 'S'){ //Se estiver no estado Splash
			states.render(g);	//Chamada da classe states(Estados) para desenhar a imagem referente ao estado atual do game.
		}else if(ESTADO == 'M'){ //Se estiver no estado Main Menu
			states.render(g);
		}else if(ESTADO == 'R'){ //Se estiver no estado Reiniciando/ Game Over
			states.render(g);
		}else if(ESTADO == 'E'){ //Se estiver no estado Executando
		switch(cenario){
		  	case 1:
			   fase1.render(g); break;  //desenhando a primeira fase
 			case 2:
				fase2.render(g); //desenhando a segunda fase
				plat.render(g); break; // plataforma da segunda fase
			case 3:
				fase3.render(g);  //desenhando a terceira fase
				boss.render(g); break;
		}	
				//desenhando Naham
				heroi.render(g);
				

		}else{//Se estiver no estado PAUSADO
			states.render(g);
		}
	}


		
}
