package fenetre;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;



public class PanelPrincipal
{
	JPanel panelPrincipal = new JPanel();
    BarRecherche barRecherche = new BarRecherche();
    Onglets pageAcceuil=new Onglets();
    JTabbedPaneWithCloseIcons toutesLesPages;
	
	public PanelPrincipal()
	{
		 FlowLayout lprincipale = new FlowLayout();		 
		 lprincipale.setHgap(0);
		 lprincipale.setVgap(0);

	        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	        int screenHeight = (int)screenSize.getHeight();
	        int screenWidth  = (int)screenSize.getWidth();
		//----------------------------------- Première partie + Layout ---------------------------------------------
		
	    
		this.panelPrincipal.setLayout(lprincipale);
          
        this.panelPrincipal.add(barRecherche);

		 
	//------------------------------------------- Partie onglets et pageHTML -------------------------------

		 
		 toutesLesPages=new JTabbedPaneWithCloseIcons();
		 toutesLesPages.setPreferredSize(new Dimension(screenWidth,screenHeight-100));
		 panelPrincipal.add(toutesLesPages);
		 
		 toutesLesPages.addTab("HOME", new CloseTabIcon() ,pageAcceuil);
		 toutesLesPages.addTab("+", new Onglets());
		 

		 toutesLesPages.setEnabledAt(toutesLesPages.getTabCount()-1, false); //On désactive l'accée a l'onglet "+"
		 
			pageAcceuil.page.addHyperlinkListener(new HyperlinkListener() 
		     {
		    	 public void hyperlinkUpdate(HyperlinkEvent event) 
		    	 {
		    	      addHypLink(event,pageAcceuil);
		    	      panelPrincipal.setFocusable(true);
		    	   }
		     });
		 
		 
	          
	          barRecherche.urlField.addKeyListener(new KeyAdapter() 
	          {
	              public void keyReleased(KeyEvent e) 
	              {
	                if (e.getKeyCode() == KeyEvent.VK_ENTER)
	                {
                    	newPage();
	                }
	              }
	          });
	          
	          barRecherche.prec.addActionListener(new ActionListener()
	          {
				public void actionPerformed(ActionEvent event) 
				{
					prec_suiv(true);
				}
	          });
	          
	          barRecherche.suiv.addActionListener(new ActionListener()
	          {
				public void actionPerformed(ActionEvent event) 
				{
                	prec_suiv(false);
		    	      panelPrincipal.setFocusable(true);
				}
	          });
	          
	          barRecherche.afficherHistorique.addActionListener(new ActionListener()
	          {
				public void actionPerformed(ActionEvent event) 
				{
					chargerHistorique();

 
				}
	          });
	          
	          barRecherche.afficherFavoris.addMouseListener(new MouseAdapter()
	        {
                  public void mouseReleased(MouseEvent event)
                  {
                	  add_disp(event);
                  }
	        });
	          
	          toutesLesPages.addChangeListener(new ChangeListener()
	        		  {
						public void stateChanged(ChangeEvent event) 
						{
							changeListener(event);
						}
	        	  
	        		  });
	          toutesLesPages.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_H)
				{
					chargerHistorique();
				}
			}
			public void keyReleased(KeyEvent arg0) { }
			public void keyTyped(KeyEvent e) { }
			
		});
	}

	
	void add_disp(MouseEvent event)
	{

        if(event.isPopupTrigger())
        {
           JPopupMenu popup = new JPopupMenu();
           
           for(int i=0; i<barRecherche.listFavoris.size(); i++)
           {
               JMenu menu = new JMenu(barRecherche.listFavoris.get(i).toString());
               
               JMenuItem ouvrir = new JMenuItem("Ouvrir");
               menu.add(ouvrir);
               ouvrir.addActionListener(new ActionListener()
            		   {
            	   			public void actionPerformed(ActionEvent e)
              	 			{
              	 				String urlClique = menu.getText();
              	 				barRecherche.urlField.setText(urlClique);
              	 				newPage();
              	 			}
            		   });
               
               JMenuItem supprimer = new JMenuItem("Supprimer");
               menu.add(supprimer);
               supprimer.addActionListener(new ActionListener()
            		   {
            	   			public void actionPerformed(ActionEvent e)
              	 			{
              	 				barRecherche.listFavoris.remove(menu.getText());
              	 			}
            		   });
               
               popup.add(menu);
           }
           
           popup.show( event.getComponent(), event.getX(), event.getY());
        }
       else
       {
            if(barRecherche.urlField.getText().length()!=0 && !barRecherche.listFavoris.contains(barRecherche.urlField.getText()))
            {
          	  barRecherche.listFavoris.add(barRecherche.urlField.getText());
            }
       }
	}
	
	void chargerHistorique()
	{
		barRecherche.urlField.setText(" ");
		barRecherche.historique.setContentOfHistoric(barRecherche.listURL);
		toutesLesPages.remove(toutesLesPages.getTabCount()-1);
		toutesLesPages.addTab("HISTORY", new CloseTabIcon(), barRecherche.historique);
		toutesLesPages.setSelectedIndex(toutesLesPages.getSelectedIndex()+1);
		
		 toutesLesPages.addTab("+", new Onglets());
		
		 toutesLesPages.setEnabledAt(toutesLesPages.getTabCount()-1, false); //On désactive l'accée a l'onglet "+"
	}
	
	void prec_suiv(boolean prec)
	{
		int selected = toutesLesPages.getSelectedIndex();
		Onglets ong = (Onglets) toutesLesPages.getComponent(selected);
    	int pageIndex = ong.listUrlOnglet.indexOf(ong.urlCurrent.toString());
    	
    	String newUrl;
    	
    	if(prec==false)
		    newUrl =  ong.listUrlOnglet.get(pageIndex+1).toString();
    	else
		    newUrl =  ong.listUrlOnglet.get(pageIndex-1).toString();
    	try { ong.setNewPage(new URL(newUrl), false);} 
    	catch (Exception e) { }
    	toutesLesPages.setTitleAt(selected, newUrl.toString());
    	
   	 Date date = new Date();
	 
   	 DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
   	 SimpleDateFormat formatHeure = new SimpleDateFormat ("hh:mm");
   	 
   	 String dateFormate = formatDate.format(date);
   	 String heureFormate = formatHeure.format(date);
   	 
   	 String date_heure = dateFormate + "  " + heureFormate;
    	
        barRecherche.urlField.setText(newUrl);
  	    barRecherche.listURL.add(date_heure);
  	    barRecherche.listURL.add(newUrl);
		
	}
	
	void newPage()
	{
		URL url=AfficheurPage.verifyUrl(barRecherche.urlField.getText());
    	int selected = toutesLesPages.getSelectedIndex();
		Onglets ong = (Onglets) toutesLesPages.getComponent(selected);
    	ong.setNewPage(url, true);
    	
  	   toutesLesPages.setTitleAt(selected, url.toString());
       barRecherche.urlField.setText(url.toString());
       
     Date date = new Date();
    	 
  	 DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
  	 SimpleDateFormat formatHeure = new SimpleDateFormat ("hh:mm");
  	 
  	 String dateFormate = formatDate.format(date);
  	 String heureFormate = formatHeure.format(date);
  	 
   	 String date_heure = dateFormate + "  " + heureFormate;

	    barRecherche.listURL.add(date_heure);
	    barRecherche.listURL.add(url);
	}
	
	void addHypLink(HyperlinkEvent event, Onglets ong)
	{
		int selected = toutesLesPages.getSelectedIndex();
		 HyperlinkEvent.EventType eventType = event.getEventType();
	       if (eventType == HyperlinkEvent.EventType.ACTIVATED) 
	       {
	           if (event instanceof HTMLFrameHyperlinkEvent) 
	           {
	               HTMLFrameHyperlinkEvent linkEvent = (HTMLFrameHyperlinkEvent) event;
	               HTMLDocument document = (HTMLDocument) ong.page.getDocument();
	               document.processHTMLFrameHyperlinkEvent(linkEvent);
	           }
	           else 
	           {
	        	   ong.setNewPage(event.getURL(),true);
	               barRecherche.urlField.setText(event.getURL().toString());
	               toutesLesPages.setTitleAt(selected, event.getURL().toString());
	               
	          	 Date date = new Date();
	        	 
	        	 DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
	        	 SimpleDateFormat formatHeure = new SimpleDateFormat ("hh:mm");
	        	 
	        	 String dateFormate = formatDate.format(date);
	        	 String heureFormate = formatHeure.format(date);
	        	 
	           	 String date_heure = dateFormate + "  " + heureFormate;
	               

	       	    barRecherche.listURL.add(date_heure);
	       	    barRecherche.listURL.add(event.getURL().toString());
	           }
	       }
	}
	
	void changeListener(ChangeEvent event)
	{
		JTabbedPane tabbedPane = (JTabbedPane) event.getSource();
		int selected = toutesLesPages.getSelectedIndex();
		if(tabbedPane.getTitleAt(selected)=="HOME" || tabbedPane.getTitleAt(selected)=="HISTORY" || tabbedPane.getTitleAt(selected)=="+")
			barRecherche.urlField.setText("");
		else barRecherche.urlField.setText(tabbedPane.getTitleAt(selected));

		Onglets ong = (Onglets) toutesLesPages.getComponent(selected);
		
		ong.page.addHyperlinkListener(new HyperlinkListener() 
	     {
	    	 public void hyperlinkUpdate(HyperlinkEvent event)
	    	 {
	    	      addHypLink(event, ong);
	    	 }
	     });
	}
	

}