package Telas;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sound.sampled.AudioInputStream;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class TelaPrincipal extends javax.swing.JFrame {

	private int X;
	private int Y;
	private int horasCrono = 1;
	private int minutosCrono = 0;
	private int segundosCrono = 0;
	private int volume = 50;
	private boolean init = true;
	private boolean pause;
	private boolean acao;
	private boolean som;
	private boolean infoAtivo = false;
	private boolean tSom = false;
	private boolean tCrono = false;
	private long tempoSom = 5040000;
	private long tempoCrono = 3600000;
	private Font Oswald = null;
	private String SO = "";
	private String opcaoAcao = "desligar";
	private String opcaoSom = "chuva";
	private FileInputStream fileSom;
	private Thread threadSom;
	private Thread threadCrono;
	private Player player;
	private TelaConfiguracoes c = new TelaConfiguracoes();
	private TelaInfo i = new TelaInfo();

	public void setInfoAtivo(boolean infoAtivo) {
		this.infoAtivo = infoAtivo;
	}

	public boolean getInfoAtivo() {
		return infoAtivo;
	}

	// construtor
	public TelaPrincipal() {
		SO = System.getProperty("os.name").toLowerCase();

		try {
			Oswald = Font.createFont(Font.TRUETYPE_FONT,
					getClass().getClassLoader().getResourceAsStream("Fonte/Oswald.ttf"));

		} catch (IOException | FontFormatException e) {
			JOptionPane.showMessageDialog(null,
					"Erro em TP\nOcorreu um erro ao trocar a fonte. Por favor contate o desenvolver no painel "
							+ "principal através do menu informações no canto superior esquerdo.",
					"Erro", 0);
		}
		Oswald = Oswald.deriveFont(Font.PLAIN, 11);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Oswald);

		initComponents();

		threadSom = new Thread(runnableSom);
		threadCrono = new Thread(runnableCrono);

		mudarConfiguracoesModo(true, true, opcaoAcao, opcaoSom, tempoSom, false, tempoCrono, horasCrono, minutosCrono,
				segundosCrono, volume);

		init = false;
	}

	// muda as cofigurações da aplicação
	public void mudarConfiguracoesModo(boolean acao, boolean som, String opcaoAcao, String opcaoSom, long tempoSom,
			boolean tempoIntacto, long tempoCrono, int horasCrono, int minutosCrono, int segundosCrono, int volume) {

		this.acao = acao;
		this.som = som;
		this.opcaoAcao = opcaoAcao;
		
		try {
			//File f = new File(getClass().getResource("/Audios/"+opcaoSom+".mp3").toURI());
			
			String caminho = new File("").getCanonicalPath()+"/Audios/"+opcaoSom+".mp3";
			this.fileSom = new FileInputStream(caminho);
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null,
					"Erro em TP\nOcorreu um erro ao mudar as configurações de modo. Por favor contate o desenvolver no painel "
							+ "principal através do menu informações no canto superior esquerdo.",
					"Erro", 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.tempoSom = tempoSom;
		if (tempoIntacto == false) {
			toInts(tempoCrono);
		}
		if (tSom & opcaoSom != this.opcaoSom) {
			threadSom.stop();
			threadSom = new Thread(runnableSom);
			threadSom.start();
		}

		this.opcaoSom = opcaoSom;
		this.volume = volume;
		editarCrono();
		ajustarVolume();

		if (acao && som) {
			infoCrono.setText(
					"O COMPUTADOR IRÁ " + opcaoAcao.toUpperCase() + ", AO SOM DE " + opcaoSom.toUpperCase() + ", EM");
		} else if (acao) {
			infoCrono.setText("O COMPUTADOR IRÁ " + opcaoAcao.toUpperCase() + " EM");
		} else if (som) {
			infoCrono.setText("SOM DE " + opcaoSom.toUpperCase());
		}

		if (init == false) {
			if (acao == false & tCrono) {
				threadCrono.stop();
				tCrono = false;
				threadCrono = new Thread(runnableCrono);
			} else if (acao & tCrono == false & pause == true) {
				threadCrono.start();
				tCrono = true;
			}

			if (som == false & tSom) {
				threadSom.stop();
				tSom = false;
				threadSom = new Thread(runnableSom);
			} else if (som & tSom == false & pause == true) {
				threadSom.start();
				tSom = true;
			}

		}
	}

	// método que atualiza os texto do cronometro
	public void editarCrono() {
		if (horasCrono < 10) {
			crono1.setText("0" + horasCrono);
		} else {
			crono1.setText("" + horasCrono);
		}
		if (minutosCrono < 10) {
			crono3.setText("0" + minutosCrono);
		} else {
			crono3.setText("" + minutosCrono);
		}
		if (segundosCrono < 10) {
			crono5.setText("0" + segundosCrono);
		} else {
			crono5.setText("" + segundosCrono);
		}

	}

	// método que ajusta o volume do sistema
	public void ajustarVolume() {
	}

	public void desligarPC() throws IOException {
		if (SO.contains("win")) {
			if (opcaoAcao.equals("desligar")) {
				Process p = Runtime.getRuntime().exec("shutdown -s -t 0");
			} else {
				Process p = Runtime.getRuntime().exec("shutdown -r -t 0");
			}
		} else if (SO.contains("nix") || SO.contains("nux") || SO.contains("aix")) {
			if (opcaoAcao.equals("desligar")) {
				Process p = Runtime.getRuntime().exec("shutdown -h now");
			} else {
				Process p = Runtime.getRuntime().exec("shutdown -r now");
			}
		}
		System.exit(0);
	}

	// coverte os inteiros horas, minutos e segundos na long milissegundos.
	public void toLong(int a, int b, int c) {
		horasCrono = a;
		minutosCrono = b;
		segundosCrono = c;
		a = ((a * 60) * 60) * 1000;
		b = (b * 60) * 1000;
		c = c * 1000;
		tempoCrono = a + b + c;
	}

	// converte a long milissendos nos inteiros horas, minutos e segundos.
	public void toInts(long ms) {
		tempoCrono = ms;
		segundosCrono = (int) (ms / 1000) % 60;
		minutosCrono = (int) (ms / 60000) % 60;
		horasCrono = (int) ms / 3600000;
	}

	// Runnable da Thread responsável pelo play do som
	public Runnable runnableSom = new Runnable() {
		@Override
		public void run() {
			while (true) {
				try {
					player = new Player(fileSom);
					player.play();
					Thread.sleep(tempoSom);
				} catch (JavaLayerException | InterruptedException ex) {
					JOptionPane.showMessageDialog(null,
							"Erro em TP\nOcorreu um erro ao tocar o som. Por favor contate o desenvolver no painel "
									+ "principal através do menu informações no canto superior esquerdo.",
							"Erro", 0);
				}
			}
		}
	};

	// Runnable da Thread responsável pela contagem do cronometro
	public Runnable runnableCrono = new Runnable() {
		@Override
		public void run() {
			while (true) {
				try {
					tempoCrono = tempoCrono - 1000;
					toInts(tempoCrono);
					editarCrono();
					Thread.sleep(1000);
					if (tempoCrono == 0) {
						try {
							desligarPC();
						} catch (IOException ex) {
							Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
						}
						break;
					}
				} catch (InterruptedException e) {
					JOptionPane.showMessageDialog(null,
							"Erro em TP\nOcorreu um erro ao cronometrar. Por favor contate o desenvolver no painel "
									+ "principal através do menu informações no canto superior esquerdo.",
							"Erro", 0);
					e.printStackTrace();
				}
			}
		}
	};

	// método de declaração das variáveis swings
	private void initComponents() {

		tela = new javax.swing.JPanel();
		sair = new javax.swing.JLabel();
		sair.setBounds(620, 10, 20, 20);
		minimizar = new javax.swing.JLabel();
		minimizar.setBounds(600, 10, 20, 20);
		info = new javax.swing.JLabel();
		info.setBounds(10, 10, 20, 20);
		mover = new javax.swing.JLabel();
		mover.setFont(new Font("Oswald", Font.PLAIN, 15));
		mover.setForeground(new Color(236, 102, 47));
		mover.setBounds(0, 0, 655, 40);
		playPause = new javax.swing.JLabel();
		playPause.setBounds(330, 290, 312, 61);
		engrenagem = new javax.swing.JLabel();
		engrenagem.setBounds(10, 290, 313, 61);
		crono5 = new javax.swing.JLabel();
		crono5.setBounds(457, 77, 124, 179);
		crono4 = new javax.swing.JLabel();
		crono4.setBounds(407, 67, 24, 179);
		crono3 = new javax.swing.JLabel();
		crono3.setBounds(267, 77, 124, 179);
		crono2 = new javax.swing.JLabel();
		crono2.setBounds(217, 67, 24, 179);
		crono1 = new javax.swing.JLabel();
		crono1.setBounds(77, 77, 124, 179);
		fundoCrono = new javax.swing.JLabel();
		fundoCrono.setBounds(10, 40, 634, 240);
		fundo = new javax.swing.JLabel();
		fundo.setBounds(0, 0, 659, 370);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);
		setResizable(false);

		sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/sair1.png")));
		sair.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				sairMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				sairMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				sairMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				sairMouseReleased(evt);
			}
		});
		tela.setLayout(null);
		tela.add(sair);

		minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/minimizar1.png")));
		minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				minimizarMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				minimizarMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				minimizarMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				minimizarMouseReleased(evt);
			}
		});
		tela.add(minimizar);

		info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/info1.png")));
		info.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				infoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				infoMouseExited(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				infoMouseReleased(evt);
			}
		});
		tela.add(info);

		mover.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				moverMouseDragged(evt);
			}
		});
		mover.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				moverMousePressed(evt);
			}
		});
		tela.add(mover);

		playPause.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/play grande.png")));
		playPause.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				playPauseMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				playPauseMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				playPauseMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				playPauseMouseReleased(evt);
			}
		});
		tela.add(playPause);

		engrenagem.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/engrenagem grande.png")));
		engrenagem.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				engrenagemMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				engrenagemMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				engrenagemMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				engrenagemMouseReleased(evt);
			}
		});
		tela.add(engrenagem);

		infoCrono = new JLabel("O COMPUTADOR IRÁ DESLIGAR EM");
		infoCrono.setHorizontalAlignment(SwingConstants.CENTER);
		infoCrono.setForeground(Color.WHITE);
		infoCrono.setBounds(10, 68, 635, 29);
		infoCrono.setFont(new java.awt.Font("Oswald", 0, 20));
		tela.add(infoCrono);

		crono5.setFont(new Font("Oswald", Font.PLAIN, 120));
		crono5.setForeground(new java.awt.Color(236, 102, 46));
		crono5.setText("00");
		tela.add(crono5);

		crono4.setFont(new Font("Oswald", Font.PLAIN, 120));
		crono4.setForeground(new java.awt.Color(255, 255, 255));
		crono4.setText(":");
		tela.add(crono4);

		crono3.setFont(new Font("Oswald", Font.PLAIN, 120));
		crono3.setForeground(new java.awt.Color(236, 102, 46));
		crono3.setText("00");
		tela.add(crono3);

		crono2.setFont(new Font("Oswald", Font.PLAIN, 120));
		crono2.setForeground(new java.awt.Color(255, 255, 255));
		crono2.setText(":");
		tela.add(crono2);

		crono1.setFont(new Font("Oswald", Font.PLAIN, 120));
		crono1.setForeground(new java.awt.Color(236, 102, 46));
		crono1.setText("00");
		tela.add(crono1);

		fundoCrono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/fundo crono.png")));
		tela.add(fundoCrono);

		fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/fechar.png")));
		tela.add(fundo);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.TRAILING).addComponent(tela, Alignment.LEADING,
				GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(tela,
				GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE));

		label = new JLabel("New label");
		label.setBounds(0, 0, 46, 14);
		tela.add(label);
		getContentPane().setLayout(layout);

		pack();
		setLocationRelativeTo(null);
	}

	// métodos de ventos listenners dos componentes do JFrame
	private void playPauseMousePressed(java.awt.event.MouseEvent evt) {
		if (pause == false) {
			playPause.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/play pequeno claro.png")));
		} else {
			playPause.setIcon(new javax.swing.ImageIcon(
					getClass().getResource("/Imagens/TelaPrincipal/pause pequeno claro.png")));
		}
	}

	@SuppressWarnings("deprecation")
	private void playPauseMouseReleased(java.awt.event.MouseEvent evt) {
		if (pause == false) {
			playPause.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/pause grande claro.png")));
			pause = true;

			if (som) {
				threadSom.start();
				tSom = true;
			}
			if (acao) {
				threadCrono.start();
				tCrono = true;
			}

		} else {
			playPause.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/play grande claro.png")));
			pause = false;

			if (som) {
				threadSom.stop();
				tSom = false;
				threadSom = new Thread(runnableSom);
			}
			if (acao) {
				threadCrono.stop();
				tCrono = false;
				threadCrono = new Thread(runnableCrono);
			}
		}
	}

	private void engrenagemMousePressed(java.awt.event.MouseEvent evt) {
		engrenagem.setIcon(new javax.swing.ImageIcon(
				getClass().getResource("/Imagens/TelaPrincipal/engrenagem pequena claro.png")));
	}

	private void engrenagemMouseReleased(java.awt.event.MouseEvent evt) {
		engrenagem.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/engrenagem grande.png")));
		c.mandarFrame(this);
		this.setVisible(false);
		c.setTempoIntacto(true);
		c.mudarHoras(horasCrono);
		c.mudarMinutos(minutosCrono);
		c.mudarSegundos(segundosCrono);
		c.mudarLocalizacaoHoras();
		c.mudarLocalizacaoMinutos();
		c.mudarLocalizacaoSegundos();
		c.setVisible(true);
	}

	private void sairMousePressed(java.awt.event.MouseEvent evt) {
		sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/sair2.png")));
	}

	private void sairMouseReleased(java.awt.event.MouseEvent evt) {
		if (tCrono) {
			int jOP = JOptionPane.showConfirmDialog(null,
					"Ainda estamos medindo o tempo para o encerramento do computador. Realmente deseja sair?\n\n",
					"Saída inesperada", 0, 2);
			if (jOP == 0) {
				sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/sair1.png")));
				System.exit(0);
			}
		} else {
			sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/sair1.png")));
			System.exit(0);
		}

	}

	private void minimizarMousePressed(java.awt.event.MouseEvent evt) {
		minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/minimizar2.png")));
	}

	private void minimizarMouseReleased(java.awt.event.MouseEvent evt) {
		minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/minimizar1.png")));
		this.setExtendedState(ICONIFIED);
	}

	private void infoMouseReleased(java.awt.event.MouseEvent evt) {
		if (infoAtivo == false) {
			i.setLocation(this.getX(), this.getY());
			i.mandarFrame(this);
			infoAtivo = true;
		}

	}

	private void moverMousePressed(java.awt.event.MouseEvent evt) {
		X = evt.getX();
		Y = evt.getY();
	}

	private void moverMouseDragged(java.awt.event.MouseEvent evt) {
		this.setLocation(evt.getXOnScreen() - X, evt.getYOnScreen() - Y);
		i.setLocation(evt.getXOnScreen() - X, evt.getYOnScreen() - Y);
	}

	private void playPauseMouseEntered(java.awt.event.MouseEvent evt) {
		if (pause == false) {
			playPause.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/play grande claro.png")));
		} else {
			playPause.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/pause grande claro.png")));
		}
	}

	private void playPauseMouseExited(java.awt.event.MouseEvent evt) {
		if (pause == false) {
			playPause.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/play grande.png")));
		} else {
			playPause.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/pause grande.png")));
		}
	}

	private void engrenagemMouseEntered(java.awt.event.MouseEvent evt) {
		engrenagem.setIcon(new javax.swing.ImageIcon(
				getClass().getResource("/Imagens/TelaPrincipal/engrenagem grande claro.png")));
	}

	private void engrenagemMouseExited(java.awt.event.MouseEvent evt) {
		engrenagem.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/engrenagem grande.png")));
	}

	private void infoMouseEntered(java.awt.event.MouseEvent evt) {
		info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/info2.png")));
	}

	private void infoMouseExited(java.awt.event.MouseEvent evt) {
		info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/info1.png")));

	}

	private void minimizarMouseEntered(java.awt.event.MouseEvent evt) {
		minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/minimizar2.png")));
	}

	private void minimizarMouseExited(java.awt.event.MouseEvent evt) {
		minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/minimizar1.png")));
	}

	private void sairMouseEntered(java.awt.event.MouseEvent evt) {
		sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/sair2.png")));
	}

	private void sairMouseExited(java.awt.event.MouseEvent evt) {
		sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaPrincipal/sair1.png")));
	}

	// método principal
	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| javax.swing.UnsupportedLookAndFeelException ex) {
			JOptionPane.showMessageDialog(null,
					"Erro em TP\nOcorreu um erro na troca do LookAndFeel. Por favor contate o desenvolver no painel "
							+ "principal através do menu informações no canto superior esquerdo.",
					"Erro", 0);
		}
		java.awt.EventQueue.invokeLater(() -> {
			new TelaPrincipal().setVisible(true);
		});
	}

	// declaração das variaveis swing de maneira global
	private javax.swing.JLabel crono1;
	private javax.swing.JLabel crono2;
	private javax.swing.JLabel crono3;
	private javax.swing.JLabel crono4;
	private javax.swing.JLabel crono5;
	private javax.swing.JLabel engrenagem;
	private javax.swing.JLabel fundo;
	private javax.swing.JLabel fundoCrono;
	private javax.swing.JLabel info;
	private javax.swing.JLabel minimizar;
	private javax.swing.JLabel mover;
	private javax.swing.JLabel playPause;
	private javax.swing.JLabel sair;
	private javax.swing.JPanel tela;
	private javax.swing.JLabel infoCrono;
	private JLabel label;
}
