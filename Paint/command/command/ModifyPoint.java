package command;

import geometry.Point;

public class ModifyPoint implements Command {

	private Point point = new Point();
	private Point temp;
	private Point oldPoint = new Point();
	
	public ModifyPoint(Point temp, Point newPoint) {
		this.temp = temp;
		this.point = newPoint;
	}
	
	@Override
	public void execute() {
		oldPoint.setX(temp.getX());
		oldPoint.setY(temp.getY());
		oldPoint.setLineColor(temp.getLineColor());
		oldPoint.setSelected(temp.isSelected());
		
		temp.setX(point.getX());
		temp.setY(point.getY());
		temp.setLineColor(point.getLineColor());
		temp.setSelected(point.isSelected());
	}

	@Override
	public void unexecute() {
		temp.setX(oldPoint.getX());
		temp.setY(oldPoint.getY());
		temp.setLineColor(oldPoint.getLineColor());
		temp.setSelected(oldPoint.isSelected());
	}
	
	public String toString() {
		String s = (oldPoint + " to " + point);
		return s;
	}

}
