package command;

import geometry.Line;
import model.Model;

public class AddLine implements Command {

	private Model model;
	private Line line;
	
	public AddLine(Model model, Line line) {
		this.model = model;
		this.line = line;
	}
	
	@Override
	public void execute() {
		model.getShapeList().add(line);
	}

	@Override
	public void unexecute() {
		model.getShapeList().add(line);
	}

}
