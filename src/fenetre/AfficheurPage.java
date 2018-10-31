
package fenetre;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

public class AfficheurPage extends JEditorPane
{
	
    void showError(String errorMessage) 
    {
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
     
    static URL verifyUrl(String url)
    {
        if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://"))
        	url="http://"+url;
         
        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(url);
        } catch (Exception e) {
            return null;
        }
         
        return verifiedUrl;
    }

    public AfficheurPage() 
    { 
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = (int)screenSize.getHeight();
        int screenWidth  = (int)screenSize.getWidth();
        
    	this.setPreferredSize(new Dimension(screenWidth-40,screenHeight-40));
        try
        {
          this.setPage(new URL("http://www.google.com"));
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), e.getMessage(), JOptionPane.ERROR_MESSAGE);
            System.exit(ERROR);
        }
        
        

    }
}

