package ui;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;


/**
 * Minimal Device Discovery example.
 */
public class RemoteDeviceDiscoverer{

//	private final Vector devicesDiscovered = new Vector();
	List <RemoteDevice> test = new ArrayList<RemoteDevice>();
	public void searchDevices() throws IOException, InterruptedException {

		final Object inquiryCompletedEvent = new Object();

//		devicesDiscovered.clear();

		DiscoveryListener listener = new DiscoveryListener() {

			public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
				try {
				System.out.println("Device " + btDevice.getBluetoothAddress() + " found"+"     name " + btDevice.getFriendlyName(false));
//				devicesDiscovered.addElement(btDevice);
				test.add(btDevice);
				} catch (IOException cantGetDeviceName) {
				}
			}

			public void inquiryCompleted(int discType) {
				System.out.println("Device Inquiry completed!");
				synchronized(inquiryCompletedEvent){
					inquiryCompletedEvent.notifyAll();
				}
			}

			public void serviceSearchCompleted(int transID, int respCode) {
			}

			public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
			}
		};

		synchronized(inquiryCompletedEvent) {
			boolean started = LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, listener);
			if (started) {
				//wait for device inquiry to complete
				inquiryCompletedEvent.wait();
				System.out.println(test.size() +  " device(s) found");
			}
		}
	}
	public List<RemoteDevice> getTest() {
		return test;
	}
	public void setTest(List<RemoteDevice> test) {
		this.test = test;
	}

	


}
