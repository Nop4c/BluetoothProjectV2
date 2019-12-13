package outils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.bluetooth.RemoteDevice;
import javax.swing.table.AbstractTableModel;

import object.BluetoothDevice;
import ui.RemoteDeviceDiscoverer;

public class DetectedDevicePanel extends AbstractTableModel {
	String[] entetes ={"Periphérique","Nom du telephone"};
    
    RemoteDeviceDiscoverer remoteDeviceDiscoverer;
   
    
    // La, justilise pour le test la liste des BluetoothDevice
    private static List <RemoteDevice> bluetoothDeviceList;
    

   
    public DetectedDevicePanel() throws IOException, InterruptedException {
    		super();
    		entetes =new String[]{"Périphérique","Nom de l'appareil"};
    		remoteDeviceDiscoverer = new RemoteDeviceDiscoverer();
    		remoteDeviceDiscoverer.searchDevices();
    		bluetoothDeviceList = new ArrayList<RemoteDevice>();
    		bluetoothDeviceList = remoteDeviceDiscoverer.getTest();
    }
    
    /**
     * Retourne le nombre de ligne
     */
    public int getRowCount() {
        return bluetoothDeviceList.size();
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
                return bluetoothDeviceList.get(rowIndex).getBluetoothAddress();
            case 1:
			try {
				return bluetoothDeviceList.get(rowIndex).getFriendlyName(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
            default:
                return null; 
        }
    }
    
    public void removeDevice(int rowIndex) {
    		bluetoothDeviceList.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }


}
