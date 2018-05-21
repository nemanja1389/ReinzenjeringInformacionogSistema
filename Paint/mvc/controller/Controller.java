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

import org.omg.CosNaming.IstringHelper;

import command.AddCircle;
import command.AddHexagon;
import command.AddLine;
import command.AddPoint;
import command.AddRectangle;
import command.AddSquare;
import command.Command;
import command.DeleteCircle;
import command.DeleteHexagon;
import command.DeleteLine;
import command.DeletePoint;
import command.DeleteRectangle;
import command.DeleteSquare;
import command.ModifyPoint;
import dialog.CircleDlg;
import dialog.HexagonDlg;
import dialog.PointDlg;
import dialog.RectanglDlg;
import dialog.SquareDlg;
import geometry.Circle;
import geometry.HexagonAdapter;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;
import geometry.Square;
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
	private int mousePressed = 0;
	private Point startPoint;
	
	
	private LinkedList<Command> commandList = new LinkedList<Command>();
	private int commandListIndex = -1;
	
	private String stringListLine;
	private String stringListArray[];
	private String stringListCmd[];
	private String stringListShape[];
	
	
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
				}else if(getFrame().getTglbtnCircle().isSelected()) {
					CircleDlg dialog = new CircleDlg(e.getX(), e.getY(), lineColor, areaColor);
					
					dialog.setModal(true);
					dialog.setLocationRelativeTo(getFrame());
					dialog.setVisible(true);
					
					AddCircle addCircle = new AddCircle(model, new Circle(new Point(e.getX(), e.getY()), dialog.getRadius(), dialog.getLine(), dialog.getArea()));
					doCommand(addCircle);
					getFrame().getLogTextArea().append("Add: "+ new Circle(new Point(e.getX(), e.getY()), dialog.getRadius(), dialog.getLine(), dialog.getArea()).toString() + '\n');
				} else if(getFrame().getTglbtnSquare().isSelected()) {
					SquareDlg dialog = new SquareDlg(e.getX(), e.getY(), lineColor, areaColor);
					
					dialog.setModal(true);
					dialog.setLocationRelativeTo(getFrame());
					dialog.setVisible(true);
					
					AddSquare addSquare = new AddSquare(model, new Square(new Point(e.getX(), e.getY()), dialog.getSqwidth(), dialog.getLine(), dialog.getArea()));
					doCommand(addSquare);
					getFrame().getLogTextArea().append("Add: "+ new Square(new Point(e.getX(), e.getY()), dialog.getSqwidth(), dialog.getLine(), dialog.getArea()).toString() + '\n');
				}else if(getFrame().getTglbtnRectangle().isSelected()) {
					RectanglDlg dialog = new RectanglDlg(e.getX(), e.getY(), lineColor, areaColor);
					
					dialog.setModal(true);
					dialog.setLocationRelativeTo(getFrame());
					dialog.setVisible(true);
					
					AddRectangle addRectangle = new AddRectangle(model, new Rectangle(new Point(e.getX(), e.getY()), dialog.getSqwidth(), dialog.getReheight(), dialog.getLine(), dialog.getArea()));
					doCommand(addRectangle);
					getFrame().getLogTextArea().append("Add: "+ new Rectangle(new Point(e.getX(), e.getY()), dialog.getSqwidth(), dialog.getReheight(), dialog.getLine(), dialog.getArea()).toString() + '\n');
				}else if(getFrame().getTglbtnHexagon().isSelected()) {
					HexagonDlg dialog = new HexagonDlg(e.getX(), e.getY(), lineColor, areaColor);
					
					dialog.setModal(true);
					dialog.setLocationRelativeTo(getFrame());
					dialog.setVisible(true);
					
					AddHexagon addHexagon = new AddHexagon(model, new HexagonAdapter(e.getX(), e.getY(), dialog.getSqwidth(), dialog.getLine(), dialog.getArea()));
					doCommand(addHexagon);
					getFrame().getLogTextArea().append("Add: "+ new HexagonAdapter(e.getX(), e.getY(), dialog.getSqwidth(), dialog.getLine(), dialog.getArea()).toString() + '\n');
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
					if(mousePressed == 0) {
						startPoint = new Point(e.getX(), e.getY(), lineColor);
						mousePressed = 1;
					}else {
						//model.getShapeList().add(new Line(startPoint, new Point(e.getX(), e.getY(), lineColor), lineColor));
						AddLine addLine = new AddLine(model, new Line(startPoint, new Point(e.getX(), e.getY(), lineColor), lineColor));
						doCommand(addLine);
						getFrame().getLogTextArea().append("Add: "+ new Line(startPoint, new Point(e.getX(), e.getY(), lineColor), lineColor).toString() + '\n');
						mousePressed = 0;
						getView().repaint();
					}
					
				}
			}
			
			/**
			 * 
			 */
			/*@Override
			public void mouseReleased(MouseEvent e) {
				if(getFrame().getTglbtnLine().isSelected()){
					model.getShapeList().add(new Line(startPoint, new Point(e.getX(), e.getY(), lineColor), lineColor));
					getFrame().getLogTextArea().append("Add: "+ new Line(startPoint, new Point(e.getX(), e.getY(), lineColor), lineColor).toString() + '\n');
				}
			}*/
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
			/*@Override
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
			}*/
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
						if(s instanceof Point) {
							int result = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete tacku?", "Warning!", JOptionPane.WARNING_MESSAGE);
							if(JOptionPane.OK_OPTION == result)
							{	
								it.remove();
								DeletePoint deletePoint = new DeletePoint(model, (Point) s);
								doCommand(deletePoint);
								getFrame().getLogTextArea().append("Deleted: "+ s.toString() + '\n');
							}
						}else if(s instanceof Line) {
							int result = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete liniju?", "Warning!", JOptionPane.WARNING_MESSAGE);
							if(JOptionPane.OK_OPTION == result) 
							{
								it.remove();
								DeleteLine deleteLine = new DeleteLine(model, (Line) s);
								doCommand(deleteLine);
								getFrame().getLogTextArea().append("Deleted: "+ s.toString() + '\n');
							}
						}else if(s instanceof Circle) {
							int result = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete krug?", "Warning!", JOptionPane.WARNING_MESSAGE);
							if(JOptionPane.OK_OPTION == result) 
							{
								it.remove();
								DeleteCircle deleteCircle = new DeleteCircle(model, (Circle) s);
								doCommand(deleteCircle);
								getFrame().getLogTextArea().append("Deleted: "+ s.toString() + '\n');
							}
						}else if(s instanceof Square) {
							int result = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete kvadrat?", "Warning!", JOptionPane.WARNING_MESSAGE);
							if(JOptionPane.OK_OPTION == result) 
							{
								it.remove();
								DeleteSquare deleteSquare = new DeleteSquare(model, (Square) s);
								doCommand(deleteSquare);
								getFrame().getLogTextArea().append("Deleted: "+ s.toString() + '\n');
							}
						}else if(s instanceof Rectangle) {
							int result = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete pravougaonik?", "Warning!", JOptionPane.WARNING_MESSAGE);
							if(JOptionPane.OK_OPTION == result) 
							{
								it.remove();
								DeleteRectangle deleteRectangle = new DeleteRectangle(model, (Rectangle) s);
								doCommand(deleteRectangle);
								getFrame().getLogTextArea().append("Deleted: "+ s.toString() + '\n');
							}
						}else if(s instanceof HexagonAdapter) {
							int result = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete pravougaonik?", "Warning!", JOptionPane.WARNING_MESSAGE);
							if(JOptionPane.OK_OPTION == result) 
							{
								it.remove();
								DeleteHexagon deleteHexagon = new DeleteHexagon(model, (HexagonAdapter) s);
								doCommand(deleteHexagon);
								getFrame().getLogTextArea().append("Deleted: "+ s.toString() + '\n');
							}
						}
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
		
		getFrame().getBtnAreaColor().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				areaColor = JColorChooser.showDialog(null, "Izaberi boju", areaColor);
				if(areaColor != null){
					areaColor = areaColor;
					getFrame().getBtnAreaColor().setBackground(areaColor);
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
				if(selectedFile == null) {
					System.out.println("Nije izabran fajl");
				} else {
					LinkedList<String> stringLog = getStringLine(selectedFile);
  					for (int i = 0; i < stringLog.size(); i++) {
						stringListLine = stringLog.get(i);
						System.out.println(i + ". " + stringListLine);
						stringListArray = stringListLine.split(" ");
						stringListCmd = stringListArray[0].split(":");
						stringListShape = stringListArray[1].split(":"); 
						if (stringListCmd[0].equals("Add")){
							if (stringListShape[0].equals("Point")) {
								drawPoint(stringListArray);
							}else if(stringListShape[0].equals("Line")) {
								drawLine(stringListArray);
							}else if(stringListShape[0].equals("Hexagon")) {
								drawHexagon(stringListArray);
							}else if(stringListShape[0].equals("Circle")) {
								drawCircle(stringListArray);
							}else if(stringListShape[0].equals("Square")) {
								drawSquare(stringListArray);
							}else if(stringListShape[0].equals("Rectangle")) {
								drawRectangle(stringListArray); 
							}
						}else if(stringListCmd[0].equals("Deleted")){
							
						}
						
					}
					getView().repaint();
				}
				
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
		
		getFrame().getBtnModify().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Iterator it = model.getShapeList().iterator();
				while (it.hasNext()){
					Shape s = (Shape)it.next();
					if(s.isSelected()){					
						if(s instanceof Point) {
							Point p = (Point) s;
							PointDlg dialog = new PointDlg(p.getLineColor(), p.getX(), p.getY());
							dialog.setVisible(true);
							dialog.setLocationRelativeTo(getFrame());
							
							ModifyPoint modifiyPoint = new ModifyPoint(p, new Point(dialog.getX(),dialog.getY(),dialog.getPointColor()));
							doCommand(modifiyPoint);
							getFrame().getLogTextArea().append("Modified: "+ modifiyPoint.toString() + '\n');
						}
					}
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
	
	public void drawPoint(String stringLine[]) {
		String px[] = stringLine[3].split(",");
		String py[] = stringLine[5].split(";");
		String sr[] = stringLine[8].split(",");
		String sg[] = stringLine[10].split(",");
		String sb[] = stringLine[12].split(";");
		int x = Integer.parseInt(px[0]);
		int y = Integer.parseInt(py[0]);
		int r = Integer.parseInt(sr[0]);
		int g = Integer.parseInt(sg[0]);
		int b = Integer.parseInt(sb[0]);
		AddPoint addPoint = new AddPoint(model, new Point(x, y, new Color(r, g, b)));
		doCommand(addPoint);
		getFrame().getLogTextArea().append(stringListLine  + '\n');
	}
	
	public void drawLine(String stringLine[]) {
		String startX[] = stringLine[5].split(",");
		String startY[] = stringLine[7].split("-->");
		String endX[] = stringLine[11].split(",");
		String endY[] = stringLine[13].split(";");
		String lr[] = stringLine[16].split(",");
		String lg[] = stringLine[18].split(",");
		String lb[] = stringLine[20].split(";");
		int sx = Integer.parseInt(startX[0]);
		int sy = Integer.parseInt(startY[0]);
		int ex = Integer.parseInt(endX[0]);
		int ey = Integer.parseInt(endY[0]);
		int r = Integer.parseInt(lr[0]);
		int g = Integer.parseInt(lg[0]);
		int b = Integer.parseInt(lb[0]);
		AddLine addLine = new AddLine(model, new Line(new Point(sx, sy), new Point(ex, ey), new Color(r, g, b)));
		doCommand(addLine);
		getFrame().getLogTextArea().append(stringListLine  + '\n');
	}
	
	public void drawHexagon(String stringLine[]) {
		String cx[] = stringLine[5].split(",");
		String cy[] = stringLine[7].split(";");
		String radius[] = stringLine[9].split(";");
		String lr[] = stringLine[13].split(",");
		String lg[] = stringLine[15].split(",");
		String lb[] = stringLine[17].split(";");
		String ar[] = stringLine[21].split(",");
		String ag[] = stringLine[23].split(",");
		String ab[] = stringLine[25].split(";");
		int x = Integer.parseInt(cx[0]);
		int y = Integer.parseInt(cy[0]);
		int r = Integer.parseInt(radius[0]);
		int lnr = Integer.parseInt(lr[0]);
		int lng = Integer.parseInt(lg[0]);
		int lnb = Integer.parseInt(lb[0]);
		int arr = Integer.parseInt(ar[0]);
		int arg = Integer.parseInt(ag[0]);
		int arb = Integer.parseInt(ab[0]);
		AddHexagon addHexagon = new AddHexagon(model, new HexagonAdapter(x, y, r, new Color(lnr, lng, lnb), new Color(arr, arg, arb)));
		doCommand(addHexagon);
		getFrame().getLogTextArea().append(stringListLine  + '\n');
	}
	
	public void drawCircle(String stringLine[]) {
		String cx[] = stringLine[5].split(",");
		String cy[] = stringLine[7].split(";");
		String radius[] = stringLine[9].split(";");
		String lr[] = stringLine[13].split(",");
		String lg[] = stringLine[15].split(",");
		String lb[] = stringLine[17].split(";");
		String ar[] = stringLine[21].split(",");
		String ag[] = stringLine[23].split(",");
		String ab[] = stringLine[25].split(";");
		int x = Integer.parseInt(cx[0]);
		int y = Integer.parseInt(cy[0]);
		int r = Integer.parseInt(radius[0]);
		int lnr = Integer.parseInt(lr[0]);
		int lng = Integer.parseInt(lg[0]);
		int lnb = Integer.parseInt(lb[0]);
		int arr = Integer.parseInt(ar[0]);
		int arg = Integer.parseInt(ag[0]);
		int arb = Integer.parseInt(ab[0]);
		AddCircle addCircle = new AddCircle(model, new Circle(new Point(x, y), r, new Color(lnr, lng, lnb), new Color(arr, arg, arb)));
		doCommand(addCircle);
		getFrame().getLogTextArea().append(stringListLine  + '\n');
	}
	
	public void drawSquare(String stringLine[]) {
		String uplcx[] = stringLine[6].split(",");
		String uplcy[] = stringLine[8].split(";");
		String radius[] = stringLine[10].split(";");
		String lr[] = stringLine[14].split(",");
		String lg[] = stringLine[16].split(",");
		String lb[] = stringLine[18].split(";");
		String ar[] = stringLine[22].split(",");
		String ag[] = stringLine[24].split(",");
		String ab[] = stringLine[26].split(";");
		int x = Integer.parseInt(uplcx[0]);
		int y = Integer.parseInt(uplcy[0]);
		int r = Integer.parseInt(radius[0]);
		int lnr = Integer.parseInt(lr[0]);
		int lng = Integer.parseInt(lg[0]);
		int lnb = Integer.parseInt(lb[0]);
		int arr = Integer.parseInt(ar[0]);
		int arg = Integer.parseInt(ag[0]);
		int arb = Integer.parseInt(ab[0]);
		AddSquare addSquare = new AddSquare(model, new Square(new Point(x, y), r, new Color(lnr, lng, lnb), new Color(arr, arg, arb)));
		doCommand(addSquare);
		getFrame().getLogTextArea().append(stringListLine  + '\n');
	}
	
	public void drawRectangle(String stringLine[]) {
		String uplcx[] = stringLine[6].split(",");
		String uplcy[] = stringLine[8].split(";");
		String stranicaA[] = stringListArray[10].split(";");
		String stranicaB[] = stringListArray[12].split(";");
		String lr[] = stringListArray[16].split(",");
		String lg[] = stringListArray[18].split(",");
		String lb[] = stringListArray[20].split(";");
		String ar[] = stringListArray[24].split(",");
		String ag[] = stringListArray[26].split(",");
		String ab[] = stringListArray[28].split(";");
		int x = Integer.parseInt(uplcx[0]);
		int y = Integer.parseInt(uplcy[0]);
		int stranicaAA = Integer.parseInt(stranicaA[0]);
		int stranicaBB = Integer.parseInt(stranicaB[0]);
		int lnr = Integer.parseInt(lr[0]);
		int lng = Integer.parseInt(lg[0]);
		int lnb = Integer.parseInt(lb[0]);
		int arr = Integer.parseInt(ar[0]);
		int arg = Integer.parseInt(ag[0]);
		int arb = Integer.parseInt(ab[0]);
		AddRectangle addRectangle = new AddRectangle(model, new Rectangle(new Point(x, y), stranicaAA, stranicaBB, new Color(lnr, lng, lnb), new Color(arr, arg, arb)));
		doCommand(addRectangle);
		getFrame().getLogTextArea().append(stringListLine  + '\n');
	}
	
	public LinkedList getStringLine(File file) {
		LinkedList<String> stringList = new LinkedList<String>();
		BufferedReader br = null;
		String line;
		try {
			br = new BufferedReader(new FileReader(file));
			
			while ((line = br.readLine()) != null) {
				stringList.add(line);
			}
			
			br.close();
		} 
		catch (Exception ee) {
				ee.printStackTrace();
		}
		return stringList;
	}
	
}
