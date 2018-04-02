package geometry;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Nemanja
 * @version 1.0
 */
public abstract class Shape {

	protected boolean selected;
	protected Color lineColor;
	
	public Shape(){}
	
	/**
	 * 
	 * @param lineColor boja ivice
	 */
	public Shape(Color lineColor){
		this.lineColor = lineColor;
	}

	/**
	 * 
	 * @param selected selektovan (true ili false)
	 * @param lineColor boja ivice
	 */
	public Shape(boolean selected, Color lineColor){
		this.selected = selected;
		this.lineColor = lineColor;
	}

	/**
	 * 
	 * @return selected (true ili false)
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * 
	 * @param selected selektovan (true ili false)
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * 
	 * @return lineColor (boja ivice)
	 */
	public Color getLineColor() {
		return lineColor;
	}

	/**
	 * 
	 * @param lineColor boja ivice
	 */
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	/**
	 * 
	 * @param x x koordinata
	 * @param y y koordinata
	 * @return contain - true ili false
	 */
	public abstract boolean contains(int x, int y);

	/**
	 * 
	 * @param g referenca na objekat klase Graphics
	 */
	public abstract void draw(Graphics g);

}
