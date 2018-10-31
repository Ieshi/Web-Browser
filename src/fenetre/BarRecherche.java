package fenetre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class BarRecherche extends JPanel
{
	JButton prec = new JButton("<");
	JButton suiv = new JButton(">");
	JTextField urlField = new JTextField();
	JButton afficherHistorique=new JButton("H");
	JButton afficherFavoris=new JButton("Ajouter/Afficher");
	
	ArrayList listFavoris = new ArrayList();
	
	static ArrayList listURL = new ArrayList();
	static History historique = new History();
	
	public void setButtonPagePrecedente()
	{
		prec.setPreferredSize(new Dimension(25,25));
		prec.setBorderPainted(false);
		prec.setFocusPainted(false);
		prec.setMargin(new Insets(0,0,0,0));
		prec.setBackground(Color.LIGHT_GRAY);
		
	}
	
	public void setButtonPageSuivante()
	{
		suiv.setPreferredSize(new Dimension(25,25));
		suiv.setBorderPainted(false);
		suiv.setFocusPainted(false);
		suiv.setMargin(new Insets(0,0,0,0));
		suiv.setBackground(Color.LIGHT_GRAY);
	}
	
	public void setURLSearchField()
	{
		urlField.setPreferredSize(new Dimension (600,25));
		urlField.setFont(new Font("Serif", Font.PLAIN, 14));
	}
	
	public void setAfficherHistorique()
	{
		afficherHistorique.setPreferredSize(new Dimension(45,25));
		afficherHistorique.setBorderPainted(false);
	}
	
	public void setAfficherFavoris()
	{
		afficherFavoris.setPreferredSize(new Dimension(80,25));
		afficherFavoris.setBorderPainted(false);
	}
	
	public BarRecherche()
	{
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth  = (int)screenSize.getWidth();
        
		 this.setPreferredSize(new Dimension(screenWidth,35));
		 this.setBackground(Color.LIGHT_GRAY);
		 setButtonPagePrecedente();
		 setButtonPageSuivante();
		 setURLSearchField();
		 this.add(prec);
		 this.add(urlField);
		 this.add(suiv);
		 this.add(afficherHistorique);
		 this.add(afficherFavoris);
	}
}
