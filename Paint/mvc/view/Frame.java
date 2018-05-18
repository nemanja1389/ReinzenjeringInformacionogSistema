package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Model;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;

/**
 * 
 * @author Nemanja
 * @version 1.0
 */
public class Frame extends JFrame {

	/**
	 * 
	 */
	private Color lineColor = Color.BLACK;
	private Color areaColor = Color.WHITE;
	private JPanel contentPane;
	private View paintPnl;
	private JPanel toolPnl;
	private JPanel coordPnl;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnSelect;
	private JToggleButton tglbtnLine;
	private JButton btnDelete;
	private JButton btnLineColor;
	private JButton btnSave;
	private JButton btnOpen;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextArea coordText;
	private JTabbedPane tabbedPane;
	private JPanel paintTab;
	private JPanel logPnl;
	private JTextArea logTextArea;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnModify;
	private JToggleButton tglbtnCircle;
	private JButton btnAreaColor;
	private JToggleButton tglbtnSquare;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Model model = new Model();
					View view = new View(model);
					Frame frame = new Frame(view);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame(View paintPnl) {
		this.paintPnl = paintPnl;
		this.paintPnl.setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		/**
		 * 
		 */
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		paintTab = new JPanel();
		tabbedPane.addTab("Paint", null, paintTab, null);
		paintTab.setLayout(new BorderLayout(0, 0));
		
		paintTab.add(paintPnl, BorderLayout.CENTER);
		
		toolPnl = new JPanel();
		paintTab.add(toolPnl, BorderLayout.NORTH);
		toolPnl.setLayout(new BoxLayout(toolPnl, BoxLayout.X_AXIS));
		
		btnOpen = new JButton("Open");
		toolPnl.add(btnOpen);
		
		btnSave = new JButton("Save");
		toolPnl.add(btnSave);
		
		btnUndo = new JButton("Undo");
		toolPnl.add(btnUndo);
		
		btnRedo = new JButton("Redo");
		toolPnl.add(btnRedo);
		
		btnModify = new JButton("Modify");
		toolPnl.add(btnModify);
		
		tglbtnPoint = new JToggleButton("Point");
		buttonGroup.add(tglbtnPoint);
		toolPnl.add(tglbtnPoint);
		
		tglbtnLine = new JToggleButton("Line");
		buttonGroup.add(tglbtnLine);
		toolPnl.add(tglbtnLine);
		
		tglbtnCircle = new JToggleButton("Circle");
		buttonGroup.add(tglbtnCircle);
		toolPnl.add(tglbtnCircle);
		
		tglbtnSquare = new JToggleButton("Square");
		buttonGroup.add(tglbtnSquare);
		toolPnl.add(tglbtnSquare);
		
		tglbtnSelect = new JToggleButton("Select");
		buttonGroup.add(tglbtnSelect);
		toolPnl.add(tglbtnSelect);
		
		btnDelete = new JButton("Delete");
		toolPnl.add(btnDelete);
		
		btnLineColor = new JButton("Line Color");
		btnLineColor.setBackground(lineColor);
		btnLineColor.setForeground(Color.WHITE);
		toolPnl.add(btnLineColor);
		
		btnAreaColor = new JButton("Area Color");
		btnAreaColor.setBackground(areaColor);
		btnAreaColor.setForeground(Color.BLACK);
		toolPnl.add(btnAreaColor);
		
		coordPnl = new JPanel();
		paintTab.add(coordPnl, BorderLayout.SOUTH);
		coordPnl.setLayout(new BoxLayout(coordPnl, BoxLayout.X_AXIS));
		
		coordText = new JTextArea();
		coordText.setBackground(SystemColor.menu);
		coordPnl.add(coordText);
		
		logPnl = new JPanel();
		tabbedPane.addTab("Log", null, logPnl, null);
		logPnl.setLayout(new BorderLayout(0, 0));
		
		logTextArea = new JTextArea();
		logPnl.add(logTextArea, BorderLayout.CENTER);
	}

	/**
	 * 
	 * @return
	 */
	public View getPaintPnl() {
		return paintPnl;
	}

	/**
	 * 
	 * @param paintPnl
	 */
	public void setPaintPnl(View paintPnl) {
		this.paintPnl = paintPnl;
	}

	/**
	 * 
	 * @return
	 */
	public JPanel getToolPnl() {
		return toolPnl;
	}

	/**
	 * 
	 * @param toolPnl
	 */
	public void setToolPnl(JPanel toolPnl) {
		this.toolPnl = toolPnl;
	}
	
	/**
	 * 
	 * @return
	 */
	public JPanel getCoordPnl() {
		return coordPnl;
	}

	/**
	 * 
	 * @param coordPnl
	 */
	public void setCoordPnl(JPanel coordPnl) {
		this.coordPnl = coordPnl;
	}

	/**
	 * 
	 * @return
	 */
	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	/**
	 * 
	 * @param tglbtnPoint
	 */
	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	/**
	 * 
	 * @return
	 */
	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	/**
	 * 
	 * @param tglbtnSelect
	 */
	public void setTglbtnSelect(JToggleButton tglbtnSelect) {
		this.tglbtnSelect = tglbtnSelect;
	}
	
	/**
	 * 
	 * @return
	 */
	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	/**
	 * 
	 * @param tglbtnLine
	 */
	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getBtnDelete() {
		return btnDelete;
	}

	/**
	 * 
	 * @param btnDelete
	 */
	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getBtnLineColor() {
		return btnLineColor;
	}

	/**
	 * 
	 * @param btnLineColor
	 */
	public void setBtnLineColor(JButton btnLineColor) {
		this.btnLineColor = btnLineColor;
	}
	
	/**
	 * 
	 * @return
	 */
	public JButton getBtnSave() {
		return btnSave;
	}

	/**
	 * 
	 * @param btnSave
	 */
	public void setBtnSave(JButton btnSave) {
		this.btnSave = btnSave;
	}

	/**
	 * 
	 * @return
	 */
	public JButton getBtnOpen() {
		return btnOpen;
	}

	/**
	 * 
	 * @param btnOpen
	 */
	public void setBtnOpen(JButton btnOpen) {
		this.btnOpen = btnOpen;
	}

	/**
	 * 
	 * @return
	 */
	public JTextArea getCoordText() {
		return coordText;
	}

	/**
	 * 
	 * @param coordText
	 */
	public void setCoordText(JTextArea coordText) {
		this.coordText = coordText;
	}

	/**
	 * 
	 * @return
	 */
	public JTextArea getLogTextArea() {
		return logTextArea;
	}

	/**
	 * 
	 * @param logTextArea
	 */
	public void setLogTextArea(JTextArea logTextArea) {
		this.logTextArea = logTextArea;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public Color getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}

	public JButton getBtnAreaColor() {
		return btnAreaColor;
	}

	public void setBtnAreaColor(JButton btnAreaColor) {
		this.btnAreaColor = btnAreaColor;
	}

	public JToggleButton getTglbtnSquare() {
		return tglbtnSquare;
	}

	public void setTglbtnSquare(JToggleButton tglbtnSquare) {
		this.tglbtnSquare = tglbtnSquare;
	}
	
	
}
