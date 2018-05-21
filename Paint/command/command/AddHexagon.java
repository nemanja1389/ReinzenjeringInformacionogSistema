package command;

import geometry.HexagonAdapter;
import model.Model;

public class AddHexagon implements Command {
	
	private Model model;
	private HexagonAdapter hexagon;

	public AddHexagon(Model model, HexagonAdapter hexagon) {
		this.model = model;
		this.hexagon = hexagon;
	}
	
	@Override
	public void execute() {
		model.getShapeList().add(hexagon);
	}

	@Override
	public void unexecute() {
		model.getShapeList().remove(hexagon);
	}

}
