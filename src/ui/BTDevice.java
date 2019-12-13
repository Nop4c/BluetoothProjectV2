package ui;

public class BTDevice {

	private String name;
	private String BTURL;
	
	public BTDevice(String name,String BTURL) {
		this.name=name;
		this.BTURL=BTURL;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBTURL() {
		return BTURL;
	}

	public void setBTURL(String bTURL) {
		BTURL = bTURL;
	}
	
	
	
}
