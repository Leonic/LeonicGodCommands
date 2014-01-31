package uk.Leonic.GodCommands;

import java.net.*;
import java.io.*;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class Update {
    
	private Main plugin;
	private URL filesFeed;
	
	private String version;
	private String link;
	
	public Update(Main plugin, String url){
		this.plugin = plugin;
		
		try {
			this.filesFeed = new URL(url);
		} catch (MalformedURLException e) {
			plugin.log.info("Whoa. Something Went Wrong...");
			e.printStackTrace();
		}
	}
	
	public boolean updateNeeded(){
		try {
			InputStream Input = this.filesFeed.openConnection().getInputStream();
		    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(Input);
		    
		    Node latestFile = document.getElementsByTagName("item").item(0);
		    NodeList children = latestFile.getChildNodes();
		    
		    this.version = children.item(1).getTextContent().replaceAll("[a-zA-Z ]", "");
		    this.link = children.item(3).getTextContent();
		    
		    if(plugin.getDescription().getVersion().equals(this.version)){
		    	return true;
		    }
		} catch (Exception e) {
			plugin.log.info("Whoa. Something Went Wrong...");
			e.printStackTrace();
		}
		
		return false;
	}
	
	public String getVersion(){
		return this.version;
	}
	
	public String getLink(){
		return this.link;
	}
}
