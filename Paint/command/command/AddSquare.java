package command;

import geometry.Square;
import model.Model;

public class AddSquare implements Command {

	private Model model;
	private Square square;
	
	public AddSquare(Model model, Square square) {
		this.model = model;
		this.square = square;
	}
	
	@Override
	public void execute() {
		model.getShapeList().add(square);
	}

	@Override
	public void unexecute() {
		model.getShapeList().remove(square);
	}

}
