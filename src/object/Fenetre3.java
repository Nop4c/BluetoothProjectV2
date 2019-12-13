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

import outils.Sms;


public class Fenetre3 {
	private JFrame fenetre3;
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
	private JLabel label11;
	private JLabel label12;
	private JLabel label13;
	private JLabel label14;
	private JLabel label15;
	private JLabel label16;
	private Font font1;
	private GridLayout grid;
	private JPanel boutons;
	private String adresseMac;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	
	public Fenetre3(String adresseMac) throws ClassNotFoundException, SQLException {
		this.adresseMac=adresseMac;
		fenetre3 = new JFrame("Fiche client");
		pan1 = new JPanel();

		grid= new GridLayout(8,2);
		grid.setHgap(10);
		grid.setVgap(10);
		pan1.setLayout(grid);

		fenetre3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fenetre3.setLocation(500, 200); 
		fenetre3.setSize(700,800);
		fenetre3.setVisible(true);
		ConnexionBDD con = new ConnexionBDD();

		label1= new JLabel("     Adresse MAC : ");
		label3= new JLabel("     Nom  : ");
		label5= new JLabel("     Prenom : ");
		label7= new JLabel("     Ville, adresse : ");
		label9= new JLabel("     Mail : ");
		label11= new JLabel("     Numero de telephone : ");
		label13= new JLabel("     Genre : ");
		label15= new JLabel("     Date de naissance : ");

		label2= new JLabel(con.selectedFiche("adresseMac", adresseMac));
		label4= new JLabel(con.selectedFiche("nom", adresseMac));
		label6= new JLabel(con.selectedFiche("prenom", adresseMac));
		label8= new JLabel(con.selectedFiche("adresse", adresseMac));
		label10= new JLabel(con.selectedFiche("adresseMail", adresseMac));
		label12= new JLabel(con.selectedFiche("telephone", adresseMac));
		label14= new JLabel(con.selectedFiche("genre", adresseMac));
		label16= new JLabel(con.selectedFiche("dateNaissance", adresseMac));

		font1 = new Font("Serial", Font.PLAIN, 26);
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
		label11.setFont(font1);
		label12.setFont(font1);
		label13.setFont(font1);
		label14.setFont(font1);
		label15.setFont(font1);
		label16.setFont(font1);

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
		pan1.add(label11);
		pan1.add(label12);
		pan1.add(label13);
		pan1.add(label14);
		pan1.add(label15);
		pan1.add(label16);

		boutons = new JPanel();
		button1 = new JButton(new Visite());
		button2 = new JButton(new SendMail());
		button3 = new JButton(new SendSMS());
		boutons.add(button1);
		boutons.add(button2);
		boutons.add(button3);

		con.connexionClose();

		fenetre3.getContentPane().add(pan1,BorderLayout.CENTER);
		fenetre3.getContentPane().add(boutons, BorderLayout.SOUTH);
	}
	
	public String getAdresseMac() {
		return adresseMac;
	}

	private class Visite extends AbstractAction {
		private Visite() {
			super("Visite");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				ConnexionBDD con = new ConnexionBDD();
				con.UpdatePeriphVisiteDate(getAdresseMac());
				String text ="Pour vous remercier de votre visite, voici  -10 % de remise sur le pack EPSI !!";
				String nbVisite =con.selectedPeriph("nombreVisite", adresseMac);
				if(nbVisite.equals("3") || nbVisite.equals("8")|| nbVisite.equals("12")) {
					Sms sms = new Sms();
					sms.sendSms(label12.getText(), text);
				}
				con.connexionClose();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class SendMail extends AbstractAction {
		private SendMail() {
			super("Envoyer un mail");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				FenetreMail sendMail = new FenetreMail(label10.getText());
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private class SendSMS extends AbstractAction {
		private SendSMS() {
			super("Envoyer un SMS");
		}

		public void actionPerformed(ActionEvent e) {
			try {
				FenetreSms sendSms = new FenetreSms(label12.getText());
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
