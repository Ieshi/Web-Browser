package fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class JTabbedPaneWithCloseIcons extends JTabbedPane implements MouseListener 
{
  public JTabbedPaneWithCloseIcons() 
  {
    super();
    addMouseListener(this);
    
    this.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent e) 
			{
				if(e.getKeyCode() == KeyEvent.VK_T)
				{
					addNewTab();
				}
				
				if(e.getKeyCode() == KeyEvent.VK_W)
				{
					supprimerTab();
				}
			}
			public void keyReleased(KeyEvent arg0) { }
			public void keyTyped(KeyEvent e) { }
			
		});
    
  }

	
	void addNewTab()
	{

		int tabCount = this.getTabCount();
		this.remove(tabCount-1);
	       this.addTab("HOME", new CloseTabIcon(), new Onglets());	
		   this.addTab("+", new Onglets());
		 this.setSelectedIndex(tabCount-2);
		   this.setEnabledAt(this.getTabCount()-1, false); //On désactive l'accée a l'onglet "+"
	}
	
	void supprimerTab()
	{
		int index = this.getSelectedIndex();
		if(this.getTabCount()>2)
		{
			this.removeTabAt(index);
		}
	}

  
  public void mouseClicked(MouseEvent e) 
  {
	  //tabForCoordinate renvoye l'onglet qui contient le point ou on a cliqué (e.getX(),e.getY())
    int tabNumber=getUI().tabForCoordinate(this, e.getX(), e.getY()); //getUI() retourne BasicTabbedPaneUI puisque c'est l'UI qui implémente le L&F (look and feel) de JTabbedPane
    if (getTabCount()-3 < 0)   //S'il n'y a que deux onglets (un onglet + l'onglet pour ajouter un onglet) on ne peut qu'ajouter un onglet
    {
    	if(tabNumber==this.getTabCount()-1)
        {
               this.remove(tabNumber);
               this.addTab("HOME", new CloseTabIcon(), new Onglets());	
      		   this.addTab("+", new Onglets());
               this.setSelectedIndex(tabNumber);
      		   this.setEnabledAt(this.getTabCount()-1, false); //On désactive l'accée a l'onglet "+"
        }
    }
    
    else  //Sinon on peut ajouter et supprimer un onglet
    {
    	if(tabNumber==this.getTabCount()-1)
        {
               this.remove(tabNumber);
               this.addTab("HOME", new CloseTabIcon(), new Onglets());
      		   this.addTab("+", new Onglets());
               this.setSelectedIndex(tabNumber);
      		   this.setEnabledAt(this.getTabCount()-1, false); //On désactive l'accée a l'onglet "+"
        }
    	else 
    	{
            Rectangle rect=((CloseTabIcon)getIconAt(tabNumber)).getBounds(); //Retourne l'icone qui se trouve dans l'onglet où on a cliqué
            if (rect.contains(e.getX(), e.getY())) //Si le rectangle contient le point où on a cliqué
            {
              this.removeTabAt(tabNumber);
            }
    	}

    }
  }

  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {}
  public void mouseReleased(MouseEvent e) {}
}


class CloseTabIcon implements Icon 
{
  private int x_pos;
  private int y_pos;
  private int width;
  private int height;
  private Icon fileIcon;

  public CloseTabIcon()
  {
    width=16;
    height=16;
  }

  public void paintIcon(Component c, Graphics g, int x, int y)
  {
    this.x_pos=x;
    this.y_pos=y;

    Color col=g.getColor();

    g.setColor(Color.black);
    int y_p=y+2;
    g.drawLine(x+1, y_p, x+12, y_p);
    g.drawLine(x+1, y_p+13, x+12, y_p+13);
    g.drawLine(x, y_p+1, x, y_p+12);
    g.drawLine(x+13, y_p+1, x+13, y_p+12);
    g.drawLine(x+3, y_p+3, x+10, y_p+10);
    g.drawLine(x+3, y_p+4, x+9, y_p+10);
    g.drawLine(x+4, y_p+3, x+10, y_p+9);
    g.drawLine(x+10, y_p+3, x+3, y_p+10);
    g.drawLine(x+10, y_p+4, x+4, y_p+10);
    g.drawLine(x+9, y_p+3, x+3, y_p+9);
    g.setColor(col);
    if (fileIcon != null)
    {
      fileIcon.paintIcon(c, g, x+width, y_p);
    }
  }

  public int getIconWidth()
  {
    return width + (fileIcon != null? fileIcon.getIconWidth() : 0);
  }

  public int getIconHeight() {
    return height;
  }

  public Rectangle getBounds() {
    return new Rectangle(x_pos, y_pos, width, height);
  }
}

