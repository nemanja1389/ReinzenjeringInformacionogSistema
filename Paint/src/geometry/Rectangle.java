package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Square {
	
private int height;
	
	/**
	 * Prazan konstruktor.
	 */
	public Rectangle(){}
	
	/**
	 * Konstruktor koji prihvata tri parametra.
	 * @param upLeft taèka gore levog ugla pravougaonika
	 * @param width širina pravougaonika
	 * @param height visina pravougaonika
	 */
	public Rectangle(Point upLeft, int width, int height){
		super(upLeft, width);
		this.height = height;
	}
	
	/**
	 * Konstruktor koji privhvata pet parametara.
	 * @param upLeft taèka gore levog ugla pravougaonika
	 * @param width širina pravougaonika
	 * @param height visina pravougaonika
	 * @param lineColor boja linije pravougaonika
	 * @param areaColor boja površine pravougaonika
	 */
	public Rectangle(Point upLeft, int width, int height, Color lineColor, Color areaColor){
		super(upLeft, width, lineColor, areaColor);
		this.height = height;
	}
	
	/**
	 * Konstruktor koji privhata šest parametara.
	 * @param upLeft taèka gore levog ugla pravougaonika
	 * @param width širina pravougaonika
	 * @param height visina pravougaonika
	 * @param selected selektovan (true or false)
	 * @param lineColor boja linije pravougaonika
	 * @param areaColor boja površine pravougaonika
	 */
	public Rectangle(Point upLeft, int width, int height, boolean selected, Color lineColor, Color areaColor){
		super(upLeft, width, selected, lineColor, areaColor);
		this.height = height;
	}

	/**
	 * Get metoda vraæa visinu pravougaonika.
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Set metoda postavlja visinu pravougaonika.
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * Preklopljena metoda koja ispisuje informacije o pravougaoniku.
	 */
	@Override
	public String toString(){
		return "Rectangle: " + "Up left point: " + "x: " + upLeft.getX() + ", y: " + upLeft.getY() + "; Width: " + width + "; Height: " + height
				+ "; Line color: " + "R: " + getLineColor().getRed() + ", " + "G: " + getLineColor().getGreen() + ", " + "B: " + getLineColor().getBlue()
				+ "; Area color: " + "R: " + getAreaColor().getRed() + ", " + "G: " + getAreaColor().getGreen() + ", " + "B: " + getAreaColor().getBlue();
	}
	/**
	 * Metoda koja za prosleðeni objekat ispituje da li je objekat pravougaonika ili nije.
	 * @param o prosleðen objekat koji se ispituje
	 * @return true or false
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof Rectangle){
			Rectangle rectangle = (Rectangle)o;
			if (upLeft.equals(rectangle.getUpLeft()) 
			    && width == rectangle.getWidth()
			    && height == rectangle.getHeight()){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Metoda boji površinu pravougaonika.
	 * @param g grafika
	 */
	@Override
	public void fillArea(Graphics g){
		g.setColor(getAreaColor());
		g.fillRect(upLeft.getX(), upLeft.getY(), width, height);
	}
	
	/**
	 * Metoda izraèunava površinu pravougaonika.
	 * @return površina pravougaonika
	 */
	@Override
	public double surfaceArea() {
		return width*height;
	}

	/**
	 * Metoda izraèunava obim pravougaonika.
	 * @return obim pravougaonika
	 */
	@Override
	public double volume() {
		return width*2 + height*2;
	}

	/**
	 * Metoda proverava da li se prosleðene koordinate x i y pripadaju pravougaonika.
	 * @param x koordinata x
	 * @param y koordinata y
	 * @return true or false
	 */
	@Override
	public boolean contains(int x, int y) {
		if(this.getUpLeft().getX()<=x 
				&& x<=(this.getUpLeft().getX() + width)
				&& this.getUpLeft().getY()<=y 
				&& y<=(this.getUpLeft().getY() + height)){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Metoda koja crta pravougaonika.
	 * @param g grafika
	 */
	@Override
	public void draw(Graphics g) {
		fillArea(g);
		g.setColor(getLineColor());
		g.drawRect(upLeft.getX(), upLeft.getY(), width, height);
		if(isSelected()){
			upLeft.drawSquare(g);
			new Point(upLeft.getX()+width, upLeft.getY()).drawSquare(g);
			new Point(upLeft.getX(), upLeft.getY()+height).drawSquare(g);
			new Point(upLeft.getX()+width, upLeft.getY()+height).drawSquare(g);
			new Point(upLeft.getX()+width/2, upLeft.getY()).drawSquare(g);
			new Point(upLeft.getX()+width/2, upLeft.getY()+height).drawSquare(g);
			new Point(upLeft.getX(), upLeft.getY()+height/2).drawSquare(g);
			new Point(upLeft.getX()+width, upLeft.getY()+height/2).drawSquare(g);
		}
	}


}
