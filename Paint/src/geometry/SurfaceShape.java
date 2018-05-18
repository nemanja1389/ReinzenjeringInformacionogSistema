package geometry;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape{

protected Color areaColor;
	
	/**
	 * Prazan konstruktor
	 */
	public SurfaceShape(){}
	
	/**
	 * Konstruktor koji prihvata dva parametra
	 * @param lineColor boja linije
	 * @param areaColor boja površine oblika
	 */
	public SurfaceShape(Color lineColor, Color areaColor){
		this.areaColor = areaColor;
		this.lineColor = lineColor;
	}
	
	/**
	 * Konstruktor koji prihvata tri parametra
	 * @param selected selektovan (true or false)
	 * @param lineColor boja linije
	 * @param areaColor boja površine oblika
	 */
	public SurfaceShape(boolean selected, Color lineColor, Color areaColor){
		super(selected, lineColor);
		this.areaColor = areaColor;
	}
	
	/**
	 * Get metoda vraæa boju površine oblika.
	 * @return areaColor
	 */
	public Color getAreaColor() {
		return areaColor;
	}
	
	/**
	 * Set metoda postavlja boju površine oblika.
	 * @param areaColor boja površine
	 */
	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}
	
	/**
	 * Metoda boji površinu oblika.
	 * @param g grafika
	 */
	public abstract void fillArea(Graphics g);
	
	/**
	 * Metoda izraèunava površinu oblika.
	 * @return površina oblika
	 */
	public abstract double surfaceArea();
	
	/**
	 * Metoda izraèunava obim oblika.
	 * @return obim oblika
	 */
	public abstract double volume();

}
