package fenetre;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class History extends Onglets
{
	JTextArea historic = new JTextArea();
	JPanel panelHistorique = new JPanel();
	JPanel suprChaqueLien = new JPanel();
	JPanel panelBoutton = new JPanel();
	ArrayList<JCheckBox> buttonSupr=new ArrayList();
	ArrayList listChecked = new ArrayList();
	
	public History()
	{		
		this.remove(this.page);

        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = (int)screenSize.getHeight();
        int screenWidth  = (int)screenSize.getWidth();
        
        int wImage = 400;               
        int wHistorique = screenWidth - 400; 
        
		
		panelHistorique.setPreferredSize(new Dimension(screenWidth-25,screenHeight-135));
		

		JPanel panelImage = new JPanel()
		{

            protected void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
 
                ImageIcon m = new ImageIcon("historique.jpg");
                Image monImage = m.getImage();
                g.drawImage(monImage, 0, 0,this);
 
            }
		};
		
		panelImage.setPreferredSize(new Dimension(wImage,screenHeight-135));
		panelImage.setVisible(true);
		panelHistorique.add(panelImage);
		
		JPanel search_display = new JPanel();
		search_display.setPreferredSize(new Dimension(wHistorique-35, screenHeight-135));
		search_display.setLayout(new BorderLayout());
		
		JPanel rechercherHistorique = new JPanel();
		rechercherHistorique.setPreferredSize(new Dimension(wHistorique-35,40));
		rechercherHistorique.setBackground(Color.LIGHT_GRAY);
		
		JTextField rechercher = new JTextField();
		rechercher.setPreferredSize(new Dimension(600,25));
		rechercher.setFont(new Font("Serif", Font.PLAIN, 14));
		rechercher.addKeyListener(new KeyAdapter() 
        {
            public void keyReleased(KeyEvent e) 
            {
              if (e.getKeyCode() == KeyEvent.VK_ENTER)
              {
              	rechercherURL (rechercher.getText());
              }
            }
        });
		rechercherHistorique.add(rechercher);
		
		JButton supprimerHistorique = new JButton("Supprimer l'historique");
		supprimerHistorique.addActionListener(new ActionListener()
				{
			        public void actionPerformed(ActionEvent e)
			        {
			        	BarRecherche.listURL.clear();
			        	setContentOfHistoric(BarRecherche.listURL);
			        }
				});
		rechercherHistorique.add(supprimerHistorique);
		
		search_display.add(rechercherHistorique, BorderLayout.NORTH);
		
		
		suprChaqueLien.setBackground(Color.LIGHT_GRAY);
		suprChaqueLien.setPreferredSize(new Dimension(50, screenHeight-180));
		

		panelBoutton.setPreferredSize(new Dimension(48,25));
		panelBoutton.setBackground(Color.LIGHT_GRAY);
		JButton supprimerLien = new JButton();
		supprimerLien.setText("Supprimer");
		supprimerLien.setBorderPainted(false);
		supprimerLien.setFont(new Font("Arial", Font.PLAIN, 9));
		supprimerLien.setBackground(Color.LIGHT_GRAY);
		supprimerLien.addActionListener(new ActionListener()
				{
			        public void actionPerformed(ActionEvent e)
			        {
			        	supprimerSelectionne();
			        }
				});
		panelBoutton.add(supprimerLien);
		suprChaqueLien.add(panelBoutton);
		search_display.add(suprChaqueLien, BorderLayout.EAST);
		
		historic.setPreferredSize(new Dimension(wHistorique-65, screenHeight-180));
		historic.setEditable(false);
		search_display.add(historic, BorderLayout.CENTER);
		
		panelHistorique.add(search_display);
	}
	
	void setContentOfHistoric(ArrayList list)
	{
		
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int screenHeight = (int)screenSize.getHeight();
        int screenWidth  = (int)screenSize.getWidth();

		suprChaqueLien.removeAll();
		suprChaqueLien.add(panelBoutton);
		
		listChecked.clear();
		buttonSupr.clear();
		
		
		String str="";
		
		int lenghtoflist=list.size();
		
		for(int i=lenghtoflist-1;i>=1; i=i-2)
		{
			str+="  " + list.get(i-1).toString()+ "	" + list.get(i).toString() + "\n";
		}
		
		JPanel tmp = new JPanel();
		tmp.setBackground(Color.LIGHT_GRAY);
		tmp.setPreferredSize(new Dimension (20,screenHeight-180 ));
		
		for(int j=0; j<lenghtoflist/2; j++)
		{
			JCheckBox button = new JCheckBox();
			button.setBackground(Color.LIGHT_GRAY);

			buttonSupr.add(button);
			
			   button.addItemListener(new ItemListener() 
			   {
					public void itemStateChanged(ItemEvent arg0)
					{
						if(button.isSelected() && !listChecked.contains(buttonSupr.indexOf(button)))
						{
							listChecked.add(buttonSupr.indexOf(button));
						}
						if(!button.isSelected() && listChecked.contains(buttonSupr.indexOf(button)))
						{
							listChecked.remove((Object)(buttonSupr.indexOf(button)));
						}
					}
			   });
			   
			   tmp.add(buttonSupr.get(j));
		}
		
		suprChaqueLien.add(tmp);
		
		historic.setFont(new Font("Serif", Font.PLAIN, 18));
		
		historic.setText("\n" + str);
		
		this.setViewportView(panelHistorique);
	}
	
	void rechercherURL(String url)
	{
		ArrayList index = new ArrayList();
		
		int firstIndex = BarRecherche.listURL.indexOf(url);
		int lastIndex = BarRecherche.listURL.lastIndexOf(url);
		
		System.out.println(firstIndex + "	,	" + lastIndex);
		
		int i=firstIndex;
		
		while(i!=lastIndex && i!=-1)
		{
			index.add(i-1);
			index.add(i);
			i++;
			ArrayList listtmp = (ArrayList) BarRecherche.listURL.subList(i, lastIndex-1);
			i=listtmp.indexOf(url);
		}
		
		ArrayList listResultat = new ArrayList();
		for(int j=0; j<index.size(); j++)
		{
			listResultat.add(BarRecherche.listURL.get((int) index.get(j)));
		}
		
		System.out.println(listResultat.toString());
		
		setContentOfHistoric(listResultat);
	}
	
	void supprimerSelectionne()
	{
		Collections.sort(listChecked);
		System.out.print(listChecked.toString());
		
		for(int i=0; i<listChecked.size(); i++)
		{
			BarRecherche.listURL.remove(BarRecherche.listURL.size()-2*(int)(listChecked.get(i))-1 + 2*i);
			BarRecherche.listURL.remove(BarRecherche.listURL.size()-2*(int)(listChecked.get(i))-1 + 2*i);
		}
		
		setContentOfHistoric(BarRecherche.listURL);
	}
	
}
