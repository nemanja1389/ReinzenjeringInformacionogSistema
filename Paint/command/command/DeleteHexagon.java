package command;

import geometry.HexagonAdapter;
import model.Model;

public class DeleteHexagon implements Command {

	private Model model;
	private HexagonAdapter hexagon;

	public DeleteHexagon(Model model, HexagonAdapter hexagon) {
		this.model = model;
		this.hexagon = hexagon;
	}
	
	@Override
	public void execute() {
		model.getShapeList().remove(hexagon);
	}

	@Override
	public void unexecute() {
		model.getShapeList().add(hexagon);
	}

}
