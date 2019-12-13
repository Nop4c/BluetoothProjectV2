package object;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import outils.Sms;

public class FenetreSms {
	private JFrame fenetreSms;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JTextArea textArea1;
	private Font font1;
	private String numero;
	private JButton button;

	public FenetreSms(String numero) throws ClassNotFoundException, SQLException {
		this.numero=numero;
		fenetreSms = new JFrame("Sms");
		fenetreSms.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fenetreSms.setLocation(500, 200); 
		fenetreSms.setSize(900,600);
		fenetreSms.setVisible(true);
		fenetreSms.setLayout(null);
		fenetreSms.setResizable(false);
		label1= new JLabel("Au : " );
		label2= new JLabel(numero);
		label3= new JLabel("Message : ");
		textArea1= new JTextArea();
		button = new JButton(new Envoyer());

		font1 = new Font("Arial", Font.PLAIN, 26);
		label1.setFont(font1);
		label2.setFont(font1);
		label3.setFont(font1);
		textArea1.setFont(font1);
		textArea1.setLineWrap(true);
		button.setFont(font1);
		label1.setBounds(20,10,300, 50);
		label2.setBounds(150,10,300, 50);
		label3.setBounds(20,60,300, 50);
		textArea1.setBounds(150,70,700, 350);
		button.setBounds(150,450,300, 50);

		fenetreSms.getContentPane().add(label1);
		fenetreSms.getContentPane().add(label2);
		fenetreSms.getContentPane().add(label3);
		fenetreSms.getContentPane().add(textArea1);
		fenetreSms.getContentPane().add(button);
	}


	private class Envoyer extends AbstractAction {
		private Envoyer() {
			super("Envoyer");
		}

		public void actionPerformed(ActionEvent e) {
			Sms sms = new Sms();			
			sms.sendSms(numero,textArea1.getText());
			if(sms.getValidation()) {
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null,"Message envoyé","",JOptionPane.INFORMATION_MESSAGE);
				fenetreSms.dispose();
			}else {
				JOptionPane jop = new JOptionPane();
				jop.showMessageDialog(null,"Mauvais numero","ERROR",JOptionPane.ERROR_MESSAGE);
			}

		}
	}

}
