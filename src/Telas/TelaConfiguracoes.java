/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 *
 * @author msa04
 */
public class TelaConfiguracoes extends javax.swing.JFrame {

	/**
	 * Creates new form TelaConfiguracoes
	 */
	private int X;
	private int Y;
	private int horasCrono;
	private int minutosCrono;
	private int segundosCrono;
	private int audio = 50;
	private int horas = 1;
	private int minutos;
	private int segundos;
	private long tempoSom;
	private long tempoCrono;
	private boolean ativarAcao = true;
	private boolean ativarSom = true;
	private boolean alternarDesligar = true;
	private boolean alternarReiniciar = false;
	private boolean alternarChuva = true;
	private boolean alternarFogo = false;
	private boolean alternarFloresta = false;
	private boolean alternarHipHop = false;
	private boolean alternarJazz = false;
	private boolean alternarEletronica = false;
	private boolean tempoIntacto = true;
	private String opcaoAcao = "desligar";
	private String opcaoSom = "chuva";
	private Point pointControle;
	private Point pointVolume;
	private TelaPrincipal p;

	public TelaConfiguracoes() {
		setUndecorated(true);
		initComponents();
		audio = volume.getLocation().x / 2 - 215;
	}

	public void setTempoIntacto(boolean a) {
		tempoIntacto = a;
	}

	// método a ser usado pela tela Principal
	public void mandarFrame(TelaPrincipal f) {
		this.p = f;
	}

	// método que altera a posição do controlador do jslider para a posição
	// correspondete às horas
	public void mudarLocalizacaoHoras() {
		controleHoras.setLocation(Integer.parseInt(campoHoras.getText()) * 8 + 108, barraHoras.getY());
	}

	// método que altera a posição do controlador do jslider para a posição
	// correspondete aos minutos
	public void mudarLocalizacaoMinutos() {
		controleMinutos.setLocation((int) (Integer.parseInt(campoMinutos.getText()) * 3.3 + 101), barraMinutos.getY());
	}

	// método que altera a posição do controlador do jslider para a posição
	// correspondete aos segundos
	public void mudarLocalizacaoSegundos() {
		controleSegundos.setLocation((int) (Integer.parseInt(campoSegundos.getText()) * 3.3 + 101),
				barraSegundos.getY());
	}

	// transforma horas, minutos e segundos em um long de milissegundos
	public void toLong(int a, int b, int c) {
		horasCrono = a;
		minutosCrono = b;
		segundosCrono = c;
		a = ((a * 60) * 60) * 1000;
		b = (b * 60) * 1000;
		c = c * 1000;
		tempoCrono = (long) a + b + c;
	}

	// método que formata o tempo do campoHoras
	public void mudarHoras(int a) {
		if (a < 10) {
			campoHoras.setText("0" + a);
		} else {
			campoHoras.setText("" + a);
		}
		horasCrono = a;
		toLong(horasCrono, minutosCrono, segundosCrono);
	}

	// método que formata o tempo do campoMinutos
	public void mudarMinutos(int a) {
		if (a < 10) {
			campoMinutos.setText("0" + a);
		} else {
			campoMinutos.setText("" + a);
		}
		minutosCrono = a;
		toLong(horasCrono, minutosCrono, segundosCrono);
	}

	// método que formata o tempo do campoSegundos
	public void mudarSegundos(int a) {
		if (a < 10) {
			campoSegundos.setText("0" + a);
		} else {
			campoSegundos.setText("" + a);
		}
		segundosCrono = a;
		toLong(horasCrono, minutosCrono, segundosCrono);
	}

