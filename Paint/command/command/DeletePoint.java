package command;

import geometry.Point;
import model.Model;

public class DeletePoint implements Command {

	private Model model;
	private Point point;
	
	public DeletePoint(Model m, Point p) {
		model = m;
		point = p;
	}
	
	@Override
	public void execute() {
		model.getShapeList().remove(point);
	}

	@Override
	public void unexecute() {
		model.getShapeList().add(point);
	}

}
