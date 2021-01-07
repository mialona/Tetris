package dataAccess;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesAccess {
	private int port;
	private int maxRequests;
	
	public PropertiesAccess() {
		File file = new File("conexion.properties");
		Properties properties = new Properties();
		
		try {
			if(file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				properties.load(fis);
				fis.close();
			}
			else {
				properties.setProperty("port", "");
				properties.setProperty("maxRequests", "");
				
				FileOutputStream fos = new FileOutputStream(file);
				properties.store(fos, "Conexion roperties");
				fos.close();
			}
			
			String port = properties.getProperty("port");
			if(port.isEmpty()) {
				this.port = 3300;
			}
			else {
				this.port = Integer.parseInt(port);
			}
			
			String maxRequests = properties.getProperty("maxRequests");
			if(maxRequests.isEmpty()) {
				this.maxRequests = 20;
			}
			else {
				this.maxRequests = Integer.parseInt(maxRequests);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}

	public int getPort() {
		return port;
	}

	public int getMaxRequests() {
		return maxRequests;
	}
	
}
