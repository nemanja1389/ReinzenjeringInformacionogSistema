package view;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;
import model.Model;

/**
 * 
 * @author Nemanja
 * @version 1.0
 */
public class View extends JPanel {

	/**
	 * 
	 */
	private Model model;

	/**
	 * 
	 * @param model referenca na objekat klase Model
	 */
	public View(Model model){
		this.model = model;
	}

	/**
	 * 
	 * @return model - referenca na objekat klase Model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * 
	 * @param model referenca na objekat klase Model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * 
	 */
	public void paint(Graphics g) {
		super.paint(g);
		
		Iterator it = model.getShapeList().iterator();
		while(it.hasNext()){
			Shape s = (Shape)it.next();
			s.draw(g);
		}
		
		repaint();
	}
}
