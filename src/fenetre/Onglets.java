package fenetre;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;

public class Onglets extends JScrollPane
{
	AfficheurPage page;
	ArrayList listUrlOnglet = new ArrayList();
	URL urlCurrent;
	
	public Onglets()
	{
		page=new AfficheurPage();
        page.setContentType("text/html");
        page.setEditable(false);
		
		this.setViewportView(page);
		
	}
	
	void setNewPage(URL url, boolean add)
	{
		
    	if(url !=null)
    	{
          try                      { this.page.setPage(url);} 
          catch (IOException e1)   { this.page.showError("Unable to load page"); }
        }
    	else   this.page.showError("Invalid URL");
    	this.urlCurrent=url;
    	
     	if(add==true) 
     	{
     		listUrlOnglet.add(url.toString());
     	}
     	
		this.setViewportView(page);
    	
	}
	
	
}
