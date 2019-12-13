package object;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import outils.Mail;


public class FenetreMail {
	private JFrame fenetreMail;
	private JPanel pan1;
	private JPanel pan2;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JTextArea textArea1;
	private JTextArea textArea2;
	private Font font1;
	private String email;
	private JButton button;
	
	public FenetreMail(String email) throws ClassNotFoundException, SQLException {
		this.email=email;
		fenetreMail = new JFrame("Mail");
		pan1 = new JPanel();
		pan2 = new JPanel();
		fenetreMail.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fenetreMail.setLocation(500, 200); 
		fenetreMail.setSize(900,600);
		fenetreMail.setVisible(true);
		fenetreMail.setLayout(null);
		fenetreMail.setResizable(false);
		label1= new JLabel("Pour : " );
		label2= new JLabel(email);
		label3= new JLabel("Objet : ");
		label4= new JLabel("Message : ");
		textArea1= new JTextArea();
		textArea2= new JTextArea();
		button = new JButton(new Envoyer());
		
		font1 = new Font("Arial", Font.PLAIN, 26);
		label1.setFont(font1);
		label2.setFont(font1);
		label3.setFont(font1);
		label4.setFont(font1);
		textArea1.setFont(font1);
		textArea2.setFont(font1);
		textArea2.setLineWrap(true);
		button.setFont(font1);
		label1.setBounds(20,10,300, 50);
		label2.setBounds(150,10,300, 50);
		label3.setBounds(20,60,300, 50);
		textArea1.setBounds(150,65,700, 40);
		label4.setBounds(20,110,300, 50);
		textArea2.setBounds(150,120,700, 350);
		
		button.setBounds(150,500,300, 50);
		
		fenetreMail.getContentPane().add(label1);
		fenetreMail.getContentPane().add(label2);
		fenetreMail.getContentPane().add(label3);
		fenetreMail.getContentPane().add(textArea1);
		fenetreMail.getContentPane().add(label4);
		fenetreMail.getContentPane().add(textArea2);
		fenetreMail.getContentPane().add(button);

		}
	
	private class Envoyer extends AbstractAction {
		private Envoyer() {
			super("Envoyer");
		}

		public void actionPerformed(ActionEvent e) {
			Mail mail = new Mail();
			mail.envoie(email, textArea1.getText(), textArea2.getText());
			JOptionPane jop = new JOptionPane();
    		jop.showMessageDialog(null,"Message envoyé","",JOptionPane.INFORMATION_MESSAGE);
    		fenetreMail.dispose();
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		FenetreMail fen =new FenetreMail("test");
		
		
	}
}
