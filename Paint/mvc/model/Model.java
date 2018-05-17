package model;

import java.util.ArrayList;

import geometry.Shape;

/**
 * 
 * @author Nemanja
 * @version 1.0
 */
public class Model {
	
	/**
	 * 
	 */
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();
	private ArrayList<Shape> selectShapeList = new ArrayList<Shape>();

	/**
	 * 
	 * @return shapeList - lista oblika
	 */
	public ArrayList<Shape> getShapeList() {
		return shapeList;
	}

	/**
	 * 
	 * @param shapeList lista oblika
	 */
	public void setShapeList(ArrayList<Shape> shapeList) {
		this.shapeList = shapeList;
	}
}
