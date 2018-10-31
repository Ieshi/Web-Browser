
package fenetre;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class FenetrePrincipale extends JFrame
{
	PanelPrincipal panelPrincipal = new PanelPrincipal();
	
	public FenetrePrincipale()
	{
		this.setVisible(true);
		this.setTitle("My Web Browser");
		this.setLocationRelativeTo(null);
		 
		 this.setContentPane(panelPrincipal.panelPrincipal);
         
			this.setSize(800,500);
			pack();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		 
	}

	public static void main(String[] args) 
	{
		FenetrePrincipale fenetreP = new FenetrePrincipale();

	}

}





















