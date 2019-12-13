package outils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.bluetooth.RemoteDevice;
import javax.swing.table.AbstractTableModel;

import ui.RemoteDeviceDiscoverer;
import object.ConnexionBDD;
public class ListClient extends AbstractTableModel {

	private  String[] entetes ={"Nom","Prenom","Mail"};
    private List<FicheClient> list;
   
    
    // La, justilise pour le test la liste des BluetoothDevice
    

   
    public ListClient() throws IOException, InterruptedException, ClassNotFoundException, SQLException {
    		super();
    		ConnexionBDD con = new ConnexionBDD();
    		entetes =new String[]{"Adresse MAC","Nom","Prenom","Mail"};
    		list = con.displayAll();
    		
    		
    		con.connexionClose();
    }
    
    /**
     * Retourne le nombre de ligne
     */
    public int getRowCount() {
        return list.size();
    }
 
    /**
     * Retourne le nombre de colonne
     */
    public int getColumnCount() {
        return entetes.length;
    }
 
    /**
     * Retourne le nom du numéro de la colonne
     */
    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }
 
    /**
     * Affecte les valeurs des object aux colonnes du tableau
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
		
        switch(columnIndex){
        	case 0:
        		return list.get(rowIndex).getAdresse_mac();
            case 1:
                return list.get(rowIndex).getNom();
            case 2:
				return list.get(rowIndex).getPrenom();
            case 3:
				return list.get(rowIndex).getMail();
            default:
                return null; 

        }
        
    }
    
    public void removeDevice(int rowIndex) {
    		list.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, SQLException {
		new ListClient();
	}

}
