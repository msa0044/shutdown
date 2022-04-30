package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Desktop;

public class TelaInfo extends JFrame {
	private TelaPrincipal p;

	// método a ser usado pela tela Principal
	public void mandarFrame(TelaPrincipal p) {
		this.p = p;
		this.setVisible(true);
	}

	// método de declaração das variaveis swing
	public TelaInfo() {
		addMouseListener(new MouseAdapter() {

		});
		getContentPane().addMouseListener(new MouseAdapter() {

		});
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 162, 81);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panelMouseExited(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				panelMouseEntered(e);
			}
		});
		panel.setBorder(new LineBorder(new Color(33, 33, 33), 2));
		panel.setBounds(0, 0, 162, 82);
		panel.setBackground(new Color(18, 18, 18));
		getContentPane().add(panel);
		panel.setLayout(null);

		JLabel separador = new JLabel("");
		separador.setIcon(new ImageIcon(TelaInfo.class.getResource("/Imagens/TelaConfiguracoes/barraseparacao.png")));
		separador.setBounds(10, 39, 142, 3);
		panel.add(separador);

		JLabel labelComoUsar = new JLabel("Como usar - tutorial");
		labelComoUsar.setForeground(Color.WHITE);
		labelComoUsar.setHorizontalAlignment(SwingConstants.LEFT);
		labelComoUsar.setFont(new Font("Oswald", Font.PLAIN, 14));
		labelComoUsar.setBounds(10, 10, 114, 25);
		panel.add(labelComoUsar);

		JLabel labelDev = new JLabel("Desenvolvedor");
		labelDev.setForeground(Color.WHITE);
		labelDev.setHorizontalAlignment(SwingConstants.LEFT);
		labelDev.setFont(new Font("Oswald", Font.PLAIN, 14));
		labelDev.setBounds(10, 45, 74, 25);
		panel.add(labelDev);

		a = new JLabel("");
		a.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				aMouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				aMouseExited(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				aMouseClicked(e);
			}
		});
		a.setIcon(new ImageIcon(TelaInfo.class.getResource("/Imagens/TelaPrincipal/arquivo de link (1).png")));
		a.setBounds(128, 0, 24, 43);
		panel.add(a);

		i = new JLabel("");
		i.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				iMouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				iMouseExited(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				iMouseClicked(e);
			}
		});
		i.setIcon(new ImageIcon(TelaInfo.class.getResource("/Imagens/TelaPrincipal/instagram.png")));
		i.setBounds(128, 39, 24, 43);
		panel.add(i);

		w = new JLabel("");
		w.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				wMouseEntered(e);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				wMouseExited(e);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				wMouseClicked(e);
			}
		});
		w.setIcon(new ImageIcon(TelaInfo.class.getResource("/Imagens/TelaPrincipal/whatsapp.png")));
		w.setBounds(94, 39, 24, 43);
		panel.add(w);

	}

	// metodos de eventos dos icone do Jframe
	private void aMouseClicked(java.awt.event.MouseEvent evt) {
		try {
			String s = new File("").getAbsolutePath();
			File link = new File((s + "\\src\\Html\\Index.html"));
			Desktop.getDesktop().browse(link.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.dispose();
		p.setInfoAtivo(false);
	}

	private void wMouseClicked(java.awt.event.MouseEvent evt) {
		try {
			URI link = new URI("https://api.whatsapp.com/send?phone=5533999261087&text=Olá%20Marcos!");
			Desktop.getDesktop().browse(link);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		this.dispose();
		p.setInfoAtivo(false);
	}

	private void iMouseClicked(java.awt.event.MouseEvent evt) {
		try {
			URI link = new URI("https://www.instagram.com/marcossimoesaraujo/");
			Desktop.getDesktop().browse(link);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		this.dispose();
		p.setInfoAtivo(false);
	}

	private void aMouseEntered(java.awt.event.MouseEvent evt) {
		a.setIcon(new ImageIcon(TelaInfo.class.getResource("/Imagens/TelaPrincipal/arquivo de link.png")));
	}

	private void aMouseExited(java.awt.event.MouseEvent evt) {
		a.setIcon(new ImageIcon(TelaInfo.class.getResource("/Imagens/TelaPrincipal/arquivo de link (1).png")));
	}

	private void wMouseEntered(java.awt.event.MouseEvent evt) {
		w.setIcon(new ImageIcon(TelaInfo.class.getResource("/Imagens/TelaPrincipal/whatsapp (1).png")));
	}

	private void wMouseExited(java.awt.event.MouseEvent evt) {
		w.setIcon(new ImageIcon(TelaInfo.class.getResource("/Imagens/TelaPrincipal/whatsapp.png")));
	}

	private void iMouseEntered(java.awt.event.MouseEvent evt) {
		i.setIcon(new ImageIcon(TelaInfo.class.getResource("/Imagens/TelaPrincipal/instagram (1).png")));
	}

	private void iMouseExited(java.awt.event.MouseEvent evt) {
		i.setIcon(new ImageIcon(TelaInfo.class.getResource("/Imagens/TelaPrincipal/instagram.png")));
	}

	private void panelMouseEntered(java.awt.event.MouseEvent evt) {
	}

	private void panelMouseExited(java.awt.event.MouseEvent evt) {
		int pX = evt.getXOnScreen();
		int pY = evt.getYOnScreen();
		int xMin = p.getX();
		int xMax = p.getX() + this.getWidth();
		int yMin = p.getY();
		int yMax = p.getY() + this.getHeight();

		if (pX - 1 < xMin || pX + 1 > xMax || pY - 1 < yMin || pY + 1 > yMax) {
			this.dispose();
			p.setInfoAtivo(false);
		}
	}

	// método principal
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInfo frame = new TelaInfo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// declarações das variaveis swing de forma global
	private JLabel a; // variavel para o icone que abre o arquivo html
	private JLabel w; // variavel para o icone que abre o whatsapp
	private JLabel i; // variavel para o icone que abre o instagram
}
