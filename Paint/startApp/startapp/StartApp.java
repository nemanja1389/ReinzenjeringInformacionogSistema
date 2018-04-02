package startapp;

import controller.Controller;
import model.Model;
import view.Frame;
import view.View;

/**
 * 
 * @author Nemanja
 * @version 1.0
 */
public class StartApp {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Model model = new Model();
		View view = new View(model);
		Frame frame = new Frame(view);
		Controller controller = new Controller(model, view, frame);
		controller.showPaint();
		frame.setVisible(true);
	}
}
