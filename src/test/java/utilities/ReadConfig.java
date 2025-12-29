package utilities;

import java.io.FileInputStream;
import java.util.Properties;


public class ReadConfig {
	
	Properties ps;
	
	public ReadConfig() {
		
		try {
			
			ps = new Properties();
			FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\configuration\\config.properties");
//			FileInputStream fis= new FileInputStream("D:\\Work_Space\\Project3\\configuration\\congif.properties");
			ps.load(fis);

		} catch (Exception e) {
			System.out.println("error :"+e);
		}
	}
	
	public String GetUrl() {
		return ps.getProperty("url");
	}
	
	public String GetBrowser() {
		return ps.getProperty("browser");
	}	
	
	public String SystemInfo(String key) {
		return ps.getProperty(key);
	}
	
	public String GetEmail() {
		return ps.getProperty("email");
	}
	
	public String GetPassword() {
		return ps.getProperty("password");
	}
	
}
