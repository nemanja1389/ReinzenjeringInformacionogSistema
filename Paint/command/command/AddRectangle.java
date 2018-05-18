package command;

import geometry.Rectangle;
import model.Model;

public class AddRectangle implements Command {
	
	private Model model;
	private Rectangle rectangle;

	public AddRectangle(Model model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;
	}
	
	@Override
	public void execute() {
		model.getShapeList().add(rectangle);
	}

	@Override
	public void unexecute() {
		model.getShapeList().remove(rectangle);
	}

}
