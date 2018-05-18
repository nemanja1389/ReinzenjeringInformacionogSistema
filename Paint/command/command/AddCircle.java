package command;

import geometry.Circle;
import model.Model;

public class AddCircle implements Command {
	
	private Model model;
	private Circle circle;

	public AddCircle(Model model, Circle circle) {
		this.model = model;
		this.circle = circle;
	}
	
	@Override
	public void execute() {
		model.getShapeList().add(circle);
	}

	@Override
	public void unexecute() {
		model.getShapeList().remove(circle);
	}

}
