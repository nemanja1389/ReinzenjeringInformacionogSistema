package command;

import geometry.Square;
import model.Model;

public class DeleteSquare implements Command {

	private Model model;
	private Square square;
	
	public DeleteSquare(Model model, Square square) {
		this.model = model;
		this.square = square;
	}
	
	@Override
	public void execute() {
		model.getShapeList().remove(square);
	}

	@Override
	public void unexecute() {
		model.getShapeList().add(square);
	}

}