	// runnable que pista o icone do chekAcao
	public Runnable avisoChekAcao = new Runnable() {
		@Override
		public void run() {
			alterAcao.setIcon(new javax.swing.ImageIcon(
					getClass().getResource("/Imagens/TelaConfiguracoes/desligado claro.png")));
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				JOptionPane
						.showMessageDialog(null,
								"Erro em TC\nOcorreu na troca do ícone ação. Por favor contate o desenvolver no painel "
										+ "principal através do menu informações no canto superior esquerdo.",
								"Erro", 0);
			}
			alterAcao.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/desligado.png")));
		}
	};

	// runnable que pista o icone do chekSom
	public Runnable avisoChekSom = new Runnable() {
		@Override
		public void run() {
			alterSom.setIcon(new javax.swing.ImageIcon(
					getClass().getResource("/Imagens/TelaConfiguracoes/desligado claro.png")));
			try {
				Thread.sleep(50);
			} catch (InterruptedException ex) {
				JOptionPane.showMessageDialog(null,
						"Erro em TC\nOcorreu um erro na troca do ícone som. Por favor contate o desenvolver no painel "
								+ "principal através do menu informações no canto superior esquerdo.",
						"Erro", 0);
			}
			alterSom.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/desligado.png")));
		}
	};

	// método que organiza as acoes e qual delas está selecionada
	public void verificarAcao() {
		if (alternarDesligar) {
			desligar.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/desligarcinza.png")));
			labelDesligar.setForeground(new Color(71, 71, 71));
			alternarDesligar = !alternarDesligar;
		}
		if (alternarReiniciar) {
			reiniciar.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/reiniciarcinza.png")));
			labelReiniciar.setForeground(new Color(71, 71, 71));
			alternarReiniciar = !alternarReiniciar;
		}
	}

	// método que organiza os sons e qual deles está selecionado
	public void verificarSom() {
		if (alternarChuva) {
			chuva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/chuva.png")));
			alternarChuva = !alternarChuva;
		}
		if (alternarFogo) {
			fogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/fogo.png")));
			alternarFogo = !alternarFogo;
		}
		if (alternarFloresta) {
			floresta.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/floresta.png")));
			alternarFloresta = !alternarFloresta;
		}
		if (alternarHipHop) {
			hipHop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/hiphop.png")));
			alternarHipHop = !alternarHipHop;
		}
		if (alternarJazz) {
			jazz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/jazz.png")));
			alternarJazz = !alternarJazz;
		}
		if (alternarEletronica) {
			eletronica.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/eletronica.png")));
			alternarEletronica = !alternarEletronica;
		}
	}

	// método de declaração dos componetes swings
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		Tela = new javax.swing.JPanel();
		volume = new javax.swing.JLabel();
		volume.setBounds(480, 310, 24, 30);
		barraVolume = new javax.swing.JLabel();
		barraVolume.setBounds(440, 310, 210, 30);
		barraTitulo = new javax.swing.JLabel();
		barraTitulo.setBounds(0, 0, 700, 20);
		altoFalante = new javax.swing.JLabel();
		altoFalante.setBounds(400, 310, 24, 30);
		eletronica = new javax.swing.JLabel();
		eletronica.setBounds(580, 210, 64, 64);
		jazz = new javax.swing.JLabel();
		jazz.setBounds(484, 210, 70, 64);
		hipHop = new javax.swing.JLabel();
		hipHop.setBounds(390, 210, 64, 64);
		floresta = new javax.swing.JLabel();
		floresta.setBounds(580, 130, 64, 64);
		fogo = new javax.swing.JLabel();
		fogo.setBounds(484, 130, 70, 64);
		chuva = new javax.swing.JLabel();
		chuva.setBounds(390, 130, 64, 64);
		separador2 = new javax.swing.JLabel();
		separador2.setBounds(390, 110, 258, 3);
		alterSom = new javax.swing.JLabel();
		alterSom.setBounds(570, 50, 75, 39);
		som = new javax.swing.JLabel();
		som.setBounds(390, 50, 48, 40);
		fundoSom = new javax.swing.JLabel();
		fundoSom.setBounds(360, 20, 321, 348);
		reiniciar = new javax.swing.JLabel();
		reiniciar.setBounds(184, 130, 33, 28);
		desligar = new javax.swing.JLabel();
		desligar.setBounds(60, 130, 33, 28);
		separador1 = new javax.swing.JLabel();
		separador1.setBounds(50, 110, 258, 3);
		alterAcao = new javax.swing.JLabel();
		alterAcao.setBounds(230, 50, 75, 39);
		acao = new javax.swing.JLabel();
		acao.setBounds(50, 50, 59, 40);
		fundoAcao = new javax.swing.JLabel();
		fundoAcao.setBounds(20, 20, 321, 348);
		ok = new javax.swing.JLabel();
		ok.setBounds(20, 390, 658, 56);
		fundo = new javax.swing.JLabel();
		fundo.setBounds(0, 0, 700, 463);
		this.setBounds(0, 0, 700, 463);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);

		volume.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		volume.setIcon(new javax.swing.ImageIcon(
				getClass().getResource("/Imagens/TelaConfiguracoes/controle deslizante.png"))); // NOI18N
		volume.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				volumeMouseDragged(evt);
			}
		});
		volume.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				volumeMousePressed(evt);
			}
		});
		Tela.setLayout(null);

		campoSegundos = new JLabel("00");
		campoSegundos.setHorizontalAlignment(SwingConstants.CENTER);
		campoSegundos.setFont(new Font("Oswald", Font.PLAIN, 30));
		campoSegundos.setBounds(46, 300, 42, 39);
		campoSegundos.setForeground(new Color(236, 102, 47));
		Tela.add(campoSegundos);

		campoHoras = new JLabel("00");
		campoHoras.setHorizontalAlignment(SwingConstants.CENTER);
		campoHoras.setFont(new Font("Oswald", Font.PLAIN, 30));
		campoHoras.setBounds(46, 189, 42, 39);
		campoHoras.setForeground(new Color(236, 102, 47));
		Tela.add(campoHoras);

		campoMinutos = new JLabel("00");
		campoMinutos.setHorizontalAlignment(SwingConstants.CENTER);
		campoMinutos.setFont(new Font("Oswald", Font.PLAIN, 30));
		campoMinutos.setBounds(46, 245, 42, 39);
		campoMinutos.setForeground(new Color(236, 102, 47));
		Tela.add(campoMinutos);

		labelReiniciar = new JLabel("REINICIAR");
		labelReiniciar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				reiniciarMousePressed(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				reiniciarMouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				reiniciarMouseExited(e);
			}
		});
		labelReiniciar.setBounds(222, 130, 80, 28);
		labelReiniciar.setForeground(new Color(71, 71, 71));
		labelReiniciar.setFont(new Font("Oswald", Font.PLAIN, 17));
		Tela.add(labelReiniciar);

		labelDesligar = new JLabel("DESLIGAR");
		labelDesligar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				desligarMousePressed(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				desligarMouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				desligarMouseExited(e);
			}
		});
		labelDesligar.setBounds(96, 130, 80, 28);
		labelDesligar.setFont(new Font("Oswald", Font.PLAIN, 17));
		labelDesligar.setForeground(new Color(236, 102, 47));
		Tela.add(labelDesligar);

		labelHoras = new JLabel("HORAS");
		labelHoras.setBounds(46, 180, 268, 14);
		labelHoras.setHorizontalAlignment(SwingConstants.CENTER);
		labelHoras.setForeground(Color.WHITE);
		labelHoras.setFont(new Font("Oswald", Font.PLAIN, 13));
		Tela.add(labelHoras);

		labelMinutos = new JLabel("MNUTOS");
		labelMinutos.setBounds(46, 235, 268, 14);
		labelMinutos.setHorizontalAlignment(SwingConstants.CENTER);
		labelMinutos.setForeground(Color.WHITE);
		labelMinutos.setFont(new Font("Oswald", Font.PLAIN, 13));
		Tela.add(labelMinutos);

		labelSegundos = new JLabel("SEGUNDOS");
		labelSegundos.setBounds(46, 291, 268, 14);
		labelSegundos.setHorizontalAlignment(SwingConstants.CENTER);
		labelSegundos.setFont(new Font("Oswald", Font.PLAIN, 13));
		labelSegundos.setForeground(Color.WHITE);
		Tela.add(labelSegundos);

		controleSegundos = new JLabel();
		controleSegundos.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				controleSegundosMouseDragged(e);
			}
		});
		controleSegundos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controleSegundosMousePressed(e);
			}
		});
		controleSegundos.setBounds(98, 305, 19, 39);
		controleSegundos.setIcon(
				new ImageIcon(TelaConfiguracoes.class.getResource("/Imagens/TelaTime/controle deslizante.png")));
		Tela.add(controleSegundos);

		barraSegundos = new JLabel();
		barraSegundos.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				barraSegundosMouseDragged(e);
			}
		});
		barraSegundos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				barraSegundosMousePressed(e);
			}
		});
		barraSegundos.setBounds(104, 305, 210, 39);
		barraSegundos.setIcon(new ImageIcon(
				TelaConfiguracoes.class.getResource("/Imagens/TelaTime/controle deslizante barra time.png")));
		Tela.add(barraSegundos);

		controleMinutos = new JLabel();
		controleMinutos.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				controleMinutosMouseDragged(e);
			}
		});
		controleMinutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controleMinutosMousePressed(e);
			}
		});
		controleMinutos.setBounds(99, 250, 19, 39);
		controleMinutos.setIcon(
				new ImageIcon(TelaConfiguracoes.class.getResource("/Imagens/TelaTime/controle deslizante.png")));
		Tela.add(controleMinutos);

		barraMinutos = new JLabel();
		barraMinutos.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				barraMinutosMouseDragged(e);
			}
		});
		barraMinutos.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				barraMinutosMousePressed(e);
			}
		});
		barraMinutos.setBounds(103, 250, 211, 39);
		barraMinutos.setIcon(new ImageIcon(
				TelaConfiguracoes.class.getResource("/Imagens/TelaTime/controle deslizante barra time.png")));
		Tela.add(barraMinutos);

		controleHoras = new JLabel();
		controleHoras.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				controleHorasMouseDragged(e);
			}
		});
		controleHoras.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controleHorasMousePressed(e);
			}
		});
		controleHoras.setBounds(99, 194, 19, 39);
		controleHoras.setIcon(
				new ImageIcon(TelaConfiguracoes.class.getResource("/Imagens/TelaTime/controle deslizante.png")));
		Tela.add(controleHoras);

		barraHoras = new JLabel();
		barraHoras.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				barraHorasMouseDragged(e);
			}
		});
		barraHoras.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				barraHorasMousePressed(e);
			}
		});
		barraHoras.setBounds(104, 194, 210, 39);
		barraHoras.setIcon(new ImageIcon(
				TelaConfiguracoes.class.getResource("/Imagens/TelaTime/controle deslizante barra time.png")));
		Tela.add(barraHoras);
		Tela.add(volume);

		barraVolume.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		barraVolume.setIcon(
				new ImageIcon(TelaConfiguracoes.class.getResource("/Imagens/TelaTime/controle deslizante barra.png"))); // NOI18N
		barraVolume.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				barraVolumeMouseDragged(evt);
			}
		});
		barraVolume.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				barraVolumeMousePressed(evt);
			}
		});
		Tela.add(barraVolume);

		barraTitulo.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				barraTituloMouseDragged(evt);
			}
		});
		barraTitulo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				barraTituloMousePressed(evt);
			}
		});
		Tela.add(barraTitulo);

		altoFalante.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/altofalante.png"))); // NOI18N
		Tela.add(altoFalante);

		eletronica.setIcon(
				new ImageIcon(TelaConfiguracoes.class.getResource("/Imagens/TelaConfiguracoes/eletronica.png"))); // NOI18N
		eletronica.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				eletronicaMousePressed(evt);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (alternarEletronica == false) {
					eletronica.setIcon(new javax.swing.ImageIcon(
							getClass().getResource("/Imagens/TelaConfiguracoes/eletronica3.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (alternarEletronica == false) {
					eletronica.setIcon(new javax.swing.ImageIcon(
							getClass().getResource("/Imagens/TelaConfiguracoes/eletronica.png")));
				}
			}
		});
		Tela.add(eletronica);

		jazz.setIcon(new ImageIcon(TelaConfiguracoes.class.getResource("/Imagens/TelaConfiguracoes/jazz.png"))); // NOI18N
		jazz.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				jazzMousePressed(evt);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (alternarJazz == false) {
					jazz.setIcon(
							new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/jazz3.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (alternarJazz == false) {
					jazz.setIcon(
							new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/jazz.png")));
				}
			}
		});
		Tela.add(jazz);

		hipHop.setIcon(new ImageIcon(TelaConfiguracoes.class.getResource("/Imagens/TelaConfiguracoes/hiphop.png"))); // NOI18N
		hipHop.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				hipHopMousePressed(evt);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (alternarHipHop == false) {
					hipHop.setIcon(new javax.swing.ImageIcon(
							getClass().getResource("/Imagens/TelaConfiguracoes/hiphop3.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (alternarHipHop == false) {
					hipHop.setIcon(
							new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/hiphop.png")));
				}
			}
		});
		Tela.add(hipHop);

		floresta.setIcon(new ImageIcon(TelaConfiguracoes.class.getResource("/Imagens/TelaConfiguracoes/floresta.png"))); // NOI18N
		floresta.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				florestaMousePressed(evt);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (alternarFloresta == false) {
					floresta.setIcon(new javax.swing.ImageIcon(
							getClass().getResource("/Imagens/TelaConfiguracoes/floresta3.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (alternarFloresta == false) {
					floresta.setIcon(new javax.swing.ImageIcon(
							getClass().getResource("/Imagens/TelaConfiguracoes/floresta.png")));
				}
			}
		});
		Tela.add(floresta);

		fogo.setIcon(new ImageIcon(TelaConfiguracoes.class.getResource("/Imagens/TelaConfiguracoes/fogo.png"))); // NOI18N
		fogo.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				fogoMousePressed(evt);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (alternarFogo == false) {
					fogo.setIcon(
							new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/fogo3.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (alternarFogo == false) {
					fogo.setIcon(
							new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/fogo.png")));
				}
			}
		});
		Tela.add(fogo);

		chuva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/chuva2.png"))); // NOI18N
		chuva.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				chuvaMousePressed(evt);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (alternarChuva == false) {
					chuva.setIcon(
							new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/chuva3.png")));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (alternarChuva == false) {
					chuva.setIcon(
							new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/chuva.png")));
				}
			}
		});
		Tela.add(chuva);

		separador2.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/barraseparacao.png"))); // NOI18N
		Tela.add(separador2);

		alterSom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/ligado.png"))); // NOI18N
		alterSom.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				alterSomMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				alterSomMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				alterSomMousePressed(evt);
			}
		});
		Tela.add(alterSom);

		som.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/titulosom.png"))); // NOI18N
		Tela.add(som);

		fundoSom.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/fundoacao.png"))); // NOI18N
		Tela.add(fundoSom);

		reiniciar.setIcon(
				new ImageIcon(TelaConfiguracoes.class.getResource("/Imagens/TelaConfiguracoes/reiniciarcinza.png"))); // NOI18N
		reiniciar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				reiniciarMousePressed(evt);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				reiniciarMouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				reiniciarMouseExited(e);
			}
		});
		Tela.add(reiniciar);

		desligar.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/desligarlaranja.png"))); // NOI18N
		desligar.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				desligarMousePressed(evt);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				desligarMouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				desligarMouseExited(e);
			}
		});
		Tela.add(desligar);

		separador1.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/barraseparacao.png"))); // NOI18N
		Tela.add(separador1);

		alterAcao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/ligado.png"))); // NOI18N
		alterAcao.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				alterAcaoMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				alterAcaoMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				alterAcaoMousePressed(evt);
			}
		});
		Tela.add(alterAcao);

		acao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/tituloacao.png"))); // NOI18N
		Tela.add(acao);

		fundoAcao
				.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/fundoacao.png"))); // NOI18N
		Tela.add(fundoAcao);

		ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/ok2.png"))); // NOI18N
		ok.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				okMouseEntered(evt);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				okMouseExited(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				okMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				okMouseReleased(evt);
			}
		});
		Tela.add(ok);

		fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/fundosom.png"))); // NOI18N
		Tela.add(fundo);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(Tela,
				GroupLayout.PREFERRED_SIZE, 698, GroupLayout.PREFERRED_SIZE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(Tela,
				GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE));
		getContentPane().setLayout(groupLayout);

		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	// métodos de eventos listenners dos componentes do jFrame
	private void barraTituloMousePressed(java.awt.event.MouseEvent evt) {
		X = evt.getX();
		Y = evt.getY();
	}

	private void barraTituloMouseDragged(java.awt.event.MouseEvent evt) {
		this.setLocation(evt.getXOnScreen() - X, evt.getYOnScreen() - Y);
	}

	private void alterAcaoMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			if (ativarAcao) {
				alterAcao.setIcon(new javax.swing.ImageIcon(
						getClass().getResource("/Imagens/TelaConfiguracoes/desligado claro.png")));
				desligar.setEnabled(false);
				reiniciar.setEnabled(false);
				labelDesligar.setForeground(Color.GRAY);
				labelReiniciar.setForeground(Color.GRAY);
				labelHoras.setForeground(Color.GRAY);
				labelMinutos.setForeground(Color.GRAY);
				labelSegundos.setForeground(Color.GRAY);
				campoHoras.setForeground(Color.GRAY);
				campoMinutos.setForeground(Color.GRAY);
				campoSegundos.setForeground(Color.GRAY);
				barraHoras.setEnabled(false);
				barraMinutos.setEnabled(false);
				barraSegundos.setEnabled(false);
				controleHoras.setEnabled(false);
				controleMinutos.setEnabled(false);
				controleSegundos.setEnabled(false);
				ativarAcao = !ativarAcao;
			} else {
				alterAcao.setIcon(new javax.swing.ImageIcon(
						getClass().getResource("/Imagens/TelaConfiguracoes/ligado claro.png")));
				ativarAcao = !ativarAcao;
				desligar.setEnabled(true);
				reiniciar.setEnabled(true);
				labelDesligar.setForeground(new Color(71, 71, 71));
				labelReiniciar.setForeground(new Color(71, 71, 71));
				if (opcaoAcao.equals("desligar")) {
					labelDesligar.setForeground(new Color(236, 102, 47));
				} else if (opcaoAcao.equals("reiniciar")) {
					labelReiniciar.setForeground(new Color(236, 102, 47));
				}
				labelHoras.setForeground(Color.WHITE);
				labelMinutos.setForeground(Color.WHITE);
				labelSegundos.setForeground(Color.WHITE);
				campoHoras.setForeground(new Color(236, 102, 47));
				campoMinutos.setForeground(new Color(236, 102, 47));
				campoSegundos.setForeground(new Color(236, 102, 47));
				barraHoras.setEnabled(true);
				barraMinutos.setEnabled(true);
				barraSegundos.setEnabled(true);
				controleHoras.setEnabled(true);
				controleMinutos.setEnabled(true);
				controleSegundos.setEnabled(true);
			}
		} else {
			new Thread(avisoChekSom).start();
		}
	}

	private void alterSomMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			if (ativarSom) {
				alterSom.setIcon(new javax.swing.ImageIcon(
						getClass().getResource("/Imagens/TelaConfiguracoes/desligado claro.png")));
				chuva.setEnabled(false);
				fogo.setEnabled(false);
				floresta.setEnabled(false);
				hipHop.setEnabled(false);
				jazz.setEnabled(false);
				eletronica.setEnabled(false);
				altoFalante.setEnabled(false);
				volume.setEnabled(false);
				ativarSom = !ativarSom;
			} else {
				alterSom.setIcon(new javax.swing.ImageIcon(
						getClass().getResource("/Imagens/TelaConfiguracoes/ligado claro.png")));
				chuva.setEnabled(true);
				fogo.setEnabled(true);
				floresta.setEnabled(true);
				hipHop.setEnabled(true);
				jazz.setEnabled(true);
				eletronica.setEnabled(true);
				altoFalante.setEnabled(true);
				volume.setEnabled(true);
				ativarSom = !ativarSom;
			}
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void desligarMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			verificarAcao();
			if (alternarDesligar) {
				desligar.setIcon(new javax.swing.ImageIcon(
						getClass().getResource("/Imagens/TelaConfiguracoes/desligarcinza.png")));
				labelDesligar.setForeground(new Color(71, 71, 71));

			} else {
				desligar.setIcon(new javax.swing.ImageIcon(
						getClass().getResource("/Imagens/TelaConfiguracoes/desligarlaranja.png")));
				opcaoAcao = "desligar";
				labelDesligar.setForeground(new Color(236, 102, 47));
			}
			alternarDesligar = !alternarDesligar;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void reiniciarMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			verificarAcao();
			if (alternarReiniciar) {
				reiniciar.setIcon(new javax.swing.ImageIcon(
						getClass().getResource("/Imagens/TelaConfiguracoes/reiniciarcinza.png")));
				labelReiniciar.setForeground(new Color(71, 71, 71));
			} else {
				reiniciar.setIcon(new javax.swing.ImageIcon(
						getClass().getResource("/Imagens/TelaConfiguracoes/reiniciarlaranja.png")));
				opcaoAcao = "reiniciar";
				labelReiniciar.setForeground(new Color(236, 102, 47));
			}
			alternarReiniciar = !alternarReiniciar;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void reiniciarMouseEntered(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			if (alternarReiniciar == false) {
				reiniciar.setIcon(new ImageIcon(
						TelaConfiguracoes.class.getResource("/Imagens/TelaConfiguracoes/reiniciarbranco.png")));
				labelReiniciar.setForeground(new Color(90, 90, 90));
			}
		} else {
		}
	}

	private void reiniciarMouseExited(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			if (alternarReiniciar == false) {
				reiniciar.setIcon(new ImageIcon(
						TelaConfiguracoes.class.getResource("/Imagens/TelaConfiguracoes/reiniciarcinza.png")));
				labelReiniciar.setForeground(new Color(71, 71, 71));
			} else {
			}
		}

	}

	private void desligarMouseEntered(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			if (alternarDesligar == false) {
				desligar.setIcon(new ImageIcon(
						TelaConfiguracoes.class.getResource("/Imagens/TelaConfiguracoes/desligarbranco.png")));
				labelDesligar.setForeground(new Color(90, 90, 90));
			} else {
			}
		}

	}

	private void desligarMouseExited(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			if (alternarDesligar == false) {
				desligar.setIcon(new ImageIcon(
						TelaConfiguracoes.class.getResource("/Imagens/TelaConfiguracoes/desligarcinza.png")));
				labelDesligar.setForeground(new Color(71, 71, 71));
			} else {
			}
		}

	}

	private void chuvaMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			verificarSom();
			if (alternarChuva) {
				chuva.setIcon(
						new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/chuva.png")));
			} else {
				chuva.setIcon(
						new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/chuva2.png")));
				opcaoSom = "chuva";
				tempoSom = 5040000;
			}
			alternarChuva = !alternarChuva;
		} else {
			new Thread(avisoChekSom).start();
		}
	}

	private void fogoMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			verificarSom();
			if (alternarFogo) {
				fogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/fogo.png")));
			} else {
				fogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/fogo2.png")));
				opcaoSom = "fogo";
				tempoSom = 7440000;
			}
			alternarFogo = !alternarFogo;
		} else {
			new Thread(avisoChekSom).start();
		}
	}

	private void florestaMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			verificarSom();
			if (alternarFloresta) {
				floresta.setIcon(
						new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/floresta.png")));
			} else {
				floresta.setIcon(
						new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/floresta2.png")));
				opcaoSom = "floresta";
				tempoSom = 5280000;
			}
			alternarFloresta = !alternarFloresta;
		} else {
			new Thread(avisoChekSom).start();
		}
	}

	private void hipHopMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			verificarSom();
			if (alternarHipHop) {
				hipHop.setIcon(
						new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/hiphop.png")));
			} else {
				hipHop.setIcon(
						new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/hiphop2.png")));
				opcaoSom = "hipHop";
				tempoSom = 8940000;
			}
			alternarHipHop = !alternarHipHop;
		} else {
			new Thread(avisoChekSom).start();
		}
	}

	private void jazzMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			verificarSom();
			if (alternarJazz) {
				jazz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/jazz.png")));
			} else {
				jazz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/jazz2.png")));
				opcaoSom = "jazz";
				tempoSom = 4260000;
			}
			alternarJazz = !alternarJazz;
		} else {
			new Thread(avisoChekSom).start();
		}
	}

	private void eletronicaMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			verificarSom();
			if (alternarEletronica) {
				eletronica.setIcon(
						new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/eletronica.png")));
			} else {
				eletronica.setIcon(new javax.swing.ImageIcon(
						getClass().getResource("/Imagens/TelaConfiguracoes/eletronica2.png")));
				opcaoSom = "eletronica";
				tempoSom = 3600000;
			}
			alternarEletronica = !alternarEletronica;
		} else {
			new Thread(avisoChekSom).start();
		}
	}

	private void okMousePressed(java.awt.event.MouseEvent evt) {
		ok.setIcon(
				new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/ok pequeno claro.png")));
	}

	private void okMouseReleased(java.awt.event.MouseEvent evt) {
		ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/ok2.png")));
		p.mudarConfiguracoesModo(ativarAcao, ativarSom, opcaoAcao, opcaoSom, tempoSom, tempoIntacto, tempoCrono,
				horasCrono, minutosCrono, segundosCrono, audio);
		this.dispose();
		p.setVisible(true);
	}

	private void alterAcaoMouseEntered(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			alterAcao.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/ligado claro.png")));
		} else {
			alterAcao.setIcon(new javax.swing.ImageIcon(
					getClass().getResource("/Imagens/TelaConfiguracoes/desligado claro.png")));
		}
	}

	private void alterAcaoMouseExited(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			alterAcao.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/ligado.png")));
		} else {
			alterAcao.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/desligado.png")));
		}
	}

	private void alterSomMouseEntered(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			alterSom.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/ligado claro.png")));
		} else {
			alterSom.setIcon(new javax.swing.ImageIcon(
					getClass().getResource("/Imagens/TelaConfiguracoes/desligado claro.png")));
		}
	}

	private void alterSomMouseExited(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			alterSom.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/ligado.png")));
		} else {
			alterSom.setIcon(
					new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/desligado.png")));
		}
	}

	private void okMouseEntered(java.awt.event.MouseEvent evt) {
		ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/ok grande claro.png")));
	}

	private void okMouseExited(java.awt.event.MouseEvent evt) {
		ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/TelaConfiguracoes/ok2.png")));
	}

	private void volumeMouseDragged(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			if ((evt.getXOnScreen() - this.getX() - pointVolume.x) >= 430
					&& (evt.getXOnScreen() - this.getX() - pointVolume.x) <= 630) {
				volume.setLocation(evt.getXOnScreen() - this.getX() - pointVolume.x, barraVolume.getY());
			}

			audio = volume.getLocation().x / 2 - 215;
		} else {
			new Thread(avisoChekSom).start();
		}
	}

	private void volumeMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			pointVolume = evt.getPoint();
		} else {
			new Thread(avisoChekSom).start();
		}

	}

	private void barraVolumeMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			volume.setLocation(evt.getXOnScreen() - this.getX() - volume.getWidth() / 2, barraVolume.getY());
			audio = volume.getLocation().x / 2 - 215;
		} else {
			new Thread(avisoChekSom).start();
		}
	}

	private void barraVolumeMouseDragged(java.awt.event.MouseEvent evt) {
		if (ativarSom) {
			if ((evt.getXOnScreen() - this.getX() - volume.getWidth() / 2) >= 430
					&& (evt.getXOnScreen() - this.getX() - volume.getWidth() / 2) <= 630) {
				volume.setLocation(evt.getXOnScreen() - this.getX() - volume.getWidth() / 2, barraVolume.getY());
			}
			audio = volume.getLocation().x / 2 - 215;
		} else {
			new Thread(avisoChekSom).start();
		}
	}

	private void barraHorasMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			controleHoras.setLocation(evt.getXOnScreen() - this.getX() - controleHoras.getWidth() / 2,
					barraHoras.getY());
			horas = (controleHoras.getLocation().x - 108) / 8;

			mudarHoras(horas);
			tempoIntacto = false;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void barraHorasMouseDragged(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			if ((evt.getXOnScreen() - this.getX() - controleHoras.getWidth() / 2) >= 101
					&& (evt.getXOnScreen() - this.getX() - controleHoras.getWidth() / 2) <= 300) {
				controleHoras.setLocation(evt.getXOnScreen() - this.getX() - controleHoras.getWidth() / 2,
						barraHoras.getY());
			}
			horas = (controleHoras.getLocation().x - 108) / 8;
			mudarHoras(horas);
			tempoIntacto = false;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void controleHorasMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			pointControle = evt.getPoint();
			tempoIntacto = false;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void controleHorasMouseDragged(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			if ((evt.getXOnScreen() - this.getX() - pointControle.x) >= 101
					&& (evt.getXOnScreen() - this.getX() - pointControle.x) <= 300) {
				controleHoras.setLocation(evt.getXOnScreen() - this.getX() - pointControle.x, barraHoras.getY());
			}
			horas = ((controleHoras.getLocation().x - 108) / 8);
			mudarHoras(horas);
			tempoIntacto = false;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void barraMinutosMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			controleMinutos.setLocation(evt.getXOnScreen() - this.getX() - controleMinutos.getWidth() / 2,
					barraMinutos.getY());
			minutos = (int) ((controleMinutos.getLocation().x - 101) / 3.3);
			mudarMinutos(minutos);
			tempoIntacto = false;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void barraMinutosMouseDragged(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			if ((evt.getXOnScreen() - this.getX() - controleMinutos.getWidth() / 2) >= 101
					&& (evt.getXOnScreen() - this.getX() - controleMinutos.getWidth() / 2) <= 300) {
				controleMinutos.setLocation(evt.getXOnScreen() - this.getX() - controleMinutos.getWidth() / 2,
						barraMinutos.getY());
			}
			minutos = (int) ((controleMinutos.getLocation().x - 101) / 3.3);
			mudarMinutos(minutos);
			tempoIntacto = false;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void controleMinutosMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			pointControle = evt.getPoint();
			tempoIntacto = false;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void controleMinutosMouseDragged(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			if ((evt.getXOnScreen() - this.getX() - pointControle.x) >= 101
					&& (evt.getXOnScreen() - this.getX() - pointControle.x) <= 300) {
				controleMinutos.setLocation(evt.getXOnScreen() - this.getX() - pointControle.x, barraMinutos.getY());
			}
			minutos = (int) ((controleMinutos.getLocation().x - 101) / 3.3);
			mudarMinutos(minutos);
			tempoIntacto = false;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void barraSegundosMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			controleSegundos.setLocation(evt.getXOnScreen() - this.getX() - controleSegundos.getWidth() / 2,
					barraSegundos.getY());
			segundos = (int) ((controleSegundos.getLocation().x - 101) / 3.3);
			mudarSegundos(segundos);
			tempoIntacto = false;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void barraSegundosMouseDragged(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			if ((evt.getXOnScreen() - this.getX() - controleSegundos.getWidth() / 2) >= 101
					&& (evt.getXOnScreen() - this.getX() - controleSegundos.getWidth() / 2) <= 300) {
				controleSegundos.setLocation(evt.getXOnScreen() - this.getX() - controleSegundos.getWidth() / 2,
						barraSegundos.getY());
			}
			segundos = (int) ((controleSegundos.getLocation().x - 101) / 3.3);
			mudarSegundos(segundos);
			tempoIntacto = false;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void controleSegundosMousePressed(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			pointControle = evt.getPoint();
			tempoIntacto = false;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

	private void controleSegundosMouseDragged(java.awt.event.MouseEvent evt) {
		if (ativarAcao) {
			if ((evt.getXOnScreen() - this.getX() - pointControle.x) >= 101
					&& (evt.getXOnScreen() - this.getX() - pointControle.x) <= 300) {
				controleSegundos.setLocation(evt.getXOnScreen() - this.getX() - pointControle.x, barraSegundos.getY());
			}
			segundos = (int) ((controleSegundos.getLocation().x - 101) / 3.3);
			mudarSegundos(segundos);
			tempoIntacto = false;
		} else {
			new Thread(avisoChekAcao).start();
		}
	}

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
					"Erro em TC\nOcorreu um erro na troca do LookAndFeel. Por favor contate o desenvolver no painel "
							+ "principal através do menu informações no canto superior esquerdo.",
					"Erro", 0);
		}
		java.awt.EventQueue.invokeLater(() -> {
			new TelaConfiguracoes().setVisible(true);
		});
	}

	private javax.swing.JPanel Tela;
	private javax.swing.JLabel acao;
	private javax.swing.JLabel alterAcao;
	private javax.swing.JLabel alterSom;
	private javax.swing.JLabel altoFalante;
	private javax.swing.JLabel barraTitulo;
	private javax.swing.JLabel barraVolume;
	private javax.swing.JLabel barraHoras;
	private javax.swing.JLabel barraMinutos;
	private javax.swing.JLabel barraSegundos;
	private javax.swing.JLabel chuva;
	private javax.swing.JLabel desligar;
	private javax.swing.JLabel eletronica;
	private javax.swing.JLabel floresta;
	private javax.swing.JLabel fogo;
	private javax.swing.JLabel fundo;
	private javax.swing.JLabel fundoAcao;
	private javax.swing.JLabel fundoSom;
	private javax.swing.JLabel hipHop;
	private javax.swing.JLabel jazz;
	private javax.swing.JLabel ok;
	private javax.swing.JLabel reiniciar;
	private javax.swing.JLabel separador1;
	private javax.swing.JLabel separador2;
	private javax.swing.JLabel som;
	private javax.swing.JLabel volume;
	private JLabel labelSegundos;
	private JLabel labelMinutos;
	private JLabel labelHoras;
	private JLabel labelDesligar;
	private JLabel labelReiniciar;
	private JLabel controleHoras;
	private JLabel controleMinutos;
	private JLabel controleSegundos;
	private JLabel campoMinutos;
	private JLabel campoHoras;
	private JLabel campoSegundos;
}
