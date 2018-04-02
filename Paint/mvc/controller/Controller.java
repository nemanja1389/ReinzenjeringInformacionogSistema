package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;

import geometry.Point;
import geometry.Shape;
import model.Model;
import view.Frame;
import view.View;

/**
 * 
 * @author Nemanja
 *
 */
public class Controller {

	/**
	 * 
	 */
	private Model model;
	private View view ;
	private Frame frame;
	private Color lineColor = Color.BLACK;
	private Color areaColor = Color.WHITE;
	
	/**
	 * 
	 * @param model
	 * @param view
	 * @param frame
	 */
	public Controller(Model model, View view, Frame frame) {
		this.model = model;
		this.view = view;
		this.frame = frame;
	}

	/**
	 * 
	 * @return
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * 
	 * @param model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * 
	 * @return
	 */
	public View getView() {
		return view;
	}

	/**
	 * 
	 * @param view
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * 
	 * @return
	 */
	public Frame getFrame() {
		return frame;
	}

	/**
	 * 
	 * @param frame
	 */
	public void setFrame(Frame frame) {
		this.frame = frame;
	}
	
	/**
	 * 
	 * @return
	 */
	public Color getLineColor() {
		return lineColor;
	}

	/**
	 * 
	 * @param lineColor
	 */
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	/**
	 * 
	 * @return
	 */
	public Color getAreaColor() {
		return areaColor;
	}

	/**
	 * 
	 * @param areaColor
	 */
	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}

	/**
	 * 
	 */
	public void showPaint() {
		getView().addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e){
				if(frame.getTglbtnPoint().isSelected()){
					model.getShapeList().add(new Point(e.getX(), e.getY(), lineColor));
					
				} else if(frame.getTglbtnSelect().isSelected()){
					
					Iterator it = model.getShapeList().iterator();
					ArrayList select = new ArrayList<>();
					Shape s1 = null;
					while(it.hasNext()){
						Shape s = (Shape)it.next();
						s.setSelected(false);
						if(s.contains(e.getX(), e.getY())){
							select.add(s);
							s1=s;
						}
					}
					if(s1 != null){
						s1.setSelected(true);
					}
				}
			
			}	
		});
		
		getFrame().getBtnDelete().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Iterator it = model.getShapeList().iterator();
				while (it.hasNext()){
					Shape s = (Shape)it.next();
					if(s.isSelected()){					
						it.remove();
					}
				}
			}
		});
		
		getFrame().getBtnLineColor().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lineColor = JColorChooser.showDialog(null, "Izaberi boju", lineColor);
				if(lineColor != null){
					lineColor = lineColor;
					frame.getBtnLineColor().setBackground(lineColor);
				}
			}
		});
	}
}
