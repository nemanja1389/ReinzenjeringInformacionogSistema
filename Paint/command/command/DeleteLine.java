package command;


import geometry.Line;
import model.Model;

public class DeleteLine implements Command {

	private Model model;
	private Line line;
	
	public DeleteLine(Model model, Line line) {
		this.model = model;
		this.line = line;
	}
	
	@Override
	public void execute() {
		model.getShapeList().remove(line);
	}

	@Override
	public void unexecute() {
		model.getShapeList().add(line);
	}

}
