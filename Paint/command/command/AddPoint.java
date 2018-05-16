package command;

import geometry.Point;
import model.Model;

public class AddPoint implements Command {

	/**
	 * 
	 */
	private Model model;
	private Point point;
	
	/**
	 * 
	 * @param model
	 * @param point
	 */
	public AddPoint(Model model, Point point) {
		this.model = model;
		this.point = point;
	}
	
	/**
	 * 
	 */
	@Override
	public void execute() {
		model.getShapeList().add(point);
	}

	/**
	 * 
	 */
	@Override
	public void unexecute() {
		model.getShapeList().remove(point);
	}

}
