package controller;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.List;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import command.AddPoint;
import command.Command;
import geometry.Line;
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
	private int mouseDragged = 0;
	private Point startPoint;
	
	
	private LinkedList<Command> commandList = new LinkedList<Command>();
	private int commandListIndex = -1;
	private LinkedList<String> commandsLog = new LinkedList<String>();
	private int commandsLogIndex = 0;
	private String commandsLogString;
	private String[] commandsLogStringParts;
	private String[] commandsLogStringPartsInner;
	private String[] commandsLogStringPartsInnerParams;
	
	private LinkedList<String> stringList = new LinkedList<String>();
	private int stringListIndex = 0;
	private String stringListLine;
	private String stringListArray[];
	private String stringListCmd[];
	private String stringListShape[];
	private String stringListX[];
	private String stringListY[];
	private String stringListRed[];
	private String stringListGreen[];
	private String stringListBlue[];
	
	
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
			
			/**
			 * Crtanje tacke
			 */
			@Override
			public void mouseClicked(MouseEvent e){
				if(frame.getTglbtnPoint().isSelected()){
					AddPoint addPoint = new AddPoint(model,new Point(e.getX(), e.getY(), lineColor));
					doCommand(addPoint);
					getFrame().getLogTextArea().append("Add: "+ new Point(e.getX(), e.getY(), lineColor).toString() + '\n');
					getView().repaint();
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
			
			/**
			 * 
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				getFrame().getCoordText().setText("");
			}
			
			/**
			 * 
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				if(getFrame().getTglbtnLine().isSelected()){
					startPoint = new Point(e.getX(), e.getY(), lineColor);
				}
			}
			
			/**
			 * 
			 */
			@Override
			public void mouseReleased(MouseEvent e) {
				if(getFrame().getTglbtnLine().isSelected()){
					model.getShapeList().add(new Line(startPoint, new Point(e.getX(), e.getY(), lineColor), lineColor));
					getFrame().getLogTextArea().append("Add: "+ new Line(startPoint, new Point(e.getX(), e.getY(), lineColor), lineColor).toString() + '\n');
				}
			}
		});
		
		getView().addMouseMotionListener(new MouseAdapter() {
			
			/**
			 * 
			 */
			@Override
			public void mouseMoved(MouseEvent e) {
				getFrame().getCoordText().setText("X: " + e.getX() + "   Y: " + e.getY());
			}
			
			/**
			 * 
			 */
			@Override
			public void mouseDragged(MouseEvent e) {
				if(getFrame().getTglbtnLine().isSelected()){
					if(mouseDragged == 0){
						model.getShapeList().add(new Line(startPoint, new Point(e.getX(), e.getY(), lineColor), lineColor));
						mouseDragged = 1;
					} else {
						model.getShapeList().remove(model.getShapeList().size()-1);
						model.getShapeList().add(new Line(startPoint, new Point(e.getX(), e.getY(), lineColor), lineColor));
					}
				}
			}
		});
		
		getFrame().getBtnDelete().addActionListener(new ActionListener() {
			
			/**
			 * 
			 */
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
			
			/**
			 * 
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				lineColor = JColorChooser.showDialog(null, "Izaberi boju", lineColor);
				if(lineColor != null){
					lineColor = lineColor;
					getFrame().getBtnLineColor().setBackground(lineColor);
				}
			}
		});
		
		getFrame().getBtnSave().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				File selectedFile = null;
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Log aplikacije", "log");
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Desktop"));
				fileChooser.setFileFilter(filter);
				
				int result = fileChooser.showSaveDialog(getFrame().getPaintPnl());
				if (result == JFileChooser.APPROVE_OPTION) {
					if(!fileChooser.getSelectedFile().getAbsolutePath().endsWith(".log")){
						selectedFile = new File(fileChooser.getSelectedFile() + ".log");
					}
					BufferedWriter writer = null;
					try {
						writer = new BufferedWriter(new FileWriter(selectedFile.getAbsolutePath()));
					} catch (IOException ee) {
						JOptionPane.showMessageDialog(null, "File format is not correct.", "Warning!", JOptionPane.WARNING_MESSAGE );
						ee.printStackTrace();
					} 
					try {
						getFrame().getLogTextArea().write(writer);
					} catch (IOException ee) {
						JOptionPane.showMessageDialog(null, "File format is not correct.", "Warning!", JOptionPane.WARNING_MESSAGE );
						ee.printStackTrace();
					}
					try {
						writer.close();
					} catch (IOException ee) {
						JOptionPane.showMessageDialog(null, "File format is not correct.", "Warning!", JOptionPane.WARNING_MESSAGE );
						ee.printStackTrace();
					}
				}
			}
		});
		
		getFrame().getBtnOpen().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("log", "log");
				fileChooser.setFileFilter(filter);
				File selectedFile = null;
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(getFrame().getPaintPnl());
				
				selectedFile = fileChooser.getSelectedFile();
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(selectedFile));
				} 
				catch (FileNotFoundException ee) {
						ee.printStackTrace();
				}
				String line;
				try {
					while ((line = br.readLine()) != null) {
						stringList.add(line);
					}
				} 
				catch (IOException ee) {
					
						ee.printStackTrace();
				}
				try {
					br.close();
				} 
				catch (IOException ee) {
						// TODO Auto-generated catch block
						ee.printStackTrace();
					
				}
				for (int i = 0; i < stringList.size(); i++) {
					stringListLine = stringList.get(i);
					System.out.println(i + ". " + stringListLine);
					stringListArray = stringListLine.split(" ");
					stringListCmd = stringListArray[0].split(":");
					stringListShape = stringListArray[1].split(":");
					if (stringListCmd[0].equals("Add")){
						if (stringListShape[0].equals("Point")) {
							stringListX = stringListArray[3].split(",");
							stringListY = stringListArray[5].split(";");
							stringListRed = stringListArray[8].split(",");
							stringListGreen = stringListArray[10].split(",");
							stringListBlue = stringListArray[12].split(";");
		 					System.out.println(i + ". " + stringListCmd[0]);
							System.out.println(i + ". " + stringListShape[0]);
							System.out.println(i + ". " + stringListX[0]);
							System.out.println(i + ". " + stringListY[0]);
							System.out.println(i + ". " + stringListRed[0]);
							System.out.println(i + ". " + stringListGreen[0]);
							System.out.println(i + ". " + stringListBlue[0]);
							int x = Integer.parseInt(stringListX[0]);
							int y = Integer.parseInt(stringListY[0]);
							int r = Integer.parseInt(stringListRed[0]);
							int g = Integer.parseInt(stringListGreen[0]);
							int b = Integer.parseInt(stringListBlue[0]);
							AddPoint addPoint = new AddPoint(model, new Point(x, y, new Color(r, g, b)));
							doCommand(addPoint);
							getFrame().getLogTextArea().append(stringListLine  + '\n');
						}
					}
					
				}
				getView().repaint();
			}
		});
		
		getFrame().getBtnUndo().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (commandListIndex > -1) {
					commandList.get(commandListIndex).unexecute();
					getFrame().getLogTextArea().append("Undo command" + '\n');
					commandListIndex--;
				} 		
				getView().repaint();
			}
		});
		
		getFrame().getBtnRedo().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (commandListIndex +1 < (commandList.size())) {
					commandList.get(commandListIndex+1).execute();
					commandListIndex++;
					getFrame().getLogTextArea().append("Redo command" + '\n');
					getView().repaint();
				}
			}
		});
	}
	
	
	/**
	 * Izvrsavanje komandi
	 * @param c - prosledjena komanda
	 */
	public void doCommand(Command c) {
		if (commandListIndex + 1 < (commandList.size())) {
			System.out.println("true");
			int cc = commandList.size();
			for (int i = cc - 1; i > commandListIndex; i--) {
				commandList.remove(i);
			}
			c.execute();
			commandList.add(c);
		} 
		else {
			System.out.println("false");
			c.execute();
			commandList.add(c);
		}
		commandListIndex++;
		view.repaint();
	}
	
	public void recreate(){
		if (commandsLogIndex < commandsLog.size()) {
			commandsLogString = commandsLog.get(commandsLogIndex);
			commandsLogStringParts = commandsLogString.split(": ");
			if (commandsLogStringParts[0].equals("Add")){
				commandsLogStringPartsInner = commandsLogStringParts[1].split(":");
				if (commandsLogStringPartsInner[0].equals("Point")) {
					commandsLogStringPartsInnerParams = commandsLogStringPartsInner[1].split(",");
					System.out.println(commandListIndex + ", " + commandsLogString + ", " );
					/*AddPoint addPoint = new AddPoint(model,*/
					/*Point t = new Point(commandsLogStringPartsInnerParams[0], commandsLogStringPartsInnerParams[1], Color.decode(commandsLogStringPartsInnerParams[2]));
					CmdAddPoint c2 = new CmdAddPoint(model, t);
					doCmd(c2);*/
				}/* 
				else if (commandsLogStringPartsInner[0].equals("Line")){
					String commandsLogStringPartsInnerParamsLine[];
					commandsLogStringPartsInnerParamsLine = commandsLogStringPartsInner[1].split(",");
					Point start = new Point(commandsLogStringPartsInnerParamsLine[0], commandsLogStringPartsInnerParamsLine[1], Color.black);
					Point end = new Point(commandsLogStringPartsInnerParamsLine[2], commandsLogStringPartsInnerParamsLine[3], Color.black);
					Line l = new Line(start, end, Color.decode(commandsLogStringPartsInnerParamsLine[4]));
					CmdAddLine c3 = new CmdAddLine(model, l);
					doCmd(c3);
				}*/ 
			}
		}
	}
}
