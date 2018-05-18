package command;

import geometry.Circle;
import model.Model;

public class DeleteCircle implements Command {
	
	private Model model;
	private Circle circle;
	
	public DeleteCircle(Model model, Circle circle) {
		this.model = model;
		this.circle = circle;
	}

	@Override
	public void execute() {
		model.getShapeList().remove(circle);
	}

	@Override
	public void unexecute() {
		model.getShapeList().add(circle);
	}

}
