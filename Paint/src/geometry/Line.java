package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

	/**
	 * 
	 */
	private Point startPoint;
	private Point endPoint;

	/**
	 * 
	 */
	public Line(){}
	
	/**
	 * 
	 * @param startPoint
	 * @param endPoint
	 */
	public Line(Point startPoint, Point endPoint){
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	/**
	 * 
	 * @param startPoint
	 * @param endPoint
	 * @param lineColor
	 */
	public Line(Point startPoint, Point endPoint, Color lineColor){
		this.lineColor = lineColor;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	/**
	 * 
	 * @param startPoint
	 * @param endPoint
	 * @param selected
	 * @param lineColor
	 */
	public Line(Point startPoint, Point endPoint, boolean selected, Color lineColor){
		super(selected, lineColor);
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	/**
	 * 
	 * @return
	 */
	public Point getStartPoint() {
		return startPoint;
	}

	/**
	 * 
	 * @param startPoint
	 */
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 *
	 * @return
	 */
	public Point getEndPoint() {
		return endPoint;
	}

	/**
	 * 
	 * @param endPoint
	 */
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	/**
	 * 
	 * @return
	 */
	public double length(){
		return startPoint.distance(endPoint);
	}
	
	/**
	 * 
	 * @return
	 */
	public Point midfieldLine(){
		return new Point((startPoint.getX()+endPoint.getX())/2, (startPoint.getY()+endPoint.getY())/2);
	}
	
	/**
	 * 
	 */
	@Override
	public String toString(){
		return "Line: Start point: " + "x: " + startPoint.getX() + ", y: " + startPoint.getY() +  "--> End point: "
	+ "x: " + endPoint.getX() + ", y: " + endPoint.getY()  
	+ "; Color: " + "R: " + getLineColor().getRed() + ", " + "G: " + getLineColor().getGreen() + ", " + "B: " + getLineColor().getBlue();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean equals(Object o){
		if (o instanceof Line){
			Line line = (Line)o;
			if (startPoint.equals(line.getStartPoint()) 
			    && endPoint.equals(line.getEndPoint())){
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
		if(new Point(x,y).distance(startPoint)+new Point(x,y).distance(endPoint)-this.length() <= 0.05){
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
		g.drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		if(isSelected()){
			startPoint.drawSquare(g);
			endPoint.drawSquare(g);
			midfieldLine().drawSquare(g);
		}
	}
	
}
