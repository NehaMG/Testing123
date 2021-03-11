package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class S1_ReadPropertyFile
{
	public Properties properties;
	
	public S1_ReadPropertyFile() throws IOException {
		properties = new Properties();
		FileInputStream fis = new FileInputStream("config.properties");
		properties.load(fis);
		fis.close();
	}

	public String getProperties(String key) {
		String property = properties.getProperty(key);
		if(property!= null) 
			return property;
		else 
			throw new RuntimeException(property + " not specified in the Configuration.properties file."); 	
	}
	
}
