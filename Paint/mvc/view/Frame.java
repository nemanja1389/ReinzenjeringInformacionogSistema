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
	private JPanel contentPane;
	private View paintPnl;
	private JPanel toolPnl;
	private JPanel coordPnl;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnSelect;
	private JButton btnDelete;
	private JButton btnLineColor;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextArea coordText;
	
	
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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		/**
		 * 
		 */
		contentPane.add(paintPnl, BorderLayout.CENTER);
		
		toolPnl = new JPanel();
		contentPane.add(toolPnl, BorderLayout.NORTH);
		toolPnl.setLayout(new BoxLayout(toolPnl, BoxLayout.X_AXIS));
		
		tglbtnPoint = new JToggleButton("Point");
		buttonGroup.add(tglbtnPoint);
		toolPnl.add(tglbtnPoint);
		
		tglbtnSelect = new JToggleButton("Select");
		buttonGroup.add(tglbtnSelect);
		toolPnl.add(tglbtnSelect);
		
		btnDelete = new JButton("Delete");
		toolPnl.add(btnDelete);
		
		btnLineColor = new JButton("Line Color");
		btnLineColor.setBackground(lineColor);
		btnLineColor.setForeground(Color.WHITE);
		toolPnl.add(btnLineColor);
		
		coordPnl = new JPanel();
		contentPane.add(coordPnl, BorderLayout.SOUTH);
		coordPnl.setLayout(new BoxLayout(coordPnl, BoxLayout.X_AXIS));
		
		coordText = new JTextArea();
		coordText.setBackground(SystemColor.menu);
		coordPnl.add(coordText);
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
	
	

}
