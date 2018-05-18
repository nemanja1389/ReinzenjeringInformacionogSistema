package command;

import geometry.Rectangle;
import model.Model;

public class DeleteRectangle implements Command {
	
	private Model model;
	private Rectangle rectangle;
	
	public DeleteRectangle(Model model, Rectangle rectangle) {
		this.model = model;
		this.rectangle = rectangle;
	}

	@Override
	public void execute() {
		model.getShapeList().remove(rectangle);
	}

	@Override
	public void unexecute() {
		model.getShapeList().add(rectangle);
	}

}
