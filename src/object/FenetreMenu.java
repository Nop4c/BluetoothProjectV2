package object;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class FenetreMenu {
	private JFrame fenetreMenu;
	private Font font1;
	private Font font2;
	private JButton button1;
	private JButton button2;
	private JLabel label1;

	public FenetreMenu(){
		fenetreMenu = new JFrame("Menu");
		fenetreMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fenetreMenu.setLocation(500, 200); 
		fenetreMenu.setSize(1100,700);
		fenetreMenu.setVisible(true);
		fenetreMenu.setLayout(null);
		fenetreMenu.setResizable(false);

		button1 = new JButton(new FichesClients());
		button2 = new JButton(new DetectionBluetooth());

		label1 = new JLabel("Bienvenue dans le gestionnaire de fiches clients");
		font1 = new Font("Serial", Font.PLAIN, 26);
		font2 = new Font("Serial", Font.BOLD, 30);
		button1.setFont(font1);
		button2.setFont(font1);
		label1.setFont(font2);
		label1.setBounds(200,120,800, 50);
		button1.setBounds(30,300,500, 300);
		button2.setBounds(550,300,500, 300);

		fenetreMenu.getContentPane().add(label1);
		fenetreMenu.getContentPane().add(button1);
		fenetreMenu.getContentPane().add(button2);	

	}

	private class FichesClients extends AbstractAction {
		private FichesClients() {
			super("Consulter les fiches clients");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				FenetreList list = new FenetreList();
			} catch (ClassNotFoundException | IOException | InterruptedException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}


	private class DetectionBluetooth extends AbstractAction {
		private DetectionBluetooth() {
			super("Détecter les périphériques bluetooth");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				Fenetre fen = new Fenetre();
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}

		}
	}

}


