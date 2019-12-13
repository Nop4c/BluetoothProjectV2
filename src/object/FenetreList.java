package object;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import outils.ListClient;

public class FenetreList {

	private JTable tableau;
	private JScrollPane scrollPane;
	private JFrame fenetreList;
	private Font font;
	private Font font2;

	public FenetreList() throws ClassNotFoundException, IOException, InterruptedException, SQLException{
		fenetreList = new JFrame("Liste des clients");
		fenetreList.setSize(900, 700);
		fenetreList.setLocation(550, 200);
		fenetreList.setVisible(true);
		fenetreList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 

		tableau = new JTable(new ListClient());
		font = new Font("Serial", Font.PLAIN, 26);
		font2 = new Font("Serial", Font.BOLD, 30);

		tableau.setFont(font);
		tableau.setRowHeight(30);
		tableau.getTableHeader().setFont(font2);
		tableau.getTableHeader().setPreferredSize(new Dimension( tableau.getTableHeader().getWidth(),35));
		scrollPane =new JScrollPane(tableau);
		fenetreList.getContentPane().add(scrollPane, BorderLayout.CENTER);

		JPanel boutons = new JPanel();
		JButton button1 = new JButton(new VoirFiche());
		JButton button2 = new JButton(new ModifierFiche());
		JButton button3 = new JButton(new SupprimerFiche());
		JButton button4 = new JButton(new Actualiser());
		button1.setFont(font);
		button2.setFont(font);
		button3.setFont(font);
		button4.setFont(font);
		boutons.add(button1);
		boutons.add(button2);
		boutons.add(button3);
		boutons.add(button4);

		fenetreList.getContentPane().add(boutons, BorderLayout.SOUTH);
	}

	private class VoirFiche extends AbstractAction {
		private VoirFiche() {
			super("Voir la fiche");
		}
		public void actionPerformed(ActionEvent e) {
			if(tableau.getSelectedColumnCount() != 0) {
				int selection = tableau.getSelectedRow();
				String adresseMac = (String) tableau.getValueAt(selection, 0);
				try {

					Fenetre3 frame3 = new Fenetre3(adresseMac);
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}else {
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null,"Sélectioner un client","Attention",JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private class SupprimerFiche extends AbstractAction {
		private SupprimerFiche() {
			super("Supprimer la fiche");
		}
		public void actionPerformed(ActionEvent e) {
			JOptionPane jop = new JOptionPane();
			if(tableau.getSelectedColumnCount() != 0) {
				int reply =jop.showConfirmDialog(null,"Etes vous sur de vouloir supprimer ce cette fiche? ","Attention",JOptionPane.YES_NO_OPTION);
				if( reply == jop.YES_OPTION) {
					int selection = tableau.getSelectedRow();
					String adresseMac = (String) tableau.getValueAt(selection, 0);
					try {
						ConnexionBDD con = new ConnexionBDD();
						con.deleteFicheClient(adresseMac);
						con.deletePeriph(adresseMac);
						con.connexionClose();
						tableau.setModel(new ListClient());
						scrollPane.setViewportView(tableau);
					} catch (ClassNotFoundException | SQLException | IOException | InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}else {
				JOptionPane jop2 = new JOptionPane();
				jop2.showMessageDialog(null,"Sélectioner un client","Attention",JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private class ModifierFiche extends AbstractAction {
		private ModifierFiche() {
			super("Modifier la fiche");
		}
		public void actionPerformed(ActionEvent e) {
			JOptionPane jop = new JOptionPane();
			if(tableau.getSelectedColumnCount() != 0) {
				int reply =jop.showConfirmDialog(null,"Etes vous sur de vouloir modifier ce cette fiche? ","Attention",JOptionPane.YES_NO_OPTION);
				if( reply == jop.YES_OPTION) {
					int selection = tableau.getSelectedRow();
					String adresseMac = (String) tableau.getValueAt(selection, 0);
					try {
						Fenetre5 fen5 = new Fenetre5(adresseMac);
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
									}
			}else {
				JOptionPane jop2 = new JOptionPane();
				jop2.showMessageDialog(null,"Sélectioner un client","Attention",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	private class Actualiser extends AbstractAction {
		private Actualiser() {
			super("Actualiser");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				tableau.setModel(new ListClient());
			} catch (ClassNotFoundException | IOException | InterruptedException | SQLException e1) {
				e1.printStackTrace();
			}
			scrollPane.setViewportView(tableau);
		}
	}
}
