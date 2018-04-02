package geometry;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author Nemanja
 * @version 1.0
 */
public class Point extends Shape {
	
	/**
	 * 
	 */
	private int x = 0;
	private int y = 0;
	
	/**
	 * 
	 */
	public Point(){}
	
	/**
	 * 
	 * @param x x koordinata ta�ke
	 * @param y y koordinata ta�ke
	 */
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @param x x koordinata ta�ke
	 * @param y y koordinata ta�ke
	 * @param lineColor boja ivice
	 */
	public Point(int x, int y, Color lineColor){
		super(lineColor);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @param x x koordinata ta�ke
	 * @param y y koordinata ta�ke
	 * @param selected selektovan (true ili false)
	 * @param lineColor lineColor boja ivice
	 */
	public Point(int x, int y, boolean selected, Color lineColor) {
		super(selected, lineColor);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * @return x - x koordinata ta�ke
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * 
	 * @param x x koordinata ta�ke
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * 
	 * @return y - y koordinata
	 */
	public int getY() {
		return y;
	}

	/**
	 * 
	 * @param y y koordinata
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * 
	 * @param point ta�ka do koje se meri udaljenost
	 * @return razdaljina - razdaljina izme�u dve ta�ke
	 */
	public double distance(Point point) {
		double razdaljina;
		razdaljina = Math.sqrt(((point.x - this.x)*(point.x - this.x))+((point.y - this.y)*(point.y - this.y)));
		return razdaljina;
	}
	
	/**
	 * 
	 * @param g referenca na objekat klase Graphics
	 */
	public void drawSquare(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(this.getX()-2, this.getY()-2, 5, 5);
	}

	/**
	 * 
	 */
	@Override
	public String toString(){
		return "Point: " + "x: " + x + ", " + "y: " + y + "; Color: " + "R: " + getLineColor().getRed() + ", " + "G: " + getLineColor().getGreen() + ", " + "B: " + getLineColor().getBlue();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Point) {
			Point point = (Point)o;
			if (this.x == point.getX()
				&& this.y == point.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 
	 */
	@Override
	public boolean contains(int x, int y) {
		if(new Point(this.x, this.y).distance(new Point(x,y)) <= 3){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 */
	@Override
	public void draw(Graphics g) {
		g.setColor(getLineColor());
		g.drawLine(x-1, y-1, x+1, y+1);
		g.drawLine(x-1, y+1, x+1, y-1);
		if(isSelected()) {
			this.drawSquare(g);
		}
	}
}
