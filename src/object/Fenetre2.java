package object;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre2 {

	private String adresseMac;
	private String deviceName;
	private JFrame fenetre2;
	private JPanel pan1;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label5;
	private JLabel label6;
	private JLabel label7;
	private JLabel label8;
	private JLabel label9;
	private JLabel label10;
	private Font font1;
	private JPanel boutons;
	private GridLayout grid;

	public Fenetre2(String adresseMac, String deviceName) throws ClassNotFoundException, SQLException {
		this.adresseMac=adresseMac;
		this.deviceName=deviceName;
		fenetre2 = new JFrame("BluetoothDevice");
		pan1 = new JPanel();
		System.out.println(adresseMac);
		fenetre2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fenetre2.setLocation(500, 200); 
		fenetre2.setSize(600,700);
		fenetre2.setVisible(true);

		grid= new GridLayout(5,2);
		grid.setHgap(10);
		grid.setVgap(10);
		pan1.setLayout(grid);

		ConnexionBDD con = new ConnexionBDD();

		label1= new JLabel("     Adresse MAC : ");
		label3= new JLabel("     Nom du téléphone : ");
		label5= new JLabel("     Nombre de visite : ");
		label7= new JLabel("     Derniere visite : ");
		label9= new JLabel("     Fiche créee");

		boutons = new JPanel();

		if(con.selectedFiche("adresseMac", adresseMac) != null) {
			label2= new JLabel(con.selectedPeriph("adresseMac", adresseMac));
			label4= new JLabel(deviceName);
			label6= new JLabel(con.selectedPeriph("nombreVisite", adresseMac));
			label8= new JLabel(con.selectedPeriph("derniereVisite", adresseMac));
			label10= new JLabel("oui");
			boutons.add(new JButton(new CheckFiche()));
		}else {
			label2= new JLabel(adresseMac);
			label4= new JLabel(deviceName);
			label6= new JLabel("0");
			label8= new JLabel("");
			label10= new JLabel("non");
			boutons.add(new JButton(new CreateFiche()));

		}
		font1 = new Font("Arial", Font.PLAIN, 26);
		label1.setFont(font1);
		label2.setFont(font1);
		label3.setFont(font1);
		label4.setFont(font1);
		label5.setFont(font1);
		label6.setFont(font1);
		label7.setFont(font1);
		label8.setFont(font1);
		label9.setFont(font1);
		label10.setFont(font1);

		pan1.add(label1);
		pan1.add(label2);
		pan1.add(label3);
		pan1.add(label4);
		pan1.add(label5);
		pan1.add(label6);
		pan1.add(label7);
		pan1.add(label8);
		pan1.add(label9);
		pan1.add(label10);

		fenetre2.getContentPane().add(pan1,BorderLayout.CENTER);
		fenetre2.getContentPane().add(boutons, BorderLayout.SOUTH);
		con.connexionClose();
	}

	public String getAdresseMac() {
		return adresseMac;
	}
	public String deviceName() {
		return deviceName;
	}

	private class CheckFiche extends AbstractAction {
		private CheckFiche() {
			super("Voir la fiche client");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				Fenetre3 frame3 = new Fenetre3(getAdresseMac());
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			fenetre2.dispose();
		}
	}

	private class CreateFiche extends AbstractAction {
		private CreateFiche() {
			super("Créer une fiche client");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				Fenetre4 frame4 = new Fenetre4(getAdresseMac());
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			fenetre2.dispose();
		}
	}

}


